package com.thinksys.ldap.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.thinksys.ldap.dao.LdapDao;
import com.thinksys.ldap.service.LdapService;
import com.thinksys.ldap.util.Constants;
import com.thinksys.ldap.util.ResponseMessage;

@Service
public class LdapServiceImpl  implements LdapService{


	private Logger logger = LoggerFactory.getLogger(LdapServiceImpl.class);

	@Autowired
	LdapDao ldapdao;



	@Override
	public ResponseMessage changeUSerPassword(String oldPassword,  String newPassword ){

		ResponseMessage jsonResponse=null;
		int result=0;
		try{
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			logger.info("LdapServiceImpl "+username);
			result=this.ldapdao.changePasswordDao(oldPassword, newPassword ,username);
			
			if(username!="anonymousUser"){
				jsonResponse=result>0?new ResponseMessage(Constants.SUCCESS_CODE,"Password changed successfully" ):new ResponseMessage(Constants.SUCCESS_CODE,"Invalid old password !");
			}else
			{
				jsonResponse=new ResponseMessage(Constants.ERROR_CODE_INVALID,"Session has ended.");
			}
			
		}catch (Exception e) {

			jsonResponse = new ResponseMessage(Constants.ERROR_CODE_UNKNOWN, e.getMessage());
			logger.error(" LdapServiceImpl ::  " , e);
		}
		return jsonResponse;
	}

}
