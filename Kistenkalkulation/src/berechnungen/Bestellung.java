package berechnungen;

public class Bestellung {

	private String kunde, artikel;
	private int tour, bestellnummer, mtv_nummer;
	private double menge, volumen;
	
	public Bestellung(int t, String k, int bn, String a, double m, double v, int mtv) {
		setKunde(k);
		setArtikel(a);
		setTour(t);
		setBestellnummer(bn);
		setVolumen(v);
		setMtv_nummer(mtv);
		setMenge(m);
	}

	public String getKunde() {
		return kunde;
	}

	public void setKunde(String kunde) {
		this.kunde = kunde;
	}

	public String getArtikel() {
		return artikel;
	}

	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public int getBestellnummer() {
		return bestellnummer;
	}

	public void setBestellnummer(int bestellnummer) {
		this.bestellnummer = bestellnummer;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public int getMtv_nummer() {
		return mtv_nummer;
	}

	public void setMtv_nummer(int mtv_nummer) {
		this.mtv_nummer = mtv_nummer;
	}

	public double getMenge() {
		return menge;
	}

	public void setMenge(double menge) {
		this.menge = menge;
	}
	
	public String toString() {
		return tour + ";" + kunde + ";" + bestellnummer + ";" + 
				artikel + ";" + menge + ";" + volumen + ";" + mtv_nummer; 
	}
}
