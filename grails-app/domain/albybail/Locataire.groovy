package albybail

class Locataire implements Comparable {

	String		nom
	String		adresse
	String		remarques
	
	SortedSet	contrats
	
	static hasMany = [contrats: Contrat]
	
    static constraints = {
		nom			blank: false, size: 3..100, unique: true
		adresse		blank: true, nullable: true, maxSize: 400
		remarques	blank: true, nullable: true
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
