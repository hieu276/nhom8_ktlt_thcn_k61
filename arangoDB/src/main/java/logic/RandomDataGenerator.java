package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import entity.*;

public class RandomDataGenerator {
	private final Connection connection = new Neo4jConnection().getConnection("jdbc:neo4j:bolt://localhost:11004",
			"neo4j", "123456");
	// link
	private ArrayList<String> urls = new ArrayList<String>();
	// location
	private ArrayList<String> cities = new ArrayList<String>();
	private ArrayList<String> locations = new ArrayList<String>();
	private ArrayList<String> locationDescriptions = new ArrayList<String>();
	// person
	private ArrayList<String> fullNames = new ArrayList<String>();
	private ArrayList<String> personDescriptions = new ArrayList<String>();
	// organizations
	private ArrayList<String> organizations = new ArrayList<String>();
	private ArrayList<String> organizationDescriptions = new ArrayList<String>();
	//countries
	private ArrayList<String> countries = new ArrayList<String>();
	private ArrayList<String> capitals = new ArrayList<String>();
	private ArrayList<String> countryDescriptions = new ArrayList<String>();
	// events
	private ArrayList<String> events = new ArrayList<String>();
	private ArrayList<String> eventDescriptions = new ArrayList<String>();
	// agreements
	private ArrayList<String> agreements = new ArrayList<String>();
	private ArrayList<String> agreementDescriptions = new ArrayList<String>();
	
	private int personsNumber = 1;
	private int organizationsNumber = 1;
	private int locationsNumber = 1;
	private int countriesNumber = 1;
	private int eventsNumber = 1;
	private int timesNumber = 1;
	private int agreementsNumber = 1;
	private static Random random = new Random();
        
