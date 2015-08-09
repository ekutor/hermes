package com.co.iatech.crm.sugarmovil.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MDS5 {
	
	private static final char[] HEXADECIMAL = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	
	public String md5(String stringToHash){
		
		try{
			System.out.println("stringToHash " + stringToHash);
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] bytes = messageDigest.digest(stringToHash.getBytes());
			StringBuilder stringBuilder = new StringBuilder(2 * bytes.length);
			
			for(int i =0; i<bytes.length;i++){
				int low = (int)(bytes[i] & 0x0f);
				int high = (int)((bytes[i] & 0xf0) >> 4);
				stringBuilder.append(HEXADECIMAL[high]);
				stringBuilder.append(HEXADECIMAL[low]);
			}			
			return stringBuilder.toString();
		}		
		
		catch(NoSuchAlgorithmException exception){			
			return null;			
		}
	}
}
