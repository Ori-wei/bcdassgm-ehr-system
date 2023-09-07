package Admin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Clinician.CreateClinicalSummary1;
import Clinician.DisplayPatientEHR;
import Clinician.PatientCreation;

public class MenuAdmin {

	private JFrame frame;
	public String adminID;
	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String adminID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdmin window = new MenuAdmin(adminID);
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
	public MenuAdmin(String adminID) {
		initialize();
		this.adminID=adminID;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 685, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	    
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 1, 655, 640);
        frame.getContentPane().add(panel);
        
        // Inside the panel
        JLabel lblNewLabel = new JLabel("EHR System");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblNewLabel.setBounds(236, 37, 132, 71);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Admin Menu");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(236, 99, 132, 46);
        panel.add(lblNewLabel_1);
        
        JButton btnCreateHospital = new JButton("Create Hospital");
        btnCreateHospital.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HospitalDetails.createAndShowGUI(adminID);
        		frame.dispose();
        	}
        });
        btnCreateHospital.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnCreateHospital.setBounds(212, 266, 193, 21);
        panel.add(btnCreateHospital);
        
        JButton btnRegisterClnician = new JButton("Register Clinician");
        btnRegisterClnician.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RegisterClinician.createAndShowGUI(adminID);
        		frame.dispose();
        	}
        });
        btnRegisterClnician.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnRegisterClnician.setBounds(225, 221, 167, 21);
        panel.add(btnRegisterClnician);

	}

}
