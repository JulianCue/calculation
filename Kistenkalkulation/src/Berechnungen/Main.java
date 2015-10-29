package Berechnungen;

import Kisten.KisteNotFoundException;
import Kisten.Kistentypen;

public class Main {

	public static void main(String[] args) throws KisteNotFoundException {

		Kistentypen test = new Kistentypen();
		
		System.out.println(test.getKisteByMtv(10000000));
		System.out.println(test.getKisteByVolumen(200000000));
	}

}
