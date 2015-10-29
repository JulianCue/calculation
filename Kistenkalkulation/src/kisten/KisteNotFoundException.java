package kisten;

public class KisteNotFoundException extends Exception {
	private static final long serialVersionUID = -2945238249838732757L;

	public KisteNotFoundException() {
		super("Kiste nicht gefunden, programm beendet!");
	}
	
	public KisteNotFoundException(String msg) {
		super(msg);
	}
}
