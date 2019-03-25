package buscaMinas;

import java.util.Random;

public class BuscaMinas {
	protected static Ventana ventana;
	protected static Panel panel;
	protected static boolean gameOver = false;
	private static final int MINAS = 20;
	protected static int[] array_bombas = new int[MINAS];
	
	public BuscaMinas() {
		ventana = new Ventana();
		crearMinas();
	}
	
	//Generar minas
	protected void crearMinas() {
		Random rd = new Random();
		int random = 0;
		boolean existe = false;
		
		for(int x = 0; x < MINAS; x++) {
			random = rd.nextInt(100)+1;
			
			for(int i = 0; i < array_bombas.length; i++) {
				if(random == array_bombas[i]) {
					existe = true;
					x--;
					break;
				}else {
					existe = false;
				}
			}
			
			if(!existe) {
				array_bombas[x] = random;
				System.out.println(random);
			}
		}
	}
}