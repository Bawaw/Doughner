package be.ibizz.hackathon.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import java.util.Date;
import java.util.Objects;

/**
 * The domain class Broodautomaat. This Class will be used as an object to store in the database.  
 *  *
 */
@TypeDiscriminator("doc.type == 'broodautomaat'")
public class Broodautomaat extends CouchDbDocument {
  private String type;
  private Geolocatie geolocatie;
  private Adres adres;
  private boolean broodBeschikbaar;
  private boolean witBroodBeschikbaar;
  private boolean bruinBroodBeschikbaar;
  private boolean speciaalBroodBeschikbaar;
  @JsonSerialize(using = DateSerializer.class)
  @JsonDeserialize(using = DateDeserializer.class)
  private Date laatsteUpdate;

  public Adres getAdres() {
    return adres;
  }

  public void setAdres(Adres adres) {
    this.adres = adres;
  }

  public boolean isBroodBeschikbaar() {
    return broodBeschikbaar;
  }

  public void setBroodBeschikbaar(boolean broodBeschikbaar) {
    this.broodBeschikbaar = broodBeschikbaar;
  }

  public boolean isBruinBroodBeschikbaar() {
    return bruinBroodBeschikbaar;
  }

  public void setBruinBroodBeschikbaar(boolean bruinBroodBeschikbaar) {
    this.bruinBroodBeschikbaar = bruinBroodBeschikbaar;
  }

  public Geolocatie getGeolocatie() {
    return geolocatie;
  }

  public void setGeolocatie(Geolocatie geolocatie) {
    this.geolocatie = geolocatie;
  }

  public boolean isSpeciaalBroodBeschikbaar() {
    return speciaalBroodBeschikbaar;
  }

  public void setSpeciaalBroodBeschikbaar(boolean speciaalBroodBeschikbaar) {
    this.speciaalBroodBeschikbaar = speciaalBroodBeschikbaar;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isWitBroodBeschikbaar() {
    return witBroodBeschikbaar;
  }

  public void setWitBroodBeschikbaar(boolean witBroodBeschikbaar) {
    this.witBroodBeschikbaar = witBroodBeschikbaar;
  }

  public Date getLaatsteUpdate() {
    return laatsteUpdate;
  }

  public void setLaatsteUpdate(Date laatsteUpdate) {
    this.laatsteUpdate = laatsteUpdate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, geolocatie, adres, broodBeschikbaar, witBroodBeschikbaar, bruinBroodBeschikbaar, speciaalBroodBeschikbaar);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Broodautomaat other = (Broodautomaat) obj;
    return Objects.equals(this.type, other.type) && Objects.equals(this.geolocatie, other.geolocatie) && Objects.equals(this.adres, other.adres) && Objects.equals(this.broodBeschikbaar, other.broodBeschikbaar) && Objects.equals(this.witBroodBeschikbaar, other.witBroodBeschikbaar) && Objects.equals(this.bruinBroodBeschikbaar, other.bruinBroodBeschikbaar) && Objects.equals(this.speciaalBroodBeschikbaar, other.speciaalBroodBeschikbaar);
  }

  @Override
  public String toString() {
    return "Broodautomaat{" +
      ", type='" + type + '\'' +
      "  id=" + this.getId() +
      "  adres=" + adres +
      ", geolocatie=" + geolocatie +
      ", broodBeschikbaar=" + broodBeschikbaar +
      ", witBroodBeschikbaar=" + witBroodBeschikbaar +
      ", bruinBroodBeschikbaar=" + bruinBroodBeschikbaar +
      ", speciaalBroodBeschikbaar=" + speciaalBroodBeschikbaar + "}";
  }
}