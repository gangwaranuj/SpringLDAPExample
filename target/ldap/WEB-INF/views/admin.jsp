
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<link type="text/css" href="/ldap/resources/css/header/style.css"
	rel="stylesheet" />
</head>
<body>
<div class="topbar1">
		<img src="/ldap/resources/images/thinksys-logo.png" width="150" alt="">
		<span class="pro-head">
		<b>Password Self Service</b>
	</span>
	</div>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<div class="topbar">
			 Current User : ${pageContext.request.userPrincipal.name} |
				<a href="<c:url value="/logout" />" class="logout-btn"> Logout</a>
			</b>
		</div>
	</c:if>
	<div
		style="margin: 4% auto; background: #fff; border: 1px solid #e2e2e2; padding: 10px 20px; width: 300px;">
		Ldap Url : <input id="ldapurl" type="text" name="ldapurl"
			placeholder="ldap://xx.xxx.xx.xx:xxx/"><br> Manager Dn:
		<input id="managerdn" type="text" name="managerdn"
			placeholder="uid=xxx,dc=example,dc=com"><br> Manager
		Password: <input id="managerpassword" type="password"
			name="managerpassword" placeholder="*******	" /><br> Group
		Search: <input id="groupsearch" type="text" name="groupsearch"
			placeholder=" cn=xxx,dc=example,dc=com" /><br> Searchbase DN :
		<input id="searchsearch" type="text" name="searchsearch"
			placeholder=" cn=xxx,dc=example,dc=com" /><br>
		<button type="button" onclick="ajaxcall()">submit</button>

	</div>
	<div id="renderdata"></div>
	<script>
	var protocol = location.protocol;
	var slashes = protocol.concat("//");
	var host = slashes.concat(window.location.hostname);
		var responseText = null;
		var divContainer = document.getElementById("renderdata");

		function service(rendor, _url, _data) {
			var _request = new XMLHttpRequest();
			_request.onreadystatechange = function(_response) {
				if (_request.readyState === 4) {
					if (_request.status === 200) {
						responseText = this.responseText;
						rendor();
					}
				}
			};
			if (_data) {
				_request.open('POST', _url, false);
				_request.send(_data);
			}
		};

		function ajaxcall() {

			var _data = {
				ldap_url : '',
				ldap_managerdn : '',
				ldap_basesearch : '',
				ldap_groupsearch : '',
				ldap_managerpassword : ''
			};
			_data.ldap_url = document.getElementById("ldapurl").value;
			_data.ldap_managerdn = document.getElementById("managerdn").value!=''?document.getElementById("managerdn").value:null;
			_data.ldap_managerpassword = document.getElementById("managerpassword").value!=''?document.getElementById("managerpassword").value:null;
			_data.ldap_groupsearch = document.getElementById("groupsearch").value!=''?document.getElementById("groupsearch").value:null;
			_data.ldap_basesearch = document.getElementById("searchsearch").value!=''?document.getElementById("searchsearch").value:null;
			requestJson = JSON.stringify(_data);

			service(resultMessage, host+':8080/ldap/set',
					requestJson);
		}

		var resultMessage = function() {

			divContainer.style.display = "block";
			divContainer.innerHTML = JSON.parse(responseText).responseDescription;
		}
	</script>
















</body>






</html>