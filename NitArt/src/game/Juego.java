package game;

import javax.swing.ImageIcon;

import entidades.Boss;
import entidades.Enemy;
import entidades.Player;

public class Juego {
	public static Ventana ventana;
	
	protected static boolean start = false;
	public static boolean gameover = false;
	
	//Controla la inmunidad del personaje
	private static int cont = 0;

	static boolean pause;
	
	//Controla la ejecucion del metodo para los sprites
	private int fps = 0;
	
	//Controlan los disparos para que no se puedan spamear
	private int tempDisparos = 0;
	private int tempDisparosEnemies = 0;
	
	//Controla la ejecucion del metodo que hace que el boss spawnee enemigos
	private int bossEnemies = 0;
	
	protected static boolean win = false;
	
	//CONSTRUCTOR
	protected Juego() {
		Juego.ventana = new Ventana();
	}
	
	private void running() {
		
		System.out.println();
		
		while(!Juego.gameover && start && ventana.panelActual > -1 && ventana.panelActual <= 11) {
			try {
				
				if(fps == 4) {
					sprites();
					fps = 0;
				}else {
					fps++;
				}
				
				repintar();
				
				movimiento();
				
				colisiones();
				
				comprobarDisparo();
				
				disparos();
				
				inmunidad();
				System.out.println("CHETAR AL BOSSSS");
				cambiarHabitacion();
				
				comprobarVidas();
				
				bossSkills();
				
				pause();

				esperar();
			}catch(Exception e) {
				System.out.println("Error en el bucle principal");
				e.printStackTrace();
			}
		}
	}
	
