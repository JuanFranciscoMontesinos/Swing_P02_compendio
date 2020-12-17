/**
 * swing_c_p02_montesinosParraJuanFrancisco
 * 25 nov. 2020 8:26:14
 */
package swing_c_p02_montesinosParraJuanFrancisco;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * Clase para iniciar la ventana creando el menubar y llamando al panel
 * prinicipal
 *
 * @author Juan
 */
@SuppressWarnings("serial")
public class Ventana extends JFrame implements ActionListener {

	/** The menu bar. */
	JMenuBar menuBar;

	/** JMenus */
	JMenu menuArch, menuRegistro, menuAyuda;

	/** JMenuItems */
	public JMenuItem menuItSalir, menuItAlta, menuItBaja, menuItInfo;

	/**
	 * Instantiates a new ventana.
	 */
	public Ventana() {
		this.setTitle("Gestion Hotel JFMP");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/recursos/anagrama.png")).getImage());
		// Obtener tamaño de la pantalla//
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanioPantalla = new Dimension();
		tamanioPantalla = miPantalla.getScreenSize();
		int altoPantalla = tamanioPantalla.height;
		int anchoPantalla = tamanioPantalla.width;
		// Obtener tamaño de la pantalla//

		// Posicion y tamaño de la ventana//
		this.setSize(anchoPantalla / 2, altoPantalla / 2);
		this.setLocationRelativeTo(null);
		// Posicion y tamaño de la ventana//

		// Crear barra de la ventana
		barraMenu();

		// Crear el panel
		this.add(new Panel());
	}

	/**
	 * Barra menu.
	 */
	void barraMenu() {
		// Se crea la barra de menú
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		// Menús principales de la barra de menú
		menuArch = new JMenu("Archivo");
		menuRegistro = new JMenu("Registro");
		menuAyuda = new JMenu("Ayuda");

		// Botón del menú de salida de la barra de menú
		menuItSalir = new JMenuItem("Salir");
		menuItSalir.addActionListener(this);
		menuArch.add(menuItSalir);

		// Menu items del menú de registro
		menuItAlta = new JMenuItem("Alta Reservas");
		menuItAlta.addActionListener(this);
		menuItBaja = new JMenuItem("Baja Reservas");
		menuItBaja.addActionListener(this);

		// Se añaden los menuitems al menú de registro
		menuRegistro.add(menuItAlta);
		menuRegistro.add(menuItBaja);
		menuRegistro.setMnemonic(KeyEvent.VK_R);

		// Se crea menú de ayuda
		menuItInfo = new JMenuItem("Acerca de...");
		menuItInfo.addActionListener(this);
		menuAyuda.add(menuItInfo);

		// Se añaden todos los menús a la barra de menú
		menuBar.add(menuArch);
		menuBar.add(menuRegistro);
		menuBar.add(menuAyuda);
	}

	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuItSalir) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}

		if (e.getSource() == menuItBaja) {
			JOptionPane.showMessageDialog(null, "Aún no esta desarrollada la opción", "Aplicación en desarrollo",
					JOptionPane.WARNING_MESSAGE);
		}

		if (e.getSource() == menuItInfo) {
			JOptionPane.showMessageDialog(null,
					"Nombre de la empresa: JFMP\nPara crear una reserva nueva pulsar el botón verde, para eliminar, pulsar el botón rojo."
							+ "\nPara imprimir el documento, pulsar el primer boton en la interfaz de inserción de reservas.\nSegundo botón para crear una nueva reserva"
							+ "\nTercer botón para guardar la reserva y continuar en un futuo.\nCuarto botón salir de la ventana para añadir reservas."
							+ "\n\nDesarrollado por: Juan Francisco Montesinos Parra",
					"Mensaje de información", JOptionPane.INFORMATION_MESSAGE);
		}
		if (e.getSource() == menuItAlta) {
			DialogoAlta altaReservas = new DialogoAlta(this, true);
			altaReservas.setVisible(true);
		}

	}
}
