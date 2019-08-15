package com.example.wlplatform.utils;

public class Utils {


	    public static byte[] intToByteArray(int i) {
	        byte[] result = new byte[4];
	        result[0] = (byte)((i >> 24) & 0xFF);
	        result[1] = (byte)((i >> 16) & 0xFF);
	        result[2] = (byte)((i >> 8) & 0xFF);
	        result[3] = (byte)(i & 0xFF);
	        return result;
	    }


	    public static int byteArrayToInt(byte[] bytes) {
	        int value=0;
	        for(int i = 0; i < 4; i++) {
	            int shift= (3-i) * 8;
	            value +=(bytes[i] & 0xFF) << shift;
	        }
	        return value;

	    }
	    
	    public static byte intToByte(int x) { 
	     return (byte) x; 
	    } 
	      
	    public static int byteToInt(byte b) { 
	     return b & 0xFF; 
	    }

}
