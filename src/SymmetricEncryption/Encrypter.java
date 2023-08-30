package SymmetricEncryption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encrypter {
	public String encrypter(String data) {
		Symmetric symm = new Symmetric();
		// retrieve key pair
	    byte[] secretKeyBytes = null;
	    try {
			secretKeyBytes = Files.readAllBytes(Paths.get("KeyManagement/Shared/SymmetricKey/SecretKey"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // Convert it from byte array back to Key 
	    SecretKey secretKey = new SecretKeySpec(secretKeyBytes, "AES");  // Use "AES" or whatever algorithm you are using
	    System.out.println("Secret key retrieved (Key): " + secretKey);
	    String secretKeyString = Base64.getEncoder().encodeToString(secretKeyBytes);	
		System.out.println("Secret key retrieved (String): " + secretKeyString);
		
		//encrypt it!
		String ciphertext = symm.encrypt(data, secretKey);
		System.out.println("Encrypted: " + ciphertext);
		return ciphertext;
	}
}
