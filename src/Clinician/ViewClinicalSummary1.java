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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.derby.client.am.DateTime;

import BlockchainObject.ClinicalSummary;
import DatabaseObject.Clinician;
import DatabaseObject.Patient;
import SymmetricEncryption.Decrypter;
import SymmetricEncryption.Encrypter;
import ehrBlockchain.Block;
import ehrBlockchain.Blockchain;
import ehrBlockchain.RecordCollection;

public class ViewClinicalSummary1 {

	public String username;
	public String patientID;
	public String patientName = null;
	public String CSID = null;
	String decrpytedPatientIC;
	String sex;
	String areaOfSpecialist = null;
    String dateOfVisit = null;
	String doctorID = null;
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
	String selectedBlockchainRecord = null; 

	
	private JFrame frame;
	private JTextField txtPatient;
	private JTextField txtCSID;
	private JTextField txtPatientID;
	//private JComboBox<Patient> cbPatientID;
	private JTextField txtDTVisit;
	private JTextField txtSpecialist;
	//private JComboBox<String> specialistDropdown;
	private JTextField txtDoc;
	private JTextArea txtHistory;
	private JTextArea txtPhysicalExm;
	public List<String> record;

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String username, String CSID, String timestampPassed, String patientID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewClinicalSummary1 window = new ViewClinicalSummary1(username, CSID, timestampPassed, patientID);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public ViewClinicalSummary1(String username, String CSID, String timestampPassed, String patientID) throws ParseException {
		initialize();
		this.username=username;
		this.patientID=patientID;
		this.CSID = CSID;
		//from the PatientID (IC_No) gotten, retrieve name and sex from database
		List<Patient> patientList = new ArrayList<>();
		try {
	    	Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * from BCD.patient where IC_No = '" + patientID + "'");
            while (rs.next()) {
                Patient patient = new Patient(rs.getString("name"), rs.getString("sex"), "0");
                patientList.add(patient);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            for (Patient patient : patientList) {
            	patientName = patient.getName(); 
            	sex=patient.getSex();
            	txtPatient.setText(patientName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Decrypter decrypt = new Decrypter();
        decrpytedPatientIC = decrypt.decrypter(patientID);
        txtPatientID.setText(decrpytedPatientIC);
        
        final String masterFolder = "masterEHR";
        final String fileName = masterFolder + "/chain.bin";
        List<ClinicalSummary> clinicalSummaryList = new ArrayList<>();
		//1. from blockchain, retrieve transaction about the PatientID 
		//2. after retrieved, get the CSID and the Date 
		//3. set these 2 field into arraylist of object ClinicalSummary 
		// Read the blockchain
        Blockchain bc = new Blockchain(fileName);
	    LinkedList<Block> EHRchain = bc.readBlockchain(fileName);
	    
	    // Initialize DateTimeFormatter for timestamp comparison
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .appendFraction(ChronoField.MILLI_OF_SECOND, 1, 3, true) // 1 to 3 digits for milliseconds
                .toFormatter();
        LocalDateTime targetTimestamp = LocalDateTime.parse(timestampPassed, formatter);

	    // Loop through blocks and records
	    for (Block block : EHRchain) {
	        RecordCollection recordCollection = block.getEhrContainer(); // Assuming getEhrContainer returns a RecordCollection
	        for (String record : recordCollection) { // Assuming RecordCollection is an Iterable
	            // Deserialize or parse the record to check the ID
	            String[] components = record.split("\\|");
	            String id = components[0];
	            String IC = components[1];
	            areaOfSpecialist = components[2];
	            dateOfVisit = components[3];
	        	doctorID = components[4];
	        	history = components[5];
	        	physicalExamination = components[6];
	        	diagnosis = components[7];
	        	summary = components[8];
	        	treatment = components[9];
	        	followUpProgress = components[10];
	        	admissionID = components[11];
	        	dateTimeOfAdmission = components[12];
	        	dateTimeOfDischarge = components[13];
	        	statusAtDischarge= components[14];
	        	timestamp = components[15];
	        	signature = components[16];
	        	System.out.println(record);

	            // Parse date for comparison
	            //Date recordDate = sdf.parse(date);
	        	LocalDateTime recordTimestamp = LocalDateTime.parse(timestamp, formatter);

	            // Check if this record matches target CSID and date
	            if (id.equals(CSID) && recordTimestamp.equals(targetTimestamp)) {
	                // Create ClinicalSummary object and return
	            	ClinicalSummary clinicalSummary = new ClinicalSummary(id, areaOfSpecialist, dateOfVisit, doctorID, history, physicalExamination, 
	            			diagnosis, summary, treatment, followUpProgress, admissionID, dateTimeOfAdmission, dateTimeOfDischarge, statusAtDischarge, timestamp, signature);
	            	clinicalSummaryList.add(clinicalSummary);
	            	selectedBlockchainRecord=record;
	            }
	        }
	    }
	    try {
            for (ClinicalSummary clinicalSummary : clinicalSummaryList) {
            	CSID = clinicalSummary.getCSID();
            	areaOfSpecialist = clinicalSummary.getAreaOfSpecialist();
            	dateOfVisit = clinicalSummary.getdateOfVisit();
            	doctorID = clinicalSummary.getDoctorID();
	        	history = clinicalSummary.getHistory();
	        	physicalExamination = clinicalSummary.getPhysicalExamination();
	        	diagnosis = clinicalSummary.getDiagnosis();
	        	summary = clinicalSummary.getSummary();
	        	treatment = clinicalSummary.getTreatment();
	        	followUpProgress = clinicalSummary.getFollowUpProgress();
	        	admissionID = clinicalSummary.getAdmissionID();
	        	dateTimeOfAdmission = clinicalSummary.getDateTimeOfAdmission();
	        	dateTimeOfDischarge = clinicalSummary.getDateTimeOfDischarge();
	        	statusAtDischarge= clinicalSummary.getStatusAtDischarge();
	        	timestamp = clinicalSummary.getTimestamp();
	        	signature = clinicalSummary.getSignature();
            			
            }
        } catch (Exception ex2) {
            ex2.printStackTrace();
        } 
	    txtCSID.setText(CSID);
	    txtSpecialist.setText(areaOfSpecialist);
	    txtDTVisit.setText(dateOfVisit);
	    txtDoc.setText(doctorID);
	    txtHistory.setText(history);
	    txtPhysicalExm.setText(physicalExamination);
	    
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
		
		txtPatientID = new JTextField();
		txtPatientID.setBounds(33, 175, 134, 32);
		txtPatientID.setEditable(false);
		panel.add(txtPatientID);
		txtPatientID.setColumns(10);
        
		JLabel lblNewLabel_2 = new JLabel("Patient Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(235, 149, 101, 13);
		panel.add(lblNewLabel_2);
		
		txtPatient = new JTextField();
		txtPatient.setBounds(235, 175, 143, 32);
		txtPatient.setEditable(false);
		panel.add(txtPatient);
		txtPatient.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("CS ID:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(451, 149, 101, 13);
		panel.add(lblNewLabel_3);
		
		txtCSID = new JTextField();
		txtCSID.setBounds(451, 175, 143, 32);
		txtCSID.setEditable(false);
		panel.add(txtCSID);
		txtCSID.setColumns(10);
        
		JLabel lblSpecialist = new JLabel("Area of Specialist");
		lblSpecialist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSpecialist.setBounds(29, 240, 132, 19);
		panel.add(lblSpecialist);
		
		txtSpecialist = new JTextField();
		txtSpecialist.setBounds(27, 269, 150, 32);
		txtSpecialist.setEditable(false);
		panel.add(txtSpecialist);
        
        JLabel DatetimeVisit = new JLabel("Datetime of Visit");
        DatetimeVisit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        DatetimeVisit.setBounds(235, 240, 143, 19);
        panel.add(DatetimeVisit);
        
        txtDTVisit = new JTextField();
        txtDTVisit.setBounds(237, 270, 143, 32);
        txtDTVisit.setEditable(false);
		panel.add(txtDTVisit);
		txtDTVisit.setColumns(10);
        
		JLabel PrincipalDoctor = new JLabel("Principal Doctor");
        PrincipalDoctor.setFont(new Font("Tahoma", Font.PLAIN, 15));
        PrincipalDoctor.setBounds(451, 240, 143, 19);
        panel.add(PrincipalDoctor);
        
        txtDoc = new JTextField();
        txtDoc.setBounds(451, 270, 143, 32);
        txtDoc.setEditable(false);
		panel.add(txtDoc);
		txtDoc.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("History");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(35, 338, 101, 19);
		panel.add(lblNewLabel_2_1);
		
		txtHistory = new JTextArea();
		txtHistory.setBounds(37, 367, 557, 58);
		txtHistory.setEditable(false);
		panel.add(txtHistory);
	    
	    JLabel Physical_Examination = new JLabel("Physical Examination");
		Physical_Examination.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Physical_Examination.setBounds(35, 451, 143, 19);
		panel.add(Physical_Examination);
		
		txtPhysicalExm = new JTextArea();
		txtPhysicalExm.setBounds(37, 480, 557, 58);
		txtPhysicalExm.setEditable(false);
		panel.add(txtPhysicalExm);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				nextActionPerformed();
			}
		});
		btnNext.setBounds(509, 572, 85, 21);
		panel.add(btnNext);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayPatientEHR.createAndShowGUI(username, patientID);
        		frame.dispose();
			}
		});
		btnBack.setBounds(33, 37, 85, 21);
		panel.add(btnBack);
	}
	
	public void nextActionPerformed() {
		ViewClinicalSummary2.createAndShowGUI(username, patientID, CSID, patientName, diagnosis, summary, treatment, followUpProgress, admissionID, dateTimeOfAdmission, dateTimeOfDischarge, statusAtDischarge, timestamp, signature, doctorID, selectedBlockchainRecord);
		frame.dispose();
	}

}
