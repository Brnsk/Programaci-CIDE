package game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer.UIResource;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class Menu extends JPanel {


	private static DefaultListModel<String> opciones = new DefaultListModel<String>();	
	private static JList<String> lista = new JList<String>();
	JLabel img = new JLabel();
	static UIResource posicion = new UIResource();

	private String jugar = "Jugar";
	private String salir = "Sortir";
	
	protected static Image fondo;
	
	private final String FONDOURL = "img\\menufondo.png";

	//CONSTRUCTOR
	protected Menu() {
		jlist();
		imagen();
		opciones.addElement(jugar);
		opciones.addElement(salir);

		this.setBackground(new java.awt.Color(152, 119, 103));
		this.setLayout(null);
		this.setOpaque(true);

		lista.setModel(opciones);	
		this.add(lista);
	}
	
	protected void imagen() {
		fondo = new ImageIcon(FONDOURL).getImage();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fondo, 0, 0, Ventana.WIDTH, Ventana.HEIGHT, null);
	}
	
	//Crear el JList
	private static void jlist(){
		
		// Tipo de lista.
		lista.setSize(new Dimension(700,400));
		lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lista.setLayoutOrientation(JList.VERTICAL);

		// Colores y fuente.
		lista.setFont(new Font("04b",Font.BOLD,80));
		//lista.setForeground(new java.awt.Color(58, 58, 58));
		//lista.setSelectionForeground(new java.awt.Color(114, 18, 18));
		//lista.setSelectionBackground(new java.awt.Color(152, 119, 103));
		//lista.setBackground(new java.awt.Color(152, 119, 103));
		lista.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
		
		//lista.setOpaque(false);
		JScrollPane sp = new JScrollPane(lista);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(true);

		// Los textos se alinean al centro.
		posicion.setHorizontalAlignment(SwingConstants.CENTER);
		lista.setCellRenderer(posicion);

		// Hacer visible.
		lista.setLocation(267, 355);
		lista.setVisible(true);
		elegirOpcion();
	}	

	//Elegir Opcion en el menu
	private static void elegirOpcion() {
		lista.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				
				switch(e.getKeyCode()) {
					
				case KeyEvent.VK_ENTER: 
					Juego.start = true;
					Juego.ventana.menu.setVisible(false);
					Menu.lista.removeKeyListener(this);
					Menu.lista.setFocusable(false);
					Juego.ventana.menu.setFocusable(false);
					Juego.ventana.addPanel();
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
				}	
			}
		});
	}
}