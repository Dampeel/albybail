package albybail

import grails.transaction.Transactional

class ReviserController {
    
	def contratService
	def revisionService
	
	def beforeInterceptor = [ action: {
			params.dateDebut = params.date('dateDebut', 'dd/MM/yyyy')
			params.dateFin = params.date('dateFin', 'dd/MM/yyyy')
		}, only:['sauver']
	]

    static allowedMethods = [sauver: "POST"]
	
	def index() {
		return [revisions: contratService.listeAReviser()]
	}
	
	def reviser() {
		def contrat = Contrat.get(params.contratId)
		def revision = revisionService.creerRevision(contrat)
		
		return [revision: revision]
	}
	
	@Transactional
	def sauver(Revision revision) {

        if (revision.hasErrors()) {
            render(view: "reviser", model: [revision: revision])
			return
        }

		revisionService.sauverRevision(revision)

        flash.message = message(code: 'default.created.message', args: [message(code: 'revision.label', default: 'Revision'), revision.id])
        redirect(action: "index")
	}
}
