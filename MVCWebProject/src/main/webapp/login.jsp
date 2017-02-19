<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Login Page</title>
<style type="text/css">
	#loginBox {
		width: 300px;
		clear: both;
	}
	#loginBox input {
		width: 100%;
		clear: both;
	}
</style>
</head>

<body>
	<div id="pageWrapper" style="display:flex;justify-content:center;align-items:center;">
		<div id="loginBox">

			<form action="./login-validation.do" method="post">
				Username: <input type="text" name="username" /> <br/> <br/>
				Password: <input type="password" name="password" /> <br/> <br/>
				<input type="submit" value="LOGIN" />
			</form> <br/> <br/>

			<input type="button" onclick="location.href='./registration.do';" value="REGISTER" />
		</div> <!-- End of loginBox -->
	</div> <!-- End of pageWrapper -->
</body>
</html>