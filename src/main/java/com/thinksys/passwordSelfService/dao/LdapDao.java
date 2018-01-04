package com.thinksys.passwordSelfService.dao;

public interface LdapDao {
	
	public int changePasswordDao(String oldPassword,  String newPassword,String username);

}
