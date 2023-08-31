package Clinician;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import DatabaseObject.Clinician;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class LoginClinician {

	private JFrame frame;
	private JTextField tfUsername;
	private JTextField tfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginClinician window = new LoginClinician();
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
	public LoginClinician() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 574, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 548, 356);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbSystem = new JLabel("EHR System");
		lbSystem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbSystem.setBounds(214, 31, 145, 33);
		panel.add(lbSystem);
		
		JLabel lbTitle = new JLabel("Login Clinician");
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTitle.setBounds(224, 74, 135, 33);
		panel.add(lbTitle);
		
		JLabel lbUsername = new JLabel("Username: ");
		lbUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbUsername.setBounds(76, 151, 85, 34);
		panel.add(lbUsername);
		
		JLabel lbPassword = new JLabel("Password: ");
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbPassword.setBounds(76, 209, 85, 34);
		panel.add(lbPassword);
		
		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfUsername.setBounds(160, 151, 157, 34);
		panel.add(tfUsername);
		tfUsername.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPassword.setColumns(10);
		tfPassword.setBounds(160, 209, 157, 34);
		panel.add(tfPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clinicianID=null;
				String cmpUsername=null;
				String saltedHashPassword=null;
				String salt = null;
				String username = tfUsername.getText();
				String password=tfPassword.getText();
				List<Clinician> clinicianList = new ArrayList<>();
				String sql = "SELECT * from BCD.clinician where username = '" + username + "'";
					
		        try (Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
		             Statement stmt = conn.createStatement();
		             ResultSet rs = stmt.executeQuery("SELECT * from BCD.clinician where username = '" + username + "'")) {
		        	
		            while (rs.next()) {
		                Clinician clinician = new Clinician(rs.getString("ClinicianID"), rs.getString("username"), rs.getString("password"), rs.getString("salt"));
		                clinicianList.add(clinician);
		            }
		        } catch (SQLException ex1) {
		            ex1.printStackTrace();
		        }
		        try {
		            for (Clinician clinician : clinicianList) {
		            	clinicianID=clinician.getClinicianID();
		            	cmpUsername = clinician.getUsername();
		            	saltedHashPassword=clinician.getSaltedPassword();
		            	salt=clinician.getSalt();   
		            }
		        } catch (Exception ex2) {
		            ex2.printStackTrace();
		        }  
				
		        //compare password	
				System.out.println("salt is: " + salt);

		  		//compare
		  		boolean isValid = BCrypt.checkpw(password, saltedHashPassword);
		  		if (isValid==true)
		  		{
		  			System.out.println("Valid");
		  			System.out.println("Welcome Clinician: " + username);
		  		}
		  		else
		  		{
		  			System.out.println("Not Valid");
		  			System.out.println("Password not found. Please try again.");
		  		}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(170, 287, 105, 33);
		panel.add(btnLogin);
	}
}
