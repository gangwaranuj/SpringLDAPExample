package com.thinksys.passwordSelfService.web;

import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

	
@Controller
@Api(tags="UI")
public class UserController {
 

	@ApiOperation(value="return view page to user for changing password.")
	@CrossOrigin(origins={"*"})
	@RequestMapping(value = {"/change"}, method = RequestMethod.GET)
	public ModelAndView change(@ApiParam(name="username",required=false)@RequestParam(value = "username", required = false) String username ) {

		ModelAndView model = new ModelAndView();
		model.addObject("username",username);
		model.setViewName("home");
		return model;
		
	}
	
	@CrossOrigin(origins={"*"})
	@ApiOperation(value="return view page to admin for changing ldap configuration.")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(@ApiParam(name="username",required=false)@RequestParam(value = "username", required = false) String username ) throws ConfigurationException, IOException {

		ModelAndView model = new ModelAndView();
		model.addObject("username",username);
		model.setViewName("admin");
		return model;
	}
}
