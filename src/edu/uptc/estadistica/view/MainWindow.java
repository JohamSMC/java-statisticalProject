package edu.uptc.estadistica.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.uptc.estadistica.control.ControlEstadistica;

public class MainWindow extends JFrame {

	private MainMenu mainMenu;
	private JButton datosAgrupados;
	private JButton datosNoAgrupados;
	private JButton exit;

	public MainWindow() {

		setTitle("ANALISIS DE DATOS");
		setSize(new Dimension(400, 500));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);

		setLayout(new BorderLayout());
		setIconImage(new ImageIcon("resource/images/icono.jpg").getImage());

		beginComponests();
		addComponets();
		close();

	}

	private void beginComponests() {
		mainMenu = new MainMenu();

		datosNoAgrupados = new JButton("Datos en Lista");
		datosNoAgrupados.setActionCommand("DATESNO");
		datosNoAgrupados.setFont(new Font("Arial", Font.ITALIC, 15));

		datosAgrupados = new JButton("Datos por Intervalos");
		datosAgrupados.setActionCommand("DATESYES");
		datosAgrupados.setFont(new Font("Arial", Font.ITALIC, 15));

		exit = new JButton("SALIR");
		exit.setActionCommand("EXIT");
		exit.setFont(new Font("Arial", Font.BOLD, 12));

	}

	private void addComponets() {
		JPanel pnlBut = new JPanel();
		pnlBut.setLayout(null);

		JLabel title = new JLabel("ANALISIS DE DATOS");
		title.setBounds(60, 0, 300, 100);
		title.setFont(new Font("Verdana", 3, 25));
		title.setForeground(new Color(17, 120, 100));

		JLabel imageInquiry = new JLabel();
		imageInquiry.setBounds(50, 80, 300, 200);
		ImageIcon inquiry = new ImageIcon("resource/images/encuesta.jpg");

		Icon icono = new ImageIcon(inquiry.getImage().getScaledInstance(imageInquiry.getWidth(),
				imageInquiry.getHeight(), Image.SCALE_DEFAULT));
		imageInquiry.setIcon(icono);

		datosNoAgrupados.setBounds(100, 300, 200, 30);
		datosAgrupados.setBounds(100, 350, 200, 30);
		exit.setBounds(150, 400, 100, 25);

		pnlBut.add(title);
		pnlBut.add(imageInquiry);
		pnlBut.add(datosAgrupados);
		pnlBut.add(datosNoAgrupados);
		pnlBut.add(exit);

		add(pnlBut);
		setJMenuBar(mainMenu);

	}

	public void close() {

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int valor;
				do {
					valor = JOptionPane.showConfirmDialog(null, "¿DESEA SALIR?", "ADVERTENCIA",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				} while (valor != JOptionPane.OK_OPTION && valor != JOptionPane.CANCEL_OPTION
						&& valor != JOptionPane.CLOSED_OPTION);

				if (valor == JOptionPane.OK_OPTION) {
					setVisible(false);

					JOptionPane.showMessageDialog(null, "GRACIAS POR USAR LA APLICACION");
					System.exit(0);
				}

			}
		});

	}

	public void btnClosed() {
		int valor;

		valor = JOptionPane.showConfirmDialog(null, "¿DESEA SALIR?", "ADVERTENCIA", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE);

		if (valor == JOptionPane.OK_OPTION) {
			setVisible(false);
			JOptionPane.showMessageDialog(null, "GRACIAS POR USAR LA APLICACION");
			System.exit(0);
		}
	}

	public void setControl(ControlEstadistica control) {

		mainMenu.setControl(control);

		datosAgrupados.addActionListener(control);
		datosNoAgrupados.addActionListener(control);
		exit.addActionListener(control);

	}

	public void begin() {
		setVisible(true);

	}

}
