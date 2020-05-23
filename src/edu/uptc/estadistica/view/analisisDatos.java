package edu.uptc.estadistica.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.uptc.estadistica.model.MngGroupedData;
import edu.uptc.estadistica.model.MngNonGroupedData;

public class analisisDatos extends JPanel {

	private JLabel media;
	private JLabel mediana;
	private JLabel moda;
	private JLabel desviacionMedia;
	private JLabel varianza;
	private JLabel desviacionEstandar;
	private JLabel coeficienteVariacion;
	private JLabel coeficienteAsimetria;
	private JLabel kurtosis;

	public analisisDatos() {

		this.setLayout(null);

		beginComponests();
	}

	public void beginComponests() {

		media = new JLabel("0");
		mediana = new JLabel("0");
		moda = new JLabel("0");
		desviacionMedia = new JLabel("0");
		varianza = new JLabel("0");
		desviacionEstandar = new JLabel("0");
		coeficienteVariacion = new JLabel("0");
		coeficienteAsimetria = new JLabel("0");
		kurtosis = new JLabel("0");

		JLabel title = new JLabel("ANALISIS DE ENCUESTA");
		title.setBounds(270, 0, 500, 100);
		title.setFont(new Font("Verdana", 3, 30));
		title.setForeground(new Color(176, 58, 46));

		JLabel textMedia = new JLabel("Media:");
		textMedia.setBounds(50, 100, 100, 30);
		textMedia.setFont(new Font("Verdana", 1, 15));
		// textMedia.setForeground(new Color(135, 54, 0));

		media.setBounds(150, 100, 100, 30);
		media.setFont(new Font("Verdana", 2, 15));

		JLabel textMediana = new JLabel("Mediana:");
		textMediana.setBounds(50, 130, 100, 30);
		textMediana.setFont(new Font("Verdana", 1, 15));
		// textMediana.setForeground(new Color(135, 54, 0));

		mediana.setBounds(150, 130, 100, 30);
		mediana.setFont(new Font("Verdana", 2, 15));

		JLabel textModa = new JLabel("Moda:");
		textModa.setBounds(50, 160, 100, 30);
		textModa.setFont(new Font("Verdana", 1, 15));
		// textModa.setForeground(new Color(135, 54, 0));

		moda.setBounds(150, 160, 400, 30);
		moda.setFont(new Font("Verdana", 2, 15));

		JLabel textDesviacionMedia = new JLabel("Desviacion Media:");
		textDesviacionMedia.setBounds(50, 190, 250, 30);
		textDesviacionMedia.setFont(new Font("Verdana", 1, 15));
		// textDesviacionMedia.setForeground(new Color(135, 54, 0));

		desviacionMedia.setBounds(270, 190, 200, 30);
		desviacionMedia.setFont(new Font("Verdana", 2, 15));

		JLabel textVarianza = new JLabel("Varianza:");
		textVarianza.setBounds(50, 220, 250, 30);
		textVarianza.setFont(new Font("Verdana", 1, 15));
		// textVarianza.setForeground(new Color(135, 54, 0));

		varianza.setBounds(270, 220, 200, 30);
		varianza.setFont(new Font("Verdana", 2, 15));

		JLabel textDesviacionEstandar = new JLabel("Desviacion Estandar:");
		textDesviacionEstandar.setBounds(50, 250, 250, 30);
		textDesviacionEstandar.setFont(new Font("Verdana", 1, 15));
		// textDesviacionEstandar.setForeground(new Color(135, 54, 0));

		desviacionEstandar.setBounds(270, 250, 200, 30);
		desviacionEstandar.setFont(new Font("Verdana", 2, 15));

		JLabel textCoeficienteVariacion = new JLabel("Coeficiente de Variación:");
		textCoeficienteVariacion.setBounds(50, 280, 250, 30);
		textCoeficienteVariacion.setFont(new Font("Verdana", 1, 15));
		// textCoeficienteVariacion.setForeground(new Color(135, 54, 0));

		coeficienteVariacion.setBounds(270, 280, 200, 30);
		coeficienteVariacion.setFont(new Font("Verdana", 2, 15));

		JLabel textCoeficienteAsimetria = new JLabel("Coeficiente de Asimetría:");
		textCoeficienteAsimetria.setBounds(50, 310, 250, 30);
		textCoeficienteAsimetria.setFont(new Font("Verdana", 1, 15));
		// textCoeficienteAsimetria.setForeground(new Color(135, 54, 0));

		coeficienteAsimetria.setBounds(270, 310, 200, 30);
		coeficienteAsimetria.setFont(new Font("Verdana", 2, 15));

		JLabel textKurtosis = new JLabel("Kurtosis:");
		textKurtosis.setBounds(50, 340, 100, 30);
		textKurtosis.setFont(new Font("Verdana", 1, 15));
		// textKustosis.setForeground(new Color(135, 54, 0));

		kurtosis.setBounds(270, 340, 200, 30);
		kurtosis.setFont(new Font("Verdana", 2, 15));

		add(title);
		add(textMedia);
		add(textMediana);
		add(textModa);
		add(textDesviacionMedia);
		add(textVarianza);
		add(textDesviacionEstandar);
		add(textCoeficienteVariacion);
		add(textCoeficienteAsimetria);
		add(textKurtosis);

		add(media);
		add(mediana);
		add(moda);
		add(desviacionMedia);
		add(varianza);
		add(desviacionEstandar);
		add(coeficienteVariacion);
		add(coeficienteAsimetria);
		add(kurtosis);

	}

