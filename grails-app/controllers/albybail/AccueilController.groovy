package albybail

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AccueilController {

	def contratService
	
	//TODO : différence entre index() et index=
    def index() {
		return [
			contratAReviserList: contratService.listeAReviser(new Date()),
			contratChezNotaireList: Contrat.findAllByChezNotaire(true)
		]
	}
}
