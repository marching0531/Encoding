package com.whykeykey.it.util;

public class Hex {

	private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	public static String encode(byte[] input) {
		
		StringBuilder res = new StringBuilder();
		int len = input.length;

		for (int i = 0; i < len; i++) {
			res.append(HEX[(0xF0 & input[i]) >>> 4]);
			res.append(HEX[(0x0F & input[i])]);
		}

		return res.toString();
	}
	
	public static byte[] decode(String input) {

		int len = input.length();

		byte[] res = new byte[len / 2];
	
		for (int i = 0; i < len; i += 2) {
			
			int ub = Character.digit(input.charAt(i), 16);
			int lb = Character.digit(input.charAt(i + 1), 16);
	
			res[i / 2] = (byte) ((ub << 4) | lb);
		}
		
		return res;
	}
}
