package com.thinksys.ldap.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinksys.ldap.bean.LdapConfigurationBean;
import com.thinksys.ldap.dao.AdminDao;
import com.thinksys.ldap.service.AdminService;
import com.thinksys.ldap.util.Constants;
import com.thinksys.ldap.util.Response;
import com.thinksys.ldap.util.ResponseMessage;

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
