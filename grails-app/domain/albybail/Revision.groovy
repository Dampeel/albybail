package albybail

class Revision {
	
	Date	dateDebut
	Date	dateFin
	Indice	indice
	String	remarques
	 
	Contrat	contrat
	
	static belongsTo = Contrat

    static constraints = {
		dateFin		validator: { val, obj -> val?.after(obj.dateDebut) }
		remarques	blank: true, nullable: true
    }
}