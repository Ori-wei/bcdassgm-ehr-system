package Clinician;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;
	public String username;

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu(username);
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
	public Menu(String username) {
		this.username = username;
		initialize();
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

        JLabel lblNewLabel_1 = new JLabel("Clinician Menu");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(236, 99, 132, 46);
        panel.add(lblNewLabel_1);
        
        JButton btnNewButton = new JButton("Create Medical Report");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CreateClinicalSummary1.createAndShowGUI(username);
        		frame.dispose();
        	}
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(212, 266, 193, 21);
        panel.add(btnNewButton);
        
        JButton btnNewButton_2 = new JButton("Search to View EHR");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DisplayPatientEHR.createAndShowGUI(username, "patientIC");
        		frame.dispose();
        	}
        });
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_2.setBounds(212, 313, 193, 21);
        panel.add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("Create Patient");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		PatientCreation.createAndShowGUI();
        		frame.dispose();
        	}
        });
        btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_3.setBounds(238, 220, 132, 21);
        panel.add(btnNewButton_3);
        
        JButton btnNewButton_1 = new JButton("Verify Record");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_1.setBounds(238, 361, 132, 21);
        panel.add(btnNewButton_1);
	}
}
