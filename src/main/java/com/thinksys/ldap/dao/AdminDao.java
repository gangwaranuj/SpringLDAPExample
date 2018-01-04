package com.thinksys.ldap.dao;

import java.io.IOException;
import java.net.URISyntaxException;

import com.thinksys.ldap.bean.LdapConfigurationBean;
import com.thinksys.ldap.util.Response;

public interface AdminDao {

	
	public Response setproperties(LdapConfigurationBean property) throws URISyntaxException, IOException;
	
}
