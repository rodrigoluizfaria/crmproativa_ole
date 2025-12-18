package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.MessagesEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class SupervisaoMonitorProjecaoAtendimento extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date dataInicio;
    private Date dataFim;
    private List<?> listItens;

    @Inject
    private AtendimentoService serviceAtendimento;

    @PostConstruct
    public void init() {
        try {

            inicializarEmpresa(retornarEmpresaUsuarioSessao());
            this.dataInicio = DateUtil.builder().retornarDataPrimeiroDiaMes().getData();
            this.dataFim = DateUtil.builder().retornarDataUltimoDiaMes().getData();

            pesquisar();

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void pesquisar() {

        try {

            if (this.dataInicio.after(this.dataFim))
                throw new ProativaException("Data Inicial de ser menor que a Data final");


            this.listItens = this.serviceAtendimento.pesquisarMonitorProjecaoAtendimento(this.dataInicio, this.dataFim, retornarUsuarioSessao(), getEmpresa());


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public List<?> getListItens() {
        return listItens;
    }

    public void setListItens(List<?> listItens) {
        this.listItens = listItens;
    }

    public AtendimentoService getServiceAtendimento() {
        return serviceAtendimento;
    }

    public void setServiceAtendimento(AtendimentoService serviceAtendimento) {
        this.serviceAtendimento = serviceAtendimento;
    }

    public String getTotalFooter(int value) {

        BigInteger valor = BigInteger.ZERO;

        if (CollectionUtils.isNotEmpty(listItens)) {

            for (Object object : this.listItens) {

                Object[] obj = (Object[]) object;

                if (obj[value] != null) {

                    valor = valor.add((BigInteger) obj[value]);
                }
            }

        } else {

            return "";

        }

        return valor.toString();

    }

}
