package albybail

class Indice {

	Date		date
	BigDecimal	valeur
	
    static constraints = {
		date	unique: true
		valeur	nullable: true, min: 0.0
    }
}
