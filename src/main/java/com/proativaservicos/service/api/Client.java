package com.proativaservicos.service.api;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;


@Path("/onPreditivo")
public class Client {
	
	
	@POST()
	@Path("/receberDados")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response receber(String dados) {
		
		System.out.println("Dados recebidos: "+ dados);
				
		
		if(StringUtils.isBlank(dados)) {
			
			return  Response.status(Response.Status.NO_CONTENT).entity("Nenhum dado recebido.").build();
		}
		
		return  Response.status(Response.Status.OK).entity("Dados recebidos.").build();
		
		
	}

}
