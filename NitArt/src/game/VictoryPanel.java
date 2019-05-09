package game;

import java.awt.Image;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class VictoryPanel extends Panel{
	
	protected static Image img;
	
	protected VictoryPanel() {
		iniciar();
		this.setVisible(true);
	}
	
	private void iniciar() {
		img = new ImageIcon("img\\win.jpg").getImage();
	}
}