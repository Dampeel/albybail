package albybail

import java.util.Date;

class Plage {

	Integer	moisDebut
	Integer	moisFin
	
	static belongsTo = Profil
	
    static constraints = {
    }
	
	// renvoie la date de début de la plage
	public Date dateDebut(Date date) {
		def dateDebut = new GregorianCalendar(2000,1,1,0,0,0);
		dateDebut[Calendar.YEAR] = date[Calendar.YEAR]
		dateDebut[Calendar.MONTH] = this.moisDebut-1
		dateDebut[Calendar.DAY_OF_MONTH] = dateDebut.getActualMinimum(Calendar.DAY_OF_MONTH)
		return dateDebut.time
	}
	
	// renvoie la date de fin de la plage
	public Date dateFin(Date date) {
		def dateFin = new GregorianCalendar(2000,1,1,23,59,59);
		dateFin[Calendar.YEAR] = date[Calendar.YEAR]
		dateFin[Calendar.MONTH] = this.moisFin-1
		dateFin[Calendar.DAY_OF_MONTH] = dateFin.getActualMaximum(Calendar.DAY_OF_MONTH)
		return dateFin.time
	}
	
	public String toString() {
		return "Plage du " + moisDebut + " au " + moisFin
	}
}
