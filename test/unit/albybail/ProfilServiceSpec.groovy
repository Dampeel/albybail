package albybail

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ProfilService)
@Mock([Batiment, Local, Locataire, Plage, Profil, Contrat])
class ProfilServiceSpec extends Specification {
	
    void "plage mensuelle debut et fin"() {
		
		def jan = new Plage(moisDebut: 1, moisFin: 1)
		def fev = new Plage(moisDebut: 2, moisFin: 2)
		
		Date date1 = Date.parse("dd/MM/yyyy", "01/02/2013")
		Date date2 = Date.parse("dd/MM/yyyy", "01/01/2014")
		Date date3 = Date.parse("dd/MM/yyyy", "01/02/2016") // année bisextile
		Date date4 = Date.parse("dd/MM/yyyy", "01/01/2018")
		
		expect:
		
		"01/01/2018" == jan.dateDebut(date4).format('dd/MM/yyyy')
		"31/01/2018" == jan.dateFin(date4).format('dd/MM/yyyy')
		
		"01/01/2014" == jan.dateDebut(date2).format('dd/MM/yyyy')
		"31/01/2014" == jan.dateFin(date2).format('dd/MM/yyyy')
		
		"29/02/2016" == fev.dateFin(date3).format('dd/MM/yyyy')
		"28/02/2013" == fev.dateFin(date1).format('dd/MM/yyyy')
    }
	
    void "plage trimestrielle debut et fin"() {
		
		def tr1 = new Plage(moisDebut: 1, moisFin: 3)
		def tr3 = new Plage(moisDebut: 7, moisFin: 9)
		
		Date date1 = Date.parse("dd/MM/yyyy", "01/01/2018")
		Date date2 = Date.parse("dd/MM/yyyy", "01/01/2014")
		
		expect:
		
		"01/01/2018" == tr1.dateDebut(date1).format('dd/MM/yyyy')
		"31/03/2018" == tr1.dateFin(date1).format('dd/MM/yyyy')
		
		"01/01/2014" == tr1.dateDebut(date2).format('dd/MM/yyyy')
		"31/03/2014" == tr1.dateFin(date2).format('dd/MM/yyyy')
		
		"01/07/2018" == tr3.dateDebut(date1).format('dd/MM/yyyy')
		"30/09/2018" == tr3.dateFin(date1).format('dd/MM/yyyy')
		
		"01/07/2014" == tr3.dateDebut(date2).format('dd/MM/yyyy')
		"30/09/2014" == tr3.dateFin(date2).format('dd/MM/yyyy')
    }
	
	void "plage mensuelle plage courante"() {
		
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
			profil:			Profil.findByNom("mensuel")
		).addToLocaux(Local.first())
		
		Date date1 = Date.parse("dd/MM/yyyy", "01/01/2013")
		Date date2 = Date.parse("dd/MM/yyyy", "31/01/2014")
		Date date3 = Date.parse("dd/MM/yyyy", "29/02/2016") // année bisextile
		Date date4 = Date.parse("dd/MM/yyyy", "28/02/2018")
		Date date5 = Date.parse("dd/MM/yyyy", "15/08/2018")
		
		expect:
		
		"Du 1 au 1" == service.plageCourante(contrat, date1).toString()
		"Du 1 au 1" == service.plageCourante(contrat, date2).toString()
		"Du 2 au 2" == service.plageCourante(contrat, date3).toString()
		"Du 2 au 2" == service.plageCourante(contrat, date4).toString()
		"Du 8 au 8" == service.plageCourante(contrat, date5).toString()
	}
	
	void "plage trimestrielle plage courante"() {
		
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
			profil:			Profil.findByNom("trimestriel")
		).addToLocaux(Local.first())
		
		Date date1 = Date.parse("dd/MM/yyyy", "01/01/2013")
		Date date2 = Date.parse("dd/MM/yyyy", "31/03/2014")
		Date date3 = Date.parse("dd/MM/yyyy", "29/02/2016") // année bisextile
		Date date4 = Date.parse("dd/MM/yyyy", "28/02/2018")
		Date date5 = Date.parse("dd/MM/yyyy", "15/08/2018")
		Date date6 = Date.parse("dd/MM/yyyy", "01/08/2018")
		
		expect:
		
		"Du 1 au 3" == service.plageCourante(contrat, date1).toString()
		"Du 1 au 3" == service.plageCourante(contrat, date2).toString()
		"Du 1 au 3" == service.plageCourante(contrat, date3).toString()
		"Du 1 au 3" == service.plageCourante(contrat, date4).toString()
		"Du 7 au 9" == service.plageCourante(contrat, date5).toString()
		"Du 7 au 9" == service.plageCourante(contrat, date6).toString()
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
