package edu.uptc.estadistica.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MngNonGroupedData {

	private ArrayList<NonGroupedData> dates;

	/*
	 * Constructor de la clase DatosNoAgrupados donde se inicializa el
	 * ArrayList()
	 */
	public MngNonGroupedData() {
		dates = new ArrayList<>();
	}

	/*
	 * Metodo que retorna el ArrayList() que contiene los objetos de tipo
	 * NonGroupedData
	 */
	public ArrayList<NonGroupedData> getDates() {
		return (ArrayList<NonGroupedData>) dates.clone();
	}

	/*
	 * Metodo que recibe como parametro un double correspondiente al valor del
	 * objeto, busca en todo el arrayList() y retorna el objeto que corresponde
	 * al valor ingresado por parametro
	 */
	public NonGroupedData findDate(double value) {
		for (NonGroupedData nonGroupedData : dates) {
			if (nonGroupedData.getValue() == value) {
				return nonGroupedData;
			}
		}

		return null;

	}

	/*
	 * Metodo que recibe un ArraList() y lo asigna directamente al ArrayList de
	 * la clase
	 */
	public void setDates(ArrayList<NonGroupedData> dates) {
		this.dates = dates;
	}

	/*
	 * Metodo que recibe un objeto de tipo NonGroupedData y lo anexa al
	 * ArrayList() de la clase
	 */
	public void addDate(NonGroupedData date) {
		boolean aux = false;
		for (NonGroupedData nonGroupedData : dates) {
			if (nonGroupedData.getValue() == date.value) {
				nonGroupedData.setTimes(nonGroupedData.getTimes() + date.getTimes());
				aux = true;
				break;
			}

		}
		if (aux == false) {
			dates.add(date);
		}

	}

	/* Metodo que elimina un objeto del ArrayList() de la clase */
	public boolean deteleDate(double date) {
		if (findDate(date) != null) {
			dates.remove(findDate(date));
			return true;
		}
		return false;

	}

	public ArrayList<NonGroupedData> sortInsert(boolean type) {

		ArrayList<NonGroupedData> sorted = (ArrayList<NonGroupedData>) dates.clone();
		for (int i = 1; i < sorted.size(); i++) {
			NonGroupedData value = sorted.get(i);
			int pos = i - 1;
			if (type) {
				while (pos >= 0 && value.getValue() < sorted.get(pos).getValue()) {
					sorted.set(pos + 1, sorted.get(pos--));
				}

			} else {
				while (pos >= 0 && value.getValue() > sorted.get(pos).getValue()) {
					sorted.set(pos + 1, sorted.get(pos--));
				}

			}

			sorted.set(pos + 1, value);
		}
		return sorted;
	}

	/*
	 * Metodo que ordena el arrayList() de la clase( en orden ascendente ) segun
	 * el "value" del objeto
	 */
	public ArrayList<NonGroupedData> sortArray() {

		ArrayList<NonGroupedData> sorted = getDates();
		Collections.sort(sorted, new Comparator<NonGroupedData>() {
			@Override
			public int compare(NonGroupedData o1, NonGroupedData o2) {
				return String.valueOf(o1.getValue()).compareTo(String.valueOf(o2.getValue()));
			}
		});
		return sorted;
	}

	/* Metodo que calcula y retorna la media o promedio */
	public double calculateAverage() {
		double sum = 0;
		for (NonGroupedData date : dates) {
			sum += (date.getValue() * date.getTimes());
		}
		double average = sum / getSize();
		return average;

	}

	/* Metodo que calcula y retorna la Mediana */
	public double calculateMedian() {
		ArrayList<NonGroupedData> sorted = sortArray();
		ArrayList<Double> arrayData = arrayData(sorted);
		int size = getSize();
		if (size % 2 == 0) {
			int indexOne = size / 2;
			int indexTwo = indexOne + 1;
			double medianOne = arrayData.get(indexOne - 1);
			double medianTwo = arrayData.get(indexTwo - 1);
			return (medianOne + medianTwo) / 2;
		} else {
			int index = (size + 1) / 2;
			return arrayData.get(index - 1);
		}

	}

	/* Metodo que calcula y retorna la Moda */
	public ArrayList<Double> calcStadisticalMode() {
		ArrayList<Double> mode = new ArrayList<>();
		int hightFrequently = 0;
		for (NonGroupedData data : sortArray()) {
			hightFrequently = data.getTimes() > hightFrequently ? data.getTimes() : hightFrequently;
		}
		for (NonGroupedData data : sortArray()) {
			if (data.getTimes() == hightFrequently) {
				mode.add(data.getValue());
			}
		}
		if (mode.size() == 1 || mode.size() == 2) {
			return mode;
		} else {
			return null;
		}

	}

	/* Metodo que calcula y retorna la desviacion media */
	public double calcMeanDeviation() {
		ArrayList<NonGroupedData> sorted = sortArray();
		ArrayList<Double> arrayData = arrayData(sorted);
		double sumatory = 0;
		for (Double data : arrayData) {
			sumatory += Math.abs(data - calculateAverage());
		}
		return sumatory / arrayData.size();

	}

	/* Metodo que calcula y retorna la desviacion estandar */
	public double calculateStandardDeviation() {
		double standarDeviation = Math.sqrt(calculateVariance());
		return standarDeviation;
	}

	/* Metodo que calcula y retorna el coheficiente de variacion CV */

	public double calculateCV() {
		return calculateStandardDeviation() / calculateAverage();
	}

	/* Metodo que calcula y retorna el cohefiente de asimetria */
	public double calcCoefficientOfAsymmetry() {
		double mSub3 = calcMsubN((byte) 3);
		double sExp3 = Math.pow(calculateStandardDeviation(), 3);
		return mSub3 / sExp3;
	}

	/* Metodo que calcula y retorna la Kurtosis */
	public double calcKurtosis() {
		double mSub4 = calcMsubN((byte) 4);
		double sExp4 = Math.pow(calculateStandardDeviation(), 4);
		return (mSub4 / sExp4) - 3;
	}

	/*
	 * Metodo que calcula y retorna la varianza muestral Nota: Hay que validar
	 * por fuera que el tamaño del arrayList() de datos o arrayData.size() sea
	 * mayor o igual a 2 (Para que el denominador no sea 0)
	 */
	public double calculateVariance() {
		ArrayList<NonGroupedData> sorted = sortArray();
		ArrayList<Double> arrayData = arrayData(sorted);
		double sumatory = 0;
		for (Double data : arrayData) {
			sumatory += Math.pow(data - calculateAverage(), 2);
		}
		return sumatory / (arrayData.size() - 1);
	}

	/* Metodo que calcula y retorna el numero de datos no agrupados en total */
	public int getSize() {
		int size = 0;
		for (NonGroupedData date : dates) {
			size += date.getTimes();

		}
		return size;

	}

	/*
	 * Metodo que recibe un ArrayList<NonGroupedData>() con los objetos y
	 * desglosa la informacion y agrega en un ArrayList<Double> el valor tantas
	 * veces como times tengay retorna ese ArrayList() con todos los valores de
	 * los datos, este metodo se realizo con el fin de facilitar el calculo de
	 * algunas medidas de tendencia central y medidas dedispersion
	 */
	public ArrayList<Double> arrayData(ArrayList<NonGroupedData> dataComp) {
		ArrayList<Double> allData = new ArrayList<>();
		for (NonGroupedData date : dataComp) {
			for (int i = 0; i < date.getTimes(); i++) {
				allData.add(date.getValue());
			}
		}
		return allData;
	}

	public double calcMsubN(byte i) {
		ArrayList<NonGroupedData> sorted = sortArray();
		ArrayList<Double> arrayData = arrayData(sorted);
		double sumatory = 0;
		for (Double data : arrayData) {
			sumatory += Math.pow(data - calculateAverage(), i);
		}
		return sumatory / (arrayData.size());
	}

	public void deleteAll() {
		dates.removeAll(dates);

	}
}
