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
		//System.out.println("md5加密后再base64:"+pass);	
		
		//db 假如数据库密码为
		String dbpass="gdyb21LQTT8229gxPtBV";
		
		System.out.println(pass);
		System.out.println(dbpass);
		System.out.println("密码是否一致:"+pass.equals(dbpass));
	
		
	}
	
	//MD5加密
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
