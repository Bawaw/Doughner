package be.ibizz.hackathon.util.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.ektorp.CouchDbConnector;
import org.ektorp.DocumentOperationResult;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.util.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class CustomDataLoader {
  private static final Logger LOGGER = LoggerFactory.getLogger(CustomDataLoader.class);
  private CouchDbConnector db;
  private ObjectMapper mapper = new ObjectMapper();

  public CustomDataLoader(CouchDbConnector db) {
    this.db = db;
  }

  /**
   * Reads documents from the reader and stores them in the database.
   *
   * @param in
   */
  public void load(Reader in, Class clazz) {
    try {
      doLoad(in, clazz);
    } catch (Exception e) {
      throw Exceptions.propagate(e);
    }
  }

  private <T extends CouchDbDocument> void doLoad(Reader in, Class clazz) throws IOException {
    List<T> inputDocuments = mapper.readValue(in,
      TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));

    List<CouchDbDocument> outputDocuments = new ArrayList<>();
    for (T element : inputDocuments) {
      outputDocuments.add(element);
    }

    List<DocumentOperationResult> saveResult = db.executeBulk(outputDocuments);
    if (saveResult.isEmpty()) {
      LOGGER.info("Successfully added {} documents to Cloudant", outputDocuments.size());
    } else {
      LOGGER.warn("Only added {} out of {} documents succesfully", outputDocuments.size() - saveResult.size(), outputDocuments.size());
    }
  }
}
