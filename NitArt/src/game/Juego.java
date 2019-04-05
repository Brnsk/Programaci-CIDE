package game;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import entidades.Enemy;

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
			if(!gameover && start && ventana.panelActual > -1) {
				ventana.paneles[ventana.panelActual].player.disparando = false;
			}	
		}
	};
	
	private void running() {
		int fps = 0;
		System.out.println("fuera bucle");//Print obligatorio
		
		while(!Juego.gameover && start && ventana.panelActual > -1) {
			
			movimiento();
			
			if(fps == 3) {
				sprites();
				fps = 0;
			}else {
				fps++;
			}
			
			disparos();
			
			colisiones();
			
			comprobarVidas();
			
			inmunidad();
			
			cambiarHabitacion();
			
			esperar();
		}
	}
	
	//Movimiento de entidades
	private void movimiento() {
		ventana.paneles[ventana.panelActual].player.mover();
		
		for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
			ventana.paneles[ventana.panelActual].enemigos.get(i).mover();
			ventana.paneles[ventana.panelActual].enemigos.get(i).moverDiagonal();
		}
	}
	
	//Disparos de entidades
	private void disparos() {
		if(ventana.paneles[ventana.panelActual].player.cargador.size() > 0) {
			ventana.paneles[ventana.panelActual].player.disparar();
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
		ventana.paneles[ventana.panelActual].enemy.comprobarColision();
		ventana.paneles[ventana.panelActual].player.comprobarColision();
	}
	
	//Comprobar vida de entidades
	private void comprobarVidas() {
		//Player
		if(ventana.paneles[ventana.panelActual].player.pv == 0) {
			gameover = true;
			System.out.println("ADIOS");
		}
		
		//Enemigos
		for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
			if(ventana.paneles[ventana.panelActual].enemigos.get(i).pv == 0) {
				
				ventana.paneles[ventana.panelActual].enemigos.get(i).setIcon(null);
				ventana.paneles[ventana.panelActual].enemigos.remove(i);
			}
		}
	}
	
	//Inmunidad del personaje si le han dañado
	private void inmunidad() {
		cont++;
		
		if(!ventana.paneles[ventana.panelActual].player.inmunidad && (ventana.paneles[ventana.panelActual].player.pv < ventana.paneles[ventana.panelActual].player.vidaRestante)) {
			
			ventana.paneles[ventana.panelActual].player.vidaRestante--;
			ventana.paneles[ventana.panelActual].player.inmunidad = true;
		}else if(cont > 50){
			
			cont = 0;
			ventana.paneles[ventana.panelActual].player.inmunidad = false;
		}
	}
	
	//Cambiar de habitacion
	private void cambiarHabitacion() {
		if(ventana.paneles[ventana.panelActual].enemigos.size() == 0) {
			
			ventana.addPanel();
		}
	}
	
	//Animaciones
	private void sprites() {
		//ENEMIGOS
		for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
			Enemy enemigo = ventana.paneles[ventana.panelActual].enemigos.get(i);
			
			//if(enemigo.down && !enemigo.left && !enemigo.right && !enemigo.up) {
				if(enemigo.getIcon() == enemigo.imagenes[0] || enemigo.getIcon() == enemigo.imagenes[2]) {
					enemigo.setIcon(new ImageIcon(enemigo.imagenes[1].getScaledInstance(enemigo.WIDTH, enemigo.HEIGHT, Image.SCALE_SMOOTH)));
					System.out.println("pene");
				}else {
					enemigo.setIcon(new ImageIcon(enemigo.imagenes[2].getScaledInstance(enemigo.WIDTH, enemigo.HEIGHT, Image.SCALE_SMOOTH)));
					System.out.println("jamon");
				}
			//}
		}
	}
	
	//MAIN======================
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
		while(true) {
			juego.running();
		}
	}
}