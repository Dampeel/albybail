import securite.*
import albybail.*

class BootStrap {

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
			def local5 = new Local(nom: "Niveau 2, Atelier 2", surface: "211", batiment: touviere).save(failOnError: true)
			
			def locataire1 = new Locataire(nom: "Banque Populaire", adresse: "2 avenue de Grésivaudan\n38700 CORENC").save(failOnError: true)
			def locataire2 = new Locataire(nom: "Bel Air", adresse: "Zone espace leaders\n156 rue de Moutti Sud\n74540 Alby-sur-Chéran", remarques: "Demande à partir le 31/07/2014").save(failOnError: true)
			def locataire3 = new Locataire(nom: "Serrur'elec", adresse: "12 rue Saint Polycarpe, 69001 Lyon").save(failOnError: true)
			def locataire4 = new Locataire(nom: "Pettini", adresse: "10 avenue du Maroc, Marcoville").save(failOnError: true)
			
			/*String		nom
			Date		dateDebut		= new Date()
			Date		dateFin
			Integer		dureeRevision
			Boolean		chezNotaire		= true
			Boolean		estTermine
			String		remarques
			Locataire	locataire*/
			
			def contrat1 = new Contrat(	
				nom: 			"Contrat de location",
				dateDebut: 		new Date("15/11/2012"),
				dateFin: 		new Date("14/11/2021"),
				dureeRevision:	3,
				chezNotaire:	false,
				estTermine:		false,
				remarques:		"",
				locataire:		locataire1
			)
			.addToLocaux(local1)
			.save(failOnError: true)
			
			def contrat2 = new Contrat(	
				nom: 			"Contrat de location",
				dateDebut: 		new Date("15/11/2012"),
				dateFin: 		new Date("14/11/2021"),
				dureeRevision:	1,
				chezNotaire:	true,
				estTermine:		false,
				remarques:		"",
				locataire:		locataire2
			)
			.addToLocaux(local3)
			.save(failOnError: true)
			
			def contrat3 = new Contrat(	
				nom: 			"Contrat de location",
				dateDebut: 		new Date("15/11/2012"),
				dateFin: 		new Date("14/11/2021"),
				dureeRevision:	3,
				chezNotaire:	false,
				estTermine:		true,
				remarques:		"Contrat cloturé le 12/12/2012",
				locataire:		locataire2
			)
			.addToLocaux(local3)
			.save(failOnError: true)
			
		}
		
    }
	
    def destroy = {
    }
}
