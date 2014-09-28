package albybail

class EditionFactureController {
	
	def contratService

	/* Edition des factures :
	 * 1. Générer les révisions nécessaires (en fonction de la date de révision)
	 * 2. Générer les facturables du mois, si ce n'est pas déjà fait :
	 * 		- les loyers facturables (cf. fréquence de facturation)
	 * 		- les mises à jour dues à la mise à jour d'un indice
	 * 3. Générer les lignes d'édition
	 * 4. Générer les éditions complètes
	 * 5. Générer les PDFs (loyer, revalorisation, charges)
	 * */
	
	/* Génération des facturables :
	 * 1. Par le menu d'édition des factures
	 * 		- Choix du mois de facturation (possibilité de faire en avance de phase ? Au moins un mois a priori)
	 * 		- Pour tous les contrats, si (!chezNotaire && !estTerminé && !existe déjà) alors créer le facturable de type loyer
	 * 		- si (pas d'indice à la date de revalorisation), possibilité de marquer le facturable comme "non revalorisé"
	 * 2. Par mise à jour de l'indice INSEE
	 * 		- Création de l'indice
	 * 		- Pour toutes les éditions existantes, si (date revalorisation < date indice) alors créer facturable de type reval
	 * 		
	 * */
	
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
