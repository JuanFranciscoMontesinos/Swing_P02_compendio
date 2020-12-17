/**
 * swing_c_p02_montesinosParraJuanFrancisco
 * 25 nov. 2020 23:02:14
 */
package swing_c_p02_montesinosParraJuanFrancisco;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * Clase para crear el panel principal que solo contiene botones para llamar al dialogo principal del ejercicio
 *
 * @author Juan
 */
@SuppressWarnings("serial")
public class Panel extends JPanel implements ActionListener, ComponentListener {

	/** JButtons */
	JButton btAltaReservas, btBajaReservas;
	
	/** ImageIcons */
	ImageIcon icAdd, icRemove;
	
	/** Tamaño */
	int anchoVentana, altoVentana;

	/**
	 * Instantiates a new panel.
	 */
	public Panel() {
		//Obtener tamaño
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanioPantalla = new Dimension();
		tamanioPantalla = miPantalla.getScreenSize();
		int altoPantalla = tamanioPantalla.height;
		int anchoPantalla = tamanioPantalla.width;

		this.setLayout(new BorderLayout());

		//Panel para añadir los botones juntos y centrarlos en el panel principal
		JPanel botones = new JPanel();
		//Se crean las imágenes para añadirlos a los botones
		icAdd = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/add.png")).getImage()
				.getScaledInstance(anchoPantalla / 20, altoPantalla / 10, Image.SCALE_DEFAULT));
		btAltaReservas = new JButton(icAdd);
		btAltaReservas.addActionListener(this);
		btAltaReservas.setMnemonic(KeyEvent.VK_A);

		icRemove = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/remove.png")).getImage()
				.getScaledInstance(anchoPantalla / 20, altoPantalla / 10, Image.SCALE_DEFAULT));
		btBajaReservas = new JButton(icRemove);
		btBajaReservas.addActionListener(this);
		btBajaReservas.setMnemonic(KeyEvent.VK_B);

		//Se añaden los botones a su panel correspondiente y este al pricipal
		botones.add(btAltaReservas);
		botones.add(btBajaReservas);
		this.add(botones, BorderLayout.SOUTH);
		this.addComponentListener(this);
	}

	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btAltaReservas) {
			Ventana v = new Ventana();
			DialogoAlta altaReservas = new DialogoAlta(v, true);
			altaReservas.setVisible(true);
		}

		if (e.getSource() == btBajaReservas) {
			JOptionPane.showMessageDialog(null, "Aún no esta desarrollada la opción", "Aplicación en desarrollo",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	/**
	 * Metodo que se va a utilizar durante todo el ejercicio para que sea una aplicacion responsive
	 *
	 * @param e the e
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		anchoVentana = this.getSize().width;
		altoVentana = this.getSize().height;

		icAdd.setImage(new ImageIcon(getClass().getResource("/recursos/add.png")).getImage()
				.getScaledInstance(anchoVentana / 10, altoVentana / 5, Image.SCALE_DEFAULT));
		icRemove.setImage(new ImageIcon(getClass().getResource("/recursos/remove.png")).getImage()
				.getScaledInstance(anchoVentana / 10, altoVentana / 5, Image.SCALE_DEFAULT));

	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
}
