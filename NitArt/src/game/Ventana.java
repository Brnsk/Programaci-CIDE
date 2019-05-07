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
	
	//Paneles
	private final String TITLE = "Isaac?";
	public Panel panel;
	
	public Panel[] paneles;
	
	public int panelActual = -1;
	
	//Menu
	protected Menu menu;
	
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
		this.add(menu);
		this.setVisible(true);
	}
	
	private void iniciar() {
		paneles = new Panel[11];
		menu = new Menu();
	}
	
	//Metodo paint
	public void paint(Graphics2D g) {
		g.drawImage(this.paneles[panelActual].fondo, 0, 0, Ventana.WIDTH, Ventana.HEIGHT, Color.pink.darker(), null);
		
		if(panelActual == 10) {
			g.drawImage(this.paneles[panelActual].suelo2, 150, Ventana.HEIGHT / 2, 928, 330, Color.pink.darker(), null);
		}else {
			g.drawImage(this.paneles[panelActual].suelo, 150, 150, 928, 660, Color.pink.darker(), null);
			
			g.setColor(Color.BLACK);
			g.fillRect(150, Ventana.HEIGHT / 2 -35, 70, 70);//Cambiar de sala
		}
	}
	
	protected void addPanel() {
		System.out.println(panelActual);
		if(panelActual < 10) {
			panel = new Panel();
			
		}else {
			panel = new VictoryPanel();
		}
		
		panelActual++;
		
		paneles[panelActual] = panel;
		
		if(panelActual > 0) {
			this.paneles[panelActual-1].setFocusable(false);
			this.paneles[panelActual-1].removeKeyListener(panel);
			this.paneles[panelActual-1].setVisible(false);
		}
		
		this.add(paneles[panelActual]);
		paneles[panelActual].requestFocus();
	}
}