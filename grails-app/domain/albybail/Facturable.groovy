package albybail

class Facturable {
	
	Date		dateDebut
	Date		dateFin
	BigDecimal	valeur
	String		description

	Contrat		contrat
	
	static belongsTo = Contrat

    static constraints = {
		dateFin		validator: { val, obj -> val?.after(obj.dateDebut) }
		valeur		nullable: true, min: 0.0, scale: 2
		description	blank: false, size: 3..50
    }
}
