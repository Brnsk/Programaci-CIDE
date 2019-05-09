package game;

import java.awt.Image;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class GameOverPanel extends Panel{
	
	protected static Image gover;
	
	protected GameOverPanel() {
		iniciar();
		this.setVisible(true);
	}
	
	private void iniciar() {
		gover = new ImageIcon("img\\lose.jpg").getImage();
	}
}