package albybail

class Batiment {
	
	String		nom
	String		adresse

	static hasMany = [ locaux: Local ]
	
    static constraints = {
		nom			blank: false, size: 3..100, unique: true
		adresse		blank: true, nullable: true, maxSize: 400
    }
	
	static mapping = {
		sort	"nom"
		locaux	sort:"nom"
	}
	
	String toString() {
		nom
	}
}
