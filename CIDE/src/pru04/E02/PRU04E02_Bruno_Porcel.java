//package pru04.E02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PRU04E02_Bruno_Porcel {
	
	//Obrir READER
	public static BufferedReader obrirFitxer(String nom_fitxer) {
		try {
			BufferedReader br_fitxer = new BufferedReader(new FileReader(nom_fitxer));
			
			return br_fitxer;
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	//Obrir WIRTER
		public static BufferedWriter obrirWriter(String nom_fitxer) {
			try {
				BufferedWriter bw_fitxer = new BufferedWriter(new FileWriter(nom_fitxer));
				
				return bw_fitxer;
			}catch(IOException e) {
				System.out.println(e.getMessage());
			}
			return null;
		}
	
	//Trocetjar cadena
	public static String trocejarCamp(String cadena, int pos_in, int pos_fin) {
		String linea = "";
		int i = pos_in-1;
		
		while(i < pos_fin) {
			if(cadena.charAt(i) == 32) {
				break;
			}
			linea = linea+cadena.charAt(i);
			i++;
		}
		
		return linea;
	}
	
	//Recorrer arxiu
	public static void recorrerArxiu(BufferedReader entrada,BufferedWriter fitxer_desti) {
		String linea;
		String linea_escriure = "";
		int contador = 1;//6
		
		try {
			linea = entrada.readLine();
			while(linea != null) {
			
				switch(contador) {
					case 1: linea_escriure = trocejarCamp(linea,1,6);
							escriureFitxer(linea_escriure,fitxer_desti,contador);
							contador = 2;
					
					case 2: linea_escriure = trocejarCamp(linea,7,16);
							escriureFitxer(linea_escriure,fitxer_desti,contador);
							contador = 3;
					
					case 3: linea_escriure = trocejarCamp(linea,17,27);
							escriureFitxer(linea_escriure,fitxer_desti,contador);
							contador = 4;
					
					case 4: linea_escriure = trocejarCamp(linea,28,41);
							escriureFitxer(linea_escriure,fitxer_desti,contador);
							contador = 5;
					
					case 5: linea_escriure = trocejarCamp(linea,42,48);
							escriureFitxer(linea_escriure,fitxer_desti,contador);
							contador = 6;
					
					case 6: linea_escriure = trocejarCamp(linea,49,58);
							escriureFitxer(linea_escriure,fitxer_desti,contador);
							contador = 1;
				}
				linea = entrada.readLine();
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Escriure al fitxer de sortida
	public static void escriureFitxer(String linea,BufferedWriter fitxer_desti, int contador) {
		try {
			if(contador != 6) {
				fitxer_desti.write(linea+",");
			}else {
				fitxer_desti.write(linea+SALTOLINEA);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Tancar fitxer
	public static void tancarFitxers(BufferedReader entrada, BufferedWriter sortida) {
		try {
			entrada.close();
			sortida.close();
			
			System.out.println("Fitxers tancats amb exit");
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static final String SALTOLINEA = System.lineSeparator();
	public static void main(String[] args) {
		String nom_fitxer = "",nom_desti = "";
		BufferedReader fitxer_lletgir;
		BufferedWriter fitxer_desti;
		
		if(args.length != 0) {
			nom_fitxer = args[0];
			nom_desti = args[1];
		}else {
			System.out.println("No has passat el path com a parametre.");
		}
		
		fitxer_desti = obrirWriter(nom_desti);
		fitxer_lletgir = obrirFitxer(nom_fitxer);
		recorrerArxiu(fitxer_lletgir,fitxer_desti);
		tancarFitxers(fitxer_lletgir,fitxer_desti);
	}
}