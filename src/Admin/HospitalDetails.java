package Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class HospitalDetails {

	private JFrame frame;
	private JTextField tfName;
	private JTextField tfAddress;
	private JTextField tfContactNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospitalDetails window = new HospitalDetails();
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
	public HospitalDetails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 731, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 697, 499);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbSystem = new JLabel("EHR System");
		lbSystem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbSystem.setBounds(260, 36, 145, 33);
		panel.add(lbSystem);
		
		JLabel lblHospitalDetails = new JLabel("Hospital Details");
		lblHospitalDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHospitalDetails.setBounds(261, 79, 156, 33);
		panel.add(lblHospitalDetails);
		
		JLabel lbName = new JLabel("Hospital Name: ");
		lbName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbName.setBounds(63, 178, 131, 34);
		panel.add(lbName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfName.setColumns(10);
		tfName.setBounds(242, 177, 157, 34);
		panel.add(tfName);
		
		JLabel lbAddress = new JLabel("Address: ");
		lbAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbAddress.setBounds(63, 236, 131, 34);
		panel.add(lbAddress);
		
		tfAddress = new JTextField();
		tfAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfAddress.setColumns(10);
		tfAddress.setBounds(242, 236, 157, 34);
		panel.add(tfAddress);
		
		JLabel lbContactNumber = new JLabel("Contact Number: ");
		lbContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbContactNumber.setBounds(63, 301, 131, 34);
		panel.add(lbContactNumber);
		
		tfContactNumber = new JTextField();
		tfContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfContactNumber.setColumns(10);
		tfContactNumber.setBounds(242, 301, 157, 34);
		panel.add(tfContactNumber);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(179, 384, 105, 33);
		panel.add(btnSubmit);
	}

}
