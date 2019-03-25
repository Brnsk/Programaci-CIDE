package entidades;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import game.Juego;
import game.Ventana;

@SuppressWarnings("serial")
public class Player extends JLabel implements JugadorEnemigos{
	
	//Atributos para la imagen del pj
	private Image img;
	private ImageIcon icon;
	
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
	public Bala bala2;
	public Bala bala3;
	public Bala bala4;
	
	//Velocidad
	public static int speed;
	
	//Tamaño
	public static final int WIDTH = 80;
	public static final int HEIGHT = 80;
	
	//Timer
	private Timer timer;
	private ActionListener actionlistener;
	
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
		
		//Velocidad
		speed = 5;
		
		//Insertar imagen
		imagen();
		actionListener();
		
		//Timer que llama al metodo mover cada 20 ms
		timer = new Timer(20, actionlistener);
		timer.start();
	}
	
	//Añadir imagen a jlabel
	private void imagen() {
		img = new ImageIcon("D:\\git\\repository\\NitArt\\img\\Naruto.png").getImage();
		icon = new ImageIcon(img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
	}
	
	//Metodo que llama a mover cada 20 ms
	private void actionListener() {
		actionlistener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mover();
				
				if(bala != null) {
					if(bala.y > 150 && bala.y < Ventana.HEIGHT-150-bala.HEIGHT && bala.x < Ventana.WIDTH-150-bala.WIDTH && bala.x > 150) {
						disparar();
						
					}else {
						
						eliminarBala();
						
						bala.setIcon(null);
						bala = null;
						
					}
				}
			}
		};
	}
	
	//Metodo que llama a mover cada 20 ms BETA
	/*public void activarMovimiento() {
		while(!Juego.gameover) {
			
			mover();
			
			//Dormir 20 ms
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}*/
	
	//Eliminar bala
	private void eliminarBala() {
		disparando = false;
		bulletUp = false;
		bulletDown = false;
		bulletRight = false;
		bulletLeft = false;
	}
	
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
		if( y >= Ventana.HEIGHT - 150 - HEIGHT) {
			down = false;
		}else if( y <= 150 - HEIGHT + 20) {
			up = false;
		}
	}
	
	//================ METODOS INTERFAZ COMUN ENTRE JUGADOR Y ENEMIGOS ================
	
	//Aun no implementado en la interfaz
	public void disparar() {
		
		if(bulletUp) {
			bala.y -= Bala.speed;
			bala.setLocation(bala.x , bala.y);
			
		}else if(bulletDown) {
			bala2.y += Bala.speed;
			bala2.setLocation(bala2.x , bala2.y);
			
		}else if(bulletRight) {
			bala4.x += Bala.speed;
			bala4.setLocation(bala4.x , bala4.y);
			
		}else if(bulletLeft) {
			bala3.x -= Bala.speed;
			bala3.setLocation(bala3.x , bala3.y);
		}
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
}