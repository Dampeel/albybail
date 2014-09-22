package albybail



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class IndiceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Indice.list(params), model:[indiceInstanceCount: Indice.count()]
    }

    def show(Indice indiceInstance) {
        respond indiceInstance
    }

    def create() {
        respond new Indice(params)
    }

    @Transactional
    def save(Indice indiceInstance) {
        if (indiceInstance == null) {
            notFound()
            return
        }

        if (indiceInstance.hasErrors()) {
            respond indiceInstance.errors, view:'create'
            return
        }

        indiceInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'indice.label', default: 'Indice'), indiceInstance.id])
                redirect indiceInstance
            }
            '*' { respond indiceInstance, [status: CREATED] }
        }
    }

    def edit(Indice indiceInstance) {
        respond indiceInstance
    }

    @Transactional
    def update(Indice indiceInstance) {
        if (indiceInstance == null) {
            notFound()
            return
        }

        if (indiceInstance.hasErrors()) {
            respond indiceInstance.errors, view:'edit'
            return
        }

        indiceInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Indice.label', default: 'Indice'), indiceInstance.id])
                redirect indiceInstance
            }
            '*'{ respond indiceInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Indice indiceInstance) {

        if (indiceInstance == null) {
            notFound()
            return
        }

        indiceInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Indice.label', default: 'Indice'), indiceInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'indice.label', default: 'Indice'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
