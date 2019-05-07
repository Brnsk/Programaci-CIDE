package entidades;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Juego;
import game.Ventana;

@SuppressWarnings("serial")
public class Player extends JLabel implements JugadorEnemigos{
	
	//Atributos para la imagen del pj
	public int currImage;
	private ImageIcon icon;
	public Image[] imagenes;
	
	//Coordenadas
	private int x;
	private int y;
	
	//Movimiento
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	//Direccion de las balas
	public boolean bulletUp;
	public boolean bulletDown;
	public boolean bulletLeft;
	public boolean bulletRight;
	public boolean disparando;
	
	public Bala bala;
	
	public ArrayList <Bala> cargador;
	
	protected int contadorBalas;
	
	//Velocidad
	public static int speed;
	
	//Tamaño
	public final int WIDTH = 90;
	public final int HEIGHT = 100;
	
	//Vidas
	public int pv;
	public int vidaRestante;
	
	//Inmunidad
	public boolean inmunidad = false;
	
	public Player() {
		iniciar();
		this.setIcon(icon);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLocation(Ventana.WIDTH / 2 - WIDTH / 2, Ventana.HEIGHT / 2 - HEIGHT / 2);
		
		this.x = this.getX();
		this.y = this.getY();
		
	}
	
	private void iniciar() {
		//Variables booleanas de movimiento
		up = false;
		down = false;
		left = false;
		right = false;
		
		bulletUp = false;
		bulletDown = false;
		bulletLeft = false;
		bulletRight = false;
		disparando = false;
		
		cargador = new ArrayList <Bala>();
		
		contadorBalas = 0;
		
		//Velocidad
		speed = 15;
		
		//vidas
		pv = 6;
		vidaRestante = 999;
		
		//Insertar imagen
		imagen();
	}
	
