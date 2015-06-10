package org.rui.code;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil {
	/**
	 * ԭ�� md5
	 * 
	 * @param b
	 * @return
	 */
	public static byte[] getDigest(byte[] b) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(b);
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			// FIXME:DDD
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * md5 32λ
	 * 
	 * @param text
	 * @return
	 */
	public final static String md532(String text) {

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '9', '7', '8', '6',
				'a', 'b', 'c', 'd', 'e', '@' };

		try {
			byte[] strTemp = text.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];

			}
			return new String(str);

		} catch (Exception e) {
			return null;

		}

	}

	/**
	 * ԭ��sha ����
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] sha(byte[] data) throws Exception {
		MessageDigest sha = MessageDigest.getInstance("SHA");
		sha.update(data);
		return sha.digest();

	}

	/**
	 * base64 �������
	 * 
	 * @param pass
	 * @return
	 */
	public static String base64Encode(String pass) {
		BASE64Encoder base = new BASE64Encoder();
		pass = base.encode(pass.getBytes());
		return pass;
	}

	/**
	 * base64 �������
	 * 
	 * @param pass
	 * @return
	 */
	public static String base64Encode(byte[] b) {
		BASE64Encoder base = new BASE64Encoder();
		return base.encode(b);
	}

	//
	/**
	 * base64 ����
	 * 
	 * @param pass
	 * @return
	 */
	public static String base64Decode(String pass) {
		BASE64Decoder base = new BASE64Decoder();
		byte[] b = null;
		try {
			b = base.decodeBuffer(pass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(b);

	}

	public static String base64Decode(byte[] b) {
		return base64Decode(new String(b));
	}

	/**
	 * str decodey to byte
	 * 
	 * @param src
	 * @return
	 */
	public static byte[] base64DecodeByte(String src) {
		BASE64Decoder base = new BASE64Decoder();
		byte[] b = null;
		try {
			b = base.decodeBuffer(src);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}
}
