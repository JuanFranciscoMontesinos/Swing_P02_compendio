/**
 * swing_c_p02_montesinosParraJuanFrancisco
 * 25 nov. 2020 23:28:54
 */
package swing_c_p02_montesinosParraJuanFrancisco;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import panelesDialogo.*;

/**
 * The Class DialogoAlta.
 *
 * @author Juan
 */
@SuppressWarnings("serial")
public class DialogoAlta extends JDialog implements ComponentListener, ActionListener {

	/** The layout. */
	GridBagLayout layout;

	/** The constraints. */
	GridBagConstraints constraints;

	/** JLabels */
	JLabel lblDatosP, lblDatosH, lblMenu;

	/** The tamanio pantalla. */
	Dimension tamanioPantalla;

	/** The p datos persona. */
	PanelDatosPersonales pDatosPersona;

	/** The p datos habitacion. */
	PanelDatosHabitacion pDatosHabitacion;

	/** The panel impresion. */
	PanelImpresion panelImpresion;

	/** Botones. */
	JButton btnImprimir, btnNuevo, btnSave, btnExit;

	/** ImageIcons. */
	ImageIcon icImprimir, icNuevo, icSave, icExit;

	/** Enteros. */
	int anchoVentana, altoVentana, restarDias = 0;

	/**
	 * Dialogo principal del ejercicio, que contine todos los paneles necesarios
	 * para la alta de habitaciones del hotel
	 *
	 * @param miVentana the mi ventana
	 * @param modal     the modal
	 */
	public DialogoAlta(Ventana miVentana, boolean modal) {
		super(miVentana, modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Tamaño de la pantalla y layout
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		tamanioPantalla = new Dimension();
		tamanioPantalla = miPantalla.getScreenSize();
		int altoPantalla = tamanioPantalla.height;
		int anchoPantalla = tamanioPantalla.width;
		layout = new GridBagLayout();
		this.setLayout(layout);
		constraints = new GridBagConstraints();

		this.setTitle("Alta Reservas");
		this.setLocation(0, 0);
		this.setSize(anchoPantalla, altoPantalla - (altoPantalla / 30));

		// Se crea y se añade el panel que contiene el título
		setTamano(GridBagConstraints.BOTH, 5, 0.25);
		this.addComponent(new PanelTitulo(), 0, 0, 5, 1);

		/*
		 * Se crea y se añade el panel que contiene la información personal, además de
		 * una label para indicar cual es la función del panel
		 */
		pDatosPersona = new PanelDatosPersonales();
		setTamano(GridBagConstraints.BOTH, 60, 0.25);
		lblDatosP = new JLabel("Datos personales", JLabel.CENTER);
		lblDatosP.setFont(new Font("Arial", Font.BOLD, anchoPantalla / 80));
		lblDatosP.setForeground(new Color(207, 240, 158));
		lblDatosP.setBackground(new Color(59, 134, 134));
		lblDatosP.setOpaque(true);
		this.addComponent(lblDatosP, 1, 0, 1, 1);
		setTamano(GridBagConstraints.BOTH, 60, 1.5);
		this.addComponent(pDatosPersona, 2, 0, 1, 1);

		// Se crea y se añade el panel que imprimirá los datos recogidos
		setTamano(GridBagConstraints.BOTH, 60, 2.5);
		panelImpresion = new PanelImpresion();
		this.addComponent(panelImpresion, 3, 0, 1, 1);
		setTamano(GridBagConstraints.BOTH, 1, 0);

		// Se crea una label vacía para crear una separación entre los paneles
		JLabel separacion1 = new JLabel("");
		separacion1.setBackground(Color.BLACK);
		separacion1.setOpaque(true);
		this.addComponent(separacion1, 1, 1, 1, 3);

		/*
		 * Se crea y se añade el panel que contiene la información de la habitación,
		 * además de una label para indicar cual es la función del panel
		 */
		setTamano(GridBagConstraints.BOTH, 60, 0.25);
		lblDatosH = new JLabel("Datos de la habitación", JLabel.CENTER);
		lblDatosH.setFont(new Font("Arial", Font.BOLD, anchoPantalla / 80));
		lblDatosH.setForeground(new Color(207, 240, 158));
		lblDatosH.setBackground(new Color(59, 134, 134));
		lblDatosH.setOpaque(true);
		this.addComponent(lblDatosH, 1, 2, 1, 1);
		setTamano(GridBagConstraints.BOTH, 1, 1);
		pDatosHabitacion = new PanelDatosHabitacion(pDatosPersona);
		this.addComponent(pDatosHabitacion, 2, 2, 1, 3);

		// Se crea una label vacía para crear una separación entre los paneles
		setTamano(GridBagConstraints.BOTH, 1, 0);
		JLabel separacion2 = new JLabel("");
		separacion2.setBackground(Color.BLACK);
		separacion2.setOpaque(true);
		this.addComponent(separacion2, 1, 3, 1, 3);

		// Se crea una label para indicar la zona de botones del dialogo
		setTamano(GridBagConstraints.BOTH, 60, 0.25);
		lblMenu = new JLabel("Menú", JLabel.CENTER);
		lblMenu.setFont(new Font("Arial", Font.BOLD, anchoPantalla / 80));
		lblMenu.setForeground(new Color(207, 240, 158));
		lblMenu.setBackground(new Color(59, 134, 134));
		lblMenu.setOpaque(true);
		this.addComponent(lblMenu, 1, 4, 1, 1);

		setTamano(GridBagConstraints.BOTH, 1, 1);

		// Se crea un panel para los botones en la misma clase que el diálogo//
		JPanel panelBotones = new JPanel();
		// El panel contiene 4 botones con sus respectivas imágenes
		panelBotones.setLayout(new GridLayout(4, 1, anchoPantalla / 90, anchoPantalla / 90));

		icImprimir = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imprimir.png")).getImage()
				.getScaledInstance(anchoPantalla / 35, altoPantalla / 20, Image.SCALE_DEFAULT));
		btnImprimir = new JButton(icImprimir);
		btnImprimir.addActionListener(this);

		icNuevo = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/nuevo.png")).getImage()
				.getScaledInstance(anchoPantalla / 35, altoPantalla / 20, Image.SCALE_DEFAULT));
		btnNuevo = new JButton(icNuevo);
		btnNuevo.addActionListener(this);

