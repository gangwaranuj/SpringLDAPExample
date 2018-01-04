package com.thinksys.passwordSelfService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.thinksys.passwordSelfService.bean.PasswordBean;
import com.thinksys.passwordSelfService.service.LdapService;
import com.thinksys.passwordSelfService.serviceImpl.LdapServiceImpl;
import com.thinksys.passwordSelfService.util.ResponseMessage;

@RestController
public class LdapController {

	@Autowired
	LdapService ldapServiceImpl;
	
	@CrossOrigin(origins={"*"})
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage change(@RequestBody String requestJson) {
		
		Gson gson =new Gson();
		PasswordBean passwodBean = gson.fromJson(requestJson,PasswordBean.class);
		ResponseMessage response=this.ldapServiceImpl.changeUSerPassword(passwodBean.getOldPassword(),passwodBean.getNewPassword(),passwodBean.getUsername());
		return  response;
	}
	
}
