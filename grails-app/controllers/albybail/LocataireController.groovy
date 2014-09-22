package albybail



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LocataireController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Locataire.list(params), model:[locataireInstanceCount: Locataire.count()]
    }

    def show(Locataire locataireInstance) {
        respond locataireInstance
    }

    def create() {
        respond new Locataire(params)
    }

    @Transactional
    def save(Locataire locataireInstance) {
        if (locataireInstance == null) {
            notFound()
            return
        }

        if (locataireInstance.hasErrors()) {
            respond locataireInstance.errors, view:'create'
            return
        }

        locataireInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'locataire.label', default: 'Locataire'), locataireInstance.id])
                redirect locataireInstance
            }
            '*' { respond locataireInstance, [status: CREATED] }
        }
    }

    def edit(Locataire locataireInstance) {
        respond locataireInstance
    }

    @Transactional
    def update(Locataire locataireInstance) {
        if (locataireInstance == null) {
            notFound()
            return
        }

        if (locataireInstance.hasErrors()) {
            respond locataireInstance.errors, view:'edit'
            return
        }

        locataireInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Locataire.label', default: 'Locataire'), locataireInstance.id])
                redirect locataireInstance
            }
            '*'{ respond locataireInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Locataire locataireInstance) {

        if (locataireInstance == null) {
            notFound()
            return
        }

        locataireInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Locataire.label', default: 'Locataire'), locataireInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'locataire.label', default: 'Locataire'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
