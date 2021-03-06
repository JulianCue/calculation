package kisten;

import java.util.ArrayList;

import berechnungen.Bestellung;

public class Kiste implements Comparable<Kiste> {

	private int mtv_nummer;
	private double volumen;
	private double aktuellesVolumen;
	private int tourID;
	private int nummer = 0;
	private int bestellnummer = 0;
	private static int maxTourID = 1;
	private static double kistenfuellgrad = 1;
	private String kunde;
	private ArrayList<Bestellung> bestellungen;

	public Kiste(double v, int mtv, int n, int bs) {
		mtv_nummer = mtv;
		volumen = v;
		bestellungen = new ArrayList<>();
		aktuellesVolumen = 0;
		nummer = n;
		bestellnummer = bs;
	}

	public Kiste(double v, int mtv) {
		mtv_nummer = mtv;
		volumen = v;
		bestellungen = new ArrayList<>();
		aktuellesVolumen = 0;
	}

	public int getNummer() {
		return nummer;
	}

	public static int getMaxTourID() {
		return maxTourID;
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
		return (((aktuellesVolumen + bestellung.getVolumen()
				* bestellung.getMenge()) / volumen) <= kistenfuellgrad)
				&& kunde.equals(bestellung.getKunde())
				&& (mtv_nummer == bestellung.getMtv_nummer()
						&& bestellung.getTour() == tourID && bestellnummer == bestellung
						.getBestellnummer());
	}

	public double getVolumen() {
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
		if (tourID > maxTourID) {
			maxTourID = tourID;
		}
	}

	public int getTourID() {
		return tourID;
	}

	public int getBestellnummer() {
		return bestellnummer;
	}

	public ArrayList<Bestellung> getBestellungen() {
		return bestellungen;
	}

	public String toString() {
		String ret = "";
		for (Bestellung b : bestellungen) {
			ret += b.toString() + "\n";
		}
		return ret;
	}

	@Override
	public int compareTo(Kiste o) {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		Bestellung a, b;
		for (int i = 0; i < bestellungen.size(); i++) {
			a = bestellungen.get(i);
			b = ((Kiste) obj).bestellungen.get(i);

			if (!a.getArtikel().equals(b.getArtikel())
					&& a.getBestellnummer() != b.getBestellnummer()
					&& !a.getKunde().equals(b.getKunde())
					&& a.getMenge() != a.getMenge()
					&& a.getMtv_nummer() != b.getMtv_nummer()
					&& a.getTour() != b.getTour()
					&& a.getVolumen() != b.getVolumen()) {
				return false;
			}
		}
		return true;
	}
}
