package albybail

class Contrat {
	
	String		nom
	Date		dateDebut		= new Date()
	Date		dateFin
	Integer		dureeRevision	= 3
	Boolean		chezNotaire		= true
	Boolean		estTermine		= false
	BigDecimal	montantLoyer
	String		remarques
	
	Locataire	locataire
	
	static hasOne = [revisionActive: Revision]
	
	static hasMany = [
		locaux: 		Local,
		revisions:		Revision,
		facturables:	Facturable,
		editions:		Edition
	]
	
    static constraints = {
		nom				blank: false, size: 3..100
		dateFin			validator: { val, obj -> val?.after(obj.dateDebut) }
		montantLoyer	min: 0.0
		remarques		blank: true, nullable: true, sizeMax: 500
		revisionActive	nullable: true
    }
}
