package com.proativaservicos.bean;

import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.util.constantes.MessagesEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class SupervisorMonitorAtendimentoBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AtendimentoService serviceAtendimentos;

    private List<?> listAtendimentos;

    @PostConstruct
    public void init() {
        pesquisar();

    }

    public void pesquisar() {

        try {

            this.listAtendimentos = serviceAtendimentos.pesquisarMonitoramentoAtendimentos(retornarEmpresaUsuarioSessao().getId());

        } catch (Exception e) {
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public List<?> getListAtendimentos() {
        return listAtendimentos;
    }

    public void setListAtendimentos(List<?> listAtendimentos) {
        this.listAtendimentos = listAtendimentos;
    }

}
