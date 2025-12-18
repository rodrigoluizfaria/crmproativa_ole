package com.proativaservicos.util;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumeroUtil implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Double numero;
  private String numeroTexto;
  private BigDecimal numeroBigDecimal;
  
  private NumeroUtil(Double numero) { this.numero = numero; }
 
  private NumeroUtil(String numero) { this.numeroTexto = numero; }

  private NumeroUtil(BigDecimal numero) { this.numeroBigDecimal = numero; }
  

  public static NumeroUtil builder(Double numero) { return new NumeroUtil(numero); }

  public static NumeroUtil builder(String numero) { return new NumeroUtil(numero); }
  
  public static NumeroUtil builder(BigDecimal numero) { return new NumeroUtil(numero); }


  
  public Double getNumero() { return this.numero; }
  
  public BigDecimal getBigdecimal() { return this.numeroBigDecimal; }

  
  public NumeroUtil formatarNumero() {
	  
    if (this.numeroTexto == null || this.numeroTexto.trim().isEmpty()) {
    	
      this.numero = null;
      
      return this;
    } 
    
    DecimalFormat formatador = new DecimalFormat();
    formatador.setMaximumFractionDigits(2);
    formatador.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("pt", "BR")));
    
    try {
    	
      Number numeroFormatado = null;
      
      if (this.numeroTexto.contains(".") && this.numeroTexto.contains(",")) {
    	  
        numeroFormatado = formatador.parse(this.numeroTexto.replaceAll("[.]", ""));
     
      } else {
    	  
        numeroFormatado = formatador.parse(this.numeroTexto.replaceAll("[.]", ","));
      } 

      
      if (numeroFormatado instanceof Long) {
    	  
        this.numero = Double.valueOf(((Long)numeroFormatado).doubleValue());
        
      } else if (numeroFormatado instanceof Integer) {
    	  
        this.numero = Double.valueOf(((Integer)numeroFormatado).doubleValue());
        
      } else {
    	  
        this.numero = (Double)numeroFormatado;
      } 
      
      return this;
    } catch (ParseException e) {
      this.numero = null;
      return this;
    } 
  }
  public NumeroUtil formatarBigDecimal() {
	  
	  if (this.numeroTexto == null || this.numeroTexto.trim().isEmpty()) {
		  
		  this.numero = null;
		  
		  return this;
	  } 
	  
	  DecimalFormat formatador = new DecimalFormat();
	  formatador.setMaximumFractionDigits(2);
	  formatador.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("pt", "BR")));
	  
	  try {
		  
		  Number numeroFormatado = null;
		  
		  if(this.numeroTexto.contains("R$")) {
			  
			this.numeroTexto =  this.numeroTexto.replace("R$", "").trim();
			
		  }
		  
		  if (this.numeroTexto.contains(".") && this.numeroTexto.contains(",")) {
			  
			  numeroFormatado = formatador.parse(this.numeroTexto.replaceAll("[.]", ""));
			  
		  } else {
			  
			  numeroFormatado = formatador.parse(this.numeroTexto.replaceAll("[.]", ","));
		  } 
		  
		  
		  if (numeroFormatado instanceof Long) {
			  
			  this.numeroBigDecimal = BigDecimal.valueOf((long) numeroFormatado);
			  
		  } else if (numeroFormatado instanceof Integer) {
			  
			  this.numeroBigDecimal = BigDecimal.valueOf((Integer) numeroFormatado);
			  
		  } else if (numeroFormatado instanceof Double) {
			  
			  this.numeroBigDecimal = BigDecimal.valueOf((Double) numeroFormatado);
			  
		  } else {
			  
			  this.numeroBigDecimal = (BigDecimal) numeroFormatado;
		  }
		  
		  return this;
		  
	  } catch (ParseException e) {
		  
		  this.numero = null;
		  return this;
	  } 
  }

  
  public NumeroUtil converterStringDoubleComPonto(int posicao) {
    if (this.numeroTexto != null) {
      this.numero = this.numeroTexto.contains(".") ? Double.valueOf(this.numeroTexto) : Double.valueOf((new StringBuilder(this.numeroTexto)).insert(posicao, ".").toString());
    }
    return this;
  }
  
  public NumeroUtil verificarSinalNumero(String sinal) {
    if (this.numero != null && sinal != null) {
      this.numero = Double.valueOf(sinal.equals("-") ? (this.numero.doubleValue() * -1.0D) : this.numero.doubleValue());
    }
    return this;
  }

  
  public String formatarDoubleParaString() { return formatarDoubleParaString(2); }

  
  public String formatarDoubleParaString(int casasDecimais) {
    if (this.numero == null) {
      return null;
    }
    DecimalFormat formatador = new DecimalFormat();
    formatador.setMaximumFractionDigits(casasDecimais);
    formatador.setMinimumFractionDigits(casasDecimais);
    formatador.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("pt", "BR")));
    
    return formatador.format(this.numero);
  }

  
  public Double formartarParaDuasCadasDecimais() {
	  
	  if(this.numero == null)
		  return null;
	  
	 return Double.valueOf(String.format("%.2f", this.numero).replace(",", "."));
  }
  
  public String formatarNumeroParaMoeda() {
 
	  if (this.numero == null) {
      return null;
    }
    return NumberFormat.getCurrencyInstance().format(this.numero);
  }
  
  public String formatarBigDecimalParaMoeda() {
	  
	  if (this.numeroBigDecimal == null) {
		  return null;
	  }
	  return NumberFormat.getCurrencyInstance().format(this.numeroBigDecimal);
  }

  
  @SuppressWarnings("deprecation")
public NumeroUtil arredondarNumero(Integer precisao) {
 
	  if (this.numero == null || precisao == null) {
    	
      this.numero = null;
      
      return this;
    } 
    
    this.numero = Double.valueOf((new BigDecimal(this.numero.doubleValue())).setScale(precisao.intValue(), 4).doubleValue());
    
    return this;
  }
}
