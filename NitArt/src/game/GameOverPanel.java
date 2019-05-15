package game;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class GameOverPanel extends Panel{
	
	protected static ImageIcon ICON;
	
	protected GameOverPanel() {
		iniciar();
		this.setVisible(true);
	}
	
	private void iniciar() {
		ICON = new ImageIcon(getClass().getResource("lose.jpg"));
	}
}