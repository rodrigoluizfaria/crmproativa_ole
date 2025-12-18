package com.proativaservicos.dao.integra;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.proativaservicos.model.integracao.Contact;
import com.proativaservicos.model.integracao.Number;
import com.proativaservicos.util.DateUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class VonixClient {

	private String usuario = "proativa";

	private String senha = "";

	private int statusCode;

	private String respondeMessage;

	private String resourceUrl = "";

	public enum RequestMethod {
		GET, POST, PUT, DELETE;
	}

	public VonixClient(String url, String usuario, String senha) {
	
		this.resourceUrl = url;
		this.usuario = usuario;
		this.senha = senha;
	}

	public boolean enviarContatos(Long idContato, String nome, String idFila, Collection<String> telefones)	throws IOException {

		List<NameValue> param = new ArrayList<NameValue>();

		param.add(new NameValue("contact_name", nome));
		param.add(new NameValue("queue", idFila));

		param.add(new NameValue("billing_group_id", "1"));
		

		for (String tel : telefones) {

			String[] tels = tel.split("[;]");
			String id = tels[0];
			String numero = tels[1];
			param.add(new NameValue("to[" + id + "]", numero));

		}

		sendRequest("/contact/" + idContato, param, RequestMethod.GET);
		return (this.statusCode == 200);
	}
	
	
	public boolean removerContato(Long id) throws IOException {

		sendRequest("/contact/" + id, null, RequestMethod.DELETE);
		return (this.statusCode == 200);

	}

	public boolean removerTodosContatos(String idFila) throws IOException {

		sendRequest("/contacts/" + idFila, null, RequestMethod.DELETE);
		return (this.statusCode == 200);

	}

	private String sendRequest(String path, Collection<NameValue> parameters, RequestMethod method) throws IOException {

		URL url = new URL(this.resourceUrl + path);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		String userPassword = this.usuario + ":" + this.senha;
		String encoding = new String(Base64.encodeBase64(userPassword.getBytes()));

		connection.setRequestProperty("Authorization", "Basic " + encoding);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod(method.name());
		
		

		if (parameters != null && !parameters.isEmpty()) {

			StringBuilder urlParameters = new StringBuilder();

			for (NameValue item : parameters)
				urlParameters.append(item.getName() + "=" + URLEncoder.encode(item.getValue(), "UTF-8") + "&");

			urlParameters.deleteCharAt(urlParameters.length() - 1);
			connection.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		
			wr.writeBytes(urlParameters.toString());
		}

		this.statusCode = connection.getResponseCode();
		this.respondeMessage = connection.getResponseMessage();

		if (this.statusCode >= 200 && this.statusCode <= 299) {

			InputStream inputStream = connection.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer response = new StringBuffer();
			String inputLine;

			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
				

			}
			
			reader.close();
			connection.disconnect();
			
			return response.toString();

		} else {

			return null;
		}

	}

	
	private String sendRequest(String path, Collection<NameValue> parameters, RequestMethod method,int timeout)   {

		try {
			
		URL url = new URL(this.resourceUrl + path);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		String userPassword = this.usuario + ":" + this.senha;
		String encoding = new String(Base64.encodeBase64(userPassword.getBytes()));

		connection.setRequestProperty("Authorization", "Basic " + encoding);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod(method.name());
		connection.setConnectTimeout(timeout);
		
		if (parameters != null && !parameters.isEmpty()) {

			StringBuilder urlParameters = new StringBuilder();

			for (NameValue item : parameters)
				urlParameters.append(item.getName() + "=" + URLEncoder.encode(item.getValue(), "UTF-8") + "&");

			urlParameters.deleteCharAt(urlParameters.length() - 1);
			connection.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		
			wr.writeBytes(urlParameters.toString());
		}

		this.statusCode = connection.getResponseCode();
		this.respondeMessage = connection.getResponseMessage();

		if (this.statusCode >= 200 && this.statusCode <= 299) {

			InputStream inputStream = connection.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer response = new StringBuffer();
			String inputLine;

			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
				

			}
			
			reader.close();
			connection.disconnect();
			
			return response.toString();

		} 
		
		}catch (IOException e) {
			System.out.println("ERRO: CONSULTA RESULTS VONIX  = IOException sendRequest Vonix.");
		}
		
		return null;
	}
	
	public String consultarFila(String idFila) throws IOException {
		
		
		try {
			
			
			String resposta = sendRequest("/queue/" + idFila + "/status", null, RequestMethod.GET);
			Document doc;
					
			doc = carregarXMLFromString(resposta);
			
			NodeList nodeListContact = doc.getElementsByTagName("queue");
			
			for (int i = 0; i < nodeListContact.getLength(); i++) {

				Node obj = nodeListContact.item(i);
			
				
				if (obj.getNodeType() == Node.ELEMENT_NODE) {

					Element elementNumber = (Element) obj;
					System.out.println(elementNumber.getAttribute("status"));
			
				
			}
			}
			
			
			return "";
	
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return idFila;
		

	}

	public void consultarFilaParse(String idFila) throws IOException, SAXException, ParserConfigurationException {

		String resposta = sendRequest("/queue/" + idFila + "/status", null, RequestMethod.GET);

		Document doc = carregarXMLFromString(resposta);

		NodeList queue = doc.getElementsByTagName("queue");

		for (int i = 0; i < queue.getLength(); i++) {

			Node obj = queue.item(i);
		
			System.out.println("Fila: " + obj.getAttributes().getNamedItem("id").getNodeValue());
			NodeList atributos = obj.getChildNodes();

			for (int j = 0; j < atributos.getLength(); j++) {

				if (!atributos.item(j).getNodeName().equals("#text")) {
					System.out.print("\t" + atributos.item(j).getNodeName() + ": ");
					System.out.println(atributos.item(j).getFirstChild().getTextContent());
				}

			}

		}

	}
	
	public String consultarContatosFila(String idFila) throws IOException, SAXException, ParserConfigurationException {

		String resposta = sendRequest("/queue/" + idFila + "/status", null, RequestMethod.GET);

		Document doc = carregarXMLFromString(resposta);

		NodeList queue = doc.getElementsByTagName("queue");
		
		for (int i = 0; i < queue.getLength(); i++) {

			Node obj = queue.item(i);
		
			NodeList atributos = obj.getChildNodes();

			for (int j = 0; j < atributos.getLength(); j++) {

				if (!atributos.item(j).getNodeName().equals("#text")) {
				
					
					if(atributos.item(j).getNodeName().equals("stored_contacts")) {
						
						return atributos.item(j).getFirstChild().getTextContent();
						
					}
					
				}

			}

		}
		
		return null;

	}

	public String consultarTodasFilas() throws IOException, ParserConfigurationException, SAXException {

		String path = "/queues";

		System.out.println("Consultando Filas:");

		String response = sendRequest(path, null, RequestMethod.GET);

		Document doc = carregarXMLFromString(response.toString());

		NodeList queues = doc.getElementsByTagName("queue");

		
		for (int i = 0; i < queues.getLength(); i++) {

			Node obj = queues.item(i);
			System.out.println();

			System.out.println("Fila: " + obj.getAttributes().getNamedItem("id").getNodeValue());

			NodeList attrs = obj.getChildNodes();

			for (int j = 0; j < attrs.getLength(); j++) {

				if (!attrs.item(j).getNodeName().equals("#text")) {

					System.out.print("\t" + attrs.item(j).getNodeName() + ":");
					System.out.println(attrs.item(j).getFirstChild().getTextContent());

				}

			}

		}

		return null;

	}




	public List<Contact> retornarContacts() throws IOException, SAXException, ParserConfigurationException {

		String resposta = sendRequest("/results", null, RequestMethod.GET);
		
		List<Contact> listCotacts = null;
		
		if (this.statusCode >= 200 && this.statusCode <= 299) {
				
			listCotacts = new ArrayList<Contact>();

			Document doc = carregarXMLFromString(resposta);
			
			NodeList nodeListContact = doc.getElementsByTagName("contact");

			if (doc != null && nodeListContact!=null && nodeListContact.getLength()>0)
				escreverXml(doc);
			
			for (int i = 0; i < nodeListContact.getLength(); i++) {

				Node nodeContact = nodeListContact.item(i);
			
				NodeList nodeListNumbers = nodeContact.getChildNodes();
				
				Contact contact = new Contact();
				contact.setId(Long.valueOf(nodeContact.getAttributes().getNamedItem("id").getNodeValue()).longValue());
				contact.setStatus(nodeContact.getAttributes().getNamedItem("status").getNodeValue());
				contact.setCodQueue(nodeContact.getAttributes().getNamedItem("queue").getNodeValue());
								
				List<Number> listNumber = new ArrayList<Number>();

				for (int j = 0; j < nodeListNumbers.getLength(); j++) {

					Node nodeNumber = nodeListNumbers.item(j);

					Number number = new Number();

					if (nodeNumber.getNodeType() == Node.ELEMENT_NODE) {

						Element elementNumber = (Element) nodeNumber;
						number.setId(Long.valueOf(elementNumber.getAttribute("id")).longValue());
						number.setCodRetorno(Long.valueOf(elementNumber.getTextContent()).longValue());
						number.setCallFilename(elementNumber.getAttribute("callfilename"));
						number.setDestino(elementNumber.getAttribute("destination"));
						number.setDuracao((StringUtils.isNotBlank(elementNumber.getAttribute("destination"))? Long.valueOf(elementNumber.getAttribute("destination")): null));
						number.setDataAtendimento((StringUtils.isNotBlank(elementNumber.getAttribute("answered_at"))? DateUtil.builder(elementNumber.getAttribute("answered_at")).formatarStringParaData("dd/MM/yyyy HH:mm:ss").getData(): null));
						number.setDataDiscagem((StringUtils.isNotBlank(elementNumber.getAttribute("dialed_at"))	? DateUtil.builder(elementNumber.getAttribute("dialed_at")).formatarStringParaData("dd/MM/yyyy HH:mm:ss").getData(): null));
						number.setDataHangup((StringUtils.isNotBlank(elementNumber.getAttribute("hangup_at"))? DateUtil.builder(elementNumber.getAttribute("hangup_at")).formatarStringParaData("dd/MM/yyyy HH:mm:ss").getData(): null));

						listNumber.add(number);

					}

				}

				contact.setListNumber(listNumber);
				listCotacts.add(contact);
			}
		
		}
		
		return listCotacts;

	}
	public List<Contact> retornarContacts(int timeout) throws IOException, SAXException, ParserConfigurationException {
		
		
		String resposta = sendRequest("/results", null, RequestMethod.GET,timeout);
		
		List<Contact> listCotacts = null;
		
		if (this.statusCode >= 200 && this.statusCode <= 299) {
			
			listCotacts = new ArrayList<Contact>();
			
			Document doc = carregarXMLFromString(resposta);
			
			NodeList nodeListContact = doc.getElementsByTagName("contact");
			
			if (doc != null && nodeListContact!=null && nodeListContact.getLength()>0)
				escreverXml(doc);
			
			for (int i = 0; i < nodeListContact.getLength(); i++) {
				
				Node nodeContact = nodeListContact.item(i);
				
				NodeList nodeListNumbers = nodeContact.getChildNodes();
				
				Contact contact = new Contact();
				contact.setId(Long.valueOf(nodeContact.getAttributes().getNamedItem("id").getNodeValue()).longValue());
				contact.setStatus(nodeContact.getAttributes().getNamedItem("status").getNodeValue());
				contact.setCodQueue(nodeContact.getAttributes().getNamedItem("queue").getNodeValue());
				
				contact.setWillRetry(retornarRetry(nodeContact));
				
				List<Number> listNumber = new ArrayList<Number>();
				
				for (int j = 0; j < nodeListNumbers.getLength(); j++) {
					
					Node nodeNumber = nodeListNumbers.item(j);
					
					Number number = new Number();
					
					if (nodeNumber.getNodeType() == Node.ELEMENT_NODE) {
						
						Element elementNumber = (Element) nodeNumber;
					
						if(StringUtils.isNumeric(elementNumber.getAttribute("id"))) {
							
							number.setId(Long.valueOf(elementNumber.getAttribute("id")).longValue());
							
							if(StringUtils.isNumeric(elementNumber.getTextContent()))
								number.setCodRetorno(Long.valueOf(elementNumber.getTextContent()).longValue());
							
							number.setCallFilename(elementNumber.getAttribute("callfilename"));
							number.setDestino(elementNumber.getAttribute("destination"));
							number.setDuracao((StringUtils.isNotBlank(elementNumber.getAttribute("duration"))? Long.valueOf(elementNumber.getAttribute("duration")): null));
							number.setDataAtendimento((StringUtils.isNotBlank(elementNumber.getAttribute("answered_at"))? DateUtil.builder(elementNumber.getAttribute("answered_at")).formatarStringParaData("dd/MM/yyyy HH:mm:ss").getData(): null));
							number.setDataDiscagem((StringUtils.isNotBlank(elementNumber.getAttribute("dialed_at"))	? DateUtil.builder(elementNumber.getAttribute("dialed_at")).formatarStringParaData("dd/MM/yyyy HH:mm:ss").getData(): null));
							number.setDataHangup((StringUtils.isNotBlank(elementNumber.getAttribute("hangup_at"))? DateUtil.builder(elementNumber.getAttribute("hangup_at")).formatarStringParaData("dd/MM/yyyy HH:mm:ss").getData(): null));
							number.setIrvDigit(elementNumber.getAttribute("ivr_digit"));
							listNumber.add(number);
						
						}
					}
					
				}
				
				contact.setListNumber(listNumber);
				listCotacts.add(contact);
			}
			
		}
		
		return listCotacts;
		
	}
	
	private Boolean retornarRetry(Node nodeContact) {
		
		try {
			
		
				return nodeContact.getAttributes().getNamedItem("will_retry").getNodeValue().equals("yes") ?Boolean.TRUE:Boolean.FALSE;
			
		
		
		}catch (Exception e) {
		
		}
		
		return null;
	}

	public List<Contact> retornarResultsPorXmlFile(File file) {

		List<Contact> listCotacts = null;

		if (file.exists() && file.isFile()) {

			Document doc = convertXMLFileToXMLDocument(file.getAbsolutePath());
					
			listCotacts = new ArrayList<Contact>();

			NodeList nodeListContact = doc.getElementsByTagName("contact");


			for (int i = 0; i < nodeListContact.getLength(); i++) {
				
				Node nodeContact = nodeListContact.item(i);
				
				NodeList nodeListNumbers = nodeContact.getChildNodes();
				
				Contact contact = new Contact();
				contact.setId(Long.valueOf(nodeContact.getAttributes().getNamedItem("id").getNodeValue()).longValue());
				contact.setStatus(nodeContact.getAttributes().getNamedItem("status").getNodeValue());
				contact.setCodQueue(nodeContact.getAttributes().getNamedItem("queue").getNodeValue());
				
				contact.setWillRetry(retornarRetry(nodeContact));
				
				List<Number> listNumber = new ArrayList<Number>();

				
				for (int j = 0; j < nodeListNumbers.getLength(); j++) {

					Node nodeNumber = nodeListNumbers.item(j);
					
					Number number = new Number();

					if (nodeNumber.getNodeType() == Node.ELEMENT_NODE) {

						Element elementNumber = (Element) nodeNumber;
						number.setId(Long.valueOf(elementNumber.getAttribute("id")).longValue());
						number.setCodRetorno(Long.valueOf(elementNumber.getTextContent().trim()).longValue());
						number.setCallFilename(elementNumber.getAttribute("callfilename"));
						number.setDestino(elementNumber.getAttribute("destination"));
						number.setIrvDigit(elementNumber.getAttribute("ivr_digit"));
						number.setDuracao((StringUtils.isNotBlank(elementNumber.getAttribute("duration"))? Long.valueOf(elementNumber.getAttribute("duration")): null));
						number.setDataAtendimento((StringUtils.isNotBlank(elementNumber.getAttribute("answered_at"))? DateUtil.builder(elementNumber.getAttribute("answered_at")).formatarStringParaData("dd/MM/yyyy HH:mm:ss").getData(): null));
						number.setDataDiscagem((StringUtils.isNotBlank(elementNumber.getAttribute("dialed_at"))	? DateUtil.builder(elementNumber.getAttribute("dialed_at")).formatarStringParaData("dd/MM/yyyy HH:mm:ss").getData(): null));
						number.setDataHangup((StringUtils.isNotBlank(elementNumber.getAttribute("hangup_at"))? DateUtil.builder(elementNumber.getAttribute("hangup_at")).formatarStringParaData("dd/MM/yyyy HH:mm:ss").getData(): null));
						
						listNumber.add(number);

						
						
					}
					
				}
				
				contact.setListNumber(listNumber);
				listCotacts.add(contact);
			}
			
		}

		
		return listCotacts;
		
		

	}
	
	private Document carregarXMLFromString(String xml) throws SAXException, IOException, ParserConfigurationException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		InputSource is = new InputSource(new StringReader(xml));

		return builder.parse(is);

	}

	
	private void escreverXml(Document doc) {

		File file = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator	+ "retorno_xml" + File.separator + "results_vonix_"+DateUtil.builder().formatarDataParaString("yyyyMMdd").getDataTexto());

		boolean create = true;
		
		if(!file.exists()) {
			
			create = file.mkdirs();
		}
		
		if(create)
			escreverXmlDocumentToXmlFile(doc, file.getAbsolutePath()+File.separator+"results_vonix_"+DateUtil.builder().formatarDataParaString("yyyyMMddHHmmss").getDataTexto()+".xml");
		
	}


	public String getRespondeMessage() {
		return respondeMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public static void main(String[] args) {

		try {
			
		VonixClient vonix = new VonixClient("http://10.8.1.251:8003", "proativa", "PsYq29XzK");
		
		System.out.println(vonix.consultarContatosFila("bigbox")+" - TOTAL");

	//	System.out.println(vonix.consultarFila("bigbox"));
	//	System.err.println(vonix.getStatusCode()+" - "+vonix.getRespondeMessage());
	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Document convertXMLFileToXMLDocument(String filePath) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = null;

		try {

			builder = factory.newDocumentBuilder();
			System.out.println(filePath);


			return  builder.parse(new File(filePath));

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;
	}

	private static void escreverXmlDocumentToXmlFile(Document xmlDocument, String fileName) {

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;

		try {

			transformer = tf.newTransformer();

			// Escrever XML to file
			FileOutputStream outStream = new FileOutputStream(new File(fileName));

			transformer.transform(new DOMSource(xmlDocument), new StreamResult(outStream));

		} catch (TransformerException e) {

			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * private static String lerXmlDocumentToXmlFile(Document xmlDocument) {
	 * 
	 * TransformerFactory tf = TransformerFactory.newInstance(); Transformer
	 * transformer;
	 * 
	 * try {
	 * 
	 * transformer = tf.newTransformer();
	 * 
	 * StringWriter writer = new StringWriter(); transformer.transform(new
	 * DOMSource(xmlDocument), new StreamResult(writer)); String xmlString =
	 * writer.getBuffer().toString();
	 * 
	 * return xmlString;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return null; }
	 */
}
