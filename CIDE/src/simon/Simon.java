package simon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Simon implements MouseListener{
	protected static Simon simon;
	private Panel panel;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	private static final String TITLE = "Simon";

	private static int ticks = 0;
	protected static int flashed = 0;
	private static boolean gameover = false;
	private static boolean start = false;
	private static boolean patron = false;
	private static ArrayList<Integer> pattern = new ArrayList<Integer>();

	//CONSTRUCTOR
	public Simon() {
		JFrame frame = new JFrame(TITLE);

		panel = new Panel();

		frame.setVisible(true);//Hacerla visible
		frame.setSize(WIDTH, HEIGHT+35);//Establecer tamaño
		frame.setResizable(false);//Quitar la redimensionizacion
		frame.setLocationRelativeTo(null);//Centrar la ventana en la pantalla
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Cerrar aplicacion al cerrar ventana
		frame.add(panel);//Añadir Jpanel de la clase Panel
		frame.addMouseListener(this);
		}


	//Crear random
	private int createRandom(){
		int random;

		random = (int)(Math.random()*4+1);

		return random;
	}

	//Crear patron
	protected void createPattern() {
		int random = createRandom();

		pattern.add(random);

		for(int i = 0;i < pattern.size(); i++) {
			flashed = random;
			panel.repaint();

			flashed = 0;
			panel.repaint();
		}
	}

	public void paint(Graphics2D g) {
		if(flashed == 1) {
			g.setColor(Color.PINK);
			g.fillRect(0, 0, WIDTH / 2, HEIGHT / 2);
		}else {
			g.setColor(Color.PINK.darker());
			g.fillRect(0, 0, WIDTH / 2, HEIGHT / 2);
		}

		if(flashed == 2) {
			g.setColor(Color.BLUE);
			g.fillRect(WIDTH / 2, 0, WIDTH / 2, HEIGHT / 2);
		}else {
			g.setColor(Color.BLUE.darker());
			g.fillRect(WIDTH / 2, 0, WIDTH / 2, HEIGHT / 2);
		}

		if(flashed == 3) {
			g.setColor(Color.YELLOW);
			g.fillRect(0, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
		}else {
			g.setColor(Color.YELLOW.darker());
			g.fillRect(0, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
		}

		if(flashed == 4) {
			g.setColor(Color.GREEN);
			g.fillRect(WIDTH / 2, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
		}else {
			g.setColor(Color.GREEN.darker());
			g.fillRect(WIDTH / 2, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
		}
		
		System.out.println(flashed);
	}

	//Esperar 1 seg
	private void unSeg() {
		try {
			Thread.sleep(1000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Comprobar si se ha hecho el click correcto
	private boolean checkPressed(int flashed) {
		boolean correcte = false;
		
		if(flashed == pattern.get(ticks)) {
			correcte = true;
			ticks++;
		}else {
			correcte = false;
			ticks = 0;
		}
		
		return correcte;
	}

	private void comprobarRonda() {
		if(checkPressed(flashed)) {
			pintarFosc();
			System.out.println("Acierto");
		}else {
			gameover = true;
			System.out.println("Has perdido");
		}
	}
	
	private void pintarFosc() {
		flashed = 0;
		panel.repaint();
	}

	//--------------------MOUSELISTENER---------------------------------------------------
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(x < WIDTH / 2 && x > 0 && y > 0 && y < HEIGHT / 2 +32) {
			flashed = 1;
		}else if(x > WIDTH / 2 && y > 0 && y < HEIGHT / 2 +32) {
			flashed = 2;
		}else if(x > 0 && x < WIDTH / 2 && y > HEIGHT / 2 +32) {
			flashed = 3;
		}else if(x > WIDTH / 2 && y > HEIGHT / 2 +32) {
			flashed = 4;
		}

		panel.repaint();
		comprobarRonda();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}