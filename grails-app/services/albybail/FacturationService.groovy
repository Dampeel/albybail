package albybail

import grails.transaction.Transactional

@Transactional
class FacturationService {

	ProfilService profilService
	RevisionService revisionService

	def creerFacturables(Contrat contrat, Date dateFact) {
		
		// TODO: améliorer gestion des contrats sans révision
		if (!contrat.revisionActive) {
			println "pas de révision active"
			return
		}
		
		def loyer = calculLoyer(contrat, dateFact)
		def charges = calculCharges(contrat, dateFact)
		
		contrat.addToFacturables(loyer)
		contrat.addToFacturables(charges)
		
		if (contrat.hasErrors()) { prinln "errors" }
		
		contrat.save(flush: true)
	}
	
	Facturable calculLoyer(Contrat contrat, Date date) {
		
		def loyer = new Facturable(categorie: "loyer")
		
		// recherche de la plage de facturation correspondant à la date de facturation
		def dateDebutFact = profilService.dateDebutFact(contrat, date)
		def dateFinFact = profilService.dateFinFact(contrat, date)
		def nbJoursFact = profilService.nbJoursFact(contrat, date)

		// recherche des révisions correspondant à la plage de facturation
		def revisionDebut = revisionService.revisionCourante(contrat, dateDebutFact)
		def revisionFin = revisionService.revisionCourante(contrat, dateFinFact)
		
		// cas 4 : aucune revision courante, seulement la révision active
		if (!revisionDebut && !revisionFin) {
			println "cas 4"
			loyer.valeur = contrat.revisionActive.montantLoyer * (dateFinFact - dateDebutFact + 1) / nbJoursFact
			loyer.description = "Montant mensuel (à régulariser lorsque l'indice INSEE sera connu) HT"
			loyer.aReguler = true
		}
		
		// cas 3 : plage à cheval sur deux révisions mais seconde révision non définie
		else if (!revisionFin) {
			println "cas 3"
			loyer.valeur = revisionDebut.montantLoyer * (dateFinFact - dateDebutFact + 1) / nbJoursFact
			loyer.description = "Montant mensuel (à régulariser lorsque l'indice INSEE sera connu) HT"
			loyer.aReguler = true
		}

		// cas 1 : plage inclue dans une seule révision, cas normal
		else if (revisionDebut.equals(revisionFin)) {
			println "cas 1"
			loyer.valeur = revisionDebut.montantLoyer * (dateFinFact - dateDebutFact + 1) / nbJoursFact
			loyer.description = "Montant mensuel HT"
		}

		// cas 2 : plage à cheval sur deux révisions
		else if (!revisionDebut.equals(revisionFin)) {
			println "cas 2"
			
			def nbJour1 = revisionDebut.dateFin - dateDebutFact + 1
			def nbJour2 = dateFinFact - revisionFin.dateDebut + 1
			
			def loyer1 = revisionDebut.montantLoyer * nbJour1 / nbJoursFact
			def loyer2 = revisionFin.montantLoyer * nbJour2 / nbJoursFact
			loyer.valeur = loyer1 + loyer2
			loyer.description = "Montant mensuel (révisé au " + revisionFin.dateDebut.format("dd/MM/yyyy") + ") HT"
		}
		
		// cas anormal
		else {
			println "cas non prévu"
		}
		
		
		if (loyer.hasErrors()) { prinln "errors loyer" }
		
		loyer.save()
		
		return loyer
	}
	
	Facturable calculCharges(Contrat contrat, Date date) {
		def charges = new Facturable(categorie: "charges")
		
		// recherche de la plage de facturation correspondant à la date de facturation
		def dateDebutFact = profilService.dateDebutFact(contrat, date)
		def dateFinFact = profilService.dateFinFact(contrat, date)
		def nbJoursFact = profilService.nbJoursFact(contrat, date)
		
		charges.valeur = contrat.montantCharges * (dateFinFact - dateDebutFact + 1) / nbJoursFact
		charges.description = "Provisions sur charges HT"
		
		charges.save()
		
		return charges
	}
}
