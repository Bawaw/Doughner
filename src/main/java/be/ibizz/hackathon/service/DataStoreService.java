package be.ibizz.hackathon.service;

import be.ibizz.hackathon.domain.Broodautomaat;
import be.ibizz.hackathon.domain.Gemeente;
import be.ibizz.hackathon.domain.User;

import java.util.List;
/**
 * An interface class to expose the services.
 *
 */
public interface DataStoreService {

  List<Broodautomaat> getBroodautomaten();

  List<Broodautomaat> findBroodautomatenInGemeente(String postCode);

  List<Gemeente> findAllGemeenten();

  Broodautomaat createBroodautomaat(Broodautomaat nieuweBroodautomaat);

  Broodautomaat updateBroodautomaat(Broodautomaat broodautomaat);

  Broodautomaat getBroodautomaat(String id);
  
  User getUser(String email);
  
  List<User> getUsers();
  
  User updateUser(User user);
  
  User addUser(User user);
  

}