package entidades;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Panel;

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
	
	public Enemy() {
		iniciar();
		
		this.setIcon(icon);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLocation(x, y);
	}
	
	private void iniciar() {
		imagen();
		
		x = 200;
		y = 200;
		speed = 2;
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
		
		if(x < Panel.player.getX()) {
			//mover derecha
			x += speed;			
		}else if(x > Panel.player.getX()) {
			//mover izquierda
			x -= speed;
		}else if(y < Panel.player.getY()) {
			//Mover abajo
			y += speed;
		}else if(y > Panel.player.getY()) {
			//Mover arriba
			y -= speed;
		}
		this.setLocation(x, y);
	}

	@Override
	public void moverDiagonal() {
		if(x > Panel.player.getX() && y > Panel.player.getY()){
			//arriba izquierda
			y -= speed-1;
			x -= speed-1;
			
		}else if(x < Panel.player.getX() && y > Panel.player.getY()){
			//arriba derecha
			y -= speed-1;
			x += speed-1;
			
		}else if (x > Panel.player.getX() && y < Panel.player.getY()){
			//abajo izquierda
			y += speed-1;
			x -= speed-1;
			
		}else if(x < Panel.player.getX() && y < Panel.player.getY()){
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
}