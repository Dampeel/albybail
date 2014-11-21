package albybail

class Local implements Comparable {
	
	String		nom
	Integer		surface
	
	Batiment 	batiment
	SortedSet	contrats
	
	static belongsTo = [ Batiment, Contrat ]
	static hasMany = [ contrats: Contrat ]

    static constraints = {
		nom		blank: false, size: 3..100
		surface	nullable: true, min: 0
    }
	
	static mapping = {
		sort "nom"
	} 
	
	String toString() {
		return nom
	}
	
	int compareTo(obj) {
		nom.compareTo(obj.nom)
	}
}
