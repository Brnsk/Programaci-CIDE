package snake;

public class Juego{
	private Snake snake;
	protected static Ventana ventana;
	protected static boolean gameover = false;
	protected static boolean start = false;
	
	public Juego() {
		iniciarJuego();
	}
	
	private void iniciarJuego() {
		ventana = new Ventana();
		snake = new Snake();
	}	
}
