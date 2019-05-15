package entidades;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.Juego;
import game.Ventana;

@SuppressWarnings("serial")
public class TriEnemy extends Enemy{
	//CONSTRUCTOR
	public TriEnemy(int name) {
		super(name);
		this.name = this.name + 20;
		this.speed = 2;
		
		corregirPos();
		
		this.setLocation(this.x, this.y);
	}
	
	private void corregirPos() {
		//Corregir x
		if(this.x < Ventana.WIDTH - TriEnemy.WIDTH - 500) {
			this.x += 300;
		}else {
			this.x -= 300;
		}
		
		//Corregir y
		if(this.y < Ventana.HEIGHT - TriEnemy.HEIGHT- 500) {
			this.y += 300;
		}else {
			this.y -= 300;
		}
		
	}
	
	@Override
	protected void imagenes() {
		this.imagenes = new ImageIcon[9];
		
		//Imagen de frente con el boss arriba
		this.imagenes[0] = new ImageIcon(new ImageIcon(getClass().getResource("triEnemyFrontTop.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
		this.icon = new ImageIcon(imagenes[0].getImage());
		this.currImg = 0;
		
		//Imagen de frente con el boss abajo
		this.imagenes[1] = new ImageIcon(new ImageIcon(getClass().getResource("triEnemyFrontBottom.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH));
		
		//Imagen de frente con con todos en linea
		this.imagenes[2] = new ImageIcon(new ImageIcon(getClass().getResource("triEnemyFrontMiddle.png")).getImage().getScaledInstance(WIDTH, HEIGHT / 2, Image.SCALE_SMOOTH));
		
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
		
		if(right && !left && !up && !down && !colision) {//derecha
			x += speed;
		}else if(!right && left && !up && !down && !colision) {//izquierda
			x -= speed;
		}else if(!right && !left && up && !down && !colision) {//arriba
			y -= speed;
		}else if(!right && !left && !up && down && !colision) {//abajo
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
		
		if(right && !left && up && !down && !colision) {//arriba derecha
			y -= speed-1;
			x += speed-1;
		}else if(!right && left && up && !down && !colision) {//arriba izquierda
			y -= speed-1;
			x -= speed-1;
		}else if(!right && left && !up && down && !colision) {//abajo izquierda
			y += speed-1;
			x -= speed-1;
		}else if(right && !left && !up && down && !colision) {//abajo derecha
			y += speed-1;
			x += speed-1;
		}
		
		this.setLocation(x, y);
	}
	
	@Override
	public void disparar() {
		//Este enemigo no dispara
	}
}