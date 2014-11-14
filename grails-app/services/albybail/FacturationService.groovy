package albybail

import grails.transaction.Transactional
import util.*

@Transactional
class FacturationService {

	ProfilService profilService
	RevisionService revisionService

	List<Facturable> creerFacturables(Contrat contrat, Date dateFact) {
		
		// TODO: améliorer gestion des contrats sans révision
		if (!contrat.revisionActive) {
			println "pas de révision active"
			return
		}
		
		def loyer = calculLoyer(contrat, dateFact)
		def charges = calculCharges(contrat, dateFact)
		def liste = [loyer, charges]
		
		contrat.addToFacturables(loyer)
		contrat.addToFacturables(charges)
		contrat.save(flush: true)
		
		return liste
	}
	
	Facturable calculLoyer(Contrat contrat, Date date) {
		
		def loyer = new Facturable(nom: "loyer")
		
		// recherche de la plage de facturation correspondant à la date de facturation
		def plage = profilService.plageCourante(contrat, date)

		// recherche des révisions correspondant à la plage de facturation
		def revisionDebut = revisionService.revisionCourante(contrat, plage.dateDebut(date))
		def revisionFin = revisionService.revisionCourante(contrat, plage.dateFin(date))
		
		// cas 3 : plage à cheval sur deux révisions mais seconde révision non définie
		if (!revisionFin && !revisionDebut) {
			println "cas 3"
			loyer.valeur = revisionDebut.montantLoyer
			loyer.description = "Montant mensuel (à régulariser lorsque l'indice INSEE sera connu) HT"
			loyer.aReguler = true
		}

		// cas 1 : plage inclue dans une seule révision, cas normal
		else if (revisionDebut.id == revisionFin.id) {
			println "cas 1"
			loyer.valeur = revisionDebut.montantLoyer
			loyer.description = "Montant mensuel HT"
		}

		// cas 2 : plage à cheval sur deux révisions
		else if (revisionDebut.id != revisionFin.id) {
			println "cas 2"
			
			def nbJourTotal = plage.dateFin(date) - plage.dateDebut(date) + 1
			def nbJour1 = revisionDebut.dateFin - plage.dateDebut(date) + 1
			def nbJour2 = plage.dateFin(date) - revisionFin.dateDebut + 1
			
			def loyer1 = revisionDebut.montantLoyer * nbJour1 / nbJourTotal
			def loyer2 = revisionFin.montantLoyer * nbJour2 / nbJourTotal
			loyer.valeur = loyer1 + loyer2
			loyer.description = "Montant mensuel (révisé au " + Date.format(revisionFin.dateDebut, "dd/MM/yyyy") + " HT"
		}
		
		// cas anormal
		else {
			println "cas non prévu"
		}
		
		loyer.save()
		
		return loyer
	}
	
	Facturable calculCharges(Contrat contrat, Date date) {
		def charges = new Facturable(nom: "charges")
		
		// recherche de la plage de facturation correspondant à la date de facturation
		def plage = profilService.plageCourante(contrat, date)

		// recherche des révisions correspondant à la plage de facturation
		def revisionDebut = revisionService.revisionCourante(contrat, plage.dateDebut(date))
		
		charges.valeur = revisionDebut.montantCharges
		charges.description = "Provisions sur charges HT"
		
		charges.save()
		
		return charges
	}
}
