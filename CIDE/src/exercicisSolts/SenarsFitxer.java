package exercicisSolts;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class SenarsFitxer {
	public static void main(String[] args) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("D:/03.CIDE/Programacion/Ficheros/senars.dat"));
			for(int i=1; i<500;i = i+2) {
					bf.write(i+"\n");
			}
			bf.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
