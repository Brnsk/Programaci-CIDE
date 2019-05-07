package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import entidades.Bala;
import entidades.Boss;
import entidades.Enemy;
import entidades.Player;
import entidades.TriEnemy;

@SuppressWarnings("serial")
public class Panel extends JPanel implements KeyListener{
	public Player player;
	private int key;
	
	private Enemy enemy;
	private TriEnemy triEnemy;
	
	
	public ArrayList <Enemy> enemigos = new ArrayList<Enemy>();
	
	protected Image suelo;
	
	protected Image suelo2;

	protected Image fondo;
	
	public Boss boss;
	
	private Corazones[] corazones;
	
	
	//CONSTRUCTOR
	protected Panel() {
		iniciar();
		
		this.setBackground(Color.PINK.darker());
		
		this.setLayout(null);
		
		this.setFocusable(true);
		this.addKeyListener(this);
		
		this.addEnemies();
		
		this.addVidas();
	}
	
	//METODOS PROPIOS DE LA CLASE
	private void iniciar() {
		
		//A�adir los corazones
		
		corazones = new Corazones[6];
		
		//A�adir entidades
		
		if(Juego.ventana.panelActual <= 0) {
			player = new Player();
			this.add(player);
		}else {
			player = Juego.ventana.paneles[Juego.ventana.panelActual-1].player;
			this.add(player);
			
			player.setLocation(1000,400);
			
			player.up = false;
			player.down = false;
			player.right = false;
			player.left = false;
		}
		
		suelo = new ImageIcon("D:\\git\\repository\\NitArt\\img\\suelo.jpg").getImage();
		suelo2 = new ImageIcon("D:\\git\\repository\\NitArt\\img\\suelo_2.jpg").getImage();
		fondo = new ImageIcon("D:\\git\\repository\\NitArt\\img\\fondo.jpg").getImage();
	}
	
	//A�adir enemigos
	private void addEnemies() {

		if (Juego.ventana.panelActual == 0) {//A partir del primer panel con enemigos
			int random = (int)(Math.random()*2 +1);
			
			for(int i = 0; i < random; i++) {
				enemy = new Enemy(i);
				enemigos.add(enemy);
				this.add(enemy);
			}
		}else if(Juego.ventana.panelActual >= 1 && Juego.ventana.panelActual < 3) {
			int random = (int)(Math.random()*3 +1);
			
			for(int i = 0; i < random; i+=2) {
				enemy = new Enemy(i);
				enemigos.add(enemy);
				
				triEnemy = new TriEnemy(i);
				enemigos.add(triEnemy);
				
				this.add(enemy);
				this.add(triEnemy);
			}
		}else if(Juego.ventana.panelActual >= 3 && Juego.ventana.panelActual < 6) {
			int random = (int)(Math.random()*4 +2);
			
			for(int i = 0; i < random; i+=2) {
				enemy = new Enemy(i);
				enemigos.add(enemy);
				
				triEnemy = new TriEnemy(i);
				enemigos.add(triEnemy);
				
				this.add(enemy);
				this.add(triEnemy);
			}
		}else if(Juego.ventana.panelActual >= 6 && Juego.ventana.panelActual < 9) {
			int random = (int)(Math.random()*5 +2);
			
			for(int i = 0; i < random; i+=2) {
				enemy = new Enemy(i);
				enemigos.add(enemy);
				
				triEnemy = new TriEnemy(i);
				enemigos.add(triEnemy);
				
				this.add(enemy);
				this.add(triEnemy);
			}
		}else if(Juego.ventana.panelActual >= 9){
			boss = new Boss();
			
			this.add(boss);
		}
	}
	
	private void addVidas() {
		for(int i = 0; i < corazones.length; i++) {
			corazones[i] = new Corazones();
			Corazones.x += 70;
			this.add(corazones[i]);
		}
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
		break;
		case KeyEvent.VK_ESCAPE:
		}
		//=========== Balas ===============
		
		if(!player.disparando) {
			switch(key) {
			case KeyEvent.VK_UP:
				player.bulletUp = true;
				
				player.bala = new Bala(player.getX() + (player.WIDTH/2),(player.getY()+player.HEIGHT / 2 -20),"up");
				player.bala.setIcon(icon);
				player.cargador.add(player.bala);
				Juego.ventana.paneles[Juego.ventana.panelActual].add(player.bala);
				
				player.disparando = true;
			break;
			case KeyEvent.VK_DOWN:
				player.bulletDown = true;
				
				player.bala = new Bala(player.getX() + (player.WIDTH/2), (player.getY()+player.HEIGHT / 2 -20),"down");
				player.cargador.add(player.bala);
				Juego.ventana.paneles[Juego.ventana.panelActual].add(player.bala);
				
				player.disparando = true;
			break;
			case KeyEvent.VK_LEFT:
				player.bulletLeft = true;
				
				player.bala = new Bala(player.getX() + (player.WIDTH/2), (player.getY()+player.HEIGHT / 2 -20),"left");
				player.cargador.add(player.bala);
				Juego.ventana.paneles[Juego.ventana.panelActual].add(player.bala);
				
				player.disparando = true;
			break;
			case KeyEvent.VK_RIGHT:
				player.bulletRight = true;
				
				player.bala = new Bala(player.getX() + (player.WIDTH/2), (player.getY()+player.HEIGHT / 2 -20),"right");
				player.cargador.add(player.bala);
				Juego.ventana.paneles[Juego.ventana.panelActual].add(player.bala);
				
				player.disparando = true;
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