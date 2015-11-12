package berechnungen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import kisten.Kiste;
import kisten.KisteNotFoundException;
import kisten.Kistentypen;

public class Berechnung {

	//private static File input = new File("C://Users//le.peters//SE-Projekt//calculation//Kistenkalkulation//src//berechnungen//Bestellung.txt");
	private static File input = new File("//home//j.cuepper//Dokumente//Klausuren 3. Semester//Software Engineering//Projekt//projects//calculation//Kistenkalkulation//src//berechnungen//Bestellung.txt");
	
	private ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
	private ArrayList<Kiste> kisten = new ArrayList<>();
	private Kistentypen typ;
	
	/**
	 * Leitet die Berechnung ein und fuehrt alle noetigen Schritte durch:
	 * 1. Kistentypen erstellen
	 * 2. Bestellung einlesen
	 * 3. Bestellungen sortieren
	 * 4. Kisten befuellen
	 * 5. Ausgabe erstellen
	 */
	public Berechnung() {
		typ = new Kistentypen();
		
		bestellungenEinlesen();
		sortiereBestellungen();
		befuelleKisten();
		
		System.out.println(erstelleAusgabe());
	}
	
	public void addBestellung(Bestellung bestellung) {
		bestellungen.add(bestellung);
	}
	
	/**
	 * Liest in der Input-Datei alle Zeilen einzeln und 
	 * erstellt dementsprechend alle zu bearbeitenden Bestellungen
	 */
	public void bestellungenEinlesen() {
		try {
			FileReader dateileser = new FileReader(input);
			BufferedReader dateiausleser = new BufferedReader(dateileser);
			String line, bestellung[];
			
			while((line = dateiausleser.readLine()) != null) {
				bestellung = line.split(";");
				addBestellung(new Bestellung(Integer.parseInt(bestellung[0]),
											bestellung[1],
											Integer.parseInt(bestellung[2]),
											bestellung[3],
											Double.parseDouble(bestellung[4].replace(",", ".")),
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
		System.out.println("test");
	}
	
	/**
	 * Befuellt Kisten mit den Inhalten der Bestellungen.
	 * Prueft zunaechst, ob die Bestellung in eine bereits vorhandende Kiste passt.
	 * Erstellt ansonsten eine neue Kiste.
	 */
	public void befuelleKisten(){
		int mtv;
		boolean verpackt = false;
		int i = 1;
		for (Bestellung b : bestellungen){
			mtv = b.getMtv_nummer();
			try {
				for(Kiste k : kisten) {
					if(k.passtInKiste(b)) {
						k.addBestellung(b);
						verpackt = true;
						break;
					}
				}
				
				if(!verpackt) {
					Kiste a = new Kiste(typ.getKisteByMtv(mtv).getVolumen(), mtv, i++, b.getBestellnummer());
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
	
	/**
	 * Sortiert alle Bestellungen nach Kunde und Volumen(absteigend)
	 */
	public void sortiereBestellungen() {
		for(int i = 0; i < bestellungen.size()-1; i++) {
			for(int j = i; j < bestellungen.size(); j++) {
				if(((bestellungen.get(i).getMenge() * bestellungen.get(i).getVolumen()) < (bestellungen.get(j).getMenge() * bestellungen.get(j).getVolumen())) &&
						bestellungen.get(i).getKunde().equals(bestellungen.get(j).getKunde())) {
					Collections.swap(bestellungen, i, j);
				}
			}
		}
	}
	
	/**
	 * Erstellt die Ausgabe im gewuenschten Format.
	 * @return ausgabe
	 */
	public String erstelleAusgabe() {
		String lastKunde = "", kunde = "";
		int lastBestellnummer = 0, bestellnummer = 0;
		StringBuilder ausgabe = new StringBuilder();
		for(int i = 1; i <= Kiste.getMaxTourID(); i++) {
			ausgabe.append("Tour " +i+ "\n");
			for(Kiste k : kisten) {
				kunde = k.getKunde();
				bestellnummer = k.getBestellnummer();
				if(k.getTourID() == i) {
					if(!lastKunde.equals(kunde)) {
						ausgabe.append("\t" +kunde+ "\n");
						lastKunde = kunde;
					}
					if(lastBestellnummer != bestellnummer) {
						ausgabe.append("\t\t" +bestellnummer+" (Bestellung)\n");
						lastBestellnummer = bestellnummer;
					}
					
					ausgabe.append("\t\t\tKiste " +k.getNummer()+ "\n");
					for(Bestellung b : k.getBestellungen()) {
						ausgabe.append("\t\t\t\t" + b.getArtikel() + ": " +b.getMenge()+ "\n");
					}
				}
			}
		}
		return ausgabe.toString();
	}


}
