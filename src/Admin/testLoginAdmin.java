package Admin;

import java.awt.Menu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.mindrot.jbcrypt.BCrypt;

import DatabaseObject.Admin;
import Hashing.SaltedHasher;

public class testLoginAdmin {
	public static List<Admin> getAdmin(String username)
	{
		List<Admin> adminList = new ArrayList<>();
		String sql = "SELECT * from BCD.admin where username = '" + username + "'";
			
        try (Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from BCD.admin where username = '" + username + "'")) {
        	
            while (rs.next()) {
                Admin admin = new Admin(rs.getString("AdminID"), rs.getString("username"), rs.getString("password"), rs.getString("salt"));
                adminList.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
	}
	
	public static void main(String[] args) throws DecoderException
	{
		String adminID=null;
		String cmpUsername=null;
		String saltedHashPassword=null;
		String salt = null;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter username: ");
		String	username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();
		List<Admin> adminList = getAdmin(username);
		
		try {
            for (Admin admin : adminList) {
            	adminID=admin.getAdminID();
            	cmpUsername = admin.getUsername();
            	saltedHashPassword=admin.getSaltedPassword();
            	salt=admin.getSalt();   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
		
        //compare password	
		System.out.println("salt is: " + salt);
//        // Check if all characters are valid hex digits
//        if (!salt.matches("[0-9a-fA-F]+")) {
//            System.out.println("The salt string contains illegal hexadecimal characters.");
//            return;
//        }
//
//        // Check if length is even
//        if (salt.length() % 2 != 0) {
//            System.out.println("The salt string has an odd number of characters. Cannot proceed.");
//            return;
//        }
//        
//		byte[] saltBytes = Hex.decodeHex(salt.toCharArray());
//        // Now you can use saltBytes in your hash function
//        String saltedcmpPasswordHash = SaltedHasher.sha256(password, saltBytes);
//  		System.out.println("Salted cmpPass hash is: " + saltedcmpPasswordHash);	
//  		
  		//compare
  		//boolean isValid = saltedcmpPasswordHash.equals(saltedHashPassword);
  		boolean isValid = BCrypt.checkpw(password, saltedHashPassword);
  		if (isValid==true)
  		{
  			System.out.println("Valid");
  			System.out.println("Welcome admin: " + adminID);
  		}
  		else
  		{
  			System.out.println("Not Valid");
  			System.out.println("Password not found. Please try again.");
  		}
	}
}
