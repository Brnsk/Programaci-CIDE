package entidades;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Juego;
import game.Panel;

public class Bala extends JLabel {
	//Imagen
	Image img;
	ImageIcon icon;
	
	//Rango de la bala
	private int maxRange;
	
	//Velocidad de la bala
	protected static int speed;
	
	//Tamaño
	protected final int WIDTH = 30;
	protected final int HEIGHT= 30;
	
	//Coordenadas
	protected int x;
	protected int y;
	
	//Direccion
	protected String direccion;
	
	public Bala(int x, int y, String direccion) {
		iniciar();
		imagen();
		this.setIcon(icon);
		this.setSize(WIDTH, HEIGHT);
		this.x = x;
		this.y = y;
		this.setLocation(x, y);
		this.direccion = direccion;
	}
	
	private void iniciar() {
		maxRange = 700;
		speed = 8;
	}
	
	private void imagen() {
		img  = new ImageIcon("D:\\git\\repository\\NitArt\\img\\bala.png").getImage();
		icon = new ImageIcon(img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
	}
	
	//Comprobar donde esta la bala
	protected boolean comprobarPosicion() {
		boolean posicionOk = false;
		
		if(comprobarY() && comprobarX() && !comprobarColision()) {
			posicionOk = true;
		}else{
			this.setIcon(null);
			posicionOk = false;
		}
		
		return posicionOk;
	}
	
	//Comprobar que la bala esta dentro del tablero eje X
	private boolean comprobarX() {
		boolean posicionX = false;
		
		if(this.x <= 150 - Juego.ventana.paneles[Juego.ventana.panelActual].player.WIDTH || this.x >= Juego.ventana.WIDTH - 150 - this.WIDTH + Juego.ventana.paneles[Juego.ventana.panelActual].player.WIDTH) {
			posicionX = false;
		}else {
			posicionX = true;
		}
		
		return posicionX;
	}
	
	//Comprobar que la bala esta dentro del tabler eje Y
	private boolean comprobarY() {
		boolean posicionY = false;
		
		if(this.y >= Juego.ventana.HEIGHT - 150 - this.HEIGHT + Juego.ventana.paneles[Juego.ventana.panelActual].player.HEIGHT || this.y <= 150 - this.HEIGHT - Juego.ventana.paneles[Juego.ventana.panelActual].player.HEIGHT) {
			posicionY = false;
		}else {
			posicionY = true;
		}
		
		return posicionY;
	}
	
	//Comprobar colision de la bala con un enemigo
	private boolean comprobarColision() {	
		int balaX = this.x;
		int bX = balaX - this.speed;
		int enemigoX = 0;
		int eX = 0;
		
		int balaY = this.y;
		int bY = balaY - this.speed;
		int enemigoY = 0;
		int eY = 0;
		
		boolean colision = false;
		
		try {
			for(int i = 0; i < Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.size(); i++) {
				colision = false;
				
				enemigoX = Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.get(i).getX();
				eX = enemigoX - Enemy.speed;
				
				enemigoY = Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.get(i).getY();
				eY = enemigoY - Enemy.speed;
			
				//COLISION CON ENEMIGOS (DE LAS BALAS)
				
				//Disparos en eje X (Derecha o Izquierda)
				if(balaX + this.WIDTH >= enemigoX && bX <= eX || balaX >= enemigoX + Enemy.WIDTH && bX <= eX + Enemy.WIDTH) {
					for(int j = 0; j < Enemy.HEIGHT; j++) {
						if(balaY + this.HEIGHT == enemigoY + j) {//Derecha?
							colision = true;
						}else if(balaY == enemigoY + j) {//Izquierda?
							colision = true;
						}
					}
				//Disparos en eje Y (Arriba o Abajo)
				}else if(balaY >= enemigoY && bY <= eY || balaY >= enemigoY + Enemy.HEIGHT && bY <= eY + Enemy.HEIGHT) {
					for(int j = 0; j < Enemy.WIDTH; j++) {
						if(balaX + this.WIDTH == enemigoX + j) {//Arriba?
							colision = true;
						}else if(balaX == enemigoX + j) {//Abajo?
							colision = true;
						}
					}
				}
				if(colision) {
					Juego.ventana.paneles[Juego.ventana.panelActual].player.eliminarBala(Juego.ventana.paneles[Juego.ventana.panelActual].player.contadorBalas);
					Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.get(i).pv--;
				}
			}
		}catch(Exception e) {
			System.out.println("Error en la colision de la bala con el enemigo");
			System.out.println(e.getMessage());
		}
		
		return colision;
	}
}