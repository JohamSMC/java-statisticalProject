package edu.uptc.estadistica.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.uptc.estadistica.model.MngGroupedData;
import edu.uptc.estadistica.model.MngNonGroupedData;
import edu.uptc.estadistica.model.GroupedData;
import edu.uptc.estadistica.model.NonGroupedData;
import edu.uptc.estadistica.view.MainMenu;
import edu.uptc.estadistica.view.MainWindow;
import edu.uptc.estadistica.view.WindowDatosAgrupados;
import edu.uptc.estadistica.view.WindowDatosNoAgrupados;

public class ControlEstadistica implements ActionListener {

	private MainWindow mainWindow;
	private WindowDatosNoAgrupados windowDatosNoAgrupados;
	private WindowDatosAgrupados windowDatosAgrupados;
	private MngGroupedData datosAgrupados;
	private MngNonGroupedData datosNoAgrupados;

	public ControlEstadistica(MainWindow mainWindow, WindowDatosAgrupados windowDatosAgrupados,
			WindowDatosNoAgrupados windowDatosNoAgrupados, MngGroupedData datosAgrupados,
			MngNonGroupedData datosNoAgrupados) {
		this.mainWindow = mainWindow;
		this.datosAgrupados = datosAgrupados;
		this.datosNoAgrupados = datosNoAgrupados;
		this.windowDatosNoAgrupados = windowDatosNoAgrupados;
		this.windowDatosAgrupados = windowDatosAgrupados;
		datosAgrupados.addDate(new GroupedData(7.09, 7.37, 12));
		datosAgrupados.addDate(new GroupedData(7.37, 7.65, 16));
		datosAgrupados.addDate(new GroupedData(7.65, 7.93, 32));
		datosAgrupados.addDate(new GroupedData(7.93, 8.21, 45));
		datosAgrupados.addDate(new GroupedData(8.21, 8.49, 31));
		datosAgrupados.addDate(new GroupedData(8.49, 8.77, 14));
		datosAgrupados.addDate(new GroupedData(8.77, 9.05, 12));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {

		// EVENTOS DATOS NO AGRUPADOS

		case "DATESNO":
			windowDatosNoAgrupados.begin();
			windowDatosNoAgrupados.drawTableDates(datosNoAgrupados.sortInsert(true));

			break;
		case "addDatesNO":
			Object[] aux = windowDatosNoAgrupados.readDates();
			if (aux[0].toString().equals("") || aux[1].toString().equals("")) {
				JOptionPane.showMessageDialog(null, "Faltan datos por ingresar", "Error", JOptionPane.WARNING_MESSAGE);
				break;
			}
			datosNoAgrupados.addDate(
					new NonGroupedData(Double.parseDouble(aux[0].toString()), Integer.parseInt(aux[1].toString())));
			windowDatosNoAgrupados.drawTableDates(datosNoAgrupados.sortInsert(true));

			break;
		case "deleteDatesNO":
			double tempDate = windowDatosNoAgrupados.deleteDate();
			if (tempDate != -1) {

				if (datosNoAgrupados.deteleDate(tempDate)) {
					windowDatosNoAgrupados.drawTableDates(datosNoAgrupados.sortInsert(true));
					JOptionPane.showMessageDialog(null, "Se elimino correctamente el DATO", "EXITO",
							JOptionPane.WARNING_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Seleccione la FILA en la TABLA que desea ELIMINAR", "Error",
						JOptionPane.WARNING_MESSAGE);
			}

			break;
		case "viewDatesNO":
			if (datosNoAgrupados.getDates().isEmpty()) {
				JOptionPane.showMessageDialog(null, "NO HAY DATOS", "ERROR", JOptionPane.WARNING_MESSAGE);

			} else {
				windowDatosNoAgrupados.writeAnalysis(datosNoAgrupados);
			}

			break;

		// EVENTOS DATOS AGRUPADOS
		case "DATESYES":
			windowDatosAgrupados.begin();
			windowDatosAgrupados.drawTableDates(datosAgrupados.sortInsert(true));
			break;

		case "addDates":
			Object[] aux2 = windowDatosAgrupados.readDates();
			if (aux2[0].toString().equals("") || aux2[1].toString().equals("") || aux2[2].toString().equals("")) {
				JOptionPane.showMessageDialog(null, "Faltan datos por ingresar", "Error", JOptionPane.WARNING_MESSAGE);
				break;
			} else if (aux2[0].toString().equals(aux2[1].toString())) {
				JOptionPane.showMessageDialog(null, "Limite Superior e Snferior IGUALES", "Error",
						JOptionPane.WARNING_MESSAGE);
				break;

			} else if (Double.parseDouble(aux2[0].toString()) > Double.parseDouble(aux2[1].toString())) {
				JOptionPane.showMessageDialog(null, "Limite Inferior MAYOR a Limite Superrior", "Error",
						JOptionPane.WARNING_MESSAGE);
				break;
			}
			if (datosAgrupados.addDate(new GroupedData(Double.parseDouble(aux2[0].toString()),
					Double.parseDouble(aux2[1].toString()), Integer.parseInt(aux2[2].toString())))) {

			} else {
				JOptionPane.showMessageDialog(null, "Limite Inferior y/o Superrior NO VALIDOS", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
			windowDatosAgrupados.suggestLowerLimit(datosAgrupados.sortInsert(true));
			windowDatosAgrupados.drawTableDates(datosAgrupados.sortInsert(true));

			break;
		case "deleteDates":
			double tempDates = windowDatosAgrupados.deleteDate();
			if (tempDates != -1) {

				if (datosAgrupados.deteleDate(tempDates)) {
					windowDatosAgrupados.drawTableDates(datosAgrupados.sortInsert(true));
					JOptionPane.showMessageDialog(null, "Se elimino correctamente el DATO", "EXITO",
							JOptionPane.WARNING_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Seleccione la FILA en la TABLA que desea ELIMINAR", "Error",
						JOptionPane.WARNING_MESSAGE);
			}

			break;

		case "viewDates":
			if (datosAgrupados.getDates().isEmpty()) {
				JOptionPane.showMessageDialog(null, "NO HAY DATOS", "ERROR", JOptionPane.WARNING_MESSAGE);

			} else {
				windowDatosAgrupados.writeAnalysis(datosAgrupados);
			}

			break;

		// Muestra la Informacion de los Creadores del Programa

		case "information":
			new MainMenu().getInformation();
			break;

		// CERRAR VENTANA DATOS NO AGRUPADOS
		case "EXITDatesNO":
			datosNoAgrupados.deleteAll();
			windowDatosNoAgrupados.closed();

			// Borra los datos del a ventana de Datos no Agrupados
			break;
		case "deleteAllNO":
			datosNoAgrupados.deleteAll();
			windowDatosNoAgrupados.drawTableDates(datosNoAgrupados.sortInsert(true));
			break;
		// CERRAR VENTANA DATOS AGRUPADOS
		case "EXITDates":
			datosAgrupados.deleteAll();
			windowDatosAgrupados.closed();

			// Borra los datos del a ventana de Datos Agrupados
			break;
		case "deleteAll":
			datosAgrupados.deleteAll();
			windowDatosAgrupados.drawTableDates(datosAgrupados.sortInsert(true));
			break;

		case "EXITMAIN":
			mainWindow.close();
			break;

		case "EXIT":
			mainWindow.btnClosed();
		}

	}

}
