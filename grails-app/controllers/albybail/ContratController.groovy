package albybail



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ContratController {
	
	def beforeInterceptor = [ action: {
			params.dateDebut = params.date('dateDebut', 'dd/MM/yyyy')
			params.dateFin = params.date('dateFin', 'dd/MM/yyyy')
		}, only:['save', 'update']
	]

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Contrat.list(params), model:[contratInstanceCount: Contrat.count()]
    }

    def show(Contrat contratInstance) {
        respond contratInstance
    }

    def create() {
        respond new Contrat(params)
    }

    @Transactional
    def save(Contrat contratInstance) {
        if (contratInstance == null) {
            notFound()
            return
        }

        if (contratInstance.hasErrors()) {
            respond contratInstance.errors, view:'create'
            return
        }

        contratInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contrat.label', default: 'Contrat'), contratInstance.id])
                redirect contratInstance
            }
            '*' { respond contratInstance, [status: CREATED] }
        }
    }

    def edit(Contrat contratInstance) {
        respond contratInstance
    }

    @Transactional
    def update(Contrat contratInstance) {
        if (contratInstance == null) {
            notFound()
            return
        }

        if (contratInstance.hasErrors()) {
            respond contratInstance.errors, view:'edit'
            return
        }

        contratInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Contrat.label', default: 'Contrat'), contratInstance.id])
                redirect contratInstance
            }
            '*'{ respond contratInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Contrat contratInstance) {

        if (contratInstance == null) {
            notFound()
            return
        }

        contratInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Contrat.label', default: 'Contrat'), contratInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contrat.label', default: 'Contrat'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
