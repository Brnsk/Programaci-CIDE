package entidades;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Juego;
import game.Ventana;

@SuppressWarnings("serial")
public class Bala extends JLabel {
	//Imagen
	Image img;
	ImageIcon icon;
	
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
		speed = 15;
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
		
		if(this.x <= 150 - Juego.ventana.paneles[Juego.ventana.panelActual].player.WIDTH || this.x >= Ventana.WIDTH - 150 - this.WIDTH + Juego.ventana.paneles[Juego.ventana.panelActual].player.WIDTH) {
			posicionX = false;
		}else {
			posicionX = true;
		}
		
		return posicionX;
	}
	
	//Comprobar que la bala esta dentro del tabler eje Y
	private boolean comprobarY() {
		boolean posicionY = false;
		
		if(this.y >= Ventana.HEIGHT - 150 - this.HEIGHT + Juego.ventana.paneles[Juego.ventana.panelActual].player.HEIGHT || this.y <= 150 - this.HEIGHT - Juego.ventana.paneles[Juego.ventana.panelActual].player.HEIGHT) {
			posicionY = false;
		}else {
			posicionY = true;
		}
		
		return posicionY;
	}
	
	//Comprobar colision de la bala con un enemigo
	private boolean comprobarColision() {	
		boolean colision = false;
		
		try {
			for(int i = 0; i < Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.size(); i++) {
				colision = false;
				
				Enemy enemigo = Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.get(i);
			
				if(this.getBounds().intersects(enemigo.getBounds())) {
					colision = true;
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