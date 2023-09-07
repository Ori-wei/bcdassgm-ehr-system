package Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HospitalDetails {

	private JFrame frame;
	private JTextField tfName;
	private JTextField tfAddress;
	private JTextField tfContactNumber;
	private JTextField tfHospitalID;
	
	String adminID = null;

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String adminID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospitalDetails window = new HospitalDetails(adminID);
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
	public HospitalDetails(String adminID) {
		initialize();
		int rowCount;
		String hospitalID = null;
		this.adminID=adminID;
		try (Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\ASUS\\MyDB;","root","toor");
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery("SELECT * from BCD.hospital")) {
	        	
			rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            if(rowCount>=0)
            {
         	   rowCount+=1;
         	   System.out.println("Proceed");
         	   String prefix = "H";
                // Find the number of digits in row
                int numberOfDigits = String.valueOf(rowCount).length();                        
                // Determine the number of zeros to prepend
                int numberOfZeros = 4 - numberOfDigits;  // Assuming 4-digit hospitalID
                // Create zeros string
                StringBuilder zeros = new StringBuilder();
                for (int i = 0; i < numberOfZeros; i++) {
                    zeros.append("0");
                }
                hospitalID = (prefix + zeros + rowCount);    
                System.out.println(hospitalID);
            }
            else
            {
         	   System.out.println("Error");  
            }
		} catch (SQLException e) {
            e.printStackTrace();
        }
		tfHospitalID.setText(hospitalID);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 731, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 697, 499);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbSystem = new JLabel("EHR System");
		lbSystem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbSystem.setBounds(260, 36, 145, 33);
		panel.add(lbSystem);
		
		JLabel lblHospitalDetails = new JLabel("Hospital Details");
		lblHospitalDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHospitalDetails.setBounds(261, 79, 156, 33);
		panel.add(lblHospitalDetails);
		
		JLabel lbName = new JLabel("Hospital Name: ");
		lbName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbName.setBounds(69, 211, 131, 34);
		panel.add(lbName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfName.setColumns(10);
		tfName.setBounds(248, 210, 157, 34);
		panel.add(tfName);
		
		JLabel lbAddress = new JLabel("Address: ");
		lbAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbAddress.setBounds(69, 269, 131, 34);
		panel.add(lbAddress);
		
		tfAddress = new JTextField();
		tfAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfAddress.setColumns(10);
		tfAddress.setBounds(248, 269, 157, 34);
		panel.add(tfAddress);
		
		JLabel lbContactNumber = new JLabel("Contact Number: ");
		lbContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbContactNumber.setBounds(69, 326, 131, 34);
		panel.add(lbContactNumber);
		
		tfContactNumber = new JTextField();
		tfContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfContactNumber.setColumns(10);
		tfContactNumber.setBounds(248, 326, 157, 34);
		panel.add(tfContactNumber);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hospitalID = tfHospitalID.getText();
				String name = tfName.getText();
				String address = tfAddress.getText();
				String contactNumber = tfContactNumber.getText();
				int affectedRows;
				try {
					Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\ASUS\\MyDB;","root","toor");
					Statement stmt = conn.createStatement();
					affectedRows = stmt.executeUpdate("INSERT INTO BCD.hospital (hospitalID, name, address, contact)  "
                    		+ "VALUES ('" + hospitalID + "','" + name + "','" + address + "','" + contactNumber + "')");
                    System.out.println("Affected rows: " + affectedRows);
                    if(affectedRows==1)
                    {
                    	System.out.println("Hospital record " + hospitalID + " created.");
                    	HospitalDetails.createAndShowGUI(adminID);
                    }
                    else
                    {
                    	System.out.println("Error. Hospital not created.");
                    }
				} catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(185, 417, 105, 33);
		panel.add(btnSubmit);
		
		JLabel lbHospitalID = new JLabel("Hospital ID: ");
		lbHospitalID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbHospitalID.setBounds(69, 156, 131, 34);
		panel.add(lbHospitalID);
		
		tfHospitalID = new JTextField();
		tfHospitalID.setEditable(false);
		tfHospitalID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfHospitalID.setColumns(10);
		tfHospitalID.setBounds(248, 155, 157, 34);
		panel.add(tfHospitalID);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAdmin.createAndShowGUI(adminID);
			}
		});
		btnBack.setBounds(28, 36, 85, 21);
		panel.add(btnBack);
	}

}
