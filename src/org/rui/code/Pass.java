package org.rui.code;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Pass
{
public static void main(String[] args) throws NoSuchAlgorithmException
{
	String pass="123456";
	MessageDigest md5 = MessageDigest.getInstance("MD5");  
    md5.update(pass.getBytes());  
    byte[] b= md5.digest();  
    
    String pass2="123456";
    md5.update(pass2.getBytes());  
    byte[] b2= md5.digest();  
    
    System.out.println(b);
    System.out.println(new String(b));
    
    System.out.println(b.equals(b2));
}
}
