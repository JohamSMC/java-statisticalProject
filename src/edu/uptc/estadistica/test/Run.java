package edu.uptc.estadistica.test;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.uptc.estadistica.control.ControlEstadistica;
import edu.uptc.estadistica.model.MngGroupedData;
import edu.uptc.estadistica.model.MngNonGroupedData;
import edu.uptc.estadistica.view.MainWindow;
import edu.uptc.estadistica.view.WindowDatosAgrupados;
import edu.uptc.estadistica.view.WindowDatosNoAgrupados;

public class Run {

	public static void main(String[] args) {

		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MainWindow mainWindow = new MainWindow();
		WindowDatosAgrupados windowDatosAgrupados = new WindowDatosAgrupados();
		WindowDatosNoAgrupados windowDatosNoAgrupados = new WindowDatosNoAgrupados();
		MngGroupedData datosAgrupados = new MngGroupedData();
		MngNonGroupedData datosNoAgrupados = new MngNonGroupedData();

		ControlEstadistica control = new ControlEstadistica(mainWindow, windowDatosAgrupados, windowDatosNoAgrupados,
				datosAgrupados, datosNoAgrupados);

		mainWindow.setControl(control);
		windowDatosNoAgrupados.setControl(control);
		windowDatosAgrupados.setControl(control);

		mainWindow.begin();
		JOptionPane.showMessageDialog(null, "Utilize el [.] como separador de cifras decimales", "Bienvenidos",
				JOptionPane.INFORMATION_MESSAGE);

	}

}
