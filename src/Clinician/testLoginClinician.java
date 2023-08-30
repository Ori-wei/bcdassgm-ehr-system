package Clinician;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.codec.DecoderException;
import org.mindrot.jbcrypt.BCrypt;

import DatabaseObject.Clinician;

public class testLoginClinician {
	public static List<Clinician> getClinician(String username)
	{
		List<Clinician> clinicianList = new ArrayList<>();
		String sql = "SELECT * from BCD.clinician where username = '" + username + "'";
			
        try (Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from BCD.clinician where username = '" + username + "'")) {
        	
            while (rs.next()) {
                Clinician clinician = new Clinician(rs.getString("ClinicianID"), rs.getString("username"), rs.getString("password"), rs.getString("salt"));
                clinicianList.add(clinician);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clinicianList;
	}
	
	public static void main(String[] args) throws DecoderException
	{
		String clinicianID=null;
		String cmpUsername=null;
		String saltedHashPassword=null;
		String salt = null;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter username: ");
		String	username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();
		List<Clinician> clinicianList = getClinician(username);
		
		try {
            for (Clinician clinician : clinicianList) {
            	clinicianID=clinician.getClinicianID();
            	cmpUsername = clinician.getUsername();
            	saltedHashPassword=clinician.getSaltedPassword();
            	salt=clinician.getSalt();   
            }
        } catch (Exception e) {
            e.printStackTrace();
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
}
