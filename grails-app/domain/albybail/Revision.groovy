package albybail

class Revision {
	
	Date		dateDebut
	Date		dateFin
	BigDecimal	indice
	BigDecimal	montantLoyer
	String		remarques

	Contrat		contrat
	
	static belongsTo = Contrat

    static constraints = {
		dateFin			validator: { val, obj -> val?.after(obj.dateDebut) }
		indice			min: 0.0, scale: 2
		montantLoyer	min: 0.0, scale: 2
		remarques		blank: true, nullable: true, sizeMax: 500
    }
	
	static mapping = {
		sort dateDebut:"desc"
	} 
	
	String toString() {
		"Révision du " + dateDebut.format("dd/MM/yyyy") + " au " + dateFin.format("dd/MM/yyyy")
	}
	
	boolean equals(obj) {
		dateDebut == obj.dateDebut && dateFin == obj.dateFin && contrat == obj.contrat
	}
}