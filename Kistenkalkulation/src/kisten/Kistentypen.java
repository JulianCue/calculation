package kisten;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Kistentypen {

	protected ArrayList<Kiste> kistentypen;
	private static double kistenfuellgrad = 1;
	private static File input;

	public Kistentypen() {
		kistentypen = new ArrayList<Kiste>();
		leseKistenTypen();
	}

	public double getKistenfuellgrad() {
		return kistenfuellgrad;
	}

	public static void setKistenfuellgrad(double kfg) {
		if (kfg >= Math.pow(10, -4) && kfg <= 1) {
			kistenfuellgrad = kfg;
		}
	}

	public void addKiste(Kiste kiste) {
		kistentypen.add(kiste);
	}

	public Kiste getKisteByMtv(int mtv) throws KisteNotFoundException {
		for (Kiste kiste : kistentypen) {
			if (kiste.getMtv_nummer() == mtv) {
				return kiste;
			}
		}

		throw new KisteNotFoundException();
	}

	public Kiste getKisteByVolumen(int volumen) throws KisteNotFoundException {
		for (Kiste kiste : kistentypen) {
			if (kiste.getVolumen() == volumen) {
				return kiste;
			}
		}

		throw new KisteNotFoundException();
	}

	public void leseKistenTypen() {
		Kiste.setKistenfuellgrad(kistenfuellgrad);
		try {
			FileReader dateileser = new FileReader(input);
			BufferedReader dateiausleser = new BufferedReader(dateileser);
			String line, kistendaten[];

			while ((line = dateiausleser.readLine()) != null) {
				kistendaten = line.split(";");
				addKiste(new Kiste(Double.parseDouble(kistendaten[0]),
						Integer.parseInt(kistendaten[1])));
			}

			dateiausleser.close();
			dateileser.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		String ret = "";
		for (Kiste k : kistentypen) {
			ret += (k.getMtv_nummer() + ";" + k.getVolumen());
		}
		return ret;
	}
	
	public static void setInput(File f) {
		input = new File(f.getAbsolutePath());
	}

}
