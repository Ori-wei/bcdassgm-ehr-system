package Clinician;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import SymmetricEncryption.Decrypter;

public class ViewClinicalSummary2 {
	
	String username = null;
	String patientIC = null;
	String decrpytedPatientIC = null;
	String patientName = null;
	String sex = null;
	String CSID = null;
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

	
	private JFrame frame;;
	public List<String> record;
	private JTextField txtPatient;
	private JTextField txtCSID;
	private JTextField txtPatientIC;
	private JTextArea txtDiagnosis;
	private JTextArea txtSumIvsg;
	private JTextArea txtTreatment;
	private JTextArea txtFllwUp;

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String username, String patientID, String CSID, String patientName, String diagnosis, String summary, String treatment, String followUpProgress, String admissionID, String dateTimeOfAdmission, String dateTimeOfDischarge, String statusAtDischarge, String timestamp, String signature, String doctorID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewClinicalSummary2 window = new ViewClinicalSummary2(username, patientID, CSID, patientName, diagnosis, summary, treatment, followUpProgress, admissionID, dateTimeOfAdmission, dateTimeOfDischarge, statusAtDischarge, timestamp, signature, doctorID);
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
	public ViewClinicalSummary2(String username, String patientID, String CSID, String patientName, String diagnosis, String summary, String treatment, String followUpProgress, String admissionID, String dateTimeOfAdmission, String dateTimeOfDischarge, String statusAtDischarge, String timestamp, String signature, String doctorID) {
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
    	
        Decrypter decrypt = new Decrypter();
        decrpytedPatientIC = decrypt.decrypter(patientIC);
        txtPatientIC.setText(decrpytedPatientIC);
        txtPatient.setText(patientName);
        txtCSID.setText(CSID);
        txtDiagnosis.setText(diagnosis);
        txtSumIvsg.setText(summary);
        txtTreatment.setText(treatment);
        txtFllwUp.setText(followUpProgress);
        
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
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ViewClinicalSummary1.createAndShowGUI(username, CSID, timestamp);
        		frame.dispose();
        	}
        });
        btnBack.setBounds(35, 26, 85, 21);
		panel.add(btnBack);
        
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
		
		JLabel lblNewLabel_3 = new JLabel("CS ID:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(451, 149, 101, 13);
		panel.add(lblNewLabel_3);
		
		txtCSID = new JTextField();
		txtCSID.setBounds(451, 175, 143, 32);
		txtCSID.setEditable(false);
		panel.add(txtCSID);
		txtCSID.setColumns(10);
		
		JLabel Diagnosis = new JLabel("Diagnosis");
		Diagnosis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Diagnosis.setBounds(33, 217, 143, 19);
		panel.add(Diagnosis);
		
		txtDiagnosis = new JTextArea();
		txtDiagnosis.setBounds(35, 246, 557, 58);
		txtDiagnosis.setEditable(false);
		panel.add(txtDiagnosis);
		
        JLabel SumInvestigation = new JLabel("Summary of Investigation");
        SumInvestigation.setFont(new Font("Tahoma", Font.PLAIN, 15));
        SumInvestigation.setBounds(33, 317, 180, 19);
        panel.add(SumInvestigation);
        
        txtSumIvsg = new JTextArea();
        txtSumIvsg.setBounds(35, 346, 557, 58);
        txtSumIvsg.setEditable(false);
		panel.add(txtSumIvsg);
		
        JLabel treatment = new JLabel("Treatment");
        treatment.setFont(new Font("Tahoma", Font.PLAIN, 15));
        treatment.setBounds(39, 417, 143, 19);
        panel.add(treatment);
        
        txtTreatment = new JTextArea();
        txtTreatment.setBounds(37, 446, 557, 58);
        txtTreatment.setEditable(false);
		panel.add(txtTreatment);
        
        JLabel FupProgress = new JLabel("Follow-up Progress");
        FupProgress.setFont(new Font("Tahoma", Font.PLAIN, 15));
        FupProgress.setBounds(35, 517, 143, 19);
        panel.add(FupProgress);
        
        txtFllwUp = new JTextArea();
        txtFllwUp.setBounds(37, 546, 557, 58);
        txtFllwUp.setEditable(false);
		panel.add(txtFllwUp);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				nextActionPerformed();
			}
		});
		btnNext.setBounds(509, 609, 85, 21);
		panel.add(btnNext);
	}

	public void nextActionPerformed() {

		ViewClinicalSummary3.createAndShowGUI(username, patientIC, CSID, patientName, diagnosis, summary, treatment, followUpProgress, admissionID, dateTimeOfAdmission, dateTimeOfDischarge, statusAtDischarge, timestamp, signature, doctorID);
		frame.dispose();
	}

}
