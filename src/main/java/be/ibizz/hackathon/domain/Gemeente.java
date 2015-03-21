package be.ibizz.hackathon.domain;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

/**
 * This is the domain class Gemeente. This Class will be used as an object to store in the database.
 *
 */
@TypeDiscriminator("doc.type === 'gemeente'")
public class Gemeente extends CouchDbDocument {
  private String type;
  private String postcode;
  private String naam;

  public Gemeente() {
  }

  public Gemeente(String postcode, String naam) {
    this.postcode = postcode;
    this.type = "gemeente";
    this.naam = naam;
  }

  public String getNaam() {
    return naam;
  }

  public void setNaam(String naam) {
    this.naam = naam;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

}
