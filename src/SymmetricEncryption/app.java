package SymmetricEncryption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import SymmetricEncryption.Encrypter;
import SymmetricEncryption.Decrypter;

public class app {
	public static void main(String[] args)
	{
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
		
		/* Test encrypt and decrypt with secret key retrieved */
		String msg = "peekaboo";		
		//encrypt it!
		Encrypter encypt = new Encrypter();
		String cipherText = encypt.encrypter(msg);
		System.out.println("Encrypted: " + cipherText);
		
		//decrypt it!
		Decrypter decypt = new Decrypter();
		String originalData = decypt.decrypter(cipherText);
		System.out.println("Original Content: " + originalData);
	}	
}

