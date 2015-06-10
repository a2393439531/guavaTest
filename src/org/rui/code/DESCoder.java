package org.rui.code;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
//import org.apache.commons.codec.binary.Base64;


public class DESCoder {

	/**
	 * 
	 * 生成密钥，java6只支持56位密钥，
	 * 
	 * @return byte[] 二进制密钥
	 * */
	public static byte[] initkey() throws Exception {
		// 实例化密钥生成器
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		// 初始化密钥生成器 java6只支持56位密钥 bouncycastle支持64位密钥
		kg.init(56);
		// 生成密钥
		SecretKey secretKey = kg.generateKey();
		// 获取二进制密钥编码形式
		return secretKey.getEncoded();
	}


	/**
	 * 加密数据
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return byte[] 加密后的数据
	 * */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {

		// 实例化Des密钥
		DESKeySpec dks = new DESKeySpec(key);
		// 实例化密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 生成密钥
		Key k = keyFactory.generateSecret(dks);
		// 还原密钥

		// 实例化 加密/解密算法/工作模式/填充方式
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 解密数据
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密后的数据
	 * */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {

		// 实例化Des密钥
		DESKeySpec dks = new DESKeySpec(key);
		// 实例化密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 生成密钥
		Key k = keyFactory.generateSecret(dks);
		// 还原密钥

		// 实例化 加密/解密算法/工作模式/填充方式
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String str = "123456";
		System.out.println("原文：" + str);
		// 初始化密钥
		byte[] key = DESCoder.initkey();
		System.out.println("key:" + new String(key));
		System.out.println("密钥：" + EncryptUtil.base64Encode(key));
		
		// 加密数据
		byte[] data = DESCoder.encrypt(str.getBytes(), key);
		String save = EncryptUtil.base64Encode(data);
		System.out.println("加密后：" + save);

	
		// 解密数据
		byte[] saveb = EncryptUtil.base64DecodeByte(save);
		
		System.out.println(new String(saveb));
		data = DESCoder.decrypt(saveb, key);
		System.out.println("解密后：" + new String(data));
	}
}
