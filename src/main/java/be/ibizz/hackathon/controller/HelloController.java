package be.ibizz.hackathon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/hello")
public class HelloController {

	@RequestMapping
	public String getInitialQuestions() {
		return "testje!";
	}
	

}
