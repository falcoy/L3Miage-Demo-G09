package GUI;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Module_Messagerie.MessageTexte;
import Structure_Contact.Contact;
import controleur.Connexion;
import controleur.Demarrer;
import serveur._Authentification;
import serveur._CreationEvenement;
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
	private Contact proprio;

	/**
	 * Create the application.
	 */
	public Menu(String ipServeur, Registry reg, String loginProprio) {
		this.ipServeur = ipServeur;
		this.reg = reg;
		try {
			Remote remote = reg.lookup("rmi://" + ipServeur + "/MessagerieEvenement");

			if (remote instanceof _Messagerie) {
				this.proprio = ((_Messagerie) remote).getProprio(loginProprio);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

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
				try {
					Remote remote = reg.lookup("rmi://" + ipServeur + "/CreationEvenement");

					if (remote instanceof _CreationEvenement) {
						CreationEvenement creationEvenement = new CreationEvenement(ipServeur, reg, proprio.getLogin());
					}
					frame.dispose();
				} catch (Exception ex) {
					System.out.println(ex);
				}
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
						RechercheEvenement rechercheEvenement = new RechercheEvenement(ipServeur, reg,
								new serveur.RechercheEvenement(), proprio.getLogin());
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

						Messagerie affichageMessages = new Messagerie(ipServeur, reg, proprio,
								((_Messagerie) remote).consulterMessagerie(proprio));
					}
					frame.dispose();
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}

		});
		btn_ConsulterMaMessagerie.setBounds(20, 190, 300, 50);
		frame.getContentPane().add(btn_ConsulterMaMessagerie);

		JButton btn_Quitter = new JButton("Quitter");
		btn_Quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.dispose();
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		btn_Quitter.setBounds(201, 320, 125, 35);
		frame.getContentPane().add(btn_Quitter);

		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
