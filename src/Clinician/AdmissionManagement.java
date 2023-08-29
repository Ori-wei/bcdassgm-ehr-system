package Clinician;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class AdmissionManagement extends JFrame {
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmissionManagement frame = new AdmissionManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdmissionManagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 800, 600);  // Set JFrame size
	    contentPane = new JPanel(new BorderLayout());
	    setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(612, 626)); // Size of panel
        
        // Inside the panel
        JLabel lblNewLabel = new JLabel("EHR System");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblNewLabel.setBounds(236, 37, 132, 71);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Admission Management");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(236, 99, 132, 46);
        panel.add(lblNewLabel_1);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JTabbedPane tabbedPane = new JTabbedPane();
	    tabbedPane.setPreferredSize(new Dimension(800, 600));
	    contentPane.add(tabbedPane, BorderLayout.CENTER);

        // Create Admission Panel
	    JPanel admissionPanel = new JPanel();
	    admissionPanel.setLayout(null);
	    
	    JLabel patientLabel = new JLabel("Patient:");
        patientLabel.setBounds(50, 50, 150, 25);
        admissionPanel.add(patientLabel);
        
        JComboBox<String> patientDropdown = new JComboBox<>(new String[] {"Patient 1", "Patient 2", "Patient 3"});
        patientDropdown.setBounds(200, 50, 150, 25);
        admissionPanel.add(patientDropdown);

        JLabel datetimeLabel = new JLabel("Datetime of Admission:");
        datetimeLabel.setBounds(50, 100, 150, 25);
        admissionPanel.add(datetimeLabel);
        
        JTextField datetimeField = new JTextField();
        datetimeField.setBounds(200, 100, 150, 25);
        datetimeField.setEnabled(false);
        admissionPanel.add(datetimeField);

        JLabel reasonLabel = new JLabel("Reason for Admission:");
        reasonLabel.setBounds(50, 150, 150, 25);
        admissionPanel.add(reasonLabel);
        
        JTextField reasonField = new JTextField();
        reasonField.setBounds(200, 150, 150, 25);
        admissionPanel.add(reasonField);
        
        JButton addButton = new JButton("Add");
        addButton.setBounds(200, 200, 80, 25);
        admissionPanel.add(addButton);
        
	    tabbedPane.addTab("Admission", null, admissionPanel, "Admission");

	    // dischargePanel
	    JPanel dischargePanel = new JPanel();
	    dischargePanel.setLayout(null);  // Setting to absolute layout

	    // Label and Dropdown for 'Patient'
	    JLabel patientLabelDischarge = new JLabel("Patient:");
	    patientLabelDischarge.setBounds(50, 50, 100, 25);
	    dischargePanel.add(patientLabelDischarge);

	    String[] patients = {"Patient1", "Patient2", "Patient3"};
	    JComboBox<String> patientDropdownDischarge = new JComboBox<>(patients);
	    patientDropdownDischarge.setBounds(200, 50, 150, 25);
	    dischargePanel.add(patientDropdownDischarge);

	    // Label and TextField for 'Datetime of Discharge or Death'
	    JLabel datetimeDischargeLabel = new JLabel("Datetime of Discharge/Death:");
	    datetimeDischargeLabel.setBounds(50, 100, 200, 25);
	    dischargePanel.add(datetimeDischargeLabel);

	    JTextField datetimeDischargeField = new JTextField();
	    datetimeDischargeField.setBounds(250, 100, 150, 25);
	    datetimeDischargeField.setEnabled(false);
	    dischargePanel.add(datetimeDischargeField);

	    // Label and Dropdown for 'Status at Discharge'
	    JLabel statusLabel = new JLabel("Status at Discharge:");
	    statusLabel.setBounds(50, 150, 200, 25);
	    dischargePanel.add(statusLabel);

	    String[] statuses = {"Alive", "Deceased"};
	    JComboBox<String> statusDropdown = new JComboBox<>(statuses);
	    statusDropdown.setBounds(200, 150, 100, 25);
	    dischargePanel.add(statusDropdown);

	    // Existing code to add the panel to the tab
	    tabbedPane.addTab("Discharge", null, dischargePanel, "Discharge Details");
	    
	    JButton addButton_1 = new JButton("Add");
	    addButton_1.setBounds(200, 200, 80, 25);
	    dischargePanel.add(addButton_1);
	    
	    pack();

	}
}
