package Clinician;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateClinicalSummary2 {

	private JFrame frame;
	public String username;
	public String patientName;
	public String CSID;
	private JTextField txtPatient;
	private JTextField txtReportID;
	private JTextField txtPatientIC;

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String username, String patientName, String CSID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateClinicalSummary2 window = new CreateClinicalSummary2(username, patientName, CSID);
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
	public CreateClinicalSummary2(String username, String patientName, String CSID) {
		initialize();
		this.username = username;
		this.CSID = CSID;
		
		txtPatient.setText(patientName);
		txtReportID.setText(CSID);
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
        		CreateClinicalSummary1.createAndShowGUI(username);
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
		txtPatientIC.setEnabled(false);
		panel.add(txtPatientIC);
		txtPatientIC.setColumns(10);
        
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
		
		JLabel Diagnosis = new JLabel("Diagnosis");
		Diagnosis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Diagnosis.setBounds(33, 217, 143, 19);
		panel.add(Diagnosis);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(35, 246, 557, 58);
		panel.add(textArea_2);
		
        JLabel SumInvestigation = new JLabel("Summary of Investigation");
        SumInvestigation.setFont(new Font("Tahoma", Font.PLAIN, 15));
        SumInvestigation.setBounds(33, 317, 180, 19);
        panel.add(SumInvestigation);
        
        JTextArea textArea_4 = new JTextArea();
		textArea_4.setBounds(35, 346, 557, 58);
		panel.add(textArea_4);
		
        JLabel treatment = new JLabel("Treatment");
        treatment.setFont(new Font("Tahoma", Font.PLAIN, 15));
        treatment.setBounds(39, 417, 143, 19);
        panel.add(treatment);
        
        JTextArea textArea_5 = new JTextArea();
		textArea_5.setBounds(37, 446, 557, 58);
		panel.add(textArea_5);
        
        JLabel FupProgress = new JLabel("Follow-up Progress");
        FupProgress.setFont(new Font("Tahoma", Font.PLAIN, 15));
        FupProgress.setBounds(35, 517, 143, 19);
        panel.add(FupProgress);
        
        JTextArea textArea_6 = new JTextArea();
		textArea_6.setBounds(37, 546, 557, 58);
		panel.add(textArea_6);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				actionPerformed(evt);
			}
		});
		btnNext.setBounds(509, 609, 85, 21);
		panel.add(btnNext);
	}

	public void actionPerformed(ActionEvent evt) {
		CreateClinicalSummary2.createAndShowGUI(username, patientName, CSID);
	}
}
