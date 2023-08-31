package Clinician;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class ManageAdmission {

	public String username;
	public String patientName;
	public String CSID;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageAdmission window = new ManageAdmission();
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
	public ManageAdmission() {
		initialize();
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
        lblNewLabel.setBounds(236, 37, 170, 71);
        panel.add(lblNewLabel);
        panel.setLayout(new BorderLayout(0, 0));
        
        JTabbedPane tabbedPane = new JTabbedPane();
	    tabbedPane.setPreferredSize(new Dimension(800, 600));
	    panel.add(tabbedPane, BorderLayout.CENTER);

        // Create Admission Panel
	    JPanel admissionPanel = new JPanel();
	    admissionPanel.setLayout(null);
	    
	    JLabel patientLabel = new JLabel("Patient:");
        patientLabel.setBounds(33, 151, 150, 25);
        admissionPanel.add(patientLabel);
        
        JComboBox<String> patientDropdown = new JComboBox<>(new String[] {"Patient 1", "Patient 2", "Patient 3"});
        patientDropdown.setBounds(201, 151, 150, 25);
        admissionPanel.add(patientDropdown);

        JLabel datetimeLabel = new JLabel("Datetime of Admission:");
        datetimeLabel.setBounds(33, 201, 150, 25);
        admissionPanel.add(datetimeLabel);
        
        JTextField datetimeField = new JTextField();
        datetimeField.setBounds(201, 201, 150, 25);
        datetimeField.setEnabled(false);
        admissionPanel.add(datetimeField);

        JLabel reasonLabel = new JLabel("Reason for Admission:");
        reasonLabel.setBounds(33, 251, 150, 25);
        admissionPanel.add(reasonLabel);
        
        JTextField reasonField = new JTextField();
        reasonField.setBounds(201, 251, 150, 25);
        admissionPanel.add(reasonField);
        
        JButton addButton = new JButton("Add");
        addButton.setBounds(201, 301, 80, 25);
        admissionPanel.add(addButton);
        
	    tabbedPane.addTab("Admission", null, admissionPanel, "Admission");
	    
        JLabel lblNewLabel_1 = new JLabel("Admission Management");
        lblNewLabel_1.setBounds(201, 74, 218, 46);
        admissionPanel.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(33, 10, 85, 21);
        admissionPanel.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//CreateClinicalSummary3.createAndShowGUI(username, patientName, CSID);
        	}
        });

	    // dischargePanel
	    JPanel dischargePanel = new JPanel();
	    dischargePanel.setLayout(null);  // Setting to absolute layout

	    // Label and Dropdown for 'Patient'
	    JLabel patientLabelDischarge = new JLabel("Patient:");
	    patientLabelDischarge.setBounds(33, 151, 100, 25);
	    dischargePanel.add(patientLabelDischarge);

	    String[] patients = {"Patient1", "Patient2", "Patient3"};
	    JComboBox<String> patientDropdownDischarge = new JComboBox<>(patients);
	    patientDropdownDischarge.setBounds(200, 151, 150, 25);
	    dischargePanel.add(patientDropdownDischarge);

	    // Label and TextField for 'Datetime of Discharge or Death'
	    JLabel datetimeDischargeLabel = new JLabel("Datetime of Discharge/Death:");
	    datetimeDischargeLabel.setBounds(33, 201, 200, 25);
	    dischargePanel.add(datetimeDischargeLabel);

	    JTextField datetimeDischargeField = new JTextField();
	    datetimeDischargeField.setBounds(200, 201, 150, 25);
	    datetimeDischargeField.setEnabled(false);
	    dischargePanel.add(datetimeDischargeField);

	    // Label and Dropdown for 'Status at Discharge'
	    JLabel statusLabel = new JLabel("Status at Discharge:");
	    statusLabel.setBounds(33, 251, 200, 25);
	    dischargePanel.add(statusLabel);

	    String[] statuses = {"Alive", "Deceased"};
	    JComboBox<String> statusDropdown = new JComboBox<>(statuses);
	    statusDropdown.setBounds(200, 251, 100, 25);
	    dischargePanel.add(statusDropdown);

	    // Existing code to add the panel to the tab
	    tabbedPane.addTab("Discharge", null, dischargePanel, "Discharge Details");
	    
	    JButton addButton_1 = new JButton("Add");
	    addButton_1.setBounds(200, 301, 80, 25);
	    dischargePanel.add(addButton_1);
	    
	}

}
