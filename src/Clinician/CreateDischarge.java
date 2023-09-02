package Clinician;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DatabaseObject.Patient;
import SymmetricEncryption.Decrypter;

public class CreateDischarge {

    private JFrame frame;
    private JTextField datetimeDischargeField;
    private JTextField txtPatient;
    private JComboBox<Object> patientDropdownDischarge;
    public String patientID;
    public String patientName;
    private Timestamp pytimestamp;
    private String statusAtDischarge;
    public String admissionID;

    public static void createAndShowGUI(String admissionID) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateDischarge window = new CreateDischarge(admissionID);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CreateDischarge(String admissionID) {
        initialize();
        
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
            	patientDropdownDischarge.addItem(patient);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
     // Populate DateTime of Visit
        pytimestamp = new Timestamp(System.currentTimeMillis());
        datetimeDischargeField.setText(pytimestamp.toString());
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 685, 680);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 1, 655, 640);
        frame.getContentPane().add(panel);

        JLabel lblNewLabel = new JLabel("EHR System");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblNewLabel.setBounds(236, 37, 170, 71);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Create Discharge");
        lblNewLabel_1.setBounds(230, 99, 210, 46);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel.add(lblNewLabel_1);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnBack.setBounds(33, 10, 85, 21);
        panel.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //CreateClinicalSummary3.createAndShowGUI(username, patientName, CSID);
            }
        });
        
        JLabel lblPatientIc = new JLabel("Patient IC:");
        lblPatientIc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPatientIc.setBounds(34, 169, 100, 25);
        panel.add(lblPatientIc);
        
        patientDropdownDischarge = new JComboBox<>();
        patientDropdownDischarge.setFont(new Font("Tahoma", Font.PLAIN, 15));
        patientDropdownDischarge.setBounds(257, 169, 190, 25);
        patientDropdownDischarge.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            Patient selectedPatient = (Patient) e.getItem();
		            String patientName = selectedPatient.getName();
		            txtPatient.setText(patientName);
		        }
		    }
		});
        panel.add(patientDropdownDischarge);

        JLabel patientLabelDischarge = new JLabel("Patient Name:");
        patientLabelDischarge.setFont(new Font("Tahoma", Font.PLAIN, 15));
        patientLabelDischarge.setBounds(34, 224, 100, 25);
        panel.add(patientLabelDischarge);
        
        txtPatient = new JTextField();
        txtPatient.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtPatient.setEnabled(false);
        txtPatient.setBounds(257, 224, 190, 25);
        panel.add(txtPatient);

        JLabel datetimeDischargeLabel = new JLabel("Datetime of Discharge:");
        datetimeDischargeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        datetimeDischargeLabel.setBounds(34, 274, 200, 25);
        panel.add(datetimeDischargeLabel);

        datetimeDischargeField = new JTextField();
        datetimeDischargeField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        datetimeDischargeField.setBounds(257, 274, 190, 25);
        datetimeDischargeField.setEnabled(false);
        panel.add(datetimeDischargeField);

        JLabel statusLabel = new JLabel("Status at Discharge:");
        statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        statusLabel.setBounds(34, 324, 200, 25);
        panel.add(statusLabel);

        String[] statuses = { "Alive", "Deceased" };
        JComboBox<String> statusDropdown = new JComboBox<>(statuses);
        statusDropdown.setFont(new Font("Tahoma", Font.PLAIN, 15));
        statusDropdown.setBounds(257, 324, 100, 25);
        statusDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusAtDischarge = statusDropdown.getSelectedItem().toString();
            }
        });
        panel.add(statusDropdown);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnUpdate.setBounds(257, 379, 85, 21);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateAction(admissionID);
            }
        });
        panel.add(btnUpdate);
    }
    
    public void updateAction(String admissionID) {
    	this.admissionID = admissionID;
    	String dtAdmission = null;
    	String CSID = null;
    	
    	// Step 1: Update Admission DB
    	try {
    		Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\ASUS\\MyDB;","root","toor");
            Statement stmt = conn.createStatement();
            String query = "UPDATE BCD.Admission SET DATETIMEOFDISCHARGE = " + pytimestamp + ","
            		+ " STATUSATDISCHARGE = " + statusAtDischarge + " WHERE ADMISSIONID = " + admissionID;
            int rs = stmt.executeUpdate(query);
            
            if (rs > 0) {
            	// Step 2: Select the record, retrieve data
            	String query2 = "SELECT * FROM BCD.Admission WHERE ADMISSIONID = " + admissionID;
            	ResultSet rs2 = stmt.executeQuery(query2);
            	while (rs2.next()) {
                    dtAdmission = rs2.getString("datetimeOfAdmission");
                    CSID = rs2.getString("CSID");
                }
            }
            
        	// Step 3: Retrieve record from blockchain
        	
        	// Step 4: Concat both string
        	
        	// Step 5: Insert new record into blockchain
    	}catch(Exception e) {
    		e.printStackTrace();
    	}  
    }
}
