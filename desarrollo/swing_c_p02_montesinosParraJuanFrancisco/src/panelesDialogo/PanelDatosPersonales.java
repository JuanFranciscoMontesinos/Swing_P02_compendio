/**
 * PanelDatos.java
	26 nov. 2020 20:58:10
 */
package panelesDialogo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

/**
 * The Class PanelDatosPersonales.
 *
 * @author Juan Francisco Montesinos Parra
 */
@SuppressWarnings("serial")
public class PanelDatosPersonales extends JPanel implements ComponentListener, KeyListener, FocusListener {

	/** The layout. */
	GridBagLayout layout;

	/** The constraints. */
	GridBagConstraints constraints;

	/** The lbl dias. */
	JLabel lblNombre, lblAp, lblDNI, lblTlf, lblFechEnt, lblFechSal, lblDias;

	/** The color. */
	Color color;

	/** The error salida. */
	public JTextField tfNombre, tfAp, tfDNI, tfTlf, tfDias, errorNom, errorAp, errorDNI, errorTlf, errorDias,
			errorEntrada, errorSalida;

	/** The tf fech sal. */
	public JFormattedTextField tfFechEnt, tfFechSal;

	/** The fecha sal. */
	public Date fechaEnt, fechaSal;

	/** The dia sal. */
	public Calendar diaEnt, diaSal;

	/** The fuente error. */
	Font fuentePlain, fuenteBold, fuenteError;

	/** Enteros */
	int anchoPantalla, altoPantalla, altoVentana, anchoVentana, restarDias = 0;

	/**
	 * Instantiates a new panel datos personales.
	 */
	int num = 0;

