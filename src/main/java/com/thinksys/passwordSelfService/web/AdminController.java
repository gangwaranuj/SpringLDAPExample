package com.thinksys.passwordSelfService.web;

import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.thinksys.passwordSelfService.bean.LdapConfigurationBean;
import com.thinksys.passwordSelfService.service.AdminService;
import com.thinksys.passwordSelfService.util.ResponseMessage;


@Controller
public class AdminController {

	@Autowired
	AdminService adminservice;

	@CrossOrigin(origins={"*"})
	@RequestMapping(value = "/set", method = RequestMethod.POST)
	public @ResponseBody  ResponseMessage setProperties (@RequestBody String requestJson ) throws ConfigurationException, IOException {

		Gson gson =new Gson();
		LdapConfigurationBean ldapConfigurationBean = gson.fromJson(requestJson,LdapConfigurationBean.class);
		ResponseMessage response=this.adminservice.setLdapProperties(ldapConfigurationBean);
		return  response;
	}	

}
