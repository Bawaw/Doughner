package be.ibizz.hackathon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import be.ibizz.hackathon.domain.Broodautomaat;
import be.ibizz.hackathon.domain.User;
import be.ibizz.hackathon.service.DataStoreService;
/**
 * This Rest controller class is used to configure the exposed REST services for the Broodautomaat. 
 * You can adapt this class to add more REST services. 
 *
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
  @Autowired
  private DataStoreService dataStoreService;

  @RequestMapping(method = RequestMethod.POST)
  public User logIn(@RequestParam(value = "email", required = true) String email,@RequestParam(value = "password", required = true) String password, HttpServletRequest request, HttpSession session) {
	  User user = dataStoreService.getUser(email);
	  if(user.getPassword().equals(password)){
		  setSession(request,"user", user);
		  return isSignedIn(request);
	  }	
	  return null;
  }
 
  @RequestMapping(method = RequestMethod.GET)
  public List<User> getUsers() {
    return dataStoreService.getUsers();
  }
  
  //check is return is null to know if signed in
  public User isSignedIn(HttpServletRequest request){
	  return (User)request.getSession().getAttribute("user");
  }
  
  public void setSession(HttpServletRequest request,String key, Object value){
	  request.getSession().setAttribute(key, value);
  }
  public void logOut(HttpServletRequest request){
	  request.getSession().invalidate();
  }
  
  //@RequestMapping(value = "/{email}", method = RequestMethod.POST)
  //public User update(@PathVariable("email") String email, @RequestBody User user) {
     // return dataStoreService.updateUser(user);
	//  return null;
  //}
  
  @RequestMapping(value = "/{email}", method = RequestMethod.POST)
  public User increaseCheckIn(@PathVariable("email") String email, @RequestBody User user) {
      user.setCommits(user.getCommits()+1);
	  return dataStoreService.updateUser(user); 
  }
  
  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public ModelAndView registreerGebruiker() {
    return new ModelAndView("User","command",new User());
  }
  
  @RequestMapping(value = "/addUser", method = RequestMethod.POST)
  public User registreerGebruiker(@ModelAttribute("SpringWeb") User user,ModelAndView model) {
    return dataStoreService.addUser(user);
  }
}