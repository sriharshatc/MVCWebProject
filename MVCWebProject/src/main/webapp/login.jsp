<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Login Page</title>

<link rel="stylesheet" type="text/css" href="./styles/details.css"/>

	<c:if test="${ not empty PARAM_USER }">
		<c:redirect url="./logout.do" />
	</c:if>

</head>

<body>
	<div id="pageWrapper">
		<div id="pageHeading">
			<h1>MVC Online Shopping</h1>
		</div>  <!-- End of pageHeading -->
		<div id="detailsBox">

			<form action="./login-validation.do" method="post">
				Username: <input type="text" name="PARAM_USERNAME" /> <br/> <br/>
				Password: <input type="password" name="PARAM_PASSWORD" /> <br/> <br/>
				<input type="submit" value="LOGIN" />
			</form> <br/> <br/>

			<form action="./registration.do" method="get">
				<input type="submit" value="REGISTER" />
			</form>

		</div> <!-- End of detailsBox -->
	</div> <!-- End of pageWrapper -->
	<c:if test="${ not empty POPUP_ALERT_MSG }">
		<script type="text/javascript">
			alert("${POPUP_ALERT_MSG}");
		</script>
	</c:if>
</body>
</html>