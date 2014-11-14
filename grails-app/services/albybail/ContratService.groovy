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
			if (estRevisable(contrat)) {
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
		
		// si il n'existe pas de révision
		if (!contrat.revisionActive) {
			return false
		}
		
		// recherche de la plage de facturation correspondant à la date de revision
		def plageFact = profilService.plageCourante(contrat, date)
		
		// si la date de facturation tombe sur un mois facturable
		if (date[Calendar.MONTH] == plageFact.moisDebut - 1) {
			return true
		}
		
		return false
	}
	
	// si le contrat doit être révisé
	boolean estRevisable(Contrat contrat, Date date) {
		
		// si le contrat est terminé
		if (contrat.estTermine) {
			return false
		}
		
		// recherche de la révision correspondant à la date de revision
		def revision = revisionService.revisionCourante(contrat, date)
		
		// si il n'existe pas de révision pour la date donnée
		if (revision == null) {
			return true
		}
		
		// recherche de la plage de facturation correspondant à la date de revision
		def plageFact = profilService.plageCourante(contrat, date)
		
		// si la date de fin de facturation > date de fin de la révision active
		if (plageFact.dateFin(date).after(revision.dateFin)) {
			return true
		}
		
		return false
	}
}
