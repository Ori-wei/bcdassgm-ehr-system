package Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class RegisterClinician {

	private JFrame frame;
	private JTextField tfName;
	private JTextField tfDesignation;
	private JTextField tfClinicianICNo;
	private JTextField tfHospitalID;
	private JTextField tfDepartment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterClinician window = new RegisterClinician();
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
	public RegisterClinician() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 696, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 662, 465);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbSystem = new JLabel("EHR System");
		lbSystem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbSystem.setBounds(216, 28, 145, 33);
		panel.add(lbSystem);
		
		JLabel lbScreen = new JLabel("Register Clinician");
		lbScreen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbScreen.setBounds(205, 71, 156, 33);
		panel.add(lbScreen);
		
		JLabel lbClinicianName = new JLabel("Clinician Name: ");
		lbClinicianName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbClinicianName.setBounds(124, 170, 131, 34);
		panel.add(lbClinicianName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfName.setColumns(10);
		tfName.setBounds(303, 169, 157, 34);
		panel.add(tfName);
		
		JLabel lbDesignation = new JLabel("Designation:");
		lbDesignation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDesignation.setBounds(124, 214, 131, 34);
		panel.add(lbDesignation);
		
		tfDesignation = new JTextField();
		tfDesignation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfDesignation.setColumns(10);
		tfDesignation.setBounds(303, 214, 157, 34);
		panel.add(tfDesignation);
		
		JLabel lbHospitalName = new JLabel("Hospital Name: ");
		lbHospitalName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbHospitalName.setBounds(124, 302, 131, 34);
		panel.add(lbHospitalName);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(244, 412, 105, 33);
		panel.add(btnSubmit);
		
		JLabel lbClinicianICNo = new JLabel("Clinician IC: ");
		lbClinicianICNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbClinicianICNo.setBounds(124, 125, 131, 34);
		panel.add(lbClinicianICNo);
		
		tfClinicianICNo = new JTextField();
		tfClinicianICNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfClinicianICNo.setColumns(10);
		tfClinicianICNo.setBounds(303, 124, 157, 34);
		panel.add(tfClinicianICNo);
		
		JLabel lbHospitalID = new JLabel("Hospital ID: ");
		lbHospitalID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbHospitalID.setBounds(124, 346, 131, 34);
		panel.add(lbHospitalID);
		
		tfHospitalID = new JTextField();
		tfHospitalID.setEditable(false);
		tfHospitalID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfHospitalID.setColumns(10);
		tfHospitalID.setBounds(303, 346, 157, 34);
		panel.add(tfHospitalID);
		
		JComboBox cbHospitalName = new JComboBox();
		cbHospitalName.setBounds(303, 302, 157, 30);
		panel.add(cbHospitalName);
		
		JLabel lbDepartment = new JLabel("Department: ");
		lbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDepartment.setBounds(124, 258, 131, 34);
		panel.add(lbDepartment);
		
		tfDepartment = new JTextField();
		tfDepartment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfDepartment.setColumns(10);
		tfDepartment.setBounds(303, 258, 157, 34);
		panel.add(tfDepartment);
	}
}
