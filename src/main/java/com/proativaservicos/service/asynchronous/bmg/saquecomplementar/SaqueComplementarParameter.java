/**
 * SaqueComplementarParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class SaqueComplementarParameter  extends DadosCartaoParameter  implements java.io.Serializable {
    private Integer aberturaContaPagamento;

    private AgenciaParameter agencia;

    private AgregacaoMargemParameter agregacaoMargem;

    private BancoParameter banco;

    private int bancoOrdemPagamento;

    private BoletoParameter[] boletoPagamento;

    private TelefoneParameter celular1;

    private String codigoFormaEnvioTermo;

    private Integer codigoLoja;

    private Integer codigoRejeicaoContaSimples;

    private Integer codigoSituacaoServidor;

    private ContaParameter conta;

    private String cpfAgente;

    private String email;

    private int finalidadeCredito;

    private int formaCredito;

    private EletroParameter[] listaEletro;

    private Integer numeroExterno;

    private Integer numeroParcelas;

    private Long protocoloMultiProdutos;

    private Seguros[] seguros;

    private TelefoneParameter telefoneFixoOuCelular;

    private String token;

    private Double valorParcela;

    private Double valorSaque;

    public SaqueComplementarParameter() {
    }

    public SaqueComplementarParameter(
           String login,
           String senha,
           String loginConsig,
           int codigoEntidade,
           String cpf,
           String senhaConsig,
           Integer sequencialOrgao,
           Boolean agregacaoDeMargemParaSaqueComplementar,
           boolean cpfImpedidoComissionar,
           DadosLimiteDeSaque dadosLimiteDeSaqueComplementar,
           String ipUsuario,
           String matricula,
           String matriculaInstituidor,
           Long numeroContaInterna,
           String script,
           int tipoSaque,
           Double valorAgregacaoDeMargemParaSaqueComplementar,
           String vinculoMatricula,
           Integer aberturaContaPagamento,
           AgenciaParameter agencia,
           AgregacaoMargemParameter agregacaoMargem,
           BancoParameter banco,
           int bancoOrdemPagamento,
           BoletoParameter[] boletoPagamento,
           TelefoneParameter celular1,
           String codigoFormaEnvioTermo,
           Integer codigoLoja,
           Integer codigoRejeicaoContaSimples,
           Integer codigoSituacaoServidor,
           ContaParameter conta,
           String cpfAgente,
           String email,
           int finalidadeCredito,
           int formaCredito,
           EletroParameter[] listaEletro,
           Integer numeroExterno,
           Integer numeroParcelas,
           Long protocoloMultiProdutos,
           Seguros[] seguros,
           TelefoneParameter telefoneFixoOuCelular,
           String token,
           Double valorParcela,
           Double valorSaque) {
        super(
            login,
            senha,
            loginConsig,
            codigoEntidade,
            cpf,
            senhaConsig,
            sequencialOrgao,
            agregacaoDeMargemParaSaqueComplementar,
            cpfImpedidoComissionar,
            dadosLimiteDeSaqueComplementar,
            ipUsuario,
            matricula,
            matriculaInstituidor,
            numeroContaInterna,
            script,
            tipoSaque,
            valorAgregacaoDeMargemParaSaqueComplementar,
            vinculoMatricula);
        this.aberturaContaPagamento = aberturaContaPagamento;
        this.agencia = agencia;
        this.agregacaoMargem = agregacaoMargem;
        this.banco = banco;
        this.bancoOrdemPagamento = bancoOrdemPagamento;
        this.boletoPagamento = boletoPagamento;
        this.celular1 = celular1;
        this.codigoFormaEnvioTermo = codigoFormaEnvioTermo;
        this.codigoLoja = codigoLoja;
        this.codigoRejeicaoContaSimples = codigoRejeicaoContaSimples;
        this.codigoSituacaoServidor = codigoSituacaoServidor;
        this.conta = conta;
        this.cpfAgente = cpfAgente;
        this.email = email;
        this.finalidadeCredito = finalidadeCredito;
        this.formaCredito = formaCredito;
        this.listaEletro = listaEletro;
        this.numeroExterno = numeroExterno;
        this.numeroParcelas = numeroParcelas;
        this.protocoloMultiProdutos = protocoloMultiProdutos;
        this.seguros = seguros;
        this.telefoneFixoOuCelular = telefoneFixoOuCelular;
        this.token = token;
        this.valorParcela = valorParcela;
        this.valorSaque = valorSaque;
    }


    /**
     * Gets the aberturaContaPagamento value for this SaqueComplementarParameter.
     * 
     * @return aberturaContaPagamento
     */
    public Integer getAberturaContaPagamento() {
        return aberturaContaPagamento;
    }


    /**
     * Sets the aberturaContaPagamento value for this SaqueComplementarParameter.
     * 
     * @param aberturaContaPagamento
     */
    public void setAberturaContaPagamento(Integer aberturaContaPagamento) {
        this.aberturaContaPagamento = aberturaContaPagamento;
    }


    /**
     * Gets the agencia value for this SaqueComplementarParameter.
     * 
     * @return agencia
     */
    public AgenciaParameter getAgencia() {
        return agencia;
    }


    /**
     * Sets the agencia value for this SaqueComplementarParameter.
     * 
     * @param agencia
     */
    public void setAgencia(AgenciaParameter agencia) {
        this.agencia = agencia;
    }


    /**
     * Gets the agregacaoMargem value for this SaqueComplementarParameter.
     * 
     * @return agregacaoMargem
     */
    public AgregacaoMargemParameter getAgregacaoMargem() {
        return agregacaoMargem;
    }


    /**
     * Sets the agregacaoMargem value for this SaqueComplementarParameter.
     * 
     * @param agregacaoMargem
     */
    public void setAgregacaoMargem(AgregacaoMargemParameter agregacaoMargem) {
        this.agregacaoMargem = agregacaoMargem;
    }


    /**
     * Gets the banco value for this SaqueComplementarParameter.
     * 
     * @return banco
     */
    public BancoParameter getBanco() {
        return banco;
    }


    /**
     * Sets the banco value for this SaqueComplementarParameter.
     * 
     * @param banco
     */
    public void setBanco(BancoParameter banco) {
        this.banco = banco;
    }


    /**
     * Gets the bancoOrdemPagamento value for this SaqueComplementarParameter.
     * 
     * @return bancoOrdemPagamento
     */
    public int getBancoOrdemPagamento() {
        return bancoOrdemPagamento;
    }


    /**
     * Sets the bancoOrdemPagamento value for this SaqueComplementarParameter.
     * 
     * @param bancoOrdemPagamento
     */
    public void setBancoOrdemPagamento(int bancoOrdemPagamento) {
        this.bancoOrdemPagamento = bancoOrdemPagamento;
    }


    /**
     * Gets the boletoPagamento value for this SaqueComplementarParameter.
     * 
     * @return boletoPagamento
     */
    public BoletoParameter[] getBoletoPagamento() {
        return boletoPagamento;
    }


    /**
     * Sets the boletoPagamento value for this SaqueComplementarParameter.
     * 
     * @param boletoPagamento
     */
    public void setBoletoPagamento(BoletoParameter[] boletoPagamento) {
        this.boletoPagamento = boletoPagamento;
    }


    /**
     * Gets the celular1 value for this SaqueComplementarParameter.
     * 
     * @return celular1
     */
    public TelefoneParameter getCelular1() {
        return celular1;
    }


    /**
     * Sets the celular1 value for this SaqueComplementarParameter.
     * 
     * @param celular1
     */
    public void setCelular1(TelefoneParameter celular1) {
        this.celular1 = celular1;
    }


    /**
     * Gets the codigoFormaEnvioTermo value for this SaqueComplementarParameter.
     * 
     * @return codigoFormaEnvioTermo
     */
    public String getCodigoFormaEnvioTermo() {
        return codigoFormaEnvioTermo;
    }


    /**
     * Sets the codigoFormaEnvioTermo value for this SaqueComplementarParameter.
     * 
     * @param codigoFormaEnvioTermo
     */
    public void setCodigoFormaEnvioTermo(String codigoFormaEnvioTermo) {
        this.codigoFormaEnvioTermo = codigoFormaEnvioTermo;
    }


    /**
     * Gets the codigoLoja value for this SaqueComplementarParameter.
     * 
     * @return codigoLoja
     */
    public Integer getCodigoLoja() {
        return codigoLoja;
    }


    /**
     * Sets the codigoLoja value for this SaqueComplementarParameter.
     * 
     * @param codigoLoja
     */
    public void setCodigoLoja(Integer codigoLoja) {
        this.codigoLoja = codigoLoja;
    }


    /**
     * Gets the codigoRejeicaoContaSimples value for this SaqueComplementarParameter.
     * 
     * @return codigoRejeicaoContaSimples
     */
    public Integer getCodigoRejeicaoContaSimples() {
        return codigoRejeicaoContaSimples;
    }


    /**
     * Sets the codigoRejeicaoContaSimples value for this SaqueComplementarParameter.
     * 
     * @param codigoRejeicaoContaSimples
     */
    public void setCodigoRejeicaoContaSimples(Integer codigoRejeicaoContaSimples) {
        this.codigoRejeicaoContaSimples = codigoRejeicaoContaSimples;
    }


    /**
     * Gets the codigoSituacaoServidor value for this SaqueComplementarParameter.
     * 
     * @return codigoSituacaoServidor
     */
    public Integer getCodigoSituacaoServidor() {
        return codigoSituacaoServidor;
    }


    /**
     * Sets the codigoSituacaoServidor value for this SaqueComplementarParameter.
     * 
     * @param codigoSituacaoServidor
     */
    public void setCodigoSituacaoServidor(Integer codigoSituacaoServidor) {
        this.codigoSituacaoServidor = codigoSituacaoServidor;
    }


    /**
     * Gets the conta value for this SaqueComplementarParameter.
     * 
     * @return conta
     */
    public ContaParameter getConta() {
        return conta;
    }


    /**
     * Sets the conta value for this SaqueComplementarParameter.
     * 
     * @param conta
     */
    public void setConta(ContaParameter conta) {
        this.conta = conta;
    }


    /**
     * Gets the cpfAgente value for this SaqueComplementarParameter.
     * 
     * @return cpfAgente
     */
    public String getCpfAgente() {
        return cpfAgente;
    }


    /**
     * Sets the cpfAgente value for this SaqueComplementarParameter.
     * 
     * @param cpfAgente
     */
    public void setCpfAgente(String cpfAgente) {
        this.cpfAgente = cpfAgente;
    }


    /**
     * Gets the email value for this SaqueComplementarParameter.
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this SaqueComplementarParameter.
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Gets the finalidadeCredito value for this SaqueComplementarParameter.
     * 
     * @return finalidadeCredito
     */
    public int getFinalidadeCredito() {
        return finalidadeCredito;
    }


    /**
     * Sets the finalidadeCredito value for this SaqueComplementarParameter.
     * 
     * @param finalidadeCredito
     */
    public void setFinalidadeCredito(int finalidadeCredito) {
        this.finalidadeCredito = finalidadeCredito;
    }


    /**
     * Gets the formaCredito value for this SaqueComplementarParameter.
     * 
     * @return formaCredito
     */
    public int getFormaCredito() {
        return formaCredito;
    }


    /**
     * Sets the formaCredito value for this SaqueComplementarParameter.
     * 
     * @param formaCredito
     */
    public void setFormaCredito(int formaCredito) {
        this.formaCredito = formaCredito;
    }


    /**
     * Gets the listaEletro value for this SaqueComplementarParameter.
     * 
     * @return listaEletro
     */
    public EletroParameter[] getListaEletro() {
        return listaEletro;
    }


    /**
     * Sets the listaEletro value for this SaqueComplementarParameter.
     * 
     * @param listaEletro
     */
    public void setListaEletro(EletroParameter[] listaEletro) {
        this.listaEletro = listaEletro;
    }


    /**
     * Gets the numeroExterno value for this SaqueComplementarParameter.
     * 
     * @return numeroExterno
     */
    public Integer getNumeroExterno() {
        return numeroExterno;
    }


    /**
     * Sets the numeroExterno value for this SaqueComplementarParameter.
     * 
     * @param numeroExterno
     */
    public void setNumeroExterno(Integer numeroExterno) {
        this.numeroExterno = numeroExterno;
    }


    /**
     * Gets the numeroParcelas value for this SaqueComplementarParameter.
     * 
     * @return numeroParcelas
     */
    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }


    /**
     * Sets the numeroParcelas value for this SaqueComplementarParameter.
     * 
     * @param numeroParcelas
     */
    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }


    /**
     * Gets the protocoloMultiProdutos value for this SaqueComplementarParameter.
     * 
     * @return protocoloMultiProdutos
     */
    public Long getProtocoloMultiProdutos() {
        return protocoloMultiProdutos;
    }


    /**
     * Sets the protocoloMultiProdutos value for this SaqueComplementarParameter.
     * 
     * @param protocoloMultiProdutos
     */
    public void setProtocoloMultiProdutos(Long protocoloMultiProdutos) {
        this.protocoloMultiProdutos = protocoloMultiProdutos;
    }


    /**
     * Gets the seguros value for this SaqueComplementarParameter.
     * 
     * @return seguros
     */
    public Seguros[] getSeguros() {
        return seguros;
    }


    /**
     * Sets the seguros value for this SaqueComplementarParameter.
     * 
     * @param seguros
     */
    public void setSeguros(Seguros[] seguros) {
        this.seguros = seguros;
    }


    /**
     * Gets the telefoneFixoOuCelular value for this SaqueComplementarParameter.
     * 
     * @return telefoneFixoOuCelular
     */
    public TelefoneParameter getTelefoneFixoOuCelular() {
        return telefoneFixoOuCelular;
    }


    /**
     * Sets the telefoneFixoOuCelular value for this SaqueComplementarParameter.
     * 
     * @param telefoneFixoOuCelular
     */
    public void setTelefoneFixoOuCelular(TelefoneParameter telefoneFixoOuCelular) {
        this.telefoneFixoOuCelular = telefoneFixoOuCelular;
    }


    /**
     * Gets the token value for this SaqueComplementarParameter.
     * 
     * @return token
     */
    public String getToken() {
        return token;
    }


    /**
     * Sets the token value for this SaqueComplementarParameter.
     * 
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }


    /**
     * Gets the valorParcela value for this SaqueComplementarParameter.
     * 
     * @return valorParcela
     */
    public Double getValorParcela() {
        return valorParcela;
    }


    /**
     * Sets the valorParcela value for this SaqueComplementarParameter.
     * 
     * @param valorParcela
     */
    public void setValorParcela(Double valorParcela) {
        this.valorParcela = valorParcela;
    }


    /**
     * Gets the valorSaque value for this SaqueComplementarParameter.
     * 
     * @return valorSaque
     */
    public Double getValorSaque() {
        return valorSaque;
    }


    /**
     * Sets the valorSaque value for this SaqueComplementarParameter.
     * 
     * @param valorSaque
     */
    public void setValorSaque(Double valorSaque) {
        this.valorSaque = valorSaque;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SaqueComplementarParameter)) return false;
        SaqueComplementarParameter other = (SaqueComplementarParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.aberturaContaPagamento==null && other.getAberturaContaPagamento()==null) || 
             (this.aberturaContaPagamento!=null &&
              this.aberturaContaPagamento.equals(other.getAberturaContaPagamento()))) &&
            ((this.agencia==null && other.getAgencia()==null) || 
             (this.agencia!=null &&
              this.agencia.equals(other.getAgencia()))) &&
            ((this.agregacaoMargem==null && other.getAgregacaoMargem()==null) || 
             (this.agregacaoMargem!=null &&
              this.agregacaoMargem.equals(other.getAgregacaoMargem()))) &&
            ((this.banco==null && other.getBanco()==null) || 
             (this.banco!=null &&
              this.banco.equals(other.getBanco()))) &&
            this.bancoOrdemPagamento == other.getBancoOrdemPagamento() &&
            ((this.boletoPagamento==null && other.getBoletoPagamento()==null) || 
             (this.boletoPagamento!=null &&
              java.util.Arrays.equals(this.boletoPagamento, other.getBoletoPagamento()))) &&
            ((this.celular1==null && other.getCelular1()==null) || 
             (this.celular1!=null &&
              this.celular1.equals(other.getCelular1()))) &&
            ((this.codigoFormaEnvioTermo==null && other.getCodigoFormaEnvioTermo()==null) || 
             (this.codigoFormaEnvioTermo!=null &&
              this.codigoFormaEnvioTermo.equals(other.getCodigoFormaEnvioTermo()))) &&
            ((this.codigoLoja==null && other.getCodigoLoja()==null) || 
             (this.codigoLoja!=null &&
              this.codigoLoja.equals(other.getCodigoLoja()))) &&
            ((this.codigoRejeicaoContaSimples==null && other.getCodigoRejeicaoContaSimples()==null) || 
             (this.codigoRejeicaoContaSimples!=null &&
              this.codigoRejeicaoContaSimples.equals(other.getCodigoRejeicaoContaSimples()))) &&
            ((this.codigoSituacaoServidor==null && other.getCodigoSituacaoServidor()==null) || 
             (this.codigoSituacaoServidor!=null &&
              this.codigoSituacaoServidor.equals(other.getCodigoSituacaoServidor()))) &&
            ((this.conta==null && other.getConta()==null) || 
             (this.conta!=null &&
              this.conta.equals(other.getConta()))) &&
            ((this.cpfAgente==null && other.getCpfAgente()==null) || 
             (this.cpfAgente!=null &&
              this.cpfAgente.equals(other.getCpfAgente()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            this.finalidadeCredito == other.getFinalidadeCredito() &&
            this.formaCredito == other.getFormaCredito() &&
            ((this.listaEletro==null && other.getListaEletro()==null) || 
             (this.listaEletro!=null &&
              java.util.Arrays.equals(this.listaEletro, other.getListaEletro()))) &&
            ((this.numeroExterno==null && other.getNumeroExterno()==null) || 
             (this.numeroExterno!=null &&
              this.numeroExterno.equals(other.getNumeroExterno()))) &&
            ((this.numeroParcelas==null && other.getNumeroParcelas()==null) || 
             (this.numeroParcelas!=null &&
              this.numeroParcelas.equals(other.getNumeroParcelas()))) &&
            ((this.protocoloMultiProdutos==null && other.getProtocoloMultiProdutos()==null) || 
             (this.protocoloMultiProdutos!=null &&
              this.protocoloMultiProdutos.equals(other.getProtocoloMultiProdutos()))) &&
            ((this.seguros==null && other.getSeguros()==null) || 
             (this.seguros!=null &&
              java.util.Arrays.equals(this.seguros, other.getSeguros()))) &&
            ((this.telefoneFixoOuCelular==null && other.getTelefoneFixoOuCelular()==null) || 
             (this.telefoneFixoOuCelular!=null &&
              this.telefoneFixoOuCelular.equals(other.getTelefoneFixoOuCelular()))) &&
            ((this.token==null && other.getToken()==null) || 
             (this.token!=null &&
              this.token.equals(other.getToken()))) &&
            ((this.valorParcela==null && other.getValorParcela()==null) || 
             (this.valorParcela!=null &&
              this.valorParcela.equals(other.getValorParcela()))) &&
            ((this.valorSaque==null && other.getValorSaque()==null) || 
             (this.valorSaque!=null &&
              this.valorSaque.equals(other.getValorSaque())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAberturaContaPagamento() != null) {
            _hashCode += getAberturaContaPagamento().hashCode();
        }
        if (getAgencia() != null) {
            _hashCode += getAgencia().hashCode();
        }
        if (getAgregacaoMargem() != null) {
            _hashCode += getAgregacaoMargem().hashCode();
        }
        if (getBanco() != null) {
            _hashCode += getBanco().hashCode();
        }
        _hashCode += getBancoOrdemPagamento();
        if (getBoletoPagamento() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBoletoPagamento());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getBoletoPagamento(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCelular1() != null) {
            _hashCode += getCelular1().hashCode();
        }
        if (getCodigoFormaEnvioTermo() != null) {
            _hashCode += getCodigoFormaEnvioTermo().hashCode();
        }
        if (getCodigoLoja() != null) {
            _hashCode += getCodigoLoja().hashCode();
        }
        if (getCodigoRejeicaoContaSimples() != null) {
            _hashCode += getCodigoRejeicaoContaSimples().hashCode();
        }
        if (getCodigoSituacaoServidor() != null) {
            _hashCode += getCodigoSituacaoServidor().hashCode();
        }
        if (getConta() != null) {
            _hashCode += getConta().hashCode();
        }
        if (getCpfAgente() != null) {
            _hashCode += getCpfAgente().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        _hashCode += getFinalidadeCredito();
        _hashCode += getFormaCredito();
        if (getListaEletro() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaEletro());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getListaEletro(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroExterno() != null) {
            _hashCode += getNumeroExterno().hashCode();
        }
        if (getNumeroParcelas() != null) {
            _hashCode += getNumeroParcelas().hashCode();
        }
        if (getProtocoloMultiProdutos() != null) {
            _hashCode += getProtocoloMultiProdutos().hashCode();
        }
        if (getSeguros() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSeguros());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getSeguros(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTelefoneFixoOuCelular() != null) {
            _hashCode += getTelefoneFixoOuCelular().hashCode();
        }
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        if (getValorParcela() != null) {
            _hashCode += getValorParcela().hashCode();
        }
        if (getValorSaque() != null) {
            _hashCode += getValorSaque().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SaqueComplementarParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SaqueComplementarParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aberturaContaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aberturaContaPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "AgenciaParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agregacaoMargem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agregacaoMargem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "AgregacaoMargemParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("banco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "banco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "BancoParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bancoOrdemPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bancoOrdemPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("boletoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "boletoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://parameter.consignacao.econsig.bmg.com", "BoletoParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("celular1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "celular1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoFormaEnvioTermo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoFormaEnvioTermo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoLoja");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoLoja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRejeicaoContaSimples");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoRejeicaoContaSimples"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSituacaoServidor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSituacaoServidor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "ContaParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpfAgente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpfAgente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("finalidadeCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "finalidadeCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formaCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formaCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaEletro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaEletro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "EletroParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroExterno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroExterno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroParcelas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroParcelas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protocoloMultiProdutos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "protocoloMultiProdutos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seguros");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seguros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "Seguros"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefoneFixoOuCelular");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefoneFixoOuCelular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "TelefoneParameter"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorParcela");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorParcela"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSaque");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSaque"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
