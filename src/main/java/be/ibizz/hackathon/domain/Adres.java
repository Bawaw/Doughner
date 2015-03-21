package be.ibizz.hackathon.domain;

/**
 * The domain class Adres. 
 * 
 *
 */

public class Adres {
  private String straat;
  private String nummer;
  private String postcode;
  private String gemeente;

  public String getGemeente() {
    return gemeente;
  }

  public void setGemeente(String gemeente) {
    this.gemeente = gemeente;
  }

  public String getNummer() {
    return nummer;
  }

  public void setNummer(String nummer) {
    this.nummer = nummer;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public String getStraat() {
    return straat;
  }

  public void setStraat(String straat) {
    this.straat = straat;
  }
}
