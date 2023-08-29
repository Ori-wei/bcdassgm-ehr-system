package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

import org.mindrot.jbcrypt.BCrypt;

import Hashing.Salt;
import Hashing.SaltedHasher;

public class test {
	public static void main(String[] args) throws ClassNotFoundException
	{
		String password = "admin123";
		//generate salt
		byte[] salt_byte = Salt.generate();
		System.out.println("Salt is: " + Base64.getEncoder().encodeToString(salt_byte));
		//test salted password hash
		String saltedPasswordHash = SaltedHasher.sha256(password, salt_byte);
		System.out.println("Salted hash is: " + saltedPasswordHash);	
		
		//compare password
		String cmpPass = "admin123";	
		String saltedcmpPasswordHash = SaltedHasher.sha256(cmpPass, salt_byte);
		System.out.println("Salted cmpPass hash is: " + saltedcmpPasswordHash);	
		//compare
		boolean isValid = saltedcmpPasswordHash.equals(saltedPasswordHash);
		if (isValid==true)
		{
			System.out.println("Valid");
		}
		else
		{
			System.out.println("Not Valid");
		}
		String adminID = "A0001";
		String username = "admin123";
      // SQL query
      String sql = "INSERT INTO BCD.admin (AdminID, Username, Password, Salt) VALUES ('" + adminID + "', '" + username + "', '" + saltedPasswordHash + "', '" + salt_byte + "')";
      
      try {
    	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      	Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\user\\MyDB;","root","toor");
      	conn.setSchema("BCD");
          // Create PreparedStatement
          try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
              // Execute update
              int rowsAffected = pstmt.executeUpdate();

              // Check the affected rows
              if (rowsAffected > 0) {
                  System.out.println("A new admin has been inserted.");
              }
          }
      } catch (SQLException ex) {
          ex.printStackTrace();
      }
	}
}
