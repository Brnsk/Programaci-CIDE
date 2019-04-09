package game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer.UIResource;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Cursor;

public class Menu extends JPanel {


	private static DefaultListModel<String> opciones = new DefaultListModel();	
	private static JList lista = new JList();
	JLabel img = new JLabel();
	static UIResource posicion = new UIResource();

	private String jugar = "Jugar";
	private String creditos = "Crèdits";
	private String salir = "Sortir";

	//CONSTRUCTOR
	protected Menu() {
		opciones.addElement(jugar);
		opciones.addElement(creditos);
		opciones.addElement(salir);

		this.setBackground(new java.awt.Color(152, 119, 103));
		this.setLayout(null);

		jlist();
		lista.setModel(opciones);	
		this.add(lista);
	}
	
	//Crear el JList
	private static void jlist(){

		// Tipo de lista.
		lista.setSize(new Dimension(700,400));
		lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lista.setLayoutOrientation(JList.VERTICAL);

		// Colores y fuente.
		lista.setFont(new Font("04b",Font.BOLD,80));
		lista.setForeground(new java.awt.Color(58, 58, 58));
		lista.setSelectionForeground(new java.awt.Color(114, 18, 18));
		lista.setSelectionBackground(new java.awt.Color(152, 119, 103));
		lista.setBackground(new java.awt.Color(152, 119, 103));
		lista.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());

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
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					switch(lista.getSelectedIndex()) {
					
					case 0: 
						Juego.start = true;
						Juego.ventana.menu.setVisible(false);
						Juego.ventana.menu.lista.removeKeyListener(this);
						Juego.ventana.menu.lista.setFocusable(false);
						Juego.ventana.menu.setFocusable(false);
						Juego.ventana.addPanel();
						break;
					case 1:
						break;
					case 2:	
						System.exit(0);
						break;
					}	
				}
			}
		});
	}
}