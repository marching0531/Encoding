package com.whykeykey.it.util;

public class WKKDebug {

	public static void printHex(String title, byte[] input) {

		String res = WKKHex.encode(input);
		int j = 0;
		
		System.out.println("[" + title + "] " + input.length + " bytes");
		System.out.println("------------------------------------------------");
		for(int i = 1; i<=9; i++) System.out.print("0"+i+":");
		for(int i = 10; i<=16; i++) System.out.print(i+":");
		System.out.println("\n================================================");
		
		for(int i = 0; i<res.length()/2;i++,j++) {
			System.out.print(res.charAt(i*2)+""+res.charAt((i*2)+1)+":");
		
			if(j%15==0 && j != 0) {System.out.println();j=-1;}
		}
		System.out.println("\n------------------------------------------------");
	}
}
