package albybail

class Locataire {

	String	nom
	String	adresse
	String	remarques
	
	static hasMany = [contrats: Contrat]
	
    static constraints = {
		nom			blank: false, size: 3..100, unique: true
		adresse		blank: true, nullable: true, maxSize: 400
		remarques	blank: true, nullable: true
    }
}
