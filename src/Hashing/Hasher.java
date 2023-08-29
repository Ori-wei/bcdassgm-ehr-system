package Hashing;

import java.security.MessageDigest;
import java.util.Base64;
import org.apache.commons.codec.binary.Hex;

public class Hasher {
	/* -hash(String, String) : String */
	private static String hash(String input)
	{
		String hashCode = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(input.getBytes()); //convert the string (hashcode) into bytes
			//digesting...
			byte[] hashBytes = md.digest(); //store the bytes gotten into a bytes array hashBytes. //digest is to digest which algorithm this program belongs to 
			//convert the byte[] to String
			//1)
			hashCode = Base64.getEncoder().encodeToString(hashBytes); //Base64 is a build-in function to convert bytes to string and vice versa.
			//2) hex format output - recommended! Using external imported library
			hashCode = Hex.encodeHexString(hashBytes); //Hex is an external library to convert Hex to string. industry all use Hex
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashCode;
	}
	
	//sha256 hashing method code 
	/* sha256 */
	public static String sha256(String input)
	{
		return hash(input);
	}
}