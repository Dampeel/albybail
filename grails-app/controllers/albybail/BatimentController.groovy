package albybail



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BatimentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Batiment.list(params), model:[batimentInstanceCount: Batiment.count()]
    }

    def show(Batiment batimentInstance) {
        respond batimentInstance
    }

    def create() {
        respond new Batiment(params)
    }

    @Transactional
    def save(Batiment batimentInstance) {
        if (batimentInstance == null) {
            notFound()
            return
        }

        if (batimentInstance.hasErrors()) {
            respond batimentInstance.errors, view:'create'
            return
        }

        batimentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'batiment.label', default: 'Batiment'), batimentInstance.id])
                redirect batimentInstance
            }
            '*' { respond batimentInstance, [status: CREATED] }
        }
    }

    def edit(Batiment batimentInstance) {
        respond batimentInstance
    }

    @Transactional
    def update(Batiment batimentInstance) {
        if (batimentInstance == null) {
            notFound()
            return
        }

        if (batimentInstance.hasErrors()) {
            respond batimentInstance.errors, view:'edit'
            return
        }

        batimentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Batiment.label', default: 'Batiment'), batimentInstance.id])
                redirect batimentInstance
            }
            '*'{ respond batimentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Batiment batimentInstance) {

        if (batimentInstance == null) {
            notFound()
            return
        }

        batimentInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Batiment.label', default: 'Batiment'), batimentInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'batiment.label', default: 'Batiment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
