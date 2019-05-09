package entidades;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Juego;
import game.Ventana;

@SuppressWarnings("serial")
public class Enemy extends JLabel implements JugadorEnemigos{
	//Fps de la clase triEnemy
	public int triFps = 0;
	
	public Image[] imagenes;
	protected ImageIcon icon;
	public int currImg;
	
	
	//Tamaño
	public static final int WIDTH = 95;
	public static final int HEIGHT = 95;
	
	//Coordenadas
	protected int x;
	protected int y;
	
	//Velocidad
	protected int speed;
	
	//Vida
	public int pv = 1;
	
	//Movimiento
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	public int name;
	
	public boolean colision = false;
	
	//Cargador de balas
	public ArrayList<Bala> cargador = new ArrayList<Bala>();
	public boolean disparando = false;
	
	//Spawn points
	private int[][] spawns = {{0,Ventana.WIDTH -50 - Enemy.WIDTH, 300,400,500},
							  {0,Ventana.HEIGHT-50 - Enemy.HEIGHT,300,400,500}};
	
	//CONSTRUCTOR
	public Enemy(int name) {
		iniciar();
		spawn();
		
		this.setIcon(icon);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLocation(x, y);
		this.name = name;
	}
	
	private void iniciar() {
		imagenes();
		
		speed = 5;
		
		//Movimiento
		up = false;
		down = false;
		left = false;
		right = false;
	}
	
	//Spawn aleatorio
	private void spawn() {
		this.x = this.spawns[0][(int)(Math.random()*5)];
		
		if(this.x == 150 || this.x == Ventana.WIDTH -150 - Enemy.WIDTH) {
			this.y = this.spawns[1][(int)(Math.random()*5)];
		}else {
			this.y = this.spawns[1][(int)(Math.random()*2)];
		}
	}
	
	protected void imagenes() {
		imagenes = new Image[11];
		
		//Preparar imagen principal
		imagenes[0] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadfront.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		icon = new ImageIcon(imagenes[0]);
		currImg = 0;
		
		//Caminando de frente paso1
		imagenes[1] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadFrontPaso1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		//Caminando de frente paso2
		imagenes[2] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadFrontPaso2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		//Caminando de espaldas paso1
		imagenes[3] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadBackPaso1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
				
		//Caminando de espaldas paso2
		imagenes[4] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadBackPaso2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		imagenes[5] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadEnemyright.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		imagenes[6] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadEnemyright1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		imagenes[7] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadEnemyright2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		imagenes[8] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadEnemyleft.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		imagenes[9] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadEnemyleft1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		imagenes[10] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\sadEnemy\\sadEnemyleft2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
	}

	@Override
	public void mover() {
		x = this.getX();
		y = this.getY();

		if(x < Ventana.WIDTH -50 - Enemy.WIDTH && y <= 0) {//Mover derecha
			right = true;
			
			left = false;
			up = false;
			down= false;
		}else if(y <= Ventana.HEIGHT - Enemy.HEIGHT  && x >= Ventana.WIDTH - 150 - Enemy.WIDTH) {//Mover abajo
			down = true;

			right = false;
			up = false;
			left = false;
		}else if(x > Enemy.WIDTH -50) {//Mover izquierda
			left = true;
			
			down = false;
			up = false;
			right= false;
		}else if(y > 50) {//Mover arriba
			up = true;
			
			left = false;
			right = false;
			down= false;
		}
		
		if(right && !left && !up && !down && !colision) {//derecha
			x += speed;
		}else if(!right && left && !up && !down && !colision) {//izquierda
			x -= speed;
		}else if(!right && !left && up && !down && !colision) {//arriba
			y -= speed;
		}else if(!right && !left && !up && down && !colision) {//abajo
			y += speed;
		}
		
		this.setLocation(x, y);
	}

	@Override
	public void moverDiagonal() {
		/*NOTHING*/
	}

	@Override
	public void disparar() {
		String direccion = "";
		
		if(this.x >= 985) {
			direccion = "left";
		}else if(this.x <= 150) {
			direccion = "right";
		}else if(this.y <= 150) {
			direccion = "down";
		}else if(this.y >= Ventana.HEIGHT - Enemy.HEIGHT) {
			direccion = "up";
		}
		
		if(!this.disparando) {
			cargador.add(new Bala(this.getX(),this.getY(),direccion));
			disparando = true;
		}
		
		//Mover la bala dependiendo de la direccion
		if(cargador.size() > 0) {
			
			for(int i = 0; i < cargador.size(); i++) {
				
				switch(cargador.get(i).direccion) {
				case "left":
					cargador.get(i).setIcon(new ImageIcon(cargador.get(i).img[4]));
					cargador.get(i).setLocation(cargador.get(i).getX() - 7, cargador.get(i).getY());
					Juego.ventana.paneles[Juego.ventana.panelActual].add(cargador.get(i));
					
					break;
				case "right":
					cargador.get(i).setIcon(new ImageIcon(cargador.get(i).img[4]));
					cargador.get(i).setLocation(cargador.get(i).getX() + 7, cargador.get(i).getY());
					Juego.ventana.paneles[Juego.ventana.panelActual].add(cargador.get(i));
					
					break;
				case "down":
					cargador.get(i).setIcon(new ImageIcon(cargador.get(i).img[4]));
					cargador.get(i).setLocation(cargador.get(i).getX(), cargador.get(i).getY() +7);
					Juego.ventana.paneles[Juego.ventana.panelActual].add(cargador.get(i));
					
					break;
				case "up":
					cargador.get(i).setIcon(new ImageIcon(cargador.get(i).img[4]));
					cargador.get(i).setLocation(cargador.get(i).getX(), cargador.get(i).getY() -7);
					Juego.ventana.paneles[Juego.ventana.panelActual].add(cargador.get(i));
				}
				
				//Eliminar la bala si sale del mapa o si impacta con el jugador
				if(cargador.get(i).getX() > Ventana.WIDTH +100 || cargador.get(i).getX() < -100 || 
						cargador.get(i).getY() > Ventana.HEIGHT +100 ||  cargador.get(i).getY() < -100) {
					
					cargador.get(i).setIcon(null);
					cargador.remove(i);
				}else if(cargador.get(i).getBounds().intersects(Juego.ventana.paneles[Juego.ventana.panelActual].player.getBounds())){
					if(!Juego.ventana.paneles[Juego.ventana.panelActual].player.inmunidad) {

						Player.pv--;
						cargador.get(i).setIcon(null);
						cargador.remove(i);
					}
				}else {
					moverBala();
				}
			}
		}
	}
	
