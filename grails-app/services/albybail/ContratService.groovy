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
	 * mois de révision >= dateFin révision courante ou nouveau contrat
	 */
	List<Contrat> listeAReviser() {
		def liste = new ArrayList<Contrat>()
		def ajd = new Date() // aujourd'hui

		Contrat.findAllByEstTermine(false).each { contrat ->
			if (contrat.revisionActive == null) {
				liste.add(contrat)
			} else if (ajd.after(contrat.revisionActive.dateFin)) {
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
	
	def sauverRevision(Contrat contrat, Revision revision) {
		if (contrat.revisionActive == null) {
			contrat.revisionActive = revision
		} else {
			def ancienneRevision = contrat.revisionActive
			contrat.revisionActive = revision
			contrat.addToRevisions(ancienneRevision)
		}
		contrat.save(flush:true)
	}

	Revision creerRevisionNouveauContrat(Contrat contrat) {
		
		// récupération des données
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
	
	def creerRevisionContratExistant(Contrat contrat) {
		
		// récupération des données
		def ancienneRevision = contrat.revisionActive
		def dateDebut = ancienneRevision.dateFin
		def dateFin = ancienneRevision.dateFin
		// def montantCharges = ancienneRevision.montantCharges
		// def indice = ancienneRevision.indice
		
		// calcul des dates de la nouvelle révision
		use(TimeCategory) {
			dateDebut += 1.day
			dateFin = dateDebut + contrat.dureeRevision.year
		}
		
		// récupération de l'indice applicable
		// def nouvelIndice = indiceService.indiceApplicable(dateDebut)
		
		// calcul du loyer
		// TODO : arrondir valeur à deux décimales
		// def montantLoyer = ancienneRevision.montantLoyer * (ancienIndice.valeur / nouvelIndice.valeur)
		
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
	
	def calculAReguler(Revision revision) {
		if (revision.indice.dateFin < revision.dateDebut) {
			revision.aReguler = true
		} else {
			revision.aReguler = false
		}
	}
}
