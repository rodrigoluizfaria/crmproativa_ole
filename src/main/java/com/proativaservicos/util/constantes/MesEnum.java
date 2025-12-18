package com.proativaservicos.util.constantes;


import java.util.Arrays;

public enum MesEnum
{
  JANEIRO("Janeiro", Integer.valueOf(1), "Jan"),
  FEVEREIRO("Fevereiro", Integer.valueOf(2), "Fev"),
  MARCO("Março", Integer.valueOf(3), "Mar"),
  ABRIL("Abril", Integer.valueOf(4), "Abr"),
  MAIO("Maio", Integer.valueOf(5), "Mai"),
  JUNHO("Junho", Integer.valueOf(6), "Jun"),
  JULHO("Julho", Integer.valueOf(7), "Jul"),
  AGOSTO("Agosto", Integer.valueOf(8), "Ago"),
  SETEMBRO("Setembro", Integer.valueOf(9), "Set"),
  OUTUBRO("Outubro", Integer.valueOf(10), "Out"),
  NOVEMBRO("Novembro", Integer.valueOf(11), "Nov"),
  DEZEMBRO("Dezembro", Integer.valueOf(12), "Dez");
  
  private String descricao;
  private Integer valor;
  private String abreviacao;
  
  MesEnum(String descricao, Integer valor, String abreviacao) {
    this.descricao = descricao;
    this.valor = valor;
    this.abreviacao = abreviacao;
  }

  
  public String getDescricao() { return this.descricao; }


  
  public void setDescricao(String descricao) { this.descricao = descricao; }


  
  public Integer getValor() { return this.valor; }


  
  public void setValor(Integer valor) { this.valor = valor; }


  
  public String getAbreviacao() { return this.abreviacao; }


  
  public void setAbreviacao(String abreviacao) { this.abreviacao = abreviacao; }


  
  public static MesEnum getMes(Integer mes) {
    if (mes == null || mes.intValue() < 0) {
      return null;
    }
    
    switch (mes.intValue()) {
      case 0:
        return JANEIRO;
      case 1:
        return FEVEREIRO;
      case 2:
        return MARCO;
      case 3:
        return ABRIL;
      case 4:
        return MAIO;
      case 5:
        return JUNHO;
      case 6:
        return JULHO;
      case 7:
        return AGOSTO;
      case 8:
        return SETEMBRO;
      case 9:
        return OUTUBRO;
      case 10:
        return NOVEMBRO;
      case 11:
        return DEZEMBRO;
      case 12:
        return JANEIRO;
    } 
    return null;
  }


  
  public static MesEnum getMesPorValor(Integer valor) { return Arrays.asList(values()).stream().filter(m -> m.getValor().equals(valor)).findFirst().get(); }
}