	//Repintar si es necesario
	private void repintar() {
		if(ventana.panelActual == 10 && !Juego.win || (ventana.paneles[ventana.panelActual].enemigos.size() <= 0 && !Juego.win)) {//Para pintar la "puerta"
			ventana.paneles[ventana.panelActual].repaint();
			
		}else if(Juego.win || Juego.gameover) {//En caso de ganar o perder
			for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
				ventana.paneles[ventana.panelActual].enemigos.get(i).setVisible(false);
			}
			
			for(int i = 0; i < Panel.vidas; i++) {
				ventana.paneles[ventana.panelActual].corazones[i].setVisible(false);;
			}
			ventana.paneles[ventana.panelActual].player.setVisible(false);
			ventana.paneles[ventana.panelActual].repaint();
		}
	}
	
	//Movimiento de entidades
	private void movimiento() {
		//player
		ventana.paneles[ventana.panelActual].player.mover();
		
		//Enemigos
		for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
				ventana.paneles[ventana.panelActual].enemigos.get(i).mover();
				ventana.paneles[ventana.panelActual].enemigos.get(i).moverDiagonal();
		}
		
		//Boss
		if(ventana.paneles[ventana.panelActual].boss != null) {
			ventana.paneles[ventana.panelActual].boss.mover();
		}
	}
	
	//Comprobar si se puede disparar
	private void comprobarDisparo() {
		//Limitar los disparos del player
		if(ventana.paneles[ventana.panelActual].player.disparando) {
			if(tempDisparos < 25) {
				tempDisparos++;
				
			}else {
				ventana.paneles[ventana.panelActual].player.disparando = false;
				
				tempDisparos = 0;
			}
			
		}
		
		//Limitar los disparos de los enemigos
		for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
			if(ventana.paneles[ventana.panelActual].enemigos.get(i).disparando) {

				if(tempDisparosEnemies < 150) {
					tempDisparosEnemies++;
					
				}else {
					ventana.paneles[ventana.panelActual].enemigos.get(i).disparando = false;
					
					tempDisparosEnemies = 0;
				}	
			}
		}
	}
	
	//Disparos de entidades
	private void disparos() {	
		//Player
		if(ventana.paneles[ventana.panelActual].player.cargador.size() > 0) {
			ventana.paneles[ventana.panelActual].player.disparar();
		}
		
		//Enemigos
		for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size();i++) {
			ventana.paneles[ventana.panelActual].enemigos.get(i).disparar();
		}
	}
	
	//Dormir el thread durante 30 ms
	public static void esperar() {
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Comprobar Colisiones
	private void colisiones() {
		//Enemigos
		for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
			ventana.paneles[ventana.panelActual].enemigos.get(i).comprobarColision();
		}
		
		//Jugador
		ventana.paneles[ventana.panelActual].player.comprobarColision();
		
		//Boss
		if(ventana.paneles[ventana.panelActual].boss != null) {
			ventana.paneles[ventana.panelActual].boss.colision();
		}
	}
	
	//Comprobar vida de entidades
	private void comprobarVidas() {
		//Player
		if(Player.pv == 0) {
			gameover = true;
			ventana.addPanel();
		}
		
		//Enemigos
		for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
			if(ventana.paneles[ventana.panelActual].enemigos.get(i).pv == 0) {
				
				//Eliminar las balas de los enemigos cuando mueren
				if(ventana.paneles[ventana.panelActual].enemigos.get(i).cargador.size() > 0) {
					for(int j = 0; j < ventana.paneles[ventana.panelActual].enemigos.get(i).cargador.size(); j++) {
						ventana.paneles[ventana.panelActual].enemigos.get(i).cargador.get(j).setIcon(null);
						ventana.paneles[ventana.panelActual].enemigos.get(i).cargador.remove(j);
					}
				}
				
				//Eliminar al enemigo en cuestion
				ventana.paneles[ventana.panelActual].enemigos.get(i).setIcon(null);
				ventana.paneles[ventana.panelActual].enemigos.remove(i);
			}
		}
		
		//Boss
		for(int j = 0; j < ventana.paneles[ventana.panelActual].bossList.size(); j++) {
				if(ventana.paneles[ventana.panelActual].boss.pv == 0 && ventana.paneles[ventana.panelActual].enemigos.size() <= 0) {
					
					ventana.paneles[ventana.panelActual].bossList.get(0).setIcon(null);
					ventana.paneles[ventana.panelActual].bossList.remove(0);
					win();
				}
		}
	}
	
	//Has Ganado!
	private void win() {
		win = true;
		ventana.addPanel();
	}
	
	//Inmunidad del personaje si le han dañado
	private void inmunidad() {
		
		if(ventana.paneles[ventana.panelActual].player.inmunidad) {
			cont++;
		}
		
		//Quitar vidas (icono del CIDE)
		if(!ventana.paneles[ventana.panelActual].player.inmunidad && 
				(Player.pv < Player.vidaRestante)) {
			
			
			Panel.vidas--;
			Player.vidaRestante--;
			System.out.println(Panel.vidas);
			ventana.paneles[ventana.panelActual].removeVidas();
			ventana.paneles[ventana.panelActual].player.inmunidad = true;
			
		}else if(cont > 75){ //Quitar de nuevo la inmunidad
			
			cont = 0;
			ventana.paneles[ventana.panelActual].player.inmunidad = false;
		}
	}
	
	//Cambiar de habitacion
	private void cambiarHabitacion() {
		if(ventana.paneles[ventana.panelActual].enemigos.size() == 0 && (ventana.paneles[ventana.panelActual].player.getX() >= 150 &&
				ventana.paneles[ventana.panelActual].player.getX() <= 200) &&
				(ventana.paneles[ventana.panelActual].player.getY() >= 350 &&
				ventana.paneles[ventana.panelActual].player.getY() <= 450) && ventana.paneles[ventana.panelActual].boss == null) {
			
			Corazones.x = 0;
			ventana.addPanel();
		}
	}
	
	//Animaciones
	private void sprites() {
		//ENEMIGOS=========
		if(ventana.panelActual >= 0) {
			//SadEnemy
			sadSprites();
			
			//TriEnemy
			triSprites();	
		}
		
		//Boss
		if(ventana.panelActual == 10 && ventana.paneles[ventana.panelActual].bossList.size() > 0) {
			bossSprites();
		}
		
		//PLAYER=========
		playerSprites();
	}
	
	//Sprites para SadEnemy
	private void sadSprites() {
		if(ventana.paneles[ventana.panelActual].enemigos.size() > 0) {
			for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
				Enemy enemigo = ventana.paneles[ventana.panelActual].enemigos.get(i);
				
				if(enemigo.name < 10) {
					if(enemigo.down) {
						if(enemigo.currImg == 0 || enemigo.currImg == 2 || enemigo.currImg == 3 || enemigo.currImg == 4 || enemigo.currImg == 5
								|| enemigo.currImg == 6 || enemigo.currImg == 7) {
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[1]));
							enemigo.currImg = 1;
						}else if(enemigo.currImg == 1){
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[2]));
							enemigo.currImg = 2;
						}
					}else if(enemigo.up){
						if(enemigo.currImg == 4 || enemigo.currImg == 2 || enemigo.currImg == 1 || enemigo.currImg == 0 || enemigo.currImg == 8
								|| enemigo.currImg == 9 || enemigo.currImg == 10) {
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[3]));
							enemigo.currImg = 3;
						}else if(enemigo.currImg == 3) {
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[4]));
							enemigo.currImg = 4;
						}
					}else if(enemigo.left) {
						
						if(enemigo.currImg == 1 || enemigo.currImg == 2 || enemigo.currImg == 0 || enemigo.currImg == 10) {
							
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[8]));
							enemigo.currImg = 8;
							
						}else if(enemigo.currImg == 5) {
							
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[9]));
							enemigo.currImg = 9;
							
						}else if(enemigo.currImg == 6) {
							
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[10]));
							enemigo.currImg = 10;
						}
					}else if(enemigo.right) {
						
						if(enemigo.currImg == 3 || enemigo.currImg == 4 || enemigo.currImg == 0 || enemigo.currImg == 7) {
							
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[5]));
							enemigo.currImg = 5;
							
						}else if(enemigo.currImg == 5) {
							
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[6]));
							enemigo.currImg = 6;
							
						}else if(enemigo.currImg == 6) {
							
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[7]));
							enemigo.currImg = 7;
						}
					}
				}
			}
		}
	}
	
	//Sprites para TriEnemy
	private void triSprites() {
		
		if(ventana.paneles[ventana.panelActual].enemigos.size() > 0) {
			for(int i = 0; i < ventana.paneles[ventana.panelActual].enemigos.size(); i++) {
				Enemy enemigo = ventana.paneles[ventana.panelActual].enemigos.get(i);
				
				if(enemigo.name >=20 && enemigo.name <= 30 && enemigo.triFps == 1) {
					if(enemigo.down) {//Si el enemigo esta yendo hacia abajo
						if(enemigo.currImg == 0) {//TOP
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[2]));
							enemigo.currImg = 2;
						}else if(enemigo.currImg == 2) {//MIDDLE
								enemigo.setIcon(new ImageIcon(enemigo.imagenes[1]));
								enemigo.currImg = 1;
						}else if(enemigo.currImg == 1) {//BOTTOM
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[2]));
							enemigo.currImg = 2;
						}
					}else if(enemigo.up) {//Si el enemigo se esta moviendo hacia arriba
						if(enemigo.currImg == 0) {//TOP
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[2]));
							enemigo.currImg = 2;
						}else if(enemigo.currImg == 2) {//MIDDLE
								enemigo.setIcon(new ImageIcon(enemigo.imagenes[0]));
								enemigo.currImg = 0;
						}else if(enemigo.currImg == 1) {//BOTTOM
							enemigo.setIcon(new ImageIcon(enemigo.imagenes[2]));
							enemigo.currImg = 2;
						}
					}
					enemigo.triFps = 0;
				}else {
					enemigo.triFps++;
				}
			}
		}
	}
	
	//Sprites para el player
	private void playerSprites() {
		Player player = ventana.paneles[ventana.panelActual].player;
		
		if(!player.down && !player.up && !player.left && !player.right) {//Si el personaje NO se mueve
			
			player.setIcon(new ImageIcon(player.imagenes[0].getImage()));
		}else if(player.up) {//Si el personaje va hacia arriba
			
			if(player.currImage >= 0 && player.currImage <= 13 && player.currImage != 4) {//Paso 1
				player.setIcon(new ImageIcon(player.imagenes[4].getImage()));
				player.currImage = 4;
			}else if(player.currImage == 4) {//Paso 2
				player.setIcon(new ImageIcon(player.imagenes[3].getImage()));
				player.currImage = 3;
			}
		}else if(player.down){//Si el personaje va hacia abajo
			
			if(player.currImage >= 0 && player.currImage <= 13 && player.currImage != 2) {//Paso 1
				player.setIcon(new ImageIcon(player.imagenes[2].getImage()));
				player.currImage = 2;
			}else if(player.currImage == 2) {//Paso 2
				player.setIcon(new ImageIcon(player.imagenes[1].getImage()));
				player.currImage = 1;
			}
		}else if(!player.up && !player.down && player.left && !player.right){//Si el personaje va hacia la izquierda
			
			if(player.currImage >= 0 && player.currImage <= 13 && player.currImage != 6 && player.currImage != 7) {//Paso 1
				player.setIcon(new ImageIcon(player.imagenes[6].getImage()));
				player.currImage = 6;
			}else if(player.currImage == 6) {//Paso 2
				player.setIcon(new ImageIcon(player.imagenes[7].getImage()));
				player.currImage = 7;
			}else if(player.currImage == 7) {//Paso 3
				player.setIcon(new ImageIcon(player.imagenes[8].getImage()));
				player.currImage = 8;
			}
		}else if(!player.up && !player.down && !player.left && player.right){//Si el personaje va hacia la derecha
			
			if(player.currImage >= 0 && player.currImage <= 13 && player.currImage != 10 && player.currImage != 11) {//Paso 1
				player.setIcon(new ImageIcon(player.imagenes[10].getImage()));
				player.currImage = 10;
			}else if(player.currImage == 10) {//Paso 2
				player.setIcon(new ImageIcon(player.imagenes[11].getImage()));
				player.currImage = 11;
			}else if(player.currImage == 11) {//Paso 3
				player.setIcon(new ImageIcon(player.imagenes[12].getImage()));
				player.currImage = 12;
			}
		}
	}
	
	//Sprites para el boss
	private void bossSprites() {
		Boss boss = ventana.paneles[ventana.panelActual].boss;
		
		if(Boss.cambio >= 0 && Boss.cambio < 4) {
			Boss.cambio++;
		}else if(Boss.cambio >= 4){
			Boss.cambio = 0;
		}

		if(Boss.cambio == 4) {
			if(boss.currImg == 0) {
				
				boss.setIcon(new ImageIcon(boss.imagenes[1]));
				boss.currImg = 1;
				
			}else if(boss.currImg == 1){
				
				boss.setIcon(new ImageIcon(boss.imagenes[3]));
				boss.currImg = 3;
				
			}else if(boss.currImg == 3){
				
				boss.setIcon(new ImageIcon(boss.imagenes[2]));
				boss.currImg = 2;
				
			}else if(boss.currImg == 2){
				
				Boss.speed = 6;
				
				boss.setIcon(new ImageIcon(boss.imagenes[4]));
				boss.currImg = 4;
				
			}else if(boss.currImg == 4){
				
				boss.setIcon(new ImageIcon(boss.imagenes[5]));
				boss.currImg = 5;
				
			}else if(boss.currImg == 5){
				
				boss.setIcon(new ImageIcon(boss.imagenes[6]));
				boss.currImg = 6;
			}
		}
		
		if(boss.currImg == 6){
			
			Boss.speed = 10;
			
			boss.setIcon(new ImageIcon(boss.imagenes[7]));
			boss.setSize(Boss.WIDTH, Boss.HEIGHT + 585);
			boss.currImg = 7;
			
		}else if(boss.currImg == 7 && Boss.cambio == 4){
			
			Boss.speed = 4;
			
			boss.setSize(Boss.WIDTH, Boss.HEIGHT);
			boss.setIcon(new ImageIcon(boss.imagenes[0]));
			boss.currImg = 0;
			
		}
	}
	
	//Skills del boss
	private void bossSkills() {
		
		if(bossEnemies == 200) {
			bossEnemies = 0;
		}else {
			bossEnemies++;
		}
		
		if(ventana.paneles[ventana.panelActual].bossList.size() > 0 && bossEnemies == 200) {
			ventana.paneles[ventana.panelActual].boss.spawnEnemy();
		}
	}
	
	//Metodo Pausa
	public void pause() {
		while (Juego.pause) {
			System.out.println("");
		}
	}
	
	//MAIN======================
	public static void main(String[] args) {

		Juego juego = new Juego();
		while((!Juego.start || Juego.start) && Juego.ventana.panelActual <= 11) {
			juego.running();
		}
	}
}