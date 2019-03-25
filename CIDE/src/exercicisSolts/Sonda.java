package exercicisSolts;

import java.util.Date;

public abstract class Sonda {
	public boolean enregistrarTemperatura(String ip, String ubicacio, double valor) {
		return false;
	}
	
	public double obtenirTemperatura(String ubicacio, Date data) {
		return 0;
	}
}