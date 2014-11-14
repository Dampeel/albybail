package albybail

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
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
	
	def "facture contrat avec révision (cas normal)"() {
		
		def contrat = new Contrat(
			nom:			"Contrat",
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/06/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/06/2022"),
			dureeRevision:	1,
			chezNotaire:	false,
			estTermine:		false,
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			locataire:		Locataire.first(),
			profil:			Profil.findByNom("mensuel")
		).addToLocaux(Local.first()).save()
			
		def revision = new Revision(
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/06/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/06/2014"),
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			indice:			100,
			contrat:		contrat
		).save()
		
		contrat.revisionActive = revision
		contrat.save()
		
		Date date = Date.parse("dd/MM/yyyy", "01/01/2014")
		
		def facturables = service.creerFacturables(contrat, date)
		
		expect:
		
		1100 == facturables[0].valeur
		100 == facturables[1].valeur
		2 == contrat.facturables?.size()
	}
	
	def "facture contrat sans révision courante (cas normal)"() {
		
		def contrat = new Contrat(
			nom:			"Contrat",
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/06/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/06/2022"),
			dureeRevision:	1,
			chezNotaire:	false,
			estTermine:		false,
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			locataire:		Locataire.first(),
			profil:			Profil.findByNom("mensuel")
		).addToLocaux(Local.first()).save()
			
		def revision = new Revision(
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/06/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/06/2014"),
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			indice:			100,
			contrat:		contrat
		).save()
		
		contrat.revisionActive = revision
		contrat.save()
		
		Date date = Date.parse("dd/MM/yyyy", "01/08/2014")
		
		def facturables = service.creerFacturables(contrat, date)
		
		expect:
		
		1100 == facturables[0].valeur
		100 == facturables[1].valeur
		2 == contrat.facturables?.size()
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
