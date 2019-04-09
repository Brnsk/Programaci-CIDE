package entidades;

import java.awt.Dimension;
import java.awt.Image;

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
			x += speed;
			right = true;
			
			left = false;
			up = false;
			down= false;
			
		}else if(x > Juego.ventana.paneles[Juego.ventana.panelActual].player.getX()) {
			//mover izquierda
			x -= speed;
			left = true;
			
			right = false;
			up = false;
			down= false;
		}else if(y < Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()) {
			//Mover abajo
			y += speed;
			down = true;
			
			left = false;
			up = false;
			right= false;
		}else if(y > Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()) {
			//Mover arriba
			y -= speed;
			up = true;
			
			left = false;
			right = false;
			down= false;
		}
		this.setLocation(x, y);
	}

	@Override
	public void moverDiagonal() {
		if(x > Juego.ventana.paneles[Juego.ventana.panelActual].player.getX() && y > Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()){
			//arriba izquierda
			y -= speed-1;
			x -= speed-1;
			
			up = true;
			left = true;
			
			right = false;
			down= false;
		}else if(x < Juego.ventana.paneles[Juego.ventana.panelActual].player.getX() && y > Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()){
			//arriba derecha
			y -= speed-1;
			x += speed-1;
			
			up = true;
			right = true;
			
			left = false;
			down= false;
		}else if (x > Juego.ventana.paneles[Juego.ventana.panelActual].player.getX() && y < Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()){
			//abajo izquierda
			y += speed-1;
			x -= speed-1;
			
			down = true;
			left = true;
			
			right = false;
			up = false;
		}else if(x < Juego.ventana.paneles[Juego.ventana.panelActual].player.getX() && y < Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()){
			//abajo derecha
			y += speed-1;
			x += speed-1;
			
			down = true;
			right = true;
			
			left = false;
			up = false;
		}
		this.setLocation(x, y);
	}

	@Override
	public void disparar() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void comprobarColision() {
		boolean colision = false;
		int cambio = 0;
		
		for(int i = 0; i < Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.size(); i++) {
			for(int j = 0; j < Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.size(); j++) {
				
				Enemy enemigo = Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.get(i);
				Enemy otroEnemigo = Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.get(j);
				
				if(i == j) {
					continue;
				}else {
					if((enemigo.x + this.WIDTH) >= otroEnemigo.x && (enemigo.x - this.speed) <= (otroEnemigo.x - this.speed)) {//Choque entre enemigos por la derecha del actual
						colision = colisionY(enemigo.y,otroEnemigo.y);
						if(colision  && cambio == 0) {
							enemigo.setLocation(enemigo.x - this.speed, enemigo.y + this.speed);
							cambio = 1;
						}else if(colision && cambio == 1) {
							enemigo.setLocation(enemigo.x + this.speed, enemigo.y - this.speed);
							cambio = 2;
						}else if(colision && cambio == 2) {
							enemigo.setLocation(enemigo.x + this.speed, enemigo.y + this.speed);
							cambio = 3;
						}else if(colision && cambio == 3) {
							enemigo.setLocation(enemigo.x - this.speed, enemigo.y - this.speed);
							cambio = 0;
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean colisionY(int enemigoY,int otroEnemigoY) {
		int c = this.HEIGHT-40;
		boolean bool=false;
		
		for(int j = 0; j < Enemy.HEIGHT; j++) {
			
			if(enemigoY+c == otroEnemigoY+j) {
				
				bool = true;
			}
			if(j == Enemy.HEIGHT -1 && c > 40) {
				
				c--;
				j = 0;
			}
		}
		return bool;
	}

	@Override
	public boolean colisionX(int a, int b) {
		return false;
		
	}
}