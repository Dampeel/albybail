package albybail

class Contrat {
	
	String		nom
	Date		dateDebut		= new Date()
	Date		dateFin
	Integer		dureeRevision	= 3
	BigDecimal	montantLoyer
	BigDecimal	montantCharges
	String		remarques
	Boolean		estNouveau		= true
	Boolean		chezNotaire		= true
	Boolean		estTermine		= false
	
	Locataire	locataire
	Revision	revisionActive
	
	static hasMany = [
		locaux: 		Local,
		revisions:		Revision,
		facturables:	Facturable,
		editions:		Edition
	]
	
    static constraints = {
		nom				blank: false, size: 3..100
		dateFin			validator: { val, obj -> val?.after(obj.dateDebut) }
		montantLoyer	min: 0.0, scale: 2
		montantCharges	min: 0.0, scale: 2
		remarques		blank: true, nullable: true, sizeMax: 500
		revisionActive	nullable: true
    }
}
