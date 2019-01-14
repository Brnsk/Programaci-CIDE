package pru03.E07;

import java.util.ArrayList;
import java.util.Scanner;

public class PRU03E07_Bruno_Porcel {
	private int codi;
	private String descripcio;
	private double preu_compra;
	private double preu_venta;
	private int stock;
	
	static int num_productes;
	static int auto_increment=0;
	static Scanner sc = new Scanner(System.in);
	static ArrayList <PRU03E07_Bruno_Porcel> productes = new ArrayList <PRU03E07_Bruno_Porcel>();
	
	//Constructor
	public PRU03E07_Bruno_Porcel() {
		num_productes++;
		this.codi = auto_increment+1;
		auto_increment++;
	}
	
	//Getters i setters
	public int getCodi() {
		return codi;
	}
	public void setCodi(int codi) {
		this.codi = codi;
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public double getPreu_compra() {
		return preu_compra;
	}
	public void setPreu_compra(double preu_compra) {
		this.preu_compra = preu_compra;
	}
	public double getPreu_venta() {
		return preu_venta;
	}
	public void setPreu_venta(double preu_venta) {
		this.preu_venta = preu_venta;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	//toString
	@Override
	public String toString() {
		return "Codi producte: "+this.codi+", descripcio: "+this.descripcio+", preu compra: "+this.preu_compra+", preu venta: "+this.preu_venta+", stock: "+this.stock+".";
	}
	
	
	
	
	//Printar menu
	public static int printarMenu() {
		System.out.println("1.Llistat");
		System.out.println("2.Alta");
		System.out.println("3.Baixa");
		System.out.println("4.Modificació");
		System.out.println("5.Entrada de mercaderia");
		System.out.println("6.Sortida de mercaderia");
		System.out.println("7.Sortir");
		
		return sc.nextInt();
	}
	//Mostrar llistat de productes
	public static void mostraLlistat() {
		for(int i=0; i < productes.size(); i++) {
			System.out.println(productes.get(i));
		}
	}
	
	//Ata de producte
	public static void altaProducte() {
		sc.nextLine();
		PRU03E07_Bruno_Porcel producte = new PRU03E07_Bruno_Porcel();
		
		productes.add(producte);
		System.out.println("Descripcio del producte");
		producte.setDescripcio(sc.nextLine());
		System.out.println("Preu de compra");
		producte.setPreu_compra(sc.nextDouble());
		System.out.println("Preu de venta");
		producte.setPreu_venta(sc.nextDouble());
	}
	
	//baixa de producte
	public static void baixaProducte() {
		int codi=0;
		System.out.println("Escriu el 'codi' del producte que vols donar de baixa");
		codi = sc.nextInt();
		
		for(int i=0;i<productes.size();i++) {
			if(productes.get(i).getCodi() == codi) {
				productes.remove(i);
				num_productes--;
			}
		}
	}
	
	//Modificar producte
	public static void modificarProducte() {
		int codi=0;
		System.out.println("Escriu el 'codi' del producte a modificar.");
		codi = sc.nextInt();
		
		sc.nextLine();
		for(int i=0;i<productes.size();i++) {
			if(productes.get(i).getCodi() == codi) {
				System.out.println("Descripcio del producte");
				
				productes.get(i).setDescripcio(sc.nextLine());
				System.out.println("Preu de compra");
				productes.get(i).setPreu_compra(sc.nextDouble());
				System.out.println("Preu de venta");
				productes.get(i).setPreu_venta(sc.nextDouble());
			}
		}
	}
	
	//Comprar productes
	public static void comprarProductes() {
		int codi=0;
		int quantitat=0;
		
		System.out.println("Quin producte vols comprar?(Introdueix el codi corresponent.)");
		mostraLlistat();
		codi = sc.nextInt();
		System.out.println("Quantes unitats vols comprar?");
		quantitat = sc.nextInt();
		for(int i=0;i<productes.size();i++) {
			if(productes.get(i).getCodi() == codi) {
				productes.get(i).stock=quantitat;
			}
		}
	}
	
	//Vendre productes
	public static void vendreProductes() throws Exception{
		int codi=0;
		int quantitat=0;
		
		System.out.println("Quin producte vols vendre?(Introdueix el codi corresponent.)");
		mostraLlistat();
		codi = sc.nextInt();
		System.out.println("Quantes unitats vols vendre?");
		quantitat = sc.nextInt();
		
		for(int i=0;i<productes.size();i++) {
			if(productes.get(i).getCodi() == codi) {
				if(productes.get(i).stock>quantitat) {
					productes.get(i).stock -= quantitat;
				}else {
					throw new Exception ("Error: No dispones de tantos productos");
				}
			}
		}
	}
	
	//MAIN
	public static void main(String[] args) {
		int resposta_menu=0;
		
		while(resposta_menu != 7) {
			resposta_menu = printarMenu();
			switch(resposta_menu) {
				case 1: mostraLlistat();
					break;
				case 2: altaProducte();
					break;
				case 3: baixaProducte();
					break;
				case 4: modificarProducte();
					break;
				case 5: comprarProductes();
					break;
				case 6: try {
							vendreProductes();
						}catch(Exception e) {
							System.out.println(e.getMessage());
						}
			}
		}
	}
}