package albybail

import grails.transaction.Transactional

@Transactional
class IndiceService {

    Indice indiceApplicable(Date date) {
			
		// TODO : Completer
		return Indice.last(sort: "dateDebut")
    }
}
