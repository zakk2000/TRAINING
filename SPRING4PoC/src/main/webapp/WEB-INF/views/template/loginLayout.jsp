<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<tiles:importAttribute name="bootstrapCss" />
<c:forEach var="bootstrapCssItem" items="${bootstrapCss}">
	<link rel="stylesheet" type="text/css" href="<c:url value="${bootstrapCssItem}" />">
</c:forEach>
<tiles:importAttribute name="faviconIco" />
<c:forEach var="faviconIcoItem" items="${faviconIco}">
	<link rel="shortcut icon" href="<c:url value="${faviconIcoItem}" />">
</c:forEach>
</head>
<body>

<tiles:importAttribute name="jQuery" />
<c:forEach var="jQueryItem" items="${jQuery}">
	<script type="text/javascript" src="<c:url value="${jQueryItem}" />"></script>
</c:forEach>

<tiles:insertAttribute name="body" />

<tiles:importAttribute name="bootstrapJs" />
<c:forEach var="bootstrapJsItem" items="${bootstrapJs}">
	<script type="text/javascript" src="<c:url value="${bootstrapJsItem}" />"></script>
</c:forEach>

</body>
</html>
