package GUI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Module_Evenement.Agenda;
import Module_Evenement.Evenement;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;

public class AffichageEvenement {

	private JFrame frame;
	private JTable table_Participants;
	
	private Evenement evenement;
	private String ipServeur;
	private Registry reg;
	private serveur.RechercheEvenement rechercheEvenements;

	/**
	 * Create the application.
	 * @param evenements 
	 */
	public AffichageEvenement(String ipServeur, Registry reg, Evenement evenement, serveur.RechercheEvenement rechercheEvenements) {
		this.evenement = evenement;
		this.ipServeur = ipServeur;
		this.reg = reg;
		this.rechercheEvenements = rechercheEvenements;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 350, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btn_Retour = new JButton("Retour");
		btn_Retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RechercheEvenement rechercheEvenement = new RechercheEvenement(ipServeur, reg, rechercheEvenements);
				frame.dispose();
			}
		});
		btn_Retour.setBounds(213, 395, 125, 35);
		frame.getContentPane().add(btn_Retour);

		JLabel lbl_MyEwine = new JLabel("My eWine");
		lbl_MyEwine.setFont(new Font("Dialog", Font.BOLD, 16));
		lbl_MyEwine.setBounds(133, 12, 80, 26);
		frame.getContentPane().add(lbl_MyEwine);

		JLabel lbl_AfficherUnEvenement = new JLabel("Afficher un événement");
		lbl_AfficherUnEvenement.setBounds(108, 50, 147, 14);
		frame.getContentPane().add(lbl_AfficherUnEvenement);
		JLabel lbl_Nom = new JLabel("Nom");
		lbl_Nom.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Nom.setBounds(79, 98, 35, 14);
		frame.getContentPane().add(lbl_Nom);

		JLabel lbl_Date = new JLabel("Date");
		lbl_Date.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Date.setBounds(79, 119, 35, 14);
		frame.getContentPane().add(lbl_Date);

		JLabel lbl_Lieu = new JLabel("Lieu");
		lbl_Lieu.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Lieu.setBounds(79, 145, 35, 14);
		frame.getContentPane().add(lbl_Lieu);

		JLabel lbl_Description = new JLabel("Description");
		lbl_Description.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Description.setBounds(26, 171, 88, 14);
		frame.getContentPane().add(lbl_Description);

		JTextField textField_Nom = new JTextField(evenement.getNom());
		textField_Nom.setEditable(false);
		textField_Nom.setBounds(121, 94, 175, 18);
		frame.getContentPane().add(textField_Nom);
		textField_Nom.setColumns(10);

		int date = evenement.getDate();
		int annee = date / 10000;
		int mois = (date - (date - (date%10000)))/100;
		int jour = date - ((date - (date%10000)) + mois*100);
		
		JTextField textField_Date = new JTextField(jour + "/" + mois + "/" + annee);
		textField_Date.setEditable(false);
		textField_Date.setColumns(10);
		textField_Date.setBounds(121, 118, 175, 18);
		frame.getContentPane().add(textField_Date);

		JTextField textField_Lieu = new JTextField(evenement.getLieu());
		textField_Lieu.setEditable(false);
		textField_Lieu.setColumns(10);
		textField_Lieu.setBounds(121, 144, 175, 18);
		frame.getContentPane().add(textField_Lieu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(121, 172, 175, 100);
		frame.getContentPane().add(scrollPane);

		JTextArea textArea_Description = new JTextArea(evenement.getDescription());
		textArea_Description.setEditable(false);
		scrollPane.setViewportView(textArea_Description);
		textArea_Description.setBorder(UIManager.getBorder("TextField.border"));
		textArea_Description.setLineWrap(true);

		JButton btn_Participer = new JButton("Participer");
		btn_Participer.setBounds(12, 395, 125, 35);
		frame.getContentPane().add(btn_Participer);
		
		JLabel lbl_Participants = new JLabel("Participants");
		lbl_Participants.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Participants.setBounds(26, 281, 88, 14);
		frame.getContentPane().add(lbl_Participants);
		
		table_Participants = new JTable();
		table_Participants.setBorder(UIManager.getBorder("TextField.border"));
		table_Participants.setBounds(121, 281, 175, 100);
		frame.getContentPane().add(table_Participants);
		
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
