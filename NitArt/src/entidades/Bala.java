package entidades;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Panel;
import game.Ventana;

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
		
		if(this.x <= 150 - Panel.player.WIDTH || this.x >= Ventana.WIDTH - 150 - this.WIDTH + Panel.player.WIDTH) {
			posicionX = false;
		}else {
			posicionX = true;
		}
		
		return posicionX;
	}
	
	//Comprobar que la bala esta dentro del tabler eje Y
	private boolean comprobarY() {
		boolean posicionY = false;
		
		if(this.y >= Ventana.HEIGHT - 150 - this.HEIGHT + Panel.player.HEIGHT || this.y <= 150 - this.HEIGHT - Panel.player.HEIGHT) {
			posicionY = false;
		}else {
			posicionY = true;
		}
		
		return posicionY;
	}
	
	//Comprobar colision de la bala con un enemigo
	private boolean comprobarColision() {	
		int balaX = this.x;
		int enemigoX = Ventana.panel.enemy.getX();
		int bX = balaX - this.speed;
		int eX = enemigoX - Enemy.speed;
		
		int balaY = this.y;
		int enemigoY = Ventana.panel.enemy.getY();
		int bY = balaY - this.speed;
		int eY = enemigoY - Enemy.speed;
		
		boolean colision = false;
		
		//Colision con enemigos
		if((balaX >= enemigoX && bX <= eX) || (balaY >= enemigoY && bY <= eY)) {
			colision = true;
			Ventana.panel.enemy.pv--;
		}
		
		return colision;
	}
}