/**
 * PanelTitulo.java
	26 nov. 2020 20:49:57
 */
package panelesDialogo;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;

/**
 * The Class PanelTitulo.
 *
 * @author Juan Francisco Montesinos Parra
 */
@SuppressWarnings("serial")
public class PanelTitulo extends JPanel implements ComponentListener{

	/** The lbl nombre. */
	JLabel lblNombre;
	
	/** The borde. */
	int borde;
	
	/**
	 * Instantiates a new panel titulo.
	 */
	public PanelTitulo() {
		//Tamaño de la pantalla
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanioPantalla = new Dimension();
		tamanioPantalla = miPantalla.getScreenSize();
		int altoPantalla = tamanioPantalla.height;
		
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(168,219,168));
		this.setOpaque(true);
		borde = altoPantalla /300;
		this.setBorder(BorderFactory.createMatteBorder(borde,borde,borde,borde, new Color(11,72,107)));
		
		//Label para mostrar como titulo
		lblNombre = new JLabel("Hotel JFMP");
		lblNombre.setFont(new Font("Caladea", Font.BOLD, altoPantalla/40));
		lblNombre.setForeground(new Color(11,72,107));
		this.add(lblNombre);
		
		this.addComponentListener(this);
		this.setVisible(true);
	}

	/**
	 * Metodo para que sea una aplicacion responsive
	 *
	 * @param e the e
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		borde = this.getParent().getSize().height/300;
		lblNombre.setFont(new Font("Caladea", Font.BOLD,this.getParent().getSize().height/30));
		this.setBorder(BorderFactory.createMatteBorder(borde,borde,borde,borde, new Color(11,72,107)));
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
