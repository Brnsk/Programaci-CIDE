package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import entidades.Bala;
import entidades.Enemy;
import entidades.Player;

@SuppressWarnings("serial")
public class Panel extends JPanel implements KeyListener{
	public static Player player;
	private int key;
	
	public Enemy enemy;
	
	ArrayList <Enemy> enemigos = new ArrayList<Enemy>();
	
	
	//CONSTRUCTOR
	protected Panel() {
		iniciar();
		
		this.setBackground(Color.PINK.darker());
		this.setLayout(null);
		
		this.setFocusable(true);
		this.addKeyListener(this);
		
		this.add(player);
		this.add(enemy);
	}
	
	//METODOS PROPIOS DE LA CLASE
	private void iniciar() {
		player = new Player();
		enemy = new Enemy();
		enemigos.add(enemy);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(Juego.ventana != null) {
			Juego.ventana.paint((Graphics2D) g);
		}
	}
	
	//======================== KEY LISTENER ==========================
	
	@Override
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_W:
			player.up = true;
		break;
		case KeyEvent.VK_S:
			player.down = true;
		break;
		case KeyEvent.VK_A:
			player.left = true;
		break;
		case KeyEvent.VK_D:
			player.right = true;
		}
		//=========== Balas ===============
		
		if(!player.disparando) {
			switch(key) {
			case KeyEvent.VK_UP:
				player.bulletUp = true;
				player.disparando = true;
				
				player.bala = new Bala(player.getX(),player.getY(),"up");
				player.cargador.add(player.bala);
				Ventana.panel.add(player.bala);
			break;
			case KeyEvent.VK_DOWN:
				player.bulletDown = true;
				player.disparando = true;
				
				player.bala = new Bala(player.getX(),player.getY(),"down");
				player.cargador.add(player.bala);
				Ventana.panel.add(player.bala);
			break;
			case KeyEvent.VK_LEFT:
				player.bulletLeft = true;
				player.disparando = true;
				
				player.bala = new Bala(player.getX(),player.getY(),"left");
				player.cargador.add(player.bala);
				Ventana.panel.add(player.bala);
			break;
			case KeyEvent.VK_RIGHT:
				player.bulletRight = true;
				player.disparando = true;
				
				player.bala = new Bala(player.getX(),player.getY(),"right");
				player.cargador.add(player.bala);
				Ventana.panel.add(player.bala);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_W:
			player.up = false;
		break;
		case KeyEvent.VK_S:
			player.down = false;
		break;
		case KeyEvent.VK_A:
			player.left = false;
		break;
		case KeyEvent.VK_D:
			player.right = false;
		}
		//=========== Balas ===============
		/*switch(key) {
		case KeyEvent.VK_UP:
			player.bulletUp = false;
		break;
		case KeyEvent.VK_DOWN:
			player.bulletDown = false;
		break;
		case KeyEvent.VK_LEFT:
			player.bulletLeft = false;
		break;
		case KeyEvent.VK_RIGHT:
			player.bulletRight = false;
		}*/
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}