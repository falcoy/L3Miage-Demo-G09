package GUI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ModificationContributionBien {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public ModificationContributionBien() {
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
		btn_Retour.setBounds(112, 320, 125, 35);
		frame.getContentPane().add(btn_Retour);
		
		JLabel lbl_MyEwine = new JLabel("My eWine");
		lbl_MyEwine.setFont(new Font("Dialog", Font.BOLD, 16));
		lbl_MyEwine.setBounds(133, 12, 80, 26);
		frame.getContentPane().add(lbl_MyEwine);
		
		JLabel lbl_ModificationContributionBien = new JLabel("Modification de la contribution d'un bien");
		lbl_ModificationContributionBien.setBounds(59, 42, 251, 14);
		frame.getContentPane().add(lbl_ModificationContributionBien);JLabel lbl_Nom = new JLabel("Nom");
		lbl_Nom.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Nom.setBounds(41, 94, 35, 14);
		frame.getContentPane().add(lbl_Nom);
		
		JLabel lbl_Qantite = new JLabel("Quantite");
		lbl_Qantite.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Qantite.setBounds(12, 119, 73, 14);
		frame.getContentPane().add(lbl_Qantite);
		
		JLabel lbl_ValeurFinanciere = new JLabel("Valeur financière");
		lbl_ValeurFinanciere.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_ValeurFinanciere.setBounds(12, 145, 136, 14);
		frame.getContentPane().add(lbl_ValeurFinanciere);
		
		JTextField textField_Nom = new JTextField();
		textField_Nom.setEditable(false);
		textField_Nom.setBounds(83, 93, 213, 18);
		frame.getContentPane().add(textField_Nom);
		textField_Nom.setColumns(10);
		
		JTextField textField_Quantite = new JTextField();
		textField_Quantite.setColumns(10);
		textField_Quantite.setBounds(83, 118, 90, 18);
		frame.getContentPane().add(textField_Quantite);
		
		JTextField textField_ValeurFinanciere = new JTextField();
		textField_ValeurFinanciere.setColumns(10);
		textField_ValeurFinanciere.setBounds(146, 144, 150, 18);
		frame.getContentPane().add(textField_ValeurFinanciere);
		
		JLabel lbl_Unite = new JLabel("Unite");
		lbl_Unite.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Unite.setBounds(191, 119, 46, 14);
		frame.getContentPane().add(lbl_Unite);
		
		JTextField textField_Unite = new JTextField();
		textField_Unite.setColumns(10);
		textField_Unite.setBounds(231, 118, 65, 18);
		frame.getContentPane().add(textField_Unite);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(121, 172, 175, 100);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea_Description = new JTextArea();
		scrollPane.setViewportView(textArea_Description);
		textArea_Description.setBorder(UIManager.getBorder("TextField.border"));
		textArea_Description.setLineWrap(true);
		
		JLabel lbl_Description = new JLabel("Description");
		lbl_Description.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Description.setBounds(26, 171, 88, 14);
		frame.getContentPane().add(lbl_Description);

		JButton btn_Valider = new JButton("Valider");
		btn_Valider.setBounds(26, 281, 100, 30);
		frame.getContentPane().add(btn_Valider);
		
		JButton btn_Supprimer = new JButton("Supprimer");
		btn_Supprimer.setBounds(196, 281, 100, 30);
		frame.getContentPane().add(btn_Supprimer);
		
		frame.setVisible(true);
	}

}
