package Clinician;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import SymmetricEncryption.Encrypter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SearchPatientIC {
	String patientIC = null;
	String username = null;
	String encryptedPatientIC = null;

	private JFrame frame;
	private JTextField tfPatientIC;

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchPatientIC window = new SearchPatientIC(username);
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
	public SearchPatientIC(String username) {
		initialize();
		this.username = username;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JLabel lbSystem = new JLabel("EHR System");
		lbSystem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbSystem.setBounds(171, 10, 145, 33);
		frame.getContentPane().add(lbSystem);
		
		JLabel lbTitle = new JLabel("Login Clinician");
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTitle.setBounds(181, 53, 135, 33);
		frame.getContentPane().add(lbTitle);
		
		tfPatientIC = new JTextField();
		tfPatientIC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPatientIC.setColumns(10);
		tfPatientIC.setBounds(159, 116, 187, 34);
		frame.getContentPane().add(tfPatientIC);
		
		JLabel lbPatientIC = new JLabel("Patient IC: ");
		lbPatientIC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbPatientIC.setBounds(75, 116, 85, 34);
		frame.getContentPane().add(lbPatientIC);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientIC = tfPatientIC.getText();
				Encrypter encrpyt = new Encrypter();
				encryptedPatientIC = encrpyt.encrypter(patientIC);
				boolean cont = false;
				try {
		    		Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
		            Statement stmt = conn.createStatement();
		            ResultSet rs1 = stmt.executeQuery("SELECT * from BCD.patient where IC_No = '" + encryptedPatientIC + "'");
		            while(rs1.next())
		            {
		            	cont = true;
		            }   
		    	}catch(SQLException ex) {
		    		ex.printStackTrace();
		        }	
				if(cont==true)
	            {
					System.out.println("true");
					DisplayPatientEHR.createAndShowGUI(username, encryptedPatientIC);
					frame.dispose();
	            }
				else
				{
					System.out.println("false");
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearch.setBounds(171, 181, 105, 33);
		frame.getContentPane().add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.createAndShowGUI(username);
			}
		});
		btnBack.setBounds(10, 22, 85, 21);
		frame.getContentPane().add(btnBack);
		frame.setBounds(100, 100, 507, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
