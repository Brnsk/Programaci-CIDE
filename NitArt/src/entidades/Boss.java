package entidades;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Juego;
import game.Ventana;

@SuppressWarnings("serial")
public class Boss extends JLabel{
	public ImageIcon[] imagenes;
	protected ImageIcon icon;
	public int currImg;
	
	//Tama�o
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	//Coordenadas
	protected int x = 300;
	public int y = 0;
	
	//Velocidad
	public static int speed;
	
	//Movimiento
	boolean izquierda = false;
	boolean derecha = true;
	
	public static int cambio = 0;
	
	//Puntos de vida
	public static int pv = 30;
	
	//Skill
	private int enemyType = 0;
	
	public Boss() {
		iniciar();
		
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setIcon(icon);
		this.setLocation(200, 0);
	}
	
	private void iniciar() {
		imagenes();
		speed = 4;
	}
	
	private void imagenes() {
		imagenes = new ImageIcon[8];
		
		//Imagen por defecto
		imagenes[0] = new ImageIcon(new ImageIcon(getClass().getResource("bossChargeNo.png")).getImage().getScaledInstance(Boss.WIDTH, Boss.HEIGHT, Image.SCALE_SMOOTH));
		icon = new ImageIcon(imagenes[0].getImage());
		currImg = 0;
		
		imagenes[1] = new ImageIcon(new ImageIcon(getClass().getResource("bossChargeNoA.png")).getImage().getScaledInstance(Boss.WIDTH, Boss.HEIGHT, Image.SCALE_SMOOTH));
		
		imagenes[2] = new ImageIcon(new ImageIcon(getClass().getResource("bossChargingLow.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
		
		imagenes[3] = new ImageIcon(new ImageIcon(getClass().getResource("bossChargingLowA.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
		
		imagenes[4] = new ImageIcon(new ImageIcon(getClass().getResource("bossChargingWarning.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
		
		imagenes[5] = new ImageIcon(new ImageIcon(getClass().getResource("bossChargingWarningA.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
		
		imagenes[6] = new ImageIcon(new ImageIcon(getClass().getResource("bossShooting6.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
		
		imagenes[7] = new ImageIcon(new ImageIcon(getClass().getResource("bossShooting6A.png")).getImage().getScaledInstance(WIDTH, HEIGHT +560, Image.SCALE_SMOOTH));
	}
	
	//Movimiento
	public void mover() {
		
		if(this.derecha) {
			
			if(this.x > Ventana.WIDTH - Boss.WIDTH) {
				this.derecha = false;
				this.izquierda = true;
			}else {
				this.x += Boss.speed;
			}
			
		}else if(this.izquierda) {
			
			if(this.x < 0) {
				this.derecha = true;
				this.izquierda = false;
			}else {
				this.x -= Boss.speed;
			}
		}
		
		this.setLocation(this.x , this.y);
	}
	
	//Colision con una bala
	public void colision() {
		
		Rectangle bossBounds = this.getBounds();
		
		bossBounds.width = (int)bossBounds.getWidth() - 400;
		bossBounds.height =(int) bossBounds.getHeight() - 200;
		bossBounds.x = (int)bossBounds.getX() + 200;
		
		if(this.currImg == 7) {
			bossBounds.height =(int) bossBounds.getHeight() - 570;
		}
		
		for(int i = 0; i < Juego.ventana.paneles[Juego.ventana.panelActual].player.cargador.size(); i++) {
			if(Juego.ventana.paneles[Juego.ventana.panelActual].player.cargador.get(i).getBounds().intersects(bossBounds) && Boss.pv > 0) {
				Juego.ventana.paneles[Juego.ventana.panelActual].player.eliminarBala(i);
				Boss.pv--;
			}
		}
	}
	
	//Skill del boss que spawnea enemigos
	public void spawnEnemy() {
		if(enemyType == 0) {
			
			Enemy enemy = new Enemy(1);
			Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.add(enemy);
			Juego.ventana.paneles[Juego.ventana.panelActual].add(enemy);
			
			enemyType++;
		}else {
			
			TriEnemy enemy = new TriEnemy(22);
			Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.add(enemy);
			Juego.ventana.paneles[Juego.ventana.panelActual].add(enemy);
			
			enemyType = 0;
		}
	}
}