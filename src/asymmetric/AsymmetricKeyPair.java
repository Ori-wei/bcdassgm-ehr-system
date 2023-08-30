package asymmetric;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

public class AsymmetricKeyPair {
	
	private static final String ALGORITHM = "RSA";
	
	private KeyPairGenerator keygen;
	private KeyPair keypair;
	private PublicKey publickey;
	private PrivateKey privatekey;
	
	public PublicKey getPublickey() {
		return publickey;
	}
	
	public PrivateKey getPrivatekey() {
		return privatekey;
	}

	public AsymmetricKeyPair() {
		try {
	        keygen = KeyPairGenerator.getInstance(ALGORITHM);
	        SecureRandom random = new SecureRandom(); // Initialize a secure random number generator
	        keygen.initialize(2048, random); // Initialize the key generator with the bit-size and the random number generator
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void createKeyPair() {
		AsymmetricKeyPair keyMaker = new AsymmetricKeyPair();
		
		//generate key pair
		keyMaker.keypair = keyMaker.keygen.generateKeyPair();
		
		//get public key
		publickey = keyMaker.keypair.getPublic();
		
		//get private key
		privatekey = keyMaker.keypair.getPrivate();
	}
	
	public static void makeDirectory(byte[] keybytes, String path) {
		File f = new File(path);
		f.getParentFile().mkdirs();
		try {
			Files.write(Paths.get(path), keybytes, StandardOpenOption.CREATE);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}