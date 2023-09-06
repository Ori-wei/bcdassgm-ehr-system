package Clinician;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.security.PrivateKey;
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

import org.mindrot.jbcrypt.BCrypt;

import BlockchainObject.ClinicalSummary;
import DatabaseObject.Patient;
import DigitalSignature.UserSignature;
import SymmetricEncryption.Decrypter;
import SymmetricEncryption.Encrypter;
import asymmetric.AccessKey;
import ehrBlockchain.Block;
import ehrBlockchain.Blockchain;
import ehrBlockchain.RecordCollection;
import ehrBlockchain.RecordHandler;

public class CreateDischarge {

	public String username;
    private JFrame frame;
    private JTextField datetimeDischargeField;
    private JTextField txtPatient;
    public String CSID;
    public String patientID;
    public String patientName;
    private Timestamp dischargetimestamp;
    private String statusAtDischarge;    
    private JTextField txtPatientIC;
    private JTextField txtSignature;
    private String finalCSRecord = null;
    private JButton btnNext;
    private JButton btnBack;
    private JButton btnSign;
    private String hashRecord = null;
    private byte[] signature = null;

    public static void createAndShowGUI(String CSID, String patientID, String username) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateDischarge window = new CreateDischarge(CSID, patientID, username);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CreateDischarge(String CSID, String patientID, String username) {
        initialize();
        this.CSID = CSID;
        this.patientID = patientID;
        this.username = username;
        
        // decrypt IC, populate IC
        Decrypter decrypt = new Decrypter();
		String decryptedIC = decrypt.decrypter(patientID);
		txtPatientIC.setText(decryptedIC);
        
        // Populate Name
        try {
	    	Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\ASUS\\MyDB;","root","toor");
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * from BCD.patient");
            while (rs.next()) {
            	Patient patient = new Patient(decryptedIC, rs.getString("name"));
            	txtPatient.setText(patient.getName());
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        
        // Populate DateTime of Visit
        dischargetimestamp = new Timestamp(System.currentTimeMillis());
        datetimeDischargeField.setText(dischargetimestamp.toString());
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

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnBack.setBounds(33, 10, 85, 21);
        panel.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Menu.createAndShowGUI(username);
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
        
        JLabel lblSignature = new JLabel("Signature:");
        lblSignature.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSignature.setBounds(33, 379, 200, 25);
        panel.add(lblSignature);
        
        txtSignature = new JTextField();
        txtSignature.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtSignature.setEnabled(false);
        txtSignature.setBounds(257, 384, 190, 25);
        panel.add(txtSignature);
        
        btnSign = new JButton("Sign");
        btnSign.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnSign.setBounds(257, 443, 85, 21);
        btnSign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signAction();
            }
        });
        panel.add(btnSign);
        
