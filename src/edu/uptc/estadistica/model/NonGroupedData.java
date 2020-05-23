package edu.uptc.estadistica.model;

public class NonGroupedData {
	int times;
	double value;

	public NonGroupedData(double value, int times) {
		this.value = value;
		this.times = times;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

}
