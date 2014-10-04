package albybail

class Indice {

	String		trimestre
	String		annee
	BigDecimal	valeur
	
    static constraints = {
		// TODO : contraintes trimestre et annee
		valeur	nullable: true, min: 0.0
    }
}
