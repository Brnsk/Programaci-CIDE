package game;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Corazones extends JLabel{
	//Tamaño
	private final int WIDTH = 60;
	private final int HEIGHT = 60;
	
	//Imagen
	protected final ImageIcon ICON = new ImageIcon(new ImageIcon(getClass().getResource("vida.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
	
	//Location
	public static int x = 0;
	
	//CONSTRUCTOR
	protected Corazones() {
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setIcon(ICON);
		this.setLocation(x, 0);
	}
}