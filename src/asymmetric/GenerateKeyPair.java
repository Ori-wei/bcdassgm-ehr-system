package asymmetric;

import java.security.PublicKey;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class GenerateKeyPair {
	public static void main(String[] args) throws Exception{
		
		AsymmetricKeyPair asymmetricKeyPair = new AsymmetricKeyPair();
		// =============================================
		// create key pair
		asymmetricKeyPair.createKeyPair();
		
		byte[] publicKey = asymmetricKeyPair.getPublickey().getEncoded();
		byte[] privateKey = asymmetricKeyPair.getPrivatekey().getEncoded();
		
		AsymmetricKeyPair.makeDirectory(publicKey, "KeyManagement/OriWei/AsymmetricKeyPair/PublicKey");
		AsymmetricKeyPair.makeDirectory(privateKey, "KeyManagement/OriWei/AsymmetricKeyPair/PrivateKey");

		System.out.println("Generated Key Pair:");
		System.out.println("Public key:" + Base64.getEncoder().encodeToString(publicKey));
		System.out.println("Private key:" + Base64.getEncoder().encodeToString(privateKey));
		
		// =============================================
		// retrieve key pair
		PublicKey pubkey = AccessKey.getPublicKey("KeyManagement/OriWei/AsymmetricKeyPair/PublicKey");
		PrivateKey prikey = AccessKey.getPrivateKey("KeyManagement/OriWei/AsymmetricKeyPair/PrivateKey");
		
		String pubKeyString = Base64.getEncoder().encodeToString(pubkey.getEncoded());
		String privKeyString = Base64.getEncoder().encodeToString(prikey.getEncoded());
		
		System.out.println("Retrieved Key Pair:");
		System.out.println("Public Key: " + pubKeyString);
		System.out.println("Public Key: " + privKeyString);
		
		// =============================================
		// test encryption
		AsymmetricEncryption aym = new AsymmetricEncryption();
		String msg = "love you biison!!!";
		
		String encryptMsg = aym.encrypt(msg, pubkey);
		System.out.println("Encrypted:"+encryptMsg);
		
		TimeUnit.SECONDS.sleep(3);
		
		//decrypt it
		String decryptMsg = aym.decrypt(encryptMsg, prikey);
		System.out.println("\n\n> Original Content: "+decryptMsg);
	}
}