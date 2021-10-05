package algorithm;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.util.Base64Utils;

import springboot.utils.Commons;

public class TripleDES {

	final static String HARD_KEY = "thiSISatempkey";

	public static String Encrypt(String plainText, String key) {
		try {
			byte[] arrayBytes = getValidKey(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.ENCRYPT_MODE, seckey);
			byte[] plainByte = plainText.getBytes("UTF8");
			byte[] encryptedByte = cipher.doFinal(plainByte);
			
			return Base64.getEncoder().encodeToString(encryptedByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String EncryptHex(String plainText, String key) {
		try {
			byte[] arrayBytes = getValidKey(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.ENCRYPT_MODE, seckey);
			byte[] plainByte = plainText.getBytes("UTF8");
			byte[] encryptedByte = cipher.doFinal(plainByte);
			return HexUtils.toHexString(encryptedByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String Decrypt(String encryptData, String key) {
		try {
			byte[] arrayBytes = getValidKey(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.DECRYPT_MODE, seckey);
			
			byte[] encryptByte = Base64.getDecoder().decode(encryptData);
			byte[] plainByte = cipher.doFinal(encryptByte);
			return new String(plainByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String DecryptHex(String encryptData, String key) {
		try {
			byte[] arrayBytes = getValidKey(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.DECRYPT_MODE, seckey);
			// BASE64Decoder decode = new BASE64Decoder();
			byte[] encryptByte = HexUtils.fromHexString(encryptData);
			byte[] plainByte = cipher.doFinal(encryptByte);
			return new String(plainByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] DecryptHexReturnByte(String encryptData, String key) {
		byte[] plainByte = null;
		try {
			byte[] arrayBytes = getValidKey(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.DECRYPT_MODE, seckey);
			// BASE64Decoder decode = new BASE64Decoder();
			byte[] encryptByte = HexUtils.fromHexString(encryptData);
			plainByte = cipher.doFinal(encryptByte);
			return plainByte;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] getValidKey(String key) {
		try {
			String sTemp = MD5.hash(key).substring(0, 24);
			return sTemp.getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decryptHardKey(String encryptData) {
		return Decrypt(encryptData, HARD_KEY);
	}

	public static String generateHexKey() {
		// Get a key generator for Triple DES (a.k.a DESede)
		KeyGenerator keygen;

		// 676b8a1085cdb3ec20629d5731bf8ff7
		// 7ff7269eb0f44c3bd5eaf8523d49975b385df4abaed54367
		try {
			keygen = KeyGenerator.getInstance("DESede");
			keygen.init(168);
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
			DESedeKeySpec keyspec;
			try {
				keyspec = (DESedeKeySpec) keyfactory.getKeySpec(keygen.generateKey(), DESedeKeySpec.class);
				byte[] rawkey = keyspec.getKey();

				return Commons.bytesToHex(rawkey);
				// return bytesToHex(rawkey);
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		// Use it to generate a key
	}

	public static String generateBase64StringKey() {
		// Get a key generator for Triple DES (a.k.a DESede)
		KeyGenerator keygen;

		// 676b8a1085cdb3ec20629d5731bf8ff7
		// 7ff7269eb0f44c3bd5eaf8523d49975b385df4abaed54367
		try {
			keygen = KeyGenerator.getInstance("DESede");
			keygen.init(168);
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
			DESedeKeySpec keyspec;
			try {
				keyspec = (DESedeKeySpec) keyfactory.getKeySpec(keygen.generateKey(), DESedeKeySpec.class);
				byte[] rawkey = keyspec.getKey();

				return Base64Utils.encodeToString(rawkey);
				// return bytesToHex(rawkey);
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		// Use it to generate a key
	}

	public static void main(String[] args) {
		// System.out.println("KEY HEX:" + En);
		System.out.println(Encrypt("http://103.68.241.71:8084/service?", HARD_KEY));
		System.out.println(Encrypt("http://103.68.241.71:8084/service/query?", HARD_KEY));

		System.out.println(generateBase64StringKey());

		System.out.println(
				Decrypt("54487a642f447472585871514d37556e7934727654566b516b753333375967334b636641313746654c4a346b7342752f6841536f36575a624d352f76703579416450646279354141694b504532366e706f304e513556464775376744494e7961",
						"AUA7ELUy6bW5V09SWOU3yHpMQ0X7/Xqe"));
	}
}