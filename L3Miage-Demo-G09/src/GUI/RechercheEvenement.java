package GUI;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Module_Evenement.Agenda;
import Module_Evenement.Evenement;

import java.awt.event.ActionListener;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class RechercheEvenement {

	private JFrame frame;
	private JTextField textField_ChampRecherche;

	private String ipServeur;
	private Registry reg;
	private String loginProprio;
	private Agenda evenements;
	private serveur.RechercheEvenement rechercheEvenement;

	/**
	 * Create the application.
	 */
	public RechercheEvenement(String ipServeur, Registry reg, serveur.RechercheEvenement evenements, String loginProprio) {
		this.ipServeur = ipServeur;
		this.reg = reg;
		this.loginProprio=loginProprio;
		this.evenements = evenements.getAgenda();
		this.rechercheEvenement = evenements;

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 350, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btn_Retour = new JButton("Retour");
		btn_Retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu(ipServeur, reg, loginProprio);
				frame.dispose();
			}
		});
		btn_Retour.setBounds(112, 320, 125, 35);
		frame.getContentPane().add(btn_Retour);

		JLabel lbl_MyEwine = new JLabel("My eWine");
		lbl_MyEwine.setFont(new Font("Dialog", Font.BOLD, 16));
		lbl_MyEwine.setBounds(133, 12, 80, 26);
		frame.getContentPane().add(lbl_MyEwine);

		JLabel lbl_RechercheDEvenement = new JLabel("Recherche d'événement");
		lbl_RechercheDEvenement.setBounds(96, 51, 147, 14);
		frame.getContentPane().add(lbl_RechercheDEvenement);

		textField_ChampRecherche = new JTextField();
		textField_ChampRecherche.setBounds(12, 77, 205, 24);
		frame.getContentPane().add(textField_ChampRecherche);
		textField_ChampRecherche.setColumns(10);

		JButton btn_Modifier = new JButton("Modifier");
		btn_Modifier.setBounds(192, 265, 125, 35);
		frame.getContentPane().add(btn_Modifier);

		JList<String> list = new JList<String>();
		list.setBounds(12, 110, 315, 140);
		frame.getContentPane().add(list);
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		for (int i = 0; i < this.evenements.getEvenements().size(); i++) {
			dlm.addElement(evenements.getEvenements().get(i).getNom());
		}
		list.setModel(dlm);

		JButton btn_Afficher = new JButton("Afficher");
		btn_Afficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!list.isSelectionEmpty()) {

					Evenement evenement = null;

					for (Evenement evnmnt : evenements.getEvenements()) {
						if (evnmnt.getNom() == list.getSelectedValue()) {
							evenement = evnmnt;
						}
					}

					AffichageEvenement affichageEvenement = new AffichageEvenement(ipServeur, reg, evenement,
							rechercheEvenement, loginProprio);
					frame.dispose();
				}
			}
		});
		btn_Afficher.setBounds(22, 265, 125, 35);
		frame.getContentPane().add(btn_Afficher);

		JButton btn_Recherche = new JButton("Recherche");
		btn_Recherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField_ChampRecherche.getText() != "") {
					List<String> noms = new ArrayList<String>();
					for (int i = 0; i < list.getModel().getSize(); i++) {
						noms.add(list.getModel().getElementAt(i).toString());
					}

					DefaultListModel<String> dlm2 = new DefaultListModel<String>();
					for (int i = 0; i < dlm.getSize(); i++) {
						if (dlm.get(i).contains(textField_ChampRecherche.getText())) {
							list.removeAll();

							dlm2.addElement(dlm.get(i));
						}
					}
					list.setModel(dlm2);
				}
			}
		});
		btn_Recherche.setBounds(230, 77, 98, 24);
		frame.getContentPane().add(btn_Recherche);

		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
