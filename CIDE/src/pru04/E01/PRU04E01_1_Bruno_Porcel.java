//package pru04.E01;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class PRU04E01_1_Bruno_Porcel {
	public static void main(String[] args) {
		ArrayList <String> llista_paraules = new ArrayList <String>();
		String linea = "";
		int num_lletres = 0, aux = 0, c = 0, x = 1;
		
		//Preparar al programa per lletgir i escriure a arxius
		if(args.length != 0) {
			try {
				//Fitxers a lletgir
				FileReader fr_words = new FileReader(args[0]);
				BufferedReader br_words = new BufferedReader(fr_words);
				
				//Fitxer a escriure
				BufferedWriter bw_sortWords = new BufferedWriter(new FileWriter("D:\\03.CIDE\\Programacion\\Ficheros\\words_sort.txt"));
				
				//Guardar a ArrayList
				while(linea != null) {
					linea = br_words.readLine();
					if(linea != null) {
						llista_paraules.add(linea.toLowerCase());
					}
				}
				
				//Ordenar alfabeticament
				Collections.sort(llista_paraules);
				for(int i=0;i<llista_paraules.size();i++) {
					bw_sortWords.write(llista_paraules.get(i)+"\n");
				}
				
				//Tancar Fitxers
				br_words.close();
				bw_sortWords.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}else {
			System.out.println("No has passat el nom de l' axiu a lletgir com a parametre.");
		}
	}
}