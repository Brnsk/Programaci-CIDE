package game;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Corazones extends JLabel{
	protected Image img;
	protected ImageIcon icon;
	
	private final int WIDTH = 50;
	private final int HEIGHT = 50;
	
	//Location
	public static int x = 0;
	
	protected Corazones() {
		iniciar();
		
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setIcon(icon);
		this.setLocation(x, 0);
	}
	
	
	private void iniciar() {
		imagenes();
	}
	
	//Metodo para las imagenes
	private void imagenes() {
		
		img = new ImageIcon("D:\\git\\repository\\NitArt\\img\\corazones\\vida.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
	}
}