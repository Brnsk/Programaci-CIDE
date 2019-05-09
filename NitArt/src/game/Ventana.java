package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Ventana extends JFrame {
	//Medidas de la ventana
	private final int VENTANA_WIDTH = 1234;
	private final int VENTANA_HEIGHT = 900;
	public final static int WIDTH = 1228;
	public final static int HEIGHT = 865;
	
	//Paneles
	private final String TITLE = "Space Dungeon";
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(menu);
		this.setVisible(true);
	}
	
	private void iniciar() {
		paneles = new Panel[12];
		menu = new Menu();
	}
	
	
	
	//Metodo paint
	public void paint(Graphics2D g) {
		
		if(panelActual <= 10 && panelActual >= 0) {
			g.drawImage(this.paneles[panelActual].fondo, 0, 0, Ventana.WIDTH, Ventana.HEIGHT, Color.pink.darker(), null);
			
			if(panelActual == 10) {
				g.drawImage(this.paneles[panelActual].suelo2, 150, Ventana.HEIGHT / 2, 928, 330, null);
			}else {
				g.drawImage(this.paneles[panelActual].suelo, 150, 90, 928, 660, null);
				
				if(Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.size() <= 0) {
					g.setColor(Color.BLACK);
					g.fillRect(150, Ventana.HEIGHT / 2 -35, 70, 70);
				}
			}
			
			//Afegir imatges a la pausa
			if (Juego.pause) {
				g.drawImage(Panel.pausa, 150, 0, 928, 150, null);
				g.drawImage(Panel.pausab, 25, 25, 100, 100, null);
			}
		}
		
		if(Juego.win && panelActual == 11) {
			g.drawImage(VictoryPanel.img, 0, 0, Ventana.WIDTH, Ventana.HEIGHT, null);
		}else if (Juego.gameover) {
			g.drawImage(GameOverPanel.gover, 0, 0, Ventana.WIDTH, Ventana.HEIGHT, null);
		}
	}
	
	protected void addPanel() {
		System.out.println("Habitacion: "+panelActual);
		
		if(panelActual < 10 && !Juego.gameover) {
			panel = new Panel();
		}else if(Juego.gameover){
			panel = new GameOverPanel();
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