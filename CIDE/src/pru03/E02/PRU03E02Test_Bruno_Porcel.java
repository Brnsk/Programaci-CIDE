package pru03.E02;

import java.util.Scanner;

public class PRU03E02Test_Bruno_Porcel {
	
	//ARRANCAR MOTOR
	public static void encendreCotxe(PRU03E02SubCotxe_Bruno_Porcel pandita) {
		
		try {
			pandita.arrancarMotor();
			System.out.println("S' ha encès el cotxe.");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//COMPROVAR ESTADO DEL MOTOR
	public static void estatMotor(PRU03E02SubCotxe_Bruno_Porcel pandita) {
		String estat_motor = "";
		
		estat_motor = pandita.comprovaMotor().toString();
		System.out.println("Estat del motor: "+estat_motor);
	}
	
	//REVOLUCIONES DEL MOTOR
	public static void rpm(PRU03E02SubCotxe_Bruno_Porcel pandita) {
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
	public static void apagarCotxe(PRU03E02SubCotxe_Bruno_Porcel pandita) {
		
		try {
			pandita.aturarMotor();
			System.out.println("S' ha apagat el cotxe.");
		}catch(Exception e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	//CANVIAR DE MARXA
	public static void canviarMarxa(PRU03E02SubCotxe_Bruno_Porcel pandita){
		String marxa = "";
		char puja_baixa = ' ';
		
		if(pandita.motor.equals(EstatsMotorCotxe.EnMarxa)) {
			
			//Printar la marxa actual
			if(pandita.tipuscanvi.equals(TipusCanvi.CanviManual) && !primer_canvi_marxa){
				pandita.marxa_man = MarxaManual.primera;
				System.out.println("Et trobes en la marxa: "+pandita.marxa_man);
				primer_canvi_marxa = true;
			}else if (pandita.tipuscanvi.equals(TipusCanvi.CanviAutomatic) && !primer_canvi_marxa){
				pandita.marxa_aut = MarxaAutomatic.N;
				System.out.println("Et trobes en la marxa: "+pandita.marxa_aut);
				primer_canvi_marxa = true;
			}
			
			//Demanar per pujar o baixar marxa
			do {
				System.out.println("Pitja '+' per pujar de marxa o '-' per baixar: ");
				puja_baixa = sc.next().charAt(0);
			}while(puja_baixa != '+' && puja_baixa != '-');
			
			//Canvi manual
			if(pandita.tipuscanvi.equals(TipusCanvi.CanviManual)) {
				try {
					marxa = pandita.canviarMarxaManual(puja_baixa).toString();
					if(marxa.equals("enrera")) {
						System.out.println("Has canviat a marxa "+marxa+".");
					}else {
						System.out.println("Has canviat a "+marxa+" marxa.");
					}
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			//Canvi automatic
			}else {
				try {
					marxa = pandita.canviarMarxaAutomatic(puja_baixa).toString();
					System.out.println("Has canviat a marxa "+marxa+".");			
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}else {
			System.out.println("Jo primer encendria el cotxe...");
		}
	}
	
	//variables statiques
	static Scanner sc = new Scanner(System.in);
	static boolean primer_canvi_marxa = false;
	
	//MAIN-------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		PRU03E02SubCotxe_Bruno_Porcel pandita = new PRU03E02SubCotxe_Bruno_Porcel("Fiat","Panda",TipusCanvi.CanviManual, EstatsMotorCotxe.Aturat);
		
		encendreCotxe(pandita);
		estatMotor(pandita);
		rpm(pandita);
		canviarMarxa(pandita);
		canviarMarxa(pandita);
		canviarMarxa(pandita);
		canviarMarxa(pandita);
		canviarMarxa(pandita);
		canviarMarxa(pandita);
		apagarCotxe(pandita);
		estatMotor(pandita);
	}
}