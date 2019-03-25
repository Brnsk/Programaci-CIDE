package game;

@SuppressWarnings("static-access")
public class Juego {
	protected static Ventana ventana;
	protected static boolean start = false;
	public static boolean gameover = false;
	
	
	protected Juego() {
		this.ventana = new Ventana();
	}
	
	//MAIN======================
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}