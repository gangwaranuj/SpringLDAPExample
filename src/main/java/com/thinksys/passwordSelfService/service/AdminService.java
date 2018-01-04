package com.thinksys.passwordSelfService.service;

import com.thinksys.passwordSelfService.bean.LdapConfigurationBean;
import com.thinksys.passwordSelfService.util.ResponseMessage;

public interface AdminService {

	
	public ResponseMessage setLdapProperties( LdapConfigurationBean ldapConfigurationBean) ;
	
	
}
