package com.thinksys.passwordSelfService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	LdapService ldapServiceImpl =new LdapServiceImpl();
	
	@CrossOrigin(origins={"*"})
	@RequestMapping(value = "/changep",consumes="application/json",
            produces="application/json",
            method=RequestMethod.POST
          )
	public  ResponseMessage change(@RequestBody String requestJson) {
		
		Gson gson =new Gson();
		PasswordBean passwodBean = gson.fromJson(requestJson,PasswordBean.class);
		ResponseMessage response=this.ldapServiceImpl.changeUSerPassword(passwodBean.getOldPassword(),passwodBean.getNewPassword(),passwodBean.getUsername());
//		ResponseMessage response=new ResponseMessage();
//		response.setResponseCode(200);
//		response.setResponseDescription("sample");
		
		return  response;
	}	
	
	
	@CrossOrigin(origins={"*"})
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String admin1()  {

		
		return  "test";
	}
}
