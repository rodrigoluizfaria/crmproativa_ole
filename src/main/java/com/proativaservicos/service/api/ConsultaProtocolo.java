package com.proativaservicos.service.api;


import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.Cliente;
import com.proativaservicos.model.argus.GenericResponse;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.ClienteService;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.DataEnum;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Path("/consultar")
public class ConsultaProtocolo extends Application {

    @Inject
    private AtendimentoService atendimentoService;


    @Inject
    private ClienteService clienteService;

    @GET()
    @Path("/protocolo/{protocolo}")
    @Produces("application/json")
    public Response buscarProtocolo(@PathParam("protocolo") String protocolo) {

        if (StringUtils.isBlank(protocolo))
            return Response.status(Response.Status.BAD_REQUEST).entity(new GenericResponse("Nenhum protocolo informado.", 0)).build();

        Atendimento atendimento = this.atendimentoService.pesquisarAtendimentoSacPorPrococolo(protocolo);

        if (atendimento == null)
            return Response.status(Response.Status.BAD_REQUEST).entity(new GenericResponse("Protocolo não localizado.", false)).build();

        String nome = atendimento.getCliente() == null ? "" : atendimento.getCliente().getNome();

        if (isEncaminharAtendimentoOuvidoria(atendimento))
            return Response.status(Response.Status.OK).entity(new GenericResponse("Demanda aberta com prazo excedido.", true, atendimento.getCpf(), nome, true)).build();


        return Response.status(Response.Status.OK).entity(new GenericResponse("Sem demanda aberta ou dentro do prazo.", false, atendimento.getCpf(), nome, true)).build();

    }

    @GET()
    @Path("/cpf/{cpf}")
    @Produces("application/json")
    public Response buscarCpf(@PathParam("cpf") String cpf) {

        if (StringUtils.isBlank(cpf))
            return Response.status(Response.Status.BAD_REQUEST).entity(new GenericResponse("Nenhum cpf informado.", 0)).build();

        List<Atendimento> atendimentos = this.atendimentoService.pesquisarAtendimentosSacPorCpf(cpf);

        if (CollectionUtils.isEmpty(atendimentos))
            return Response.status(Response.Status.OK).entity(new GenericResponse("Nenhum atendimento localizado.", false)).build();

        String nome = atendimentos.get(0).getCliente().getNome();

        if (isEncaminharAtendimentoOuvidoria(atendimentos))
            return Response.status(Response.Status.OK).entity(new GenericResponse("Demanda aberta com prazo excedido.", true, cpf, nome, true)).build();


        return Response.status(Response.Status.OK).entity(new GenericResponse("Sem demanda aberta ou dentro do prazo.", false, cpf, nome, true)).build();

    }


    @GET()
    @Path("/telefone/{telefone}")
    @Produces("application/json")
    public Response buscarClientePorTelefone(@PathParam("telefone") String telefone) {

        if (StringUtils.isBlank(telefone))
            return Response.status(Response.Status.BAD_REQUEST).entity(new GenericResponse("Nenhum telefone informado.", 0)).build();

        Cliente cliente = this.clienteService.pesquisarClientePorTelefone(telefone);

        if (cliente == null)
            return Response.status(Response.Status.BAD_REQUEST).entity(new GenericResponse("Nenhum cliente localizado.", false)).build();

        return buscarCpf(cliente.getCpf());

    }


    private boolean isEncaminharAtendimentoOuvidoria(Atendimento atendimento) {

        if (atendimento != null && atendimento.getSubMotivo() != null
                && atendimento.getSubMotivo().getPrazoDemanda() != null
                && atendimento.getDataCadastro() != null && atendimento.getDemandaEncerrada() != null
                && !atendimento.getDemandaEncerrada()) {

            LocalDateTime localDatePrazo = toLocalDateTime(DateUtil.builder(atendimento.getDataCadastro()).adicionarTempoData(DataEnum.DIA, atendimento.getSubMotivo().getPrazoDemanda()).getData());
            LocalDateTime localDateCadastro = LocalDateTime.now();

            return localDatePrazo.isBefore(localDateCadastro);
        }

        return false;
    }

    private boolean isEncaminharAtendimentoOuvidoria(List<Atendimento> atendimentos) {

        if (CollectionUtils.isNotEmpty(atendimentos)) {

            for (Atendimento atendimento : atendimentos) {

                if (isEncaminharAtendimentoOuvidoria(atendimento))
                    return true;

            }

        }


        return false;
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
