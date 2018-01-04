package com.thinksys.passwordSelfService.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinksys.passwordSelfService.bean.LdapConfigurationBean;
import com.thinksys.passwordSelfService.dao.AdminDao;
import com.thinksys.passwordSelfService.service.AdminService;
import com.thinksys.passwordSelfService.util.Constants;
import com.thinksys.passwordSelfService.util.Response;
import com.thinksys.passwordSelfService.util.ResponseMessage;

@Service
public class AdminServiceImpl  implements AdminService{

	
	private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	AdminDao admindao;

	@Override
	public ResponseMessage setLdapProperties(LdapConfigurationBean ldapConfigurationBean ) {
	
		ResponseMessage jsonResponse=null;
		try{
			
			Response response=this.admindao.setproperties(ldapConfigurationBean);
			
			if(response.getStatus()){
				logger.info("AdminServiceImpl:: " +"ldap.properites file is updated.");
				jsonResponse=new ResponseMessage(Constants.SUCCESS_CODE,"property file has been updated !");
			}
		}catch (Exception e) {
			
			jsonResponse = new ResponseMessage(Constants.ERROR_CODE_UNKNOWN, e.getMessage());
			logger.error(" AdminServiceImpl :: setLdapProperties ()  " , e);
		}
		return jsonResponse;
	}

	
}
