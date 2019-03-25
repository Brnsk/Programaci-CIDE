package buscaMinas;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel{
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(BuscaMinas.ventana != null) {
			BuscaMinas.ventana.paint((Graphics2D) g);
		}
	}
}
