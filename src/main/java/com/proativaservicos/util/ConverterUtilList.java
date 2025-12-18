package com.proativaservicos.util;

import java.io.Serializable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;

import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.Consistencia;
import com.proativaservicos.model.SubMotivo;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;


public class ConverterUtilList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static List<Usuario> converterUsuario(List<Object[]> lista) {

		return (List<Usuario>) CollectionUtils.collect(lista, new Transformer() {

			public Usuario transform(Object object) {

				Object[] informacoes = (Object[]) object;

				return new Usuario(Long.valueOf(((Integer) informacoes[0]).longValue()), informacoes[1].toString());
			}
		});
	}
	
	
	 public static List<Campanha> converterCampanha(List<Object[]> lista) {
		  
		  return (List<Campanha>)CollectionUtils.collect(lista, new Transformer() {

			  public Campanha transform(Object object) {
				  
		            Object[] informacoes = (Object[]) object;
		            
		            return new Campanha(Long.valueOf(((Integer)informacoes[0]).longValue()), informacoes[1].toString());
		          }
		        });
		  }
	 
	 public static List<SubMotivo> converterSubMotivo(List<Object[]> lista) {
		 
		 return (List<SubMotivo>)CollectionUtils.collect(lista, new Transformer() {
			 
			 public SubMotivo transform(Object object) {
				 
				 Object[] informacoes = (Object[]) object;
				 
				 return new SubMotivo(Long.valueOf(((Integer)informacoes[0]).longValue()), informacoes[1].toString());
			 }
		 });
	 }
	 
	 public static List<Consistencia> converterConcistencias(List<Object[]> lista) {
		
		 if(lista ==null || lista.isEmpty()) 
			 return new ArrayList<Consistencia>();
	
		 
		  return (List<Consistencia>)CollectionUtils.collect(lista, new Transformer() {

			  public Consistencia transform(Object object) {
				  				  				  
		            Object[] informacoes = (Object[]) object;
		            
		            if(informacoes[0] ==null)
		            	return null;
		            
		            Long id = null;
		         
		            if(informacoes[0] instanceof Integer) {
		            	
		            	id = Long.valueOf(((Integer)informacoes[0]).longValue());
		            
		            }else {
		            	
		            	id = new BigInteger(informacoes[0].toString()).longValue();
		            }
		            
		            Boolean tradata = null;
		          
		            if( informacoes[10] !=null) {
		        	   tradata = Boolean.valueOf(informacoes[10].toString());
		           }
		           
		            return new Consistencia(id.longValue(), ((Integer)informacoes[1]), validarObject( informacoes[2]) ,validarObject(informacoes[3]),InstituicaoFinanceiraEnum.retornarEnum(validarObject(informacoes[4])),validarObject(informacoes[5]),validarObject(informacoes[6]),validarObject(informacoes[7]),validarObject(informacoes[8]),validarObject(informacoes[9]), tradata );
		          
			  
			  }
			  
		        });
		  }
	 
	 private static String validarObject(Object ob) {
		 
		 Optional<Object> op =  Optional.ofNullable(ob);
		 
		 if(op.isPresent())
			 return op.get().toString();
		 
		 return null;
	 }
}
