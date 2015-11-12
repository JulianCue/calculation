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
	
	public Berechnung() {

		typ = new Kistentypen();
		
		bestellungenEinlesen();
		sortiereBestellungen();
		befuelleKisten();
		
		int i = 1;
		for(Kiste k: kisten) {
			System.out.print("Kiste: "+ i +"  "+ k.getKistenfuellgrad() +"\n"+ k.toString());
			i++;
		}
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
	
	public void befuelleKisten(){
		int mtv;
		boolean verpackt = false;
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
					Kiste a = new Kiste(typ.getKisteByMtv(mtv).getVolumen(), mtv);
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
	
	public void sortiereBestellungen() {
		for(int i = 0; i < bestellungen.size()-1; i++) {
			for(int j = i; j < bestellungen.size(); j++) {
				if((bestellungen.get(i).getMenge() * bestellungen.get(i).getVolumen()) < (bestellungen.get(j).getMenge() * bestellungen.get(j).getVolumen())) {
					Collections.swap(bestellungen, i, j);
				}
			}
		}
	}


}
