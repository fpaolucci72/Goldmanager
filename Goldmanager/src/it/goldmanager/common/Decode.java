package it.goldmanager.common;


public class Decode {

	
	public static void main(String[] args) {
		String a = "g0ld";
		CryptManager crypt = new CryptManager("DES");
		String encryptedLogin = crypt.encryptHexString(a);
		System.out.println("encryptedLogin " +encryptedLogin);
		String decryString = crypt.decryptHexString("-1830-5C-7239-8018-343B35-60-4D45-212E29");
		System.out.println("decryptedLogin " + decryString);
	}
}
