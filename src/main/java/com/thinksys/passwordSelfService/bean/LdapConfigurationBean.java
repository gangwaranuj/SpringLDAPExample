package com.thinksys.passwordSelfService.bean;

public class LdapConfigurationBean {

	private String ldap_managerUid;
	private String ldap_url;
	private String ldap_managerdn;
	private String ldap_basesearch;
	private String ldap_groupsearch;
	private String ldap_managerpassword;
	public String getLdap_url() {
		return ldap_url;
	}
	public void setLdap_url(String ldap_url) {
		this.ldap_url = ldap_url;
	}
	public String getLdap_managerdn() {
		return ldap_managerdn;
	}
	public void setLdap_managerdn(String ldap_managerdn) {
		this.ldap_managerdn = ldap_managerdn;
	}
	public String getLdap_basesearch() {
		return ldap_basesearch;
	}
	public void setLdap_basesearch(String ldap_basesearch) {
		this.ldap_basesearch = ldap_basesearch;
	}
	public String getLdap_groupsearch() {
		return ldap_groupsearch;
	}
	public void setLdap_groupsearch(String ldap_groupsearch) {
		this.ldap_groupsearch = ldap_groupsearch;
	}
	public String getLdap_managerpassword() {
		return ldap_managerpassword;
	}
	public void setLdap_managerpassword(String ldap_managerpassword) {
		this.ldap_managerpassword = ldap_managerpassword;
	}
	public String getManagerUid() {
		return ldap_managerUid;
	}
	public void setManagerUid(String managerUid) {
		this.ldap_managerUid = managerUid;
	}
	@Override
	public String toString() {
		return "LdapConfigurationBean [managerUid=" + ldap_managerUid + ", ldap_url=" + ldap_url + ", ldap_managerdn="
				+ ldap_managerdn + ", ldap_basesearch=" + ldap_basesearch + ", ldap_groupsearch=" + ldap_groupsearch
				+ ", ldap_managerpassword=" + ldap_managerpassword + "]";
	}
	
}