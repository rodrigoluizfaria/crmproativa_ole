package com.proativaservicos.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.Duration;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.proativaservicos.util.constantes.DadosBaseImportacaoEnum;
import org.json.JSONObject;

import javax.swing.text.MaskFormatter;

public class Util {

	public static final String SENHA_INICIAL = "123456";
	
	
	public static String encrypt(String senha) {

		MessageDigest mDigest;
		try {

			mDigest = MessageDigest.getInstance("MD5");

			byte[] valorMD5 = mDigest.digest(senha.getBytes("UTF-8"));

			StringBuffer sb = new StringBuffer();

			for (byte b : valorMD5) {

				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return null;
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
			return null;
			
		}

	}

	public static String formatString(String value, String pattern) {
		MaskFormatter mf;
		try {
			mf = new MaskFormatter(pattern);
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(value);
		} catch (ParseException ex) {
			return value;
		}
	}

	public static String gerarSenha() {
		
		int qtdeMaximaCaracteres = 8;
		
		String[] caracteres = { "0", "1", "b", "2", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

		StringBuilder senha = new StringBuilder();

		for (int i = 0; i < qtdeMaximaCaracteres; i++) {
			
			int posicao = (int) (Math.random() * caracteres.length);
			senha.append(caracteres[posicao]);
		}
		
		return senha.toString().toUpperCase();

	}
	
	public static Map<Integer, DadosBaseImportacaoEnum> ordenarMap(Map<Integer, DadosBaseImportacaoEnum> map){
		
		
		if(map.isEmpty() || map ==null) {
			
			return null;
			
		}else {
			
			Map<Integer, DadosBaseImportacaoEnum> sortedMap = new TreeMap<Integer, DadosBaseImportacaoEnum>(map);
			
			return sortedMap;
			
		}
		
		
		
	}
	
	public static String converterSegundosToHorasMinutosSegundos(Long segundos) {
		
		try {
			
			if(segundos==null)
				return null;
			
			Long segundo = segundos % 60;
			Long minutos = segundos / 60;
			Long minuto = minutos % 60;
			Long hora = minutos / 60;
			
			return String.format ("%02d:%02d:%02d", hora, minuto, segundo);
			
			
			
			
			
		}catch (Exception e) {
			return null;
		}
		
	}

	public static boolean isValidJSON(String str) {

		try {
			JSONObject objectMapper = new JSONObject(str);// Tenta parsear a string
			return true;  // Se não lançar exceção, é um JSON válido
		} catch (Exception e) {
			return false;  // Se lançar exceção, não é um JSON válido
		}
	}

	public static void main(String[] args) {
		String [] ho ="00:08:06".split(":");

		System.out.println(isValidJSON("ii"));
		
		

		
	}
	

}
