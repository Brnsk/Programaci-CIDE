package pru03.E04;

public class PRU03E04TempsTest_Bruno_Porcel {
	
	//Metode per crear instancies
	public static PRU03E04Temps_Bruno_Porcel inicialitzaInstancia(int h, int m, int s,PRU03E04Temps_Bruno_Porcel t) {
		//Instanciar interval de temps
		try {
			t = new PRU03E04Temps_Bruno_Porcel(h,m,s);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			error = true;
		}
		
		return t;
	}
	
	//Sumar intervals
	public static void sumarIntervals(PRU03E04Temps_Bruno_Porcel t, PRU03E04Temps_Bruno_Porcel t2) {
		PRU03E04Temps_Bruno_Porcel tResultat = new PRU03E04Temps_Bruno_Porcel();
		String result;
		
		if(!error) {
			tResultat = t.suma(t2);
			result = tResultat.toString();
			System.out.println("Suma = "+result);
		}
	}
	
	//Restar intervals
	public static void restarIntervals(PRU03E04Temps_Bruno_Porcel t, PRU03E04Temps_Bruno_Porcel t2) {
		PRU03E04Temps_Bruno_Porcel tResultat = new PRU03E04Temps_Bruno_Porcel();
		String result;
		
		if(!error) {
			tResultat = t.resta(t2);
			result = tResultat.toString();
			System.out.println("Resta = "+result);
		}
	}
	
	//BOOLEAN ERROR
	static boolean error = false;
	
	public static void main(String[] args) {
		PRU03E04Temps_Bruno_Porcel t = new PRU03E04Temps_Bruno_Porcel();
		PRU03E04Temps_Bruno_Porcel t2 = new PRU03E04Temps_Bruno_Porcel();
		
		t = inicialitzaInstancia(22,45,50,t);
		t2 = inicialitzaInstancia(10,15,30,t2);
		
		
		sumarIntervals(t,t2);
		restarIntervals(t,t2);
	}
}