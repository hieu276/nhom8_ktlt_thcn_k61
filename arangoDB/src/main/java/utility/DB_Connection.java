package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.arangodb.ArangoDB;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.QueryEntity;
import com.arangodb.model.AqlQueryOptions;

public class DB_Connection {


	public static void createConnection() {
		// set configuration
//		 ArangoDB arangoDB = new ArangoDB.Builder().build();
		 
		 ArangoDB a1 = new ArangoDB.Builder().user("root").password("08021998").build();
		
		// Create Driver (this instance is thread-safe)
		 
//		 CollectionEntity myArangoCollection = arangoDB.db(CommonConst.KEY_DB.DATABASE).createCollection("Person");
		
//		 BaseDocument baseDocument = new BaseDocument("0002");
//		 baseDocument.addAttribute("name", "Nam");
//		 
//		 a1.db(CommonConst.KEY_DB.DATABASE).collection("city").insertDocument(baseDocument);
		 
		 BaseDocument baseDocument = a1.db(CommonConst.KEY_DB.DATABASE).collection("city").getDocument("0002", BaseDocument.class);
		 
		 
		 String s = "FOR C IN city return C";
		 
//		 PreparedStatement preparedStatement =
		 
		 a1.shutdown();
		 
//		return arangoDB;
	}
}
