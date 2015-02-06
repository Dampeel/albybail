package albybail

class LigneEdition {
	
	String		description
	BigDecimal	valeur
	
	Edition		edition
	
	static belongsTo = Edition

    static constraints = {
		description	blank: false, size: 3..50
		valeur		min: 0.0
    }
	
	static mapping = {
		sort "description"
	}
	
	String toString() {
		description
	}
}
