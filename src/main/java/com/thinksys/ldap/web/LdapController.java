package com.thinksys.ldap.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.thinksys.ldap.bean.PasswodBean;
import com.thinksys.ldap.service.LdapService;
import com.thinksys.ldap.util.ResponseMessage;

@Controller
public class LdapController {

	@Autowired
	LdapService ldapServiceImpl;
	
	@CrossOrigin(origins={"*"})
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage change(@RequestBody String requestJson) {
		
		Gson gson =new Gson();
		PasswodBean passwodBean = gson.fromJson(requestJson,PasswodBean.class);
		ResponseMessage response=this.ldapServiceImpl.changeUSerPassword(passwodBean.getOldPassword(),passwodBean.getNewPassword());
		return  response;
	}	
}
