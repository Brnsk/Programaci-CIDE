package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Ventana extends JFrame {
	//Medidas de la ventana
	private final int VENTANA_WIDTH = 1234;
	private final int VENTANA_HEIGHT = 995;
	public final static int WIDTH = 1228;
	public final static int HEIGHT = 960;
	
	//Atributos de la ventana
	private final String TITLE = "Isaac?";
	public static Panel panel;
	
	//CONSTRUCTOR
	protected Ventana() {
		iniciar();
		
		this.setSize(new Dimension(this.VENTANA_WIDTH,this.VENTANA_HEIGHT));
		this.setTitle(this.TITLE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setMinimumSize(new Dimension(this.VENTANA_WIDTH,this.VENTANA_HEIGHT));
		//this.setMaximumSize(new Dimension(this.WIDTH,this.HEIGHT)); // NOT WORKING
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
	}
	
	private void iniciar() {
		panel = new Panel();
	}
	
	//Metodo paint
	public void paint(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 150, HEIGHT);
		g.fillRect(0, 0, WIDTH, 150);
		g.fillRect(0, HEIGHT - 150, WIDTH, 150);
		g.fillRect(WIDTH - 150, 0, 150, HEIGHT);
	}
}