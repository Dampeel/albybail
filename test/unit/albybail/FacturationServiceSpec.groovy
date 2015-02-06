package albybail

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

import java.math.MathContext

import spock.lang.Specification

@TestFor(FacturationService)
@Mock([Batiment, Local, Locataire, Plage, Profil, Contrat, Revision, Facturable, ProfilService, RevisionService])
class FacturationServiceSpec extends Specification {

	def "facture contrat sans révision"() {

		def contrat = new Contrat(
			nom:			"Contrat",
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/11/2002"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/11/2022"),
			dureeRevision:	1,
			chezNotaire:	false,
			estTermine:		false,
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			locataire:		Locataire.first(),
			profil:			Profil.first()
		).addToLocaux(Local.first()).save()
		
		Date date = Date.parse("dd/MM/yyyy", "01/01/2014")
		service.creerFacturables(contrat, date)
		
		expect:
		
		null == contrat.facturables?.size()
	}
	
	def "facture contrat avec révisions"() {
		
		def contrat = new Contrat(
			nom:			"Contrat",
			dateDebut: 		Date.parse("dd/MM/yyyy", "16/06/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "16/06/2022"),
			dureeRevision:	1,
			chezNotaire:	false,
			estTermine:		false,
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			locataire:		Locataire.first(),
			profil:			Profil.findByNom("mensuel")
		).addToLocaux(Local.first()).save()
		
		def revision1 = new Revision(
			dateDebut: 		Date.parse("dd/MM/yyyy", "16/06/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "16/06/2014"),
			montantLoyer:	1100.00,
			indice:			100,
			contrat:		contrat
		).save()

		def revision2 = new Revision(
			dateDebut: 		Date.parse("dd/MM/yyyy", "17/06/2014"),
			dateFin: 		Date.parse("dd/MM/yyyy", "31/06/2015"),
			montantLoyer:	1200.00,
			indice:			100,
			contrat:		contrat
		).save()
			
		def revision3 = new Revision(
			dateDebut: 		Date.parse("dd/MM/yyyy", "01/07/2015"),
			dateFin: 		Date.parse("dd/MM/yyyy", "29/06/2016"),
			montantLoyer:	1300.00,
			indice:			100,
			contrat:		contrat
		).save()
			
		def revision4 = new Revision(
			dateDebut: 		Date.parse("dd/MM/yyyy", "30/06/2016"),
			dateFin: 		Date.parse("dd/MM/yyyy", "01/06/2017"),
			montantLoyer:	1400.00,
			indice:			100,
			contrat:		contrat
		).save()
		
		contrat.revisionActive = revision4
		contrat.save()
		
		Date date1 = Date.parse("dd/MM/yyyy", "01/06/2013") // premier mois rev1
		Date date2 = Date.parse("dd/MM/yyyy", "31/12/2013") // milieu rev1
		Date date3 = Date.parse("dd/MM/yyyy", "22/05/2014") // dernier mois entier rev1
		
		Date date4 = Date.parse("dd/MM/yyyy", "01/06/2014") // transition rev1 rev2
		
		Date date5 = Date.parse("dd/MM/yyyy", "01/07/2014") // premier mois entier rev2
		Date date6 = Date.parse("dd/MM/yyyy", "31/01/2015") // milieu rev2
		Date date7 = Date.parse("dd/MM/yyyy", "15/05/2015") // dernier mois entier rev2
		
		Date date8 = Date.parse("dd/MM/yyyy", "08/06/2015") // transition rev2 rev3
		Date date9 = Date.parse("dd/MM/yyyy", "03/07/2015") // transition rev2 rev3
		
		Date date10 = Date.parse("dd/MM/yyyy", "08/06/2016") // transition rev3 rev4
		Date date11 = Date.parse("dd/MM/yyyy", "03/07/2016") // transition rev3 rev4
		
		Date date12 = Date.parse("dd/MM/yyyy", "30/06/2017") // dernier mois rev4
		
		Date date19 = Date.parse("dd/MM/yyyy", "30/04/2018") // apres rev4
		Date date20 = Date.parse("dd/MM/yyyy", "12/06/2022") // dernier mois du contrat
		
		expect:
		
		550 == service.calculLoyer(contrat, date1).valeur
		1100 == service.calculLoyer(contrat, date2).valeur
		1100 == service.calculLoyer(contrat, date3).valeur
		1146.67 == service.calculLoyer(contrat, date4).valeur
		1200 == service.calculLoyer(contrat, date5).valeur
		1200 == service.calculLoyer(contrat, date6).valeur
		1200 == service.calculLoyer(contrat, date7).valeur
		1200 == service.calculLoyer(contrat, date8).valeur
		1300 == service.calculLoyer(contrat, date9).valeur
		1303.33 == service.calculLoyer(contrat, date10).valeur
		1400 == service.calculLoyer(contrat, date11).valeur
		1400 == service.calculLoyer(contrat, date12).valeur
		
		1400 == service.calculLoyer(contrat, date19).valeur
		746.67 == service.calculLoyer(contrat, date20).valeur
		
		50 == service.calculCharges(contrat, date1).valeur
		100 == service.calculCharges(contrat, date9).valeur
		53.33 == service.calculCharges(contrat, date20).valeur
	}
	
    def setup() {
		
		def bat = new Batiment(nom: "batiment").save(failOnError: true)
		def local = new Local(nom: "local", surface: 1, batiment: bat).save(failOnError: true)
		def locat = new Locataire(nom: "locataire", adresse: "adresse").save(failOnError: true)
		
		def jan = new Plage(moisDebut: 1, moisFin: 1).save()
		def fev = new Plage(moisDebut: 2, moisFin: 2).save()
		def mar = new Plage(moisDebut: 3, moisFin: 3).save()
		def avr = new Plage(moisDebut: 4, moisFin: 4).save()
		def mai = new Plage(moisDebut: 5, moisFin: 5).save()
		def jun = new Plage(moisDebut: 6, moisFin: 6).save()
		def jul = new Plage(moisDebut: 7, moisFin: 7).save()
		def aou = new Plage(moisDebut: 8, moisFin: 8).save()
		def sep = new Plage(moisDebut: 9, moisFin: 9).save()
		def oct = new Plage(moisDebut: 10, moisFin: 10).save()
		def nov = new Plage(moisDebut: 11, moisFin: 11).save()
		def dec = new Plage(moisDebut: 12, moisFin: 12).save()
		
		def tr1 = new Plage(moisDebut: 1, moisFin: 3).save()
		def tr2 = new Plage(moisDebut: 4, moisFin: 6).save()
		def tr3 = new Plage(moisDebut: 7, moisFin: 9).save()
		def tr4 = new Plage(moisDebut: 10, moisFin: 12).save()
		
		def mens = new Profil(nom: "mensuel")
		mens.addToPlages(jan)
		mens.addToPlages(fev)
		mens.addToPlages(mar)
		mens.addToPlages(avr)
		mens.addToPlages(mai)
		mens.addToPlages(jun)
		mens.addToPlages(jul)
		mens.addToPlages(aou)
		mens.addToPlages(sep)
		mens.addToPlages(oct)
		mens.addToPlages(nov)
		mens.addToPlages(dec)
		mens.save(failOnError: true)
		
		def trim = new Profil(nom: "trimestriel")
		trim.addToPlages(tr1)
		trim.addToPlages(tr2)
		trim.addToPlages(tr3)
		trim.addToPlages(tr4)
		trim.save(failOnError: true)
    }

    def cleanup() {
    }
}
