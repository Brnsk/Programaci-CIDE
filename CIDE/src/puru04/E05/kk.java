package puru04.E05;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;

public class kk {
	public static void main(String[] args) {
		try {
			FileWriter fw = new FileWriter("D:\\03.CIDE\\Programacion\\Ficheros\\Matriculas.txt");
			BufferedWriter escribir = new BufferedWriter(fw);
			for(int i = 0; i < 100; i++) {
				String numeros = "";
				for(int y = 0; y < 4; y++) {
					numeros = numeros+(char)(Math.random()*9+48+1);
				}
				String letras = "";
				for(int y = 0; y < 3; y++) {
					letras = letras+(char)(Math.random()*25+65+1);
				}
				escribir.write(numeros+letras+"\n");
			}
			
			escribir.close();
			fw.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
