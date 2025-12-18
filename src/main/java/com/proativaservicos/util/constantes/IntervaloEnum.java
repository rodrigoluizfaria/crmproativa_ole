package com.proativaservicos.util.constantes;



public enum IntervaloEnum {
  UMA_HORA("Uma hora", Integer.valueOf(1)),
  DUAS_HORAS("Duas horas", Integer.valueOf(2)),
  TRES_HORAS("Trhoras", Integer.valueOf(3)),
  QUATRO_HORAS("Quatro horas", Integer.valueOf(4)),
  CINCO_HORAS("Cinco horas", Integer.valueOf(5)),
  SEIS_HORAS("Seis horas", Integer.valueOf(6)),
  DOZE_HORAS("Doze  horas", Integer.valueOf(12)),
  VINTE_QUATRO_HORAS("Vinte quatro horas", Integer.valueOf(0));
  
  private String constante;
  private Integer intervalor;
  
  IntervaloEnum(String descricao, Integer intervalor) {
    this.constante = descricao;
    this.intervalor = intervalor;
  }

  
  public String getCconstante() { return this.constante; }


  
  public void setDonstante(String descricao) { this.constante = descricao; }


  
  public Integer getIntervalor() { return this.intervalor; }


  
  public void setIntervalor(Integer intervalor) { this.intervalor = intervalor; }
}
