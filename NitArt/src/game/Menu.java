package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel implements KeyListener {

	JLabel img = new JLabel();
	
	protected ImageIcon fondo = new ImageIcon(getClass().getResource("menufondo.png"));;

	//CONSTRUCTOR
	protected Menu() {
		this.setBackground(new java.awt.Color(152, 119, 103));
		this.setLayout(null);
		this.setOpaque(true);
		this.setFocusable(true);
		this.addKeyListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fondo.getImage(), 0, 0, Ventana.WIDTH, Ventana.HEIGHT, null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ENTER: 
			System.out.println("enter");
			Juego.start = true;
			Juego.ventana.menu.setVisible(false);
			Juego.ventana.menu.setFocusable(false);
			this.removeKeyListener(this);
			Juego.ventana.addPanel();
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}