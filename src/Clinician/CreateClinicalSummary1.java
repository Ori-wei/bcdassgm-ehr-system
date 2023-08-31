package Clinician;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DatabaseObject.Hospital;
import DatabaseObject.Patient;
import SymmetricEncryption.Decrypter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class CreateClinicalSummary1 {
	
	public String username;
	public String patientName = null;
	public String CSID = null;
	private JFrame frame;
	private JTextField txtPatient;
	private JTextField txtReportID;
	private JComboBox<Patient> cbPatientID;

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateClinicalSummary1 window = new CreateClinicalSummary1(username);
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
	public CreateClinicalSummary1(String username) {
		initialize();
		this.username = username;
		String patientID;
		String patientName;
		
		// Populate Patient Name
		
		
		// Generate CSID
        String CSID = null;
		int rowCount;
        try (Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\ASUS\\MyDB;","root","toor");
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery("SELECT * from BCD.clinicalSummary")) {
	        	
			rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            if(rowCount>=0)
            {
         	   rowCount+=1;
         	   System.out.println("Proceed");
         	   String prefix = "CS";
                // Find the number of digits in row
                int numberOfDigits = String.valueOf(rowCount).length();                        
                // Determine the number of zeros to prepend
                int numberOfZeros = 4 - numberOfDigits;  // Assuming 4-digit CSID
                // Create zeros string
                StringBuilder zeros = new StringBuilder();
                for (int i = 0; i < numberOfZeros; i++) {
                    zeros.append("0");
                }
                CSID = (prefix + zeros + rowCount);    
                System.out.println(CSID);
            }
            else
            {
         	   System.out.println("Error");  
            }
		} catch (SQLException e) {
            e.printStackTrace();
        }
        txtReportID.setText(CSID);
        
        //Populate PatientID dropdown
        List<Patient> patientList = new ArrayList<>();
        try {
	    	Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\ASUS\\MyDB;","root","toor");
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * from BCD.patient");
            while (rs.next()) {
            	//decrypt it!
				Decrypter decypt = new Decrypter();
            	String decryptedIC = decypt.decrypter(rs.getString("IC_No"));
            	System.out.println("Original Content: " + decryptedIC);
            	Patient patient = new Patient(decryptedIC, rs.getString("name"));
                patientList.add(patient);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            for (Patient patient : patientList) {
            	patientID=patient.getPatientID();
            	patientName = patient.getName();   
            	cbPatientID.addItem(patient);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }  
        
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

        JLabel lblNewLabel_1 = new JLabel("Medical Report");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(236, 99, 132, 46);
        panel.add(lblNewLabel_1);
        
        JLabel lblIC = new JLabel("Patient IC:");
        lblIC.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblIC.setBounds(33, 149, 101, 13);
		panel.add(lblIC);
        
		JLabel lblNewLabel_2 = new JLabel("Patient Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(235, 149, 101, 13);
		panel.add(lblNewLabel_2);
		
		txtPatient = new JTextField();
		txtPatient.setBounds(235, 175, 143, 32);
		txtPatient.setEnabled(false);
		panel.add(txtPatient);
		txtPatient.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Report ID:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(451, 149, 101, 13);
		panel.add(lblNewLabel_3);
		
		txtReportID = new JTextField();
		txtReportID.setBounds(451, 175, 143, 32);
		txtReportID.setEnabled(false);
		panel.add(txtReportID);
		txtReportID.setColumns(10);
        
        JLabel lblNewLabel_2_1 = new JLabel("History");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(35, 338, 101, 19);
		panel.add(lblNewLabel_2_1);
		
		JTextArea txtHistory = new JTextArea();
		txtHistory.setBounds(37, 367, 557, 58);
		panel.add(txtHistory);
		
		JLabel lblSpecialist = new JLabel("Area of Specialist");
		lblSpecialist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSpecialist.setBounds(35, 219, 132, 19);
		panel.add(lblSpecialist);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Cardiology");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNewRadioButton.setBounds(31, 244, 103, 21);
		panel.add(rdbtnNewRadioButton);
        
        JRadioButton rdbtnEndocrinology = new JRadioButton("Endocrinology");
		rdbtnEndocrinology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnEndocrinology.setBounds(174, 244, 112, 21);
		panel.add(rdbtnEndocrinology);
		
		JRadioButton rdbtnGastroenterology = new JRadioButton("Gastroenterology");
		rdbtnGastroenterology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnGastroenterology.setBounds(340, 244, 130, 21);
		panel.add(rdbtnGastroenterology);
		
		JRadioButton rdbtnHematology = new JRadioButton("Hematology");
		rdbtnHematology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnHematology.setBounds(491, 244, 103, 21);
		panel.add(rdbtnHematology);
		
		JRadioButton rdbtnInfectiousDiseases = new JRadioButton("Infectious Diseases");
		rdbtnInfectiousDiseases.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnInfectiousDiseases.setBounds(33, 267, 140, 21);
		panel.add(rdbtnInfectiousDiseases);
        
        JRadioButton rdbtnNephrology = new JRadioButton("Nephrology");
		rdbtnNephrology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNephrology.setBounds(174, 267, 103, 21);
		panel.add(rdbtnNephrology);
		
		JRadioButton rdbtnOncology = new JRadioButton("Oncology");
		rdbtnOncology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnOncology.setBounds(340, 267, 103, 21);
		panel.add(rdbtnOncology);
		
		JRadioButton rdbtnPulmonology = new JRadioButton("Pulmonology");
		rdbtnPulmonology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnPulmonology.setBounds(491, 267, 103, 21);
		panel.add(rdbtnPulmonology);
		
		JRadioButton rdbtnRheumatology = new JRadioButton("Rheumatology");
		rdbtnRheumatology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnRheumatology.setBounds(33, 290, 117, 21);
		panel.add(rdbtnRheumatology);
        
        JRadioButton rdbtnCriticalCareMedicine = new JRadioButton("Critical Care Medicine");
		rdbtnCriticalCareMedicine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnCriticalCareMedicine.setBounds(174, 290, 154, 21);
		panel.add(rdbtnCriticalCareMedicine);
		
		JRadioButton rdbtnGeriatricMedicine = new JRadioButton("Geriatric Medicine");
		rdbtnGeriatricMedicine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnGeriatricMedicine.setBounds(340, 290, 132, 21);
		panel.add(rdbtnGeriatricMedicine);
		
		JRadioButton rdbtnAllergyAndImmunology = new JRadioButton("Immunology");
		rdbtnAllergyAndImmunology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnAllergyAndImmunology.setBounds(491, 290, 101, 21);
		panel.add(rdbtnAllergyAndImmunology);
		 
	    ButtonGroup specialistGroup = new ButtonGroup();
	    specialistGroup.add(rdbtnNewRadioButton);
	    specialistGroup.add(rdbtnEndocrinology);
	    specialistGroup.add(rdbtnGastroenterology);
	    specialistGroup.add(rdbtnHematology);
	    specialistGroup.add(rdbtnInfectiousDiseases);
	    specialistGroup.add(rdbtnNephrology);
	    specialistGroup.add(rdbtnOncology);
	    specialistGroup.add(rdbtnPulmonology);
	    specialistGroup.add(rdbtnRheumatology);
	    specialistGroup.add(rdbtnCriticalCareMedicine);
	    specialistGroup.add(rdbtnGeriatricMedicine);
	    specialistGroup.add(rdbtnAllergyAndImmunology);
	    
	    JLabel Physical_Examination = new JLabel("Physical Examination");
		Physical_Examination.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Physical_Examination.setBounds(35, 451, 143, 19);
		panel.add(Physical_Examination);
		
		JTextArea txtPhysicalExm = new JTextArea();
		txtPhysicalExm.setBounds(37, 480, 557, 58);
		panel.add(txtPhysicalExm);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				actionPerformed(evt);
			}
		});
		btnNext.setBounds(509, 572, 85, 21);
		panel.add(btnNext);
		
		cbPatientID = new JComboBox();
		cbPatientID.setBounds(33, 175, 134, 32);
		cbPatientID.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            Patient selectedPatient = (Patient) e.getItem();
		            String patientName = selectedPatient.getName();
		            txtPatient.setText(patientName);
		        }
		    }
		});
		panel.add(cbPatientID);
	}
	
	public void actionPerformed(ActionEvent evt) {
		CreateClinicalSummary2.createAndShowGUI(username, patientName, CSID);
	}
}
