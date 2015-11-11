package berechnungen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import kisten.Kiste;
import kisten.KisteNotFoundException;
import kisten.Kistentypen;

public class Berechnung {

	private static File input = new File("C://Users//le.peters//SE-Projekt//calculation//Kistenkalkulation//src//berechnungen//Bestellung.txt");
	
	private ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
	private ArrayList<Kiste> kisten = new ArrayList<>();
	private Kistentypen typ;
	
	@SuppressWarnings("unused")
	public Berechnung() {

		typ = new Kistentypen();	
		
		System.out.println(typ.toString());
		
		bestellungenEinlesen();
		befuelleKisten();
		
		int i = 1;
		for(Kiste k: kisten) {
			System.out.print("Kiste: "+i+ "\n" +k.toString());
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

}
