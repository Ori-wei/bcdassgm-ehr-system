package SymmetricEncryption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.PublicKey;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import asymmetric.AccessKey;
import asymmetric.AsymmetricKeyPair;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class GenerateKey {

	public static void main(String[] args) {
		Symmetric symm = new Symmetric();
		
		/* test class of Symmetric*/
		Key secretKey = PredefinedCharsSecretKey.create();
		String secretKeyStringSymm = Base64.getEncoder().encodeToString(secretKey.getEncoded());	
		System.out.println("My secret key (Key): " + secretKey);
		System.out.println("My secret key (String): " + secretKeyStringSymm);
        symm.makeDirectory(secretKey.getEncoded(), "KeyManagement/Shared/SymmetricKey/SecretKey");
        
        // retrieve key pair
        byte[] originalKeyBytes = null;
        try {
			originalKeyBytes = Files.readAllBytes(Paths.get("KeyManagement/Shared/SymmetricKey/SecretKey"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Convert it from byte array back to Key 
        SecretKey originalKey = new SecretKeySpec(originalKeyBytes, "AES");  // Use "AES" or whatever algorithm you are using
        System.out.println("Secret key retrieved (Key): " + originalKey);
        String originalKeyString = Base64.getEncoder().encodeToString(originalKeyBytes);	
		System.out.println("Secret key retrieved (String): " + originalKeyString);
	}
}
