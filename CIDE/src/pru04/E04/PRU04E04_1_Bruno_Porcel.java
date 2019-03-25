//package pru04.E04;
import java.io.File;

public class PRU04E04_1_Bruno_Porcel {
	public static long calculateSize(File archivo) {
		long size;
		try {
			size = archivo.length();
			
			return size;
		}catch(SecurityException e) {
			System.out.println("Error: "+e.getMessage());
		}
		return 0;
	}
	
	public static void printSize(long size){
		float printable_size = size;
		System.out.println("Tamaño en B: "+printable_size);
		System.out.println("Tamaño en KB: "+printable_size/1024);
		System.out.println("Tamaño en MB: "+(printable_size/1024)/1024);
	}
	
	public static void main(String[] args) {
		long size;
		File archivo;
		if(args.length > 0) {
			archivo = new File(args[0]);
			size = calculateSize(archivo);
			printSize(size);
		}else {
			System.out.println("No m' has passat cap parametre");
		}
	}
}
