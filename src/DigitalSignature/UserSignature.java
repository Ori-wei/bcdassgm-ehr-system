package DigitalSignature;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
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
	
	public byte[] getSignature(String clinicalSummaryRecord, PrivateKey key) {
		
		byte[] signature = null;
		
		try {
			sig.initSign(key);
			
			// convert hash to bytes
			String recordHash = Hasher.sha256(clinicalSummaryRecord);
			byte[] recordBytes = recordHash.getBytes();
			
			// sign
			sig.update(recordBytes);
			signature = sig.sign();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return signature;
	}
	
	public boolean VerifySignature(String clinicalSummaryRecord, byte[] signature, PublicKey key) {
		
		boolean verification = false;
		
		try {
			sig.initVerify(key);
			
			// convert hash to bytes
			String recordHash = Hasher.sha256(clinicalSummaryRecord);
			byte[] recordBytes = recordHash.getBytes();
			
			// sign
			sig.update(recordBytes);
			signature = sig.sign();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return verification;
	}
}