		icSave = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/save.png")).getImage()
				.getScaledInstance(anchoPantalla / 35, altoPantalla / 20, Image.SCALE_DEFAULT));
		btnSave = new JButton(icSave);
		btnSave.addActionListener(this);

		icExit = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/exit.png")).getImage()
				.getScaledInstance(anchoPantalla / 35, altoPantalla / 20, Image.SCALE_DEFAULT));
		btnExit = new JButton(icExit);
		btnExit.addActionListener(this);
		// Se añaden los botones al panel
		panelBotones.add(btnImprimir);
		panelBotones.add(btnNuevo);
		panelBotones.add(btnSave);
		panelBotones.add(btnExit);
		// Se crea un panel para los botones en la misma clase que el dialogo//

		this.addComponent(panelBotones, 2, 4, 1, 2);

		this.addComponentListener(this);
	}

	/**
	 * Método para añadir componentes a un gridbaglayout
	 *
	 * @param component the component
	 * @param row       the row
	 * @param column    the column
	 * @param width     the width
	 * @param height    the height
	 */
	private void addComponent(Component component, int row, int column, int width, int height) {
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		layout.setConstraints(component, constraints);
		this.add(component);
	}

	/**
	 * Método que complementa al meétodo anterior, fijando el tamaño del próximo
	 * componente del layout
	 *
	 * @param tipo    the tipo
	 * @param anchura the anchura
	 * @param altura  the altura
	 */
	public void setTamano(int tipo, double anchura, double altura) {
		constraints.fill = tipo;
		constraints.weightx = anchura;
		constraints.weighty = altura;
	}

	/**
	 * Metodo para que sea una aplicacion responsive
	 *
	 * @param e the e
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		anchoVentana = this.getSize().height;
		altoVentana = this.getSize().height;
		lblDatosP.setFont(new Font("Arial", Font.BOLD, altoVentana / 45));
		lblDatosH.setFont(new Font("Arial", Font.BOLD, altoVentana / 45));
		lblMenu.setFont(new Font("Arial", Font.BOLD, altoVentana / 45));
		icImprimir.setImage(new ImageIcon(getClass().getResource("/recursos/imprimir.png")).getImage()
				.getScaledInstance(anchoVentana / 20, altoVentana / 20, Image.SCALE_DEFAULT));
		icNuevo.setImage(new ImageIcon(getClass().getResource("/recursos/nuevo.png")).getImage()
				.getScaledInstance(anchoVentana / 20, altoVentana / 20, Image.SCALE_DEFAULT));
		icSave.setImage(new ImageIcon(getClass().getResource("/recursos/save.png")).getImage()
				.getScaledInstance(anchoVentana / 20, altoVentana / 20, Image.SCALE_DEFAULT));
		icExit.setImage(new ImageIcon(getClass().getResource("/recursos/exit.png")).getImage()
				.getScaledInstance(anchoVentana / 20, altoVentana / 20, Image.SCALE_DEFAULT));
		btnImprimir.setSize(new Dimension(anchoVentana / 5, altoVentana / 4));
	}

	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnImprimir) {
			// Variable que voy a utilizar para confirmar que todos los valores introducidos
			// son validos
			// Cuando esta variable sea 5 significa que todos los campos son validos y se
			// puede imprimir
			int imp = 0;
			JTextField textoNombre = pDatosPersona.tfNombre, textoAp = pDatosPersona.tfAp,
					textoDNI = pDatosPersona.tfDNI, textoTlf = pDatosPersona.tfTlf;

			// Nombre//
			imp = impresion(textoNombre.getText(), panelImpresion.tfNom, pDatosPersona.errorNom, imp, 20, "<Nombre>");

			// Apellido//
			imp = impresion(textoAp.getText(), panelImpresion.tfAp, pDatosPersona.errorAp, imp, 20, "<Apellidos>");

			// DNI//
			imp = impresion(textoDNI.getText(), panelImpresion.tfDNI, pDatosPersona.errorDNI, imp, 9, "<DNI>");

			// TLF//
			imp = impresion(textoTlf.getText(), panelImpresion.tfTlf, pDatosPersona.errorTlf, imp, 9, "<Télefono>");

			restarDias = dias();

			if (!pDatosPersona.tfDias.getText().matches("0")) {
				imp++;
			}

			if (pDatosHabitacion.spNumHab.getValue().toString().matches("0")) {
				imp = 0;
				JOptionPane.showMessageDialog(null,
						"<html><font color=#FF0000>Debes introducir al menos una habitación</font></html>",
						"Guardar documento", JOptionPane.WARNING_MESSAGE);
			}

			// Imprimir datos//
			// Si todos los datos son correctos imprime
			if (imp == 5) {
				JOptionPane.showMessageDialog(null, "Se van a imprimir los datos", "Imprimir información",
						JOptionPane.INFORMATION_MESSAGE);
				panelImpresion.tfNom.setText("Nombre: " + textoNombre.getText());
				panelImpresion.tfAp.setText("Apellidos: " + textoAp.getText());
				panelImpresion.tfDNI.setText("DNI: " + textoDNI.getText());
				panelImpresion.tfTlf.setText("Telefono: " + textoTlf.getText());
				panelImpresion.tfDias.setText("Dias de estancia: " + pDatosPersona.tfDias.getText());

				if (pDatosHabitacion.ckbNinios.isSelected()) {
					panelImpresion.tfNinios.setText("Edad del niño: " + pDatosHabitacion.spEdad.getValue());
					panelImpresion.tfCama.setText("Cama: " + pDatosHabitacion.lblExtras.getText());
				} else {
					panelImpresion.tfNinios.setText("Sin niños");
					panelImpresion.tfCama.setText("Sin cama extra");
				}
				panelImpresion.tfTipo
						.setText("Tipo de habitación: " + pDatosHabitacion.cmbBTipo.getSelectedItem().toString());

			}
			/*
			 * Si se ha impreso el contenido anteriormente y luego se ha modificado a mal,
			 * el contenido impreso Vuelve a su estado original
			 */
			else if (imp < 5) {
				panelImpresion.tfNom.setText("<Nombre>");
				panelImpresion.tfAp.setText("<Apellidos>");
				panelImpresion.tfDNI.setText("<DNI>");
				panelImpresion.tfTlf.setText("<Telefono>");
				panelImpresion.tfDias.setText("<Días de estancia>");
				panelImpresion.tfNinios.setText("<Niños>");
				panelImpresion.tfCama.setText("<Cama>");
				panelImpresion.tfTipo.setText("<Tipo>");
			}
			restarDias = 0;
			imp = 0;
			// Imprimir datos//
		}
		if (e.getSource() == btnNuevo) {
			// Antes de borrar el contenido se le informa al usuario que se va a realizar,
			// con la opcion de cancelar la accion
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Borrar información actual?", "Nuevo documento",
					JOptionPane.YES_NO_OPTION);
			if (respuesta == 0) {
				pDatosPersona.tfNombre.setText("");
				pDatosPersona.tfNombre.requestFocus();
				pDatosPersona.tfAp.setText("");
				pDatosPersona.tfDNI.setText("");
				pDatosPersona.tfTlf.setText("");
				pDatosPersona.tfFechEnt.setText("");
				pDatosPersona.tfFechSal.setText("");
				pDatosPersona.tfDias.setText("");

				pDatosPersona.errorNom.setText("");
				pDatosPersona.errorAp.setText("");
				pDatosPersona.errorDNI.setText("");
				pDatosPersona.errorTlf.setText("");
				pDatosPersona.errorEntrada.setText("");
				pDatosPersona.errorSalida.setText("");
				pDatosPersona.errorDias.setText("");
				pDatosPersona.fechaEnt = pDatosPersona.diaEnt.getTime();
				pDatosPersona.tfFechEnt.setText(new SimpleDateFormat("dd/MM/yyyy").format(pDatosPersona.fechaEnt));
				pDatosPersona.fechaSal = pDatosPersona.diaSal.getTime();
				pDatosPersona.tfFechSal.setText(new SimpleDateFormat("dd/MM/yyyy").format(pDatosPersona.fechaSal));
				pDatosPersona.tfDias.setText("1");

				panelImpresion.tfNom.setText("<Nombre>");
				panelImpresion.tfAp.setText("<Apellidos>");
				panelImpresion.tfDNI.setText("<DNI>");
				panelImpresion.tfTlf.setText("<Teléfono>");
				panelImpresion.tfDias.setText("<Días de estancia>");
				panelImpresion.tfNinios.setText("<Niños>");
				panelImpresion.tfCama.setText("<Cama>");
				panelImpresion.tfTipo.setText("<Tipo>");

				pDatosHabitacion.spNumHab.setValue(0);
				pDatosHabitacion.spEdad.setValue(0);
				pDatosHabitacion.cmbBTipo.setSelectedIndex(0);
				pDatosHabitacion.ckbNinios.setSelected(false);
				pDatosHabitacion.bNinios.setVisible(false);
				pDatosHabitacion.imagen.posImg = 0;
				pDatosHabitacion.imagen.labelImagen.setIcon(pDatosHabitacion.imagen.imagen1);
				pDatosHabitacion.tfImporte.setText("Click aqui para calcular importe");
				pDatosHabitacion.tfImporte.setForeground(Color.BLACK);
			}

		}

		if (e.getSource() == btnSave) {
			// Si todos los campos estan rellenos mostrará un mensaje
			if (pDatosPersona.tfNombre.getText().length() != 0 && pDatosPersona.tfAp.getText().length() != 0
					&& pDatosPersona.tfDNI.getText().length() != 0 && pDatosPersona.tfTlf.getText().length() != 0
					&& !pDatosPersona.tfFechEnt.getText().matches("  /  /    ")
					&& !pDatosPersona.tfFechSal.getText().matches("  /  /    ")) {
				pDatosPersona.errorNom.setText("");
				pDatosPersona.errorAp.setText("");
				pDatosPersona.errorDNI.setText("");
				pDatosPersona.errorTlf.setText("");
				pDatosPersona.errorEntrada.setText("");
				pDatosPersona.errorSalida.setText("");
				JOptionPane.showMessageDialog(null, "Registro guardado", "Guardar documento",
						JOptionPane.INFORMATION_MESSAGE);
			}
			// En caso contrario, mostrará un mensaje de error además de informar donde se
			// encuentra
			else {
				rellenado(pDatosPersona.tfNombre.getText().length(), pDatosPersona.errorNom);
				rellenado(pDatosPersona.tfAp.getText().length(), pDatosPersona.errorAp);
				rellenado(pDatosPersona.tfDNI.getText().length(), pDatosPersona.errorDNI);
				rellenado(pDatosPersona.tfTlf.getText().length(), pDatosPersona.errorTlf);
				rellenado(pDatosPersona.tfFechEnt.getText(), "  /  /    ", pDatosPersona.errorEntrada);
				rellenado(pDatosPersona.tfFechSal.getText(), "  /  /    ", pDatosPersona.errorSalida);
				JOptionPane.showMessageDialog(null, "Rellene todos los campos", "Error al guardar",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		if (e.getSource() == btnExit)
			dispose();
	}

	/**
	 * Método para calcular los dias
	 *
	 */
	int dias() {
		// Fecha entrada//
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			format.setLenient(false);
			format.parse(pDatosPersona.tfFechEnt.getText());
			pDatosPersona.errorEntrada.setText("");
			restarDias++;
		} catch (ParseException pe) {
			pDatosPersona.errorEntrada.setText("Fecha no válida");
		} catch (IllegalArgumentException pe) {
			pDatosPersona.errorEntrada.setText("Fecha no válida");
		}

		// Fecha salida//
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			format.setLenient(false);
			format.parse(pDatosPersona.tfFechSal.getText());
			pDatosPersona.errorSalida.setText("");
			restarDias++;
		} catch (ParseException pe) {
			pDatosPersona.errorSalida.setText("Fecha no válida");
		} catch (IllegalArgumentException pe) {
			pDatosPersona.errorSalida.setText("Fecha no válida");
		}

		// Recoger fechas de los campos//
		pDatosPersona.fechaEnt = recogerFecha(pDatosPersona.fechaEnt, pDatosPersona.tfFechEnt.getText(),
				pDatosPersona.errorEntrada);
		pDatosPersona.fechaSal = recogerFecha(pDatosPersona.fechaSal, pDatosPersona.tfFechSal.getText(),
				pDatosPersona.errorSalida);

		// Restar dias si ambas fechas son validas//
		if (restarDias == 2) {
			long tiempoSalida = pDatosPersona.fechaSal.getTime(), tiempoEntrada = pDatosPersona.fechaEnt.getTime();
			if (tiempoEntrada < tiempoSalida) {
				pDatosPersona.tfDias
						.setText("" + (TimeUnit.DAYS.convert(tiempoSalida - tiempoEntrada, TimeUnit.MILLISECONDS)));
				pDatosPersona.errorDias.setText("");
			} else {
				pDatosPersona.errorDias.setText("La entrada debe ser antes que la salida");
				restarDias = 0;
			}
		}
		if (restarDias == 0) {
			pDatosPersona.tfDias.setText("0");
		}
		// Restar dias si ambas fechas son validas//
		return restarDias;
	}

	/**
	 * Método para informar al usuario donde se encuentra el error (No aplicable en
	 * fechas)
	 *
	 * @param tamano the tamano
	 * @param error  the error
	 */
	void rellenado(int tamano, JTextField error) {
		if (tamano == 0)
			error.setText("Rellene el campo");
		else
			error.setText("");
	}

	/**
	 * Método para informar al usuario donde se encuentra el error (Aplicable en
	 * fechas)
	 *
	 * @param fecha the fecha
	 * @param vacio the vacio
	 * @param error the error
	 */
	void rellenado(String fecha, String vacio, JTextField error) {
		if (fecha.matches(vacio)) {
			error.setText("Rellene el campo");
			pDatosPersona.tfDias.setText("0");
		} else
			error.setText("");
	}

	/**
	 * Metodo para comprobar que todos los campos tienen contenido, si uno de ellos
	 * no tiene no imprimirá
	 *
	 * @param input  the input
	 * @param output the output
	 * @param error  the error
	 * @param imp    the imp
	 * @param tam    the tam
	 * @param texto  the texto
	 * @return the int
	 */
	int impresion(String input, JTextField output, JTextField error, int imp, int tam, String texto) {
		if (input.length() == tam)
			imp++;
		else if (input.length() == 0) {
			error.setText("Rellene el campo");
			imp = 0;
		} else if (tam == 9) {
			if (input.length() > tam || input.length() < tam) {
				error.setText("Introducir solo " + tam + " caracteres");
				imp = 0;
			}
		} else if (tam == 20) {
			if (input.length() > 20) {
				error.setText("Máximo de caracteres 20");
				imp = 0;
			} else
				imp++;
		} else {
			output.setText(texto);
			imp = 0;
		}

		return imp;
	}

	/**
	 * Recoger fecha.
	 *
	 * @param fechaEnt the fecha ent
	 * @param tfFecha  the tf fecha
	 * @param error    the error
	 * @return the date
	 */
	Date recogerFecha(Date fechaEnt, String tfFecha, JTextField error) {
		try {
			return fechaEnt = new SimpleDateFormat("dd/MM/yyyy").parse(tfFecha);
		} catch (ParseException erSal) {
			error.setText("Rellene el campo con formato dd/mm/aaaa");
		}
		return null;
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
	}
}
