package exercicisSolts;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class EscriuFitxerNombres {
	public static void main(String[] args) {
		try {
			BufferedReader brSenars = new BufferedReader(new FileReader("D:/03.CIDE/Programacion/Ficheros/senars.dat"));
			BufferedReader brParells = new BufferedReader(new FileReader("D:/03.CIDE/Programacion/Ficheros/parells.dat"));
			BufferedWriter bwNumeros = new BufferedWriter(new FileWriter("D:/03.CIDE/Programacion/Ficheros/numeros.dat"));
			
			for(int i=0;i<=500;i++) {
				if(i%2 != 0) {
					bwNumeros.write(brSenars.readLine()+"\n");
				}else {
					bwNumeros.write(brParells.readLine()+"\n");
				}
			}
			bwNumeros.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}