package com.proativaservicos.model.bancoMaster;

import com.proativaservicos.util.Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.text.DecimalFormat;

public class ConsultaLimiteCartaoResponse {


    private String cpf;
    private String statusCartao;
    private String ultimos4Digitos;
    private String limiteDisponivel;
    private String produto;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getStatusCartao() {
        return statusCartao;
    }

    public void setStatusCartao(String statusCartao) {
        this.statusCartao = statusCartao;
    }

    public String getUltimos4Digitos() {
        return ultimos4Digitos;
    }

    public void setUltimos4Digitos(String ultimos4Digitos) {
        this.ultimos4Digitos = ultimos4Digitos;
    }

    public String getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public String getLimiteDisponivelFormatado() {

        if (StringUtils.isNotBlank(limiteDisponivel)) {
            return Utils.acertarCodificacaoString(limiteDisponivel);

        }

        return limiteDisponivel;
    }

    public String getLimitePercentualDisponivelFormatado() {
        try {


            if (StringUtils.isNotBlank(limiteDisponivel) && Utils.ehNumero(limiteDisponivel)) {
                DecimalFormat df = new DecimalFormat("#.00");

                return df.format(Double.parseDouble(limiteDisponivel) * 0.60);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "0.0";
    }

    public String getLimitePercentualDisponivel() {
        try {


            if (StringUtils.isNotBlank(limiteDisponivel) && Utils.ehNumero(limiteDisponivel)) {
                DecimalFormat df = new DecimalFormat("#.00");

                return df.format(Double.parseDouble(limiteDisponivel) * 0.60).replaceAll(",", ".");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.0";

    }

    public double getDoubleLimitePercentualDisponivel() {
        try {

            if (StringUtils.isNotBlank(limiteDisponivel) && Utils.ehNumero(limiteDisponivel)) {

                return Double.parseDouble(limiteDisponivel) * 0.60;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0D;

    }

    public Double getDoubleLimiteTotalDisponivel() {
        try {

            if (StringUtils.isNotBlank(limiteDisponivel) && Utils.ehNumero(limiteDisponivel)) {

                return Double.parseDouble(limiteDisponivel);

            }

        } catch (Exception e) {
            return null;
        }

        return null;

    }

    public boolean isNotNumberLimiteDisponivel() {

        if (StringUtils.isNotBlank(limiteDisponivel)) {

            try {
                Double.parseDouble(limiteDisponivel);
                return false;

            } catch (Exception e) {
                return true;
            }


        }

        return true;

    }

    public void setLimiteDisponivel(String limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("cpf", cpf)
                .append("statusCartao", statusCartao)
                .append("ultimos4Digitos", ultimos4Digitos)
                .append("limiteDisponivel", limiteDisponivel)
                .append("produto", produto)
                .append("Limite disponivel", getLimitePercentualDisponivelFormatado())
                .toString();
    }

    public static void main(String[] args) {
        ConsultaLimiteCartaoResponse resp = new ConsultaLimiteCartaoResponse();
        resp.setCpf("123.456.789");
        resp.setLimiteDisponivel("");
        System.out.println(resp.isNotNumberLimiteDisponivel());
    }
}
