package asymmetric;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class AccessKey {
	
	// retrieve Public Key
	public static PublicKey getPublicKey(String path) throws Exception {
		
		byte[] keyBytes = Files.readAllBytes(Paths.get(path));
		
		//note convert the keyBytes into appropriate key spec
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		
		return KeyFactory.getInstance("RSA").generatePublic(spec);
	}
	
	// retrieve Private Key
	public static PrivateKey getPrivateKey(String path) throws Exception {
		
		byte[] keyBytes = Files.readAllBytes(Paths.get(path));
		
		//note convert the keyBytes into appropriate key spec
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		
		return KeyFactory.getInstance("RSA").generatePrivate(spec);
	}
}
