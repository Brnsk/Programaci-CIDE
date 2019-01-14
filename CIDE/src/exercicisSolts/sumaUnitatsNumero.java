package exercicisSolts;
import java.util.Scanner;
import java.util.ArrayList;

public class sumaUnitatsNumero {
	
	public static void printarResultats(ArrayList <String> auxString) {
		for(int i = 0; i < auxString.size();i++) {
			System.out.println(auxString.get(i));
		}
	}
	
	public static String separarString(String num) {
		String texto = "";
		int suma=0,i=0;
		while(i < num.length()-1) {
			texto = texto + num.charAt(i) + " + ";
			suma = suma + Integer.parseInt(""+num.charAt(i));
			i++;
		}
		suma = suma + Integer.parseInt(""+num.charAt(i));
		texto = texto +num.charAt(i) + " = "+ suma;
		return texto;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numero=0;
		String num = "";
		ArrayList <String> auxString = new ArrayList <String>();
		
		while(numero > -1) {
			num=sc.nextLine();
			try {
				numero = Integer.parseInt(num);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			if(numero > 0) {
				auxString.add(separarString(num));
			}
		}
		printarResultats(auxString);
	}
}