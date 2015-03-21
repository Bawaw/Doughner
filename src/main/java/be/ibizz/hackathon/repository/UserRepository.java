package be.ibizz.hackathon.repository;

import java.io.Reader;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.dataload.DataLoader;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;
import org.ektorp.support.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import be.ibizz.hackathon.domain.User;
import be.ibizz.hackathon.util.loader.CustomDataLoader;

/**
 * Repository class to query the database using views.
 *
 */

@Repository
@Views({
  @View(name = "all", map = "function(doc) { if (doc.type == 'user' ) emit( null, doc )}"),
  @View(name = "by_email", map = "function(doc) { if (doc.type == 'user' && doc.email) { emit(doc.email, doc)}}")
})
@Profile("cloudant")
public class UserRepository extends CouchDbRepositorySupport<User> implements DataLoader {
  private static final Logger LOGGER = LoggerFactory.getLogger(User.class);
  private final static String[] INITIAL_DATA_PATH = {"classpath:/database/user.json"};

  @Autowired
  public UserRepository(CouchDbConnector db) {
    super(User.class, db);
    initStandardDesignDocument();
  }
  
  public User findByEmail(String email) {
    List<User> user = queryView("by_email", email);	//search view multiple values
    if(!user.isEmpty())
    	return user.get(0);
    return null;
  }

  @Override
  public void loadInitialData(Reader in) {
    new CustomDataLoader(db).load(in, User.class);
  }
  
  

  @Override
  public void allDataLoaded() {
    LOGGER.info("Finished initial User data upload");
  }

  @Override
  public String[] getDataLocations() {
    return INITIAL_DATA_PATH;
  }

}
