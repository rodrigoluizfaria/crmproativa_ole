package com.proativaservicos.util;

public class ColorUtil {
	
	
	
	public static String getColorDinamic() {
		
		 String r = String.valueOf(Math.round(Math.random() * 255));
		 String g = String.valueOf(Math.round(Math.random() * 255));
		 String b = String.valueOf(Math.round(Math.random() * 255));
         return "rgb(" + r + "," + g + "," + b + ")";
		
	}
	
	public static void main(String[] args) {
		System.out.println(ColorUtil.getColorDinamic());
	}

}
