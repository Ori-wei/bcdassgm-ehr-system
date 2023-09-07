package Clinician;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DigitalSignature.UserSignature;
import Hashing.Hasher;
import SymmetricEncryption.Decrypter;
import asymmetric.AccessKey;

public class ViewClinicalSummary3 {

	String username = null;
	String patientIC = null;
	String decrpytedPatientIC = null;
	String patientName = null;
	String sex = null;
	String CSID = null;
    String areaOfSpecialist = null;
    String dateOfVisit = null;
	String history = null;
	String physicalExamination = null;
	String diagnosis = null;
	String summary = null;
	String treatment = null;
	String followUpProgress = null;
	String admissionID = null;
	String dateTimeOfAdmission = null;
	String dateTimeOfDischarge = null;
	String statusAtDischarge = null;
	String timestamp = null;
	String signature = null;
	String doctorID = null;
	
	private Signature sig;
	String clinicalSummaryRecord;
	byte[] clnSignature;
	PublicKey key;
	
	private JFrame frame;
	private JTextField txtPatientIC;
	private JTextField txtPatient;
	private JTextField txtCSID;
	private JButton btnBack;
	private JTextField txtName;
	private JTextField txtDepartment;
	private JTextField txtDesignation;
	private JTextField txtDatetime;
	private JTextField txtSignature;
	private JButton btnValidate;
	

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String username, String patientID, String CSID, String patientName, String diagnosis, String summary, String treatment, String followUpProgress, String admissionID, String dateTimeOfAdmission, String dateTimeOfDischarge, String statusAtDischarge, String timestamp, String signature, String doctorID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewClinicalSummary3 window = new ViewClinicalSummary3(username, patientID, CSID, patientName, diagnosis, summary, treatment, followUpProgress, admissionID, dateTimeOfAdmission, dateTimeOfDischarge, statusAtDischarge, timestamp, signature, doctorID);
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
	public ViewClinicalSummary3(String username, String patientID, String CSID, String patientName, String diagnosis, String summary, String treatment, String followUpProgress, String admissionID, String dateTimeOfAdmission, String dateTimeOfDischarge, String statusAtDischarge, String timestamp, String signature, String doctorID) {
		initialize();
		this.username=username;
		this.patientIC=patientID;
		this.CSID=CSID;
		this.patientName=patientName;
		this.diagnosis=diagnosis;
		this.summary=summary;
		this.treatment=treatment;
		this.followUpProgress=followUpProgress;
		this.admissionID=admissionID;
		this.dateTimeOfAdmission=dateTimeOfAdmission;
		this.dateTimeOfDischarge=dateTimeOfDischarge;
		this.statusAtDischarge=statusAtDischarge;
		this.timestamp=timestamp;
		this.signature=signature;
		this.doctorID=doctorID;
		String doctorName = null;
		String department = null;
		String designation = null;
    	
        Decrypter decrypt = new Decrypter();
        decrpytedPatientIC = decrypt.decrypter(patientIC);
        txtPatientIC.setText(decrpytedPatientIC);
        txtPatient.setText(patientName);
        txtCSID.setText(CSID);
        txtDatetime.setText(timestamp);
        txtSignature.setText(signature);
    	try {
    		Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
            Statement stmt = conn.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * from BCD.clinician where clinicianID = '" + doctorID + "'");
            while(rs1.next())
            {
            	doctorName = rs1.getString("name");
                designation = rs1.getString("designation");
                department = rs1.getString("department");
            }
    	}catch(SQLException e) {
    		e.printStackTrace();
        }	   
        txtName.setText(doctorName);
        txtDepartment.setText(department);
        txtDesignation.setText(designation);
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
		
		txtCSID = new JTextField();
		txtCSID.setBounds(451, 175, 143, 32);
		txtCSID.setEditable(false);
		panel.add(txtCSID);
		txtCSID.setColumns(10);
		
		btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ViewClinicalSummary2.createAndShowGUI(username, patientIC, CSID, patientName, diagnosis, summary, treatment, followUpProgress, admissionID, dateTimeOfAdmission, dateTimeOfDischarge, statusAtDischarge, timestamp, signature, doctorID);
        		frame.dispose();
        	}
        });
        btnBack.setBounds(35, 26, 85, 21);
		panel.add(btnBack);
				
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
        
        btnValidate = new JButton("Validate");
        btnValidate.setBounds(190, 570, 85, 21);
        btnValidate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionValidatePerformed();
			}
		});
        panel.add(btnValidate);
	}
	
	public void actionValidatePerformed() {
		
		// step 1: Generate public key
        PublicKey publicKey = null;
        try {
        	publicKey = AccessKey.getPublicKey("KeyManagement/" + doctorID + "/AsymmetricKeyPair/PublicKey");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (publicKey != null) {
        	// Step 2: Obtain clinical summary record
        	clinicalSummaryRecord = CSID + "|" + patientIC + "|" + areaOfSpecialist + "|" + dateOfVisit +
        			"|" + doctorID + "|" + history + "|" + physicalExamination + "|" + diagnosis + "|" +
        			"|" + summary + treatment + "|" + followUpProgress + "|" + admissionID + "|" +
        			"|" + dateTimeOfAdmission + "|"+ dateTimeOfDischarge + "|" + statusAtDischarge +
        			"|" + timestamp + "|" + signature;
        	
        	// Step 3: Validate Signature
    		boolean isValid = false;
    		try {
    			sig.initVerify(key);
    		}catch(InvalidKeyException e) {
    			e.printStackTrace();
    		}
    		try {
    			sig.update(clinicalSummaryRecord.getBytes());
    			isValid = sig.verify(signature.getBytes());
    		} catch (SignatureException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		// Step 4: Display result
    		if(isValid) {
    			JOptionPane.showMessageDialog(null, "The signature is valid.");
    		}else {
    			JOptionPane.showMessageDialog(null, "Warning: The signature is invalid\n"
    					+ "This record consists of unathorized changes, "
    					+ "please contact your IT staff to conduct a security check.", 
    					"Warning", JOptionPane.WARNING_MESSAGE);
    		}
        }
	}

}
