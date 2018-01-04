
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

	<div id="renderdata"></div>


	<div
		style="margin: 6% auto; background: #fff; border: 1px solid #e2e2e2; padding: 10px 20px; width: 300px;">
		<form name='loginForm' method='GET' onsubmit="return ajaxcall()">
			<c:if test="${username != null}">
		User Name:<input id="username" type="text" name="username"
					value="${username}" readonly >
			</c:if>
			<c:if test="${username == null}">
		User Name:<input id="username" type="text" name="username"
					value="${username}" required autofocus>
			</c:if>
			<div>Old Password: <input id="oldPassword" type="password" name="oldPassword" required autofocus >
				 New Password: <input id="newPassword" type="password" name="newPassword" required>
			Confirm Password: <input id="confirmPassword" type="password" name="confirmPassword" required>
			 <input type="submit"	value="submit" />

		</form>
	</div>
	<script>
		var protocol = location.protocol;
		var slashes = protocol.concat("//");
		var host = slashes.concat(window.location.host);
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

		function pressenterhandler(evt) {
			if (evt.key == 'Enter') {
				ajaxcall();
			}
		}
		function ajaxcall() {

			divContainer.style.display = "none";

			var _data = {
				username : '',
				oldPassword : '',
				newPassword : ''
			};
			_data.oldPassword = document.getElementById("oldPassword").value;
			_data.newPassword = document.getElementById("newPassword").value;
			_data.username = document.getElementById("username").value == '' ? null
					: document.getElementById("username").value;
			requestJson = JSON.stringify(_data);

			if (validationHandler()) {
				service(resultMessage, host + '/ldap/change', requestJson);
			}
			return false
		}

		var resultMessage = function() {

			divContainer.style.display = "block";
			divContainer.innerHTML = JSON.parse(responseText).responseDescription;
			 document.getElementById("oldPassword").value = '';
			document.getElementById("newPassword").value = '';
			document.getElementById("confirmPassword").value = '';
			document.getElementById("oldPassword").focus();
 
		}

		function validationHandler() {
			var _username = document.getElementById("username").value;
			var _oldPaswword = document.getElementById("oldPassword").value;
			var _newPaswword = document.getElementById("newPassword").value;
			var _confirmPassword = document.getElementById("confirmPassword").value;
			
			//var regularExpression = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
			var regularExpression = /^(.*[0-9]+[!@#$%\^&*(){}[\]<>?/|\-]+|.*[!@#$%\^&*(){}[\]<>?/|\-]+[0-9]+)/;

			if (_newPaswword == _confirmPassword && (_username != '')
					&& _newPaswword != '' && _oldPaswword != ''
					&& _oldPaswword != _newPaswword && _newPaswword.length >= 8
					&& regularExpression.test(_newPaswword)) {
				return true;
			}  else if ((_username == '')) {
				//alert("Please fill in all required fields.");
				alert("Please fill user name.");
				return false;
			}  else if (_oldPaswword == _newPaswword) {
				alert("New password must be different.");
				return false;
			} else if (_newPaswword != _confirmPassword) {
				alert("New password and confirm password do not match.");
				return false;
			} else if (_newPaswword.length < 8) {
				alert("The password you provided must have at least 8 characters.");
				return false;
			} else if (!regularExpression.test(_newPaswword)) {
				alert("Password should contain atleast one number and one special character.");
				return false;
			}
		}
		/* var fields=['confirmPassword','oldPassword'];
		for(i=0;i<fields.length;i++){
			document.getElementById(fields[i]).onchange=changeEvent;
		}
		function changeEvent(event){
			console.log()
			for(i=0;i<fields.length;i++){
				if(!document.getElementById(fields[i])){
					return false;
				}
			}
			alert("Every Value is complete");
			
		} */
	</script>
</body>
</html>