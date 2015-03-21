package be.ibizz.hackathon.repository;

import be.ibizz.hackathon.domain.Broodautomaat;
import be.ibizz.hackathon.util.loader.CustomDataLoader;
import org.ektorp.CouchDbConnector;
import org.ektorp.dataload.DataLoader;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;
import org.ektorp.support.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Reader;
import java.util.List;

/**
 * Repository class to query the database using views. 
 *
 */

@Repository
@Views({
  @View(name = "all", map = "function(doc) { if (doc.type == 'broodautomaat' ) emit( null, doc )}"),
  @View(name = "by_postcode", map = "function(doc) { if (doc.type == 'broodautomaat' && doc.adres.postcode) { emit(doc.adres.postcode, doc)}}"),
  @View(name = "by_broodBeschikbaar", map = "function(doc) { if (doc.type == 'broodautomaat' && doc.broodBeschikbaar) { emit(doc.broodBeschikbaar, doc)}}")
})
public class BroodautomaatRepository extends CouchDbRepositorySupport<Broodautomaat> implements DataLoader {
  private static final Logger LOGGER = LoggerFactory.getLogger(GemeenteRepository.class);
  private final static String[] INITIAL_DATA_PATH = {"classpath:/database/broodautomaat.json"};

  @Autowired
  public BroodautomaatRepository(CouchDbConnector db) {
    super(Broodautomaat.class, db);
    initStandardDesignDocument();
  }
  /**
   * Method to get a list of Broodautomaat by Postcode
   * @param postcode A valid postcode
   * @return a list of Broodautomaat
   */
  public List<Broodautomaat> findByPostcode(String postcode) {
    return queryView("by_postcode", postcode);
  }
  /**
   * A method to get a list of Broodautomaat where there is still brood.
   * @param isBeschikbaar a boolean value to determine if there is still brood in the Broodautomaat
   * @return a list of broodautomaat which still has brood.
   */
  public List<Broodautomaat> findByBroodBeschikbaar(boolean isBeschikbaar) {
    return queryView("by_broodBeschikbaar", Boolean.toString(isBeschikbaar));
  }

  @Override
  public void loadInitialData(Reader in) {
    new CustomDataLoader(db).load(in, Broodautomaat.class);
  }

  @Override
  public void allDataLoaded() {
    LOGGER.info("Finished initial Broodautomaat data upload");
  }

  @Override
  public String[] getDataLocations() {
    return INITIAL_DATA_PATH;
  }

}