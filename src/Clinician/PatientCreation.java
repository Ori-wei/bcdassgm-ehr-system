package Clinician;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PatientCreation {

	private JFrame frame;
	private JTextField tfICNo;
	private JTextField tfName;
	private JTextField tfPhoneNumber;
	private JTextField tfEmail;
	private JTextField tfAddress;
	private JTextField tfDoB;
	private JTextField tfSex;
	private JTextField tfEmergencyContactPerson;
	private JTextField tfRelationship;
	private JTextField tfEmergencyPhoneNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientCreation window = new PatientCreation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PatientCreation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 873, 764);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 839, 707);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbSystem = new JLabel("EHR System");
		lbSystem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbSystem.setBounds(280, 48, 145, 33);
		panel.add(lbSystem);
		
		JLabel lbPatientCreation = new JLabel("Patient Creation");
		lbPatientCreation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbPatientCreation.setBounds(281, 91, 156, 33);
		panel.add(lbPatientCreation);
		
		JLabel lbICNo = new JLabel("IC No: ");
		lbICNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbICNo.setBounds(128, 158, 131, 34);
		panel.add(lbICNo);
		
		tfICNo = new JTextField();
		tfICNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfICNo.setColumns(10);
		tfICNo.setBounds(385, 157, 157, 34);
		panel.add(tfICNo);
		
		JLabel lbName = new JLabel("Patient Name:");
		lbName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbName.setBounds(128, 202, 131, 34);
		panel.add(lbName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfName.setColumns(10);
		tfName.setBounds(385, 202, 157, 34);
		panel.add(tfName);
		
		JLabel lbPhoneNumber = new JLabel("Phone Number: ");
		lbPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbPhoneNumber.setBounds(128, 246, 131, 34);
		panel.add(lbPhoneNumber);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPhoneNumber.setColumns(10);
		tfPhoneNumber.setBounds(385, 246, 157, 34);
		panel.add(tfPhoneNumber);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(272, 608, 105, 33);
		panel.add(btnSubmit);
		
		JLabel lbEmail = new JLabel("Email: ");
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbEmail.setBounds(128, 290, 131, 34);
		panel.add(lbEmail);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfEmail.setColumns(10);
		tfEmail.setBounds(385, 290, 157, 34);
		panel.add(tfEmail);
		
		JLabel lbAddress = new JLabel("Address: ");
		lbAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbAddress.setBounds(128, 334, 131, 34);
		panel.add(lbAddress);
		
		tfAddress = new JTextField();
		tfAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfAddress.setColumns(10);
		tfAddress.setBounds(385, 334, 157, 34);
		panel.add(tfAddress);
		
		JLabel lbDoB = new JLabel("Date of Birth: ");
		lbDoB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDoB.setBounds(128, 378, 131, 34);
		panel.add(lbDoB);
		
		tfDoB = new JTextField();
		tfDoB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfDoB.setColumns(10);
		tfDoB.setBounds(385, 378, 157, 34);
		panel.add(tfDoB);
		
		JLabel lbSex = new JLabel("Sex: ");
		lbSex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbSex.setBounds(128, 422, 131, 34);
		panel.add(lbSex);
		
		tfSex = new JTextField();
		tfSex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSex.setColumns(10);
		tfSex.setBounds(385, 422, 157, 34);
		panel.add(tfSex);
		
		JLabel lbEmergencyContactPerson = new JLabel("Emergency Contact Person: ");
		lbEmergencyContactPerson.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbEmergencyContactPerson.setBounds(128, 466, 200, 34);
		panel.add(lbEmergencyContactPerson);
		
		tfEmergencyContactPerson = new JTextField();
		tfEmergencyContactPerson.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfEmergencyContactPerson.setColumns(10);
		tfEmergencyContactPerson.setBounds(385, 466, 157, 34);
		panel.add(tfEmergencyContactPerson);
		
		JLabel lbRelationship = new JLabel("Relationship: ");
		lbRelationship.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbRelationship.setBounds(128, 510, 200, 34);
		panel.add(lbRelationship);
		
		tfRelationship = new JTextField();
		tfRelationship.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfRelationship.setColumns(10);
		tfRelationship.setBounds(385, 510, 157, 34);
		panel.add(tfRelationship);
		
		JLabel lblEmergencyPhoneNumber = new JLabel("Emergency Phone Number: ");
		lblEmergencyPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmergencyPhoneNumber.setBounds(128, 554, 200, 34);
		panel.add(lblEmergencyPhoneNumber);
		
		tfEmergencyPhoneNumber = new JTextField();
		tfEmergencyPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfEmergencyPhoneNumber.setColumns(10);
		tfEmergencyPhoneNumber.setBounds(385, 554, 157, 34);
		panel.add(tfEmergencyPhoneNumber);
	}

}
