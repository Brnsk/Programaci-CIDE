/*asd
	 * ad
	 * ad
	 * asd
	 * asd
	 * */

//package pru04.E01;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class PRU04E01_2_Bruno_Porcel {
	public static void main(String[] args) {
		String linea = "";
		final String SALTOLINEA = System.lineSeparator();
		
		if(args.length > 0) {
			try {
				
				//Arxius
				FileReader fr_programa = new FileReader(args[0]+"\\PRU04E01_2_Bruno_Porcel.java");
				BufferedReader br_programa = new BufferedReader(fr_programa);
				FileWriter fw_programa = new FileWriter(args[0]+"\\PRU04E01_2_Bruno_Porcel2.java");
				BufferedWriter bw_programa = new BufferedWriter(fw_programa);
				
				while(linea != null) {
					linea = br_programa.readLine();
					if(linea != null) {
						for(int i=0; i<linea.length();i++) {
							//Comprobar els comentaris en bloc
							if(linea.charAt(i) == '/' && linea.charAt(i+1) == '*') {
								while(linea.charAt(i) != '*' && linea.charAt(i+1) != '/') {
									linea = "";
									bw_programa.write(linea+SALTOLINEA);
									linea = br_programa.readLine();
									while (linea.equals("")) {
										linea = br_programa.readLine();
									}
								}
								//Comprobar els comentaris de una linea
							}else if(linea.charAt(i) == '/' && linea.charAt(i+1) == '/') {
								linea = "";
							}
						}
						bw_programa.write(linea+SALTOLINEA);
					}
				}
				
				//Tancar arxius
				br_programa.close();
				bw_programa.close();
				System.out.println("Exito!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Nom has passat cap parametre, necessit la uri del programa");
		}
	}
}