package springboot.mongo.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.http.converter.json.GsonBuilderUtils;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import springboot.config.ApplicationContextUtils;
import springboot.config.MainConfig;
import springboot.config.YAMLConfig;
import springboot.hbn.entities.TblProduct;
import springboot.hbn.home.TblProductHome;
import springboot.utils.GsonUltilities;

public class BaseMongo {

//	YAMLConfig yamlConfig = ApplicationContextUtils.getApplicationContext().getBean(YAMLConfig.class);

	// Mongodb connection string.

	// Connecting to the mongodb server using the given client uri.
	MongoClient mongo_client = null;
	// Fetching the database from the mongodb.
	MongoDatabase db = null;

	/**
	 * static Singleton instance.
	 */
	private static volatile BaseMongo instance;

	/**
	 * Private constructor for singleton.
	 */
	private BaseMongo() {
		try {

//			int port_no = yamlConfig.getMongo_port();
//			String host_name = yamlConfig.getMongo_ip(), db_name = yamlConfig.getMongo_database();
			int port_no = MainConfig.MONGO_PORT;
			String host_name = MainConfig.MONGO_HOST, db_name = MainConfig.MONGO_DATABASE;

			String client_url = "mongodb://" + host_name + ":" + port_no + "/" + db_name;
			MongoClientURI uri = new MongoClientURI(client_url);
			mongo_client = new MongoClient(uri);
			db = mongo_client.getDatabase(db_name);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Return a singleton instance of TblWardDao.
	 */
	public static BaseMongo getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (BaseMongo.class) {
				if (instance == null) {
					instance = new BaseMongo();
				}
			}
		}
		return instance;
	}

	public Boolean insertDocument(ArrayList<Document> listDoc, String collection) {
		// TODO Auto-generated method stub
		try {
			// Fetching the collection from the mongodb.
			MongoCollection<Document> coll = db.getCollection(collection);
			coll.insertMany(listDoc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Boolean insertDocument(String collection, Document doc) {
		// TODO Auto-generated method stub
		try {
			// Fetching the collection from the mongodb.
			MongoCollection<Document> coll = db.getCollection(collection);
			coll.insertOne(doc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Xóa collection May 26, 2021-2:43:41 PM Vietda
	 * 
	 * @param collection
	 * @return
	 */
	public Boolean dropCollection(String collection) {
		// TODO Auto-generated method stub
		try {
			// Fetching the collection from the mongodb.
			MongoCollection<Document> coll = db.getCollection(collection);
			coll.drop();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public Long updateByID(String collection, String id, String Json) {
		try {
			Bson product_id = new Document("_id", new ObjectId(id));
//			TblProduct tblProduct = (TblProduct)new TblProductHome().findById(212, new TblProduct());
//			tblProduct.setProductName("update now");
//			Document document = Document.parse(GsonUltilities.toJson(tblProduct));
			Document document = Document.parse(Json);
			Bson updateVl = new Document("$set", document);
			UpdateResult upaResult = db.getCollection(collection).updateOne(product_id, updateVl);
			System.out.println("Update rs:" + upaResult.getMatchedCount());
			return upaResult.getMatchedCount();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Long(-1);
		}
	}

	public Long deleteByBson(String collection, String id) {
		try {
			DeleteResult deleteRs = db.getCollection(collection).deleteOne(new Document("_id", new ObjectId(id)));
			System.out.println("Update rs:" + deleteRs.getDeletedCount());

			return deleteRs.getDeletedCount();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Long(-1);
		}
	}

	public Long updateByBson(String collection, Bson bsonObj, String Json) {
		try {
			Document document = Document.parse(Json);
			Bson updateVl = new Document("$set", document);
			UpdateResult upaResult = db.getCollection(collection).updateMany(bsonObj, updateVl);
			System.out.println("Update rs:" + upaResult.getMatchedCount());
			return upaResult.getMatchedCount();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Long(-1);
		}
	}

	public FindIterable<Document> getAllOldocs(String collection) {
		try {
			FindIterable<Document> listAllData = db.getCollection(collection).find();
			for (Document document : listAllData) {
				System.out.println(document.toJson());
				JSONObject jsonObject = new JSONObject(document.toJson());
				System.out.println(jsonObject.getJSONObject("_id").get("$oid"));
			}
			return listAllData;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public void getAllOldocument(String collection) {
		try {

			BasicDBObject allQuery = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject();
			fields.put("product_id", 212);
			Bson product_id = new Document("_id", new ObjectId("60acadbdbc9a403444f786f0"));
//			TblProduct tblProduct = (TblProduct)new TblProductHome().findById(212, new TblProduct());
//			tblProduct.setProductName("update now");
//			Document document = Document.parse(GsonUltilities.toJson(tblProduct));
			Document document = Document
					.parse("{\"product_name\":\"update new\",\"product_code\":\"update 3 code ne\"}");
			Bson updateVl = new Document("$set", document);
			UpdateResult upaResult = db.getCollection(collection).updateOne(product_id, updateVl);
			System.out.println("Update rs:" + upaResult.getMatchedCount());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void cleanCollection(String collection) {
		FindIterable<Document> allDocs = BaseMongo.getInstance().getAllOldocs(collection);
		for (Document document : allDocs) {
			System.out.println(document.toJson());
			JSONObject jsonObject = new JSONObject(document.toJson());
			String id = jsonObject.getJSONObject("_id").get("$oid").toString();
			BaseMongo.getInstance().deleteByBson(collection, id);
		}
	}

	public static void main(String[] args) {
//		BaseMongo.getInstance().getAllOldocs("tbl_product");
//		
//		TblProduct tblProduct = new TblProduct();
//		tblProduct.setProductName("Win 100");
//		tblProduct.setProductCode("xe cùi");
//
//		Bson filter = new Document("product_id", 536);
//		Long updateRs = BaseMongo.getInstance().updateByBson("tbl_product", filter,GsonUltilities.toJson(tblProduct));
//		System.out.println(updateRs);
//		BaseMongo.getInstance().getAllOldocument("tbl_product");
//		BaseMongo.getInstance().getAllOldocs("tbl_extra_fee");
		BaseMongo.getInstance().cleanCollection("tbl_extra_fee");
	}
}
