package com.proativaservicos.service.api;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.model.argus.GenericResponse;
import com.proativaservicos.model.argus.OperadorRequest;
import com.proativaservicos.util.ArgusService;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

@Path("/discador")
public class ArgusWb extends Application {


    @Inject
    private ArgusService argusService;


    @POST()
    @Path("/argusFicarDisponivel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response ficarDisponivel(OperadorRequest operador) {

        System.out.println(operador);


        try {

            if (operador == null) {

                return Response.status(Response.Status.OK).entity(new GenericResponse("Nenhum dado foi informado", 0)).build();


            } else if (StringUtils.isBlank(operador.getRamal()) || StringUtils.isBlank(operador.getTokem()) || StringUtils.isBlank(operador.getUrl())) {

                StringBuilder texto = new StringBuilder("Por favor informe ");

                if (StringUtils.isBlank(operador.getRamal()))
                    texto.append("ramal, ");
                if (StringUtils.isBlank(operador.getTokem()))
                    texto.append(" token, ");
                if (StringUtils.isBlank(operador.getUrl()))
                    texto.append(" url, ");

                StringUtils.removeEnd(texto.toString(), ",");

                return Response.status(Response.Status.OK).entity(new GenericResponse(texto.toString(), 0)).build();

            } else {
                IntegracaoWs integracaoWs = new IntegracaoWs(operador.getUrl(), operador.getTokem(), TipoIntegracaoEnum.ARGUS);
                ObjectMapper mapper = new ObjectMapper();
                mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
                mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
                String json = mapper.writeValueAsString(operador);
                System.out.println(json);
                String retorno = this.argusService.ficarDisponivel(integracaoWs, operador.getRamal());
                System.out.println(retorno);

                if (StringUtils.isNotBlank(retorno) && ( retorno.contains("não esta logado.") || retorno.contains(" nÃ£o esta logado") || retorno.contains("âœ… Operador nÃ£o esta logado ") )  ) {
                    System.out.println("Logando operador....");
                    this.argusService.logarOperador(integracaoWs, operador.getRamal());

                }

                return Response.status(Response.Status.OK).entity(new GenericResponse(retorno, 1)).build();

            }


        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Erro interno");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new GenericResponse("Erro interno", 0)).build();

        } catch (ProativaException e) {
            e.printStackTrace();
            System.out.println("Erro Proativa");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new GenericResponse(e.getMessage(), 0)).build();
        }


    }


}
