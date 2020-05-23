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
import edu.uptc.estadistica.model.MngGroupedData;
import edu.uptc.estadistica.model.GroupedData;
import edu.uptc.estadistica.model.NonGroupedData;

public class WindowDatosAgrupados extends JFrame {

	private analisisDatos analisisDatos;
	private JTabbedPane pestañas;
	private JPanel pnlBut;
	private JButton addDates;
	private JButton updateDates;
	private JButton deleteDates;
	private JButton deleteAll;
	private JButton viewAnalisis;
	private JButton exit;
	private JTextField limInf;
	private JTextField limSup;
	private JTextField freaAbs;
	private JTable tableDates;

	public WindowDatosAgrupados() {
		setTitle("GESTION DATOS AGRUPADOS");
		setSize(new Dimension(1000, 500));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());

		setIconImage(new ImageIcon("resource/images/icono.jpg").getImage());

		beginComponests();
		addComponets();
		close();

		validateInput(limInf, true);
		validateInput(limSup, true);
		validateInput(freaAbs, false);

	}

	private void beginComponests() {
		pestañas = new JTabbedPane();
		analisisDatos = new analisisDatos();

		addDates = new JButton("AÑADIR DATO");
		addDates.setActionCommand("addDates");
		addDates.setFont(new Font("Arial", Font.ITALIC, 15));

		updateDates = new JButton("MODIFICAR DATO");
		updateDates.setActionCommand("updateDates");
		updateDates.setFont(new Font("Arial", Font.ITALIC, 15));

		deleteDates = new JButton("ELIMINAR DATO");
		deleteDates.setActionCommand("deleteDates");
		deleteDates.setFont(new Font("Arial", Font.ITALIC, 15));

		deleteAll = new JButton("ELIMINAR TODO");
		deleteAll.setActionCommand("deleteAll");
		deleteAll.setFont(new Font("Arial", Font.ITALIC, 15));

		viewAnalisis = new JButton("VER ESTADISTICAS");
		viewAnalisis.setActionCommand("viewDates");
		viewAnalisis.setFont(new Font("Arial", Font.ITALIC, 15));

		exit = new JButton("SALIR");
		exit.setActionCommand("EXITDates");
		exit.setFont(new Font("Arial", Font.BOLD, 15));

		limInf = new JTextField();
		limSup = new JTextField();
		freaAbs = new JTextField();

		tableDates = new JTable(new DefaultTableModel());
		JScrollPane JS = new JScrollPane(tableDates);
		JS.setBounds(620, 120, 350, 250);
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
		updateDates.setBounds(75, 200, 200, 30);
		deleteDates.setBounds(75, 280, 200, 30);
		deleteAll.setBounds(75, 320, 200, 30);
		viewAnalisis.setBounds(75, 370, 200, 30);
		exit.setBounds(400, 400, 100, 20);

		JLabel texLimInf = new JLabel("Limite Inferior:");
		texLimInf.setBounds(300, 80, 200, 30);
		texLimInf.setFont(new Font("Verdana", 2, 18));
		texLimInf.setForeground(new Color(84, 178, 124));

		limInf.setBounds(450, 85, 100, 25);

		JLabel texLimSup = new JLabel("Limite Superior:");
		texLimSup.setBounds(300, 110, 200, 30);
		texLimSup.setFont(new Font("Verdana", 2, 18));
		texLimSup.setForeground(new Color(84, 178, 124));

		limSup.setBounds(450, 115, 100, 25);

		JLabel textFreAbs = new JLabel("Frecuencia:");
		textFreAbs.setBounds(300, 140, 200, 30);
		textFreAbs.setFont(new Font("Verdana", 2, 18));
		textFreAbs.setForeground(new Color(84, 178, 124));

		freaAbs.setBounds(450, 145, 100, 25);

		JLabel textTable = new JLabel(" Tabla de Datos");
		textTable.setBounds(680, 40, 250, 100);
		textTable.setFont(new Font("Verdana", 3, 27));
		textTable.setForeground(new Color(26, 82, 118));

		pnlBut.add(title);
		pnlBut.add(addDates);
		pnlBut.add(updateDates);
		pnlBut.add(deleteDates);
		pnlBut.add(deleteAll);
		pnlBut.add(viewAnalisis);
		pnlBut.add(exit);

		pnlBut.add(limInf);
		pnlBut.add(limSup);
		pnlBut.add(freaAbs);

		pnlBut.add(texLimInf);
		pnlBut.add(texLimSup);
		pnlBut.add(textFreAbs);

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
			this.dispose();
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
	}

	public void validateInput(JTextField textField, boolean decimal) {
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char Input = e.getKeyChar();
				if (decimal) {
					if ((Input < '0' || Input > '9') && (Input != KeyEvent.VK_BACK_SPACE) && (Input != '.')) {
						getToolkit().beep();
						e.consume();
					}
				} else {
					if ((Input < '0' || Input > '9') && (Input != KeyEvent.VK_BACK_SPACE)) {
						getToolkit().beep();
						e.consume();
					}

				}

			}
		});
	}

	public void drawTableDates(ArrayList<GroupedData> dates) {
		tableDates.removeAll();
		String[] nameColumns = { "Lim. Inferior", "Lim. Superior", "Fre. Adsoluta", "Marca Clase" };
		String[][] tableDate = new String[dates.size()][4];

		int counter = 0;

		for (GroupedData date : dates) {
			tableDate[counter][0] = String.valueOf(date.getLimInf());
			tableDate[counter][1] = String.valueOf(date.getLimSup());
			tableDate[counter][2] = String.valueOf(date.getFreAbs());
			tableDate[counter][3] = String.valueOf(date.getMarCla());
			counter++;

		}

		DefaultTableModel model = new DefaultTableModel(tableDate, nameColumns);
		// tableDates.setEnabled(false);
		tableDates.setModel(model);

	}

	public Object[] readDates() {
		Object[] objects = new Object[] { limInf.getText(), limSup.getText(), freaAbs.getText()

		};
		return objects;
	}

	public void suggestLowerLimit(ArrayList<GroupedData> dates) {
		limInf.setText(String.valueOf(dates.get(dates.size() - 1).getLimSup()));
		limSup.setText("");
		freaAbs.setText("");
	}

	public double deleteDate() {
		int row = tableDates.getSelectedRow();
		if (row != -1) {
			return Double.parseDouble((String) tableDates.getValueAt(row, 3));
		}
		tableDates.clearSelection();
		return row;

	}

	public void writeAnalysis(MngGroupedData dates) {
		pestañas.setEnabledAt(1, true);
		pestañas.setSelectedIndex(1);
		analisisDatos.addGropedData(dates);

	}

	public void setControl(ControlEstadistica control) {
		addDates.addActionListener(control);
		updateDates.addActionListener(control);
		deleteDates.addActionListener(control);
		deleteAll.addActionListener(control);
		viewAnalisis.addActionListener(control);
		exit.addActionListener(control);
	}

	public void begin() {
		setVisible(true);

	}

}
