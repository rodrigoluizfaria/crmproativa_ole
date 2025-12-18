package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.model.bancoMaster.CartaoResponse;
import com.proativaservicos.service.IntegracaoService;
import com.proativaservicos.util.ApiBancoMasterUtil;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class ConsultaIntegracaoBean extends GenericBean {


    @Inject
    private IntegracaoService serviceIntegracao;

    @Inject
    private ApiBancoMasterUtil apiBancoMasterUtil;

    private IntegracaoWs integracaoApiMaster;

    private CartaoResponse cartaoMasterResponse;

    private String cpf;

    private Usuario usuario;


    @PostConstruct
    public void init() {

        this.usuario = retornarUsuarioSessao();

    }


    public void onConsultarCartaoMaster() {

        try {

            if (!Utils.validaCPF(this.cpf)) {
                throw new ProativaException("CPF invalido");

            }

            this.cartaoMasterResponse = new CartaoResponse();

            this.integracaoApiMaster = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.API_BANCO_MASTER, retornarEmpresaUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            if (this.integracaoApiMaster != null && this.integracaoApiMaster.getId() != null && this.integracaoApiMaster.getTipoIntegracao() != null && this.integracaoApiMaster.getTipoIntegracao().equals(TipoIntegracaoEnum.API_BANCO_MASTER)) {

                this.cartaoMasterResponse = this.apiBancoMasterUtil.consultarLimiteCartao(this.integracaoApiMaster, this.cpf, this.usuario, null, null, true, true);


            } else {

                throw new ProativaException("No momento, não há serviços de integração disponíveis.");
            }


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());

        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public CartaoResponse getCartaoMasterResponse() {
        return cartaoMasterResponse;
    }

    public void setCartaoMasterResponse(CartaoResponse cartaoMasterResponse) {
        this.cartaoMasterResponse = cartaoMasterResponse;
    }
}
