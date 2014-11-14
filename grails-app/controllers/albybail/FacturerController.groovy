package albybail

class FacturerController {
    
	def contratService
	def facturationService

    static allowedMethods = [sauver: "POST"]

    def index() {
		return [contrats: contratService.listeAFacturer()]
	}
	
	def facturer() {
		def contrat = Contrat.get(params.contratId)
		def dateFacturation = new Date()
		dateFacturation[Calendar.MONTH] = Calendar.OCTOBER
		
		facturationService.creerFacturables(contrat, dateFacturation)
		
		def facturables = contrat.facturables
		println facturables
		
		return [facturables: facturables]
	}
}
