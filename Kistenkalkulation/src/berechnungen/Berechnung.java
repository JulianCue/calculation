package berechnungen;

import java.io.File;

import kisten.KisteNotFoundException;
import kisten.Kistentypen;

public class Berechnung {

	private static File input;
	
	public Berechnung() {

		Kistentypen test = new Kistentypen();
		
		try {
			System.out.println(test.getKisteByMtv(10000000));
			System.out.println(test.getKisteByVolumen(200000000));
			
			System.out.println(test.getKistenfuellgrad());
			
			System.out.println(input.getAbsolutePath());
		} catch (KisteNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void setInput(File f) {
		input = new File(f.getAbsolutePath());
		System.out.println("test");
	}

}
