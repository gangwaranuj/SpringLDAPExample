package com.thinksys.passwordSelfService.web;

import org.apache.http.client.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(tags="user")
public class LdapController {

	@Autowired
	@Qualifier("ldapServiceImpl")
	LdapService ldapServiceImpl;
	
	@ApiOperation(value="Change password")
	@CrossOrigin(origins={"*"})
	@RequestMapping(
			value = "/change", 
			method = RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseMessage change(@ApiParam(name="requestJson",required=true) @RequestBody String requestJson) {
		
		Gson gson =new Gson();
		PasswordBean passwodBean = gson.fromJson(requestJson,PasswordBean.class);
		ResponseMessage response=this.ldapServiceImpl.changeUSerPassword(passwodBean.getOldPassword(),passwodBean.getNewPassword(),passwodBean.getUsername());
		return  response;
	}
	
	
	
	
}
