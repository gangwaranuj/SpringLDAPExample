package com.thinksys.passwordSelfService.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {


	private Logger logger = LoggerFactory.getLogger(Utility.class);


	public static void main(String[] args) {

		InputStream is = Utility.class.getClassLoader().getResourceAsStream("ldap.properties");

		PropertiesConfiguration properties1;
		try {

			properties1 = new PropertiesConfiguration();
			properties1.load(is);
			properties1.setProperty("ldap.url", "xxxx");

			FileOutputStream fos = new FileOutputStream(new File(Utility.class.getClassLoader().getResource("ldap.properties").toURI())) ;
			properties1.save(fos);
			fos.flush();
			fos.close();
			System.out.println(properties1.getProperty("ldap.url"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}




	public static int setldapProperties(String url, String managerdn, String managerpassword, String basesearch,
			String groupserach,String manageruid) throws URISyntaxException, IOException {

		int flag=0;
		InputStream is = Utility.class.getClassLoader().getResourceAsStream("ldap.properties");
		PropertiesConfiguration properties1;
		try {
			//properties1 = new PropertiesConfiguration("D://Workspace//LDAP//src//main//resources//ldap.properties");
			properties1 = new PropertiesConfiguration();
			properties1.load(is);
			if( url!=null)
				properties1.setProperty("ldap.url", url);
			if( managerdn!=null)
				properties1.setProperty("ldap.managerdn", managerdn);
			if( managerpassword!=null)	
				properties1.setProperty("ldap.managerpassword", managerpassword);
			if( basesearch!=null)		
				properties1.setProperty("ldap.searchBase", basesearch);
			if (groupserach!=null)
				properties1.setProperty("ldap.groupsearch", groupserach);
			if( manageruid!=null)
				properties1.setProperty("ldap.magangerUid","uid="+ manageruid);

			FileOutputStream fos = new FileOutputStream(new File(Utility.class.getClassLoader().getResource("ldap.properties").toURI())) ;
			properties1.save(fos);
			fos.flush();
			fos.close();
			flag=1;

		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
