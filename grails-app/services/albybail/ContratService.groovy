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
	
	Revision creerRevision(Contrat contrat) {
		def revision
		if (contrat.revisionActive == null) {
			revision = creerRevisionNouveauContrat(contrat)
		} else {
			revision = creationRevisionContratExistant(contrat)
		}
		return revision
	}

	Revision creerRevisionNouveauContrat(Contrat contrat) {
		
		// récupération des données
		def dateDebut = contrat.dateDebut
		def dateFin = contrat.dateDebut
		def montantLoyer = contrat.montantLoyer
		def montantCharges = contrat.revisionActive.montantCharges
		
		// calcul de la date de prochaine révision
		use(TimeCategory) {
			dateFin += contrat.dureeRevision.year
		}
		
		// récupération du dernier indice
		def indice = Indice.first(sort: date)
		
		// création de la révision
		def nouvelleRevision = new Revision(
			dateDebut:		dateDebut,
			dateFin:		dateFin,
			montantLoyer:	montantLoyer,
			montantCharges:	montantCharges,
			indice:			indice,
			contrat:		contrat
		)
		
		// marquer à réguler en fonction de l'indice
		calculAReguler(nouvelleRevision)
		
		return nouvelleRevision
    }
	
	def creationRevisionContratExistant(Contrat contrat) {
		
		// récupération des données
		def ancienneRevision = contrat.revisionActive
		def dateDebut = ancienneRevision.dateDebut
		def dateFin = ancienneRevision.dateDebut
		def montantCharges = ancienneRevision.montantCharges
		def ancienIndice = ancienneRevision.indice
		
		// calcul des dates de la nouvelle révision
		use(TimeCategory) {
			dateDebut += 1.day
			dateFin = dateDebut + contrat.dureeRevision.year
		}
		
		// récupération du dernier indice
		def nouvelIndice = Indice.first(sort: date)
		
		// calcul du loyer
		def montantLoyer = ancienneRevision.montantLoyer * (ancienIndice.valeur / nouvelIndice.valeur)
		
		// création de la révision
		def nouvelleRevision = new Revision(
			dateDebut:		dateDebut,
			dateFin:		dateFin,
			montantLoyer:	montantLoyer,
			montantCharges:	montantCharges,
			indice:			nouvelIndice,
			contrat:		contrat
		)
		
		// marquer à réguler en fonction de l'indice
		calculAReguler(nouvelleRevision)
		
		return nouvelleRevision
	}
	
	def calculAReguler(Revision revision) {
		if (revision.indice.dateFin < revision.dateDebut) {
			revision.aReguler = true
		} else {
			revision.aReguler = false
		}
	}
}
