package GUI;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Module_Messagerie.MessageTexte;
import Structure_Contact.Contact;
import controleur.Connexion;
import controleur.Demarrer;
import serveur._Authentification;
import serveur._Messagerie;
import serveur._RechercheEvenement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;

	private String ipServeur;
	private Registry reg;

	/**
	 * Create the application.
	 */
	public Menu(String ipServeur, Registry reg) {
		this.ipServeur = ipServeur;
		this.reg = reg;

		initialize();
		// fermer GUI à distance
		// ouvrir interface utilisateur à distance
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

		JLabel lbl_MyEwine = new JLabel("My eWine");
		lbl_MyEwine.setFont(new Font("Dialog", Font.BOLD, 16));
		lbl_MyEwine.setBounds(133, 12, 80, 26);
		frame.getContentPane().add(lbl_MyEwine);

		JButton btn_CreationDunEvenement = new JButton("Creation d'un Evenement");
		btn_CreationDunEvenement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreationEvenement creationEvenement = new CreationEvenement();
				frame.dispose();
			}
		});
		btn_CreationDunEvenement.setBounds(20, 70, 300, 50);
		frame.getContentPane().add(btn_CreationDunEvenement);

		JButton btn_RechercheDEvenement = new JButton("Recherche d'Evenement");
		btn_RechercheDEvenement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Remote remote = reg.lookup("rmi://" + ipServeur + "/RechercheEvenement");

					if (remote instanceof _RechercheEvenement) {
						RechercheEvenement rechercheEvenement = new RechercheEvenement(ipServeur, reg, new serveur.RechercheEvenement());
					}
					frame.dispose();
				} catch (Exception ex) {
				}
			}
		});
		btn_RechercheDEvenement.setBounds(20, 130, 300, 50);
		frame.getContentPane().add(btn_RechercheDEvenement);

		JButton btn_ConsulterMaMessagerie = new JButton("Consulter ma messagerie");
		btn_ConsulterMaMessagerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Remote remote = reg.lookup("rmi://" + ipServeur + "/MessagerieEvenement");

					if (remote instanceof _Messagerie) {
						Contact proprio = new Contact(/*PageAuthentification.nom*/"juntir", null, "juntir@inc.com");
						Messagerie affichageMessages = new Messagerie(ipServeur,reg, proprio, ((_Messagerie) remote).consulterMessagerie(proprio));
					}
					frame.dispose();
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			
		});
		btn_ConsulterMaMessagerie.setBounds(20, 190, 300, 50);
		frame.getContentPane().add(btn_ConsulterMaMessagerie);

		JButton btn_Deconnexion = new JButton("Deconnexion");
		btn_Deconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PageAuthentification pageAuth = new PageAuthentification(null, ipServeur, reg);
				frame.dispose();
			}
		});
		btn_Deconnexion.setBounds(201, 320, 125, 35);
		frame.getContentPane().add(btn_Deconnexion);

		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
