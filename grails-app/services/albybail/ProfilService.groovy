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
}
