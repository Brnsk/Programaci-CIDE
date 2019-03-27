package game;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("static-access")
public class Juego {
	protected static Ventana ventana;
	
	protected static boolean start = false;
	public static boolean gameover = false;
	
	protected static Timer timer;
	
	//CONSTRUCTOR
	protected Juego() {
		this.ventana = new Ventana();
		
		//Instanciar Timer y añadir una tarea cada 500 ms
		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(this.task, 0, 500);
	}
	
	//Ejecutar la tarea cada 500 ms
	protected static TimerTask task =  new TimerTask() {
		@Override
		public void run() {
			ventana.panel.player.disparando = false;
		}
		
	};
	
	private void running() {
		while(!Juego.gameover) {
			movimiento();
			
			disparos();
			
			comprobarVidas();
			
			esperar();
		}
	}
	
	
	//Movimiento de entidades
	private void movimiento() {
		ventana.panel.player.mover();
		ventana.panel.enemy.mover();
		ventana.panel.enemy.moverDiagonal();
	}
	
	//Disparos de entidades
	private void disparos() {
		if(ventana.panel.player.cargador.size() > 0) {
			ventana.panel.player.disparar();
		}
	}
	
	//Metodo para dormir el thread durante 20 ms
	public static void esperar() {
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Comprobar vida de entidades
	private void comprobarVidas() {
		if(ventana.panel.enemy.pv == 0) {
			ventana.panel.enemy.setIcon(null);
		}
	}
	
	//MAIN======================
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.running();
	}
}