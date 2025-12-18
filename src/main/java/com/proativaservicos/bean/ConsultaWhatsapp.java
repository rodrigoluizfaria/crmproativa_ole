package com.proativaservicos.bean;


import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.ContactWhatsapp;
import com.proativaservicos.model.WhatsappApi;
import com.proativaservicos.util.Morpheus;
import com.proativaservicos.util.PhoneNumberUtil;
import com.proativaservicos.util.VerificarLinkUtil;
import com.proativaservicos.util.constantes.MessagesEnum;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

@ViewScoped
@Named
public class ConsultaWhatsapp extends GenericBean {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String numero;

    private String retorno;

    private Boolean bussnes;

    private boolean valido;


    public void init() {

    }


    public void consultar() {

        try {


            this.retorno = null;
            this.bussnes = null;
            this.valido = false;

            if (StringUtils.isBlank(this.numero)) {
                throw new ProativaException("Por favor informe o numero");
            }
            if (!PhoneNumberUtil.isTelefone(this.numero)) {
                throw new ProativaException("Número informado não é valido.");
            }

            VerificarLinkUtil.verificarLink("http://10.8.0.8:3333/verifyNumber");

            Morpheus morp = new Morpheus();

            ContactWhatsapp contatac = morp.consultarTelefoneWhatsapp("http://10.8.0.8:3333/verifyNumber", this.numero, new WhatsappApi());

            if (contatac == null) {

                this.retorno = "O telefone informado não esta registrado no whatsapp.";
                this.valido = false;

            } else {
                this.valido = true;
                this.retorno = "O telefone informado está registrado no whatsapp.";
                this.bussnes = contatac.getBusiness();


            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }


    /**
     * @return the bussnes
     */
    public Boolean getBussnes() {
        return bussnes;
    }


    /**
     * @param bussnes the bussnes to set
     */
    public void setBussnes(Boolean bussnes) {
        this.bussnes = bussnes;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

}
