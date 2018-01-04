package com.thinksys.passwordSelfService.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinksys.passwordSelfService.dao.LdapDao;
import com.thinksys.passwordSelfService.daoImpl.LdapDaoIml;
import com.thinksys.passwordSelfService.service.LdapService;
import com.thinksys.passwordSelfService.util.Constants;
import com.thinksys.passwordSelfService.util.ResponseMessage;

@Service
public class LdapServiceImpl  implements LdapService{


	private Logger logger = LoggerFactory.getLogger(LdapServiceImpl.class);

	LdapDao ldapdao=new LdapDaoIml();



	@Override
	public ResponseMessage changeUSerPassword(String oldPassword,  String newPassword ,String username){

		ResponseMessage jsonResponse=null;
		int result=0;
		try{
			if(username!=null)
			result=this.ldapdao.changePasswordDao(oldPassword, newPassword ,username);
			
			if(username!=null){
				
				jsonResponse=result>0?new ResponseMessage(Constants.SUCCESS_CODE,"Password changed successfully !" ):new ResponseMessage(Constants.SUCCESS_CODE,"Invalid user name or old password !");
			}
			else{
				
				jsonResponse=	new ResponseMessage(Constants.SUCCESS_CODE,"Ivalid user !");
			}
		}catch (Exception e) {

			jsonResponse = new ResponseMessage(Constants.ERROR_CODE_UNKNOWN, e.getMessage());
			logger.error(" LdapServiceImpl ::  " , e);
		}
		return jsonResponse;
	}

}
