package albybail

class EditionFactureController {
	
	def contratService
	
	
    def index() {
		genererRevisions()
	}
	
	def genererRevisions() {
		Contrat.findAll().each {
			contratService.nouveauContrat(it)
		}
		
		def listContrat = Contrat.findWhere(estTermine: false, chezNotaire:false)
		listContrat
	}
}
