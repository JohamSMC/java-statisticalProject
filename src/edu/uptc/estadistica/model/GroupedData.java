package edu.uptc.estadistica.model;

public class GroupedData {
	double limInf;
	double limSup;
	double marCla;
	double ampli;
	int freAbs;

	public GroupedData(double limInf, double limSup, int freAbs) {
		this.limInf = limInf;
		this.limSup = limSup;
		this.freAbs = freAbs;
		calculateValues();
	}

	public void calculateValues() {
		marCla = (limSup + limInf) / 2;
		ampli = limSup - limInf;
	}

	public double getLimInf() {
		return limInf;
	}

	public double getLimSup() {
		return limSup;
	}

	public double getMarCla() {
		return marCla;
	}

	public double getAmpli() {
		return ampli;
	}

	public int getFreAbs() {
		return freAbs;
	}

	public void setLimInf(double limInf) {
		this.limInf = limInf;
	}

	public void setLimSup(double limSup) {
		this.limSup = limSup;
	}

	public void setFreAbs(int freAbs) {
		this.freAbs = freAbs;
	}

}
