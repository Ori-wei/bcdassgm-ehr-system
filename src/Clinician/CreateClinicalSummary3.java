package Clinician;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import DigitalSignature.UserSignature;
import asymmetric.AccessKey;

public class CreateClinicalSummary3 {

	private JFrame frame;
	public String username;
	public String patientName;
	public String CSID;
	private JTextField txtPatient;
	private JTextField txtReportID;
	private JTextField txtDTVisit;
	private JTextField txtPatientIC;

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String username, String patientName, String CSID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateClinicalSummary3 window = new CreateClinicalSummary3(username, patientName, CSID);
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
	public CreateClinicalSummary3(String username, String patientName, String CSID) {
		initialize();
		this.username = username;
		this.patientName = patientName;
		this.CSID = CSID;
		
		txtPatient.setText(patientName);
		txtReportID.setText(CSID);
		
		Timestamp pytimestamp = new Timestamp(System.currentTimeMillis());
		txtDTVisit.setText(pytimestamp.toString());
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
		
		JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CreateClinicalSummary2.createAndShowGUI(username, patientName, CSID);
        	}
        });
        btnBack.setBounds(35, 26, 85, 21);
		panel.add(btnBack);
		
		// Components for page3
		JLabel DatetimeVisit = new JLabel("Datetime of Visit");
        DatetimeVisit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        DatetimeVisit.setBounds(35, 240, 143, 19);
        panel.add(DatetimeVisit);
        
        txtDTVisit = new JTextField();
        txtDTVisit.setBounds(33, 270, 143, 32);
        txtDTVisit.setEnabled(false);
		panel.add(txtDTVisit);
		txtDTVisit.setColumns(10);
        
        JLabel PrincipalDoctor = new JLabel("Principal Doctor");
        PrincipalDoctor.setFont(new Font("Tahoma", Font.PLAIN, 15));
        PrincipalDoctor.setBounds(451, 240, 143, 19);
        panel.add(PrincipalDoctor);
        
        JTextField txtDoc = new JTextField();
        txtDoc.setBounds(451, 270, 143, 32);
		panel.add(txtDoc);
		txtDoc.setColumns(10);
		
		// Prepared by
		JLabel PreparedBy = new JLabel("Prepared By:");
		PreparedBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PreparedBy.setBounds(33, 324, 143, 19);
        panel.add(PreparedBy);
        
        // Name
        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblName.setBounds(33, 361, 143, 19);
        panel.add(lblName);

        JTextField txtName = new JTextField();
        txtName.setBounds(190, 357, 143, 32);
        txtName.setEnabled(false);
        panel.add(txtName);

        // Designation
        JLabel lblDesignation = new JLabel("Designation:");
        lblDesignation.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDesignation.setBounds(33, 405, 143, 19);
        panel.add(lblDesignation);

        JTextField txtDesignation = new JTextField();
        txtDesignation.setBounds(190, 401, 143, 32);
        txtDesignation.setEnabled(false);
        panel.add(txtDesignation);

        // Department
        JLabel lblDepartment = new JLabel("Department:");
        lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDepartment.setBounds(35, 449, 143, 19);
        panel.add(lblDepartment);

        JTextField txtDepartment = new JTextField();
        txtDepartment.setBounds(190, 443, 143, 32);
        txtDepartment.setEnabled(false);
        panel.add(txtDepartment);

        // Datetime of Preparation
        JLabel lblDatetime = new JLabel("Preparation Datetime:");
        lblDatetime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDatetime.setBounds(35, 493, 180, 19);
        panel.add(lblDatetime);

        JTextField txtDatetime = new JTextField();
        txtDatetime.setBounds(190, 485, 143, 32);
        txtDatetime.setEnabled(false);
        panel.add(txtDatetime);
        
        // Signature
        JLabel lblSignature = new JLabel("Signature:");
        lblSignature.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSignature.setBounds(35, 537, 180, 19);
        panel.add(lblSignature);

        JTextField txtSignature = new JTextField();
        txtSignature.setBounds(190, 531, 143, 32);
        txtSignature.setEnabled(false);
        panel.add(txtSignature);
        
        JButton btnSign = new JButton("Sign");
        btnSign.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	String name = null;
            	String designation = null;
            	String department = null;
            	try {
            		Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * from BCD.clinician where username = '" + username + "'");
                    name = rs.getString("name");
                    designation = rs.getString("designation");
                    department = rs.getString("department");
            	} catch (SQLException e1) {
                    e1.printStackTrace();
                }
    
            	txtName.setText(name);
            	txtDesignation.setText(designation);
            	txtDepartment.setText(department);
            	txtDatetime.setText((new Timestamp(System.currentTimeMillis())).toString());
            	
            	// to-do: Concat all data into one String
                String CSRecord = "";
                PrivateKey privateKey = null;
                try {
                    privateKey = AccessKey.getPrivateKey("KeyManagement/" + username + "/AsymmetricKeyPair/PrivateKey");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (privateKey != null) {
                	UserSignature userSignature = new UserSignature();
                	byte[] signature = userSignature.getSignature(CSRecord, privateKey);
                }
            }
		});
        btnSign.setBounds(186, 325, 85, 21);
		panel.add(btnSign);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageAdmission.createAndShowGUI();
			}
		});
		btnNext.setBounds(509, 572, 85, 21);
		panel.add(btnNext);
	}

}
