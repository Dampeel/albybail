package albybail

class Facturable {
	
	Date		date		= new Date()
	String		categorie
	BigDecimal	valeur
	String		description
	Boolean		aReguler 	= false
	Boolean		aEditer		= true

	Contrat		contrat
	
	static belongsTo = Contrat

    static constraints = {
		categorie	blank: false, size: 3..100
		valeur		nullable: true, min: 0.0, scale: 2
		description	blank: false, size: 3..200
    }
	
	static mapping = {
		sort date:"desc"
	}
	
	String toString() {
		date.month + " - " + description
	}
}
