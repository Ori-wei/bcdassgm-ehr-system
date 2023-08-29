package Clinician;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;


public class MedicalReportCreation extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicalReportCreation frame = new MedicalReportCreation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 */
	public MedicalReportCreation() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 637, 640);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(612, 626)); // Size of panel
        
        // Inside the panel
        JLabel lblNewLabel = new JLabel("EHR System");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblNewLabel.setBounds(236, 37, 132, 71);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Medical Report");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(236, 99, 132, 46);
        panel.add(lblNewLabel_1);
        
        textField = new JTextField();
		textField.setBounds(35, 172, 143, 32);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Patient Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(33, 149, 101, 13);
		panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("History");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(35, 338, 101, 19);
		panel.add(lblNewLabel_2_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(37, 367, 557, 58);
		panel.add(textArea);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Area of Specialist");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(35, 219, 132, 19);
		panel.add(lblNewLabel_2_1_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Cardiology");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNewRadioButton.setBounds(17, 244, 103, 21);
		panel.add(rdbtnNewRadioButton);
        
        JRadioButton rdbtnEndocrinology = new JRadioButton("Endocrinology");
		rdbtnEndocrinology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnEndocrinology.setBounds(122, 244, 112, 21);
		panel.add(rdbtnEndocrinology);
		
		JRadioButton rdbtnGastroenterology = new JRadioButton("Gastroenterology");
		rdbtnGastroenterology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnGastroenterology.setBounds(236, 244, 130, 21);
		panel.add(rdbtnGastroenterology);
		
		JRadioButton rdbtnHematology = new JRadioButton("Hematology");
		rdbtnHematology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnHematology.setBounds(368, 244, 103, 21);
		panel.add(rdbtnHematology);
		
		JRadioButton rdbtnInfectiousDiseases = new JRadioButton("Infectious Diseases");
		rdbtnInfectiousDiseases.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnInfectiousDiseases.setBounds(473, 244, 140, 21);
		panel.add(rdbtnInfectiousDiseases);
        
        JRadioButton rdbtnNephrology = new JRadioButton("Nephrology");
		rdbtnNephrology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNephrology.setBounds(17, 273, 103, 21);
		panel.add(rdbtnNephrology);
		
		JRadioButton rdbtnOncology = new JRadioButton("Oncology");
		rdbtnOncology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnOncology.setBounds(122, 273, 103, 21);
		panel.add(rdbtnOncology);
		
		JRadioButton rdbtnPulmonology = new JRadioButton("Pulmonology");
		rdbtnPulmonology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnPulmonology.setBounds(236, 273, 103, 21);
		panel.add(rdbtnPulmonology);
		
		JRadioButton rdbtnRheumatology = new JRadioButton("Rheumatology");
		rdbtnRheumatology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnRheumatology.setBounds(368, 273, 103, 21);
		panel.add(rdbtnRheumatology);
        
        JRadioButton rdbtnCriticalCareMedicine = new JRadioButton("Critical Care Medicine");
		rdbtnCriticalCareMedicine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnCriticalCareMedicine.setBounds(473, 273, 134, 21);
		panel.add(rdbtnCriticalCareMedicine);
		
		JRadioButton rdbtnGeriatricMedicine = new JRadioButton("Geriatric Medicine");
		rdbtnGeriatricMedicine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnGeriatricMedicine.setBounds(17, 301, 107, 21);
		panel.add(rdbtnGeriatricMedicine);
		
		JRadioButton rdbtnAllergyAndImmunology = new JRadioButton("Immunology");
		rdbtnAllergyAndImmunology.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnAllergyAndImmunology.setBounds(125, 301, 107, 21);
		panel.add(rdbtnAllergyAndImmunology);
		
		JRadioButton rdbtnSportsMedicine = new JRadioButton("Sports Medicine");
		rdbtnSportsMedicine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnSportsMedicine.setBounds(236, 301, 117, 21);
		panel.add(rdbtnSportsMedicine);
		
        JRadioButton rdbtnSleepMedicine = new JRadioButton("Sleep Medicine");
		rdbtnSleepMedicine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnSleepMedicine.setBounds(368, 301, 107, 21);
		panel.add(rdbtnSleepMedicine);
		
		JRadioButton rdbtnAddictionMedicine = new JRadioButton("Addiction Medicine");
		rdbtnAddictionMedicine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnAddictionMedicine.setBounds(473, 301, 140, 21);
		panel.add(rdbtnAddictionMedicine);
		 
	    ButtonGroup specialistGroup = new ButtonGroup();
	    specialistGroup.add(rdbtnNewRadioButton);
	    specialistGroup.add(rdbtnEndocrinology);
	    specialistGroup.add(rdbtnGastroenterology);
	    specialistGroup.add(rdbtnHematology);
	    specialistGroup.add(rdbtnInfectiousDiseases);
	    specialistGroup.add(rdbtnNephrology);
	    specialistGroup.add(rdbtnOncology);
	    specialistGroup.add(rdbtnPulmonology);
	    specialistGroup.add(rdbtnRheumatology);
	    specialistGroup.add(rdbtnCriticalCareMedicine);
	    specialistGroup.add(rdbtnGeriatricMedicine);
	    specialistGroup.add(rdbtnAllergyAndImmunology);
	    specialistGroup.add(rdbtnSportsMedicine);
	    specialistGroup.add(rdbtnSleepMedicine);
	    specialistGroup.add(rdbtnAddictionMedicine);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(37, 480, 557, 58);
		panel.add(textArea_1);
		
		JLabel Physical_Examination = new JLabel("Physical Examination");
		Physical_Examination.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Physical_Examination.setBounds(35, 451, 143, 19);
		panel.add(Physical_Examination);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(37, 580, 557, 58);
		panel.add(textArea_2);
		
		JLabel Diagnosis = new JLabel("Diagnosis");
		Diagnosis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Diagnosis.setBounds(35, 551, 143, 19);
		panel.add(Diagnosis);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(37, 680, 557, 58);
		panel.add(textArea_3);
		
		JLabel Allergies = new JLabel("Allergies");
		Allergies.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Allergies.setBounds(35, 651, 143, 19);
		panel.add(Allergies);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBounds(37, 780, 557, 58);
		panel.add(textArea_4);
		
        JLabel SumInvestigation = new JLabel("Summary of Investigation");
        SumInvestigation.setFont(new Font("Tahoma", Font.PLAIN, 15));
        SumInvestigation.setBounds(35, 751, 180, 19);
        panel.add(SumInvestigation);
        
        JTextArea textArea_5 = new JTextArea();
		textArea_5.setBounds(37, 880, 557, 58);
		panel.add(textArea_5);
		
        JLabel treatment = new JLabel("Treatment");
        treatment.setFont(new Font("Tahoma", Font.PLAIN, 15));
        treatment.setBounds(35, 851, 143, 19);
        panel.add(treatment);
        
        JTextArea textArea_6 = new JTextArea();
		textArea_6.setBounds(37, 980, 557, 58);
		panel.add(textArea_6);
		
        JLabel FupProgress = new JLabel("Follow-up Progress");
        FupProgress.setFont(new Font("Tahoma", Font.PLAIN, 15));
        FupProgress.setBounds(35, 951, 143, 19);
        panel.add(FupProgress);
        
        JLabel DatetimeVisit = new JLabel("Datetime of Visit");
        DatetimeVisit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        DatetimeVisit.setBounds(35, 1051, 143, 19);
        panel.add(DatetimeVisit);
        
        JTextField textField2 = new JTextField();
		textField2.setBounds(35, 1080, 143, 32);
		panel.add(textField2);
		textField.setColumns(10);
        
        JLabel PrincipalDoctor = new JLabel("Principal Doctor");
        PrincipalDoctor.setFont(new Font("Tahoma", Font.PLAIN, 15));
        PrincipalDoctor.setBounds(305, 1051, 143, 19);
        panel.add(PrincipalDoctor);
        
        JTextField textField3 = new JTextField();
		textField3.setBounds(305, 1080, 143, 32);
		panel.add(textField3);
		textField.setColumns(10);
		
		// Prepared by
		JLabel PreparedBy = new JLabel("Prepared By:");
		PreparedBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PreparedBy.setBounds(35, 1151, 143, 19);
        panel.add(PreparedBy);
        
        // Name
        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblName.setBounds(35, 1200, 143, 19);
        panel.add(lblName);

        JTextField txtName = new JTextField();
        txtName.setBounds(190, 1200, 143, 32);
        panel.add(txtName);

        // Designation
        JLabel lblDesignation = new JLabel("Designation:");
        lblDesignation.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDesignation.setBounds(35, 1250, 143, 19);
        panel.add(lblDesignation);

        JTextField txtDesignation = new JTextField();
        txtDesignation.setBounds(190, 1250, 143, 32);
        panel.add(txtDesignation);

        // Department
        JLabel lblDepartment = new JLabel("Department:");
        lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDepartment.setBounds(35, 1300, 143, 19);
        panel.add(lblDepartment);

        JTextField txtDepartment = new JTextField();
        txtDepartment.setBounds(190, 1300, 143, 32);
        panel.add(txtDepartment);

        // Datetime of Preparation
        JLabel lblDatetime = new JLabel("Datetime of Preparation:");
        lblDatetime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDatetime.setBounds(35, 1350, 180, 19);
        panel.add(lblDatetime);

        JTextField txtDatetime = new JTextField();
        txtDatetime.setBounds(230, 1350, 143, 32);
        panel.add(txtDatetime);

        // Signature
        JLabel lblSignature = new JLabel("Signature:");
        lblSignature.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSignature.setBounds(35, 1400, 143, 19);
        panel.add(lblSignature);

        JTextField txtSignature = new JTextField();
        txtSignature.setBounds(190, 1400, 143, 32);
        panel.add(txtSignature);
        
        JButton btnSign = new JButton("Sign");
        btnSign.setBounds(480, 1450, 143, 32);
        panel.add(btnSign);
        
        // Set up the JScrollPane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(1, 1, 612, 626);
        contentPane.add(scrollPane);
        
        // Dynamically adjust padding
        int lastComponentY = btnSign.getBounds().y;
        int lastComponentHeight = btnSign.getBounds().height;
        int panelHeight = lastComponentY + lastComponentHeight + 30;
        panel.setPreferredSize(new Dimension(612, panelHeight));
    
    }
}
