package albybail

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RevisionController {

	def contratService
	
    def scaffold = true
	
	def listAReviser() {
		return [contratAReviserList: Contrat.findAllByEstTermine(true)]
	}
	
	def reviser() {
		def contrat = Contrat.get(params.id)

		contratService.creerRevision(contrat)
		
		return [
			revisionActive: contrat.revisionActive,
			revisionInstance: revisionInstance
		]
	}
}