	public void addGropedData(MngGroupedData dates) {
		media.setText(String.valueOf(dates.calculateAverage()));
		mediana.setText(String.valueOf(dates.calculateMedian()));
		moda.setText(String.valueOf(dates.calcStadisticalMode()));
		desviacionMedia.setText(String.valueOf(dates.calcMeanDeviation()));
		if (dates.getDates().size() < 2) {
			varianza.setText("No hay Suficiente Datos");
			desviacionEstandar.setText("No hay Suficiente Datos");
			coeficienteVariacion.setText("No hay Suficiente Datos");
			coeficienteAsimetria.setText("No hay Suficiente Datos");
			kurtosis.setText("No hay Suficiente Datos");
		} else {
			varianza.setText(String.valueOf(dates.calculateVariance()));
			desviacionEstandar.setText(String.valueOf(dates.calculateStandardDeviation()));
			coeficienteVariacion.setText(String.valueOf(dates.calculateCV()));
			coeficienteAsimetria.setText(String.valueOf(dates.calcCoefficientOfAsymmetry()));
			kurtosis.setText(String.valueOf(dates.calcKurtosis()));
		}

	}

	public void addNonGropedData(MngNonGroupedData dates) {
		media.setText(String.valueOf(dates.calculateAverage()));
		mediana.setText(String.valueOf(dates.calculateMedian()));
		if (dates.calcStadisticalMode() == null) {
			moda.setText("NO HAY MODA");
		} else if (dates.calcStadisticalMode().size() == 1) {
			String aux = "";
			for (Double date : dates.calcStadisticalMode()) {
				aux = String.valueOf(date);

			}
			moda.setText(aux);

		} else if (dates.calcStadisticalMode().size() == 2) {
			String aux = "";
			byte cont = 1;
			for (Double date : dates.calcStadisticalMode()) {
				aux += "Moda " + cont + " :" + String.valueOf(date) + "     ";
				cont++;
			}
			moda.setText(aux);

		}
		desviacionMedia.setText(String.valueOf(dates.calcMeanDeviation()));
		if (dates.getSize() < 2) {
			varianza.setText("No hay Suficiente Datos");
			desviacionEstandar.setText("No hay Suficiente Datos");
			coeficienteVariacion.setText("No hay Suficiente Datos");
			coeficienteAsimetria.setText("No hay Suficiente Datos");
			kurtosis.setText("No hay Suficiente Datos");
		} else {
			varianza.setText(String.valueOf(dates.calculateVariance()));
			desviacionEstandar.setText(String.valueOf(dates.calculateStandardDeviation()));
			coeficienteVariacion.setText(String.valueOf(dates.calculateCV()));
			coeficienteAsimetria.setText(String.valueOf(dates.calcCoefficientOfAsymmetry()));
			kurtosis.setText(String.valueOf(dates.calcKurtosis()));
		}

	}
}
