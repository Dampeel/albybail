package albybail

class Edition {
	
	String		nom
	Date		date
	
	// Elements recopies sur les autres objets
	String		dateFacture
	String		nomContrat
	String		adresseLocataire
	String		adresseBatiment
	String		nomTotal
	BigDecimal	total

	Contrat		contrat
	
	static belongsTo = Contrat
	
	static hasMany = [ lignes: LigneEdition ]
	
    static constraints = {
		nom					blank: false, size: 3..100
		dateFacture			blank: false, size: 3..50
		adresseLocataire	blank: false, size: 3..400
		adresseBatiment		blank: false, size: 3..400
		nomContrat			blank: false, size: 3..100
		nomTotal			blank: false, size: 3..50
		total				min: 0.0, scale: 2
    }
	
	static mapping = {
		sort "nom"
	}
	
	String toString() {
		nom
	}
}
