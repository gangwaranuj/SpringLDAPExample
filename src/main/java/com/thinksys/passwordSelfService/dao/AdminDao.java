package com.thinksys.passwordSelfService.dao;

import java.io.IOException;
import java.net.URISyntaxException;

import com.thinksys.passwordSelfService.bean.LdapConfigurationBean;
import com.thinksys.passwordSelfService.util.Response;

public interface AdminDao {

	
	public Response setproperties(LdapConfigurationBean property) throws URISyntaxException, IOException;
	
}
