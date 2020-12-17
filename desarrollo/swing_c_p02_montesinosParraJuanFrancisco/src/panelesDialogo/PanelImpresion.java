/**
 * PanelImpresion.java
	1 dic. 2020 19:17:35
 */
package panelesDialogo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;

/**
 * The Class PanelImpresion.
 *
 * @author Juan Francisco Montesinos Parra
 */
@SuppressWarnings("serial")
public class PanelImpresion extends JTabbedPane implements ComponentListener {

	/** The panel hab. */
	JPanel panelPer, panelHab;
	
	/** The tf tipo. */
	public JTextField tfNom, tfAp, tfDNI, tfTlf, tfDias, tfNinios, tfCama, tfTipo;
	
	/** The fuente bold. */
	Font fuenteItalic, fuenteBold;
	
	/** The color. */
	Color color;
	
	/** The alto ventana. */
	int anchoPantalla, altoPantalla, altoVentana;

	/**
	 * Instantiates a new panel impresion.
	 */
	public PanelImpresion() {
		//Tamaño de la pantalla
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanioPantalla = new Dimension();
		tamanioPantalla = miPantalla.getScreenSize();
		anchoPantalla = tamanioPantalla.width;
		altoPantalla = tamanioPantalla.height;
		
		//Fuente a utilizar en este panel
		fuenteItalic = new Font("Arial", Font.ITALIC, anchoPantalla / 80);
		fuenteBold = new Font("Arial", Font.BOLD, anchoPantalla / 80);

		//Panel que se utiliza en la primera pestaña, que contiene información personal
		panelPer = new JPanel();
		panelPer.setLayout(new BoxLayout(panelPer, BoxLayout.Y_AXIS));

		tfNom = setPropiedades(tfNom, "<Nombre>");
		tfAp = setPropiedades(tfAp, "<Apellidos>");
		tfDNI = setPropiedades(tfDNI, "<DNI>");
		tfTlf = setPropiedades(tfTlf, "<Teléfono>");

		panelPer.add(tfNom);
		panelPer.add(Box.createVerticalGlue());
		panelPer.add(tfAp);
		panelPer.add(Box.createVerticalGlue());
		panelPer.add(tfDNI);
		panelPer.add(Box.createVerticalGlue());
		panelPer.add(tfTlf);
		panelPer.add(Box.createVerticalGlue());

		panelPer.setMaximumSize(new Dimension(anchoPantalla / 6, altoPantalla / 6));

		//Panel que se utiliza en la segunda pestaña, que contiene información del registro

		panelHab = new JPanel();
		panelHab.setLayout(new BoxLayout(panelHab, BoxLayout.Y_AXIS));

		tfDias = setPropiedades(tfDias, "<Días de estancia>");
		tfNinios = setPropiedades(tfNinios, "<Niños>");
		tfCama = setPropiedades(tfCama, "<Cama>");
		tfTipo = setPropiedades(tfTipo, "<Tipo>");

		panelHab.add(tfDias);
		panelHab.add(Box.createVerticalGlue());
		panelHab.add(tfNinios);
		panelHab.add(Box.createVerticalGlue());
		panelHab.add(tfCama);
		panelHab.add(Box.createVerticalGlue());
		panelHab.add(tfTipo);
		panelHab.add(Box.createVerticalGlue());

		this.addTab("Datos personales", panelPer);
		this.addTab("Datos de la habitacion", panelHab);
		this.setFont(fuenteBold);
		this.addComponentListener(this);
		this.setVisible(true);
	}

	/**
	 * Método para aplicar las propiedades a los textfield para simular que son jlabels
	 *
	 * @param campo the campo
	 * @param nombreCampo the nombre campo
	 * @return the j text field
	 */
	JTextField setPropiedades(JTextField campo, String nombreCampo) {
		campo = new JTextField(nombreCampo);
		campo.setMaximumSize(new Dimension(anchoPantalla / 3, altoPantalla / 30));
		campo.setFont(fuenteItalic);
		campo.setAlignmentX(LEFT_ALIGNMENT);
		campo.setEditable(false);
		campo.setBorder(null);
		campo.setFocusable(false);
		return campo;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.tfNom.setText(nombre);
	}

	/**
	 * Sets the ap.
	 *
	 * @param ap the new ap
	 */
	public void setAp(String ap) {
		this.tfAp.setText(ap);
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni the new dni
	 */
	public void setDNI(String dni) {
		this.tfDNI.setText(dni);
	}

	/**
	 * Sets the tlf.
	 *
	 * @param tlf the new tlf
	 */
	public void setTlf(String tlf) {
		this.tfTlf.setText(tlf);
	}

	/**
	 * Metodo para que sea una aplicacion responsive
	 *
	 * @param arg0 the arg 0
	 */
	@Override
	public void componentResized(ComponentEvent arg0) {
		altoVentana = this.getParent().getSize().height;
		fuenteBold = new Font("Consolas", Font.BOLD, altoVentana / 50);
		color = new Color(121,189,154);
		this.setFont(fuenteBold);
		this.setForeground(new Color(11,72,107));
		tfNom.setFont(fuenteBold);
		tfNom.setForeground(color);
		tfAp.setFont(fuenteBold);
		tfAp.setForeground(color);
		tfDNI.setFont(fuenteBold);
		tfDNI.setForeground(color);
		tfTlf.setFont(fuenteBold);
		tfTlf.setForeground(color);
		tfDias.setFont(fuenteBold);
		tfDias.setForeground(color);
		tfNinios.setFont(fuenteBold);
		tfNinios.setForeground(color);
		tfCama.setFont(fuenteBold);
		tfCama.setForeground(color);
		tfTipo.setFont(fuenteBold);
		tfTipo.setForeground(color);

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
