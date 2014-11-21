package albybail

class Profil {
	
	String nom
	
	static hasMany = [ plages: Plage ]

    static constraints = {
		nom	blank: false, size: 3..100
    }
	
	String toString() {
		return nom
	}
}
