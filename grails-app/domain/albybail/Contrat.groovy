package albybail

class Contrat implements Comparable {
	
	String		nom
	Date		dateDebut		= new Date()
	Date		dateFin
	Integer		dureeRevision	= 3
	BigDecimal	montantLoyer
	BigDecimal	montantCharges
	String		remarques
	Boolean		chezNotaire		= true
	Boolean		estTermine		= false
	
	Locataire	locataire
	Revision	revisionActive
	Profil		profil
	SortedSet	locaux
	SortedSet	revisions
	SortedSet	facturables
	SortedSet	editions
	
	static hasMany = [
		locaux: 		Local,
		revisions:		Revision,
		facturables:	Facturable,
		editions:		Edition
	]
	
    static constraints = {
		nom				blank: false, size: 3..100
		dateDebut		validator: { val -> val?.before(new Date()) }
		dateFin			validator: { val, obj -> val?.after(obj.dateDebut) }
		montantLoyer	min: 0.0, scale: 2
		montantCharges	min: 0.0, scale: 2
		remarques		blank: true, nullable: true, sizeMax: 500
		revisionActive	nullable: true
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
