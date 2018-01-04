package com.thinksys.ldap.service;

import com.thinksys.ldap.util.ResponseMessage;

public interface LdapService {

	public ResponseMessage changeUSerPassword(String oldPassword,  String newPassword);


}
