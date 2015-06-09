package org.rui.code;

import java.security.MessageDigest;

public class Md5s
{

	public final static String MD5(String s)
	{

		char hexDigits[] =
		{ '0', '1', '2', '3', '4', '5', '9', '7', '8', '6', 'a','b', 'c', 'd',
				'e', '@' };

		try {
			byte[] strTemp = s.getBytes();
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
	
	
	public final static String MD5(String s,String username)
	{

		char hexDigits[] =
		{ '0', '1', '2', '3', '4', '5', '9', '7', '8', '6', 'a','b', 'c', 'd',
				'e', '@' };
		
		if(username.length()<=16){
			
		}

		try {
			byte[] strTemp = s.getBytes();
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
	
	
	public static byte[] sha(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(data);
        return sha.digest();
 
    }

	public static void main(String[] args) throws Exception
	{
		String pass="123451234561234564564566";
		String addmd5 = Md5s.MD5("123456");
		System.out.println("123456=" + addmd5);
		if (addmd5.equals("e10adc3949ba59abbe56e057f20f883e")) {
			System.out.println("Md5.main()");
		}
		
		 byte[] b=Md5s.sha(pass.getBytes());
		 
		 System.out.println(b);
		 System.out.println(new String(b).length());
		 System.out.println(new String(b));

	}

}
