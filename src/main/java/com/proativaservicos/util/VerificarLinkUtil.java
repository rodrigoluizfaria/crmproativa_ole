package com.proativaservicos.util;

import java.net.HttpURLConnection;
import java.net.URL;


import com.proativaservicos.exception.ProativaException;

public class VerificarLinkUtil {

	public static void verificarLink(String link) throws ProativaException {

		try {

			URL url = new URL(link);
			HttpURLConnection conectar = (HttpURLConnection) url.openConnection();
			conectar.setRequestMethod("GET");
			conectar.setConnectTimeout(20000);
			conectar.setReadTimeout(20000);
			conectar.connect();

		} catch (Exception e) {

			throw new ProativaException("Link inoperante - "+link);
		}

	}

	public static void main(String[] args) {
		
		boolean online = false;

		do {

			try {

				System.out.println("RETIRNO");
				VerificarLinkUtil.verificarLink("http://10.8.1.252:8003");
				online = true;
				
				
			} catch (ProativaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				online = false;
			}

		} while (!online);

	}

}
