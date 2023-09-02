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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BlockchainObject.ClinicalSummary;
import DatabaseObject.Patient;
import SymmetricEncryption.Decrypter;
import SymmetricEncryption.Encrypter;
import ehrBlockchain.Block;
import ehrBlockchain.Blockchain;
import ehrBlockchain.RecordCollection;

public class CreateDischarge {

    private JFrame frame;
    private JTextField datetimeDischargeField;
    private JTextField txtPatient;
    public String CSID;
    public String patientID;
    public String patientName;
    private Timestamp pytimestamp;
    private String statusAtDischarge;
    public String admissionID;
    private JTextField txtPatientIC;

    public static void createAndShowGUI(String CSID, String patientID) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateDischarge window = new CreateDischarge(CSID, patientID);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CreateDischarge(String CSID, String patientID) {
        initialize();
        this.CSID = CSID;
        this.patientID = patientID;
        
        
        // decrypt IC, populate IC
        Decrypter decrypt = new Decrypter();
		String decryptedIC = decrypt.decrypter(patientID);
		txtPatientIC.setText(decryptedIC);
        
        // Populate Name
        try {
	    	Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\ASUS\\MyDB;","root","toor");
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * from BCD.patient where patientID = " + patientID);
            while (rs.next()) {
            	txtPatient.setText(rs.getString("name"));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        
        // Populate DateTime of Visit
        pytimestamp = new Timestamp(System.currentTimeMillis());
        datetimeDischargeField.setText(pytimestamp.toString());
        
        // Populate Admission iD
        try {
	    	Connection conn1 = DriverManager.getConnection("jdbc:derby:C:\\Users\\ASUS\\MyDB;","root","toor");
	        Statement stmt1 = conn1.createStatement();
	        ResultSet rs1 = stmt1.executeQuery("SELECT * from BCD.Admission where CSID = " + CSID);
            while (rs1.next()) {
            	admissionID = rs1.getString("admissionID");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
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
        
        txtPatientIC = new JTextField();
        txtPatientIC.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtPatientIC.setEnabled(false);
        txtPatientIC.setBounds(257, 174, 190, 25);
        panel.add(txtPatientIC);

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
    	String dtAdmission = null;
    	String recordInfo = null;
        final String masterFolder = "masterEHR";
        final String fileName = masterFolder + "/chain.bin";
        
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
                }
            }
            
        	// Step 3: Retrieve record from blockchain
            List<ClinicalSummary> clinicalSummaryList = new ArrayList<>();
       		// Read the blockchain
    		Blockchain bc = new Blockchain(fileName);
    	    LinkedList<Block> EHRchain = bc.readBlockchain(fileName); // Make sure to provide the correct file name
    	    // Create a map to keep track of latest ClinicalSummary by CSID
    	    Map<String, ClinicalSummary> latestClinicalSummaries = new HashMap<>();
    	    
    	    // Filter and add records to the list
    	    for (Block block : EHRchain) {
    	        RecordCollection recordCollection = block.getEhrContainer(); // Assuming getEhrContainer returns a RecordCollection
    	        for (String record : recordCollection) { // Assuming RecordCollection is an Iterable
    	            // You would typically deserialize or parse the record to check the ID.
    	        	String[] components = record.split("\\|");
    	            String id = components[0]; 
    	            System.out.println(id);
    	            String IC = components[1];
    	            String date = components[2]; 
    	            System.out.println("Comparing patientIC: " + patientID + " with IC: " + IC);
    	            if (patientID.equals(IC)) {
    	            	    if (CSID.equals(id)) {
    	                        System.out.println("Comparing CSID: " + CSID + " with id: " + id);
    		                    recordInfo=record;
    	                    
    		                }
    	            	}  	   
    	            }
    	        }
        	// Step 4: Concat both string
    	    
            
        	// Step 5: Insert new record into blockchain
    	}catch(Exception e) {
    		e.printStackTrace();
    	}  
    }
}
