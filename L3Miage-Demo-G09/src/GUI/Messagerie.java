package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Module_Messagerie.MessageTexte;
import Structure_Contact.Contact;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;

public class Messagerie {

	private JFrame frame;
	private String ipServeur;
	private Registry reg;
	private List<MessageTexte> messages;
	private Contact proprio;

	/**
	 * Create the application.
	 */
	public Messagerie(String ipServeur, Registry reg, Contact proprio, List<MessageTexte> messages) {
		this.ipServeur = ipServeur;
		this.reg = reg;
		this.messages = messages;
		this.proprio = proprio;
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
		btn_Retour.setBounds(203, 323, 125, 35);
		frame.getContentPane().add(btn_Retour);
		btn_Retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu(ipServeur, reg, proprio.getLogin());
				frame.dispose();

			}
		});

		JLabel lbl_MyEwine = new JLabel("My eWine");
		lbl_MyEwine.setFont(new Font("Dialog", Font.BOLD, 16));
		lbl_MyEwine.setBounds(133, 12, 80, 26);
		frame.getContentPane().add(lbl_MyEwine);

		JLabel lbl_MesMessages = new JLabel("Mes messages");
		lbl_MesMessages.setBounds(126, 50, 97, 14);
		frame.getContentPane().add(lbl_MesMessages);

		JLabel lbl_Filtrer = new JLabel("Filtrer");
		lbl_Filtrer.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Filtrer.setBounds(12, 96, 61, 14);
		frame.getContentPane().add(lbl_Filtrer);

		JTextField textField_Filtrer = new JTextField();
		textField_Filtrer.setBounds(71, 95, 150, 18);
		frame.getContentPane().add(textField_Filtrer);
		textField_Filtrer.setColumns(10);

		JButton btn_NouveauMessage = new JButton("Nouveau");
		btn_NouveauMessage.setBounds(12, 281, 314, 30);
		frame.getContentPane().add(btn_NouveauMessage);
		btn_NouveauMessage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EnvoiMessage envoiMessage = new EnvoiMessage(ipServeur, reg, proprio);
			}
		});

		JList<String> list = new JList<String>();
		list.setBounds(12, 122, 316, 147);
		frame.getContentPane().add(list);
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		for (int i = 0; i < this.messages.size(); i++) {
			dlm.addElement(messages.get(i).getExpediteur().getNom() + " - " + messages.get(i).getDate());
		}
		list.setModel(dlm);

		JButton btn_Afficher = new JButton("Afficher");
		btn_Afficher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!list.isSelectionEmpty()) {
					AffichageMessage affichageMessage = new AffichageMessage(messages.get(list.getSelectedIndex()),
							ipServeur, reg, proprio);
				}
			}
		});
		btn_Afficher.setBounds(12, 323, 125, 35);
		frame.getContentPane().add(btn_Afficher);

		JButton btn_Filtrer = new JButton("Filtrer");
		btn_Filtrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField_Filtrer.getText() != "") {
					List<String> noms = new ArrayList<String>();
					for (int i = 0; i < list.getModel().getSize(); i++) {
						noms.add(list.getModel().getElementAt(i).toString());
					}

					DefaultListModel<String> dlm2 = new DefaultListModel<String>();
					for (int i = 0; i < dlm.getSize(); i++) {
						if (dlm.get(i).contains(textField_Filtrer.getText())) {
							list.removeAll();

							dlm2.addElement(dlm.get(i));
						}
					}
					list.setModel(dlm2);
				}
			}
		});
		btn_Filtrer.setBounds(250, 95, 78, 18);
		frame.getContentPane().add(btn_Filtrer);

		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
