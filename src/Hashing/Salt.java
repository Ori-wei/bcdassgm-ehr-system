package Hashing;

import java.security.SecureRandom;

	public class Salt 
	{
    /* generate salt */
		public static byte[] generate() {
        SecureRandom sr = new SecureRandom();
        byte[] b = new byte[16];
        
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) sr.nextInt(16);  // Limiting to 16 to get only 0-15 (0x0 to 0xF)
        }
        
        return b;
    }
}
