package org.rui.code;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		String  pass="1234";
		byte[] b=getDigest(pass.getBytes());		
		//System.out.println("md5"+new String(b));		
		Base64 b64= new Base64();
		pass=b64.encode(b);
		//System.out.println("md5���ܺ���base64:"+pass);	
		
		//db �������ݿ�����Ϊ
		String dbpass="gdyb21LQTT8229gxPtBV";
		
		System.out.println(pass);
		System.out.println(dbpass);
		System.out.println("�����Ƿ�һ��:"+pass.equals(dbpass));
	
		
	}
	
	//MD5����
	public static byte[] getDigest(byte[] b){
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");			
			md.update(b);
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			//FIXME:DDD
			e.printStackTrace();
		}
		
		return null;
	}
	


}
