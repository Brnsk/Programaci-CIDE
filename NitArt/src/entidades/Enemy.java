package entidades;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

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
	private int speed;
	
	//Timer
	private Timer timer;
	
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
		speed = 3;
		
		//Timer que llama al metodo mover cada 20 ms
		timer = new Timer(40,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					mover();
					moverDiagonal();
			}
		});
		timer.start();
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
			y -= speed-2;
			x -= speed-2;
			
		}else if(x < Panel.player.getX() && y > Panel.player.getY()){
			//arriba derecha
			y -= speed-2;
			x += speed-2;
			
		}else if (x > Panel.player.getX() && y < Panel.player.getY()){
			//abajo izquierda
			y += speed-2;
			x -= speed-2;
			
		}else if(x < Panel.player.getX() && y < Panel.player.getY()){
			//abajo derecha
			y += speed-2;
			x += speed-2;
		}
		this.setLocation(x, y);
	}
}