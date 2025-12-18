package com.proativaservicos.service.api;


import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.proativaservicos.dao.implemets.MongoClientProvider;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

public class MongoDB {

    private final String dataBase;

    private final String collection;

    private MongoDatabase mongoBase;

    public MongoDB(String dataBase, String collection) {

        this.dataBase = dataBase;
        this.collection = collection;

        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);


    }


    public String inserirDoc(Document doc) {

        try {
            MongoClient mongoClient = MongoClientProvider.getMongoClient();

            MongoDatabase mongoBase = mongoClient.getDatabase(this.dataBase);

            criarCollection(mongoBase, this.collection);

            MongoCollection<Document> colection = mongoBase.getCollection(this.collection);

            colection.insertOne(doc);

            if (doc.getObjectId("_id") != null) {

                return doc.getObjectId("_id").toString();
            }

            return null;

        } catch (Exception e) {

            if (!(e instanceof MongoWriteException && e.getMessage().contains("WriteError{code=11000, message='E11000 duplicate key error collection:")))
                e.printStackTrace();


        }

        return null;

    }

    public void alterarDoc(List<Document> list, String idObj) {

        if (CollectionUtils.isEmpty(list))
            return;

        try {
            MongoClient mongoClient = MongoClientProvider.getMongoClient();
            MongoDatabase mongoBase = mongoClient.getDatabase(this.dataBase);

            criarCollection(mongoBase, this.collection);

            MongoCollection<Document> colection = mongoBase.getCollection(this.collection);

            List<Bson> listUpdates = new ArrayList<Bson>();

            for (Document document : list) {

                for (String str : document.keySet()) {

                    listUpdates.add(Updates.set(str, document.get(str)));
                }
            }

            colection.updateOne(eq("_id", new ObjectId(idObj)), Updates.combine(listUpdates));

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public void alterarDocById(String field, Object value, String idObj) {

        if (value == null || StringUtils.isBlank(field))
            return;

        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);


        try {
            MongoClient mongoClient = MongoClientProvider.getMongoClient();
            MongoDatabase mongoBase = mongoClient.getDatabase(this.dataBase);
            criarCollection(mongoBase, this.collection);

            MongoCollection<Document> colection = mongoBase.getCollection(this.collection);
            Document document = new Document();
            document.append(field, value);
            List<Document> list = new ArrayList<>();
            list.add(document);

            colection.updateOne(eq("_id", new ObjectId(idObj)), Updates.combine(retornarListaUpdates(list)));

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public void alterarDocById(String idObj, Document document) {

        if (StringUtils.isBlank(idObj))
            return;

        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);


        try {

            MongoClient mongoClient = MongoClientProvider.getMongoClient();
            MongoDatabase mongoBase = mongoClient.getDatabase(this.dataBase);
            criarCollection(mongoBase, this.collection);

            MongoCollection<Document> colection = mongoBase.getCollection(this.collection);

            colection.updateOne(eq("_id", new ObjectId(idObj)), new Document("$set", document)
            );

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public void inserirLista(String idObj, List<?> list) {


        if (StringUtils.isBlank(idObj))
            return;

        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);
        MongoDB db = new MongoDB("calls_3c", "call");

        try {
            MongoClient mongoClient = MongoClientProvider.getMongoClient();

            MongoDatabase mongoBase = mongoClient.getDatabase(this.dataBase);
            criarCollection(mongoBase, this.collection);

            MongoCollection<Document> colection = mongoBase.getCollection(this.collection);

            colection.updateOne(eq("_id", new ObjectId(idObj)), Updates.pushEach("atendimentos_erro", Collections.singletonList(list))
            );

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    public void inserirLista(String idObj, String arrayName, Object arrayValue) {


        if (StringUtils.isBlank(idObj))
            return;

        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);
        MongoDB db = new MongoDB("calls_3c", "call");

        try {
            MongoClient mongoClient = MongoClientProvider.getMongoClient();

            MongoDatabase mongoBase = mongoClient.getDatabase(this.dataBase);
            criarCollection(mongoBase, this.collection);

            MongoCollection<Document> colection = mongoBase.getCollection(this.collection);

            colection.updateOne(eq("_id", new ObjectId(idObj)), Updates.addToSet(arrayName, arrayValue)
            );

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    public void incrementarConsultado(String idObj, String field, int valorASomar) {

        if (StringUtils.isBlank(idObj))
            return;

        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);

        try {
            MongoClient mongoClient = MongoClientProvider.getMongoClient();
            MongoDatabase mongoBase = mongoClient.getDatabase(this.dataBase);
            criarCollection(mongoBase, this.collection);

            MongoCollection<Document> collection = mongoBase.getCollection(this.collection);

            collection.updateOne(
                    Filters.eq("_id", new ObjectId(idObj)),
                    Updates.inc(field, valorASomar)
            );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private List<Bson> retornarListaUpdates(List<Document> list) {

        List<Bson> listUpdates = new ArrayList<>();

        for (Document document : list) {

            for (String str : document.keySet()) {

                listUpdates.add(Updates.set(str, document.get(str)));

            }
        }

        return listUpdates;
    }

    private List<Bson> retornarListaUpdatesMap(Map<String, Object> map) {

        List<Bson> listUpdates = new ArrayList<>();

        for (String str : map.keySet()) {

            listUpdates.add(Updates.set(str, map.get(str)));


        }

        return listUpdates;
    }

    public List<DocImportacaoDiscador> retornarDocPorStatus(String strImportacao) {


        try {
            MongoClient mongoClient = MongoClientProvider.getMongoClient();

            CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

            MongoDatabase mongo = mongoClient.getDatabase("importacao").withCodecRegistry(pojoCodecRegistry);

            criarCollection(mongoClient.getDatabase("importacao"), "importacao");

            mongo.withCodecRegistry(pojoCodecRegistry);

            MongoCollection<DocImportacaoDiscador> alunos = mongo.getCollection("importacao_log", DocImportacaoDiscador.class);

            MongoCursor<DocImportacaoDiscador> cursor = alunos.find(eq("status_importacao", strImportacao)).iterator();

            List<DocImportacaoDiscador> listDocImportacao = new ArrayList<>();

            while (cursor.hasNext()) {

                listDocImportacao.add(cursor.next());

            }

            return listDocImportacao;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<DocImportacao> retornarDocImportacaoPorStatus(String strImportacao) {


        try {

            MongoClient cliente = MongoClientProvider.getMongoClient();
            CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

            MongoDatabase mongo = cliente.getDatabase("importacao").withCodecRegistry(pojoCodecRegistry);

            criarCollection(cliente.getDatabase("importacao"), "importacao_log");

            mongo.withCodecRegistry(pojoCodecRegistry);

            MongoCollection<DocImportacao> alunos = mongo.getCollection("importacao_log", DocImportacao.class);
            Document filtro = new Document("status", new Document("$regex", ".*" + strImportacao + ".*").append("$options", "i"));

            MongoCursor<DocImportacao> cursor = alunos.find(filtro).iterator();

            List<DocImportacao> listDocImportacao = new ArrayList<>();

            while (cursor.hasNext()) {

                listDocImportacao.add(cursor.next());

            }

            return listDocImportacao;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public Document retornarPorImportacao(Long idImportacao) {


        try {
            MongoClient mongoClient = MongoClientProvider.getMongoClient();

            MongoDatabase database = mongoClient.getDatabase("importacao");
            MongoCollection<Document> collection = database.getCollection("importacao_log");

            Document filtro = new Document("id_importacao", idImportacao);
            Document resultado = collection.find(filtro).first();

            return resultado; // Isso é um Document BSON


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public Document retornarPorStatusImportacao(String statusImportacao) {


        try {

            MongoClient mongoClient = MongoClientProvider.getMongoClient();

            MongoDatabase database = mongoClient.getDatabase("importacao");
            MongoCollection<Document> collection = database.getCollection("importacao_log");

            Document filtro = new Document("status", new Document("$regex", statusImportacao + ".*").append("$options", "i"));


            List<Document> resultado = new ArrayList<>();

            collection.find(filtro).into(resultado);

            for (Document document : resultado) {
                for (String str : document.keySet()) {
                    System.out.println(str + ": " + document.get(str));
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    public List<Call3c> retornarListaDoc(String fildName, Object value, String collection, String db) {


        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);

        try {
            MongoClient mongoClient = MongoClientProvider.getMongoClient();
            CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

            MongoDatabase mongo = mongoClient.getDatabase(db).withCodecRegistry(pojoCodecRegistry);

            criarCollection(mongoClient.getDatabase(db), collection);

            mongo.withCodecRegistry(pojoCodecRegistry);

            MongoCollection<Call3c> collectionMongo = mongo.getCollection(collection, Call3c.class);

            MongoCursor<Call3c> cursor = collectionMongo.find(eq(fildName, value)).iterator();

            List<Call3c> list = new ArrayList<>();

            while (cursor.hasNext()) {

                list.add(cursor.next());

            }

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    private void criarCollection(MongoDatabase db, String collectionName) {

        try {

            Logger logger = Logger.getLogger("org.mongodb.driver");
            logger.setLevel(Level.SEVERE);
            db.createCollection(collectionName);

        } catch (MongoCommandException ignored) {

        }
    }

    public static void main(String[] args) {

        Integer total = 6910;

        MongoDB mongoDB = new MongoDB("importacao", "importacao_log");
        List<DocImportacao> docImportacaos = mongoDB.retornarDocImportacaoPorStatus("IMPORTANDO");

        for (DocImportacao docImportacao : docImportacaos) {
            System.out.println(docImportacao);
        }

        mongoDB.incrementarConsultado("68b1a19de15c5830c6408a2e","total_atendimentos",1);



   /*     Document docTeste = new Document();
        docTeste.append("campanha", "TESTE");
        docTeste.append("id_campanha", 15L);
        docTeste.append("arquivo", "teste.csv");
        docTeste.append("total_atendimento", 1510);
        docTeste.append("status_importacao", "CONSULTANDO");
        docTeste.append("quantidade_consultado", 10);
        docTeste.append("atendimentos_erro", null);
        docTeste.append("quantidade_erros", 10);
        docTeste.append("date_inicial", new Date());
        docTeste.append("date_final", new Date());

        mongoDB.alterarDocById("68a866f967f8ce753f161302", "", "");
*/

    /*    List<Call3c> list = (List<Call3c>) mongo.retornarListaDoc("processado", false, "call", "calls_3c");

        Document doc = new Document();
        doc.append("processado", false);
        List<Document> lista = new ArrayList<>();
        lista.add(doc);
        list.forEach(call3c -> {
            mongo.alterarDocById("processado", false, call3c.getId().toString());
        });

        Document docTeste= new Document();
        doc.append("campanha", "TESTE");
        docTeste.append("id_campanha", 15L); doc.append("arquivo", null);
        docTeste.append("quantidade_total_lote", 0); doc.append("lote_atual", 0);
        docTeste.append("total_atendimento_lote_atual", 0);
        docTeste.append("total_atendimento", 0); doc.append("status_importacao", "IMPOSRADA");
        docTeste.append("erro_envio_lote", null); doc.append("atendimentos_erro", null);
        docTeste.append("date", new Date());*/


        /*
		
		
		  Document doc= new Document(); doc.append("campanha", "TESTE");
		  doc.append("id_campanha", 15L); doc.append("arquivo", null);
		  doc.append("quantidade_total_lote", 0); doc.append("lote_atual", 0);
		  doc.append("total_atendimento_lote_atual", 0);
		  doc.append("total_atendimento", 0); doc.append("status_importacao", "IMPOSRADA");
		  doc.append("erro_envio_lote", null); doc.append("atendimentos_erro", null);
		  doc.append("date", new Date());
			
		  List<JsonArray> listArray = new ArrayList<JsonArray>();
		  JsonArray jsonArrayAtendimentos = new JsonArray();
			JsonObject jsonAtendimento = new JsonObject();

			jsonAtendimento.addProperty("id", 1000L);
			
			jsonAtendimento.addProperty("contact_name",	"RODRIGO");

			// VALIDAR POSSIBILIDADE DE PRIORIDADE
			jsonAtendimento.addProperty("billing_group_id",  "1");
			
			jsonArrayAtendimentos.add(jsonAtendimento);
			listArray.add(jsonArrayAtendimentos);
			List<String> listString = new ArrayList<String>();
			listString.add("1522");
			listString.add("11111");
			System.out.println(listArray);
		  doc.append("atendimentos_erro", jsonArrayAtendimentos);
		  
		  List<Document> list = new ArrayList<Document>();
		  list.add(doc);
		  mongo = new MongoDB("importacao", "importacao_log");
		  
		  mongo.alterarDoc(list,"62be17b00e8ba70b429c1d25");*/



        /*
         * for (DocImportacao string : mongo.retornarDocPorStatus("IMPOS")) {
         *
         * System.out.println(string);
         *
         * System.out.println(string.getTempo());
         *
         *
         *
         *
         * }
         */


    }

}
