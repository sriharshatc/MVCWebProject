<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Shopping Cart</title>

<link rel="stylesheet" type="text/css" href="./styles/products.css"/>

	<c:if test="${ empty PARAM_USER }">
		<c:redirect url="./logout.do" />
	</c:if>

	<c:if test="${ empty PARAM_SCART or PARAM_USER.isAdmin() }">
		<c:redirect url="./products.do" />
	</c:if>

</head>

<body>
	<div id="pageWrapper">
		<div class="pageHeading">
			<h3 class="userInfo">Welcome, <c:out value="${PARAM_USER.getFname()} ${PARAM_USER.getLname()}"/></h3>
			<div class="userInfo">
				<form action="./logout.do" method="get">
					<input type="submit" value="LOGOUT" />
				</form>
			</div>
		</div>  <!-- End of pageHeading -->
		<div class="pageHeading" style="text-align:center;">
			<h1 class="userInfo">Shopping Cart</h1>
		</div>  <!-- End of pageHeading -->
		<div class="divTable">
			<div class="divTableBody">
				<div class="divTableRow" style="background-color:#FF9933;font-weight:bold;">
					<div class="divTableCell">ID</div>
					<div class="divTableCell">PRODUCT</div>
					<div class="divTableCell">QTY</div>
					<div class="divTableCell">TOTAL</div>
					<div class="divTableCell">REMOVE</div>
				</div>

				<c:set var="total" value="0" scope="page" />
				<c:forEach items="${PARAM_SCART}" var="item">

					<c:set var="total" value="${total + item.getTotal()}" scope="page" />
					<div class="divTableRow">
						<div class="divTableCell">${item.getPid()}</div>
						<div class="divTableCell">${item.getPname()}</div>
						<div class="divTableCell">${item.getQty()}</div>
						<div class="divTableCell"><c:out value="$"/>${item.getTotal()}</div>
						<div class="divTableCell">
							<form action="./products.do" method="post">
								<input type="hidden" name="PARAM_PRODUCTID" value="${item.getPid()}"/>
								<input type="hidden" name="PARAM_QUANTITY" value="0"/>
								<input type="submit" value="REMOVE" size="5"/>
							</form>
						</div>
					</div> <!-- End of divTableRow -->

				</c:forEach>
			</div> <!-- End of divTableBody -->
		</div> <!-- End of divTable -->
		<br/><br/><br/>
		<div class="pageHeading">
			<form action="./checkout.do" method="post" style="width:100%;text-align:center;">
				<h2 class="userInfo">TOTAL is <c:out value="$"/><c:out value="${total}"/></h2>
				<input type="submit" value="CHECKOUT" style="font-weight:bold;font-size:large;"/>
			</form>
			<br/>
			<form action="./products.do" method="get" style="width:100%;text-align:center;">
				<input type="submit" value="GO BACK" style="font-weight:bold;font-size:large;"/>
			</form>
		</div>  <!-- End of pageHeading -->
	</div> <!-- End of pageWrapper -->
	<c:if test="${ not empty POPUP_ALERT_MSG }">
		<script type="text/javascript">
			alert("${POPUP_ALERT_MSG}");
		</script>
	</c:if>
</body>
</html>