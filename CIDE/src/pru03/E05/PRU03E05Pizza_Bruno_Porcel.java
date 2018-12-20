package pru03.E05;

public class PRU03E05Pizza_Bruno_Porcel {
	private String mida;
	private String tipus;
	private String estat;
	private static int demanades;
	private static int servides;
	
	//Constructor
	public PRU03E05Pizza_Bruno_Porcel(String mida, String tipus){
		if((tipus != "margarita" && tipus != "quatre formatges" && tipus != "funghi") || (mida != "mitjana" && mida != "familiar")) {
			System.out.println("Error: Has introduit un tipus o una mida de pizza incorrecta.");
		}else {
			this.mida = mida;
			this.tipus = tipus;
			this.estat = "demanada";
			
			demanades++;
		}
	}
	
	//Getters
	public static int getTotalDemanades() {
		
		return demanades;
	}
	public static int getTotalServides() {
		
		return servides;
	}
	
	//Metodes propis de la classe
	public void sirve() {
		
		if(this.estat == "demanada") {
			this.estat = "servida";
			System.out.println("S' ha servit la pizza.");
			servides++; 
		}else {
			System.out.println("L' ha pizza ja s' ha servit.");
		}
	}
	
	@Override
	public String toString() {
		return "Pizza "+this.tipus+" "+this.mida+", "+this.estat;
	}
}