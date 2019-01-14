package exercicisSolts;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ParellsFitxer {
	public static void main(String[] args) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("D:/03.CIDE/Programacion/Ficheros/parells.dat"));
			for(int i=0; i<=500;i = i+2) {
					bf.write(i+"\n");
			}
			bf.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
