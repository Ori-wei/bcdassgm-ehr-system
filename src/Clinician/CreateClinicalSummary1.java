package Clinician;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
import SymmetricEncryption.Encrypter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class CreateClinicalSummary1 {
	
	public String username;
	public String patientID;
	public String patientName = null;
	public String CSID = null;
	private JFrame frame;
	private JTextField txtPatient;
	private JTextField txtReportID;
	private JComboBox<Patient> cbPatientID;
	private JTextField txtDTVisit;
	private JComboBox<String> specialistDropdown;
	private JTextField txtDoc;
	private JTextArea txtHistory;
	private JTextArea txtPhysicalExm;
	public List<String> record;
	

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
	
	public static void createAndShowGUI(String username, List<String> record) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateClinicalSummary1 window = new CreateClinicalSummary1(username, record);
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
		
		// Generate CSID
        CSID = null;
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
            	patientID = patient.getPatientID();
            	patientName = patient.getName();   
            	cbPatientID.addItem(patient);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Populate DateTime of Visit
        Timestamp pytimestamp = new Timestamp(System.currentTimeMillis());
		txtDTVisit.setText(pytimestamp.toString());
        
	}
	
	public CreateClinicalSummary1(String username, List<String> record) {
		initialize();
		this.username = username;
		this.record = record;
		
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
            	patientID = patient.getPatientID();
            	patientName = patient.getName();   
            	cbPatientID.addItem(patient);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		if(!(record.isEmpty())) {
			Decrypter decrypt = new Decrypter();
			String decryptedIC = decrypt.decrypter(record.get(0));
			
			cbPatientID.setSelectedItem(decryptedIC);
			txtPatient.setText(record.get(1));
			txtReportID.setText(record.get(2));
			specialistDropdown.setSelectedItem(record.get(3));
			txtDTVisit.setText(record.get(4));
			txtDoc.setText(record.get(5));
			txtHistory.setText(record.get(6));
			txtPhysicalExm.setText(record.get(7));
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
        
		JLabel lblNewLabel_2 = new JLabel("Patient Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(235, 149, 101, 13);
		panel.add(lblNewLabel_2);
		
		txtPatient = new JTextField();
		txtPatient.setBounds(235, 175, 143, 32);
		txtPatient.setEnabled(false);
		panel.add(txtPatient);
		txtPatient.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("CSID:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(451, 149, 101, 13);
		panel.add(lblNewLabel_3);
		
		txtReportID = new JTextField();
		txtReportID.setBounds(451, 175, 143, 32);
		txtReportID.setEnabled(false);
		panel.add(txtReportID);
		txtReportID.setColumns(10);
        
		JLabel lblSpecialist = new JLabel("Area of Specialist");
		lblSpecialist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSpecialist.setBounds(29, 240, 132, 19);
		panel.add(lblSpecialist);
		
		specialistDropdown = new JComboBox<>(new String[] 
				{"Cardiology", "Endocrinology", "Gastroenterology", "Hematology",
						"Infectious Diseases", "Nephrology", "Oncology", "Pulmonology",
						"Rheumatology", "Critical Care Medicine", "Geriatric Medicine", "Immunology"});
		specialistDropdown.setBounds(27, 269, 150, 32);
        panel.add(specialistDropdown);
        
        JLabel DatetimeVisit = new JLabel("Datetime of Visit");
        DatetimeVisit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        DatetimeVisit.setBounds(235, 240, 143, 19);
        panel.add(DatetimeVisit);
        
        txtDTVisit = new JTextField();
        txtDTVisit.setBounds(237, 270, 143, 32);
        txtDTVisit.setEnabled(false);
		panel.add(txtDTVisit);
		txtDTVisit.setColumns(10);
        
		JLabel PrincipalDoctor = new JLabel("Principal Doctor");
        PrincipalDoctor.setFont(new Font("Tahoma", Font.PLAIN, 15));
        PrincipalDoctor.setBounds(451, 240, 143, 19);
        panel.add(PrincipalDoctor);
        
        txtDoc = new JTextField();
        txtDoc.setBounds(451, 270, 143, 32);
		panel.add(txtDoc);
		txtDoc.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("History");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(35, 338, 101, 19);
		panel.add(lblNewLabel_2_1);
		
		txtHistory = new JTextArea();
		txtHistory.setBounds(37, 367, 557, 58);
		panel.add(txtHistory);
	    
	    JLabel Physical_Examination = new JLabel("Physical Examination");
		Physical_Examination.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Physical_Examination.setBounds(35, 451, 143, 19);
		panel.add(Physical_Examination);
		
		txtPhysicalExm = new JTextArea();
		txtPhysicalExm.setBounds(37, 480, 557, 58);
		panel.add(txtPhysicalExm);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				nextActionPerformed();
			}
		});
		btnNext.setBounds(509, 572, 85, 21);
		panel.add(btnNext);
	}
	
	public void nextActionPerformed() {
		Encrypter encypt = new Encrypter();
		String encryptedIC = encypt.encrypter(cbPatientID.getSelectedItem().toString());		
		
		record = new ArrayList<String>();
		record.add(encryptedIC);
		record.add(txtPatient.getText());
		record.add(CSID);
		record.add(specialistDropdown.getSelectedItem().toString());
		record.add(txtDTVisit.getText());
		record.add(txtDoc.getText());
		record.add(txtHistory.getText());
		record.add(txtPhysicalExm.getText());
		CreateClinicalSummary2.createAndShowGUI(username, record);
		frame.dispose();
	}
}
