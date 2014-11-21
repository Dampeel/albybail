package albybail



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LocalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Local.list(params), model:[localInstanceCount: Local.count()]
    }

    def show(Local localInstance) {
        respond localInstance
    }

    def create() {
        respond new Local(params)
    }

    @Transactional
    def save(Local localInstance) {
        if (localInstance == null) {
            notFound()
            return
        }

        if (localInstance.hasErrors()) {
            respond localInstance.errors, view:'create'
            return
        }

        localInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'local.label', default: 'Local'), localInstance.id])
                redirect localInstance
            }
            '*' { respond localInstance, [status: CREATED] }
        }
    }

    def edit(Local localInstance) {
        respond localInstance
    }

    @Transactional
    def update(Local localInstance) {
        if (localInstance == null) {
            notFound()
            return
        }

        if (localInstance.hasErrors()) {
            respond localInstance.errors, view:'edit'
            return
        }

        localInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Local.label', default: 'Local'), localInstance.id])
                redirect localInstance
            }
            '*'{ respond localInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Local localInstance) {

        if (localInstance == null) {
            notFound()
            return
        }

        localInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Local.label', default: 'Local'), localInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'local.label', default: 'Local'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