	//Añadir imagen a jlabel
	private void imagen() {
		imagenes = new Image[14];
		imagenes[0] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerFront.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		icon = new ImageIcon(imagenes[0]);
		currImage = 0;
		
		imagenes[1] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerFrontPaso1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje de frente paso 1
		imagenes[2] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerFrontPaso2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje de frente paso 2
		imagenes[3] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerBackPaso1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje de espalda paso 1
		imagenes[4] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerBackPaso2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje de espalda paso 2
		imagenes[5] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerLeft.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje mirando a la izquierda
		imagenes[6] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerLeftPaso1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje mirando a la izquierda paso 1
		imagenes[7] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerLeftPaso2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje mirando a la izquierda paso 2
		imagenes[8] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerLeftPaso3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje mirando a la izquierda paso 3
		imagenes[9] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerRight.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje mirando a la derecha
		imagenes[10] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerRightPaso1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje mirando a la derecha paso 1
		imagenes[11] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerRightPaso2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje mirando a la derecha paso 2
		imagenes[12] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerRightPaso3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje mirando a la derecha paso 3
		imagenes[13] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\player\\playerBack.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);//Personaje de espalda
		
	}
	
	//Eliminar bala
	/*private void eliminarBala() {
		bulletUp = false;
		bulletDown = false;
		bulletRight = false;
		bulletLeft = false;
	}*/
	
	//Comprobar si el personaje se encuentra en el "Tablero"
	private void comprobarPosicion() {
		comprobarX();
		comprobarY();
	}
	
	private void comprobarX() {
		if(x <= 150) {
			left = false;
		}else if( x >= Ventana.WIDTH - 150 - WIDTH) {
			right = false;
		}
	}
	
	private void comprobarY() {
		
		if(Juego.ventana.panelActual == 10) {
			if(y >= Ventana.HEIGHT - 150 - HEIGHT) {
				down = false;
			}else if( y <= Ventana.HEIGHT /2 - (HEIGHT/2) + 20) {
				up = false;
			}
		}else {
			if(y >= Ventana.HEIGHT - 150 - HEIGHT) {
				down = false;
			}else if( y <= 150 - HEIGHT + 20) {
				up = false;
			}
		}
	}
	
	//================ METODOS INTERFAZ COMUN ENTRE JUGADOR Y ENEMIGOS ================
	
	@Override
	public void disparar() {
		
		contadorBalas = 0;
		
		while(contadorBalas < cargador.size()) {
			bala = cargador.get(contadorBalas);
			
			if(bala.direccion.equals("up")) {
				bala.y -= Bala.speed;
				bala.setLocation(bala.x, bala.y);
				
			}else if(bala.direccion.equals("down")) {
				bala.y += Bala.speed;
				bala.setLocation(bala.x, bala.y);
				
			}else if(bala.direccion.equals("left")) {
				bala.x -= Bala.speed;
				bala.setLocation(bala.x, bala.y);
				
			}else if(bala.direccion.equals("right")) {
				bala.x += Bala.speed;
				bala.setLocation(bala.x, bala.y);
			}
			
			if(!bala.comprobarPosicion()) {
				cargador.remove(bala);
			}
			contadorBalas++;
		}
	}
	
	protected void eliminarBala(int i) {
		cargador.get(i).setIcon(null);
		cargador.remove(i);
	}
	
	@Override
	public void mover() {
		comprobarPosicion();
		
		x = this.getX();
		y = this.getY();
		
		if(up && !down && !right && !left) {
		//arriba
			y -= speed;
			this.setLocation(x, y);
			
		}else if(down && !up && !right && !left) {
		//abajo
			y += speed;
			this.setLocation(x, y);
		
		}else if(left && !down && !right && !up) {
		//izquierda
			x -= speed;
			this.setLocation(x, y);
		
		}else if(right && !down && !up && !left) {
		//derecha
			x += speed;
			this.setLocation(x, y);
		
		}else {
			moverDiagonal();
		}
	}

	@Override
	public void moverDiagonal() {
		if(up && left && !down && !right){
			//arriba izquierda
			y -= speed-1;
			x -= speed-1;
			
		}else if(up && right && !down && !left){
			//arriba derecha
			y -= speed-1;
			x += speed-1;
			
		}else if (down && left && !up && !right){
			//abajo izquierda
			y += speed-1;
			x -= speed-1;
			
		}else if(down && right && !up && !left){
			//abajo derecha
			y += speed-1;
			x += speed-1;
		}
		this.setLocation(x, y);
	}

	@SuppressWarnings("unused")
	@Override
	public void comprobarColision() {
		int playerX = this.x;
		int playerY = this.y;
		
		boolean colision = false;
		
		for(int i = 0; i < Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.size(); i++) {
			Enemy enemigo = Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.get(i);
			Rectangle playerBounds = this.getBounds();
			
			playerBounds.height = (int) playerBounds.getHeight() - 30;
			playerBounds.y = (int) playerBounds.getY() + 20;
			playerBounds.width = (int) playerBounds.getWidth() - 60;
			playerBounds.x = (int) playerBounds.getX() + 30;
			
			if(playerBounds.intersects(enemigo.getBounds())) {
				colision = true;
				colisionando(playerX, playerY,enemigo);
				enemigo.colision = true;
			}else {
				colision = false;
				enemigo.colision = false;
			}
		}
		
		//Colision con el Rayo del Boss
		if(Juego.ventana.paneles[Juego.ventana.panelActual].boss != null) {
			colisionBoss();
		}
	}
	
	
	private void colisionando(int playerX, int playerY, Enemy enemigo) {

		//Corregir posicion del player
		if(this.up && !this.down && !this.left && !this.right) {
			this.setLocation(playerX, playerY + Player.speed);
		}else if(!this.up && this.down && !this.left && !this.right) {
			this.setLocation(playerX, playerY - Player.speed);
		}else if(!this.up && !this.down && this.left && !this.right) {
			this.setLocation(playerX + Player.speed, playerY);
		}else if(!this.up && !this.down && !this.left && this.right) {
			this.setLocation(playerX - Player.speed, playerY);
		}else if(!this.up && this.down && !this.left && this.right) {
			this.setLocation(playerX - Player.speed, playerY - Player.speed);
		}else if(this.up && !this.down && !this.left && this.right) {
			this.setLocation(playerX - Player.speed, playerY + Player.speed);
		}else if(!this.up && this.down && this.left && !this.right) {
			this.setLocation(playerX + Player.speed, playerY - Player.speed);
		}else if(this.up && !this.down && this.left && !this.right) {
			this.setLocation(playerX + Player.speed, playerY + Player.speed);
		}
		
		//Corregir posicion del enemigo
		if(enemigo.name >= 20) {
			if(enemigo.up && !enemigo.left && !enemigo.right && !enemigo.down) {//Enviar hacia abajo
				enemigo.setLocation(enemigo.x, enemigo.y + enemigo.speed );
				
			}else if(enemigo.left && !enemigo.up && !enemigo.right && !enemigo.down) {//Enviar hacia la derecha
				enemigo.setLocation(enemigo.x + enemigo.speed  , enemigo.y);
				
			}else if(enemigo.right && !enemigo.left && !enemigo.up && !enemigo.down) {//Enviar hacia la izquierda
				enemigo.setLocation(enemigo.x  - enemigo.speed , enemigo.y);
				
			}else if(enemigo.down && !enemigo.left && !enemigo.right && !enemigo.up) {//Enviar hacia arriba
				enemigo.setLocation(enemigo.x, enemigo.y - enemigo.speed );
				
			}else if(enemigo.up && enemigo.left && !enemigo.right && !enemigo.down) {//Enviar abajo a la derecha
				enemigo.setLocation(enemigo.x + enemigo.speed , enemigo.y + enemigo.speed );
				
			}else if(enemigo.up && !enemigo.left && enemigo.right && !enemigo.down) {//Enviar abajo a la izquierda
				enemigo.setLocation(enemigo.x - enemigo.speed , enemigo.y + enemigo.speed );
				
			}else if(!enemigo.up && enemigo.left && !enemigo.right && enemigo.down) {//Enviar arriba a la derecha
				enemigo.setLocation(enemigo.x + enemigo.speed , enemigo.y - enemigo.speed );
				
			}else if(!enemigo.up && !enemigo.left && enemigo.right && enemigo.down) {//Enviar arriba a la izquierda
				enemigo.setLocation(enemigo.x - enemigo.speed , enemigo.y - enemigo.speed );
			}
		}
	}
	
	//Colision con el boss
	@SuppressWarnings("unused")
	private void colisionBoss() {
		
		if(Juego.ventana.paneles[Juego.ventana.panelActual].boss.currImg == 7) {
			
			boolean colisionRayo = false;
			Rectangle playerBounds = this.getBounds();
			
			playerBounds.height = (int) playerBounds.getHeight() - 30;
			playerBounds.y = (int) playerBounds.getY() + 20;
			playerBounds.width = (int) playerBounds.getWidth() - 60;
			playerBounds.x = (int) playerBounds.getX() + 30;
			
			Rectangle bossBounds = Juego.ventana.paneles[Juego.ventana.panelActual].boss.getBounds();
			
			bossBounds.x = (int) bossBounds.getX() + 20;
			bossBounds.width = (int) bossBounds.getWidth() - 470;
			
			if(playerBounds.intersects(bossBounds)) {
				colisionRayo = true;
				this.pv = 0;
			}
			
		}
	}
}