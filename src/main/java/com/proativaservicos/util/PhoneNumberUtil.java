package com.proativaservicos.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PhoneNumberUtil {
	
	  private static List<String> dddValidos = Arrays.asList(new String[] { 
		        "11", "12", "13", "14", "15", "16", "17", "18", "19", "21", 
		        "22", "24", "27", "28", "31", "32", "33", "34", "35", "37", 
		        "38", "41", "42", "43", "44", "45", "46", "47", "48", "49", 
		        "51", "53", "54", "55", "61", "62", "63", "64", "65", "66", 
		        "67", "68", "69", "71", "73", "74", "75", "77", "79", "81", 
		        "82", "83", "84", "85", "86", "87", "88", "89", "91", "92", 
		        "93", "94", "95", "96", "97", "98", "99" });
	  
	  private static List<String> digitosCelularValidos = Arrays.asList(new String[] { "9", "8", "7", "6", "5", "4", "3", "2", "1" });
	
	public static boolean isCelularNumber(String telefone) {
		
		if(StringUtils.isBlank(telefone))
			return false;
			
		String dddTelefone = telefone.replaceAll("\\D+", "").replaceAll(" ", "");
		
		if(dddTelefone.length() != 11)
			return false;
		
		String ddd = telefone.substring(0, 2);

		String numero = telefone.substring(2);
		
		if(!dddValidos.contains(ddd))
			return false;
		
		if(!numero.substring(0, 1).equals("9"))
				return false;
		

		
		if(!digitosCelularValidos.contains(numero.substring(0, 1)))
			return false;
		
		return true;
	}
	public static boolean isTelefone(String telefone) {
		
		if(StringUtils.isBlank(telefone))
			return false;
		
		String dddTelefone = telefone.replaceAll("\\D+", "").replaceAll(" ", "");
		
		if(dddTelefone.length() < 10 || telefone.length() > 11)
			return false;
		
		String ddd = telefone.substring(0, 2);
		
		String numero = telefone.substring(2);
		
		
		if(!dddValidos.contains(ddd))
			return false;
		
		//if(!numero.substring(0, 1).matches("/^[3-9]$/"))
			//	return false;
	

		
		if(!digitosCelularValidos.contains(numero.substring(0, 1)))
			return false;
		
		return true;
	}

	
	public static void main(String[] args) {
		
		System.out.println(isTelefone("3137741719"));
	}
}
