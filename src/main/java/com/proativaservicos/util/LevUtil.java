package com.proativaservicos.util;

import com.google.gson.GsonBuilder;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.in100.MasterIn100;
import com.proativaservicos.model.pan.DateDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


public class LevUtil implements Serializable {

	
	private String url="http://189.44.65.252:4443/v1/api-lev/consulta-in100";
	private String username="lev_in100_2512";
	private String password="0<)F1i|9z.>j:@-*j$ech7";
	private String cpfRepresentanteLegal="";

	private List<MasterIn100> listCpfErro = new ArrayList<>();


	public static String consultaIn100(String url, String usuario, String senha,String cpf, String beneficio,String cpfRepresentente)	throws ProativaException {

		String erro = "";
		try {

			String json = gerarJson(url,cpfRepresentente,usuario,senha,cpf,beneficio);
			System.out.println(json);
			Map<String, String> retornoMap = HttpPostUtil.enviarPostOkttp(url, null, json, false);

			if (retornoMap == null || retornoMap.isEmpty())
				throw new ProativaException("Não foi possivel realizar a consulta.");
		
			JSONObject jsonOb = null;
			
			if(Utils.isJSON(retornoMap.get("boby"))) {
				
				 jsonOb = new JSONObject(retornoMap.get("boby"));
				 
			}else {

				System.out.println("ERRO CONSULTA LEV: "+retornoMap.get("boby").toString());
				erro = "ERRO CONSULTA LEV: "+retornoMap.get("boby").toString();
				throw new ProativaException("Não foi possivel realizar a consulta.");
			}
			
			return retornoMap.get("boby");

		} catch (ProativaException e) {
			throw e;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return erro;

	}

	private static String gerarJson(String url, String cpfRepresentente, String usuario, String senha,String cpf, String beneficio )throws ProativaException {

		if (StringUtils.isBlank(beneficio) || StringUtils.isBlank(cpf) )
			throw new ProativaException("Número do benefício ou CPF não informado");
		else if (StringUtils.isBlank(url) || StringUtils.isBlank(usuario) || StringUtils.isBlank(senha) )
			throw new ProativaException("Dados de integração não foi informado.");

		JSONObject jso = new JSONObject();
		jso.put("username",usuario);
		jso.put("password",senha);
		jso.put("cpf",cpf.trim());
		jso.put("matricula",beneficio.trim());
		jso.put("cpfRepresentanteLegal",cpfRepresentente.trim());


		return jso.toString();
	}
		

	private  void consulta() throws IOException {

			  String arquivoCSV = "C:\\Users\\Rodrigo\\Documents\\parte_29.csv";

	        BufferedReader br = null;
	        String linha = "";
	          

	        br = new BufferedReader(new FileReader(arquivoCSV));
	      //  br.readLine();

	        String head = br.readLine();
	        List<MasterIn100> list = new ArrayList<>();
	       
	        while ((linha = br.readLine()) != null) {

	        	 String[] arrayLinha = linha.split(";");
			     list.add(new MasterIn100(arrayLinha[0].trim(),arrayLinha[1].trim()));
	        }

			excreveNoCsv(new File("C:\\Users\\rodrigo\\Documents\\lev_retorno.csv"),  new MasterIn100().getHeader() );

			List<Future<Long>> listResultadosFuture = new ArrayList<Future<Long>>();
			ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
			int quantidadePorMinuto = 0;
			
			for (MasterIn100 masterIn100 : list) {

				System.out.println(masterIn100.toJason());
				listResultadosFuture.add(executor.submit(new ConsultaIn100(masterIn100.getNumeroMatricula(),masterIn100.getCpf())));

			}
			
			int qtidadeCancelados = 1;
			int qtidadeRepetidosCompletos = 0;
			int task=1;
			for (Future<Long> future : listResultadosFuture) {

				try {

					long qtidadeCompletadosAnterior = executor.getCompletedTaskCount();

					Thread.sleep(15999L);

					Long inicioExecucao = future.get(30L, TimeUnit.SECONDS);
					System.out.println("TAREFA  "+task+++" INICIOU EM: "+new Date(inicioExecucao));
					imprimirMonitorConsultaSaqueComplementar(executor, "");
					
					if (System.currentTimeMillis() - inicioExecucao.intValue() > 60000L) 
						future.cancel(true);
					

					if ( ( executor.getCompletedTaskCount() == executor.getTaskCount() && !executor.isTerminated() )  ) {
						executor.shutdown();
						break;
					}

					if (qtidadeCompletadosAnterior == executor.getCompletedTaskCount())
						qtidadeRepetidosCompletos++;

					if (qtidadeRepetidosCompletos == 5) {
						executor.shutdown();
						break;
					}

				} catch (Exception e) {
					// TODO: handle exception

					System.out.println(e.getMessage());

					System.out.println("[  ] - Quantidade Consultas Canceladas ==> "	+ qtidadeCancelados++);
				}

		}

		for (MasterIn100 mas: listCpfErro ) {
			System.out.println(mas.getCpf()+";"+mas.getNumeroMatricula());
		}
		
	}

	public static void main(String[] args) throws IOException {
		
   /*  LevUtil am = new LevUtil();

	 	try {

			String str =	LevUtil.consultaIn100("http://189.44.65.252:4443/v1/api-lev/consulta-in100","lev_in100_2512","0<)F1i|9z.>j:@-*j$ech7","13025082534","1371679212","3a104afb999ac0cf2b3a559aad575556");

			if(StringUtils.isNotBlank(str) && Utils.isJSON(str)) {

				GsonBuilder gsonBuilder = new GsonBuilder();
				gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
				MasterIn100 master = gsonBuilder.create().fromJson(str, MasterIn100.class);

				System.out.println(master.getHeader());
				System.out.println(master.toCsvStr());

			}else{


			}

		 } catch (ProativaException e) {
			throw new RuntimeException(e);
		}*/
		LevUtil am = new LevUtil();
		am.consulta();
	
	}
		
	class ConsultaIn100 implements Callable<Long> {

		private Long inicio;
		private String beneficio;
		private String cpf;
		public ConsultaIn100(String beneficio,String cpf) {

			this.beneficio = beneficio;
			this.cpf = cpf;
			this.inicio = System.currentTimeMillis();
		}

		@Override
		public Long call() throws Exception {

			try {


				String retorno = LevUtil.consultaIn100(url,username,password,this.cpf ,this.beneficio,cpfRepresentanteLegal);
				//System.out.println(retorno);

				if(StringUtils.isNotBlank(retorno) && Utils.isJSON(retorno)) {

					MasterIn100 masterIn100 = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create().fromJson(retorno, MasterIn100.class);
					masterIn100.setCpf(this.cpf);

					if( (StringUtils.isNotBlank(masterIn100.getStatus()) && masterIn100.getStatus().equalsIgnoreCase("Falha")) || (StringUtils.isNotBlank(masterIn100.getStatus()) && masterIn100.getStatus().equalsIgnoreCase("500")) ){

						excreveNoCsv(new File("C:\\Users\\rodrigo\\Documents\\lev_retorno.csv"), this.cpf+";"+masterIn100.getMessage() );

					}else{

						excreveNoCsv(new File("C:\\Users\\rodrigo\\Documents\\lev_retorno.csv"), masterIn100.toCsvStr().replaceAll("null","") );
					}



				} else {

					excreveNoCsv(new File("C:\\Users\\rodrigo\\Documents\\lev_retorno.csv"), cpf + ";" + "erro ao consultar");

				}



			} catch (ProativaException e) {

				System.out.println("Erro: "+e.getMessage());
				System.out.println(this.cpf+";"+this.beneficio+";erro");
				listCpfErro.add(new MasterIn100(cpf,beneficio));

			} catch (Exception e) {
			
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.out.println(this.cpf+";"+this.beneficio+";erro");
				listCpfErro.add(new MasterIn100(cpf,beneficio));


			} 

			return this.inicio;		
		}
	

	}
	
    public synchronized static void excreveNoCsv(File fileCsv, String str) {

        if (!fileCsv.exists()) {

            try {

                fileCsv.createNewFile();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

   

        ArquivoUtil.geraLogCsv(fileCsv, str);

      
    }
    
	private static void imprimirMonitorConsultaSaqueComplementar(ThreadPoolExecutor executor, String campanha) {

		System.out.println(String.format("[CONSULTA PAN SIMULACAO : " + campanha+ "] [%d/%d] Ativos: %d, Completetos: %d, Task: %d, isShutdown: %s, isTerminated: %s",
				new Object[] { Integer.valueOf(executor.getPoolSize()), Integer.valueOf(executor.getCorePoolSize()),
						Integer.valueOf(executor.getActiveCount()), Long.valueOf(executor.getCompletedTaskCount()),
						Long.valueOf(executor.getTaskCount()), Boolean.valueOf(executor.isShutdown()),
						Boolean.valueOf(executor.isTerminated()) }));

	}

}