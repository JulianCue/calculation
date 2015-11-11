package kisten;

public class Kiste {

	private int mtv_nummer;
	private int volumen;
	private double aktuellerFuellgrad;
	
	public Kiste(int mtv, int v) {
		mtv_nummer = mtv;
		volumen = v;
	}
	
	public int getVolumen() {
		return volumen;
	}
	
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	
	public int getMtv_nummer() {
		return mtv_nummer;
	}
	
	public void setMtv_nummer(int mtv_nummer) {
		this.mtv_nummer = mtv_nummer;
	}

	public double getAktuellerFuellgrad() {
		return aktuellerFuellgrad;
	}

	public void setAktuellerFuellgrad(double aktuellerFuellgrad) {
		this.aktuellerFuellgrad = aktuellerFuellgrad;
	}
	
	public String toString() {
		return mtv_nummer + ";" + volumen;
	}
}
