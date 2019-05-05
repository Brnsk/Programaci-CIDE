package entidades;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Juego;
import game.Ventana;

@SuppressWarnings("serial")
public class Enemy extends JLabel implements JugadorEnemigos{
	//Fps de la clase triEnemy
	public int triFps = 0;
	
	public Image[] imagenes;
	protected ImageIcon icon;
	public int currImg;
	
	
	//Tamaño
	public static final int WIDTH = 95;
	public static final int HEIGHT = 95;
	
	//Coordenadas
	protected int x;
	protected int y;
	
	//Velocidad
	protected int speed;
	
	//Vida
	public int pv = 1;
	
	//Movimiento
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	public int name;
	
	public boolean colision = false;
	
	//Spawn points
	private int[][] spawns = {{150,Ventana.WIDTH -150 - Enemy.WIDTH, 300,400,500,600,700},
							  {150,Ventana.HEIGHT-150 - Enemy.HEIGHT,300,400,500,600,700}};
	
	//CONSTRUCTOR
	public Enemy(int name) {
		iniciar();
		spawn();
		
		this.setIcon(icon);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLocation(x, y);
		this.name = name;
	}
	
	@SuppressWarnings("static-access")
	private void iniciar() {
		imagenes();
		
		speed = 5;
		
		//Movimiento
		up = false;
		down = false;
		left = false;
		right = false;
	}
	
	//Spawn aleatorio
	private void spawn() {
		this.x = this.spawns[0][(int)(Math.random()*7)];
		
		if(this.x == 150 || this.x == Ventana.WIDTH -150 - Enemy.WIDTH) {
			this.y = this.spawns[1][(int)(Math.random()*7)];
		}else {
			this.y = this.spawns[1][(int)(Math.random()*2)];
		}
	}
	
	protected void imagenes() {
		imagenes = new Image[6];
		
		//Preparar imagen principal
		imagenes[0] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadfront.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		icon = new ImageIcon(imagenes[0]);
		currImg = 0;
		
		//Caminando de frente paso1
		imagenes[1] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadFrontPaso1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		//Caminando de frente paso2
		imagenes[2] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadFrontPaso2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		//Caminando de espaldas paso1
		imagenes[3] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadBackPaso1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
				
		//Caminando de espaldas paso2
		imagenes[4] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadBackPaso2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
	}

	@Override
	public void mover() {
		x = this.getX();
		y = this.getY();
		
		if(x < Ventana.WIDTH - 150 - Enemy.WIDTH && y <= 150) {//Mover derecha
			right = true;
			
			left = false;
			up = false;
			down= false;
		}else if(y <= Ventana.HEIGHT - 150 - Enemy.HEIGHT && x >= Ventana.WIDTH - 150 - Enemy.WIDTH) {//Mover abajo
			down = true;

			right = false;
			up = false;
			left = false;
		}else if(x > 150) {//Mover izquierda
			left = true;
			
			down = false;
			up = false;
			right= false;
		}else if(y > 150) {//Mover arriba
			up = true;
			
			left = false;
			right = false;
			down= false;
		}
		
		if(right && !left && !up && !down) {//derecha
			x += speed;
		}else if(!right && left && !up && !down) {//izquierda
			x -= speed;
		}else if(!right && !left && up && !down) {//arriba
			y -= speed;
		}else if(!right && !left && !up && down) {//abajo
			y += speed;
		}
		
		this.setLocation(x, y);
	}

	@Override
	public void moverDiagonal() {
		/*NOTHING*/
	}