	public PanelDatosPersonales() {
		// Tamaño de la pantalla
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanioPantalla = new Dimension();
		tamanioPantalla = miPantalla.getScreenSize();
		anchoPantalla = tamanioPantalla.width;
		altoPantalla = tamanioPantalla.height;

		layout = new GridBagLayout();
		this.setLayout(layout);
		constraints = new GridBagConstraints();

		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		fuentePlain = new Font("Arial", Font.PLAIN, anchoPantalla / 95);

		// -------NOMBRE-------//
		lblNombre = new JLabel("Nombre: ");
		tfNombre = new JTextField();
		tfNombre.setPreferredSize(new Dimension(anchoPantalla / 20, altoPantalla / 60));
		tfNombre.addKeyListener(this);
		this.setTamano(GridBagConstraints.BOTH, 0, 50);
		this.addComponent(lblNombre, 0, 0, 1, 1);
		this.setTamano(GridBagConstraints.BOTH, 600, 30);
		this.addComponent(tfNombre, 0, 1, 1, 1);
		// -------NOMBRE-------//

		// -------SEPARACION-------//
		this.setTamano(GridBagConstraints.BOTH, 0, 600);
		errorNom = new JTextField("");
		errorNom = setPropiedadesError(errorNom, "");
		this.addComponent(errorNom, 1, 0, 2, 1);
		// -------SEPARACION-------//

		// -------APELLIDOS-------//
		lblAp = new JLabel("Apellidos: ");
		tfAp = new JTextField();
		tfAp.setPreferredSize(new Dimension(anchoPantalla / 20, altoPantalla / 60));
		tfAp.addKeyListener(this);
		this.setTamano(GridBagConstraints.BOTH, 0, 50);
		this.addComponent(lblAp, 2, 0, 1, 1);
		this.setTamano(GridBagConstraints.BOTH, 600, 30);
		this.addComponent(tfAp, 2, 1, 1, 1);
		// -------APELLIDOS-------//

		// -------SEPARACION-------//
		this.setTamano(GridBagConstraints.BOTH, 0, 600);
		errorAp = new JTextField("");
		errorAp = setPropiedadesError(errorAp, "");
		this.addComponent(errorAp, 3, 0, 2, 1);
		// -------SEPARACION-------//

		// -------DNI-------//
		lblDNI = new JLabel("DNI: ");
		tfDNI = new JTextField();
		tfDNI.setPreferredSize(new Dimension(anchoPantalla / 20, altoPantalla / 60));
		tfDNI.addKeyListener(this);
		this.setTamano(GridBagConstraints.BOTH, 0, 50);
		this.addComponent(lblDNI, 4, 0, 1, 1);
		this.setTamano(GridBagConstraints.BOTH, 600, 30);
		this.addComponent(tfDNI, 4, 1, 1, 1);
		// -------DNI-------//

		// -------SEPARACION-------//
		this.setTamano(GridBagConstraints.BOTH, 0, 600);
		errorDNI = new JTextField("");
		errorDNI = setPropiedadesError(errorDNI, "");
		this.addComponent(errorDNI, 5, 0, 2, 1);
		// -------SEPARACION-------//

		// -------TELEFONO-------//
		lblTlf = new JLabel("Telefono: ");
		tfTlf = new JTextField();
		tfTlf.setPreferredSize(new Dimension(anchoPantalla / 20, altoPantalla / 60));
		tfTlf.addKeyListener(this);
		this.setTamano(GridBagConstraints.BOTH, 0, 50);
		this.addComponent(lblTlf, 6, 0, 1, 1);
		this.setTamano(GridBagConstraints.BOTH, 600, 30);
		this.addComponent(tfTlf, 6, 1, 1, 1);
		// -------TELEFONO-------//

		// -------SEPARACION-------//
		this.setTamano(GridBagConstraints.BOTH, 0, 600);
		errorTlf = new JTextField();
		errorTlf = setPropiedadesError(errorTlf, "");
		this.addComponent(errorTlf, 7, 0, 2, 1);
		// -------SEPARACION-------//

		// -------FECHA ENTRADA-------//
		lblFechEnt = new JLabel("Fecha de entrada: ", SwingConstants.RIGHT);
		tfFechEnt = new JFormattedTextField();
		tfFechEnt.setPreferredSize(new Dimension(anchoPantalla / 20, altoPantalla / 60));
		tfFechEnt.addFocusListener(this);
		tfFechEnt.addKeyListener(this);
		fechaEnt = new Date();
		diaEnt = Calendar.getInstance();
		diaEnt.setTime(fechaEnt);
		fechaEnt = diaEnt.getTime();
		tfFechEnt.setText(new SimpleDateFormat("dd/MM/yyyy").format(fechaEnt));
		this.setTamano(GridBagConstraints.BOTH, 120, 50);
		this.addComponent(lblFechEnt, 0, 2, 1, 1);
		this.setTamano(GridBagConstraints.BOTH, 600, 30);
		this.addComponent(tfFechEnt, 0, 3, 1, 1);
		// -------FECHA ENTRADA-------//

		// -------SEPARACION-------//
		this.setTamano(GridBagConstraints.BOTH, 0, 600);
		errorEntrada = new JTextField("");
		errorEntrada = setPropiedadesError(errorEntrada, "");
		this.addComponent(errorEntrada, 1, 2, 2, 1);
		// -------SEPARACION-------//

		// -------FECHA SALIDA-------//
		lblFechSal = new JLabel("Fecha de salida: ", SwingConstants.RIGHT);
		tfFechSal = new JFormattedTextField();
		tfFechSal.setPreferredSize(new Dimension(anchoPantalla / 20, altoPantalla / 60));
		tfFechSal.addFocusListener(this);
		tfFechSal.addKeyListener(this);
		fechaSal = new Date();
		diaSal = Calendar.getInstance();
		diaSal.setTime(fechaSal);
		diaSal.add(Calendar.DATE, 1);
		fechaSal = diaSal.getTime();
		tfFechSal.setText(new SimpleDateFormat("dd/MM/yyyy").format(fechaSal));
		this.setTamano(GridBagConstraints.BOTH, 120, 50);
		this.addComponent(lblFechSal, 2, 2, 1, 1);
		this.setTamano(GridBagConstraints.BOTH, 600, 30);
		this.addComponent(tfFechSal, 2, 3, 1, 1);
		// -------FECHA SALIDA-------//

		// -------SEPARACION-------//
		this.setTamano(GridBagConstraints.BOTH, 0, 600);
		errorSalida = new JTextField("");
		errorSalida = setPropiedadesError(errorSalida, "");
		this.addComponent(errorSalida, 3, 2, 2, 1);
		// -------SEPARACION-------//

		// -------DIAS ESTANCIA-------//
		lblDias = new JLabel("Dias de estancia: ", SwingConstants.RIGHT);
		tfDias = new JTextField("1");
		tfDias.setPreferredSize(new Dimension(anchoPantalla / 20, altoPantalla / 60));
		tfDias.setHorizontalAlignment(JTextField.CENTER);
		tfDias.setEditable(false);
		tfDias.setFocusable(false);
		this.setTamano(GridBagConstraints.BOTH, 120, 50);
		this.addComponent(lblDias, 4, 2, 1, 1);
		this.setTamano(GridBagConstraints.BOTH, 600, 30);
		this.addComponent(tfDias, 4, 3, 1, 1);
		// -------DIAS ESTANCIA-------//

		// -------SEPARACION-------//
		this.setTamano(GridBagConstraints.BOTH, 40, 0);
		errorDias = new JTextField("");
		errorDias = setPropiedadesError(errorDias, "");
		this.addComponent(errorDias, 5, 2, 2, 1);
		// -------SEPARACION-------//

		// -------SEPARACION-------//
		this.setTamano(GridBagConstraints.BOTH, 40, 0);
		this.addComponent(new JLabel(""), 2, 4, 1, 1);
		// -------SEPARACION-------//

		// -------SEPARACION-------//
		this.setTamano(GridBagConstraints.BOTH, 40, 0);
		this.addComponent(new JLabel(""), 4, 4, 1, 1);
		// -------SEPARACION-------//

		this.setVisible(true);
		this.addComponentListener(this);
	}

