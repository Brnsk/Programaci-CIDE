package entidades;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.Juego;

@SuppressWarnings("serial")
public class TriEnemy extends Enemy{
	//CONSTRUCTOR
	public TriEnemy(int name) {
		super(name);
		this.name = this.name + 20;
		this.speed = 2;
		this.x = this.x;
		this.y = this.y + 300;
	}
	
	@Override
	protected void imagenes() {
		this.imagenes = new Image[9];
		
		//Imagen de frente con el boss arriba
		this.imagenes[0] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\triEnemy\\triEnemyFrontTop.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		this.icon = new ImageIcon(imagenes[0]);
		this.currImg = 0;
		
		//Imagen de frente con el boss abajo
		this.imagenes[1] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\triEnemy\\triEnemyFrontBottom.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		//Imagen de frente con con todos en linea
		this.imagenes[2] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\triEnemy\\triEnemyFrontMiddle.png").getImage().getScaledInstance(WIDTH, HEIGHT / 2, Image.SCALE_SMOOTH);
		
	}
	
	@Override
	public void mover() {
		x = this.getX();
		y = this.getY();
		
		if(x < Juego.ventana.paneles[Juego.ventana.panelActual].player.getX()) {
			//mover derecha
			right = true;
			
			left = false;
			up = false;
			down= false;
			
		}else if(x > Juego.ventana.paneles[Juego.ventana.panelActual].player.getX()) {
			//mover izquierda
			left = true;
			
			right = false;
			up = false;
			down= false;
		}else if(y < Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()) {
			//Mover abajo
			down = true;
			
			left = false;
			up = false;
			right= false;
		}else if(y > Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()) {
			//Mover arriba
			up = true;
			
			left = false;
			right = false;
			down= false;
		}
		
		if(right && !left && !up && !down) {//derecha
			x += speed;
		}else if(!right && left && !up && !down) {//izquierda
			x -= speed;
		}else if(!right && !left && up && !down) {//arriba
			y -= speed;
		}else if(!right && !left && !up && down) {//abajo
			y += speed;
		}
	}

	@Override
	public void moverDiagonal() {
		if(x > Juego.ventana.paneles[Juego.ventana.panelActual].player.getX() && y > Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()){
			//arriba izquierda
			up = true;
			left = true;
			
			right = false;
			down= false;
		}else if(x < Juego.ventana.paneles[Juego.ventana.panelActual].player.getX() && y > Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()){
			//arriba derecha
			up = true;
			right = true;
			
			left = false;
			down= false;
		}else if (x > Juego.ventana.paneles[Juego.ventana.panelActual].player.getX() && y < Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()){
			//abajo izquierda
			down = true;
			left = true;
			
			right = false;
			up = false;
		}else if(x < Juego.ventana.paneles[Juego.ventana.panelActual].player.getX() && y < Juego.ventana.paneles[Juego.ventana.panelActual].player.getY()){
			//abajo derecha
			down = true;
			right = true;
			
			left = false;
			up = false;
		}
		
		this.comprobarColision();
		
		if(right && !left && up && !down) {//arriba derecha
			y -= speed-1;
			x += speed-1;
		}else if(!right && left && up && !down) {//arriba izquierda
			y -= speed-1;
			x -= speed-1;
		}else if(!right && left && !up && down) {//abajo izquierda
			y += speed-1;
			x -= speed-1;
		}else if(right && !left && !up && down) {//abajo derecha
			y += speed-1;
			x += speed-1;
		}
		
		this.setLocation(x, y);
	}
}