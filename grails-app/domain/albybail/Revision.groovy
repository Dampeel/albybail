package albybail

class Revision implements Comparable {
	
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
		sort "dateDebut"
	} 
	
	String toString() {
		return "Révision du " + dateDebut.format("dd/MM/yyyy") + " au " + dateFin.format("dd/MM/yyyy")
	}
	
	int compareTo(obj) {
		dateDebut.compareTo(obj.dateDebut)
	}
}