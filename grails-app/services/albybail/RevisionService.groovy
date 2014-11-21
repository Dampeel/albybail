package albybail

import grails.transaction.Transactional
import groovy.time.TimeCategory

@Transactional
class RevisionService {

	Revision creerRevision(Contrat contrat) {
		def revision
		if (contrat.revisionActive == null) {
			revision = creerRevisionNouveauContrat(contrat)
		} else {
			revision = creerRevisionContratExistant(contrat)
		}
		return revision
	}

	Revision creerRevisionNouveauContrat(Contrat contrat) {
		
		// récupération des données du contrat
		def dateDebut = contrat.dateDebut
		def dateFin
		def montantLoyer = contrat.montantLoyer
		
		// calcul de la date de fin de la révision
		use(TimeCategory) {
			dateFin = contrat.dateDebut + contrat.dureeRevision.year
			println "durée révision = " + contrat.dureeRevision.year
		}
		if (dateFin.after(contrat.dateFin)) {
			dateFin = contrat.dateFin
		}
		
		// récupération de l'indice applicable
		def indice = 0.0
		
		// création de la révision
		def nouvelleRevision = new Revision(
			dateDebut:		dateDebut,
			dateFin:		dateFin,
			montantLoyer:	montantLoyer,
			indice:			indice,
			contrat:		contrat
		)
		
		return nouvelleRevision
    }
	
	Revision creerRevisionContratExistant(Contrat contrat) {
		
		// récupération des données de la dernière révision
		def ancienneRevision = contrat.revisionActive
		def dateDebut
		def dateFin
		
		// calcul des dates de la nouvelle révision
		use(TimeCategory) {
			dateDebut = ancienneRevision.dateFin + 1.day
			dateFin = dateDebut + contrat.dureeRevision.year
			println "durée révision = " + contrat.dureeRevision.year
		}
		if (dateFin.after(contrat.dateFin)) {
			dateFin = contrat.dateFin
		}
		
		// création de la révision
		def nouvelleRevision = new Revision(
			dateDebut:		dateDebut,
			dateFin:		dateFin,
			montantLoyer:	ancienneRevision.montantLoyer,
			indice:			ancienneRevision.indice,
			contrat:		contrat
		)
		
		return nouvelleRevision
	}
	
	def sauverRevision(Revision revision) {
		def contrat = revision.contrat
		if (contrat.revisionActive == null) {
			contrat.revisionActive = revision
			contrat.addToRevisions(revision)
		} else {
			def ancienneRevision = contrat.revisionActive
			
			// calcul du nouveau loyer
			revision.montantLoyer = ancienneRevision.montantLoyer * (revision.indice / ancienneRevision.indice)
			
			// sauvegarde
			contrat.revisionActive = revision
			contrat.addToRevisions(revision)
		}
		contrat.save(flush: true)
	}
	
	// retourne la révision en cours à la date donnée
	Revision revisionCourante(Contrat contrat, Date date) {
		
		def revisionCourante
		
		// recherche de la révision courante
		contrat.revisions.each { revision ->
			if ((revision.dateDebut.before(date) && revision.dateFin.after(date))
				|| revision.dateDebut.equals(date)
				|| revision.dateFin.equals(date)) {
				revisionCourante = revision
			}
		}
		
		return revisionCourante
	}
}
