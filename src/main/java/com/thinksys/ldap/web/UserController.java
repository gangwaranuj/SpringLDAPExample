package com.thinksys.ldap.web;

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
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "successful", required = false) String successful) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {

			String msg = Integer.parseInt(logout)==1 ? "You've been logged out successfully.":"" ;
			if(Integer.parseInt(logout)==3){
				msg ="Session has ended. Please login.";
				
			}
			model.addObject("msg",msg);
		}
		if (successful != null) {
			
			
			String msg = Integer.parseInt(successful)==1 ? "Password changed successfully.":"" ;
			model.addObject("msg", msg);
		}
		model.setViewName("login");
		return model;
	}	

	@CrossOrigin(origins={"*"})
	@RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		return model;
	}
	

	@RequestMapping("403page")
	public String ge403denied() {
		return "redirect:login?denied";
	}
	
	@CrossOrigin(origins={"*"})
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin() throws ConfigurationException, IOException {

		ModelAndView model = new ModelAndView();
		
		model.setViewName("admin");
		return model;
	}
}
