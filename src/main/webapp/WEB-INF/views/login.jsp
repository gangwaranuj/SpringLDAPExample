<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
<link type="text/css" href="/ldap/resources/css/header/style.css" rel="stylesheet" />

</head>
<body>
<div class="topbar1">
		<img src="/ldap/resources/images/thinksys-logo.png" width="150" alt="">
		
		<span class="pro-head">
		<b>Password Self Service</b>
	</span>
	</div>
	<div id="login-box">

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
			action="<c:url value='/j_spring_security_check' />" method='POST'>

			<div style="margin: 10% auto; background: #fff; border: 1px solid #e2e2e2; padding: 10px 20px; width: auto;">
				<p>User name:</p>
				<input type='text' name='username'/>
				<p>Password: </p>
				<input type='password' name='password'/>
				<input name="submit" type="submit" value="submit"/>
			</div>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>

</body>
</html>