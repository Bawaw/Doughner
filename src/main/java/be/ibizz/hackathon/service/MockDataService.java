package be.ibizz.hackathon.service;

import be.ibizz.hackathon.domain.Adres;
import be.ibizz.hackathon.domain.Broodautomaat;
import be.ibizz.hackathon.domain.Gemeente;
import be.ibizz.hackathon.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * A mock service class to run the application without the database.
 */
@Service
@Profile("cloudantStub")
public class MockDataService implements DataStoreService {
  private List<Gemeente> gemeentes = new ArrayList<>();
  private List<Broodautomaat> broodautomaten = new ArrayList<>();

  @PostConstruct
  public void init() {
    Gemeente edegem = new Gemeente("2650", "EDEGEM");
    Gemeente leuven = new Gemeente("3000", "LEUVEN");
    gemeentes.add(edegem);
    gemeentes.add(leuven);

    Broodautomaat broodautomaat = new Broodautomaat();
    broodautomaat.setId(UUID.randomUUID().toString());
    Adres adresEdegem = new Adres();
    adresEdegem.setGemeente("EDEGEM");
    adresEdegem.setNummer("10");
    adresEdegem.setPostcode("2650");
    adresEdegem.setStraat("Zomerlei");
    broodautomaat.setAdres(adresEdegem);
    broodautomaat.setBroodBeschikbaar(true);
    broodautomaat.setBruinBroodBeschikbaar(true);

    broodautomaten.add(broodautomaat);
  }

  @Override
  public List<Broodautomaat> getBroodautomaten() {
    return broodautomaten;
  }

  @Override
  public List<Broodautomaat> findBroodautomatenInGemeente(String postCode) {
    List<Broodautomaat> automatenForPostcode = new ArrayList<>();
    for (Broodautomaat broodautomaat : broodautomaten) {
      final Adres adres = broodautomaat.getAdres();
      if (adres != null) {
        if (adres.getPostcode().equalsIgnoreCase(postCode)) {
          automatenForPostcode.add(broodautomaat);
        }
      }
    }
    return automatenForPostcode;
  }

  @Override
  public List<Gemeente> findAllGemeenten() {
    return gemeentes;
  }

  @Override
  public Broodautomaat createBroodautomaat(Broodautomaat nieuweBroodautomaat) {
    broodautomaten.add(nieuweBroodautomaat);
    return nieuweBroodautomaat;
  }

  @Override
  public Broodautomaat updateBroodautomaat(Broodautomaat broodautomaat) {
    for (Broodautomaat automaat : broodautomaten) {
      if (broodautomaat.getId().equalsIgnoreCase(automaat.getId())) {
        automaat = broodautomaat;
      }
    }
    return broodautomaat;
  }

  @Override
  public Broodautomaat getBroodautomaat(String id) {
    for (Broodautomaat automaat : broodautomaten) {
      if (id.equalsIgnoreCase(automaat.getId())) {
        return automaat;
      }
    }
    return null;
  }

@Override
public List<User> getUsers() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public User getUser(String email) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public User updateUser(User user) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public User addUser(User user) {
	// TODO Auto-generated method stub
	return null;
}

}
