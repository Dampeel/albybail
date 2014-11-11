package albybail

import groovy.time.TimeCategory
import grails.transaction.Transactional

@Transactional
class ContratService {
	
	def indiceService

	/* Cas à gérer :
	 * - nouveau contrat, pas de révision, première révision à créer
	 * - contrat existant, dernière révision manquante, une révision à créer
	 * - contrat non actualisé depuis longtemps, plusieurs révisions à créer
	 * - contrat arrivant à terme, une révision partielle à créer
	 */
	
	/* Contrat à reviser :
	 * date courante >= dateFin révision courante ou nouveau contrat
	 */
	List<Contrat> listeAReviser() {
		def liste = new ArrayList<Contrat>()
		def ajd = new Date() // aujourd'hui
		
		Contrat.findAllByEstTermine(false).each { contrat ->
			if (contrat.revisionActive == null) {
				// contrat sans révision (nouveau contrat)
				liste.add(contrat)
			} else if (ajd.after(contrat.revisionActive.dateFin)) {
				// contrat dont la date de révision est passée
				liste.add(contrat)
			} else if (ajd.month == contrat.revisionActive.dateFin.month
				&& ajd.year == contrat.revisionActive.dateFin.year) {
				// contrat avec une révision dans le mois courant
				liste.add(contrat)
			}
		}
		return liste
	}
	
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
		def dateFin = contrat.dateDebut
		def montantLoyer = contrat.montantLoyer
		def montantCharges = contrat.montantCharges
		
		// calcul de la date de prochaine révision
		use(TimeCategory) {
			dateFin += contrat.dureeRevision.year
		}
		
		// récupération de l'indice applicable
		def indice = 0.0
		
		// création de la révision
		def nouvelleRevision = new Revision(
			dateDebut:		dateDebut,
			dateFin:		dateFin,
			montantLoyer:	montantLoyer,
			montantCharges:	montantCharges,
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
		}
		
		// création de la révision
		def nouvelleRevision = new Revision(
			dateDebut:		dateDebut,
			dateFin:		dateFin,
			montantLoyer:	ancienneRevision.montantLoyer,
			montantCharges:	ancienneRevision.montantCharges,
			indice:			ancienneRevision.indice,
			contrat:		contrat
		)
		
		return nouvelleRevision
	}
	
	def sauverRevision(Revision revision) {
		def contrat = revision.contrat
		if (contrat.revisionActive == null) {
			contrat.revisionActive = revision
		} else {
			def ancienneRevision = contrat.revisionActive
			
			// calcul du nouveau loyer
			println ancienneRevision.montantLoyer
			println revision.indice
			println ancienneRevision.indice
			revision.montantLoyer = ancienneRevision.montantLoyer * (revision.indice / ancienneRevision.indice)
			println revision.montantLoyer
			
			// sauvegarde
			contrat.revisionActive = revision
			contrat.addToRevisions(ancienneRevision)
		}
		contrat.save(flush:true)
	}
}