	/**
	 * Método que aplica las propiedades al textfield de los errores de forma que
	 * aparente un jlabel Esto se hace debido a que un jtextfield resulta mas facil
	 * de manejar en un gridlayout
	 *
	 * @param campo       the campo
	 * @param nombreCampo the nombre campo
	 * @return the j text field
	 */
	JTextField setPropiedadesError(JTextField campo, String nombreCampo) {
		campo = new JTextField(nombreCampo);
		campo.setForeground(Color.RED);
		campo.setEditable(false);
		campo.setBorder(null);
		campo.setFocusable(false);
		campo.setHorizontalAlignment(JTextField.CENTER);
		return campo;
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
			format.parse(tfFechEnt.getText());
			errorEntrada.setText("");
			restarDias++;
		} catch (ParseException pe) {
			errorEntrada.setText("Fecha no válida");
		} catch (IllegalArgumentException pe) {
			errorEntrada.setText("Fecha no válida");
		}

		// Fecha salida//
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			format.setLenient(false);
			format.parse(tfFechSal.getText());
			errorSalida.setText("");
			restarDias++;
		} catch (ParseException pe) {
			errorSalida.setText("Fecha no válida");
		} catch (IllegalArgumentException pe) {
			errorSalida.setText("Fecha no válida");
		}
		
		// Recoger fechas de los campos//
				fechaEnt = recogerFecha(fechaEnt, tfFechEnt.getText(),
						errorEntrada);
				fechaSal = recogerFecha(fechaSal, tfFechSal.getText(),
						errorSalida);
				
		// Restar dias si ambas fechas son validas//
		if (restarDias == 2) {
			long tiempoSalida = fechaSal.getTime(), tiempoEntrada = fechaEnt.getTime();
			if (tiempoEntrada < tiempoSalida) {
				tfDias.setText("" + (TimeUnit.DAYS.convert(tiempoSalida - tiempoEntrada, TimeUnit.MILLISECONDS)));
				errorDias.setText("");
			} else {
				errorDias.setText("La entrada debe ser antes que la salida");
				restarDias = 0;
			}
		}
		if (restarDias == 0) {
			tfDias.setText("0");
		}
		// Restar dias si ambas fechas son validas//
		return restarDias;
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
		constraints.weightx = 1;
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
	 * @param arg0 the arg 0
	 */
	@Override
	public void componentResized(ComponentEvent arg0) {

		anchoVentana = this.getParent().getSize().width;
		altoVentana = this.getParent().getSize().height;

		fuenteBold = new Font("Arial", Font.BOLD, altoVentana / 50);
		fuentePlain = new Font("Arial", Font.PLAIN, altoVentana / 50);
		fuenteError = new Font("Arial", Font.ITALIC, altoVentana / 50);
		color = new Color(59, 134, 134);

		lblNombre.setFont(fuenteBold);
		lblNombre.setForeground(color);
		lblAp.setFont(fuenteBold);
		lblAp.setForeground(color);
		lblDNI.setFont(fuenteBold);
		lblDNI.setForeground(color);
		lblTlf.setFont(fuenteBold);
		lblTlf.setForeground(color);
		lblFechEnt.setFont(fuenteBold);
		lblFechEnt.setForeground(color);
		lblFechSal.setFont(fuenteBold);
		lblFechSal.setForeground(color);
		lblDias.setFont(fuenteBold);
		lblDias.setForeground(color);

		tfNombre.setFont(fuentePlain);
		tfAp.setFont(fuentePlain);
		tfDNI.setFont(fuentePlain);
		tfTlf.setFont(fuentePlain);
		tfFechEnt.setFont(fuentePlain);
		tfFechSal.setFont(fuentePlain);
		tfDias.setFont(fuentePlain);

		errorNom.setFont(fuenteError);
		errorAp.setFont(fuenteError);
		errorDNI.setFont(fuenteError);
		errorTlf.setFont(fuenteError);
		errorEntrada.setFont(fuenteError);
		errorSalida.setFont(fuenteError);
		errorDias.setFont(fuenteError);
	}

	/**
	 * Método que comprueba que el campo en el que se esté escribiendo cumpla sus
	 * condiciones
	 *
	 * @param e the e
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == tfNombre) {
			int tamano = tfNombre.getText().length();
			if (tamano >= 21) {
				errorNom.setText("Máximo de caracteres 20");
				e.consume();
			} else if (tamano <= 20)
				errorNom.setText("");
		}

		if (e.getSource() == tfAp) {
			int tamano = tfAp.getText().length();
			if (tamano >= 21) {
				errorAp.setText("Máximo de caracteres 20");
				e.consume();
			} else if (tamano <= 20)
				errorAp.setText("");
		}

		if (e.getSource() == tfDNI) {
			int tamano = tfDNI.getText().length();
			if (tamano >= 10) {
				errorDNI.setText("Introducir solo 9 caracteres");
				e.consume();
			} else if (tamano <= 9)
				errorDNI.setText("");
		}

		if (e.getSource() == tfTlf) {
			String texto = tfTlf.getText();
			try {
				if (!texto.matches("")) {
					Integer.parseInt(texto);
					errorTlf.setText("");
				}
				if (texto.length() <= 9)
					errorTlf.setText("");
			} catch (NumberFormatException nfe) {
				errorTlf.setText("Solo introducir números");
				e.consume();
			}

			if (texto.length() > 9) {
				e.consume();
				errorTlf.setText("Introducir solo 9 caracteres");
			}
		}
		
		if(e.getSource() == tfFechEnt) {
			int tamano = tfFechEnt.getText().length();
			if (tamano >= 11) {
				errorEntrada.setText("Formato de fecha debe ser dd/mm/yyyy");
				e.consume();
			} else if (tamano <= 10)
				errorEntrada.setText("");
		}
		
		if(e.getSource() == tfFechSal) {
			int tamano = tfFechSal.getText().length();
			if (tamano >= 11) {
				errorSalida.setText("Formato de fecha debe ser dd/mm/yyyy");
				e.consume();
			} else if (tamano <= 10)
				errorSalida.setText("");
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() == tfFechEnt) {
			if(tfFechEnt.getText().length() == 11)
				tfFechEnt.setText(tfFechEnt.getText().substring(0,tfFechEnt.getText().length()-1));
			restarDias = dias();
			restarDias = 0;
		}
		if(e.getSource() == tfFechSal) {
			if(tfFechSal.getText().length() == 11)
				tfFechSal.setText(tfFechSal.getText().substring(0,tfFechSal.getText().length()-1));
			restarDias = dias();
			restarDias = 0;
		}
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

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

}
