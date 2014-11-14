package albybail

class Revision {
	
	Date		dateDebut
	Date		dateFin
	BigDecimal	indice
	BigDecimal	montantLoyer
	BigDecimal	montantCharges
	String		remarques

	Contrat		contrat
	
	static belongsTo = Contrat

    static constraints = {
		dateFin			validator: { val, obj -> val?.after(obj.dateDebut) }
		indice			min: 0.0, scale: 2
		montantLoyer	min: 0.0, scale: 2
		montantCharges	min: 0.0, scale: 2
		remarques		blank: true, nullable: true, sizeMax: 500
    }
	
	String toString() {
		return "Révision du " + dateDebut + " au " + dateFin + " pour le contrat " + contrat.nom
	}
}