package GUI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ModificationContributionService {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public ModificationContributionService() {
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
		
		JLabel lbl_ModificationContributionSrevice = new JLabel("Modification de la contribution d'un service");
		lbl_ModificationContributionSrevice.setBounds(52, 42, 269, 14);
		frame.getContentPane().add(lbl_ModificationContributionSrevice);
		
		JLabel lbl_Nom = new JLabel("Nom");
		lbl_Nom.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Nom.setBounds(41, 94, 35, 14);
		frame.getContentPane().add(lbl_Nom);
		
		JLabel lbl_ValeurFinanciere = new JLabel("Valeur financière");
		lbl_ValeurFinanciere.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_ValeurFinanciere.setBounds(12, 118, 136, 14);
		frame.getContentPane().add(lbl_ValeurFinanciere);
		
		JTextField textField_Nom = new JTextField();
		textField_Nom.setEditable(false);
		textField_Nom.setBounds(83, 93, 213, 18);
		frame.getContentPane().add(textField_Nom);
		textField_Nom.setColumns(10);
		
		JTextField textField_ValeurFinanciere = new JTextField();
		textField_ValeurFinanciere.setColumns(10);
		textField_ValeurFinanciere.setBounds(146, 118, 150, 18);
		frame.getContentPane().add(textField_ValeurFinanciere);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(121, 145, 175, 100);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea_Description = new JTextArea();
		scrollPane.setViewportView(textArea_Description);
		textArea_Description.setBorder(UIManager.getBorder("TextField.border"));
		textArea_Description.setLineWrap(true);
		
		JLabel lbl_Description = new JLabel("Description");
		lbl_Description.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Description.setBounds(26, 145, 88, 14);
		frame.getContentPane().add(lbl_Description);

		JButton btn_Valider = new JButton("Valider");
		btn_Valider.setBounds(26, 281, 100, 30);
		frame.getContentPane().add(btn_Valider);
		
		JButton btn_Supprimer = new JButton("Supprimer");
		btn_Supprimer.setBounds(196, 281, 100, 30);
		frame.getContentPane().add(btn_Supprimer);
		
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
