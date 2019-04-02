package entidades;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Juego;

@SuppressWarnings("serial")
public class Enemy extends JLabel implements JugadorEnemigos{
	//Imagen
	private Image img;
	private ImageIcon icon;
	
	//Tamaño
	public static final int WIDTH = 80;
	public static final int HEIGHT = 80;
	
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
	
	protected int name;
	
	public Enemy(int name) {
		iniciar();
		
		this.setIcon(icon);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLocation(x, y);
		this.name = name;
	}
	
	@SuppressWarnings("static-access")
	private void iniciar() {
		imagen();
		
		x = (int)(Math.random()*((Juego.ventana.WIDTH-150-this.WIDTH)-150+1)+150);
		y = (int)(Math.random()*((Juego.ventana.HEIGHT-150-this.HEIGHT)-150+1)+150);
		
		speed = 2;
		
		//Movimiento
		up = false;
		down = false;
		left = false;
		right = false;
	}
	
	private void imagen() {
		//Preparar imagen
		img = new ImageIcon("D:\\git\\repository\\NitArt\\img\\enemy.png").getImage();
		icon = new ImageIcon(img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
	}

	@Override
	public void mover() {
		x = this.getX();
		y = this.getY();
		
		if(x < Juego.ventana.panel.player.getX()) {
			//mover derecha
			x += speed;
			
		}else if(x > Juego.ventana.panel.player.getX()) {
			//mover izquierda
			x -= speed;
		}else if(y < Juego.ventana.panel.player.getY()) {
			//Mover abajo
			y += speed;
		}else if(y > Juego.ventana.panel.player.getY()) {
			//Mover arriba
			y -= speed;
		}
		this.setLocation(x, y);
		
		up = false;
		down = false;
		left = false;
		right = false;
	}

	@Override
	public void moverDiagonal() {
		if(x > Juego.ventana.panel.player.getX() && y > Juego.ventana.panel.player.getY()){
			//arriba izquierda
			y -= speed-1;
			x -= speed-1;
			
		}else if(x < Juego.ventana.panel.player.getX() && y > Juego.ventana.panel.player.getY()){
			//arriba derecha
			y -= speed-1;
			x += speed-1;
			
		}else if (x > Juego.ventana.panel.player.getX() && y < Juego.ventana.panel.player.getY()){
			//abajo izquierda
			y += speed-1;
			x -= speed-1;
			
		}else if(x < Juego.ventana.panel.player.getX() && y < Juego.ventana.panel.player.getY()){
			//abajo derecha
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
		
	}

	@Override
	public boolean colisionY(int a,int b) {
		return false;
	}

	@Override
	public boolean colisionX(int a, int b) {
		return false;
		
	}
}