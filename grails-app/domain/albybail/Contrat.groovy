package albybail

class Contrat {
	
	String		nom
	Date		dateDebut		= new Date()
	Date		dateFin
	Integer		dureeRevision
	Boolean		chezNotaire		= true
	Boolean		estTermine
	String		remarques
	
	Locataire	locataire
	
	static hasMany = [
		locaux: 	Local,
		revisions:	Revision,
		lignes:		LigneFacture
	]
	
    static constraints = {
		nom				blank: false, size: 3..100
		dateFin			validator: { val, obj -> val?.after(obj.dateDebut) }
		remarques		blank: true, nullable: true
    }
}
