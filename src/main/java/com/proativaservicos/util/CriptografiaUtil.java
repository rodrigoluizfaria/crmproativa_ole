package com.proativaservicos.util;


import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class CriptografiaUtil {

//	public static String criptografarSHA256(String valor) { return org.jboss.security.auth.spi.Util.createPasswordHash("SHA-256", "BASE64", null, null, valor); }

	public static String criptografarSHA256(String valor) {

			return getSHA1SecurePassword(valor);
			
	}

	public static boolean validarSenha(String senha, String senhaHash) {

		return org.springframework.security.crypto.bcrypt.BCrypt.checkpw(senha, senhaHash);

	}

	private static String getSHA1SecurePassword(String passwordToHash) {

		String generatedPassword = null;
		String sal = BCrypt.gensalt();
		generatedPassword = BCrypt.hashpw(passwordToHash, sal);
		return generatedPassword;
	}

	public static String descriptografarSimples(String valor) {

		BasicTextEncryptor bte = new BasicTextEncryptor();
		bte.setPassword("janela");
		return bte.decrypt(valor);

	}

	public static String criptografarSimples(String valor) {

		BasicTextEncryptor bte = new BasicTextEncryptor();
		bte.setPassword("janela");
		return bte.encrypt(valor);
	}

	public static String encodeBase64(String valor) {
	
		String encode = null;
		
		org.apache.commons.codec.binary.Base64  base = new org.apache.commons.codec.binary.Base64();
		
		
		if(StringUtils.isNotBlank(valor)) {
			
			try {
				
			 encode = new String(base.encode(valor.getBytes()));
			
			}catch (Exception e) {
				
				System.out.println("Erro ao codificar.");
			}
			
		}
		
		return encode;
	}
	
	public static String decodeBase64(String valor) {
		
		String decode = null;
		org.apache.commons.codec.binary.Base64 base = new org.apache.commons.codec.binary.Base64();
				
		if(StringUtils.isNotBlank(valor)) {
			
			try {
			 decode = new String(base.decode(valor.getBytes()));
			
			}catch (Exception e) {
				
				System.out.println("Erro ao codificar.");
			}
			
		}
		
		return decode;
	}
	
	public static void main(String[] args) {
		
		 System.out.println(decodeBase64("bDc0Y2JmYWUxMGNkZGM0MGZlOWQ3NDkwZWM1OGNjZjE0YTo4ZTM2YTEzMjJkMzU0ODE5YmIyZjQ1ODUzMzk0ZTgyZQ=="));
	//	System.out.println(criptografarSHA256("adminproativa"));
		System.out.println(criptografarSimples("51"));
		//System.out.println(descriptografarSimples("AFquQsERQyv9AS5Idh/nvQ=="));

	//	System.out.println(validarSenha("adminproativas", "$2a$10$5A8Qg2yj2kXogH9I.vc47.NURt3CMGFJlnC6IJ2mYd3m6Xo73ya4i"));

	}

}
