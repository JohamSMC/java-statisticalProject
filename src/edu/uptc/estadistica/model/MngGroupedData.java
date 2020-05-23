package edu.uptc.estadistica.model;

import java.util.ArrayList;

import javax.swing.SortingFocusTraversalPolicy;

public class MngGroupedData {

	private ArrayList<GroupedData> dates;

	public MngGroupedData() {
		dates = new ArrayList<>();

	}

	public ArrayList<GroupedData> getDates() {
		return (ArrayList<GroupedData>) dates.clone();
	}

	public GroupedData findDate(double date) {
		for (GroupedData GroupedData : dates) {
			if (GroupedData.getMarCla() == date) {
				return GroupedData;
			}
		}

		return null;

	}

	public boolean addDate(GroupedData date) {
		if (dates.isEmpty()) {
			dates.add(date);
			return true;

		} else {
			boolean aux = true;
			for (GroupedData groupedData : dates) {
				if (groupedData.getLimInf() == date.getLimInf() || groupedData.getLimSup() == date.getLimSup()) {
					aux = false;
					return false;
				} else if (date.getLimSup() < groupedData.getLimSup() && date.getLimSup() > groupedData.getLimInf()) {
					aux = false;
					return false;
				} else if (date.getLimInf() > groupedData.getLimInf() && date.getLimInf() < groupedData.getLimSup()) {
					aux = false;
					return false;
				}
			}
			if (aux) {
				dates.add(date);
				return true;
			}
		}

		return true;

	}

	public boolean deteleDate(double date) {
		if (findDate(date) != null) {
			dates.remove(findDate(date));
			return true;
		}
		return false;

	}

	public ArrayList<GroupedData> sortInsert(boolean type) {

		ArrayList<GroupedData> sorted = (ArrayList<GroupedData>) dates.clone();
		for (int i = 1; i < sorted.size(); i++) {
			GroupedData value = sorted.get(i);
			int pos = i - 1;
			if (type) {
				while (pos >= 0 && value.getLimSup() < sorted.get(pos).getLimInf()) {
					sorted.set(pos + 1, sorted.get(pos--));
				}

			} else {
				while (pos >= 0 && value.getLimSup() > sorted.get(pos).getLimInf()) {
					sorted.set(pos + 1, sorted.get(pos--));
				}

			}

			sorted.set(pos + 1, value);
		}
		return sorted;
	}

	public double calculateAverage() {
		double sum = 0;
		for (GroupedData date : dates) {
			sum += (date.getMarCla() * date.getFreAbs());
		}
		double average = sum / totalData();
		return average;

	}

	public int totalData() {
		int aux = 0;
		for (GroupedData groupedData : dates) {
			aux += groupedData.getFreAbs();
		}
		return aux;

	}

	public double calculateMedian() {
		ArrayList<GroupedData> auxDates = sortInsert(true);
		int halfSize = totalData() / 2;
		int sumFreAbs = 0;
		GroupedData auxDate = null;
		for (GroupedData groupedData : auxDates) {
			sumFreAbs += groupedData.getFreAbs();
			if (sumFreAbs >= halfSize) {
				auxDate = groupedData;
				break;
			}
		}

		double aux1 = sumFreAbs - auxDate.getFreAbs();
		double aux2 = (halfSize - aux1) / auxDate.getFreAbs();
		double mediana = auxDate.getLimInf() + (auxDate.getAmpli() * aux2);

		return mediana;

	}

	public double calcStadisticalMode() {
		ArrayList<GroupedData> auxDates = sortInsert(true);
		int halfSize = totalData() / 2;
		int sumFreAbs = 0;
		GroupedData auxDate = null;
		for (GroupedData groupedData : auxDates) {
			sumFreAbs += groupedData.getFreAbs();
			if (sumFreAbs >= halfSize) {
				auxDate = groupedData;
				break;
			}
		}
		int D1 = auxDates.indexOf(auxDate) == 0 ? 0
				: auxDate.getFreAbs() - auxDates.get(auxDates.indexOf(auxDate) - 1).getFreAbs();

		int D2 = auxDates.indexOf(auxDate) == (auxDates.size() - 1) ? 0
				: auxDate.getFreAbs() - auxDates.get(auxDates.indexOf(auxDate) + 1).getFreAbs();
		double aux1 = D1 + D2;
		double aux2 = D1 / aux1;
		double moda = auxDate.getLimInf() + (auxDate.getAmpli() * aux2);
		return moda;

	}

	/* Metodo que calcula y retorna la desviacion media */
	public double calcMeanDeviation() {
		double average = calculateAverage();
		double sumatory = 0;
		for (GroupedData data : sortInsert(true)) {
			sumatory += Math.abs(data.getMarCla() - average) * data.getFreAbs();
		}
		return sumatory / totalData();

	}

	/*
	 * Metodo que calcula y retorna la varianza muestral Nota: Hay que validar
	 * por fuera que el tamaño del arrayList() de datos o arrayData.size() sea
	 * mayor o igual a 2 (Para que el denominador no sea 0)
	 */
	public double calculateVariance() {
		double average = calculateAverage();
		double sumatory = 0;
		for (GroupedData data : sortInsert(true)) {
			sumatory += Math.pow(data.getMarCla() - average, 2) * data.getFreAbs();
		}
		return sumatory / (totalData() - 1);
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

	public double calcMsubN(byte i) {
		double average = calculateAverage();
		double sumatory = 0;
		for (GroupedData data : sortInsert(true)) {
			sumatory += Math.pow((data.getMarCla() - average), i)*data.getFreAbs();
		}
		return sumatory / totalData();
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

	public void deleteAll() {
		dates.removeAll(dates);
	}

}
