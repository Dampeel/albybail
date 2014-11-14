package albybail

class Facturable {
	
	Date		date		= new Date()
	String		nom
	BigDecimal	valeur
	String		description
	Boolean		aReguler 	= false

	Contrat		contrat
	
	static belongsTo = Contrat

    static constraints = {
		nom			blank: false, size: 3..100
		valeur		nullable: true, min: 0.0, scale: 2
		description	blank: false, size: 3..50
    }
}
