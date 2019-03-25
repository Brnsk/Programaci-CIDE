package snake;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel{
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(Juego.ventana != null) {
			Juego.ventana.paint((Graphics2D) g);
		}
	}
}
