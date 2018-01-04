package com.thinksys.ldap.daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.thinksys.ldap.dao.LdapDao;
import com.thinksys.ldap.util.ChangeADpassword;

@Repository

public class LdapDaoIml  implements LdapDao {

	private Logger logger = LoggerFactory.getLogger(LdapDaoIml.class);


	static int  flag=0;

	@Override
	public int changePasswordDao(String oldPassword,  String newPassword ,String username) {
		
		try {
		flag = ChangeADpassword.chnageADPassword(oldPassword, newPassword, username);
			return flag;
		} catch (Exception e) {
			logger.info(" LdapDaoIml ::  " +e.getMessage());
			e.printStackTrace();
		} 
		return flag;
	
	}



}
