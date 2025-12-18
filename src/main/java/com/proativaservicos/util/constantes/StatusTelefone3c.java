package com.proativaservicos.util.constantes;

import org.apache.commons.lang3.StringUtils;

public enum StatusTelefone3c {

    _1(1, "Discando"),
    _2(2, "Atendida"),
    _3(3, "Conectada"),
    _4(4, "Encerrada"),
    _5(5, "Não atendida"),
    _6(6, "Abandonada"),
    _7(7, "Finalizada"),
    _8(8, "Falha"),
    _9(9, "Caixa postal Pós atendimento"),
    _10(10, "Consulta em espera"),
    _11(11, "Consulta Conectada"),
    _12(12, "Consulta"),
    _13(13, "Transferência "),
    _14(14, "Fora de horário "),
    _15(15, "Caixa postal pré atendimento");


    private Integer codigo;

    private String descricao;

    StatusTelefone3c(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static StatusTelefone3c getStatusTelefone(String codigo) {

        if (StringUtils.isNotEmpty(codigo)) {

            String value = codigo;

            if (codigo.contains("_"))
                value = codigo.replaceAll("_", "");

            if (StringUtils.isNumeric(value)) {

                int cod = Integer.parseInt(value);

                for (StatusTelefone3c status : values()) {

                    if (status.getCodigo() == cod)
                        return status;
                }

            }

            return null;

        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(getStatusTelefone("8").getDescricao());
    }
}