	//Mover la baladel enemigo
	private void moverBala() {
		
	}
	
	@Override
	public void comprobarColision() {
		//boolean colision = false;
		
		for(int i = 0; i < Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.size(); i++) {
			for(int j = 0; j < Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.size(); j++) {
				
				Enemy enemigo = Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.get(i);
				Enemy otroEnemigo = Juego.ventana.paneles[Juego.ventana.panelActual].enemigos.get(j);
				
				Rectangle enemigoBounds;
				
				if(i == j) {
					continue;
				}else {
					//Hitbox del enemigpo
					enemigoBounds = enemigo.getBounds();
						
					if(enemigoBounds.intersects(otroEnemigo.getBounds())) {
						enemigo.colision = true;
						otroEnemigo.colision = true;
						colisionando(enemigo, otroEnemigo);
					}else {
						enemigo.colision = false;
						otroEnemigo.colision = false;
					}
				}
			}
		}
	}


	public void colisionando(Enemy enemigo, Enemy otroEnemigo) {
		//Colision entre enemigos ==========================================
		//Enemigo
		if(enemigo.up && !enemigo.left && !enemigo.right && !enemigo.down) {//Enviar hacia abajo
			enemigo.setLocation(enemigo.x, enemigo.y + this.speed );
			
		}else if(enemigo.left && !enemigo.up && !enemigo.right && !enemigo.down) {//Enviar hacia la derecha
			enemigo.setLocation(enemigo.x + this.speed  , enemigo.y);
			
		}else if(enemigo.right && !enemigo.left && !enemigo.up && !enemigo.down) {//Enviar hacia la izquierda
			enemigo.setLocation(enemigo.x  - this.speed , enemigo.y);
			
		}else if(enemigo.down && !enemigo.left && !enemigo.right && !enemigo.up) {//Enviar hacia arriba
			enemigo.setLocation(enemigo.x, enemigo.y - this.speed );
			
		}else if(enemigo.up && enemigo.left && !enemigo.right && !enemigo.down) {//Enviar abajo a la derecha
			enemigo.setLocation(enemigo.x + this.speed , enemigo.y + this.speed );
			
		}else if(enemigo.up && !enemigo.left && enemigo.right && !enemigo.down) {//Enviar abajo a la izquierda
			enemigo.setLocation(enemigo.x - this.speed , enemigo.y + this.speed );
			
		}else if(!enemigo.up && enemigo.left && !enemigo.right && enemigo.down) {//Enviar arriba a la derecha
			enemigo.setLocation(enemigo.x + this.speed , enemigo.y - this.speed );
			
		}else if(!enemigo.up && !enemigo.left && enemigo.right && enemigo.down) {//Enviar arriba a la izquierda
			enemigo.setLocation(enemigo.x - this.speed , enemigo.y - this.speed );
		}
		
		//Otro enemigo
		if(otroEnemigo.up && !otroEnemigo.left && !otroEnemigo.right && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x, otroEnemigo.y - this.speed);
			
		}else if(otroEnemigo.left && !otroEnemigo.up && !otroEnemigo.right && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x - this.speed , otroEnemigo.y);
			
		}else if(otroEnemigo.right && !otroEnemigo.left && !otroEnemigo.up && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x  + this.speed , otroEnemigo.y);
			
		}else if(otroEnemigo.down && !otroEnemigo.left && !otroEnemigo.right && !otroEnemigo.up) {
			otroEnemigo.setLocation(otroEnemigo.x, otroEnemigo.y + this.speed);
			
		}else if(otroEnemigo.up && otroEnemigo.left && !otroEnemigo.right && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x - this.speed, otroEnemigo.y - this.speed);
			
		}else if(otroEnemigo.up && !otroEnemigo.left && otroEnemigo.right && !otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x + this.speed, otroEnemigo.y - this.speed);
			
		}else if(!otroEnemigo.up && otroEnemigo.left && !otroEnemigo.right && otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x - this.speed, otroEnemigo.y + this.speed);
			
		}else if(!otroEnemigo.up && !otroEnemigo.left && otroEnemigo.right && otroEnemigo.down) {
			otroEnemigo.setLocation(otroEnemigo.x + this.speed, otroEnemigo.y + this.speed);
		}
	}
}