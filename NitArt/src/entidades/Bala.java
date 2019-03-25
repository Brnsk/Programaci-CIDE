package entidades;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
	
	public Bala(int x, int y) {
		iniciar();
		imagen();
		this.setIcon(icon);
		this.setSize(WIDTH, HEIGHT);
		this.x = x;
		this.y = y;
		this.setLocation(x, y);
	}
	
	private void iniciar() {
		maxRange = 700;
		speed = 8;
	}
	
	private void imagen() {
		img  = new ImageIcon("D:\\git\\repository\\NitArt\\img\\bala.png").getImage();
		icon = new ImageIcon(img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
	}
}