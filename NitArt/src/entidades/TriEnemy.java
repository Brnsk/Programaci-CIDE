package entidades;

import java.awt.Image;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class TriEnemy extends Enemy{
	//CONSTRUCTOR
	public TriEnemy(int name) {
		super(name);
		this.name = this.name + 20;
		this.speed = 2;
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
}