package exercicisSolts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class InputStream_parells {
	//Printar menu
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		int resposta_menu;
		
		System.out.println("1.Llegir fitxer i printar per consola.");
		System.out.println("2.Llegir fitxer i escriure a un altre 'sortida_stream.txt'.");
		resposta_menu = sc.nextInt();
		switch(resposta_menu) {
		case 1:escriure_consola();
			break;
		case 2: escriure_fitxer();
			break;
		}
	}
	
	//Escriure a fitxer
	public static void escriure_fitxer() {
		int caracter = 0;
		try {
			FileInputStream parells = new FileInputStream("D:\\03.CIDE\\Programacion\\Ficheros\\parells.dat");
			FileOutputStream sortida = new FileOutputStream("D:\\03.CIDE\\Programacion\\Ficheros\\sortida2.dat");
			while(caracter != -1) {
				caracter = parells.read();
				if(caracter == -1) {
					continue;
				}
				sortida.write(caracter);
			}
			System.out.println("Adeu!");
			parells.close();
			sortida.close();
		}catch(FileNotFoundException e) {
			System.out.println("Error: "+e.getMessage());
		}catch(IOException e2) {
			System.out.println("Error: "+e2.getMessage());
		}
	}
	
	//Printar a consola
	public static void escriure_consola() {
		int caracter = 0;
		try {
			FileInputStream parells = new FileInputStream("D:\\03.CIDE\\Programacion\\Ficheros\\parells.dat");
			while(caracter != -1) {
				caracter = parells.read();
				if(caracter == -1) {
					continue;
				}
				if(caracter == 10) {
					System.out.println("");
				}else {
					System.out.print((char)caracter);
				}
			}
			System.out.println("Adeu!");
			parells.close();
		}catch(FileNotFoundException e) {
			System.out.println("Error: "+e.getMessage());
		}catch(IOException e2) {
			System.out.println("Error: "+e2.getMessage());
		}
	}
	public static void main(String[] args) {
		menu();
	}
}