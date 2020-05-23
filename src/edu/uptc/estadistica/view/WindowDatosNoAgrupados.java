package edu.uptc.estadistica.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.uptc.estadistica.control.ControlEstadistica;
import edu.uptc.estadistica.model.MngNonGroupedData;
import edu.uptc.estadistica.model.NonGroupedData;

public class WindowDatosNoAgrupados extends JFrame {

	private analisisDatos analisisDatos;
	private JTabbedPane pestañas;
	private JPanel pnlBut;
	private JButton addDates;
	private JButton updateDates;
	private JButton deleteDates;
	private JButton deleteAll;
	private JButton viewAnalisis;
	private JButton exit;
	private JTextField value;
	private JTextField times;
	private JTable tableDates;

	public WindowDatosNoAgrupados() {

		setTitle("GESTION DATOS EN LISTA");
		setSize(new Dimension(1000, 500));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());

		setIconImage(new ImageIcon("resource/images/icono.jpg").getImage());

		beginComponests();
		addComponets();
		close();

		validateInput(value);
		validateInput(times);

	}

	private void beginComponests() {
		pestañas = new JTabbedPane();
		analisisDatos = new analisisDatos();

		addDates = new JButton("AÑADIR DATO");
		addDates.setActionCommand("addDatesNO");
		addDates.setFont(new Font("Arial", Font.ITALIC, 15));

		updateDates = new JButton("MODIFICAR DATO");
		updateDates.setActionCommand("updateDatesNO");
		updateDates.setFont(new Font("Arial", Font.ITALIC, 15));

		deleteDates = new JButton("ELIMINAR DATO");
		deleteDates.setActionCommand("deleteDatesNO");
		deleteDates.setFont(new Font("Arial", Font.ITALIC, 15));

		deleteAll = new JButton("ELIMINAR TODO");
		deleteAll.setActionCommand("deleteAllNO");
		deleteAll.setFont(new Font("Arial", Font.ITALIC, 15));

		viewAnalisis = new JButton("VER ESTADISTICAS");
		viewAnalisis.setActionCommand("viewDatesNO");
		viewAnalisis.setFont(new Font("Arial", Font.ITALIC, 15));

		exit = new JButton("SALIR");
		exit.setActionCommand("EXITDatesNO");
		exit.setFont(new Font("Arial", Font.BOLD, 15));

		value = new JTextField();
		times = new JTextField();

		tableDates = new JTable(new DefaultTableModel());
		JScrollPane JS = new JScrollPane(tableDates);
		JS.setBounds(690, 120, 270, 250);
		pnlBut = new JPanel();
		pnlBut.setLayout(null);
		pnlBut.add(JS);

	}

	private void addComponets() {
		JLabel title = new JLabel("GESTION DE ENCUESTA");
		title.setBounds(270, 0, 500, 100);
		title.setFont(new Font("Verdana", 3, 30));
		title.setForeground(new Color(135, 54, 0));

		addDates.setBounds(75, 110, 200, 30);
		updateDates.setBounds(75, 180, 200, 30);
		deleteDates.setBounds(75, 250, 200, 30);
		deleteAll.setBounds(75, 300, 200, 30);
		viewAnalisis.setBounds(75, 350, 200, 30);
		exit.setBounds(400, 400, 100, 20);

		JLabel texValue = new JLabel("Valor:");
		texValue.setBounds(300, 80, 100, 30);
		texValue.setFont(new Font("Verdana", 2, 18));
		texValue.setForeground(new Color(84, 178, 124));

		value.setBounds(400, 85, 100, 25);

		JLabel texTimes = new JLabel("# Veces:");
		texTimes.setBounds(300, 110, 100, 30);
		texTimes.setFont(new Font("Verdana", 2, 18));
		texTimes.setForeground(new Color(84, 178, 124));

		times.setBounds(400, 115, 100, 25);
		times.setText("1");

		JLabel textTable = new JLabel(" Tabla de Datos");
		textTable.setBounds(700, 40, 250, 100);
		textTable.setFont(new Font("Verdana", 3, 25));
		textTable.setForeground(new Color(26, 82, 118));

		pnlBut.add(title);
		pnlBut.add(addDates);
		pnlBut.add(updateDates);
		pnlBut.add(deleteDates);
		pnlBut.add(deleteAll);
		pnlBut.add(viewAnalisis);
		pnlBut.add(exit);

		pnlBut.add(value);
		pnlBut.add(times);

		pnlBut.add(texValue);
		pnlBut.add(texTimes);

		pnlBut.add(textTable);

		pestañas.add("GESTION", pnlBut);
		pestañas.add("ANALISIS", analisisDatos);
		pestañas.setEnabledAt(1, false);
		add(pestañas);

	}

	public void close() {

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				int valor = JOptionPane.showConfirmDialog(null,
						"¿DESEA SALIR?\nTODOS LOS DATORS DE LA\n ENCUESTA SE PERDERAN", "ADVERTENCIA",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

				if (valor == JOptionPane.OK_OPTION) {
					setVisible(false);
					pestañas.setEnabledAt(1, false);
					pestañas.setSelectedIndex(0);
					dispose();
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}

			}
		});

	}

	public void closed() {
		int valor;

		valor = JOptionPane.showConfirmDialog(null, "¿DESEA SALIR?\nTODOS LOS DATORS DE LA\n ENCUESTA SE PERDERAN",
				"ADVERTENCIA", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

		if (valor == JOptionPane.OK_OPTION) {
			this.setVisible(false);
			pestañas.setEnabledAt(1, false);
			pestañas.setSelectedIndex(0);
			this.dispose();
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
	}

	public void validateInput(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char Input = e.getKeyChar();
				if ((Input < '0' || Input > '9') && (Input != KeyEvent.VK_BACK_SPACE) && (Input != '.')) {
					getToolkit().beep();
					e.consume();
				}

			}
		});
	}

	public void drawTableDates(ArrayList<NonGroupedData> dates) {
		tableDates.removeAll();
		String[] nameColumns = { "VALOR", "# VECES" };
		String[][] tableDate = new String[dates.size()][2];

		int counter = 0;

		for (NonGroupedData date : dates) {
			tableDate[counter][0] = String.valueOf(date.getValue());
			tableDate[counter][1] = String.valueOf(date.getTimes());
			counter++;

		}

		DefaultTableModel model = new DefaultTableModel(tableDate, nameColumns);
		// tableDates.setEnabled(false);
		tableDates.setModel(model);

	}

	public double deleteDate() {
		int row = tableDates.getSelectedRow();
		if (row != -1) {
			return Double.parseDouble((String) tableDates.getValueAt(row, 0));
		}
		tableDates.clearSelection();
		return row;

	}

	public void setControl(ControlEstadistica control) {
		addDates.addActionListener(control);
		updateDates.addActionListener(control);
		deleteDates.addActionListener(control);
		deleteAll.addActionListener(control);
		viewAnalisis.addActionListener(control);
		exit.addActionListener(control);
	}

	public Object[] readDates() {
		Object[] objects = new Object[] { value.getText(), times.getText()

		};
		value.setText("");
		times.setText("1");
		return objects;
	}

	public void writeAnalysis(MngNonGroupedData dates) {
		pestañas.setEnabledAt(1, true);
		pestañas.setSelectedIndex(1);
		analisisDatos.addNonGropedData(dates);
	}

	public void begin() {
		setVisible(true);

	}

}