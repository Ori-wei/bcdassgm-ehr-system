package Hashing;

import java.security.MessageDigest;
import java.util.Base64;

import org.apache.commons.codec.binary.Hex;

public class SaltedHasher {
	/* hashing function with salt */
	public static String hash(String input, byte[] salt) {
		MessageDigest md;
		try
		{
			//instantiate the MD object
			md = MessageDigest.getInstance("SHA-256");
			//fetch input to MD
			md.update(input.getBytes());
			md.update(salt);
			
			//digest it
			byte[] hashBytes = md.digest();
			//convert to Hex format with Hex API from Apache common
			return String.valueOf(Hex.encodeHex(hashBytes));
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//sha256 hashing method code 
	/* sha256 */
	public static String sha256(String input, byte[] salt)
	{
		return hash(input, salt);
	}
	
	public static void main(String[] args) {		
		//salt generator
		byte[] salt_byte = Salt.generate();
		System.out.println("Salt is: " + Base64.getEncoder().encodeToString(salt_byte));
		//test generated Strong with added salt, sha256 hash algorithm
		String salted_sha256_string = sha256("hello", salt_byte);
		System.out.println("Salted hash is: " + salted_sha256_string);		
	}
}
