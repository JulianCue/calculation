package berechnungen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import kisten.KisteNotFoundException;
import kisten.Kistentypen;

public class Berechnung {

	private static File input;
	
	private ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
	
	public Berechnung() {

		Kistentypen typ = new Kistentypen();	
		
		bestellungenEinlesen();
		
		System.out.println(bestellungen.get(0).toString());
	}
	
	public void addBestellung(Bestellung bestellung) {
		bestellungen.add(bestellung);
	}
	
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

}
