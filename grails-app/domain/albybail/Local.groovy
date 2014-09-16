package albybail

class Local {
	
	String	nom
	Integer	surface
	
	Batiment batiment
	
	static belongsTo = [ Batiment, Contrat ]
	static hasMany = [ contrats: Contrat ]

    static constraints = {
		nom		blank: false, size: 3..100
		surface	nullable: true, min: 0
    }
}
