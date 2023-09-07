package Clinician;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
	
	String patientIC = null;
	String username = null;
	String CSID = null;
	String datetime = null;

	/**
	 * Launch the application.
	 */
	public static void createAndShowGUI(String username, String patientIC) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayPatientEHR window = new DisplayPatientEHR(username, patientIC);
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
	public DisplayPatientEHR(String username, String patientIC) throws ParseException {
		initialize();
		this.username=username;
		this.patientIC=patientIC;
		String decrpytedPatientIC;
		String patientName;
		String sex;
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
	            String timestamp = components[15]; 
	            String admissionID = components[11];
	            String statusAtDischarge = components[14];
	            System.out.println("Comparing patientIC: " + patientIC + " with IC: " + IC);
	            if (patientIC.equals(IC)) {
	                ClinicalSummary existingSummary = latestClinicalSummaries.get(id);
	                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	                DateTimeFormatter formatter = new DateTimeFormatterBuilder()
	                        .appendPattern("yyyy-MM-dd HH:mm:ss")
	                        .appendFraction(ChronoField.MILLI_OF_SECOND, 1, 3, true) // 1 to 3 digits for milliseconds
	                        .toFormatter();
	                LocalDateTime recordTimestamp = LocalDateTime.parse(timestamp, formatter);

	                // This part ensures that only the latest date for this CSID remains in the map
	                if (existingSummary == null) {
	                	//System.out.println("Comparing old date: " + sdf.parse(existingSummary.getDate() + " with new date: " + recordDate));
	                	System.out.println(" with new date: " + recordTimestamp);
	                    ClinicalSummary clinicalSummary = new ClinicalSummary(id, timestamp, admissionID, statusAtDischarge);
	                    latestClinicalSummaries.put(id, clinicalSummary);  
	                    System.out.println(recordTimestamp);
	                }
	                if (existingSummary != null) {
	                	System.out.println("record exist");
	                	LocalDateTime existingRecordTimeStamp = LocalDateTime.parse(existingSummary.getTimestamp(), formatter);
	                	if(existingRecordTimeStamp.isBefore(recordTimestamp))
	                	{
	                		//System.out.println("Comparing old date: " + sdf.parse(existingSummary.getDate() + " with new date: " + recordDate));
		                	System.out.println(" with new date: " + recordTimestamp);
		                    ClinicalSummary clinicalSummary = new ClinicalSummary(id, timestamp, admissionID, statusAtDischarge);
		                    latestClinicalSummaries.put(id, clinicalSummary);  
		                    System.out.println(recordTimestamp);
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

        model.setColumnIdentifiers(new Object[]{"Index", "CSID", "AdmissionID", "Status at Discharge", "Date"});
        jtable1.setModel(model);
        model.setRowCount(0);
        int index = 0;
        try {
            for (ClinicalSummary clinicalSummary : clinicalSummaryList) {
            	//initialize things into clinicalSummary object
            	index++;
                model.addRow(new Object[]{index, clinicalSummary.getCSID(), clinicalSummary.getAdmissionID(), clinicalSummary.getStatusAtDischarge(), clinicalSummary.getTimestamp()});     
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
		
		JButton btnNext = new JButton("Next >");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNext.setBounds(497, 396, 101, 33);
		panel.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ViewClinicalSummary1.createAndShowGUI(username, CSID, datetime, patientIC);
        		frame.dispose();
        	}
        });
		
		JButton btnCreateDischarge = new JButton("Create Discharge");
		btnCreateDischarge.setEnabled(false);
		btnCreateDischarge.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCreateDischarge.setBounds(51, 396, 157, 33);
		panel.add(btnCreateDischarge);
		btnCreateDischarge.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("Selected CSID: " + CSID);
                System.out.println("Selected patientIC: " + patientIC);
                System.out.println("Selected username: " + username);
        		CreateDischarge.createAndShowGUI(CSID, patientIC, username);
        		frame.dispose();
        	}
        });
		
		jtable1 = new JTable();
	    JScrollPane scrollPane = new JScrollPane(jtable1);
	    scrollPane.setBounds(51, 200, 547, 157); // Changed y-position for demonstration
	    panel.add(scrollPane);
	    
	    JButton btnBack = new JButton("Back");
	    btnBack.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		SearchPatientIC.createAndShowGUI(username);
	    		frame.dispose();
	    	}
	    });
	    btnBack.setBounds(21, 38, 85, 21);
	    panel.add(btnBack);
	    jtable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent e) {
	            if (!e.getValueIsAdjusting()) {
	                int selectedRow = jtable1.getSelectedRow();
	                if (selectedRow >= 0) {
	                    // Extract values from selected row here. For example:
	                    Object selectedCSID = jtable1.getValueAt(selectedRow, 1); // 1 for the 2nd column
	                    Object selectedAdmissionID = jtable1.getValueAt(selectedRow, 2); // 2 for the 3rd column
	                    Object selectedStatusAtDischarge = jtable1.getValueAt(selectedRow, 3); // 2 for the 3rd column
	                    Object selectedDateTime = jtable1.getValueAt(selectedRow, 4); // 3 for the 4th column
	                    
	                    CSID = (String) selectedCSID;
	                    String admissionID = (String) selectedAdmissionID;
	                    String statusAtDischarge = (String) selectedStatusAtDischarge;
	                    datetime = (String) selectedDateTime;
	                    
	                    // Now you can use these selected values
	                    System.out.println("Selected CSID: " + selectedCSID);
	                    System.out.println("Selected AdmissionID: " + selectedAdmissionID);
	                    System.out.println("Selected Status at Discharge: " + selectedStatusAtDischarge);
	                    System.out.println("Selected Date: " + selectedDateTime);
	                    
	                    // If you want to enable a button upon row selection:
	                    if(!admissionID.equals("NA") && statusAtDischarge.equals("NA"))
	                    {
	                    	btnCreateDischarge.setEnabled(true);
	                    }
	                    if(admissionID.equals("NA") || !statusAtDischarge.equals("NA"))
	                    {
	                    	btnCreateDischarge.setEnabled(false);
	                    }
	                }
	            }
	        }
	    });
		
		
	}
}
