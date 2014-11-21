package albybail

class Batiment implements Comparable {
	
	String		nom
	String		adresse
	
	SortedSet	locaux

	static hasMany = [ locaux: Local ]
	
    static constraints = {
		nom			blank: false, size: 3..100, unique: true
		adresse		blank: true, nullable: true, maxSize: 400
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
