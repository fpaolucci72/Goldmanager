package it.goldmanager.common;

public class CryptManager{

	private javax.crypto.spec.SecretKeySpec keySpec;
	private byte[] key = { (byte) 0x01, (byte) 0xE3, (byte) 0xA2, (byte) 0x19, (byte) 0x59, (byte) 0xBD, (byte) 0xEE, (byte) 0xAB };
	private String algorithm;

	public CryptManager(String algorithm){
		this.algorithm = algorithm;
		this.keySpec = new javax.crypto.spec.SecretKeySpec(this.key, this.algorithm);
	}

	public byte[] encryptString(String text) {
		try {
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(this.algorithm);
			cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, this.keySpec);
			return cipher.doFinal(text.getBytes());
		} catch(Exception e) {
			return null;
		}
	}

	public String decryptString(byte[] b) {
		try {
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(this.algorithm);
			cipher.init(javax.crypto.Cipher.DECRYPT_MODE, this.keySpec);
			return new String(cipher.doFinal(b));
		} catch(Exception e) {
			return null;
		}
	}

	public String encryptHexString(String text) {
		return toHex(encryptString(text));
	}

	public String decryptHexString(String text) {
		return decryptString(toByteArray(text));
	}

	private String toHex(byte[] buf) {
		String res = "";
		for(int i = 0; i < buf.length; i++) {
			int b = buf[i];
			if(b < 0) {
				res = res.concat("-");
				b = -b;
			}
			if(b < 16) {
				res = res.concat("0");
			}
			res = res.concat(Integer.toHexString(b).toUpperCase());
		}
		return res;
	}

	private byte[] toByteArray(String hex) {
		java.util.Vector<Byte> res = new java.util.Vector<Byte>();
		String part;
		int pos = 0;
		int len = 0;
		while (pos < hex.length()) {
			len = ((hex.substring(pos, pos + 1).equals("-")) ? 3 : 2);
			part = hex.substring(pos, pos + len);
			pos += len;
			int test = Integer.parseInt(part, 16);
			res.add(new Byte((byte) test));
		}
		if(res.size() > 0) {
			byte[] b = new byte[res.size()];
			for(int i = 0; i < res.size(); i++) {
				Byte a = (Byte) res.elementAt(i);
				b[i] = a.byteValue();
			}
			return b;
		} else {
			return null;
		}
	}
}
