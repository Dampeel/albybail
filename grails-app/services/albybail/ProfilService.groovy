package albybail

import grails.transaction.Transactional

@Transactional
class ProfilService {
	
	// retourne la plage courante
    Plage plageCourante(Contrat contrat, Date date) {
		def plageCourante
		
		contrat.profil.plages.each { plage ->
			if ((plage.dateDebut(date).before(date) && plage.dateFin(date).after(date))
				|| plage.dateDebut(date).equals(date)
				|| plage.dateFin(date).equals(date)) {
				plageCourante = plage
			}
		}
		
		return plageCourante
    }
	
	// retourne la date de début de la facturation
    Date dateDebutFact(Contrat contrat, Date date) {
		def plageCourante
		
		contrat.profil.plages.each { plage ->
			if ((plage.dateDebut(date).before(date) && plage.dateFin(date).after(date))
				|| plage.dateDebut(date).equals(date)
				|| plage.dateFin(date).equals(date)) {
				plageCourante = plage
			}
		}
		
		def dateDebutFact = plageCourante.dateDebut(date)
		
		if (dateDebutFact.before(contrat.dateDebut)) {
			dateDebutFact = contrat.dateDebut
		}
		
		return dateDebutFact
    }
	
	// retourne la date de fin de la facturation
    Date dateFinFact(Contrat contrat, Date date) {
		def plageCourante
		
		contrat.profil.plages.each { plage ->
			if ((plage.dateDebut(date).before(date) && plage.dateFin(date).after(date))
				|| plage.dateDebut(date).equals(date)
				|| plage.dateFin(date).equals(date)) {
				plageCourante = plage
			}
		}
		
		def dateFinFact = plageCourante.dateFin(date)
		
		if (dateFinFact.after(contrat.dateFin)) {
			dateFinFact = contrat.dateFin
		}
		
		return dateFinFact
    }
	
	// retourne le nombre de jours de facturation de référence
    Integer nbJoursFact(Contrat contrat, Date date) {
		def plageCourante
		
		contrat.profil.plages.each { plage ->
			if ((plage.dateDebut(date).before(date) && plage.dateFin(date).after(date))
				|| plage.dateDebut(date).equals(date)
				|| plage.dateFin(date).equals(date)) {
				plageCourante = plage
			}
		}
		
		return plageCourante.dateFin(date) - plageCourante.dateDebut(date) + 1
    }
}