        btnNext = new JButton("Next");
        btnNext.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNext.setBounds(505, 519, 85, 21);
        btnNext.setEnabled(false);
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu.createAndShowGUI(username);
            }
        });
        panel.add(btnNext);
    }
    
    public void signAction() {
    	String id = null;
    	String IC = null;
    	String areaOfSpecialist = null;
    	String dateOfVisit = null;
        String doctorName = null;
        String history = null;
        String physicalExamination = null;
        String diagnosis = null;
        String summary = null;
        String treatment = null;
        String followUpProgress = null;
        String admissionID = null;
        String dateTimeOfAdmission = null;
        final String masterFolder = "masterEHR";
        final String fileName = masterFolder + "/chain.bin";
        
    	try {    
        	// Step 1: Retrieve record from blockchain
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
    	            id = components[0];
    	            IC = components[1];
    	            areaOfSpecialist = components[2];
    	            dateOfVisit = components[3];
    	            doctorName = components[4];
    	            history = components[5];
    	            physicalExamination = components[6];
    	            diagnosis = components[7];
    	            summary = components[8];
    	            treatment = components[9];
    	            followUpProgress = components[10];
    	            admissionID = components[11];
    	            dateTimeOfAdmission = components[12];
    	            
    	            System.out.println(IC);
    	            System.out.println(id);
    	            
    	            System.out.println("Comparing patientIC: " + patientID + " with IC: " + IC);
                    System.out.println("Comparing CSID: " + CSID + " with id: " + id);
    	            if (patientID.equals(IC) && CSID.equals(id)) {
    	            	System.out.println("IC and ID matched! proceeding further..");
    	            	
    	            	// Step 2: Update Database
    	        	    Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\ASUS\\MyDB;","root","toor");
    	                Statement stmt = conn.createStatement();
    	                String query = "UPDATE BCD.Admission SET DATETIMEOFDISCHARGE = " + dischargetimestamp + ","
    	                		+ " STATUSATDISCHARGE = " + statusAtDischarge + " WHERE ADMISSIONID = " + admissionID;
    	                int rs = stmt.executeUpdate(query);

    	            	// Step 3: Replace old blockchain record with a new one
    	                String updatedCSRecord = id + "|" + IC + "|" + areaOfSpecialist + "|" + dateOfVisit + "|" + doctorName + "|"
    	                		+ history + "|" + physicalExamination + "|" + diagnosis + "|" + summary + "|" + treatment + "|"
    	                		+ followUpProgress + "|" + admissionID + "|" + dateTimeOfAdmission + "|" + dischargetimestamp + "|"
    	                		+ statusAtDischarge;
    	                
    	                // Step 4: Hash the string
    	                String salt = BCrypt.gensalt();
    	    	        hashRecord = BCrypt.hashpw(updatedCSRecord, salt);
    	    	        
    	    	        // Step 5: Encrypt the hash with private key
    	    	        PrivateKey privateKey = null;
    	    	        try {
    	    	            privateKey = AccessKey.getPrivateKey("KeyManagement/" + username + "/AsymmetricKeyPair/PrivateKey");
    	    	        } catch (Exception e1) {
    	    	            e1.printStackTrace();
    	    	        }
    	    	        if (privateKey != null) {
    	    	        	// step 4: set digital signature
    	    	        	UserSignature userSignature = new UserSignature();
    	    	        	signature = userSignature.getSignature(hashRecord, privateKey);
    	    	        	txtSignature.setText(signature.toString());
    	    	        }
    	        	    
    	    	        // Step 6: generate timestamp
    	    	        Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
    	    	        
    	    	        // Step 7: add timestamp and signature to record
    	    			finalCSRecord = updatedCSRecord + "|" + timestampNow + "|" + signature;
    	        	    
    	            	// Step 8: Insert new record into blockchain
    	        	    final String masterFolder1 = "masterEHR";
    	        		final String fileName1 = masterFolder + "/chain.bin";
    	        		
    	        		// add Record to list
    	        		RecordCollection accumulatedRecords = RecordHandler.deserializeRecords();
    	        		accumulatedRecords.add(finalCSRecord);
    	        		
    	        		// if 4records are accumulated, access blockchain
    	        		if (accumulatedRecords.getEhrList().size() == 4) {
    	        	        Blockchain EHRchain1 = Blockchain.get_instance(fileName1);

    	        	        if (!new File(masterFolder1).exists()) {
    	        	            System.err.println("> creating Blockchain binary !");
    	        	            new File(masterFolder1).mkdir();
    	        	            
    	        	            // create genesis block
    	        	            EHRchain1.genesis();
    	        	            EHRchain1.distribute();
    	        	        }
    	        	        
    	        	        // create block
    	        	        String previousHash = EHRchain1.get().getLast().getHeader().getCurrentHash();
    	        	        Block b1 = new Block(previousHash);
    	        	        b1.setEhrContainer(accumulatedRecords); // Assuming setEhrContainer accepts RecordCollection
    	        	        System.out.println(b1);

    	        	        EHRchain1.nextBlock(b1);
    	        	        EHRchain1.distribute();

    	        	        // Clear the accumulated records as they have been added to a block
    	        	        accumulatedRecords = new RecordCollection();
    	        	    }

    	        	    // Serialize the current state of accumulated records
    	        	    RecordHandler.serializeRecords(accumulatedRecords);
    	        	    
    	        	    // Step 9: Control button
    	        	    btnNext.setEnabled(true);
    	        	    btnSign.setEnabled(false);
    	        	    btnBack.setEnabled(false);
    	            }
    	        }
    	    }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}  
    }
}
