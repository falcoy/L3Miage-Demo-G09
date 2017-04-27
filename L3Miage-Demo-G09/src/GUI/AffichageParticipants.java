package GUI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class AffichageParticipants {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public AffichageParticipants() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btn_Retour = new JButton("Retour");
		btn_Retour.setBounds(203, 323, 125, 35);
		frame.getContentPane().add(btn_Retour);
		
		JLabel lbl_MyEwine = new JLabel("My eWine");
		lbl_MyEwine.setFont(new Font("Dialog", Font.BOLD, 16));
		lbl_MyEwine.setBounds(133, 12, 80, 26);
		frame.getContentPane().add(lbl_MyEwine);
		
		JLabel lbl_AffichageParticipations = new JLabel("Affichage des participations");
		lbl_AffichageParticipations.setBounds(84, 50, 173, 14);
		frame.getContentPane().add(lbl_AffichageParticipations);
		
		JLabel lbl_Filtrer = new JLabel("Filtrer");
		lbl_Filtrer.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Filtrer.setBounds(12, 96, 61, 14);
		frame.getContentPane().add(lbl_Filtrer);
		
		JTextField textField_Filtrer = new JTextField();
		textField_Filtrer.setBounds(71, 95, 150, 18);
		frame.getContentPane().add(textField_Filtrer);
		textField_Filtrer.setColumns(10);
		
		JButton btn_Filtrer = new JButton("Filtrer");
		btn_Filtrer.setBounds(250, 95, 78, 18);
		frame.getContentPane().add(btn_Filtrer);
		
		JTable table_Participants = new JTable();
		table_Participants.setBorder(UIManager.getBorder("TextField.border"));
		table_Participants.setBounds(12, 122, 316, 189);
		frame.getContentPane().add(table_Participants);
		
		JButton btn_Afficher = new JButton("Afficher");
		btn_Afficher.setBounds(12, 323, 125, 35);
		frame.getContentPane().add(btn_Afficher);
		
		frame.setVisible(true);
	}

}
