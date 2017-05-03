package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Remote;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import FrameworkExceptions.FrameworkException;
import Structure_Contact.Contact;
import serveur.Messagerie;
import serveur._Messagerie;

public class EnvoiMessage {

	private JFrame frame;
	private JTextField textField;

	private String ipServeur;
	private Registry reg;
	private Contact proprio;

	/**
	 * Create the application.
	 */
	public EnvoiMessage(String ipServeur, Registry reg, Contact proprio) {
		this.ipServeur = ipServeur;
		this.reg = reg;
		this.proprio=proprio;
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
				frame.dispose();
				
			}
		});

		JLabel lbl_MyEwine = new JLabel("My eWine");
		lbl_MyEwine.setFont(new Font("Dialog", Font.BOLD, 16));
		lbl_MyEwine.setBounds(133, 12, 80, 26);
		frame.getContentPane().add(lbl_MyEwine);

		JLabel lbl_EnvoiMessage = new JLabel("Envoyer un message");
		lbl_EnvoiMessage.setBounds(117, 51, 130, 14);
		frame.getContentPane().add(lbl_EnvoiMessage);

		JLabel lbl_Expediteur = new JLabel("Expediteur");
		lbl_Expediteur.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Expediteur.setBounds(41, 94, 88, 14);
		frame.getContentPane().add(lbl_Expediteur);

		JTextField textField_ValeurFinanciere = new JTextField();
		textField_ValeurFinanciere.setColumns(10);
		textField_ValeurFinanciere.setBounds(133, 93, 150, 18);
		frame.getContentPane().add(textField_ValeurFinanciere);textField_ValeurFinanciere.setText(this.proprio.getNom());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(20, 170, 300, 141);
		frame.getContentPane().add(scrollPane);

		JTextArea textArea_Message = new JTextArea();
		scrollPane.setViewportView(textArea_Message);
		textArea_Message.setBorder(UIManager.getBorder("TextField.border"));
		textArea_Message.setLineWrap(true);

		JLabel lbl_Destinataire = new JLabel("Destinataire");
		lbl_Destinataire.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Destinataire.setBounds(41, 120, 88, 14);
		frame.getContentPane().add(lbl_Destinataire);

		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(133, 119, 150, 18);
		frame.getContentPane().add(textField);

		JButton btn_Envoyer = new JButton("Envoyer");
		btn_Envoyer.setBounds(12, 323, 125, 35);
		frame.getContentPane().add(btn_Envoyer);
		btn_Envoyer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Remote remote = reg.lookup("rmi://" + ipServeur + "/MessagerieEvenement");

					if (remote instanceof _Messagerie) {
						((_Messagerie) remote).envoiMsg(proprio,textField.getText() , textArea_Message.getText());
						frame.dispose();
					}

				} catch (Exception ex) {
					new FrameworkException("envoi fail");
					System.out.println(ex);
				}
			}
		});

		JLabel lbl_Message = new JLabel("Message");
		lbl_Message.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Message.setBounds(20, 146, 88, 14);
		frame.getContentPane().add(lbl_Message);

		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
