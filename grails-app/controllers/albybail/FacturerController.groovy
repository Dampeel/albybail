package albybail

class FacturerController {
    
	def contratService
	def facturationService

    static allowedMethods = [sauver: "POST"]

    def index() {
		def date = new Date()
		return [contrats: contratService.listeAFacturer(date)]
	}
	
	def facturer() {
		def contrat = Contrat.get(params.contratId)
		def dateFacturation = new Date()
		
		facturationService.creerFacturables(contrat, dateFacturation)
		
		def facturables = contrat.facturables
		println facturables
		
		return [facturables: facturables]
	}
}
