package DigitalSignature;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.List;

import Hashing.Hasher;

public class UserSignature {
	private static String ALGORITHM = "SHA256WithRSA";
	private Signature sig;
	
	public UserSignature() {
		try {
			sig = Signature.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public byte[] getSignature(List<String> userIdentity, PrivateKey key) {
		
		byte[] signature = null;
		
		try {
			sig.initSign(key);
			
			// convert list into string
			StringBuilder user = new StringBuilder();
			for (String s: userIdentity) {
				user.append(s);
			}
			
			// convert hash to bytes
			String userHash = Hasher.sha256(user.toString());
			byte[] userIdentityBytes = userHash.getBytes();
			
			// sign
			sig.update(userIdentityBytes);
			signature = sig.sign();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return signature;
	}
	
	public boolean VerifySignature(List<String> userIdentity, byte[] signature, PublicKey key) {
		
		boolean verification = false;
		
		try {
			sig.initVerify(key);
			
			// convert list into string
			StringBuilder user = new StringBuilder();
			for (String s: userIdentity) {
				user.append(s);
			}
			
			// convert hash to bytes
			String userHash = Hasher.sha256(user.toString());
			byte[] userIdentityBytes = userHash.getBytes();
			
			// sign
			sig.update(userIdentityBytes);
			verification = sig.verify(signature);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return verification;
	}
}


