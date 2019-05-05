package dao;

import java.util.ArrayList;
import java.util.List;

import com.arangodb.ArangoDB;

import entity.Person;

public class PersonDao {

	public List<Person> getListPerson() {
		List<Person> listData = new ArrayList<Person>();
		
		ArangoDB arangoDB = new ArangoDB.Builder().build();
		
//		Connection connection = 
		
		return listData;
	}
}
