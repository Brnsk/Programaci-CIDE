package exercicisSolts;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

public class TxtToCsv {
	
	public static FileReader obrirFitxer() {
		
		try {
			FileReader fr = new FileReader("D:/03.CIDE/Programacion/Ficheros/txtToCsv.txt");
			
			return fr;
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
		
	}
	
	public static void tancarFitxer(BufferedReader br) {
		try {
			br.close();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(obrirFitxer());
			
			
			tancarFitxer(br);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
