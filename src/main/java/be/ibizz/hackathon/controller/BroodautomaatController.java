package be.ibizz.hackathon.controller;

import be.ibizz.hackathon.domain.Broodautomaat;
import be.ibizz.hackathon.service.DataStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * This Rest controller class is used to configure the exposed REST services for the Broodautomaat. 
 * You can adapt this class to add more REST services. 
 *
 */
@RestController
@RequestMapping(value = "/api/broodautomaat")
public class BroodautomaatController {
  @Autowired
  private DataStoreService dataStoreService;

/**
 * This REST service is used to get a specicfic Broodautomaat for the given ID. 
 * @param id The id of the broodautomaat
 * @return it returns a Broodautomaat from the database for the given ID. 
 */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Broodautomaat getBroodautomaat(@PathVariable("id") String id) {
    return dataStoreService.getBroodautomaat(id);
  }
  
  /**
   * This REST service is used to add a new Broodautomaat to the database
   * @param broodautomaat the new Broodautomaat that needs to be added
   * @return the new broodautomaat.
   */

  @RequestMapping(method = RequestMethod.POST)
  public Broodautomaat addBroodautomaat(@RequestBody Broodautomaat broodautomaat) {
	  System.out.println("in controller");
    return dataStoreService.createBroodautomaat(broodautomaat);
  }
  
  
  /**
   * This REST services gives back a list of Broodautomaten for a given postcode or beschikbaar or both. 
   * TODO: use the beschikbaar boolean value to create a new Query on the database. 
   * @param postcode the postcode of the city 
   * @param beschikbaar if the broodautomaat still has bread left. 
   * @return a list of broodautomaat.
   */
  @RequestMapping(method = RequestMethod.GET)
  public List<Broodautomaat> getBroodautomaten(@RequestParam(value = "postcode", required = false) String postcode,
                                               @RequestParam(value = "beschikbaar", required = false) boolean beschikbaar) {
    List<Broodautomaat> broodautomaten;
    if (StringUtils.isEmpty(postcode)) {
      broodautomaten = dataStoreService.getBroodautomaten();
    } else {
      broodautomaten = dataStoreService.findBroodautomatenInGemeente(postcode);
    }

    return broodautomaten;
  }
  
  /**
   * This REST service update a broodautomaat 
   * @param id the ID of the broodautomaat
   * @param broodautomaat the object broodautomaat with the updated values.
   * @return
   */

  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  public Broodautomaat updateBroodautomaat(@PathVariable("id") String id, @RequestBody Broodautomaat broodautomaat) {
    if (id.equals(broodautomaat.getId())) {
      return dataStoreService.updateBroodautomaat(broodautomaat);
    } else {
      throw new RuntimeException();
    }
  }

}