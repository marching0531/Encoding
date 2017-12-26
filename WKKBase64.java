package com.whykeykey.it.util;

import java.io.ByteArrayOutputStream;

public class WKKBase64 {

	private final static char[] ENCODE_TBL = {
	         'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
	         'Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f',
	         'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
	         'w','x','y','z','0','1','2','3','4','5','6','7','8','9','+','/'
	         };
	
	private final static int[] DECODE_TBL = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
            55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2,
            3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30,
            31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
            48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 
            };
	
	public static String encode(byte[] input) {

		int pad = 0;
		StringBuilder res = new StringBuilder();
            
		for (int i = 0; i < input.length; i += 3) {
                
			int b = ((input[i] & 0xFF) << 16) & 0xFFFFFF;
			
			if (i + 1 < input.length) {
				
				b |= (input[i+1] & 0xFF) << 8;
			} else {
                    
				pad++;
			}
                
			if (i + 2 < input.length) {
                    
				b |= (input[i+2] & 0xFF);
			} else {
                    
				pad++;
			}
                
			for (int j = 0; j < 4 - pad; j++) {
                	
				int c = (b & 0xFC0000) >> 18;
                    
				res.append(ENCODE_TBL[c]);
          		b <<= 6;
			}
           
		}
            
            for (int j = 0; j < pad; j++) {
                
            	res.append("=");
            }

            return res.toString();
	   }
		
		public static byte[] decode(String input) {
			
	            byte[] bytes = input.getBytes();
	            ByteArrayOutputStream res = new ByteArrayOutputStream();
	        
	            for (int i = 0; i < bytes.length; ) {
	            
	            	int b = 0;
	                
	            	if (DECODE_TBL[bytes[i]] != -1) {
	                    b = (DECODE_TBL[bytes[i]] & 0xFF) << 18;
	                }
	                else {
	                    i++;
	                    continue;
	                }

	                int num = 0;
	                
	                if (i + 1 < bytes.length && DECODE_TBL[bytes[i+1]] != -1) {
	                    b = b | ((DECODE_TBL[bytes[i+1]] & 0xFF) << 12);
	                    num++;
	                }
	                
	                if (i + 2 < bytes.length && DECODE_TBL[bytes[i+2]] != -1) {
	                    b = b | ((DECODE_TBL[bytes[i+2]] & 0xFF) << 6);
	                    num++;
	                }
	                
	                if (i + 3 < bytes.length && DECODE_TBL[bytes[i+3]] != -1) {
	                    b = b | (DECODE_TBL[bytes[i+3]] & 0xFF);
	                    num++;
	                }

	                while (num > 0) {
	                
	                	int c = (b & 0xFF0000) >> 16;
	                    
	                    res.write((char)c);
	                    b <<= 8;
	                    num--;
	                }
	                
	                i += 4;
	            }
	            return res.toByteArray();
	        }
		
}
			