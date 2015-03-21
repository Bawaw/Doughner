package be.ibizz.hackathon.controller;

import be.ibizz.hackathon.domain.Gemeente;
import be.ibizz.hackathon.service.DataStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This Rest controller class is used to configure the exposed REST services for the Gemeente. 
 * You can adapt this class to add more REST services. 
 *
 */

@RestController
@RequestMapping(value = "/api/gemeente")
public class GemeenteController {
  @Autowired
  private DataStoreService dataStoreService;
  
  /**
   * This REST service is returns a list of Gemeente from the database. 
   * @return
   */
  @RequestMapping(method = RequestMethod.GET)
  public List<Gemeente> getGemeenten() {
    return dataStoreService.findAllGemeenten();
  }

}