package albybail

class Facturable {
	
	BigDecimal	valeur
	String		description

	Contrat		contrat
	
	static belongsTo = Contrat

    static constraints = {
		description	blank: false, size: 3..50
		valeur		nullable: true, min: 0.0
    }
}
