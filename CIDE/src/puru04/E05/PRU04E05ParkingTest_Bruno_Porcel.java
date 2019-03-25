package puru04.E05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import puru04.E05.PRU04E05Parking_Bruno_Porcel.TipusPlacesParking;

//Printar menu
public class PRU04E05ParkingTest_Bruno_Porcel {

	//Printar el menu
	public static void printMenu() {
		int choice = 0;
		Scanner sc = new Scanner(System.in);

		try {
			do{
				System.out.println("=================================");
				System.out.println("Elegeix una de les següents opcions");
				System.out.println("=================================");
				System.out.println("1.Omplir parking a partir de fitxer*");
				System.out.println("2.Entrar Cotxe");
				System.out.println("3.Entrar Cotxe Discapacitat");
				System.out.println("4.Surtir Cotxe");
				System.out.println("5.Surtir Cotxe Discapacitat");
				System.out.println("6.Guardar llistat de matricules en fitxer*");
				System.out.println("7.Places normals lliures");
				System.out.println("8.Places normals ocupades");
				System.out.println("9.Places de discapacitats lliures");
				System.out.println("10.Places de discapacitats ocupades");
				System.out.println("11.Sortir");
				

				choice = sc.nextInt();
			}while(choice <=0 || choice > 11);

			respostaMenu(choice);

		}catch(Exception e) {
			System.out.println("Per favor, escull un valor valid.");
			System.out.println("=================================");
			printMenu();
		}
	}

	//Executar menu
	public static void respostaMenu(int choice) {
		Scanner sc = new Scanner(System.in);
		String path = "";
		File archivo;
		int places;
		
		switch(choice) {
		case 1:
			do{
				System.out.println("Per favor escriu la ruta ruta del fitxer:");
				path = sc.nextLine();
				archivo = new File(path);
			}while(!archivo.exists());

			matriculesFile(path);
		break;
		case 2: entrarCotxes("",choice);
		break;
		case 3: entrarCotxes("",choice);
		break;
		case 4: sortirCotxes(choice);
		break;
		case 5: sortirCotxes(choice);
		break;
		case 6: guardarMatricules();
		break;
		case 7: places = parking.getPlacesLliures(tipus.normals);
		System.out.println("Places normals lliures = "+places);
		break;
		case 8: places = parking.getPlacesOcupades(tipus.normals);
		System.out.println("Places normals ocupades = "+places);
		break;
		case 9: places = parking.getPlacesLliures(tipus.discapacitats);
		System.out.println("Places per a discapacitats lliures = "+places);
		break;
		case 10: places = parking.getPlacesOcupades(tipus.discapacitats);
		System.out.println("Places per a discapacitats ocupades = "+places);
		break;
		case 11: exit();
		}
	}	

	//Comproba el Fitxer
	public static void matriculesFile(String path) {
		try {
			//Comprobar format 
			parking.llegirMatricules(path);
			
			FileReader fr = new FileReader(path);	
			BufferedReader archivo = new BufferedReader(fr);
			String matricula = "";
			int random;
			
			//Entrar cotxes
			matricula = archivo.readLine();
			while(matricula != null) {
				random = (int)(Math.random()*9-5);
				entrarCotxes(matricula,random);
				matricula = archivo.readLine();
			}

			archivo.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("=================================");
		}
	}

	//Entrar cotxes
	public static void entrarCotxes(String matricula,int choice) {
		Scanner sc = new Scanner(System.in);
		int parking_spot;

		if(matricula.equals("")) {
			System.out.println("Introduiex la teva matricula...");
			matricula = sc.nextLine();
		}
			try {
				if(choice <= 2) {
					parking_spot = parking.entraCotxe(matricula);
					System.out.println("El cotxe amb matricula: "+matricula+" s' ha aparcat a la plaça "+(PLACES_NORMALS-parking_spot));
				}else if(choice == 3){
					parking_spot = parking.entraCotxeDiscapacitat(matricula);
					System.out.println("El cotxe amb matricula: "+matricula+" s' ha aparcat a la plaça "+ (PLACES_TOTALS-parking_spot));
				}

			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("=================================");
			}
	}

	//Sortir Cotxes
	public static void sortirCotxes(int choice) {
		Scanner sc = new Scanner(System.in);
		String matricula = "";

		System.out.println("Introduiex la matricula...");
		matricula = sc.nextLine();

			try {
				if(choice == 4) {
					parking.surtCotxe(matricula);
				}else {
					parking.surtCotxeDiscapacitats(matricula);
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("=================================");
			}
	}
	
	//Guardar matricules
	public static void guardarMatricules() {
		String path = "";
		File archivo;
		Scanner sc = new Scanner(System.in);
		
		do{
			System.out.println("Per favor escriu la ruta del fitxer:");
			path = sc.nextLine();
			archivo = new File(path);
		}while(!archivo.exists());
		
		try {
			parking.guardarMatricules(path);			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("=================================");
		}
	}
	
	//Sortir
	public static void exit() {
		System.out.println("Adeu!");
		System.exit(0);
	}
	
	
	//Variables i constatns estatiques
	static PRU04E05Parking_Bruno_Porcel parking;
	static final int PLACES_DISCAPACITATS = 5;
	static final int PLACES_NORMALS = 95;
	static final int PLACES_TOTALS = 100;
	static TipusPlacesParking tipus;
	
	//MAIN-------------------------------------------------------------------------
	public static void main(String[] args) {
		parking = new PRU04E05Parking_Bruno_Porcel(PLACES_NORMALS,PLACES_DISCAPACITATS);

		if(args.length > 0) {	
			final String path = args[0];
			
			matriculesFile(path);
			while(true) {
				printMenu();
			}
		}else {
			while(true) {
				printMenu();
			}
		}
	}
}