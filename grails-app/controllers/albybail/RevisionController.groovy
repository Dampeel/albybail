package albybail

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RevisionController {

	def contratService
	
	def beforeInterceptor = [ action: {
			params.dateDebut = params.date('dateDebut', 'dd/MM/yyyy')
			params.dateFin = params.date('dateFin', 'dd/MM/yyyy')
		}, only:['sauver', 'save', 'update']
	]

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", sauver: "POST"]
	
	def listeAReviser() {
		return [contratAReviserList: contratService.listeAReviser()]
	}
	
	def reviser() {
		def contrat = Contrat.get(params.contratId)
		def revisionInstance = contratService.creerRevision(contrat)
		
		respond revisionInstance
	}
	
	@Transactional
	def sauver(Revision revisionInstance) {
		if (revisionInstance == null) {
            notFound()
            return
        }

        if (revisionInstance.hasErrors()) {
            respond revisionInstance.errors, view:'reviser'
            return
        }

        revisionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'revision.label', default: 'Revision'), revisionInstance.id])
                redirect revisionInstance
            }
            '*' { respond revisionInstance, [status: CREATED] }
        }
	}

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Revision.list(params), model:[revisionInstanceCount: Revision.count()]
    }

    def show(Revision revisionInstance) {
        respond revisionInstance
    }

    def create() {
        respond new Revision(params)
    }

    @Transactional
    def save(Revision revisionInstance) {
        if (revisionInstance == null) {
            notFound()
            return
        }

        if (revisionInstance.hasErrors()) {
            respond revisionInstance.errors, view:'create'
            return
        }

        revisionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'revision.label', default: 'Revision'), revisionInstance.id])
                redirect revisionInstance
            }
            '*' { respond revisionInstance, [status: CREATED] }
        }
    }

    def edit(Revision revisionInstance) {
        respond revisionInstance
    }

    @Transactional
    def update(Revision revisionInstance) {
        if (revisionInstance == null) {
            notFound()
            return
        }

        if (revisionInstance.hasErrors()) {
            respond revisionInstance.errors, view:'edit'
            return
        }

        revisionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Revision.label', default: 'Revision'), revisionInstance.id])
                redirect revisionInstance
            }
            '*'{ respond revisionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Revision revisionInstance) {

        if (revisionInstance == null) {
            notFound()
            return
        }

        revisionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Revision.label', default: 'Revision'), revisionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'revision.label', default: 'Revision'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
