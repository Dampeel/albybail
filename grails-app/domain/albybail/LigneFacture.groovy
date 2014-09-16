package albybail

class LigneFacture {
	
	BigDecimal	valeur
	String		description

	Edition		edition
	Contrat		contrat
	
	static belongsTo = Contrat

    static constraints = {
		description	blank: false, size: 3..50
		valeur		nullable: true, min: 0.0
		edition		nullable: true
    }
}
