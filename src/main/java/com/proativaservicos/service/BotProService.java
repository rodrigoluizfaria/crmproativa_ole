package com.proativaservicos.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class BotProService implements Serializable {
	
	private Map<Long, String> messages;
	private int contador;
	
	public void bot(Long id) {
		
		if(messages==null)
			this.messages = new HashMap<Long, String>();
		
		if(messages.containsKey(id)) {
			
			System.out.println("Conteins key");
			
			this.messages.forEach((k,v) ->{
				
				System.out.println(k+" -> "+v);
				
			});
			
			this.messages.put(id, this.messages.get(id)+1);
		
		}else {
			
			System.out.println(" Não conteins key: ");
			this.messages.put(id, "contando: "+contador++);
		}
		
		
	}

}
