package benutzeroberflaeche;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kisten.Kistentypen;
import berechnungen.Berechnung;
import java.awt.Insets;

public class Benutzeroberflaeche {

	private JFrame frame;

	private boolean ready = false;
	private static Benutzeroberflaeche window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Benutzeroberflaeche();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Benutzeroberflaeche() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 450, 0 };
		gridBagLayout.rowHeights = new int[] { 63, 70, 74, 61, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JButton btnDatei_auswaehlen = new JButton("Dateien auswaehlen");
		GridBagConstraints gbc_btnDatei_auswaehlen = new GridBagConstraints();
		gbc_btnDatei_auswaehlen.insets = new Insets(0, 0, 5, 0);
		gbc_btnDatei_auswaehlen.gridx = 0;
		gbc_btnDatei_auswaehlen.gridy = 0;
		frame.getContentPane()
				.add(btnDatei_auswaehlen, gbc_btnDatei_auswaehlen);
		
		btnDatei_auswaehlen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					Berechnung.setInput(selectedFile);
					chooser = new JFileChooser();
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						selectedFile = chooser.getSelectedFile();
						Kistentypen.setInput(selectedFile);
						ready = true;
					}
				}
			}
		});

		JButton btnFuellgrad_setzen = new JButton("Fuellgrad setzen");
		GridBagConstraints gbc_btnFuellgrad_setzen = new GridBagConstraints();
		gbc_btnFuellgrad_setzen.insets = new Insets(0, 0, 5, 0);
		gbc_btnFuellgrad_setzen.gridx = 0;
		gbc_btnFuellgrad_setzen.gridy = 1;
		frame.getContentPane()
				.add(btnFuellgrad_setzen, gbc_btnFuellgrad_setzen);
		
		btnFuellgrad_setzen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String kfg = JOptionPane
						.showInputDialog("Wie hoch soll der Fuellgrad sein(zwischen 0.01 & 1):");
				Kistentypen.setKistenfuellgrad(Double.parseDouble(kfg.replace(
						",", ".")));
			}
		});
		
		JButton btnNummern_kreis = new JButton("Nummernkreis auswaehlen");
		GridBagConstraints gbc_btnNummern_kreis = new GridBagConstraints();
		gbc_btnNummern_kreis.insets = new Insets(0, 0, 5, 0);
		gbc_btnNummern_kreis.gridx = 0;
		gbc_btnNummern_kreis.gridy = 2;
		frame.getContentPane().add(btnNummern_kreis, gbc_btnNummern_kreis);

		btnNummern_kreis.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int von, bis;
				String nummer = JOptionPane
						.showInputDialog("Wahl des Nummerkreises (von):");
				von = Integer.parseInt(nummer);
				
				nummer = JOptionPane
							.showInputDialog("Wahl des Nummerkreises (bis):");
				bis = Integer.parseInt(nummer);
				
				Berechnung.setNummernkreis(von, bis);
			}
		});

		JButton btnBerechnung_starten = new JButton("Berechnung bereit");
		GridBagConstraints gbc_btnBerechnung_starten = new GridBagConstraints();
		gbc_btnBerechnung_starten.gridx = 0;
		gbc_btnBerechnung_starten.gridy = 3;
		frame.getContentPane().add(btnBerechnung_starten,
				gbc_btnBerechnung_starten);

		btnBerechnung_starten.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (ready) {
					try {
						Date now ;
						int hourNow;
					    SimpleDateFormat hourFormatter = new SimpleDateFormat("HH");

						window.frame.setVisible(false);
						while(true) {
							now = new Date(System.currentTimeMillis());
							hourNow = Integer.valueOf(hourFormatter.format(now));
							
							if(hourNow == 22) {
								new Berechnung();
								window.frame.setVisible(true);
								break;
							}
							
							Thread.sleep(10);
						}
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					}
					ready = false;
				}

				else {
					JOptionPane.showMessageDialog(null,
							"Datei noch nicht ausgewaehlt!");
				}
			}
		});
	}

}
