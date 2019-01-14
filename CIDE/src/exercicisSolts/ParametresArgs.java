//package exercicisSolts;  PARA PODER EJECUTAR POR CONSOLA

public class ParametresArgs {
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("No hi ha parametres.");
		}else {
			for(int i =0; i<args.length;i++) {
				System.out.println("Parametre "+(i+1)+" = "+args[i]);
			}
		}
	}
}
