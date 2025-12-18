package com.proativaservicos.util;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.model.StatusAtendimento;
import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CollectionUtils {

	
	
	public static List<StatusAtendimento> converterStatusAtendimento(List<Object[]> lista) {
		   
		  return (List<StatusAtendimento>)org.apache.commons.collections4.CollectionUtils.collect(lista, new Transformer() {
		          public StatusAtendimento transform(Object object) {
		            
		        	  Object[] informacoes = (Object[])object;
		            
		            return new StatusAtendimento(Long.valueOf(((Integer) informacoes[0]).longValue()) , informacoes[1].toString(), AcaoStatusAtendimentoEnum.valueOf(informacoes[2].toString()));
		          }
		        });
		  }
	  
	  
	  public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		    Map<Object, Boolean> map = new ConcurrentHashMap<>();
		    return t -> (map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null);
		  }
	  
	  
	  
	    public static <T> List<T> removeDuplicates(List<T> list)
	    {
	  
	        // Create a new LinkedHashSet
	        Set<T> set = new LinkedHashSet<>();
	  
	        // Add the elements to set
	        set.addAll(list);
	  
	        // Clear the list
	        list.clear();
	  
	        // add the elements of set
	        // with no duplicates to the list
	        list.addAll(set);
	  
	        // return the list
	        return list;
	       }
	    
	    public static String retornarStringNaoDuplicadas(String valor,String separator) {
	    	
	    	String retorno =null;
	    	
	    	if(StringUtils.isNotBlank(valor) && StringUtils.isNotBlank(separator)) {
	    		
	    		if(!valor.contains(separator))
	    			return valor;
	    		
	    		Set<String> has = new LinkedHashSet<>();
	    	
	    		has.addAll(Arrays.asList(valor.split(separator)));
	    			    		
	    		retorno ="";
	    		
	    		int i =0;
	    		
	    		for (String string : has) {
	    				    			
	    			
	    			if(i==0)
	    				retorno = string;
	    			else
	    				retorno = retorno+separator+string;
	    			i++;
	    		}
	    		
	    	}
	    	return retorno;
	    }
}
