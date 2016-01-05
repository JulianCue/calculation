package berechnungen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import kisten.Kiste;
import kisten.KisteNotFoundException;
import kisten.Kistentypen;

public class Berechnung {

//	private static File input = new File(
//			"C://Users//le.peters//SE-Projekt//calculation//Kistenkalkulation//src//berechnungen//Bestellung.txt");
	 private static File input = new
	 File("//home//j.cuepper//Dokumente//Klausuren 3. Semester//Software Engineering//Projekt//projects//calculation//Kistenkalkulation//src//berechnungen//Bestellung.txt");

	private ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
	private ArrayList<Kiste> kisten = new ArrayList<>();
	private Kistentypen typ;
	
	private static int von = 0;
	private static int bis = 3000;
	
	private ArrayList<Integer> benutzteNummern = new ArrayList<Integer>();
	

	/**
	 * Leitet die Berechnung ein und fuehrt alle noetigen Schritte durch: 1.
	 * Kistentypen erstellen 2. Bestellung einlesen 3. Bestellungen sortieren 4.
	 * Kisten befuellen 5. Ausgabe erstellen
	 * 
	 * @throws IOException
	 */
	public Berechnung() throws IOException {
		typ = new Kistentypen();

		bestellungenEinlesen();
		sortiereBestellungen();
		befuelleKisten();

		System.out.println(erstelleAusgabe());

		//drucken();
	}

	public void addBestellung(Bestellung bestellung) {
		bestellungen.add(bestellung);
	}

	public ArrayList<Kiste> getKisten() {
		return kisten;
	}