	@Override
	public void disparar() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void comprobarColision() {
		//boolean colision = false;
		
		for(int i = 0; i < Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.size(); i++) {
			colision = false;
			for(int j = 0; j < Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.size(); j++) {
				
				Enemy enemigo = Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.get(i);
				Enemy otroEnemigo = Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.get(j);
				
				Rectangle enemigoBounds;
				Rectangle playerBounds;
				
				if(i == j) {
					continue;
				}else {
					//Hitbox del enemigpo
					enemigoBounds = enemigo.getBounds();
					
					//Hitbox del player
					playerBounds = Juego.ventana.paneles[Juego.ventana.panelActual].player.getBounds();
					
					/*playerBounds.height = (int) playerBounds.getHeight() - 30;
					playerBounds.y = (int) playerBounds.getY() + 20;
					playerBounds.width = (int) playerBounds.getWidth() - 60;
					playerBounds.x = (int) playerBounds.getX() + 30;*/
						
					if(enemigoBounds.intersects(otroEnemigo.getBounds()) || enemigoBounds.intersects(playerBounds)) {
						colision = true;
						colisionando(enemigo, otroEnemigo);
					}
				}
			}
		}
	}


	public void colisionando(Enemy enemigo, Enemy otroEnemigo) {
		//Colision entre enemigos ==========================================
		//Enemigo
		/*if(enemigo.up && !enemigo.left && !enemigo.right && !enemigo.down) {//Enviar hacia abajo
			enemigo.setLocation(enemigo.x, enemigo.y + this.speed );
			
		}else if(enemigo.left && !enemigo.up && !enemigo.right && !enemigo.down) {//Enviar hacia la derecha
			enemigo.setLocation(enemigo.x + this.speed  , enemigo.y);
			
		}else if(enemigo.right && !enemigo.left && !enemigo.up && !enemigo.down) {//Enviar hacia la izquierda
			enemigo.setLocation(enemigo.x  - this.speed , enemigo.y);
			
		}else if(enemigo.down && !enemigo.left && !enemigo.right && !enemigo.up) {//Enviar hacia arriba
			enemigo.setLocation(enemigo.x, enemigo.y - this.speed );
			
		}else if(enemigo.up && enemigo.left && !enemigo.right && !enemigo.down) {//Enviar abajo a la derecha
			enemigo.setLocation(enemigo.x + this.speed , enemigo.y + this.speed );
			
		}else if(enemigo.up && !enemigo.left && enemigo.right && !enemigo.down) {//Enviar abajo a la izquierda
			enemigo.setLocation(enemigo.x - this.speed , enemigo.y + this.speed );
			
		}else if(!enemigo.up && enemigo.left && !enemigo.right && enemigo.down) {//Enviar arriba a la derecha
			enemigo.setLocation(enemigo.x + this.speed , enemigo.y - this.speed );
			
		}else if(!enemigo.up && !enemigo.left && enemigo.right && enemigo.down) {//Enviar arriba a la izquierda
			enemigo.setLocation(enemigo.x - this.speed , enemigo.y - this.speed );
		}*/
		
		enemigo.up = false;
		enemigo.down = false;
		enemigo.left = false;
		enemigo.right = false;
		
		//Otro enemigo
		/*if(otroEnemigo.up && !otroEnemigo.left && !otroEnemigo.right && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x, otroEnemigo.y - this.speed);
		}else if(otroEnemigo.left && !otroEnemigo.up && !otroEnemigo.right && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x - this.speed , otroEnemigo.y);
		}else if(otroEnemigo.right && !otroEnemigo.left && !otroEnemigo.up && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x  + this.speed , otroEnemigo.y);
		}else if(otroEnemigo.down && !otroEnemigo.left && !otroEnemigo.right && !otroEnemigo.up) {
			otroEnemigo.setLocation(otroEnemigo.x, otroEnemigo.y + this.speed);
		}else if(otroEnemigo.up && otroEnemigo.left && !otroEnemigo.right && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x - this.speed, otroEnemigo.y - this.speed);
		}else if(otroEnemigo.up && !otroEnemigo.left && otroEnemigo.right && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x + this.speed, otroEnemigo.y - this.speed);
		}else if(!otroEnemigo.up && otroEnemigo.left && !otroEnemigo.right && otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x - this.speed, otroEnemigo.y + this.speed);
		}else if(!otroEnemigo.up && !otroEnemigo.left && otroEnemigo.right && otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x + this.speed, otroEnemigo.y + this.speed);
		}*/
	}
}