import securite.*
import albybail.*

import groovy.time.TimeCategory

class BootStrap {

	def indiceService
	
    def init = { servletContext ->
			
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
		def testUser = new User(username: 'admin', enabled: true, password: 'admin').save(flush: true)
		UserRole.create testUser, adminRole, true
		
		if (!Batiment.count()) {
			
			def albyloc = new Batiment(nom: "Albyloc").save(failOnError: true)
			def touviere = new Batiment(nom: "Touvière", adresse: "Saint-Félix").save(failOnError: true)
			
			def local1 = new Local(nom: "Albyloc partie 1", surface: "", batiment: albyloc).save(failOnError: true)
			def local2 = new Local(nom: "Albyloc partie 2", surface: "", batiment: albyloc).save(failOnError: true)
			def local3 = new Local(nom: "Niveau 2, Atelier 1", surface: "130", batiment: touviere).save(failOnError: true)
			def local4 = new Local(nom: "Niveau 2, Bat. ouest", surface: "200", batiment: touviere).save(failOnError: true)
			def local5 = new Local(nom: "Niveau 3, Atelier 1", surface: "211", batiment: touviere).save(failOnError: true)
			def local6 = new Local(nom: "Niveau 3, Atelier 2", surface: "23", batiment: touviere).save(failOnError: true)
			def local7 = new Local(nom: "Niveau 3, Atelier 3", surface: "45", batiment: touviere).save(failOnError: true)
			def local8 = new Local(nom: "Niveau 3, Atelier 4", surface: "88", batiment: touviere).save(failOnError: true)
			
			def locataire1 = new Locataire(nom: "Banque Populaire", adresse: "2 avenue de Grésivaudan\n38700 CORENC").save(failOnError: true)
			def locataire2 = new Locataire(nom: "Bel Air", adresse: "Zone espace leaders\n156 rue de Moutti Sud\n74540 Alby-sur-Chéran", remarques: "Demande à partir le 31/07/2014").save(failOnError: true)
			def locataire3 = new Locataire(nom: "Serrur'elec", adresse: "12 rue Saint Polycarpe, 69001 Lyon").save(failOnError: true)
			def locataire4 = new Locataire(nom: "Pettini", adresse: "10 avenue du Maroc, Marcoville").save(failOnError: true)
			def locataire5 = new Locataire(nom: "Peelman", adresse: "7 rue des Lys, Narbonne").save(failOnError: true)
			def locataire6 = new Locataire(nom: "Dubois", adresse: "Sait-Aubin").save(failOnError: true)
			def locataire7 = new Locataire(nom: "La poste", adresse: "25 rue Paul Verlaine, Villeurbanne").save(failOnError: true)
			
			/*def indice1 = new Indice(nom: "1er Trimestre 2013", dateDebut: new Date("01/01/2013"), dateFin: new Date("31/03/2013"), valeur: 1234.56).save(failOnError: true)
			def indice2 = new Indice(nom: "2ème Trimestre 2013", dateDebut: new Date("01/04/2013"), dateFin: new Date("30/06/2013"), valeur: 1233.03).save(failOnError: true)
			def indice3 = new Indice(nom: "3ème Trimestre 2013", dateDebut: new Date("01/07/2013"), dateFin: new Date("30/09/2013"), valeur: 1265.16).save(failOnError: true)
			def indice4 = new Indice(nom: "4ème Trimestre 2013", dateDebut: new Date("01/10/2013"), dateFin: new Date("31/12/2013"), valeur: 1268.34).save(failOnError: true)
			def indice5 = new Indice(nom: "1er Trimestre 2014", dateDebut: new Date("01/01/2014"), dateFin: new Date("31/03/2014"), valeur: 1234.56).save(failOnError: true)
			def indice6 = new Indice(nom: "2ème Trimestre 2014", dateDebut: new Date("01/04/2014"), dateFin: new Date("30/06/2014"), valeur: 1233.03).save(failOnError: true)
			def indice7 = new Indice(nom: "3ème Trimestre 2014", dateDebut: new Date("01/07/2014"), dateFin: new Date("30/09/2014"), valeur: 1265.16).save(failOnError: true)
			def indice8 = new Indice(nom: "4ème Trimestre 2014", dateDebut: new Date("01/10/2014"), dateFin: new Date("31/12/2014"), valeur: 1268.34).save(failOnError: true)*/
			
			def ajd = new Date()
			def ajd_m3a15j
			def ajd_m1a15j
			def ajd_m15j
			def ajd_m3ap1j
			use(TimeCategory) {
				ajd_m3a15j = ajd - 3.years - 15.days
				ajd_m1a15j = ajd - 1.years - 15.days
				ajd_m15j = ajd - 15.days
				ajd_m3ap1j = ajd + 1.days
			}
			
			def contrat1 = new Contrat(
				nom: 			"Contrat de test révision",
				dateDebut: 		ajd_m1a15j,
				dateFin: 		new Date("15/08/2022"),
				dureeRevision:	1,
				chezNotaire:	false,
				estTermine:		false,
				montantLoyer:	1100.00,
				montantCharges:	100.00,
				remarques:		"contrat à réviser",
				locataire:		locataire1
			)
			.addToLocaux(local1)
			.save(failOnError: true)
			
			def revision1 = new Revision(
				dateDebut: 		ajd_m1a15j,
				dateFin: 		ajd_m15j,
				montantLoyer:	1100.00,
				montantCharges:	100.00,
				indice:			100,
				contrat:		contrat1,
				aReguler:		false
			).save(failOnError: true)
			
			contrat1.revisionActive = revision1
			contrat1.save(failOnError: true)
			
			def contrat2 = new Contrat(	
				nom: 			"Contrat de location",
				dateDebut: 		new Date("15/11/2014"),
				dateFin: 		new Date("14/11/2021"),
				dureeRevision:	1,
				chezNotaire:	true,
				estTermine:		false,
				montantLoyer:	1200.00,
				montantCharges:	100.00,
				remarques:		"",
				locataire:		locataire2
			)
			.addToLocaux(local2)
			.save(failOnError: true)
			
			def contrat3 = new Contrat(	
				nom: 			"Contrat de location",
				dateDebut: 		new Date("15/11/2014"),
				dateFin: 		new Date("14/11/2021"),
				dureeRevision:	3,
				chezNotaire:	false,
				estTermine:		true,
				montantLoyer:	100.00,
				montantCharges:	10.00,
				remarques:		"Contrat cloturé le 12/12/2012",
				locataire:		locataire3
			)
			.addToLocaux(local3)
			.save(failOnError: true)
			
			def contrat4 = new Contrat(	
				nom: 			"Contrat",
				dateDebut: 		new Date("13/09/2014"),
				dateFin: 		new Date("14/12/2025"),
				dureeRevision:	3,
				chezNotaire:	false,
				estTermine:		false,
				montantLoyer:	1300.00,
				montantCharges:	200.00,
				locataire:		locataire4
			)
			.addToLocaux(local4)
			.save(failOnError: true)
			
			def contrat5 = new Contrat(	
				nom: 			"Contrat",
				dateDebut: 		new Date("21/06/2014"),
				dateFin: 		new Date("14/12/2025"),
				dureeRevision:	1,
				chezNotaire:	false,
				estTermine:		false,
				montantLoyer:	1250.00,
				montantCharges:	100.00,
				locataire:		locataire5
			)
			.addToLocaux(local5)
			.addToLocaux(local6)
			.save(failOnError: true)
			
			def contrat6 = new Contrat(	
				nom: 			"Contrat avec révision dans le mois",
				dateDebut: 		ajd_m3ap1j,
				dateFin: 		new Date("14/12/2025"),
				dureeRevision:	3,
				chezNotaire:	false,
				estTermine:		false,
				montantLoyer:	200.00,
				montantCharges: 50.00,
				locataire:		locataire6
			)
			.addToLocaux(local7)
			.save(failOnError: true)
			
			def contrat7 = new Contrat(	
				nom: 			"Contrat",
				dateDebut: 		new Date("13/09/2001"),
				dateFin: 		new Date("14/12/2025"),
				dureeRevision:	1,
				chezNotaire:	false,
				estTermine:		false,
				montantLoyer:	1000.00,
				montantCharges:	100.00,
				locataire:		locataire7
			)
			.addToLocaux(local8)
			.save(failOnError: true)
			
		}
		
    }
	
    def destroy = {
    }
}
