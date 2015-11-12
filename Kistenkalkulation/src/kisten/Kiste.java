package kisten;

import java.util.ArrayList;

import berechnungen.Bestellung;

public class Kiste {

	private int mtv_nummer;
	private int volumen;
	private int aktuellesVolumen;
	private int tourID;
	private static double kistenfuellgrad = 1;
	private String kunde;
	private ArrayList<Bestellung> bestellungen;
	
	
	public Kiste(int v, int mtv) {
		mtv_nummer = mtv;
		volumen = v;
		bestellungen = new ArrayList<>();
		aktuellesVolumen = 0;
	}
	
	public double getKistenfuellgrad() {
		return kistenfuellgrad;
	}

	public static void setKistenfuellgrad(double kfg) {
		kistenfuellgrad = kfg;
	}
	
	public void addBestellung(Bestellung bestellung) {
		bestellungen.add(bestellung);
		aktuellesVolumen += bestellung.getVolumen() * bestellung.getMenge();
	}
	
	public boolean passtInKiste(Bestellung bestellung) {
		return (((aktuellesVolumen + bestellung.getVolumen() * bestellung.getMenge())/volumen) 
				<= kistenfuellgrad) &&
				kunde.equals(bestellung.getKunde()) &&
				(mtv_nummer == bestellung.getMtv_nummer() &&
				bestellung.getTour() == tourID);
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

	public String getKunde() {
		return kunde;
	}

	public void setKunde(String kunde) {
		this.kunde = kunde;
	}
	
	public void setTourID(int tourID) {
		this.tourID = tourID;
	}
	
	public String toString() {
		String ret = "";
		for(Bestellung b : bestellungen) {
			ret += b.toString() + "\n";
		}
		return ret;
	}
}
