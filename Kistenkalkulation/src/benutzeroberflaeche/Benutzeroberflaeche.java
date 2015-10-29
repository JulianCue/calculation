package benutzeroberflaeche;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kisten.Kistentypen;
import berechnungen.Berechnung;

public class Benutzeroberflaeche {

	private JFrame frame;
	
	private boolean ready = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Benutzeroberflaeche window = new Benutzeroberflaeche();
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
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{93, 93, 94, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton btnDatei_auswaehlen = new JButton("Datei auswaehlen");
		btnDatei_auswaehlen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = chooser.getSelectedFile();
				    Berechnung.setInput(selectedFile);
				    ready = true;
				}
			}
		});
		GridBagConstraints gbc_btnDatei_auswaehlen = new GridBagConstraints();
		gbc_btnDatei_auswaehlen.gridx = 0;
		gbc_btnDatei_auswaehlen.gridy = 0;
		frame.getContentPane().add(btnDatei_auswaehlen, gbc_btnDatei_auswaehlen);
		
		JButton btnFuellgrad_setzen = new JButton("Fuellgrad setzen");
		GridBagConstraints gbc_btnFuellgrad_setzen = new GridBagConstraints();
		gbc_btnFuellgrad_setzen.gridx = 0;
		gbc_btnFuellgrad_setzen.gridy = 1;
		frame.getContentPane().add(btnFuellgrad_setzen, gbc_btnFuellgrad_setzen);
		
		JButton btnBerechnung_starten = new JButton("Berechnung starten");
		GridBagConstraints gbc_btnBerechnung_starten = new GridBagConstraints();
		gbc_btnBerechnung_starten.gridx = 0;
		gbc_btnBerechnung_starten.gridy = 2;
		frame.getContentPane().add(btnBerechnung_starten, gbc_btnBerechnung_starten);
		
		btnFuellgrad_setzen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String kfg = JOptionPane.showInputDialog("Wie hoch soll der Fuellgrad sein(zwischen 0.01 & 1):");
				Kistentypen.setKistenfuellgrad(Double.parseDouble(kfg.replace(",", ".")));
			}
		});
		
		btnBerechnung_starten.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ready) {
					new Berechnung();
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Datei noch nicht ausgewaehlt!");
				}
			}
		});
	}

}
