package albybail

import groovy.time.TimeCategory
import grails.transaction.Transactional

@Transactional
class ContratService {

	/* Cas à gérer :
	 * - nouveau contrat, pas de révision, première révision à créer
	 * - contrat existant, dernière révision manquante, une révision à créer
	 * - contrat non actualisé depuis longtemps, plusieurs révisions à créer
	 * - contrat arrivant à terme, une révision partielle à créer
	 */
	
	// TODO: créer toutes les révisions d'un coup ???
	
    def nouveauContrat(Contrat contrat) {
		
		// récupération des données
		def dateDebutRevision = contrat.dateDebut
		def dateFinRevision = contrat.dateDebut
		def montantLoyerRevision = contrat.montantLoyer
		
		// calcul de la date de prochaine révision
		use(TimeCategory) {
			dateFinRevision += contrat.dureeRevision.year
		}
		
		// création de la révision
		def revision = new Revision(
			dateDebut:		dateDebutRevision,
			dateFin:		dateFinRevision,
			montantLoyer:	montantLoyerRevision,
			contrat:		contrat
		)
		
		contrat.revisionActive = revision
		contrat.save(flush: true)
    }
	
	def contratExistant(Contrat contrat) {
		
	}
}
