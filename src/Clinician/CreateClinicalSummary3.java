package Clinician;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.PrivateKey;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.mindrot.jbcrypt.BCrypt;
import DigitalSignature.UserSignature;
import SymmetricEncryption.Decrypter;
import asymmetric.AccessKey;
import ehrBlockchain.Block;
import ehrBlockchain.Blockchain;
import ehrBlockchain.RecordCollection;
import ehrBlockchain.RecordHandler;

import javax.swing.JRadioButton;

public class CreateClinicalSummary3 {

	private JFrame frame;
	public String username;
	public List<String> record;
	private JTextField txtPatientIC;
	private JTextField txtPatient;
	private JTextField txtReportID;
	private JTextField txtName;
	private JTextField txtDesignation;
	private JTextField txtDepartment;
	private JTextField txtDatetime;
	private JTextField txtSignature;
	public Timestamp admissionTimestamp;
	private String admissionID = "";
	private JButton btnNext;
	private String CSRecord = null;
	private String finalCSRecord = null;
	private byte[] signature = null;
	private String hashRecord = null;
	private String hospitalID = null;
	private String clinicianID = null;
	private String signatureString = null;
	private JButton btnBack;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JButton btnSign;

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String username, List<String> record) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateClinicalSummary3 window = new CreateClinicalSummary3(username, record);
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
	public CreateClinicalSummary3(String username, List<String> record) {
		initialize();
		this.username = username;
		this.record = record;
		
		Decrypter decrypt = new Decrypter();
		String decryptedIC = decrypt.decrypter(record.get(0));
		
		txtPatientIC.setText(decryptedIC);
		txtPatient.setText(record.get(1));
		txtReportID.setText(record.get(2));
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
        panel.setBounds(10, 0, 655, 640);
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
		
		txtPatientIC = new JTextField();
		txtPatientIC.setBounds(35, 175, 143, 32);
		txtPatientIC.setEditable(false);
		panel.add(txtPatientIC);
		txtPatientIC.setColumns(10);
        
		JLabel lblNewLabel_2 = new JLabel("Patient Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(235, 149, 101, 13);
		panel.add(lblNewLabel_2);
		
		txtPatient = new JTextField();
		txtPatient.setBounds(235, 175, 143, 32);
		txtPatient.setEditable(false);
		panel.add(txtPatient);
		txtPatient.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Report ID:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(451, 145, 101, 21);
		panel.add(lblNewLabel_3);
		
		txtReportID = new JTextField();
		txtReportID.setBounds(451, 175, 143, 32);
		txtReportID.setEditable(false);
		panel.add(txtReportID);
		txtReportID.setColumns(10);
		
		btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CreateClinicalSummary2.createAndShowGUI(username, record);
        		frame.dispose();
        	}
        });
        btnBack.setBounds(35, 26, 85, 21);
		panel.add(btnBack);
		
		JLabel lblAdmissionRequired = new JLabel("Admission Required?");
		lblAdmissionRequired.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdmissionRequired.setBounds(33, 236, 145, 21);
		panel.add(lblAdmissionRequired);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnYes.setBounds(33, 263, 59, 21);
		panel.add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNo.setBounds(168, 263, 47, 21);
		panel.add(rdbtnNo);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnYes);
		btnGroup.add(rdbtnNo);
		
		ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// if yes is selected
            	if(rdbtnYes.isSelected()) {
            	int rowCount = 0;	
            	
        		try {
            		Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
                    Statement stmt = conn.createStatement();
            		// Generate AdmissionID
        	        ResultSet rs = stmt.executeQuery("SELECT * from BCD.Admission");
        	        rowCount = 0;
        	        while (rs.next()) {
        	            rowCount++;
        	        }
        	        if(rowCount>=0)
        	        {
        	     	   rowCount+=1;
        	     	   System.out.println("Proceed");
        	     	   String prefix = "A";
        	            // Find the number of digits in row
        	            int numberOfDigits = String.valueOf(rowCount).length();                        
        	            // Determine the number of zeros to prepend
        	            int numberOfZeros = 4 - numberOfDigits;  // Assuming 4-digit CSID
        	            // Create zeros string
        	            StringBuilder zeros = new StringBuilder();
        	            for (int i = 0; i < numberOfZeros; i++) {
        	                zeros.append("0");
        	            }
        	            admissionID = (prefix + zeros + rowCount);    
        	            System.out.println(admissionID);
        	            record.add(admissionID);
        	        }
        	        else
        	        {
        	     	   System.out.println("Error");  
        	        }
    	        }
    	        catch(SQLException e1) {
    	        	e1.printStackTrace();
    	        }
        		// Populate DateTime of Admission
                admissionTimestamp = new Timestamp(System.currentTimeMillis());
        		}
            }
        };
        
        rdbtnYes.addActionListener(listener);
        rdbtnNo.addActionListener(listener);
		
		// Prepared by
		JLabel PreparedBy = new JLabel("Prepared By:");
		PreparedBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PreparedBy.setBounds(33, 313, 143, 19);
        panel.add(PreparedBy);
        
        // Name
        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblName.setBounds(33, 350, 143, 19);
        panel.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(190, 346, 143, 32);
        txtName.setEditable(false);
        panel.add(txtName);

        // Designation
        JLabel lblDesignation = new JLabel("Designation:");
        lblDesignation.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDesignation.setBounds(33, 394, 143, 19);
        panel.add(lblDesignation);

        txtDesignation = new JTextField();
        txtDesignation.setBounds(190, 390, 143, 32);
        txtDesignation.setEditable(false);
        panel.add(txtDesignation);

        // Department
        JLabel lblDepartment = new JLabel("Department:");
        lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDepartment.setBounds(35, 438, 143, 19);
        panel.add(lblDepartment);

        txtDepartment = new JTextField();
        txtDepartment.setBounds(190, 432, 143, 32);
        txtDepartment.setEditable(false);
        panel.add(txtDepartment);

        // Datetime of Preparation
        JLabel lblDatetime = new JLabel("Preparation Datetime:");
        lblDatetime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDatetime.setBounds(35, 482, 180, 19);
        panel.add(lblDatetime);

        txtDatetime = new JTextField();
        txtDatetime.setBounds(190, 474, 143, 32);
        txtDatetime.setEditable(false);
        panel.add(txtDatetime);
        
        // Signature
        JLabel lblSignature = new JLabel("Signature:");
        lblSignature.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSignature.setBounds(35, 526, 180, 19);
        panel.add(lblSignature);

        txtSignature = new JTextField();
        txtSignature.setBounds(190, 520, 143, 32);
        txtSignature.setEditable(false);
        panel.add(txtSignature);
        
        btnSign = new JButton("Sign");
        btnSign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent sbevt) {
				// TODO Auto-generated method stub
				actionSignButtonPerformed();
			}
		});
        btnSign.setBounds(190, 569, 85, 21);
		panel.add(btnSign);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionNextPerformed();
				frame.dispose();
			}
		});
		btnNext.setBounds(509, 572, 85, 21);
		btnNext.setEnabled(false);
		panel.add(btnNext);
	}
	
	public void actionSignButtonPerformed() {
		
		// initialize variables
    	String name = null;
    	String designation = null;
    	String department = null;
    	String dtSign = null;
    	
    	// Database part
    	// Step 1: assign variables with data
    	try {
    		Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
            Statement stmt = conn.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * from BCD.clinician where username = '" + username + "'");
            while(rs1.next())
            {
            	clinicianID = rs1.getString("clinicianID");
            	name = rs1.getString("name");
                designation = rs1.getString("designation");
                department = rs1.getString("department");
                hospitalID = rs1.getString("hospitalID");
            } 
    	
	    	dtSign = (new Timestamp(System.currentTimeMillis())).toString();
	
	    	// populate data to text field
	    	txtName.setText(name);
	    	txtDesignation.setText(designation);
	    	txtDepartment.setText(department);
	    	txtDatetime.setText(dtSign);
	    	
	    	// Step 2: Complete the 1st version CSRecord String
	    	if (admissionID.isEmpty()) {
	    		CSRecord = record.get(2) + "|" + record.get(0) + "|" + record.get(3) + "|" + record.get(4) +
		        		"|" + record.get(5) + "|" + record.get(6) + "|" + record.get(7) + "|" + record.get(8) +
		        		"|" + record.get(9) + "|" + record.get(10) + "|" + record.get(11) + "|" + "NA" +
		        		"|" + "NA" + "|" + "NA" + "|" + "NA";
	    	}else {
	    		CSRecord = record.get(2) + "|" + record.get(0) + "|" + record.get(3) + "|" + record.get(4) +
		        		"|" + record.get(5) + "|" + record.get(6) + "|" + record.get(7) + "|" + record.get(8) +
		        		"|" + record.get(9) + "|" + record.get(10) + "|" + record.get(11) + "|" + record.get(12) +
		        		"|" + admissionTimestamp + "|" + "NA" + "|" + "NA";
	    	}
	        
	        System.out.println(CSRecord);
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
	        
        // step 3: generate private key
        PrivateKey privateKey = null;
        try {
            privateKey = AccessKey.getPrivateKey("KeyManagement/" + username + "/AsymmetricKeyPair/PrivateKey");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (privateKey != null) {
        	// step 4: set digital signature
        	UserSignature userSignature = new UserSignature();
        	signature = userSignature.getSignature(CSRecord, privateKey);
        	signatureString = Base64.getEncoder().encodeToString(signature);
        	txtSignature.setText(signatureString);
        }
        
		// step 5: control buttons
		btnNext.setEnabled(true);
		btnBack.setEnabled(false);
		rdbtnYes.setEnabled(false);
		rdbtnNo.setEnabled(false);
		btnSign.setEnabled(false);
		
		// step 6: add timestamp and signature to CSRecord
		Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
		finalCSRecord = CSRecord + "|" + timestampNow + "|" + signatureString;
		System.out.println(finalCSRecord);
		
		// step 7: add to blockchain
        final String masterFolder = "masterEHR";
		final String fileName = masterFolder + "/chain.bin";
		
		// add Record to list
		RecordCollection accumulatedRecords = RecordHandler.deserializeRecords();
		accumulatedRecords.add(finalCSRecord);
		System.out.println(accumulatedRecords);
		
		if (accumulatedRecords.getEhrList().size() == 4) {
			
	        Blockchain EHRchain = Blockchain.get_instance(fileName);

	        if (!new File(masterFolder).exists()) {
	            System.err.println("> creating Blockchain binary !");
	            new File(masterFolder).mkdir();
	            
	            // create genesis block
	            EHRchain.genesis();
	            EHRchain.distribute();
	        }
	        
	        // create block
	        String previousHash = EHRchain.get().getLast().getHeader().getCurrentHash();
	        Block b1 = new Block(previousHash);
	        b1.setEhrContainer(accumulatedRecords); // Assuming setEhrContainer accepts RecordCollection
	        System.out.println(b1);

	        EHRchain.nextBlock(b1);
	        EHRchain.distribute();

	        // Clear the accumulated records as they have been added to a block
	        accumulatedRecords = new RecordCollection();
	    }

	    // Serialize the current state of accumulated records
	    RecordHandler.serializeRecords(accumulatedRecords);
    }
	
	public void actionNextPerformed() {
		int rowCount;
		String reportID = null;
		
		// save record into db: ClinicalSummary, ReportPreparedBy, Admission
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
            Statement stmt = conn.createStatement();
            
	        // ClinicalSummary
	        String query2 = "INSERT INTO BCD.ClinicalSummary (ClinicalSummaryID, Hash, HospitalID, IC_No) "
	        		+ "VALUES ('"+ record.get(2) + "','" + hashRecord + "','" + hospitalID + "','" + record.get(0) + "')";
	        int rs2 = stmt.executeUpdate(query2);
	        
	    	// ReportPreparedBy
	        ResultSet rs3 = stmt.executeQuery("SELECT * from BCD.ReportPreparedBy");
	        rowCount = 0;
	        while (rs3.next()) {
	            rowCount++;
	        }
	        if(rowCount>=0)
	        {
	     	   rowCount+=1;
	     	   System.out.println("Proceed");
	     	   String prefix = "R";
	            // Find the number of digits in row
	            int numberOfDigits = String.valueOf(rowCount).length();                        
	            // Determine the number of zeros to prepend
	            int numberOfZeros = 4 - numberOfDigits;  // Assuming 4-digit CSID
	            // Create zeros string
	            StringBuilder zeros = new StringBuilder();
	            for (int i = 0; i < numberOfZeros; i++) {
	                zeros.append("0");
	            }
	            reportID = (prefix + zeros + rowCount);    
	            System.out.println(reportID);
	        }
	        else
	        {
	     	   System.out.println("Error");  
	        }
	        
	        String query3 = "INSERT INTO BCD.ReportPreparedBy (ReportID, ClinicianID, ClinicalSummaryID) "
	        		+ "VALUES ('" + reportID + "','" + clinicianID + "','" + record.get(2) + "')";
	        int rs4 = stmt.executeUpdate(query3);
	
	        // Admission
	        if(!(admissionID.isEmpty())) {
		        String query4 = "INSERT INTO BCD.Admission (AdmissionID, PatientID, DatetimeOfAdmission, CSID) "
		        		+ "VALUES ('"+ admissionID + "','" + record.get(0) + "','" + admissionTimestamp + "','" + record.get(2) + "')";
		        int rs5 = stmt.executeUpdate(query4);
	        }
		} catch (SQLException e2) {
	        e2.printStackTrace();
	    }
		
		// Back to Menu
		Menu.createAndShowGUI(username);
	}
}
