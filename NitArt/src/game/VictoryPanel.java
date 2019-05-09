package game;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class VictoryPanel extends Panel{
	
	private URL url;
	protected static Image img;
	
	protected VictoryPanel() {
		iniciar();
		this.setVisible(true);
		
	}
	
	private void iniciar() {
		try {
			url = new URL("img\\win.jpg");
			img = new ImageIcon(url).getImage();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}