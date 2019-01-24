//package pru04.E01;
import java.io.BufferedReader;
import java.io.FileReader;

public class PRU04E01_3_Bruno_Porcel {
	public static void main(String[] args) {
		String linea = "",paraula;
		int contador = 0;
		paraula =args[1];
		if(args.length > 0) {
			try {
				FileReader fr_paraules = new FileReader(args[0]);
				BufferedReader br_paraules = new BufferedReader(fr_paraules);
				
				while(linea != null) {
					linea = br_paraules.readLine();
					if(linea != null && linea.equals(paraula)) {
						contador++;
					}
				}
				System.out.println("La paraula apareix "+contador+" vegades.");
				
				br_paraules.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("No has passat cap parametre.");
		}
	}
}
