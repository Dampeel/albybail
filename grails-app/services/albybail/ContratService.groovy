package albybail

import grails.transaction.Transactional

@Transactional
class ContratService {
	
	def profilService
	def revisionService
	
	// contrats à réviser
	List<Contrat> listeAReviser(Date date) {
		def liste = new ArrayList<Contrat>()
		
		Contrat.list().each { contrat ->
			if (estRevisable(contrat, date)) {
				liste.add(contrat)
			}
		}
		return liste
	}
	
	// contrats facturables
	List<Contrat> listeAFacturer(Date date) {
		def liste = new ArrayList<Contrat>()
		
		Contrat.list().each { contrat ->
			if (estFacturable(contrat, date)) {
				liste.add(contrat)
			}
		}
		return liste
	}
	
	// si une des plages de facturation débute le mois courant
	boolean estFacturable(Contrat contrat, Date date) {
		
		// si le contrat est terminé
		if (contrat.estTermine) {
			return false
		}
		
		// contrat sans révision (nouveau contrat)
		if (!contrat.revisionActive) {
			return false
		}
		
		// recherche de la plage de facturation correspondant à la date de revision
		def dateDebutFact = profilService.dateDebutFact(contrat, date)
		
		// si on est dans le premier mois de la plage de facturation
		if (date.month == dateDebutFact.month) {
			return true
		}
	}
	
	// si le contrat doit êre révisé
	boolean estRevisable(Contrat contrat, Date date) {
		
		// si le contrat est terminé
		if (contrat.estTermine) {
			return false
		}
		
		// contrat sans révision (nouveau contrat)
		if (!contrat.revisionActive) {
			return true
		}
		
		// recherche de la date de début de facturation correspondant à la date de facturation
		def dateDebutFact = profilService.dateDebutFact(contrat, date)
		
		// recherche de la date de fin de facturation correspondant à la date de facturation
		def dateFinFact = profilService.dateFinFact(contrat, date)
		
		// recherche de la révision correspondant à la date de fin de facturation
		def revisionDebut = revisionService.revisionCourante(contrat, dateDebutFact)
		
		// recherche de la révision correspondant à la date de fin de facturation
		def revisionFin = revisionService.revisionCourante(contrat, dateFinFact)
		
		// si il n'existe pas de révision pour la date donnée
		if (!revisionDebut || !revisionFin) {
			return true
		}
	}
}
