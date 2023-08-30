package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import DatabaseObject.Admin;
import Tools.RandomPasswordGenerator;

import java.security.SecureRandom;

public class testRegisterClinician {
	
	
	
	public static void main(String[] args)
	{
		//id == ic
		//name
		//designation
		//hospitalID
		String username = null;
		String password;
		String salt;
		String hashedPassword;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter IC: ");
		String clinicianID = sc.nextLine();
		System.out.print("Enter name: ");
		String name = sc.nextLine();
		System.out.print("Enter designation: ");
		String designation = sc.nextLine();
		System.out.print("Enter department: ");
		String department = sc.nextLine();
		System.out.print("Enter hospitalID: ");
		String hospitalID = sc.nextLine();
		hospitalID = "H0001";
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
        		salt = BCrypt.gensalt();
        		System.out.println("Salt: " + salt);
        		hashedPassword = BCrypt.hashpw(password, salt);
                System.out.println("Hashed Password" + hashedPassword); 
                //sql to count row and add username C0001
                try (Statement stmt2 = conn.createStatement();
                        ResultSet rs2 = stmt2.executeQuery("SELECT * from BCD.clinician where clinicianID = '" + clinicianID + "'")) {
                   	
                   	rowCount = 0;
                       while (rs2.next()) {
                           rowCount++;
                       }
                       if(rowCount==0)
                       {
                    	   rowCount=1;
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
                } catch (SQLException e) {
                    e.printStackTrace();
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
                    }
                    else
                    {
                    	System.out.println("Error. Clinician not created.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        	}
        	else
        	{
        		System.out.println("Record exist. Abort.");
        	}
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		//then, generate username (C0001), password
	}
	
}
