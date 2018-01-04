
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

		<span class="pro-head"> <b>Password Self Service</b>
		</span>
	</div>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<div class="topbar">
			Current User : ${pageContext.request.userPrincipal.name} | <a
				href="<c:url value="/logout" />" class="logout-btn"> Logout</a> </b>
		</div>

	</c:if>


	<div
		style="margin: 10% auto; background: #fff; border: 1px solid #e2e2e2; padding: 10px 20px; width: 300px;">


		Old Password: <input id="oldPassword" type="password"
			name="oldPassword" required> New Password: <input
			id="newPassword" type="password" name="newPassword" required>
		Confirm Password: <input id="confirmPassword" type="password"
			name="confirmPassword" required>
		<!-- <input type="submit" value="submit"  onkeypress="handle(event)" />onclick="ajaxcall()" -->
		<button type="button" onclick="ajaxcall()">Submit</button>

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

			divContainer.style.display = "none";

			var _data = {
				oldPassword : '',
				newPassword : ''
			};
			_data.oldPassword = document.getElementById("oldPassword").value;
			_data.newPassword = document.getElementById("newPassword").value;
			requestJson = JSON.stringify(_data);
			//var host = window.location.hostname;
		  
			if (validationHandler()) {
				service(resultMessage, host+':8080/ldap/change',
						requestJson);
			}
		}

		var resultMessage = function() {

			divContainer.style.display = "none";
			if (JSON.parse(responseText).responseDescription == 'Session has ended.') {

				window.location.href = host+':8080/ldap/logout?flag=2';
			}
			else if (JSON.parse(responseText).responseDescription == 'Password changed successfully') {

				window.location.href = host+':8080/ldap/logout?flag=2';
			} else {
				divContainer.style.display = "block";
				divContainer.innerHTML = JSON.parse(responseText).responseDescription;
			}
		}

		function validationHandler() {

			var _oldPaswword = document.getElementById("oldPassword").value
			var _newPaswword = document.getElementById("newPassword").value
			var _confirmPassword = document.getElementById("confirmPassword").value
			var regularExpression = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;

			if(_newPaswword == _confirmPassword && _newPaswword != ''&&_oldPaswword!= ''&& _oldPaswword != _newPaswword && _newPaswword.length >=8 && regularExpression.test(_newPaswword)) {
				return true;
			} else if ((_confirmPassword == '')|| (_newPaswword == '')||(_oldPaswword == '')) {
				alert("Please fill in all required fields.");
				return false;
			}  else if (_oldPaswword == _newPaswword) {
				alert("New password must be different.");
				return false;
			} else if(_newPaswword!=_confirmPassword){
				alert("New password and confirm password do not match.");
				return false;
			}else if (!regularExpression.test(_newPaswword)) {
				alert("Password should contain atleast one number and one special character.")
				return false;
			} 
			else if (_newPaswword.length< 8) {
				alert("The password you provided must have at least 8 characters.");
				return false;
			} 
		}
	</script>
</body>
</html>