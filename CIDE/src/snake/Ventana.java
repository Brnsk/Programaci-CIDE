package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Ventana extends JFrame implements KeyListener{
	private static int WIDTH = 800;
	private static int HEIGHT = 800;
	private static Panel panel;
	private int key;
	
	
	
	public Ventana() {
		iniciarVentana();
	}
	
	//Llamar al metodo repaint cada cierto tiempo
	Timer timer = new Timer(50,new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Juego.ventana.repaint();
		}
	});
	
	private void iniciarVentana() {
		panel = new Panel();
		
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(panel);
		this.addKeyListener(this);
		timer.start();
	}
	
	
	
	public void paint(Graphics2D g) {
		//Primera pintada
		if(!Juego.start) {
			for(int i = 0 ; i < Snake.largo*17; i+=17) {
				g.setColor(Color.black);
				g.drawRect(Snake.x, Snake.y-i, 15, 15);
				g.setColor(Color.GRAY);
				g.fillRect(Snake.x, Snake.y-i, 15, 15);
			}
		}
		
		//Cambiar direccion dependiendo de la tecla
		switch(key) {
		case KeyEvent.VK_UP:
			if(!Juego.gameover) {
				Juego.start = true;
			}
			for(int i = 0 ; i < Snake.largo*17; i+=17) {
				
				Snake.y = Snake.y - 10;
				
				g.setColor(Color.black);
				g.drawRect(Snake.x, Snake.y+i, 15, 15);
				g.setColor(Color.GRAY);
				g.fillRect(Snake.x, Snake.y+i, 15, 15);
			}
			
			break;
		case KeyEvent.VK_DOWN:
			if(!Juego.gameover) {
				Juego.start = true;
			}
			for(int i = 0 ; i < Snake.largo*17; i+=17) {
				
				Snake.y = Snake.y + 3;
				
				g.setColor(Color.black);
				g.drawRect(Snake.x, Snake.y-i, 15, 15);
				g.setColor(Color.GRAY);
				g.fillRect(Snake.x, Snake.y-i, 15, 15);
			}
			
			break;
		case KeyEvent.VK_RIGHT: 
			if(!Juego.gameover) {
				Juego.start = true;
			}
			for(int i = 0 ; i < Snake.largo*17; i+=17) {
				
				Snake.x = Snake.x + 3;
				
				g.setColor(Color.black);
				g.drawRect(Snake.x-i, Snake.y, 15, 15);
				g.setColor(Color.GRAY);
				g.fillRect(Snake.x-i, Snake.y, 15, 15);
			}
			
			break;
		case KeyEvent.VK_LEFT:
			if(!Juego.gameover) {
				Juego.start = true;
			}
			for(int i = 0 ; i < Snake.largo*17; i+=17) {
				
				Snake.x = Snake.x - 3;
				
				g.setColor(Color.black);
				g.drawRect(Snake.x +i, Snake.y, 15, 15);
				g.setColor(Color.GRAY);
				g.fillRect(Snake.x+i, Snake.y, 15, 15);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();

		Juego.ventana.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}