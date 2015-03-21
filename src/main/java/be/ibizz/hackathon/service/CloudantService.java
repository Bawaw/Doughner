package be.ibizz.hackathon.service;

import be.ibizz.hackathon.domain.Broodautomaat;
import be.ibizz.hackathon.domain.Gemeente;
import be.ibizz.hackathon.domain.User;
import be.ibizz.hackathon.repository.BroodautomaatRepository;
import be.ibizz.hackathon.repository.GemeenteRepository;
import be.ibizz.hackathon.repository.UserRepository;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
/**
 * A cloudant service to query the database
 */
@Service
@Profile("cloudant")
public class CloudantService implements DataStoreService {
  private Logger LOGGER = LoggerFactory.getLogger(CloudantService.class);
  private BroodautomaatRepository automaatRepository;
  private GemeenteRepository gemeenteRepository;
  private UserRepository userRepository;

  @Autowired
  public CloudantService(BroodautomaatRepository automaatRepository, GemeenteRepository gemeenteRepository, UserRepository userRepository) {
    this.automaatRepository = automaatRepository;
    this.gemeenteRepository = gemeenteRepository;
    this.userRepository = userRepository;
  }
  
  public List<User> getUsers(){
	  return userRepository.getAll();
  }
  
  /**
   * Get all the Broodautomaten
   */
  @Override
  public List<Broodautomaat> getBroodautomaten() {
    return automaatRepository.getAll();
  }

  /**
   * Find all the broodautomaten by postcode.
   */

  @Override
  public List<Broodautomaat> findBroodautomatenInGemeente(String postcode) {
    return automaatRepository.findByPostcode(postcode);
  }

  /**
   * a list of all the Gemeenten
   */
  @Override
  public List<Gemeente> findAllGemeenten() {
    return gemeenteRepository.getAll();
  }

  /**
   * create a new object Broodautomaat in the database.
   */

  @Override
  public Broodautomaat createBroodautomaat(Broodautomaat nieuweBroodautomaat) {
    nieuweBroodautomaat.setLaatsteUpdate(new Date());
    automaatRepository.add(nieuweBroodautomaat);

    LOGGER.debug("Created broodautomaat: {}", nieuweBroodautomaat);
    return nieuweBroodautomaat;
  }

  /**
   * Update a Broodautomaat from the database.
   */

  @Override
  public Broodautomaat updateBroodautomaat(Broodautomaat broodautomaat) {
    broodautomaat.setLaatsteUpdate(new Date());
    automaatRepository.update(broodautomaat);
    return automaatRepository.get(broodautomaat.getId());
  }

  /**
   * Get a broodautomaat by a given id.
   */
  @Override
  public Broodautomaat getBroodautomaat(String id) {
    return automaatRepository.get(id);
  }

@Override
public User getUser(String email) {
	return userRepository.findByEmail(email);
}

@Override
public User updateUser(User user) {
  userRepository.update(user);
  return userRepository.findByEmail(user.getEmail());
}

@Override
public User addUser(User user) {
	userRepository.add(user);
    LOGGER.debug("Created user: {}", user);
    return user;
}

}
