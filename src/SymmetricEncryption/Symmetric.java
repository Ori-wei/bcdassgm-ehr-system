package SymmetricEncryption;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Symmetric {
	private Cipher cipher;
	private static final String ALGORITHM = "AES";
	public Symmetric()
	{
		try {
			cipher = Cipher.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String encrypt(String data, Key key)
	{
		String cipherText = null;
		//init
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//encrypt
		byte[] cipherBytes;
		try {
			cipherBytes = cipher.doFinal(data.getBytes());
			//convert to String
			cipherText = Base64.getEncoder().encodeToString(cipherBytes);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return cipherText;
	}
	public String decrypt(String cipherText, Key key)
	{
		String decryptedText = null;
		//init
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		//convert to byte[]
		byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
		//decrypt
		byte[] dataBytes;
		try {
			dataBytes = cipher.doFinal(cipherBytes);
			decryptedText = new String(dataBytes);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return decryptedText;
	}
	public void makeDirectory(byte[] keybytes, String path) {
		File f = new File(path);
		if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
		try {
			Files.write(Paths.get(path), keybytes, StandardOpenOption.CREATE);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
