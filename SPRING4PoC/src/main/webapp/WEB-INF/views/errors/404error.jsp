<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="label.page.title" /></title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/sb-admin.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/font-awesome.min.css" />">
<link rel="shortcut icon" href="<c:url value="/resources/images/ico/favicon.ico" />">
</head>
<body>

<div id="wrapper">
	<c:import url="/WEB-INF/views/template/header.jsp" />
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						<small><spring:message code="error.system.404" /></small>
					</h1>
					<ol class="breadcrumb">
						<li>
							<i class="fa fa-dashboard"></i>  <a href="<c:url value="/index.app" />"><spring:message code="label.main.move" /></a>
						</li>
					</ol>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>
</html>
