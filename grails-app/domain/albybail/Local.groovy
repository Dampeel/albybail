package albybail

class Local {
	
	String		nom
	Integer		surface
	
	Batiment 	batiment
	
	static belongsTo = [ Batiment, Contrat ]
	static hasMany = [ contrats: Contrat ]

    static constraints = {
		nom		blank: false, size: 3..100, unique: true
		surface	nullable: true, min: 0
    }
	
	static mapping = {
		sort "nom"
	}
	
	String toString() {
		nom
	}
	
	boolean equals(obj) {
		if (!(obj instanceof Local)) {
			return false
		}
		nom == obj.nom
	}
}
