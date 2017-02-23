<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add Products</title>

<link rel="stylesheet" type="text/css" href="./styles/details.css"/>

	<c:if test="${ empty PARAM_USER or not PARAM_USER.isAdmin() }">
		<c:redirect url="./logout.do" />
	</c:if>

</head>

<body>
	<div id="pageWrapper">
		<div style="clear:both;margin:auto;">
			<h3 class="userInfo">Welcome, <c:out value="${PARAM_USER.getFname()} ${PARAM_USER.getLname()}"/></h3>
			<div class="userInfo">
				<form action="./logout.do" method="get">
					<input type="submit" value="LOGOUT" />
				</form>
			</div>
		</div>  <!-- End of userDiv -->
		<div id="pageHeading">
			<h1>Add a Product</h1>
		</div>  <!-- End of pageHeading go-->
		<div id="detailsBox">

			<form action="./add-product.do" method="post">
				Product ID: <input type="text" name="PARAM_PRODUCTID" /> <br/> <br/>
				Product Name: <input type="text" name="PARAM_PRODNAME" /> <br/> <br/>
				Price: <input type="text" name="PARAM_PRODPRICE" /> <br/> <br/>
				<input type="submit" value="ADD" />
			</form>
			<br /><br />
			<form action="./admin.do" method="get">
				<input type="submit" value="GO BACK" />
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