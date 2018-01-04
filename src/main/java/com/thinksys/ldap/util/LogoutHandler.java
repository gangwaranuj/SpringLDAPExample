package com.thinksys.ldap.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


public class LogoutHandler  implements LogoutSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(LogoutHandler.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
		if (authentication != null && authentication.getDetails() != null) {
			try {
				httpServletRequest.getSession().invalidate();
				logger.info("LogoutHandler ::"+authentication.getName()+" User Successfully Logout ");
				String query = httpServletRequest.getQueryString();
				if(query!= null){

					httpServletResponse.sendRedirect("/ldap/login?successful=1");
				}
				else{
					httpServletResponse.sendRedirect("/ldap/login?logout=1");

				}
				httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("LogoutHandler ::" ,e.getMessage());
			}
		}
		else{
			String query = httpServletRequest.getQueryString();
			logger.info("LogoutHandler ::anonymous User");
			if(query!= null){
				logger.info("LogoutHandler ::anonymous User login again.");
				httpServletResponse.sendRedirect("/ldap/login?logout=3");
			}
			else {
				logger.info("LogoutHandler ::anonymous User and click on logout.");
				httpServletResponse.sendRedirect("/ldap/login?logout=1");
			}
		}
	}

}
