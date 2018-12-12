package pru03.E02;

public class PRU03E02Test_Bruno_Porcel {
	
	//ARRANCAR MOTOR
	public static void encendreCotxe(PRU03E02Cotxe_Bruno_Porcel pandita) {
		
		try {
			pandita.arrancarMotor();
			System.out.println("S' ha encès el cotxe.");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//COMPROVAR ESTADO DEL MOTOR
	public static void estatMotor(PRU03E02Cotxe_Bruno_Porcel pandita) {
		String estat_motor = "";
		
		estat_motor = pandita.comprovaMotor().toString();
		System.out.println("Estat del motor: "+estat_motor);
	}
	
	//REVOLUCIONES DEL MOTOR
	public static void rpm(PRU03E02Cotxe_Bruno_Porcel pandita) {
		int revolucions;
		
		revolucions = pandita.getRevolucions();

		System.out.print("El cotxe es troba a "+revolucions+" rpm ");
		if(revolucions <= 2500 && revolucions > 0) {
			System.out.println("jo baixaria una marxa...");
		}else if(revolucions > 4499) {
			System.out.println("jo pujaria una marxa... O ANIRIA DE RALLIES!!");
		} else {
			System.out.println();
		}
	}
	//ATURAR MOTOR
	public static void apagarCotxe(PRU03E02Cotxe_Bruno_Porcel pandita) {
		
		try {
			pandita.aturarMotor();
			System.out.println("S' ha apagat el cotxe.");
		}catch(Exception e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	//MAIN-------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		PRU03E02Cotxe_Bruno_Porcel pandita = new PRU03E02Cotxe_Bruno_Porcel("Fiat","Panda",TipusCanvi.CanviManual, EstatsMotorCotxe.Aturat);
		
		encendreCotxe(pandita);
		estatMotor(pandita);
		rpm(pandita);
		apagarCotxe(pandita);
		estatMotor(pandita);
	}
}