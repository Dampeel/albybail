package albybail

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ContratService)
@Mock([Batiment, Local, Locataire, Plage, Profil, Contrat, Revision, ProfilService, RevisionService])
class ContratServiceSpec extends Specification {
	
	void "contrat terminé (non révisable ni facturable)"() {
		
		def contrat = new Contrat(
			nom:			"Contrat",
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/11/2002"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/11/2022"),
			dureeRevision:	1,
			chezNotaire:	false,
			estTermine:		true,
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			locataire:		Locataire.first(),
			profil:			Profil.first()
		).addToLocaux(Local.first())
		
		Date date = Date.parse("dd/MM/yyyy", "01/01/2014")
		
		expect:
		
		false == service.estRevisable(contrat, date)
		false == service.estFacturable(contrat, date)
	}
	
	void "contrat sans révision (revisable mais pas facturable)"() {

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
		
		expect:
		
		true == service.estRevisable(contrat, date)
		false == service.estFacturable(contrat, date)
	}
	
	void "revisable contrat mensuel avec révision (fonction de la date)"() {

		def contrat = new Contrat(
			nom:			"Contrat",
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/06/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/06/2015"),
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
			montantLoyer:	1111.00,
			montantCharges:	100.00,
			indice:			101,
			contrat:		contrat
		).save()
		
		contrat.revisionActive = revision
		contrat.save()
		
		Date date1 = Date.parse("dd/MM/yyyy", "10/01/2014")
		Date date2 = Date.parse("dd/MM/yyyy", "01/06/2014")
		Date date3 = Date.parse("dd/MM/yyyy", "29/02/2016")
		Date date4 = Date.parse("dd/MM/yyyy", "30/06/2014")
		Date date5 = Date.parse("dd/MM/yyyy", "04/07/2014")
		
		expect:
		
		false == service.estRevisable(contrat, date1)
		true == service.estRevisable(contrat, date2)
		true == service.estRevisable(contrat, date3)
		true == service.estRevisable(contrat, date4)
		true == service.estRevisable(contrat, date5)
	}
	
	void "revisable contrat mensuel avec révision (début et fin de contrat)"() {

		def contrat = new Contrat(
			nom:			"Contrat",
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/06/2014"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/06/2015"),
			dureeRevision:	1,
			chezNotaire:	false,
			estTermine:		false,
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			locataire:		Locataire.first(),
			profil:			Profil.findByNom("mensuel")
		).addToLocaux(Local.first()).save()
			
		def revision = new Revision(
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/06/2014"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/06/2015"),
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			indice:			100,
			contrat:		contrat
		).save()
		
		contrat.revisionActive = revision
		contrat.save()
	
		Date date1 = Date.parse("dd/MM/yyyy", "01/06/2014")
		Date date2 = Date.parse("dd/MM/yyyy", "01/06/2015")
		Date date3 = Date.parse("dd/MM/yyyy", "18/06/2014")
		Date date4 = Date.parse("dd/MM/yyyy", "18/06/2015")
		
		expect:
		
		false == service.estRevisable(contrat, date1)
		false == service.estRevisable(contrat, date2)
		false == service.estRevisable(contrat, date3)
		false == service.estRevisable(contrat, date4)
	}
	
	void "revisable contrat trimestriel avec révision (fonction de la date)"() {

		def contrat = new Contrat(
			nom:			"Contrat",
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/08/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/08/2022"),
			dureeRevision:	1,
			chezNotaire:	false,
			estTermine:		false,
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			locataire:		Locataire.first(),
			profil:			Profil.findByNom("trimestriel")
		).addToLocaux(Local.first()).save()
		
		def revision = new Revision(
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/08/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/08/2014"),
			montantLoyer:	1111.00,
			montantCharges:	100.00,
			indice:			101,
			contrat:		contrat
		).save()
		
		contrat.revisionActive = revision
		contrat.save()
		
		Date date1 = Date.parse("dd/MM/yyyy", "10/01/2014")
		Date date2 = Date.parse("dd/MM/yyyy", "30/06/2014")
		Date date3 = Date.parse("dd/MM/yyyy", "01/07/2014")
		Date date4 = Date.parse("dd/MM/yyyy", "31/07/2014")
		Date date5 = Date.parse("dd/MM/yyyy", "01/08/2014")
		Date date6 = Date.parse("dd/MM/yyyy", "31/08/2014")
		Date date7 = Date.parse("dd/MM/yyyy", "01/09/2014")
		Date date8 = Date.parse("dd/MM/yyyy", "01/10/2014")
		
		expect:
		
		false == service.estRevisable(contrat, date1)
		false == service.estRevisable(contrat, date2)
		true == service.estRevisable(contrat, date3)
		true == service.estRevisable(contrat, date4)
		true == service.estRevisable(contrat, date5)
		true == service.estRevisable(contrat, date6)
		true == service.estRevisable(contrat, date7)
		true == service.estRevisable(contrat, date8)
	}
	
	void "revisable contrat trimestriel avec révision (debut et fin de contrat)"() {

		def contrat = new Contrat(
			nom:			"Contrat",
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/08/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/08/2014"),
			dureeRevision:	1,
			chezNotaire:	false,
			estTermine:		false,
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			locataire:		Locataire.first(),
			profil:			Profil.findByNom("trimestriel")
		).addToLocaux(Local.first()).save()
		
		def revision = new Revision(
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/08/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/08/2014"),
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			indice:			100,
			contrat:		contrat
		).save()
		
		contrat.revisionActive = revision
		contrat.save()
		
		Date date1 = Date.parse("dd/MM/yyyy", "01/08/2013")
		Date date2 = Date.parse("dd/MM/yyyy", "20/08/2013")
		Date date3 = Date.parse("dd/MM/yyyy", "01/08/2014")
		Date date4 = Date.parse("dd/MM/yyyy", "31/08/2014")
		
		expect:
		
		false == service.estRevisable(contrat, date1)
		false == service.estRevisable(contrat, date2)
		false == service.estRevisable(contrat, date3)
		false == service.estRevisable(contrat, date4)
	}

	void "facturable contrat mensuel avec révision (fonction de la date)"() {

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
			montantLoyer:	1111.00,
			montantCharges:	100.00,
			indice:			101,
			contrat:		contrat
		).save()
		
		contrat.revisionActive = revision
		contrat.save()
		
		Date date1 = Date.parse("dd/MM/yyyy", "10/01/2014")
		Date date2 = Date.parse("dd/MM/yyyy", "01/06/2014")
		Date date3 = Date.parse("dd/MM/yyyy", "20/06/2014")
		Date date4 = Date.parse("dd/MM/yyyy", "30/06/2014")
		Date date5 = Date.parse("dd/MM/yyyy", "04/07/2014")
		
		expect:
		
		true == service.estFacturable(contrat, date1)
		true == service.estFacturable(contrat, date2)
		true == service.estFacturable(contrat, date3)
		true == service.estFacturable(contrat, date4)
		true == service.estFacturable(contrat, date5)
	}
	
	void "facturable contrat trimestriel avec révision (fonction de la date)"() {

		def contrat = new Contrat(
			nom:			"Contrat",
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/08/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/08/2022"),
			dureeRevision:	1,
			chezNotaire:	false,
			estTermine:		false,
			montantLoyer:	1100.00,
			montantCharges:	100.00,
			locataire:		Locataire.first(),
			profil:			Profil.findByNom("trimestriel")
		).addToLocaux(Local.first()).save()
		
		def revision = new Revision(
			dateDebut: 		Date.parse("dd/MM/yyyy", "15/08/2013"),
			dateFin: 		Date.parse("dd/MM/yyyy", "15/08/2014"),
			montantLoyer:	1111.00,
			montantCharges:	100.00,
			indice:			101,
			contrat:		contrat
		).save()
		
		contrat.revisionActive = revision
		contrat.save()
		
		Date date1 = Date.parse("dd/MM/yyyy", "10/01/2014")
		Date date2 = Date.parse("dd/MM/yyyy", "30/06/2014")
		Date date3 = Date.parse("dd/MM/yyyy", "01/07/2014")
		Date date4 = Date.parse("dd/MM/yyyy", "31/07/2014")
		Date date5 = Date.parse("dd/MM/yyyy", "01/08/2014")
		Date date6 = Date.parse("dd/MM/yyyy", "31/08/2014")
		Date date7 = Date.parse("dd/MM/yyyy", "01/09/2014")
		Date date8 = Date.parse("dd/MM/yyyy", "01/10/2014")
		Date date9 = Date.parse("dd/MM/yyyy", "29/02/2016")
		
		Date date10 = Date.parse("dd/MM/yyyy", "01/08/2013")
		
		expect:
		
		true == service.estFacturable(contrat, date1)
		false == service.estFacturable(contrat, date2)
		true == service.estFacturable(contrat, date3)
		true == service.estFacturable(contrat, date4)
		false == service.estFacturable(contrat, date5)
		false == service.estFacturable(contrat, date6)
		false == service.estFacturable(contrat, date7)
		true == service.estFacturable(contrat, date8)
		false == service.estFacturable(contrat, date9)
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
