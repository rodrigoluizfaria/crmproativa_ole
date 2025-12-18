package com.proativaservicos.bean;

import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.PontoAtendimentoService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.Util;
import com.proativaservicos.util.Utils;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;

@Named
@ViewScoped
public class DiscadorParam extends GenericBean {

    private Usuario usuario;

    private String ramal;

    private String codCliente;

    private String telefone;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private AtendimentoService atendimentoService;

    @Inject
    private PontoAtendimentoService pontoAtendimentoService;

    @PostConstruct
    public void init() {

        this.usuario = retornarUsuarioSessao();

        this.ramal = Faces.getRequestParameter("ramal");
        this.codCliente = Faces.getRequestParameter("codCliente");
        this.telefone = Faces.getRequestParameter("telefone");

        System.out.println("Ramal: " + this.ramal);
        System.out.println("Cod cliente: " + this.codCliente);
        System.out.println("Telefone: " + this.telefone);
      //  this.usuario.setPontoAtendimento(this.pontoAtendimentoService.pesquisarPontoAtendimentosPorUsuario(this.usuario.getId()));

        if (this.usuario.getPontoAtendimento() != null)
            System.out.println("Ponto Atendimento " + this.usuario.getPontoAtendimento().getRamal());

        System.out.println(this.usuario.getPontoAtendimento().getRamal());

        Atendimento atendimento = null;

        if (StringUtils.isNotBlank(this.codCliente) && Utils.isInteiro(this.codCliente))
            atendimento = this.atendimentoService.pesquisarAtendimentoPorId(Long.parseLong(this.codCliente));

        else
            System.out.println("Codigo cliente invalido...");


        if (atendimento != null)
            System.out.println("Nome CLiente: " + atendimento.getNome());

    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