	/**
	 * Liest in der Input-Datei alle Zeilen einzeln und erstellt dementsprechend
	 * alle zu bearbeitenden Bestellungen
	 */
	public void bestellungenEinlesen() {
		try {
			FileReader dateileser = new FileReader(input);
			BufferedReader dateiausleser = new BufferedReader(dateileser);
			String line, bestellung[];

			while ((line = dateiausleser.readLine()) != null) {
				bestellung = line.split(";");
				addBestellung(new Bestellung(Integer.parseInt(bestellung[0]),
						bestellung[1], Integer.parseInt(bestellung[2]),
						bestellung[3], Double.parseDouble(bestellung[4]
								.replace(",", ".")),
						Double.parseDouble(bestellung[5].replace(",", ".")),
						Integer.parseInt(bestellung[6])));
			}

			dateiausleser.close();
			dateileser.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setInput(File f) {
		input = new File(f.getAbsolutePath());
	}
	
	public static void setNummernkreis(int v, int b) {
		if( Math.abs(v) <=  Math.abs(b)){
			von = Math.abs(v);
			bis = Math.abs(b);
		}else{
			von = Math.abs(b);
			bis = Math.abs(v);
		}
	}

	/**
	 * Befuellt Kisten mit den Inhalten der Bestellungen. Prueft zunaechst, ob
	 * die Bestellung in eine bereits vorhandende Kiste passt. Erstellt
	 * ansonsten eine neue Kiste.
	 */
	public void befuelleKisten() {
		int mtv;
		boolean verpackt = false;
		int i = 1;
		for (Bestellung b : bestellungen) {
			mtv = b.getMtv_nummer();
			try {
				for (Kiste k : kisten) {
					if (k.passtInKiste(b)) {
						k.addBestellung(b);
						verpackt = true;
						break;
					}
				}

				if (!verpackt) {
					Kiste a = new Kiste(typ.getKisteByMtv(mtv).getVolumen(),
							mtv, getNummer(), b.getBestellnummer());
					a.setKunde(b.getKunde());
					a.addBestellung(b);
					a.setTourID(b.getTour());
					kisten.add(a);
				}
				verpackt = false;
			} catch (KisteNotFoundException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	private int getNummer() {
		Random r = new Random();
		int zahl = r.nextInt(bis-von+1);
		while(benutzteNummern.contains(zahl)) {
			zahl = r.nextInt(bis-von+1);
		}
		benutzteNummern.add(zahl);
		return zahl;
	}
	/**
	 * Sortiert alle Bestellungen nach Kunde und Volumen(absteigend)
	 */
	public void sortiereBestellungen() {
		int b;
		for (int i = 0; i < bestellungen.size() - 1; i++) {
			b = 1;
			for (int j = i + 1; j < bestellungen.size(); j++) {
				if (bestellungen.get(i).getKunde()
						.equals(bestellungen.get(j).getKunde())) {
					Collections.swap(bestellungen, i + b, j);
					b++;
				}
			}
		}

		for (int i = 0; i < bestellungen.size() - 1; i++) {
			b = 1;
			for (int j = i + 1; j < bestellungen.size(); j++) {
				if (bestellungen.get(i).getBestellnummer() == bestellungen.get(
						j).getBestellnummer()) {
					Collections.swap(bestellungen, i + b, j);
					b++;
				}
			}
		}

		int max;
		for (int i = 0; i < bestellungen.size() - 1; i++) {
			max = i;
			for (int j = i + 1; j < bestellungen.size(); j++) {
				if ((bestellungen.get(max).getBestellnummer() == bestellungen
						.get(j).getBestellnummer() && bestellungen.get(max)
						.getKunde().equals(bestellungen.get(j).getKunde()))
						&& (bestellungen.get(max).getMenge() * bestellungen
								.get(max).getVolumen()) < (bestellungen.get(j)
								.getMenge() * bestellungen.get(j).getVolumen())) {
					max = j;
				}
			}
			Collections.swap(bestellungen, i, max);
		}

	}

	/**
	 * Erstellt die Ausgabe im gewuenschten Format.
	 * 
	 * @return ausgabe
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public String erstelleAusgabe() throws IOException {
		String lastKunde = "", kunde = "";
		int lastBestellnummer = 0, bestellnummer = 0;
		StringBuilder ausgabeArbeiter = new StringBuilder();
		StringBuilder ausgabeRegal = new StringBuilder();
		for (int i = 1; i <= Kiste.getMaxTourID(); i++) {
			ausgabeArbeiter.append("Tour " + i + "\n");
			ausgabeRegal.append("Tour;" + i);
			for (Kiste k : kisten) {
				if (k.getTourID() == i) {
					kunde = k.getKunde();
					bestellnummer = k.getBestellnummer();
					if (!lastKunde.equals(kunde)) {
						ausgabeArbeiter.append("\t" + kunde + "\n");
						ausgabeRegal.append(";" + kunde);
						lastKunde = kunde;
					}
					if (lastBestellnummer != bestellnummer) {
						ausgabeArbeiter.append("\t\t" + bestellnummer
								+ "(Bestellung)\n");
						ausgabeRegal.append(";" + bestellnummer);
						lastBestellnummer = bestellnummer;
					}
					ausgabeRegal.append(";Kiste;" + k.getNummer());
					ausgabeArbeiter.append("\t\t\tKiste " + k.getNummer()
							+ "\n");
					for (Bestellung b : k.getBestellungen()) {
						ausgabeArbeiter.append("\t\t\t\t" + b.getArtikel()
								+ ": " + b.getMenge() + "\n");
						ausgabeRegal.append(";" + b.getArtikel() + ";"
								+ b.getMenge());
					}
				}
			}
			ausgabeRegal.append("\n");
			lastBestellnummer = 0;
			lastKunde = "";
		}

		File fileArbeiter = new File("Ausgabedatei_Lagermitarbeiter.txt");
		File fileRegal = new File("Ausgabedatei_Hochregal.txt");
		FileWriter writerArbeiter;
		FileWriter writerRegal;
		try {
			writerArbeiter = new FileWriter(fileArbeiter);
			writerArbeiter.write(ausgabeArbeiter.toString());
			writerArbeiter.flush();

			writerRegal = new FileWriter(fileRegal);
			writerRegal.write(ausgabeRegal.toString());
			writerRegal.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ausgabeArbeiter.toString();
	}

	/*public void drucken() throws IOException {
		String ausdruck = "Ausgabedatei_Lagermitarbeiter.txt";
		File ausdruckfile = new File(ausdruck);
		if (Desktop.isDesktopSupported()) {
			Desktop dt = Desktop.getDesktop();
			try {
				dt.print(ausdruckfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
}
