package DigitalSignature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

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
		
		// initialize signature
		try {
			sig.initSign(key);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// sign
		try {
			sig.update(clinicalSummaryRecord.getBytes());
			signature = sig.sign();
		}catch(SignatureException e) {
			e.printStackTrace();
		}
		
		return signature;
	}
	
	public boolean VerifySignature(String clinicalSummaryRecord, byte[] signature, PublicKey key) {
		
		boolean isValid = false;
		
		// initialize
		try {
			sig.initVerify(key);
		}catch(InvalidKeyException e) {
			e.printStackTrace();
		}
		
		// validate
		try {
			sig.update(clinicalSummaryRecord.getBytes());
			isValid = sig.verify(signature);
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isValid;
	}
}


