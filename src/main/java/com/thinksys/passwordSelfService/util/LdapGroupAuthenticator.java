package com.thinksys.passwordSelfService.util;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LdapGroupAuthenticator {


	public static void authenticate( String username, String password) throws NamingException {



		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.PROVIDER_URL, "ldap://10.101.10.11:389/");
		env.put(Context.SECURITY_PRINCIPAL, "uid=singh.anuj ,cn=users,cn=accounts,dc=thinksys,dc=com");
		env.put(Context.SECURITY_CREDENTIALS, "thinksys@123");

		DirContext ctx;

		try {

			ctx = new InitialDirContext(env);
			String searchBase = "cn=users,cn=accounts,dc=thinksys,dc=com";
			StringBuilder searchFilter = new StringBuilder("(&");
			searchFilter.append("(objectClass=person)");
			searchFilter.append("(uid=singh.anuj)");
			searchFilter.append(")");

			String returnAttrs[] = {"memberOf"};
			SearchControls sCtrl = new SearchControls();
			sCtrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
			sCtrl.setReturningAttributes(returnAttrs);

			NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter.toString(), sCtrl);
			boolean pass = false;
			while (answer.hasMoreElements()) {

				SearchResult sr = answer.next();
				String memberOfAttrValue = sr.getAttributes().get("memberOf").toString();
				System.out.println(memberOfAttrValue);
				pass = true;
			}

			if (pass) {
				System.out.println("******SEYTEY********");
			} else {
				System.out.println("**************");
			}
		} catch (NamingException ex) {
			ex.printStackTrace();
		}

	}

	/* public static boolean userMemberOf(DirContext ctx, String searchBase, HashMap processedUserGroups, HashMap unProcessedUserGroups, String groupCN, String groupDistinguishedName) throws NamingException {
        HashMap newUnProcessedGroups = new HashMap();
        for (Iterator entry = unProcessedUserGroups.keySet().iterator(); entry.hasNext();) {
            String  unprocessedGroupDistinguishedName = (String) entry.next();
            String unprocessedGroupCN = (String)unProcessedUserGroups.get(unprocessedGroupDistinguishedName);
            if ( processedUserGroups.get(unprocessedGroupDistinguishedName) != null) {
                // We already traversed this.
                continue;
            }
            if (isSame (groupCN, unprocessedGroupCN) && isSame (groupDistinguishedName, unprocessedGroupDistinguishedName)) {
                return true;
            }
        }

        for (Iterator entry = unProcessedUserGroups.keySet().iterator(); entry.hasNext();) {
            String  unprocessedGroupDistinguishedName = (String) entry.next();
            String unprocessedGroupCN = (String)unProcessedUserGroups.get(unprocessedGroupDistinguishedName);

            processedUserGroups.put(unprocessedGroupDistinguishedName, unprocessedGroupCN);

            NamingEnumeration ns = executeSearch(ctx, SearchControls.SUBTREE_SCOPE, searchBase,
                    MessageFormat.format( SEARCH_GROUP_BY_GROUP_CN, new Object[] {unprocessedGroupCN}),
                    new String[] {CN, DISTINGUISHED_NAME, MEMBER_OF});

            while (ns.hasMoreElements()) {
                SearchResult sr = (SearchResult) ns.next();

                String userDistinguishedName = sr.getAttributes().get(DISTINGUISHED_NAME).get().toString();
                if (!isSame(unprocessedGroupDistinguishedName, userDistinguishedName)) {
                    continue;
                }

                Attribute memberOf = sr.getAttributes().get(MEMBER_OF);
                if (memberOf != null) {
                    for ( Enumeration e1 = memberOf.getAll() ; e1.hasMoreElements() ; ) {
                        String unprocessedChildGroupDN = e1.nextElement().toString();
                        String unprocessedChildGroupCN = getCN(unprocessedChildGroupDN);
                        newUnProcessedGroups.put(unprocessedChildGroupDN, unprocessedChildGroupCN);
                    }
                }
            }
        }
        if (newUnProcessedGroups.size() == 0) {
            return false;
        }

        return userMemberOf(ctx, searchBase, processedUserGroups, newUnProcessedGroups, groupCN, groupDistinguishedName);
    }

    private static NamingEnumeration executeSearch(DirContext ctx, int searchScope,  String searchBase, String searchFilter, String[] attributes) throws NamingException {
        SearchControls searchCtls = new SearchControls();

        if (attributes != null) {
            searchCtls.setReturningAttributes(attributes);
        }

        searchCtls.setSearchScope(searchScope);

        NamingEnumeration result = ctx.search(searchBase, searchFilter,searchCtls);
        return result;
    }

    private static SearchResult executeSearchSingleResult(DirContext ctx, int searchScope,  String searchBase, String searchFilter, String[] attributes) throws NamingException {
        NamingEnumeration result = executeSearch(ctx, searchScope,  searchBase, searchFilter, attributes);

        SearchResult sr = null;
        while (result.hasMoreElements()) {
            sr = (SearchResult) result.next();
            break;
        }
        return sr;
    }
	 */


	public static void main (String args[]) throws NamingException{

		authenticate("singh.anuj","thinsksys@123");
	}
}