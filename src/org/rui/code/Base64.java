package org.rui.code;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Base64 base=new Base64();
		String pass="123";
		String en=base.encode(pass);
		System.out.println(en);
		
		String de=base.decode(en);
		System.out.println(de);

	}
	
	//“Î≥…√‹¬Î
	public String encode(String pass){		
		BASE64Encoder base=new BASE64Encoder();
		pass=base.encode(pass.getBytes());
		return pass;
	}
	
	//
	public String encode(byte [] b){		
		return encode(new String(b));
	}
	
	//Ω‚√‹
	public String decode(String pass) {
		BASE64Decoder base=new BASE64Decoder();
		byte[] b = null;
		try {
			b = base.decodeBuffer(pass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(b);
		
	}

}
