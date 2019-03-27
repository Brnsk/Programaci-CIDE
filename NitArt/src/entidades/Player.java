package entidades;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
	
	public ArrayList <Bala> cargador;
	
	//Velocidad
	public static int speed;
	
	//Tamaño
	public final int WIDTH = 80;
	public final int HEIGHT = 80;
	
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
		
		//Velocidad
		speed = 6;
		
		//Insertar imagen
		imagen();
	}
	
	//Añadir imagen a jlabel
	private void imagen() {
		img = new ImageIcon("D:\\git\\repository\\NitArt\\img\\Naruto.png").getImage();
		icon = new ImageIcon(img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
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
		if( y >= Ventana.HEIGHT - 150 - HEIGHT) {
			down = false;
		}else if( y <= 150 - HEIGHT + 20) {
			up = false;
		}
	}
	
	//================ METODOS INTERFAZ COMUN ENTRE JUGADOR Y ENEMIGOS ================
	
	@Override
	public void disparar() {	
		for(int i = 0; i < cargador.size(); i++) {
			bala = cargador.get(i);
			
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