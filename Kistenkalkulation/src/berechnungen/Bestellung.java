package berechnungen;

public class Bestellung {

	private String kunde, artikel;
	private int tour, bestellnummer, volumen, mtv_nummer;
	private double menge;
	
	public Bestellung(String k, String a, int t, int bn, int v, int mtv, int m) {
		setKunde(k);
		setArtikel(a);
		setTour(t);
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
