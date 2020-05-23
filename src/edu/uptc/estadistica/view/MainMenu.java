package edu.uptc.estadistica.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import edu.uptc.estadistica.control.ControlEstadistica;

public class MainMenu extends JMenuBar {

	private JMenu help;

	private JDialog information;

	private JMenuItem about;
	private JMenuItem exit;

	public MainMenu() {
		beginComponents();
		addComponents();

	}

	private void beginComponents() {
		information = new JDialog();
		information.setTitle("Acerca de");
		information.setModal(true);
		information.setResizable(false);
		information.setBounds(20, 40, 350, 500);
		information.setLayout(new GridLayout(2, 1));
		information.add(new JLabel(new ImageIcon("resource/images/logotipo.png")));
		JLabel autor = new JLabel(
				"<html><P ALIGN=center>Creado por:<br>JOHAM SEBASTIAN MEDINA CORREDOR<br>&<br>HERNAN ALIRIO CADENA GONZALES<br><br>VERSION 1.0<br>COPYRIGHT© 2017<br>ALL RIGHTS RESERVED</html>");
		autor.setForeground(new Color(11, 83, 69));
		autor.setVerticalAlignment(JLabel.TOP);
		autor.setHorizontalAlignment(JLabel.CENTER);
		information.add(autor);

		help = new JMenu("Ayuda");
		help.setMnemonic('V');

		about = new JMenuItem("Acerca de");
		about.setActionCommand("information");

		exit = new JMenuItem("Salir");
		exit.setActionCommand("EXIT");

	}

	private void addComponents() {

		help.add(about);
		help.add(exit);

		this.add(help);

	}

	public void setControl(ControlEstadistica control) {
		exit.addActionListener(control);
		about.addActionListener(control);

	}

	public void getInformation() {
		information.setVisible(true);
	}

}
