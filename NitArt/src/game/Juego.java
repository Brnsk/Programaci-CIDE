package game;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("static-access")
public class Juego {
	public static Ventana ventana;
	
	protected static boolean start = false;
	public static boolean gameover = false;
	
	protected static Timer timer;
	
	private static int cont = 0;
	
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
			
			colisiones();
			
			comprobarVidas();
			
			inmunidad();
			
			//cambiarHabitacion();
			
			esperar();
		}
	}
	
	
	//Movimiento de entidades
	private void movimiento() {
		ventana.panel.player.mover();
		
		for(int i = 0; i < ventana.panel.enemigos.size(); i++) {
			ventana.panel.enemigos.get(i).mover();
			ventana.panel.enemigos.get(i).moverDiagonal();
		}
	}
	
	//Disparos de entidades
	private void disparos() {
		if(ventana.panel.player.cargador.size() > 0) {
			ventana.panel.player.disparar();
		}
	}
	
	//Metodo para dormir el thread durante 30 ms
	public static void esperar() {
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Comprobar Colisiones
	private void colisiones() {
		ventana.panel.enemy.comprobarColision();
		ventana.panel.player.comprobarColision();
	}
	
	//Comprobar vida de entidades
	private void comprobarVidas() {
		//Player
		if(ventana.panel.player.pv == 0) {
			gameover = true;
			System.out.println("ADIOS");
		}
		
		//Enemigos
		for(int i = 0; i < ventana.panel.enemigos.size(); i++) {
			if(ventana.panel.enemigos.get(i).pv == 0) {
				
				ventana.panel.enemigos.get(i).setIcon(null);
				ventana.panel.enemigos.remove(i);
			}
		}
	}
	
	//Inmunidad del personaje si le han dañado
	private void inmunidad() {
		cont++;
		
		if(!ventana.panel.player.inmunidad && (ventana.panel.player.pv < ventana.panel.player.vidaRestante)) {
			
			ventana.panel.player.vidaRestante--;
			ventana.panel.player.inmunidad = true;
		}else if(cont > 50){
			
			cont = 0;
			ventana.panel.player.inmunidad = false;
		}
	}
	
	//Cambiar de habitacion
	private void cambiarHabitacion() {
		if(ventana.panel.enemigos.size() == 0) {
			ventana.remove(ventana.panel);
			//ventana.add(new Panel());
		}
	}
	
	//MAIN======================
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.running();
	}
}