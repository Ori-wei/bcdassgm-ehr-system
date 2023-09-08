package Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import DatabaseObject.Admin;
import DatabaseObject.Hospital;
import Hashing.Salt;
import Hashing.SaltedHasher;
import Tools.RandomPasswordGenerator;
import asymmetric.AsymmetricKeyPair;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterClinician {

	private JFrame frame;
	private JTextField tfName;
	private JTextField tfDesignation;
	private JTextField tfClinicianICNo;
	private JTextField tfHospitalID;
	private JTextField tfDepartment;
	private JComboBox<Hospital> cbHospitalName;
	
	String adminID=null;

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String adminID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterClinician window = new RegisterClinician(adminID);
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
	public RegisterClinician(String adminID) 
	{
		initialize();
		String hospitalID = null;
		String hospitalName = null;
		this.adminID=adminID;
		List<Hospital> hospitalList = new ArrayList<>();
		
		
        try {
	    	Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * from BCD.hospital");
            while (rs.next()) {
                Hospital hospital = new Hospital(rs.getString("HospitalID"), rs.getString("name"));
                hospitalList.add(hospital);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            for (Hospital hospital : hospitalList) {
            	hospitalID=hospital.getHospitalID();
            	hospitalName = hospital.getName();   
            	cbHospitalName.addItem(hospital);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }  
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 696, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 662, 465);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbSystem = new JLabel("EHR System");
		lbSystem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbSystem.setBounds(216, 28, 145, 33);
		panel.add(lbSystem);
		
		JLabel lbScreen = new JLabel("Register Clinician");
		lbScreen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbScreen.setBounds(205, 71, 156, 33);
		panel.add(lbScreen);
		
		JLabel lbClinicianName = new JLabel("Clinician Name: ");
		lbClinicianName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbClinicianName.setBounds(124, 170, 131, 34);
		panel.add(lbClinicianName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfName.setColumns(10);
		tfName.setBounds(303, 169, 157, 34);
		panel.add(tfName);
		
		JLabel lbDesignation = new JLabel("Designation:");
		lbDesignation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDesignation.setBounds(124, 214, 131, 34);
		panel.add(lbDesignation);
		
		tfDesignation = new JTextField();
		tfDesignation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfDesignation.setColumns(10);
		tfDesignation.setBounds(303, 214, 157, 34);
		panel.add(tfDesignation);
		
		JLabel lbHospitalName = new JLabel("Hospital Name: ");
		lbHospitalName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbHospitalName.setBounds(124, 302, 131, 34);
		panel.add(lbHospitalName);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = null;
				String password;
				String salt;
				String hashedPassword;
				String clinicianID = tfClinicianICNo.getText();
				String name=tfName.getText();
				String designation=tfDesignation.getText();
				String department=tfDepartment.getText();
				String hospitalID=tfHospitalID.getText();
				
				byte[] salt_byte;
				
				//search from database if the record exist. If yes, abort. If no, proceed to generate username and password and insert record	
		        try (Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
		             Statement stmt = conn.createStatement();
		             ResultSet rs = stmt.executeQuery("SELECT * from BCD.clinician where clinicianID = '" + clinicianID + "'")) {
		        	
		        	int rowCount = 0;
		            while (rs.next()) {
		                rowCount++;
		            }
		            System.out.println("Number of rows: " + rowCount);
		        	if(rowCount==0)
		        	{
		        		System.out.println("Record not found. Proceed");
		        		password = RandomPasswordGenerator.generateRandomPassword();
		        		System.out.println("Password: " + password);
		        		
		        		salt_byte = Salt.generate();
		        		salt = Base64.getEncoder().encodeToString(salt_byte);
		        		System.out.println("Salt is: " + salt);
		        		
		        		hashedPassword = SaltedHasher.sha256(password, salt_byte);
		        		System.out.println("Hashed Password is: " + hashedPassword);	
		        		
		                try (Statement stmt2 = conn.createStatement();
		                        ResultSet rs2 = stmt2.executeQuery("SELECT * from BCD.clinician ")) {
		                   	
		                   	rowCount = 0;
		                       while (rs2.next()) {
		                           rowCount++;
		                       }
		                       if(rowCount>=0)
		                       {
		                    	   rowCount+=1;
		                    	   System.out.println("Proceed");
		                    	   String prefix = "C";
		                           // Find the number of digits in row
		                           int numberOfDigits = String.valueOf(rowCount).length();                        
		                           // Determine the number of zeros to prepend
		                           int numberOfZeros = 4 - numberOfDigits;  // Assuming 4-digit username 
		                           // Create zeros string
		                           StringBuilder zeros = new StringBuilder();
		                           for (int i = 0; i < numberOfZeros; i++) {
		                               zeros.append("0");
		                           }
		                           username = (prefix + zeros + rowCount);    
		                           System.out.println(username);
		                       }
		                       else
		                       {
		                    	   System.out.println("Error");  
		                       }
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                }
		                int affectedRows=0;
		                try (Statement stmt3 = conn.createStatement()) {
		                    affectedRows = stmt3.executeUpdate("INSERT INTO BCD.clinician (clinicianID, name, designation, department, hospitalID, username, password, salt)  "
		                    		+ "VALUES ('" + clinicianID + "','" + name + "','" + designation + "','" + department + "', '" + hospitalID + "','" + username + "','" + hashedPassword + "','" + salt + "')");
		                    System.out.println("Affected rows: " + affectedRows);
		                    if(affectedRows==1)
		                    {
		                    	System.out.println("Copy this: ");
		                        System.out.println("Clinician ID inserted: " + clinicianID);
		                        System.out.println("Username: " + username);
		                        System.out.println("Password: " + password);
		                        System.out.println("Salted Password hash: " + hashedPassword);
		                                              
								// create key pair
		                        AsymmetricKeyPair asymmetricKeyPair = new AsymmetricKeyPair();
		                		asymmetricKeyPair.createKeyPair();
		                		byte[] publicKey = asymmetricKeyPair.getPublickey().getEncoded();
		                		byte[] privateKey = asymmetricKeyPair.getPrivatekey().getEncoded();
		                		
		                		AsymmetricKeyPair.makeDirectory(publicKey, "KeyManagement/" + username + "/AsymmetricKeyPair/PublicKey");
		                		AsymmetricKeyPair.makeDirectory(privateKey, "KeyManagement/" + username + "/AsymmetricKeyPair/PrivateKey");

		                		System.out.println("Generated Key Pair:");
		                		System.out.println("Public key:" + Base64.getEncoder().encodeToString(publicKey));
		                		System.out.println("Private key:" + Base64.getEncoder().encodeToString(privateKey));
		                		
		                    }
		                    else
		                    {
		                    	System.out.println("Error. Clinician not created.");
		                    }
		                } catch (SQLException ex1) {
		                    ex1.printStackTrace();
		                }
		        	}
		        	else
		        	{
		        		System.out.println("Record exist. Abort.");
		        	}
		            
		        } catch (SQLException ex2) {
		            ex2.printStackTrace();
		        }
				
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(244, 412, 105, 33);
		panel.add(btnSubmit);
		
		JLabel lbClinicianICNo = new JLabel("Clinician IC: ");
		lbClinicianICNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbClinicianICNo.setBounds(124, 125, 131, 34);
		panel.add(lbClinicianICNo);
		
		tfClinicianICNo = new JTextField();
		tfClinicianICNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfClinicianICNo.setColumns(10);
		tfClinicianICNo.setBounds(303, 124, 157, 34);
		panel.add(tfClinicianICNo);
		
		JLabel lbHospitalID = new JLabel("Hospital ID: ");
		lbHospitalID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbHospitalID.setBounds(124, 346, 131, 34);
		panel.add(lbHospitalID);
		
		tfHospitalID = new JTextField();
		tfHospitalID.setEditable(false);
		tfHospitalID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfHospitalID.setColumns(10);
		tfHospitalID.setBounds(303, 346, 157, 34);
		panel.add(tfHospitalID);
		
		cbHospitalName = new JComboBox();
		cbHospitalName.setBounds(303, 302, 157, 30);
		cbHospitalName.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            Hospital selectedHospital = (Hospital) e.getItem();
		            String hospitalID = selectedHospital.getHospitalID();
		            tfHospitalID.setText(hospitalID);
		        }
		    }
		});
		panel.add(cbHospitalName);
		
		JLabel lbDepartment = new JLabel("Department: ");
		lbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDepartment.setBounds(124, 258, 131, 34);
		panel.add(lbDepartment);
		
		tfDepartment = new JTextField();
		tfDepartment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfDepartment.setColumns(10);
		tfDepartment.setBounds(303, 258, 157, 34);
		panel.add(tfDepartment);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAdmin.createAndShowGUI(adminID);
				frame.dispose();
			}
		});
		btnBack.setBounds(25, 28, 85, 21);
		panel.add(btnBack);
	}
}
