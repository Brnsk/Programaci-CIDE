package game;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import entidades.Enemy;
import entidades.Player;

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
			if(!gameover && start && ventana.panelActual > -1 && ventana.paneles[ventana.panelActual].player.disparando) {
				ventana.paneles[ventana.panelActual].player.disparando = false;
			}
		}
	};
	
	private void running() {
		int fps = 0;
		System.out.println("");//Print obligatorio
		
		while(!Juego.gameover && start && ventana.panelActual > -1) {
			try {
				if(fps == 4) {
					sprites();
					fps = 0;
				}else {
					fps++;
				}
				
				movimiento();
				
				disparos();
				
				colisiones();
				
				comprobarVidas();
				
				inmunidad();
				
				cambiarHabitacion();
				
				esperar();
			}catch(Exception e) {
				System.out.println("Error en el bucle principal");
				e.printStackTrace();
			}
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
		for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
			ventana.paneles[ventana.panelActual].enemigos.get(i).comprobarColision();
		}
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
		
		if(!ventana.paneles[ventana.panelActual].player.inmunidad && 
				(ventana.paneles[ventana.panelActual].player.pv < ventana.paneles[ventana.panelActual].player.vidaRestante)) {
			
			ventana.paneles[ventana.panelActual].player.vidaRestante--;
			ventana.paneles[ventana.panelActual].player.inmunidad = true;
		}else if(cont > 50){
			
			cont = 0;
			ventana.paneles[ventana.panelActual].player.inmunidad = false;
		}
	}
	
	//Cambiar de habitacion
	private void cambiarHabitacion() {
		if(ventana.paneles[ventana.panelActual].enemigos.size() == 0 && (ventana.paneles[ventana.panelActual].player.getX() >= 150 &&
				ventana.paneles[ventana.panelActual].player.getX() <= 250) &&
				(ventana.paneles[ventana.panelActual].player.getY() >= 350 && ventana.paneles[ventana.panelActual].player.getY() <= 450)) {
			
			ventana.addPanel();
		}
	}
	
	//Animaciones
	private void sprites() {
		//ENEMIGOS=========
		
		//SadEnemy
		sadSprites();
		
		//TriEnemy
		triSprites();
		
		//PLAYER=========
		playerSprites();
	}
	
	private void sadSprites() {
		for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
			Enemy enemigo = ventana.paneles[ventana.panelActual].enemigos.get(i);
			
			if(enemigo.down && enemigo.name < 10) {
				if(enemigo.currImg == 0 || enemigo.currImg == 2 || enemigo.currImg == 3 || enemigo.currImg == 4) {
					enemigo.setIcon(new ImageIcon(enemigo.imagenes[1]));
					enemigo.currImg = 1;
				}else if(enemigo.currImg == 1){
					enemigo.setIcon(new ImageIcon(enemigo.imagenes[2]));
					enemigo.currImg = 2;
				}
			}else if(enemigo.up && enemigo.name < 10){
				if(enemigo.currImg == 4 || enemigo.currImg == 2 || enemigo.currImg == 1 || enemigo.currImg == 0) {
					enemigo.setIcon(new ImageIcon(enemigo.imagenes[3]));
					enemigo.currImg = 3;
				}else if(enemigo.currImg == 3) {
					enemigo.setIcon(new ImageIcon(enemigo.imagenes[4]));
					enemigo.currImg = 4;
				}
			}
		}
	}
	
	private void triSprites() {
		for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
			Enemy enemigo = ventana.paneles[ventana.panelActual].enemigos.get(i);
			
			if(enemigo.down && enemigo.name >= 20 && enemigo.name < 30 && enemigo.triFps == 2) {
				if(enemigo.currImg == 0) {
					enemigo.setIcon(new ImageIcon(enemigo.imagenes[1]));
					enemigo.currImg = 1;
					enemigo.triFps = 0;
				}else if(enemigo.currImg == 1){
					enemigo.setIcon(new ImageIcon(enemigo.imagenes[0]));
					enemigo.currImg = 0;
					enemigo.triFps = 0;
				}
			}else if(enemigo.up && enemigo.name >= 20 && enemigo.name < 30 && enemigo.triFps == 2){
				if(enemigo.currImg == 0) {
					enemigo.setIcon(new ImageIcon(enemigo.imagenes[1]));
					enemigo.currImg = 1;
					enemigo.triFps = 0;
				}else if(enemigo.currImg == 1) {
					enemigo.setIcon(new ImageIcon(enemigo.imagenes[0]));
					enemigo.currImg = 0;
					enemigo.triFps = 0;
				}
			}else{
				enemigo.triFps++;
			}
		}
	}
	
	//Sprites para el player
	private void playerSprites() {
		Player player = ventana.paneles[ventana.panelActual].player;
		
		if(!player.down && !player.up && !player.left && !player.right) {//Si el personaje NO se mueve
			
			player.setIcon(new ImageIcon(player.imagenes[0]));
		}else if(player.up && !player.down && !player.left && !player.right) {//Si el personaje va hacia arriba
			
			if(player.currImage >= 0 && player.currImage <= 13 && player.currImage != 4) {//Paso 1
				player.setIcon(new ImageIcon(player.imagenes[4]));
				player.currImage = 4;
			}else if(player.currImage == 4) {//Paso 2
				player.setIcon(new ImageIcon(player.imagenes[3]));
				player.currImage = 3;
			}
		}else if(!player.up && player.down && !player.left && !player.right){//Si el personaje va hacia abajo
			
			if(player.currImage >= 0 && player.currImage <= 13 && player.currImage != 2) {//Paso 1
				player.setIcon(new ImageIcon(player.imagenes[2]));
				player.currImage = 2;
			}else if(player.currImage == 2) {//Paso 2
				player.setIcon(new ImageIcon(player.imagenes[1]));
				player.currImage = 1;
			}
		}else if(!player.up && !player.down && player.left && !player.right){//Si el personaje va hacia la izquierda
			
			if(player.currImage >= 0 && player.currImage <= 13 && player.currImage != 6 && player.currImage != 7) {//Paso 1
				player.setIcon(new ImageIcon(player.imagenes[6]));
				player.currImage = 6;
			}else if(player.currImage == 6) {//Paso 2
				player.setIcon(new ImageIcon(player.imagenes[7]));
				player.currImage = 7;
			}else if(player.currImage == 7) {//Paso 3
				player.setIcon(new ImageIcon(player.imagenes[8]));
				player.currImage = 8;
			}
		}else if(!player.up && !player.down && !player.left && player.right){//Si el personaje va hacia la derecha
			
			if(player.currImage >= 0 && player.currImage <= 13 && player.currImage != 10 && player.currImage != 11) {//Paso 1
				player.setIcon(new ImageIcon(player.imagenes[10]));
				player.currImage = 10;
			}else if(player.currImage == 10) {//Paso 2
				player.setIcon(new ImageIcon(player.imagenes[11]));
				player.currImage = 11;
			}else if(player.currImage == 11) {//Paso 3
				player.setIcon(new ImageIcon(player.imagenes[12]));
				player.currImage = 12;
			}
		}
	}
	
	//Metodo Pausa
		//Si no te funcion posa un print dins el bucle
	
	//MAIN======================
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
		while(!juego.start || juego.start) {
			juego.running();
		}
	}
}