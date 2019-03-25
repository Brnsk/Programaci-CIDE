package interficiesGrafiques;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MiClaseVentana extends JFrame {
	
	public MiClaseVentana(String title) {
		
		
		this.setTitle(title);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);//Centrar la ventana
		//this.setBounds(700,300,500,500);      setLocation + setSize (x,y,x,y) Centrar siempre?
		this.setVisible(true);
		
		this.agregarPanel();//Llamar al metodo para añadir el panel
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void agregarPanel() {
		JPanel panel = new JPanel();
		
		//panel.setBackground(Color.green);
		
		this.getContentPane().add(panel);
	}
}
