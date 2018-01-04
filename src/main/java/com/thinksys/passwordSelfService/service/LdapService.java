package com.thinksys.passwordSelfService.service;

import com.thinksys.passwordSelfService.util.ResponseMessage;

public interface LdapService {

	public ResponseMessage changeUSerPassword(String oldPassword,  String newPassword,String username);


}
