package GUI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellEditor.DefaultTextField;

import Module_Messagerie.MessageTexte;
import Structure_Contact.Contact;
import sun.rmi.runtime.NewThreadAction;

import java.awt.event.ActionListener;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;

public class AffichageMessage {

	private JFrame frame;
	private MessageTexte message;

	private String ipServeur;
	private Registry reg;
	private Contact proprio;
	/**
	 * Create the application.
	 */
	public AffichageMessage(MessageTexte msgTxt,String ipServeur, Registry reg, Contact proprio) {
		this.ipServeur=ipServeur;
		this.reg=reg;
		this.proprio=proprio;
		this.message=msgTxt;
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
				frame.dispose();
			}
		});
		btn_Retour.setBounds(203, 323, 125, 35);
		frame.getContentPane().add(btn_Retour);
		
		JLabel lbl_MyEwine = new JLabel("My eWine");
		lbl_MyEwine.setFont(new Font("Dialog", Font.BOLD, 16));
		lbl_MyEwine.setBounds(133, 12, 80, 26);
		frame.getContentPane().add(lbl_MyEwine);
		
		JLabel lbl_AfficherMessage = new JLabel("Afficher un message");
		lbl_AfficherMessage.setBounds(117, 51, 130, 14);
		frame.getContentPane().add(lbl_AfficherMessage);
		
		JLabel lbl_Expediteur = new JLabel("Expediteur");
		lbl_Expediteur.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Expediteur.setBounds(42, 117, 88, 14);
		frame.getContentPane().add(lbl_Expediteur);
		
		JTextField textField_ValeurFinanciere = new JTextField();
		textField_ValeurFinanciere.setEditable(false);
		textField_ValeurFinanciere.setColumns(10);
		textField_ValeurFinanciere.setBounds(134, 116, 150, 18);
		frame.getContentPane().add(textField_ValeurFinanciere);textField_ValeurFinanciere.setText(this.message.getExpediteur().getNom());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(20, 170, 300, 100);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea_Message = new JTextArea();
		textArea_Message.setEditable(false);
		scrollPane.setViewportView(textArea_Message);
		textArea_Message.setBorder(UIManager.getBorder("TextField.border"));
		textArea_Message.setLineWrap(true);
		textArea_Message.setText(this.message.getcontenu());
		
		JLabel lbl_Message = new JLabel("Message");
		lbl_Message.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Message.setBounds(20, 146, 88, 14);
		frame.getContentPane().add(lbl_Message);

		JButton btn_Repondre = new JButton("Repondre");
		btn_Repondre.setBounds(117, 281, 100, 30);
		frame.getContentPane().add(btn_Repondre);
		btn_Repondre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EnvoiMessage envoiMessage = new EnvoiMessage(ipServeur, reg, proprio);
			}
		});
		
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
