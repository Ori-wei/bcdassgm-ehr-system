package asymmetric;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class AsymmetricKeyPair {
	
	private static final String ALGORITHM = "RSA";
	
	private KeyPairGenerator keygen;
	private KeyPair keypair;
	private static PublicKey publickey;
	private static PrivateKey privatekey;
	
	public static PublicKey getPublickey() {
		return publickey;
	}
	
	public static PrivateKey getPrivatekey() {
		return privatekey;
	}

	public AsymmetricKeyPair() {
		try {
			keygen = KeyPairGenerator.getInstance(ALGORITHM);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void createKeyPair() {
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