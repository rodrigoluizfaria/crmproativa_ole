/**
 * SaqueComplementarLojistaParceiroParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.proativaservicos.service.asynchronous.bmg.saquecomplementar;

public class SaqueComplementarLojistaParceiroParameter  extends SaqueComplementarParameter  implements java.io.Serializable {
    public SaqueComplementarLojistaParceiroParameter() {
    }

    public SaqueComplementarLojistaParceiroParameter(
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
            vinculoMatricula,
            aberturaContaPagamento,
            agencia,
            agregacaoMargem,
            banco,
            bancoOrdemPagamento,
            boletoPagamento,
            celular1,
            codigoFormaEnvioTermo,
            codigoLoja,
            codigoRejeicaoContaSimples,
            codigoSituacaoServidor,
            conta,
            cpfAgente,
            email,
            finalidadeCredito,
            formaCredito,
            listaEletro,
            numeroExterno,
            numeroParcelas,
            protocoloMultiProdutos,
            seguros,
            telefoneFixoOuCelular,
            token,
            valorParcela,
            valorSaque);
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SaqueComplementarLojistaParceiroParameter)) return false;
        SaqueComplementarLojistaParceiroParameter other = (SaqueComplementarLojistaParceiroParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj);
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SaqueComplementarLojistaParceiroParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.econsig.bmg.com", "SaqueComplementarLojistaParceiroParameter"));
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
