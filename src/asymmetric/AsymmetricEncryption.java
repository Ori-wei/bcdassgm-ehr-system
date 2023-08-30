package asymmetric;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;

public class AsymmetricEncryption {
	private Cipher cipher;

	public AsymmetricEncryption() {
        this("RSA");
	}
	
	private AsymmetricEncryption(String algorithm) {
		try {
            cipher = Cipher.getInstance(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public String encrypt(String data, PublicKey publicKey) {
		String cipherText = null;
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			//encrypt into bytes
			byte[] cipherBytes = cipher.doFinal(data.getBytes());
			//convert to String
			cipherText = Base64.getEncoder().encodeToString(cipherBytes);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return cipherText;
	}
	
	public String decrypt(String cipherText, PrivateKey privateKey) {
		String decryptedText = null;
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			//encrypt into bytes
			byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
			//decrypt
			byte[] dataBytes = cipher.doFinal(cipherBytes);
			decryptedText = new String(dataBytes);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//convert to String
		return decryptedText;
	}
}
