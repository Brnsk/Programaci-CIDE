package entidades;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Juego;

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
	private int x;
	private int y;
	
	//Velocidad
	protected static int speed;
	
	//Vida
	public int pv = 3;
	
	//Movimiento
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	public int name;
	
	//CONSTRUCTOR
	public Enemy(int name) {
		iniciar();
		
		this.setIcon(icon);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLocation(x, y);
		this.name = name;
	}
	
	@SuppressWarnings("static-access")
	private void iniciar() {
		imagenes();
		
		x = (int)(Math.random()*((Juego.ventana.WIDTH-150-this.WIDTH)-150+1)+150);
		y = (int)(Math.random()*((Juego.ventana.HEIGHT-150-this.HEIGHT)-150+1)+150);
		
		speed = 2;
		
		//Movimiento
		up = false;
		down = false;
		left = false;
		right = false;
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
		
		if(x < Juego.ventana.paneles[Juego.ventana.panelActual].player.getX()) {
			//mover derecha
			right = true;
			
			left = false;
			up = false;
			down= false;
			
		}else if(x > Juego.ventana.paneles[Juego.ventana.panelActual].player.getX()) {
			//mover izquierda
			left = true;
			
			right = false;
			up = false;
			down= false;
		}else if(y < Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()) {
			//Mover abajo
			down = true;
			
			left = false;
			up = false;
			right= false;
		}else if(y > Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()) {
			//Mover arriba
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
		if(x > Juego.ventana.paneles[Juego.ventana.panelActual].player.getX() && y > Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()){
			//arriba izquierda
			up = true;
			left = true;
			
			right = false;
			down= false;
		}else if(x < Juego.ventana.paneles[Juego.ventana.panelActual].player.getX() && y > Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()){
			//arriba derecha
			up = true;
			right = true;
			
			left = false;
			down= false;
		}else if (x > Juego.ventana.paneles[Juego.ventana.panelActual].player.getX() && y < Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()){
			//abajo izquierda
			down = true;
			left = true;
			
			right = false;
			up = false;
		}else if(x < Juego.ventana.paneles[Juego.ventana.panelActual].player.getX() && y < Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()){
			//abajo derecha
			down = true;
			right = true;
			
			left = false;
			up = false;
		}
		
		if(right && !left && up && !down) {//arriba derecha
			y -= speed-1;
			x += speed-1;
		}else if(!right && left && up && !down) {//arriba izquierda
			y -= speed-1;
			x -= speed-1;
		}else if(!right && left && !up && down) {//abajo izquierda
			y += speed-1;
			x -= speed-1;
		}else if(right && !left && !up && down) {//abajo derecha
			y += speed-1;
			x += speed-1;
		}
		
		this.setLocation(x, y);
	}

	@Override
	public void disparar() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void comprobarColision() {
		boolean colision = false;
		
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
					
					/*enemigoBounds.height = (int) enemigoBounds.getHeight() - 30;
					enemigoBounds.y = (int) enemigoBounds.getY() + 20;
					enemigoBounds.width = (int) enemigoBounds.getWidth() - 60;
					enemigoBounds.x = (int) enemigoBounds.getX() + 30;*/
					
					//Hitbox del player
					playerBounds = Juego.ventana.paneles[Juego.ventana.panelActual].player.getBounds();
					
					playerBounds.height = (int) playerBounds.getHeight() - 30;
					playerBounds.y = (int) playerBounds.getY() + 20;
					playerBounds.width = (int) playerBounds.getWidth() - 60;
					playerBounds.x = (int) playerBounds.getX() + 30;
						
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
		if(enemigo.up && !enemigo.left && !enemigo.right && !enemigo.down) {
			enemigo.setLocation(enemigo.x, enemigo.y + Enemy.speed +1);
			
		}else if(enemigo.left && !enemigo.up && !enemigo.right && !enemigo.down) {
			enemigo.setLocation(enemigo.x + Enemy.speed +1, enemigo.y);
			
		}else if(enemigo.right && !enemigo.left && !enemigo.up && !enemigo.down) {
			enemigo.setLocation(enemigo.x  - Enemy.speed -1, enemigo.y);
			
		}else if(enemigo.down && !enemigo.left && !enemigo.right && !enemigo.up) {
			enemigo.setLocation(enemigo.x, enemigo.y - Enemy.speed -1);
			
		}else if(enemigo.up && enemigo.left && !enemigo.right && !enemigo.down) {
			enemigo.setLocation(enemigo.x + Enemy.speed +1, enemigo.y + Enemy.speed +1);
			
		}else if(enemigo.up && !enemigo.left && enemigo.right && !enemigo.down) {
			enemigo.setLocation(enemigo.x - Enemy.speed -1, enemigo.y + Enemy.speed +1);
			
		}else if(!enemigo.up && enemigo.left && !enemigo.right && enemigo.down) {
			enemigo.setLocation(enemigo.x + Enemy.speed +1, enemigo.y - Enemy.speed -1);
			
		}else if(!enemigo.up && !enemigo.left && enemigo.right && enemigo.down) {
			enemigo.setLocation(enemigo.x - Enemy.speed -1, enemigo.y - Enemy.speed -1);
		}
		
		
		//Otro enemigo
		/*if(otroEnemigo.up && !otroEnemigo.left && !otroEnemigo.right && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x, otroEnemigo.y - Enemy.speed);
		}else if(otroEnemigo.left && !otroEnemigo.up && !otroEnemigo.right && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x - Enemy.speed , otroEnemigo.y);
		}else if(otroEnemigo.right && !otroEnemigo.left && !otroEnemigo.up && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x  + Enemy.speed , otroEnemigo.y);
		}else if(otroEnemigo.down && !otroEnemigo.left && !otroEnemigo.right && !otroEnemigo.up) {
			otroEnemigo.setLocation(otroEnemigo.x, otroEnemigo.y + Enemy.speed);
		}else if(otroEnemigo.up && otroEnemigo.left && !otroEnemigo.right && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x - Enemy.speed, otroEnemigo.y - Enemy.speed);
		}else if(otroEnemigo.up && !otroEnemigo.left && otroEnemigo.right && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x + Enemy.speed, otroEnemigo.y - Enemy.speed);
		}else if(!otroEnemigo.up && otroEnemigo.left && !otroEnemigo.right && otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x - Enemy.speed, otroEnemigo.y + Enemy.speed);
		}else if(!otroEnemigo.up && !otroEnemigo.left && otroEnemigo.right && otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x + Enemy.speed, otroEnemigo.y + Enemy.speed);
		}*/
		
		//Colision con el jugador =========================================
	}
}