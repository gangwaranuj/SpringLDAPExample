package com.thinksys.passwordSelfService.web;

import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

	
@Controller
public class UserController {
 

	@CrossOrigin(origins={"*"})
	@RequestMapping(value = {"/change"}, method = RequestMethod.GET)
	public ModelAndView welcomePage(@RequestParam(value = "username", required = false) String username ) {

		ModelAndView model = new ModelAndView();
		model.addObject("username",username);
		model.setViewName("home");
		return model;
	}
	
	@CrossOrigin(origins={"*"})
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(@RequestParam(value = "username", required = false) String username ) throws ConfigurationException, IOException {

		ModelAndView model = new ModelAndView();
		model.addObject("username",username);
		model.setViewName("admin");
		return model;
	}
}
