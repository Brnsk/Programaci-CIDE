package game;

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
	
	public ArrayList<Boss> bossList = new ArrayList<Boss>();
	
	protected Image suelo;
	protected static Image pausa;
	protected static Image pausav;
	protected static Image pausab;
	
	protected Image suelo2;

	protected Image fondo;
	
	protected String sueloUrl = "img\\Transparente.png";
	protected String suelo2Url = "img\\suelo2.png";
	
	protected String pausaUrl = "img\\Tezto.png";
	protected String pausavUrl;
	protected String pausabUrl = "img\\boton.png";
	
	protected String fondoUrl = "img\\fondo.jpg";
	
	
	public Boss boss;
	
	Corazones[] corazones;
	private int contador_pausa;
	
	protected static int vidas = 6;
	
	
	
	//CONSTRUCTOR
	protected Panel() {
		iniciar();
		
		this.setLayout(null);
		
		this.setFocusable(true);
		this.addKeyListener(this);
		
		this.addEnemies();
		
		this.addVidas();
	}
	
	//METODOS PROPIOS DE LA CLASE
	private void iniciar() {
		
		//Añadir los corazones
		corazones = new Corazones[6];
		
		//Añadir entidades
		if(Juego.ventana.panelActual <= 0 && !Juego.gameover) {
			player = new Player();
			this.add(player);
		}else if(!Juego.gameover){
			player = Juego.ventana.paneles[Juego.ventana.panelActual-1].player;
			this.add(player);
			
			player.setLocation(1000,400);
			
			player.up = false;
			player.down = false;
			player.right = false;
			player.left = false;
		}
		
		suelo = new ImageIcon(sueloUrl).getImage();
		suelo2 = new ImageIcon(suelo2Url).getImage();
		fondo = new ImageIcon(fondoUrl).getImage();
		pausa = new ImageIcon(pausaUrl).getImage(); 
		pausab = new ImageIcon(pausabUrl).getImage();
	}
	
	//Añadir enemigos
	private void addEnemies() {

		if (Juego.ventana.panelActual == 0 && !Juego.gameover) {//A partir del primer panel con enemigos
			int random = (int)(Math.random()*2 +1);
			
			for(int i = 0; i < random; i++) {
				enemy = new Enemy(i);
				enemigos.add(enemy);
				this.add(enemy);
			}
		}else if(Juego.ventana.panelActual >= 1 && Juego.ventana.panelActual < 3 && !Juego.gameover) {
			int random = (int)(Math.random()*3 +1);
			
			for(int i = 0; i < random; i+=2) {
				enemy = new Enemy(i);
				enemigos.add(enemy);
				
				triEnemy = new TriEnemy(i);
				enemigos.add(triEnemy);
				
				this.add(enemy);
				this.add(triEnemy);
			}
		}else if(Juego.ventana.panelActual >= 3 && Juego.ventana.panelActual < 6 && !Juego.gameover) {
			int random = (int)(Math.random()*4 +2);
			
			for(int i = 0; i < random; i+=2) {
				enemy = new Enemy(i);
				enemigos.add(enemy);
				
				triEnemy = new TriEnemy(i);
				enemigos.add(triEnemy);
				
				this.add(enemy);
				this.add(triEnemy);
			}
		}else if(Juego.ventana.panelActual >= 6 && Juego.ventana.panelActual < 9 && !Juego.gameover) {
			int random = (int)(Math.random()*5 +2);
			
			for(int i = 0; i < random; i+=2) {
				enemy = new Enemy(i);
				enemigos.add(enemy);
				
				triEnemy = new TriEnemy(i);
				enemigos.add(triEnemy);
				
				this.add(enemy);
				this.add(triEnemy);
			}
		}else if(Juego.ventana.panelActual == 9 && !Juego.gameover){
			boss = new Boss();
			
			bossList.add(boss);
			
			this.add(boss);
		}
	}
	
	protected void addVidas() {
		System.out.println(vidas);
		if(Juego.ventana.panelActual <= 10) {
			for(int i = 0; i < vidas; i++) {
				corazones[i] = new Corazones();
				Corazones.x += 70;
				this.add(corazones[i]);
			}
		}
	}
	
	protected void removeVidas() {
		System.out.println(vidas);
		if(Juego.ventana.panelActual <= 10) {
			corazones[vidas].setVisible(false);
			this.remove(corazones[vidas]);
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
			
			Juego.pause=true;
			contador_pausa ++;
			
			if (contador_pausa == 2) {
				System.exit(0);
			}
			
			Juego.ventana.paneles[Juego.ventana.panelActual].repaint();
			suelo = new ImageIcon("img\\Transparente_pausa.png").getImage();
		break;
		case KeyEvent.VK_ENTER:
			
			Juego.pause=false;
			contador_pausa = 0;
			Juego.ventana.paneles[Juego.ventana.panelActual].repaint();
			suelo = new ImageIcon("img\\Transparente.png").getImage();
		}
		//=========== Balas ===============
		
		if(!player.disparando) {
			switch(key) {
			case KeyEvent.VK_UP:
				player.bulletUp = true;
				
				player.bala = new Bala(player.getX() + (player.WIDTH/2),(player.getY()+player.HEIGHT / 2 -20),"up");
				
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
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}