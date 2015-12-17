package tests;

import java.io.File;
import java.io.IOException;

import kisten.Kiste;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import berechnungen.Berechnung;
import berechnungen.Bestellung;

public class BerechnungTest {

	public BerechnungTest() {
	}

	@Test
	public void testBefuelleKisten() throws IOException {
		Kiste[] testcases = { new Kiste(10, 12, 0, 1414) };
		testcases[0].addBestellung(new Bestellung(1, "McDonalds", 1414,
				"Gurken", 5, 0.01, 12));

		Berechnung.setInput(new File("src/tests/Bestellung.txt"));

		Berechnung b = new Berechnung();

		assertArrayEquals((Object[]) testcases, b.getKisten().toArray());
	}

	public static final void main(String[] args) throws IOException {
		BerechnungTest a = new BerechnungTest();
		a.testBefuelleKisten();
	}
}
