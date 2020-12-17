/**
 * PanelDatosHabitacion.java
	27 nov. 2020 18:03:04
 */
package panelesDialogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The Class PanelDatosHabitacion.
 *
 * @author Juan Francisco Montesinos Parra
 */
@SuppressWarnings("serial")
public class PanelDatosHabitacion extends JPanel
		implements ActionListener, ChangeListener, ComponentListener, FocusListener {

	/** The lbl edad ninios. */
	JLabel lblNumHab, lblEdadNinios;

	/** The p edad. */
	JPanel pNumHabs, pTipos, pEdad;

	/** The fuente italic. */
	Font fuentePlano, fuenteNegrita, fuenteItalic;

	/** The color. */
	Color color;

	/** The layout. */
	GridBagLayout layout;

	/** The constraints. */
	GridBagConstraints constraints;

	/** The barra. */
	JProgressBar barra;

	/** The lbl extras. */
	public JLabel lblExtras;

	/** The sp edad. */
	public JSpinner spNumHab, spEdad;

	/** The cmb B tipo. */
	public JComboBox<String> cmbBTipo;

	/** The ckb ninios. */
	public JCheckBox ckbNinios;

	/** The imagen. */
	public PanelImgs imagen;

	/** The b ninios. */
	public Box bNinios;

	/** The tf barra. */
	public JTextField tfTipoHab, tfImporte, tfBarra;

	/** The pista. */
	boolean pista = true;

	/** The borde. */
	int anchoVentana, altoVentana, importe = 0, borde = 1;
	
	PanelDatosPersonales pDatosPersona;

	/**
	 * Instantiates a new panel datos habitacion.
	 * 
	 * @param pDatosPersona the p datos persona
	 */
	public PanelDatosHabitacion(PanelDatosPersonales pDatosPersona) {
		this.pDatosPersona = pDatosPersona;
		layout = new GridBagLayout();
		this.setLayout(layout);
		constraints = new GridBagConstraints();

		// ----NUMERO DE HABITACIONES----//
		pNumHabs = new JPanel();
		// Etiqueta
		lblNumHab = new JLabel("Número de habitaciones");
		pNumHabs.add(lblNumHab);

		// Spinner
		spNumHab = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
		spNumHab.addChangeListener(this);
		pNumHabs.add(spNumHab);

		// ----NUMERO DE HABITACIONES----//

		// ----TIPO DE HABITACIONES----//
		pTipos = new JPanel();
		// Etiqueta
		tfTipoHab = new JTextField("Tipo de habitación");
		pTipos.add(tfTipoHab);

		// Spinner
		String[] tipoHabs = { "Simple", "Doble", "Suite" };
		cmbBTipo = new JComboBox<String>(tipoHabs);
		cmbBTipo.addActionListener(this);
		pTipos.add(cmbBTipo);

		// ----TIPO DE HABITACIONES----//

		// ----TIENE NIÑOS LA HABITACION----//

		// Checkbox
		ckbNinios = new JCheckBox("¿Añadir niño?");
		ckbNinios.setHorizontalAlignment(SwingConstants.CENTER);
		ckbNinios.setAlignmentY(CENTER_ALIGNMENT);
		ckbNinios.addActionListener(this);

		// Caja vertical para etiqueta y spinner
		bNinios = Box.createVerticalBox();

		// Panel para alinear etiqueta y spinner
		pEdad = new JPanel();

		// Etiqueta
		lblEdadNinios = new JLabel("Edad de niños: ");

		// Spinner
		spEdad = new JSpinner(new SpinnerNumberModel(0, 0, 14, 1));
		spEdad.addChangeListener(this);
		// Panel para centrar la etiqueta
		JPanel panelLblExtras = new JPanel();
		lblExtras = new JLabel("Cuna", SwingConstants.RIGHT);
		panelLblExtras.add(lblExtras, BorderLayout.NORTH);

		// Se añade la etiqueta y el spinner al panel de alineación
		pEdad.add(lblEdadNinios);
		pEdad.add(spEdad);
		// Se añaden el panel anterior y la label del contenido
		bNinios.add(pEdad);
		bNinios.add(panelLblExtras);
		bNinios.setVisible(false);
		bNinios.setBorder(BorderFactory.createMatteBorder(borde, borde, borde, borde, new Color(121, 189, 154)));
		// ----TIENE NIÑOS LA HABITACION----//

		imagen = new PanelImgs();
		imagen.setVisible(true);

		// Panel para centrar el importe
		JPanel pImporte = new JPanel();
		tfImporte = new JTextField("Click aqui para calcular importe");
		tfImporte.setBorder(BorderFactory.createMatteBorder(borde, borde, borde, borde, new Color(121, 189, 154)));
		tfImporte.addFocusListener(this);
		tfImporte.setHorizontalAlignment(JTextField.CENTER);
		pImporte.add(tfImporte);

		// Label vacia para añadir un pequeña separación al inicio
		setTamano(GridBagConstraints.BOTH, 1, 2);
		this.addComponent(new JLabel(""), 0, 0, 1, 1);

		// Se añade sl spinner de numero de habitaciones
		setTamano(GridBagConstraints.BOTH, 1, 1);
		this.addComponent(pNumHabs, 1, 0, 2, 1);

		// Se añade el comboBox de los tipos de habitación
		setTamano(GridBagConstraints.BOTH, 1, 3);
		this.addComponent(pTipos, 2, 0, 2, 1);

		// Se añaden los paneles referentes a niños en la habitación
		setTamano(GridBagConstraints.BOTH, 1, 3);
		JPanel ckbPanelNinios = new JPanel(new BorderLayout());
		ckbPanelNinios.add(ckbNinios, BorderLayout.CENTER);
		this.addComponent(ckbPanelNinios, 3, 0, 1, 1);
		this.addComponent(bNinios, 3, 1, 1, 1);

		// ---SEPARACION---//
		this.addComponent(new JLabel(""), 4, 0, 2, 1);
		setTamano(GridBagConstraints.BOTH, 1, 5);
		this.addComponent(pImporte, 5, 0, 2, 1);
		// ---SEPARACION---//

		// Se crea un textfield que funciona como una label
		setTamano(GridBagConstraints.BOTH, 2, 0.5);
		tfBarra = new JTextField("Barra de dinero máximo posible en un dia: ");
		tfBarra.setBorder(null);
		tfBarra.setHorizontalAlignment(JTextField.CENTER);
		tfBarra.setEditable(false);
		tfBarra.setFont(fuenteNegrita);
		tfBarra.setForeground(Color.ORANGE);
		tfBarra.setToolTipText("Barra que muestra hasta donde se puede seguir añadiendo coste a un dia a tiempo real");
		this.addComponent(tfBarra, 6, 0, 2, 1);
		// Se crea la progressbar
		barra = new JProgressBar(0, 6270);
		barra.setForeground(Color.YELLOW);
		barra.setToolTipText("Barra que muestra hasta donde se puede seguir añadiendo coste a un dia a tiempo real");
		this.addComponent(barra, 7, 0, 2, 1);

		// ---SEPARACION---//
		JLabel separacion1 = new JLabel("");
		separacion1.setBackground(new Color(48, 136, 149));
		separacion1.setOpaque(true);
		this.addComponent(separacion1, 8, 0, 3, 1);
		// ---SEPARACION---//

		// Se añade el panel que contiene las imágenes
		setTamano(GridBagConstraints.BOTH, 1, 5);
		this.addComponent(imagen, 9, 0, 2, 1);

		this.addComponentListener(this);
	}

	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ckbNinios) {
			if (ckbNinios.isSelected())
				bNinios.setVisible(true);
			else
				bNinios.setVisible(false);
			if (!spNumHab.getValue().toString().matches("0"))
				calcular();
		}
		if (e.getSource() == cmbBTipo)
			calcular();
	}

	/**
	 * State changed.
	 *
	 * @param e the e
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == spEdad)
			if ((int) spEdad.getValue() >= 0 && (int) spEdad.getValue() <= 3)
				lblExtras.setText("Cuna");
			else if ((int) spEdad.getValue() >= 4 && (int) spEdad.getValue() <= 10)
				lblExtras.setText("Cama supletoria pequeña");
			else if ((int) spEdad.getValue() >= 11 && (int) spEdad.getValue() <= 14)
				lblExtras.setText("Cama supletoria normal");

		if (e.getSource() == spNumHab) {
			calcular();
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

		fuenteNegrita = new Font("Arial", Font.BOLD, altoVentana / 50);
		fuentePlano = new Font("Arial", Font.PLAIN, altoVentana / 50);
		fuenteItalic = new Font("Arial", Font.ITALIC, altoVentana / 50);
		color = new Color(11, 72, 107);

		lblNumHab.setFont(fuenteNegrita);
		lblNumHab.setForeground(color);

		tfTipoHab.setFont(fuenteNegrita);
		tfTipoHab.setForeground(color);
		tfTipoHab.setMaximumSize(new Dimension(anchoVentana / 3, altoVentana / 50));
		tfTipoHab.setEditable(false);
		tfTipoHab.setBorder(null);
		tfTipoHab.setFocusable(false);

		lblExtras.setFont(fuentePlano);
		lblEdadNinios.setFont(fuentePlano);

		if (pista)
			tfImporte.setFont(fuenteItalic);
		else
			tfImporte.setFont(fuentePlano);

		ckbNinios.setFont(fuenteNegrita);
		ckbNinios.setForeground(color);

		cmbBTipo.setFont(fuentePlano);

		spNumHab.setPreferredSize(new Dimension(anchoVentana / 40, altoVentana / 40));
		spNumHab.setFont(fuentePlano);
		spEdad.setPreferredSize(new Dimension(anchoVentana / 40, altoVentana / 40));
		spEdad.setFont(fuentePlano);

		tfImporte.setPreferredSize(new Dimension(anchoVentana / 4, altoVentana / 40));

		imagen.setMaximumSize(new Dimension(this.getParent().getSize().width / 4, altoVentana / 5));

		tfBarra.setFont(fuenteNegrita);
		tfBarra.setForeground(color);

		borde = this.getParent().getSize().height / 200;
	}

	/**
	 * Focus gained.
	 *
	 * @param e the e
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == tfImporte) {
			pista = false;
			tfImporte.setFont(fuentePlano);
			calcular();
			importe *=Integer.parseInt(pDatosPersona.tfDias.getText());
			if(!pDatosPersona.tfDias.getText().contentEquals("0"))
			if (!spNumHab.getValue().toString().contentEquals("0") && importe != 0) {
				tfImporte.setText("Importe total: " + importe + "€");
				tfImporte.setForeground(Color.BLACK);
			} else {
				tfImporte.setText("Debe introducir al menos una habitación");
				tfImporte.setForeground(Color.RED);
			}
			else {
				tfImporte.setText("Debe introducir una fecha válida");
				tfImporte.setForeground(Color.RED);
			}
		}
	}

	/**
	 * Método para calcular a tiempo real el coste actual y se implementa a la barra
	 * de progreso
	 */
	void calcular() {
		switch (cmbBTipo.getSelectedItem().toString()) {
		case "Simple":
			importe = Integer.parseInt(spNumHab.getValue().toString()) * 50;
			break;
		case "Doble":
			importe = Integer.parseInt(spNumHab.getValue().toString()) * 75;
			break;
		case "Suite":
			importe = Integer.parseInt(spNumHab.getValue().toString()) * 125;
			break;
		}
		if (ckbNinios.isSelected())
			importe += 20;
		
		
		// Ligero detalle que hace que la barra cambie de color dependiendo del valor
		// que contenga la barra de progreso
		if (barra.getValue() <= barra.getMaximum() / 3)
			barra.setForeground(Color.YELLOW);
		else if (barra.getValue() >= (barra.getMaximum() / 3 * 2))
			barra.setForeground(Color.RED);
		else if (barra.getValue() >= barra.getMaximum() / 3 && barra.getValue() <= (barra.getMaximum() / 3 * 2))
			barra.setForeground(Color.ORANGE);
		barra.setValue(importe);
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
		constraints.weightx = 1;
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
	public void focusLost(FocusEvent arg0) {
	}

}
