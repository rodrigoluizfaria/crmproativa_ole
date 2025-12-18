package com.proativaservicos.model.bancoMaster;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaLimitesPorCpfResponse extends GenericResponse {

    private String cpf;
    private String cdCpf;
    private String nome;
    private String idConvenio;
    private String matricula;
    private double vlMultiploSaque;
    private double limiteUtilizado;
    private double limiteTotal;
    private double limiteDisponivel;
    private int vlLimiteParcela;
    private double limiteParcelaUtilizado;
    private int limiteParcelaDisponivel;
    private double vlMargem;
    private double vlMultiploCompra;
    private String cdBanco;
    private String cdAgencia;
    private String cdConta;
    private boolean naoPerturbe;
    private boolean saqueComplementar;
    private ContratoRefinanciamento contratoRefinanciamento;
    private List<Opcoes> opcoes;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(String idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getVlMultiploSaque() {
        return vlMultiploSaque;
    }

    public void setVlMultiploSaque(double vlMultiploSaque) {
        this.vlMultiploSaque = vlMultiploSaque;
    }

    public double getLimiteUtilizado() {
        return limiteUtilizado;
    }

    public void setLimiteUtilizado(double limiteUtilizado) {
        this.limiteUtilizado = limiteUtilizado;
    }

    public double getLimiteTotal() {
        return limiteTotal;
    }

    public void setLimiteTotal(double limiteTotal) {
        this.limiteTotal = limiteTotal;
    }

    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public void setLimiteDisponivel(double limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }

    public int getVlLimiteParcela() {
        return vlLimiteParcela;
    }

    public void setVlLimiteParcela(int vlLimiteParcela) {
        this.vlLimiteParcela = vlLimiteParcela;
    }

    public double getLimiteParcelaUtilizado() {
        return limiteParcelaUtilizado;
    }

    public void setLimiteParcelaUtilizado(double limiteParcelaUtilizado) {
        this.limiteParcelaUtilizado = limiteParcelaUtilizado;
    }

    public int getLimiteParcelaDisponivel() {
        return limiteParcelaDisponivel;
    }

    public void setLimiteParcelaDisponivel(int limiteParcelaDisponivel) {
        this.limiteParcelaDisponivel = limiteParcelaDisponivel;
    }

    public double getVlMargem() {
        return vlMargem;
    }

    public void setVlMargem(double vlMargem) {
        this.vlMargem = vlMargem;
    }

    public double getVlMultiploCompra() {
        return vlMultiploCompra;
    }

    public void setVlMultiploCompra(double vlMultiploCompra) {
        this.vlMultiploCompra = vlMultiploCompra;
    }

    public String getCdBanco() {
        return cdBanco;
    }

    public void setCdBanco(String cdBanco) {
        this.cdBanco = cdBanco;
    }

    public String getCdAgencia() {
        return cdAgencia;
    }

    public void setCdAgencia(String cdAgencia) {
        this.cdAgencia = cdAgencia;
    }

    public String getCdConta() {
        return cdConta;
    }

    public void setCdConta(String cdConta) {
        this.cdConta = cdConta;
    }

    public boolean isNaoPerturbe() {
        return naoPerturbe;
    }

    public void setNaoPerturbe(boolean naoPerturbe) {
        this.naoPerturbe = naoPerturbe;
    }

    public boolean isSaqueComplementar() {
        return saqueComplementar;
    }

    public void setSaqueComplementar(boolean saqueComplementar) {
        this.saqueComplementar = saqueComplementar;
    }

    public ContratoRefinanciamento getContratoRefinanciamento() {
        return contratoRefinanciamento;
    }

    public void setContratoRefinanciamento(ContratoRefinanciamento contratoRefinanciamento) {
        this.contratoRefinanciamento = contratoRefinanciamento;
    }

    public List<Opcoes> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<Opcoes> opcoes) {
        this.opcoes = opcoes;
    }

    public String getCdCpf() {
        return cdCpf;
    }

    public void setCdCpf(String cdCpf) {
        this.cdCpf = cdCpf;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("cpf", cpf)
                .append("nome", nome)
                .append("idConvenio", idConvenio)
                .append("matricula", matricula)
                .append("vlMultiploSaque", vlMultiploSaque)
                .append("limiteUtilizado", limiteUtilizado)
                .append("limiteTotal", limiteTotal)
                .append("limiteDisponivel", limiteDisponivel)
                .append("vlLimiteParcela", vlLimiteParcela)
                .append("limiteParcelaUtilizado", limiteParcelaUtilizado)
                .append("limiteParcelaDisponivel", limiteParcelaDisponivel)
                .append("vlMargem", vlMargem)
                .append("vlMultiploCompra", vlMultiploCompra)
                .append("cdBanco", cdBanco)
                .append("cdAgencia", cdAgencia)
                .append("cdConta", cdConta)
                .append("naoPerturbe", naoPerturbe)
                .append("saqueComplementar", saqueComplementar)
                .append("contratoRefinanciamento", contratoRefinanciamento)
                .append("opcoes", opcoes)
                .toString();
    }

    public static class ContratoRefinanciamento{
        public boolean refinanciamento;
        public double vlMaximoParcela;
        public double valor;

        public boolean isRefinanciamento() {
            return refinanciamento;
        }

        public void setRefinanciamento(boolean refinanciamento) {
            this.refinanciamento = refinanciamento;
        }

        public double getVlMaximoParcela() {
            return vlMaximoParcela;
        }

        public void setVlMaximoParcela(double vlMaximoParcela) {
            this.vlMaximoParcela = vlMaximoParcela;
        }

        public double getValor() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("refinanciamento", refinanciamento)
                    .append("vlMaximoParcela", vlMaximoParcela)
                    .append("valor", valor)
                    .toString();
        }
    }

    public static class Opcoes{

        public int qtdParcelas;
        public double vlLimite;
        public int vlLimiteParcela;

        public int getQtdParcelas() {
            return qtdParcelas;
        }

        public void setQtdParcelas(int qtdParcelas) {
            this.qtdParcelas = qtdParcelas;
        }

        public double getVlLimite() {
            return vlLimite;
        }

        public void setVlLimite(double vlLimite) {
            this.vlLimite = vlLimite;
        }

        public int getVlLimiteParcela() {
            return vlLimiteParcela;
        }

        public void setVlLimiteParcela(int vlLimiteParcela) {
            this.vlLimiteParcela = vlLimiteParcela;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("qtdParcelas", qtdParcelas)
                    .append("vlLimite", vlLimite)
                    .append("vlLimiteParcela", vlLimiteParcela)
                    .toString();
        }
    }


}
