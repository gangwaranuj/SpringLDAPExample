package com.thinksys.ldap.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangeADpassword {

	private static Logger logger = LoggerFactory.getLogger(ChangeADpassword.class);

	

	public static int chnageADPassword(String oldPassword,String newPassword,String user) throws javax.naming.NamingException, IOException{

		String passwordAttributeName = "userPassword";
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("ldap.properties");
		Properties prop=new Properties();
		prop.load(is);

		Hashtable env = new Hashtable();
		String userName = "uid="+user+ ","+ prop.getProperty("ldap.searchBase");
		String adminName =prop.getProperty("ldap.managerdn");
		String adminPassword = prop.getProperty("ldap.managerpassword");

		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION,"simple");
		env.put(Context.SECURITY_PRINCIPAL,adminName);
		env.put(Context.SECURITY_CREDENTIALS,adminPassword);
		env.put(Context.SECURITY_PROTOCOL,"kerberos");
		env.put(Context.PROVIDER_URL,prop.getProperty("ldap.url"));

		try {
			LdapContext ctx = new InitialLdapContext(env,null);
			ModificationItem[] mods = new ModificationItem[2];

			mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, new BasicAttribute(passwordAttributeName, oldPassword));
			mods[1] = new ModificationItem(DirContext.ADD_ATTRIBUTE, new BasicAttribute(passwordAttributeName, newPassword));

			logger.info("Changing password for user '" + userName);
			ctx.modifyAttributes(userName, mods);
			logger.info("Changed password for user '" + userName);
			ctx.close();
			return 1;
		} 
		catch (Exception e) {

			logger.info(" ChangePass ::  " +e.getMessage());
			return 0;
		}
	}
}
