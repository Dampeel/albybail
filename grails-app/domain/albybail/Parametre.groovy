package albybail

class Parametre {
	
	String	nom
	String	typede merde
	String	valeur

    static constraints = {
		nom		blank: false, size: 3..50
		type	blank: false, size: 3..50
		valeur	blank: false, size: 3..200
    }
}
