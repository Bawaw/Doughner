package be.ibizz.hackathon;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.dataload.DataLoader;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.spring.InitialDataLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;

import java.net.MalformedURLException;
import java.util.List;

/**
 * Deze klasse bevat de configuratie van jullie applicatie. Ze bevat een
 * main-methode waardoor je een (embedded) application server kan starten vanuit
 * je IDE.
 */

@SpringBootApplication
public class Application {
		
  private static final String DATABASE_NAME = "breaddb";
  @Value("${vcap.services.hackathon-cloudant.credentials.host:}")
  private String host;
  @Value("${vcap.services.hackathon-cloudant.credentials.username:}")
  private String username;
  @Value("${vcap.services.hackathon-cloudant.credentials.password:}")
  private String password;
/**
 * This is the main method and will be used to start your spring boot application. 
 * @param args Commandline arguments.
 */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
/**
 * This method will be used to create a connector to the Cloudant database using the parameters of the Cloudant Service(hackathon-cloudant). This Service is hosted on the bluemix platform.
 * @return a CouchDbConnector that will be used in the repositories classes for the CRUD operations to the cloudant database. 
 * @throws MalformedURLException
 */
  @Bean
  @Profile("cloudant")
  public CouchDbConnector couchDb() throws MalformedURLException {
    HttpClient httpClient = new StdHttpClient.Builder()
      .url("https://837c679c-e857-41b4-b2e9-64fac8508053-bluemix:ea92b30e2b1708ffe6fc4c5494231702c1ed97439f823d7a6a13d03ea7b28649@837c679c-e857-41b4-b2e9-64fac8508053-bluemix.cloudant.com"+ host)
      .port(443)
      .username("837c679c-e857-41b4-b2e9-64fac8508053-bluemix")
      .password("ea92b30e2b1708ffe6fc4c5494231702c1ed97439f823d7a6a13d03ea7b28649")
      .build();

    CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
    if (dbInstance.getAllDatabases().contains(DATABASE_NAME)) {
      dbInstance.deleteDatabase(DATABASE_NAME);
    }

    CouchDbConnector db = new StdCouchDbConnector(DATABASE_NAME, dbInstance);
    db.createDatabaseIfNotExists();

    return db;
  }
/**
 * This method will be used to initialize the database with sample data. 
 * @param loaders
 * @param resourceLoader
 * @return
 * @throws MalformedURLException
 */
  @Bean
  @Profile("cloudant")
  public InitialDataLoader initialDataLoader(List<DataLoader> loaders, ResourceLoader resourceLoader) throws MalformedURLException {
    return new InitialDataLoader(loaders, resourceLoader);
  }

}