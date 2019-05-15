package game;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class VictoryPanel extends Panel{
	
	protected static ImageIcon ICON;
	
	protected VictoryPanel() {
		iniciar();
		this.setVisible(true);
	}
	
	private void iniciar() {
		ICON = new ImageIcon(getClass().getResource("win.jpg"));
	}
}