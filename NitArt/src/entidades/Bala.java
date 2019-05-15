package entidades;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Juego;
import game.Ventana;

@SuppressWarnings("serial")
public class Bala extends JLabel {
	//Imagen
	public ImageIcon img [];
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

		this.setSize(WIDTH, HEIGHT);
		this.x = x;
		this.y = y;
		this.setLocation(x, y);
		this.direccion = direccion;
	}
	
	private void iniciar() {
		speed = 20;
	}
	
	private void imagen() {
		img = new ImageIcon[5];
		img[0]  = new ImageIcon(new ImageIcon(getClass().getResource("balaArriba.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
		img[1]  = new ImageIcon(new ImageIcon(getClass().getResource("balaAbajo.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
		img[2]  = new ImageIcon(new ImageIcon(getClass().getResource("balaIzquierda.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
		img[3]  = new ImageIcon(new ImageIcon(getClass().getResource("balaDerecha.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
		img[4]  = new ImageIcon(new ImageIcon(getClass().getResource("bala.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
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
		
		if(this.x <= -40 || this.x >= Ventana.WIDTH) {
			posicionX = false;
		}else {
			posicionX = true;
		}
		
		return posicionX;
	}
	
	//Comprobar que la bala esta dentro del tabler eje Y
	private boolean comprobarY() {
		boolean posicionY = false;
		
		if(this.y >= Ventana.HEIGHT || this.y <= -40) {
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