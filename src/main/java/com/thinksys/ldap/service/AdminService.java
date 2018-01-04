package com.thinksys.ldap.service;

import com.thinksys.ldap.bean.LdapConfigurationBean;
import com.thinksys.ldap.util.ResponseMessage;

public interface AdminService {

	
	public ResponseMessage setLdapProperties( LdapConfigurationBean ldapConfigurationBean) ;
	
	
}
