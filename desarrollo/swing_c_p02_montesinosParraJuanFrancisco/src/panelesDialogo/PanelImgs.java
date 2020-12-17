/**
 * PanelTotYImg.java
	27 nov. 2020 20:27:39
 */
package panelesDialogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The Class PanelImgs.
 *
 * @author Juan Francisco Montesinos Parra
 */
@SuppressWarnings("serial")
public class PanelImgs extends JPanel implements ActionListener, ComponentListener {

	/** The lbl importe. */
	JLabel lblImporte;

	/** The tit image. */
	JTextField titImage;

	/** The label imagen. */
	public JLabel labelImagen;

	/** The imagen 3. */
	public ImageIcon imagen1, imagen2, imagen3;

	/** The ant img. */
	JButton sigImg, antImg;

	/** The imagenes. */
	ArrayList<ImageIcon> imagenes;

	/** The fuente. */
	Font fuente;

	/** The pos img. */
	public int posImg = 0;

	/** The alto ventana. */
	int anchoVentana, altoVentana;

	/**
	 * Instantiates a new panel imgs.
	 */
	public PanelImgs() {
		// Tamaño de la pantalla
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanioPantalla = new Dimension();
		tamanioPantalla = miPantalla.getScreenSize();
		int anchoPantalla = tamanioPantalla.width;

		Font fuente = new Font("Times New Roman", Font.BOLD, anchoPantalla / 80);
		setImagenes(tamanioPantalla);

		// Boton para volver a la imagen anterior
		antImg = new JButton("<");
		antImg.setFont(fuente);
		antImg.addActionListener(this);

		// La imagen a mostrar
		labelImagen = new JLabel(imagenes.get(posImg));

		// Boton para pasar a la siguiente imagen
		sigImg = new JButton(">");
		sigImg.setFont(fuente);
		sigImg.addActionListener(this);

		// TextField siendo una label para dar titulo al panel de imágenes
		titImage = new JTextField("I m á g e n e s   d e l   h o t e l");
		titImage.setBorder(null);
		titImage.setEditable(false);
		titImage.setFont(fuente);
		titImage.setHorizontalAlignment(JTextField.CENTER);
		titImage.setBackground(new Color(55, 187, 253));
		titImage.setOpaque(true);

		// Se aplica layout y se añaden los componentes
		this.setLayout(new BorderLayout());
		this.add(antImg, BorderLayout.WEST);
		this.add(labelImagen, BorderLayout.CENTER);
		this.add(sigImg, BorderLayout.EAST);
		this.add(titImage, BorderLayout.NORTH);

		this.setBackground(new Color(48, 136, 149));
		this.setOpaque(true);
		this.addComponentListener(this);
	}

	/**
	 * Las imágenes a utilizar se añaden a un array de tipo ImageIcon
	 *
	 * @param tamanioPantalla the new imagenes
	 */
	void setImagenes(Dimension tamanioPantalla) {
		int anchoPantalla = tamanioPantalla.width;
		int altoPantalla = tamanioPantalla.height;
		imagenes = new ArrayList<ImageIcon>();
		imagen1 = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/hotel1.png")).getImage()
				.getScaledInstance(anchoPantalla / 4, altoPantalla / 4, Image.SCALE_DEFAULT));
		imagen2 = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/hotel2.png")).getImage()
				.getScaledInstance(anchoPantalla / 4, altoPantalla / 4, Image.SCALE_DEFAULT));
		imagen3 = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/hotel3.png")).getImage()
				.getScaledInstance(anchoPantalla / 4, altoPantalla / 4, Image.SCALE_DEFAULT));
		imagenes.add(imagen1);
		imagenes.add(imagen2);
		imagenes.add(imagen3);
	}

	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Para mostrar las imágenes, cada botón aumenta o disminuye la posición del
		// array mostrando la imagen correspondiente a su posición
		if (e.getSource() == antImg) {
			posImg--;
			if (posImg == -1)
				posImg = 2;
			labelImagen.setIcon(imagenes.get(posImg));
		}

		if (e.getSource() == sigImg) {
			posImg++;
			if (posImg == 3)
				posImg = 0;
			labelImagen.setIcon(imagenes.get(posImg));
		}

	}

	/**
	 * Metodo para que sea una aplicacion responsive
	 *
	 * @param arg0 the arg 0
	 */
	@Override
	public void componentResized(ComponentEvent arg0) {
		anchoVentana = this.getParent().getParent().getSize().width;
		altoVentana = this.getParent().getParent().getSize().height;
		fuente = new Font("Arial", Font.BOLD, getParent().getParent().getSize().height / 50);
		imagenes.get(0).setImage(new ImageIcon(getClass().getResource("/recursos/hotel1.png")).getImage()
				.getScaledInstance(anchoVentana / 4, altoVentana / 3, Image.SCALE_DEFAULT));
		imagenes.get(1).setImage(new ImageIcon(getClass().getResource("/recursos/hotel2.png")).getImage()
				.getScaledInstance(anchoVentana / 4, altoVentana / 3, Image.SCALE_DEFAULT));
		imagenes.get(2).setImage(new ImageIcon(getClass().getResource("/recursos/hotel3.png")).getImage()
				.getScaledInstance(anchoVentana / 4, altoVentana / 3, Image.SCALE_DEFAULT));
		antImg.setFont(fuente);
		sigImg.setFont(fuente);
		titImage.setFont(fuente);

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
}
