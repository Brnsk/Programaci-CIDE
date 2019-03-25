//package pru04.E04;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PRU04E04_2_Bruno_Porcel {
	public static void main(String[] args) {
		
		if(args.length>0) {
			try {
				File f_archivo = new File(args[0]);
				FileInputStream archivo = new FileInputStream(args[0]);
				byte[] array = new byte[(int)f_archivo.length()];
				
				archivo.read(array);

				archivo.close();
			}catch(IOException e) {
				System.out.println("Error: "+e.getMessage());
			}
		}else {
			System.out.println("No m' has passat cap parametre.");
		}
	}
}
