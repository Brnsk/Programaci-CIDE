package entidades;

import java.awt.Image;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class TriEnemy extends Enemy{
	//CONSTRUCTOR
	public TriEnemy(int name) {
		super(name);
		this.name = this.name + 20;
	}
	
	@Override
	protected void imagenes() {
		this.imagenes = new Image[6];
		
		//Imagen de frente con el boss arriba
		this.imagenes[0] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\triEnemy\\triEnemyFrontTop.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		this.icon = new ImageIcon(imagenes[0]);
		this.currImg = 0;
		
		//Imagen de frente con el boss abajo
		this.imagenes[1] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\triEnemy\\triEnemyFrontBottom.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		//Imagen de frente con el boss arriba con 2 vidas
		this.imagenes[2] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\triEnemy\\triEnemyFrontTop1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		//Imagen de frente con el boss arriba con 1 vida
		this.imagenes[3] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\triEnemy\\triEnemyFrontTop2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
				
		//Imagen de frente con el boss abajo con 2 vidas
		this.imagenes[4] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\triEnemy\\triEnemyFrontBottom1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		//Imagen de frente con el boss abajo con 1 vidas
		this.imagenes[5] = new ImageIcon("D:\\git\\repository\\NitArt\\img\\triEnemy\\triEnemyFrontBottom2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
	}
}