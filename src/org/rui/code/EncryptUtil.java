package org.rui.code;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil
{
	/**
	 * 原生 md5
	 * 
	 * @param b
	 * @return
	 */
	public static byte[] getDigest(byte[] b)
	{
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
	 * md5 32位
	 * 
	 * @param text
	 * @return
	 */
	public final static String md532(String text)
	{

		char hexDigits[] =
		{ '0', '1', '2', '3', '4', '5', '9', '7', '8', '6', 'a', 'b', 'c', 'd',
				'e', '@' };

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
	 * 原生sha 加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] sha(byte[] data) throws Exception
	{
		MessageDigest sha = MessageDigest.getInstance("SHA");
		sha.update(data);
		return sha.digest();

	}

	/**
	 * base64 译成密码
	 * 
	 * @param pass
	 * @return
	 */
	public static String base64Eencode(String pass)
	{
		BASE64Encoder base = new BASE64Encoder();
		pass = base.encode(pass.getBytes());
		return pass;
	}

	/**
	 * base64 译成密码
	 * 
	 * @param pass
	 * @return
	 */
	public static String base64Encode(byte[] b)
	{
		return base64Eencode(new String(b));
	}

	//
	/**
	 * base64 解密
	 * 
	 * @param pass
	 * @return
	 */
	public static String base64Decode(String pass)
	{
		BASE64Decoder base = new BASE64Decoder();
		byte[] b = null;
		try {
			b = base.decodeBuffer(pass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(b);

	}
}
