package Clinician;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import BlockchainObject.ClinicalSummary;
import DatabaseObject.Clinician;
import DatabaseObject.Hospital;
import DatabaseObject.Patient;
import SymmetricEncryption.Decrypter;
import ehrBlockchain.Block;
import ehrBlockchain.Blockchain;
import ehrBlockchain.RecordCollection;

public class DisplayPatientEHR {

	private JFrame frame;
	private JTextField tfPatientIC;
	private JTextField tfPatientName;
	private JTextField tfSex;
	private JTable jtable1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayPatientEHR window = new DisplayPatientEHR();
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
	public DisplayPatientEHR() throws ParseException {
		initialize();
		String patientIC="BD/5lZqGUxOQcYEnbMrrMg==";
		String decrpytedPatientIC;
		String patientName;
		String sex;
		String CSID = "CS0001";
		//from the PatientID (IC_No) gotten, retrieve name and sex from database
		List<Patient> patientList = new ArrayList<>();
		try {
	    	Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * from BCD.patient where IC_No = '" + patientIC + "'");
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
            	tfPatientName.setText(patientName);
            	tfSex.setText(sex);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Decrypter decrypt = new Decrypter();
        decrpytedPatientIC = decrypt.decrypter(patientIC);
        tfPatientIC.setText(decrpytedPatientIC);
        
        final String masterFolder = "masterEHR";
        final String fileName = masterFolder + "/chain.bin";
        
		List<ClinicalSummary> clinicalSummaryList = new ArrayList<>();
		//1. from blockchain, retrieve transaction about the PatientID (done)
		//2. after retrieved, get the CSID and the Date (done)
		//3. set these 2 field into arraylist of object ClinicalSummary (done)
		//4. for each row, compare if same CSID exist. if exist, take and replace the old one with the latest date / timestamp.
		// Read the blockchain
		Blockchain bc = new Blockchain(fileName);
	    LinkedList<Block> EHRchain = bc.readBlockchain(fileName); // Make sure to provide the correct file name
	    // Create a map to keep track of latest ClinicalSummary by CSID
	    Map<String, ClinicalSummary> latestClinicalSummaries = new HashMap<>();
	    
	    // Filter and add records to the list
	    for (Block block : EHRchain) {
	        RecordCollection recordCollection = block.getEhrContainer(); // Assuming getEhrContainer returns a RecordCollection
	        for (String record : recordCollection) { // Assuming RecordCollection is an Iterable
	            // You would typically deserialize or parse the record to check the ID.
	        	String[] components = record.split("\\|");
	            String id = components[0]; 
	            System.out.println(id);
	            String IC = components[1];
	            String date = components[2]; 
	            System.out.println("Comparing patientIC: " + patientIC + " with IC: " + IC);
//	            if (patientIC.equals(IC)) {
//	            	for(String key : latestClinicalSummaries.keySet()) {
//	            	    ClinicalSummary cscs = latestClinicalSummaries.get(key);
//	            	    // do something
//	            	    if (cscs.get(id).equals(id)) {
//	                        System.out.println("Comparing CSID: " + CSID + " with id: " + id);
//		                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Assuming the date format in your record
//		                    Date recordDate = sdf.parse(date);
//		                    ClinicalSummary existingSummary = latestClinicalSummaries.get(id);
//		                    if (existingSummary == null || sdf.parse(existingSummary.getDate()).before(recordDate)) {
//		                        ClinicalSummary clinicalSummary = new ClinicalSummary(id, date);
//		                        latestClinicalSummaries.put(id, clinicalSummary);  
//		                    }
//		                }
//	            	}  	   
//	            }
	            if (patientIC.equals(IC)) {
	                ClinicalSummary existingSummary = latestClinicalSummaries.get(id);
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                Date recordDate = sdf.parse(date);

	                // This part ensures that only the latest date for this CSID remains in the map
	                if (existingSummary == null) {
	                	//System.out.println("Comparing old date: " + sdf.parse(existingSummary.getDate() + " with new date: " + recordDate));
	                	System.out.println(" with new date: " + recordDate);
	                    ClinicalSummary clinicalSummary = new ClinicalSummary(id, date);
	                    latestClinicalSummaries.put(id, clinicalSummary);  
	                    System.out.println(recordDate);
	                }
	                if (existingSummary != null) {
	                	System.out.println("record exist");
	                	if(sdf.parse(existingSummary.getDate()).before(recordDate))
	                	{
	                		//System.out.println("Comparing old date: " + sdf.parse(existingSummary.getDate() + " with new date: " + recordDate));
		                	System.out.println(" with new date: " + recordDate);
		                    ClinicalSummary clinicalSummary = new ClinicalSummary(id, date);
		                    latestClinicalSummaries.put(id, clinicalSummary);  
		                    System.out.println(recordDate);
	                	}

	                }
	            }
	        }
	    }
	    clinicalSummaryList = new ArrayList<>(latestClinicalSummaries.values());
		
		DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Every cell is non-editable.
            }
        };

        model.setColumnIdentifiers(new Object[]{"Index", "CSID", "Date"});
        jtable1.setModel(model);
        model.setRowCount(0);
        int index = 0;
        try {
            for (ClinicalSummary clinicalSummary : clinicalSummaryList) {
            	//initialize things into clinicalSummary object
            	index++;
                model.addRow(new Object[]{index, clinicalSummary.getCSID(), clinicalSummary.getDate()});     
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 682, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 648, 451);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbSystem = new JLabel("EHR System");
		lbSystem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbSystem.setBounds(222, 26, 145, 33);
		panel.add(lbSystem);
		
		JLabel lblPatientEhr = new JLabel("Patient EHR");
		lblPatientEhr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPatientEhr.setBounds(232, 68, 122, 33);
		panel.add(lblPatientEhr);
		
		JLabel lbPatientIC = new JLabel("Patient IC: ");
		lbPatientIC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbPatientIC.setBounds(21, 112, 75, 34);
		panel.add(lbPatientIC);
		
		tfPatientIC = new JTextField();
		tfPatientIC.setEditable(false);
		tfPatientIC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPatientIC.setColumns(10);
		tfPatientIC.setBounds(102, 112, 157, 34);
		panel.add(tfPatientIC);
		
		tfPatientName = new JTextField();
		tfPatientName.setEditable(false);
		tfPatientName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPatientName.setColumns(10);
		tfPatientName.setBounds(331, 113, 157, 34);
		panel.add(tfPatientName);
		
		JLabel lbPatientName = new JLabel("Name: ");
		lbPatientName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbPatientName.setBounds(278, 114, 59, 34);
		panel.add(lbPatientName);
		
		JLabel lbSex = new JLabel("Sex: ");
		lbSex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbSex.setBounds(509, 112, 41, 34);
		panel.add(lbSex);
		
		tfSex = new JTextField();
		tfSex.setEditable(false);
		tfSex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSex.setColumns(10);
		tfSex.setBounds(547, 113, 51, 34);
		panel.add(tfSex);
		
		jtable1 = new JTable();
	    JScrollPane scrollPane = new JScrollPane(jtable1);
	    scrollPane.setBounds(51, 200, 547, 157); // Changed y-position for demonstration
	    panel.add(scrollPane);
		
		JButton btnNext = new JButton("Next >");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNext.setBounds(497, 396, 101, 33);
		panel.add(btnNext);
	}
}
