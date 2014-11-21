package albybail

class Indice {

	String		nom
	Date		dateDebut
	Date		dateFin
	BigDecimal	valeur
	
    static constraints = {
		valeur	nullable: true, min: 0.0
    }
	
	String toString() {
		return nom
	}
}
