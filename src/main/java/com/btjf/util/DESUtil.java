package com.btjf.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESUtil {

	//解密数据     
	public static String decrypt(String message,String key) throws Exception {     
	        key = key.substring(0,8);
	        byte[] bytesrc =convertHexString(message);        
	        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");         
	        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));        
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");        
	        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);        
	        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));     
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);           
	        byte[] retByte = cipher.doFinal(bytesrc);  
			String result = getFromBASE64(new String(retByte) );
			result = java.net.URLDecoder.decode(result, "UTF-8");
	        return result;
	}    

	//加密数据
	public static String encrypt(String message, String key) throws Exception {
		key= key.substring(0,8);
		message =java.net.URLEncoder.encode(message, "utf-8");
		message = getBASE64(message);
		String result = null;
	    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
	    DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));    
	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");     
	    SecretKey secretKey = keyFactory.generateSecret(desKeySpec);     
	    IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));     
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);     
		result = toHexString(cipher.doFinal(message.getBytes("UTF-8")))
				.toUpperCase();
		return result;
	}    

	public static byte[] convertHexString(String ss) {
	byte digest[] = new byte[ss.length() / 2];      
		for (int i = 0; i < digest.length; i++) {
	String byteString = ss.substring(2 * i, 2 * i + 2);      
	int byteValue = Integer.parseInt(byteString, 16);      
	digest[i] = (byte)byteValue;      
	}      
	return digest;      
	}     

	public static String toHexString(byte b[]) {     
	    StringBuffer hexString = new StringBuffer();     
	    for (int i = 0; i < b.length; i++) {     
	        String plainText = Integer.toHexString(0xff & b[i]);     
	        if (plainText.length() < 2)     
	            plainText = "0" + plainText;     
	        hexString.append(plainText);     
	    }        
	    return hexString.toString();     
	}     

	public static String getBASE64(String s) { 
		if (s == null) {
			return null;
		}
		return (new sun.misc.BASE64Encoder()).encode( s.getBytes() ); 
	} 

	public static String getFromBASE64(String s) { 
		if (s == null) {
			return null;
		}
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder(); 
		try { 
		byte[] b = decoder.decodeBuffer(s); 
		return new String(b); 
		} catch (Exception e) { 
		return null; 
		} 
	} 
}
