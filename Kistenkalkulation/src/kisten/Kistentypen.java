package kisten;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Kistentypen {

	protected ArrayList<Kiste> kistentypen;
	
	public Kistentypen() {
		kistentypen  = new ArrayList<Kiste>();
		leseKistenTypen();
	}
	
	public void addKiste(Kiste kiste) {
		kistentypen.add(kiste);
	}
	
	public Kiste getKisteByMtv(int mtv) throws KisteNotFoundException {
		for(Kiste kiste : kistentypen) {
			if(kiste.getMtv_nummer() == mtv) {
				return kiste;
			}
		}
		
		throw new KisteNotFoundException();
	}
	
	public Kiste getKisteByVolumen(int volumen) throws KisteNotFoundException {
		for(Kiste kiste : kistentypen) {
			if(kiste.getVolumen() == volumen) {
				return kiste;
			}
		}
		
		throw new KisteNotFoundException();
	}
	
	public void leseKistenTypen() {
		try {
			FileReader dateileser = new FileReader("src/Kisten/Kistentypen.txt");
			BufferedReader dateiausleser = new BufferedReader(dateileser);
			String line, kistendaten[];
			
			while((line = dateiausleser.readLine()) != null) {
				kistendaten = line.split(";");
				addKiste(new Kiste(Integer.parseInt(kistendaten[0]),
								   Integer.parseInt(kistendaten[1])));
			}
			
			dateiausleser.close();
			dateileser.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
