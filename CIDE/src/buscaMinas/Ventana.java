package buscaMinas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Ventana extends JFrame implements MouseListener{
	private static final String TITLE = "Busca Minas";
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final int BORDER_SIZE = 40;
	private static final int FILL_SIZE = 39;
	private static int border_paddingx = 10;
	private static int border_paddingy = 10;
	private static int fill_paddingx = 11;
	private static int fill_paddingy = 11;
	private static int casilla = 0;
	private static int x,y;
	
	public Ventana() {
		BuscaMinas.panel = new Panel();
		
		this.setTitle(TITLE);
		this.setSize(WIDTH +20, HEIGHT +45);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().add(BuscaMinas.panel);
		
		BuscaMinas.panel.addMouseListener(this);
		BuscaMinas.panel.repaint();
	}

	public void paint(Graphics2D g) {
		
		while(border_paddingy < HEIGHT) {
			while(border_paddingx < WIDTH) {
				if(comprobaMina(casilla)) {
					
					System.out.println("Perdiste");
				}else {
					g.setColor(Color.black);
					g.drawRect(border_paddingx, border_paddingy, BORDER_SIZE, BORDER_SIZE);
					g.setColor(Color.pink);
					g.fillRect(fill_paddingx, fill_paddingy, FILL_SIZE, FILL_SIZE);
					
					border_paddingx += BORDER_SIZE +10;
					fill_paddingx += BORDER_SIZE +10;
				}
			}
			
			border_paddingx = 10;
			fill_paddingx = 11;
			border_paddingy += BORDER_SIZE + 10;
			fill_paddingy += BORDER_SIZE + 10;
		}
	}
	
	//Obtener la casilla
	private void obtenerCasilla() {
		int fila = 0;
		
		if (x < BORDER_SIZE + border_paddingx) {
			casilla = 1;
			fila = comprobarFilaCasilla(y);
			cambiaCasilla(fila);
		}else if((x > BORDER_SIZE + border_paddingx) && (x < BORDER_SIZE*2 + border_paddingx * 2)) {
			casilla = 2;
			fila = comprobarFilaCasilla(y);
			cambiaCasilla(fila);
		}else if((x > BORDER_SIZE * 2 + border_paddingx*2) && (x < BORDER_SIZE*3 + border_paddingx * 3)) {
			casilla = 3;
			fila = comprobarFilaCasilla(y);
			cambiaCasilla(fila);
		}else if((x > BORDER_SIZE * 3 + border_paddingx*3) && (x < BORDER_SIZE*4 + border_paddingx * 4)) {
			casilla = 4;
			fila = comprobarFilaCasilla(y);
			cambiaCasilla(fila);
		}else if((x > BORDER_SIZE * 4 + border_paddingx*4) && (x < BORDER_SIZE*5 + border_paddingx * 5)) {
			casilla = 5;
			fila = comprobarFilaCasilla(y);
			cambiaCasilla(fila);
		}else if((x > BORDER_SIZE * 5 + border_paddingx*5) && (x < BORDER_SIZE*6 + border_paddingx * 6)) {
			casilla = 6;
			fila = comprobarFilaCasilla(y);
			cambiaCasilla(fila);
		}else if((x > BORDER_SIZE * 6 + border_paddingx*6) && (x < BORDER_SIZE*7 + border_paddingx * 7)) {
			casilla = 7;
			fila = comprobarFilaCasilla(y);
			cambiaCasilla(fila);
		}else if((x > BORDER_SIZE * 7 + border_paddingx*7) && (x < BORDER_SIZE*8 + border_paddingx * 8)) {
			casilla = 8;
			fila = comprobarFilaCasilla(y);
			cambiaCasilla(fila);
		}else if((x > BORDER_SIZE * 8 + border_paddingx*8) && (x < BORDER_SIZE*9 + border_paddingx * 9)) {
			casilla = 9;
			fila = comprobarFilaCasilla(y);
			cambiaCasilla(fila);
		}else if((x > BORDER_SIZE * 9 + border_paddingx*9) && (x < BORDER_SIZE*10 + border_paddingx * 10)) {
			casilla = 10;
			fila = comprobarFilaCasilla(y);
			cambiaCasilla(fila);
		}
	}
	
	//Comprobar en que fila se encuentra el raton
	private int comprobarFilaCasilla(int y) {
		border_paddingy = 10;
		int fila = 0;
		
		if (y < BORDER_SIZE + border_paddingx) {
			fila = 1;
		}else if((y > BORDER_SIZE + border_paddingx) && (y < BORDER_SIZE*2 + border_paddingx * 2)) {
			fila = 2;
		}else if((y > BORDER_SIZE * 2 + border_paddingx*2) && (y < BORDER_SIZE*3 + border_paddingx * 3)) {
			fila = 3;
		}else if((y > BORDER_SIZE * 3 + border_paddingx*3) && (y < BORDER_SIZE*4 + border_paddingx * 4)) {
			fila = 4;
		}else if((y > BORDER_SIZE * 4 + border_paddingx*4) && (y < BORDER_SIZE*5 + border_paddingx * 5)) {
			fila = 5;
		}else if((y > BORDER_SIZE * 5 + border_paddingx*5) && (y < BORDER_SIZE*6 + border_paddingx * 6)) {
			fila = 6;
		}else if((y > BORDER_SIZE * 6 + border_paddingx*6) && (y < BORDER_SIZE*7 + border_paddingx * 7)) {
			fila = 7;
		}else if((y > BORDER_SIZE * 7 + border_paddingx*7) && (y < BORDER_SIZE*8 + border_paddingx * 8)) {
			fila = 8;
		}else if((y > BORDER_SIZE * 8 + border_paddingx*8) && (y < BORDER_SIZE*9 + border_paddingx * 9)) {
			fila = 9;
		}else if((y > BORDER_SIZE * 9 + border_paddingx*9) && (y < BORDER_SIZE*10 + border_paddingx * 10)) {
			fila = 10;
		}
		
		return fila;
	}
	
	//Cambia la casilla segun la fila en la que se encuentra
	private void cambiaCasilla(int fila) {
		
		switch(fila) {
		case 1: casilla += 0;
			break;
		case 2: casilla += 10;
			break;
		case 3: casilla += 20;
			break;
		case 4: casilla += 30;
			break;
		case 5: casilla += 40;
			break;
		case 6: casilla += 50;
			break;
		case 7: casilla += 60;
			break;
		case 8: casilla += 70;
			break;
		case 9: casilla += 80;
			break;
		case 10: casilla += 90;
			break;
		}
	}
	
	//Comprobar si se ha clicado encima de una mina
	private boolean comprobaMina(int casilla) {

		for(int i = 0; i < BuscaMinas.array_bombas.length;i++) {
			if(casilla == BuscaMinas.array_bombas[i]) {
				BuscaMinas.gameOver = true;
				BuscaMinas.panel.repaint();
			}else {
				BuscaMinas.gameOver = false;
			}
		}
		return BuscaMinas.gameOver;
	}
	
	//IMPLEMENTED METHODS FROM MOUSE LISTENER
	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		obtenerCasilla();
		BuscaMinas.panel.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}