	// read file text lay data truoc khi random
	public RandomDataGenerator() {
		try {   
			BufferedReader urlFile = new BufferedReader(new FileReader("src/main/resources/data/url.txt"));
			String url;
			while ((url = urlFile.readLine()) != null) {
				urls.add(url);
			}
			BufferedReader cityFile = new BufferedReader(new FileReader("src/main/resources/data/city.txt"));
			String city;
			while ((city = cityFile.readLine()) != null) {
				cities.add(city);
			}
			BufferedReader agreementFile = new BufferedReader(new FileReader("src/main/resources/data/agreement.txt"));
			String agreement;
			while ((agreement = agreementFile.readLine()) != null) {
				agreements.add(agreement);
			}
			BufferedReader agreementDescriptionFile = new BufferedReader(
					new FileReader("src/main/resources/data/agreement description.txt"));
			String agreementDescription;
			while ((agreementDescription = agreementDescriptionFile.readLine()) != null) {
				agreementDescriptions.add(agreementDescription);
			}
			BufferedReader capitalFile = new BufferedReader(new FileReader("src/main/resources/data/capital.txt"));
			String capital;
			while ((capital = capitalFile.readLine()) != null) {
				capitals.add(capital);
			}
			BufferedReader countryFile = new BufferedReader(new FileReader("src/main/resources/data/country.txt"));
			String country;
			while ((country = countryFile.readLine()) != null) {
				countries.add(country);
			}
			BufferedReader countryDescriptionFile = new BufferedReader(
					new FileReader("src/main/resources/data/country description.txt"));
			String countryDescription;
			while ((countryDescription = countryDescriptionFile.readLine()) != null) {
				countryDescriptions.add(countryDescription);
			}
			BufferedReader eventFile = new BufferedReader(new FileReader("src/main/resources/data/event.txt"));
			String event;
			while ((event = eventFile.readLine()) != null) {
				events.add(event);
			}
			BufferedReader eventDescriptionFile = new BufferedReader(
					new FileReader("src/main/resources/data/event description.txt"));
			String eventDescription;
			while ((eventDescription = eventDescriptionFile.readLine()) != null) {
				eventDescriptions.add(eventDescription);
			}
			BufferedReader fullNameFile = new BufferedReader(new FileReader("src/main/resources/data/full name.txt"));
			String fullName;
			while ((fullName = fullNameFile.readLine()) != null) {
				fullNames.add(fullName);
			}
			BufferedReader locationFile = new BufferedReader(new FileReader("src/main/resources/data/location.txt"));
			String location;
			while ((location = locationFile.readLine()) != null) {
				locations.add(location);
			}
			BufferedReader locationDescriptionFile = new BufferedReader(
					new FileReader("src/main/resources/data/location description.txt"));
			String locationDescription;
			while ((locationDescription = locationDescriptionFile.readLine()) != null) {
				locationDescriptions.add(locationDescription);
			}
			BufferedReader organizationFile = new BufferedReader(
					new FileReader("src/main/resources/data/organization.txt"));
			String organization;
			while ((organization = organizationFile.readLine()) != null) {
				organizations.add(organization);
			}
			BufferedReader organizationDescriptionFile = new BufferedReader(
					new FileReader("src/main/resources/data/organization description.txt"));
			String organizationDescription;
			while ((organizationDescription = organizationDescriptionFile.readLine()) != null) {
				organizationDescriptions.add(organizationDescription);
			}
			BufferedReader personDescriptionFile = new BufferedReader(
					new FileReader("src/main/resources/data/person description.txt"));
			String personDescription;
			while ((personDescription = personDescriptionFile.readLine()) != null) {
				personDescriptions.add(personDescription);
			}
			agreementFile.close();
			agreementDescriptionFile.close();
			capitalFile.close();
			cityFile.close();
			countryFile.close();
			countryDescriptionFile.close();
			eventFile.close();
			eventDescriptionFile.close();
			fullNameFile.close();
			locationFile.close();
			locationDescriptionFile.close();
			organizationFile.close();
			organizationDescriptionFile.close();
			personDescriptionFile.close();
			urlFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
        //Ham random
	private int generateRandomBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}
        //Random date-month-year
	private String generateRandomDate(int startYear, int endYear) {
		int month = generateRandomBetween(1, 12);
		int year = generateRandomBetween(startYear, endYear);
		return month + "/" + year;
	}
        //Ramdom tao Person( co ID, Label, Describe, Age- new, Gender- new)
	private void generatePerson(int id) {
		try {
			Person person = new Person();
			person.setId("person " + id);
			person.setLabel(fullNames.get(random.nextInt(fullNames.size())));
			person.setDescribe(personDescriptions.get(random.nextInt(personDescriptions.size())));
			person.setAge(random.nextInt(100));
			person.setGender(random.nextInt(2) == 1 ? "Male" : "Female");
			String query = "CREATE (:Person{id:{1}, name:{2}, describe:{3}, age:{4}, gender:{5}})";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, person.getId());
			preparedStatement.setString(2, person.getLabel());
			preparedStatement.setString(3, person.getDescribe());
			preparedStatement.setInt(4, person.getAge());
			preparedStatement.setString(5, person.getGender());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        //Random tao Organization( co ID, Label, Describe, Headquarters- new, Website- new)
	private void generateOrganization(int id) { 
		try {
			ArrayList<String> domains = new ArrayList<String>(
					Arrays.asList(".com", ".com.vn", ".edu", ".co", ".ac", ".info", ".vn", ".edu.vn"));
			Organization organization = new Organization();
			organization.setId("organization " + id);
			organization.setLabel(organizations.get(random.nextInt(organizations.size())));
			organization.setDescribe(organizationDescriptions.get(random.nextInt(organizationDescriptions.size())));
			organization
					.setWebsite("https://www." + organization.getLabel() + domains.get(random.nextInt(domains.size())));
			organization.setHeadquarters(cities.get(random.nextInt(cities.size())));
			String query = "CREATE (:Organization{id:{1}, name:{2}, describe:{3}, headquarters:{4}, website:{5}})";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, organization.getId());
			preparedStatement.setString(2, organization.getLabel());
			preparedStatement.setString(3, organization.getDescribe());
			preparedStatement.setString(4, organization.getHeadquarters());
			preparedStatement.setString(5, organization.getWebsite());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        //Random tao Location( co ID, Label, Describe, City- new)
	private void generateLocation(int id) {
		try {
			Location location = new Location();
			location.setId("location " + id);
			location.setLabel(locations.get(random.nextInt(locations.size())));
			location.setDescribe(locationDescriptions.get(random.nextInt(locationDescriptions.size())));
			location.setCity(cities.get(random.nextInt(cities.size())));
			String query = "CREATE (:Location{id:{1}, name:{2}, describe:{3}, city:{4}})";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, location.getId());
			preparedStatement.setString(2, location.getLabel());
			preparedStatement.setString(3, location.getDescribe());
			preparedStatement.setString(4, location.getCity());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        //Random tao Country( co ID, Label, Describe, Capital- new, Population- new)
	private void generateCountry(int id) {
		try {
			Country country = new Country();
			country.setId("country " + id);
			country.setLabel(countries.get(random.nextInt(countries.size())));
			country.setDescribe(countryDescriptions.get(random.nextInt(countryDescriptions.size())));
			country.setCapital(capitals.get(random.nextInt(capitals.size())));
			country.setPopulation(random.nextInt(1000000000) + 1000000);
			String query = "CREATE (:Country{id:{1}, name:{2}, describe:{3}, capital:{4}, population: {5}})";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, country.getId());
			preparedStatement.setString(2, country.getLabel());
			preparedStatement.setString(3, country.getDescribe());
			preparedStatement.setString(4, country.getCapital());
			preparedStatement.setInt(5, country.getPopulation());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        // Random tao Event( co ID, Label, Describe, Venue- new)
	private void generateEvent(int id) {
		try {
			Event event = new Event();
			event.setId("event " + id);
			event.setLabel(events.get(random.nextInt(events.size())));
			event.setDescribe(eventDescriptions.get(random.nextInt(eventDescriptions.size())));
			event.setVenue(locations.get(random.nextInt(locations.size())));
			String query = "CREATE (:Event{id:{1}, name:{2}, describe:{3}, venue:{4}})";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, event.getId());
			preparedStatement.setString(2, event.getLabel());
			preparedStatement.setString(3, event.getDescribe());
			preparedStatement.setString(4, event.getVenue());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        // Random tao Time( co ID, Label, Describe, co the tao them 1 thuoc tinh new, VD: ngay nay la ngay gi)
	private void generateTime(int id) {
		try {
			Time time = new Time();
			time.setId("time " + id);
			time.setLabel(this.generateRandomDate(1990, 2018));
			time.setDescribe(time.getLabel());
			String query = "CREATE (:Time{id:{1}, name:{2}, describe:{3}})";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, time.getId());
			preparedStatement.setString(2, time.getLabel());
			preparedStatement.setString(3, time.getDescribe());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        // Random tao Agreement( co ID, Label, Describe)
	private void generateAgreement(int id) {
		try {
			Agreement agreement = new Agreement();
			agreement.setId("agreement " + id);
			agreement.setLabel(agreements.get(random.nextInt(agreements.size())));
			agreement.setDescribe(agreementDescriptions.get(random.nextInt(agreementDescriptions.size())));
			String query = "CREATE (:Agreement{id:{1}, name:{2}, describe:{3}})";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, agreement.getId());
			preparedStatement.setString(2, agreement.getLabel());
			preparedStatement.setString(3, agreement.getDescribe());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// Random tao quan he ( 31 case nhu trong bang)
	private void generateRelations(int quantity) {
		try {
			for (int i = 0; i < quantity; i++) {
				int randomCase = random.nextInt(32);
				switch (randomCase) {
				case 0:// person- gap go- person
					String query = "MATCH (m:Person{id:{1}}), (n:Person{id:{2}}) CREATE (m)-[:GapGo]->(n)";
					PreparedStatement preparedStatement = this.connection.prepareStatement(query);
					int firstRan = 0;
					int secondRan = 0;
					while (firstRan == secondRan) {
						firstRan = random.nextInt(personsNumber);
						secondRan = random.nextInt(personsNumber);
					}
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "person " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (n:Person{id:{1}}), (m:Person{id:{2}}) "
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "person " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 1:// Orgnization- to chuc- Event
					query = "MATCH (m:Organization{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:ToChuc]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(organizationsNumber);
					secondRan = random.nextInt(eventsNumber);
					preparedStatement.setString(1, "organization " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Organization{id:{1}}), (n:Event{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "organization " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 2:// person- to chuc- event
					query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:ToChuc]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(eventsNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 3:// city- ky thoa thuan- city
					query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:KyThoaThuan]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = 0;
					secondRan = 0;
					while (firstRan == secondRan) {
						firstRan = random.nextInt(countriesNumber);
						secondRan = random.nextInt(countriesNumber);
					}
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 4://person- tham gia- orgnization
					query = "MATCH (m:Person{id:{1}}), (n:Organization{id:{2}}) CREATE (m)-[:ThamGia]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(organizationsNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "organization " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Organization{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "organization " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 5:// person- tham gia- event
					query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:ThamGia]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(eventsNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 6://person- tham gia- agreement
					query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:ThamGia]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(agreementsNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 7://orgnization- tham gia- orgnization
					query = "MATCH (m:Organization{id:{1}}), (n:Organization{id:{2}}) CREATE (m)-[:ThamGia]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = 0;
					secondRan = 0;
					while (firstRan == secondRan) {
						firstRan = random.nextInt(organizationsNumber);
						secondRan = random.nextInt(organizationsNumber);
					}
					preparedStatement.setString(1, "organization " + firstRan);
					preparedStatement.setString(2, "organization " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Organization{id:{1}}), (n:Organization{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "organization " + firstRan);
					preparedStatement.setString(2, "organization " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 8://orgnization- tham gia- event
					query = "MATCH (m:Organization{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:ThamGia]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(organizationsNumber);
					secondRan = random.nextInt(eventsNumber);
					preparedStatement.setString(1, "organization " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Organization{id:{1}}), (n:Event{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "organization " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 9:// orgnization- tham gia- agreement
					query = "MATCH (m:Organization{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:ThamGia]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(organizationsNumber);
					secondRan = random.nextInt(agreementsNumber);
					preparedStatement.setString(1, "organization " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Organization{id:{1}}), (n:Agreement{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "organization " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 10:// event- dien ra tai- location
					query = "MATCH (m:Event{id:{1}}), (n:Location{id:{2}}) CREATE (m)-[:DienRaTai]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(eventsNumber);
					secondRan = random.nextInt(locationsNumber);
					preparedStatement.setString(1, "event " + firstRan);
					preparedStatement.setString(2, "location " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Event{id:{1}}), (n:Location{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "event " + firstRan);
					preparedStatement.setString(2, "location " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 11:// event- dien ra tai- city
					query = "MATCH (m:Event{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:DienRaTai]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(eventsNumber);
					secondRan = random.nextInt(countriesNumber);
					preparedStatement.setString(1, "event " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Event{id:{1}}), (n:Country{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "event " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 12:// person- ung ho- city
					query = "MATCH (m:Person{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:UngHo]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(countriesNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Country{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 13:// person- ung ho- agreement
					query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:UngHo]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(agreementsNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 14:// person- ung ho- event
					query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:UngHo]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(eventsNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 15:// city- ung ho- city
					query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:UngHo]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = 0;
					secondRan = 0;
					while (firstRan == secondRan) {
						firstRan = random.nextInt(countriesNumber);
						secondRan = random.nextInt(countriesNumber);
					}
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 16://city- ung ho- agreement
					query = "MATCH (m:Country{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:UngHo]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(countriesNumber);
					secondRan = random.nextInt(agreementsNumber);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Country{id:{1}}), (n:Agreement{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 17://city- ung ho- event
					query = "MATCH (m:Country{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:UngHo]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(countriesNumber);
					secondRan = random.nextInt(eventsNumber);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Country{id:{1}}), (n:Event{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 18:// person- phan doi- city
					query = "MATCH (m:Person{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:PhanDoi]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(countriesNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Country{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 19:// person- phan doi- agreement
					query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:PhanDoi]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(agreementsNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 20:// person- phan doi- event
					query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:PhanDoi]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(eventsNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 21:// city- phan doi- city
					query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:PhanDoi]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = 0;
					secondRan = 0;
					while (firstRan == secondRan) {
						firstRan = random.nextInt(countriesNumber);
						secondRan = random.nextInt(countriesNumber);
					}
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 22:// city- phan doi- agreement
					query = "MATCH (m:Country{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:PhanDoi]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(countriesNumber);
					secondRan = random.nextInt(agreementsNumber);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Country{id:{1}}), (n:Agreement{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 23:// city- phan doi- event
					query = "MATCH (m:Country{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:PhanDoi]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(countriesNumber);
					secondRan = random.nextInt(eventsNumber);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Country{id:{1}}), (n:Event{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 24:// person- phat bieu tai- event
					query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:PhatBieuTai]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(eventsNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 25://city- cang thang voi- city
					query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:CangThangVoi]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = 0;
					secondRan = 0;
					while (firstRan == secondRan) {
						firstRan = random.nextInt(countriesNumber);
						secondRan = random.nextInt(countriesNumber);
					}
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 26:// person- huy bo- agreement
					query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:HuyBo]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(agreementsNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 27://person- huy bo- event
					query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:HuyBo]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(personsNumber);
					secondRan = random.nextInt(eventsNumber);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "person " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 28://country- huy bo- agreement
					query = "MATCH (m:Country{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:HuyBo]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(countriesNumber);
					secondRan = random.nextInt(agreementsNumber);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Country{id:{1}}), (n:Agreement{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "agreement " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 29:// coutry- huy bo- event
					query = "MATCH (m:Country{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:HuyBo]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(countriesNumber);
					secondRan = random.nextInt(eventsNumber);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Country{id:{1}}), (n:Event{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "event " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 30:// country- dam phan voi- country
					query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:DamPhanVoi]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = 0;
					secondRan = 0;
					while (firstRan == secondRan) {
						firstRan = random.nextInt(countriesNumber);
						secondRan = random.nextInt(countriesNumber);
					}
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "country " + firstRan);
					preparedStatement.setString(2, "country " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				case 31:// moi them
					query = "MATCH (m:Event{id:{1}}), (n:Time{id:{2}}) CREATE (m)-[:DienRaLuc]->(n)";
					preparedStatement = connection.prepareStatement(query);
					firstRan = random.nextInt(eventsNumber);
					secondRan = random.nextInt(timesNumber);
					preparedStatement.setString(1, "event " + firstRan);
					preparedStatement.setString(2, "time " + secondRan);
					preparedStatement.executeUpdate();
					query = "MATCH (m:Event{id:{1}}), (n:Time{id:{2}})"
							+ "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" + "CREATE (n)-[:TrichTu]->(o)"
							+ "CREATE (m)-[:TrichTu]->(o)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, "event " + firstRan);
					preparedStatement.setString(2, "time " + secondRan);
					preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
					preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
					preparedStatement.executeUpdate();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        // Tao ra mot thuc the( su dung cac random person, event, country, time, agreement, location o tren)
	private void generateEntities(int quantity) {
		for (int i = 0; i < quantity; i++) {
			int ranInt = random.nextInt(7);
			switch (ranInt) {
			case 0:
				this.generatePerson(personsNumber++);
				break;
			case 1:
				this.generateOrganization(organizationsNumber++);
				break;
			case 2:
				this.generateCountry(countriesNumber++);
				break;
			case 3:
				this.generateLocation(locationsNumber++);
				break;
			case 4:
				this.generateEvent(eventsNumber++);
				break;
			case 5:
				this.generateAgreement(agreementsNumber++);
				break;
			case 6:
				this.generateTime(timesNumber++);
				break;
			}
		}
	}
        // Xoa du lieu truoc moi phien khoi tao
	private void deleteCurrentData() {
		try {
			String query = "MATCH (n) DETACH DELETE n";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        // Tao ra Database voi cac thuc the cung cac quan he ( su dung Random thuc the va Random quan he)
	public void generate(int entitiesNumber, int relationsNumber) {
		try {
			this.deleteCurrentData();
			this.generateEntities(entitiesNumber);
			this.generateRelations(relationsNumber);
			this.connection.close();
			System.out.println("Generate random data successfully...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
