package albybail

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AccueilController {

	//TODO : différence entre index() et index=
    def index() {
		return [
			contratChezNotaireList: Contrat.findAllByChezNotaire(true),
			contratAReviserList: Contrat.findAllByEstTermine(true)
		]
	}
}
