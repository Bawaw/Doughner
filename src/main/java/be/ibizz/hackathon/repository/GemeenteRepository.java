package be.ibizz.hackathon.repository;

import be.ibizz.hackathon.domain.Gemeente;
import be.ibizz.hackathon.util.loader.CustomDataLoader;
import org.ektorp.CouchDbConnector;
import org.ektorp.dataload.DataLoader;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.Reader;
/**
 *  Repository class to query the database using views. i
 *
 */
@Repository
@View(name = "all", map = "function(doc) { if (doc.type == 'gemeente' ) emit( null, doc )}")
@Profile("cloudant")
public class GemeenteRepository extends CouchDbRepositorySupport<Gemeente> implements DataLoader {
  private static final Logger LOGGER = LoggerFactory.getLogger(GemeenteRepository.class);
  private final static String[] INITIAL_DATA_PATH = {"classpath:/database/gemeente.json"};

  @Autowired
  public GemeenteRepository(CouchDbConnector db) {
    super(Gemeente.class, db);
    initStandardDesignDocument();
  }

  @Override
  public void loadInitialData(Reader in) {
    new CustomDataLoader(db).load(in, Gemeente.class);
  }

  @Override
  public void allDataLoaded() {
    LOGGER.info("Finished initial Gemeente data upload");
  }

  @Override
  public String[] getDataLocations() {
    return INITIAL_DATA_PATH;
  }

}
