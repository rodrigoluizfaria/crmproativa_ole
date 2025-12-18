package com.proativaservicos.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.detect.AutoDetectReader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


@SuppressWarnings("resource")
public class CsvUtil {

	private MappingIterator<Map<String, String>> it;

	private char separador = ';';

	private Charset charset;

	private Object[] header;

	private String nomeArquivo;

	private File csv;
	
	private byte [] stream;

	private TreeMap<Long, Map<String, String>> linhas;

	private CsvUtil(File file) {

		this.csv = file;

		try {

			this.charset = new AutoDetectReader(new FileInputStream(csv)).getCharset();

		} catch (Exception e) {

			this.charset = StandardCharsets.UTF_8;

		}
	}
	
	private CsvUtil(File file,char separador) {
		
		this.csv = file;
		this.separador = separador;
		
		try {
			
			this.charset = new AutoDetectReader(new FileInputStream(csv)).getCharset();
			
		} catch (Exception e) {
			
			this.charset = StandardCharsets.UTF_8;
			
		}
	}

	private CsvUtil(String strFile) {

		if (StringUtils.isNotEmpty(strFile)) {

			this.nomeArquivo = strFile;

		}
	}
	
	private CsvUtil(byte[] stream) {

		if (stream!=null) {

			this.stream = stream;

		}
	}


	public static CsvUtil builder(File csv) {

		if (csv.exists() && csv.isFile()) {

			return new CsvUtil(csv);
		}

		return null;

	}
	
	
	public static CsvUtil builder(byte[] csv) {

		if (csv !=null) {

			return new CsvUtil(csv);
		}

		return null;

	}
	
	public static CsvUtil builder(File csv,char separador) {
		
		if (csv.exists() && csv.isFile()) {
						
			return new CsvUtil(csv,separador);
		}
		
		return null;
		
	}

	public static CsvUtil builder(String fileStr, boolean arquivo) {

		if (!arquivo && StringUtils.isNotEmpty(fileStr)) {

			return new CsvUtil(fileStr);
		}

		if (arquivo && StringUtils.isNotEmpty(fileStr)) {

			return CsvUtil.builder(new File(fileStr));

		}

		return null;

	}


	public CsvUtil retornarCabecalho() {

		try {

			if (this.csv == null)

				return null;

			String f = FileUtils.readFileToString(this.csv, this.charset);

			CsvMapper mapper = new CsvMapper();
			
			CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(separador).withHeader();

			MappingIterator<Map<String, String>> it = mapper.reader(Map.class).with(schema).readValues(f.getBytes());

			this.header = it.next().keySet().toArray();

			return this;

		} catch (Exception e) {

			this.it = null;
			return null;

		} finally {

			this.it = null;
		}

	}
	
	public CsvUtil lerPlanilhaToMap() {
		
		return lerPlanilhaToMap(false);
	}

	public CsvUtil lerPlanilhaToMap(boolean cabecalho) {

		try {

			if (this.csv != null) {

				System.out.println(charset.getClass());

				String f = FileUtils.readFileToString(this.csv, this.charset);

				CsvMapper mapper = new CsvMapper();
				CsvSchema schema = null;
				
				if(cabecalho)
					schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';');
				else
					schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';').withHeader();

				this.it = mapper.reader(Map.class).with(schema).readValues(f.getBytes());

				this.linhas = new TreeMap<Long, Map<String, String>>();
				
				if(cabecalho)
					this.header = it.next().keySet().toArray();

				int i = 0;
				while (it.hasNext()) {

					Map<String, String> rowAsMap = it.next();
					this.linhas.put(Long.valueOf(i++), rowAsMap);

				}
			}

			return this;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public CsvUtil lerCsvByteToMap() {

		try {

			if (this.csv != null) {

				System.out.println(charset.getClass());

			

				CsvMapper mapper = new CsvMapper();
				CsvSchema schema = null;
				
			
				schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';').withHeader();

				this.it = mapper.reader(Map.class).with(schema).readValues(this.stream);

				this.linhas = new TreeMap<Long, Map<String, String>>();

				int i = 0;
				while (it.hasNext()) {

					Map<String, String> rowAsMap = it.next();
					this.linhas.put(Long.valueOf(i++), rowAsMap);

				}
			}

			return this;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object[] getHeader() {
		return header;
	}

	public TreeMap<Long, Map<String, String>> getMapInterator() {
		return this.linhas;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
}
