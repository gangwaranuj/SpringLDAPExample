package com.thinksys.passwordSelfService.daoImpl;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.thinksys.passwordSelfService.bean.LdapConfigurationBean;
import com.thinksys.passwordSelfService.dao.AdminDao;
import com.thinksys.passwordSelfService.util.Response;
import com.thinksys.passwordSelfService.util.Utility;

@Repository
public class AdminDaoImpl implements AdminDao {

	private Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);
	@Override
	public Response setproperties(LdapConfigurationBean property) throws URISyntaxException, IOException {

		Response response=new Response();
		int result= Utility.setldapProperties(property.getLdap_url(), property.getLdap_managerdn(), property.getLdap_managerpassword(), property.getLdap_basesearch(), property.getLdap_groupsearch(),"uid="+property.getManagerUid());	
		if(result>0)
		{   
			logger.info("AdminDaoImpl ::"+"property file has been updated with !!"+property.toString());
			response.setStatus(true);
		}
		else{
			logger.info("AdminDaoImpl ::"+"property file has not  updated with !!"+property.toString());
			response.setStatus(false);
		}
		return response;

	}





}
