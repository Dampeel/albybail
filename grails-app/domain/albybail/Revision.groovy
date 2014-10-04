package albybail

class Revision {
	
	Date		dateDebut
	Date		dateFin
	Indice		indice
	BigDecimal	montantLoyer
	BigDecimal	montantCharges
	String		remarques
	Boolean		aReguler		= true

	Contrat		contrat
	
	static belongsTo = Contrat

    static constraints = {
		dateFin			validator: { val, obj -> val?.after(obj.dateDebut) }
		indice			nullable: true
		montantLoyer	min: 0.0
		montantCharges	min: 0.0
		remarques		blank: true, nullable: true, sizeMax: 500
    }
}