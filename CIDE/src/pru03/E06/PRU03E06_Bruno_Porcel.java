package pru03.E06;

import java.util.Scanner;

public class PRU03E06_Bruno_Porcel {
	private int numero_entrades;
	
	//Constructor
	public PRU03E06_Bruno_Porcel(int numero_entrades) {
		this.numero_entrades = numero_entrades;
	}
	
	//Getter
	public int getEntrades() {
		return this.numero_entrades;
	}
	
	//Metodes propi de la classe
	public void vendre(int quantitat) {
		if(this.numero_entrades <= 0) {
			System.out.println("Les entrades estan esgotades.");
		}else if(this.numero_entrades < quantitat) {
			System.out.println("Només queden "+this.numero_entrades+" entrades.");
		}else if(this.numero_entrades >= quantitat) {
			System.out.println("Aquí tens, les teves "+quantitat+" entrades.");
			this.numero_entrades -= quantitat;
		}
	}
	
	
	
	//Printar el menu
	public static int printarMenu(){
		System.out.println("");
		System.out.println("1.Mostrar numero d' entrades lliures");
		System.out.println("2.Vendre entrades");
		System.out.println("3.Sortir");
		
		return sc.nextInt();
	}
	
	//Printar entrades disponibles
		public static void entradesDisponibles(PRU03E06_Bruno_Porcel platea, PRU03E06_Bruno_Porcel amfiteatre, PRU03E06_Bruno_Porcel zona_vip) {
			System.out.println("Entrades disponibles a la zona 'Platea': "+platea.getEntrades());
			System.out.println("Entrades disponibles a la zona 'Amfiteatre': "+amfiteatre.getEntrades());
			System.out.println("Entrades disponibles a la zona 'VIP': "+zona_vip.getEntrades());
		}
	
	//Elegir zona
	public static String elegirZona(String zona) {
		sc.nextLine();
		do{
			System.out.println("Elegir entre: Platea, amfiteatre o vip.");
			System.out.println("Per a quina zona vols l' entrada?");
			zona = sc.nextLine().toUpperCase();
		}while(!zona.equals("PLATEA") && !zona.equals("AMFITEATRE") && !zona.equals("VIP"));
		return zona;
	}
	
	//Quantitat d'entrades
	public static int quantitatEntrades() {
		System.out.println("Quantes entrades vols?");
		return sc.nextInt();
	}
	
	//MAIN
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		PRU03E06_Bruno_Porcel platea = new PRU03E06_Bruno_Porcel(1000);
		PRU03E06_Bruno_Porcel amfiteatre = new PRU03E06_Bruno_Porcel(200);
		PRU03E06_Bruno_Porcel zona_vip = new PRU03E06_Bruno_Porcel(25);
		int resposta_menu = 0,quantitat = 0;
		String zona = "";
		
		while(resposta_menu != 3) {
			resposta_menu = printarMenu();
			if(resposta_menu == 1) {
				entradesDisponibles(platea,amfiteatre,zona_vip);
			}else if(resposta_menu == 2) {
				zona = elegirZona(zona);
				quantitat = quantitatEntrades();
				switch (zona.toUpperCase()) {
					case "PLATEA": platea.vendre(quantitat);
						break;
					case "AMFITEATRE": amfiteatre.vendre(quantitat);
						break;
					case "VIP": zona_vip.vendre(quantitat);
				}
			}else if(resposta_menu == 3) {
				System.out.println("Adeu!");
			}
		}
		
	}
}