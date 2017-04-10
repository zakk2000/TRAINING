<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<c:set var="currURL" value="${requestScope['javax.servlet.forward.servlet_path']}" />
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle Navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="<c:url value="/index.app" />"><spring:message code="label.page.title.abbreviation" /></a>
	</div>

	<!-- Top Menu Items -->
<c:if test="${!empty pageContext.request.userPrincipal.name}">
	<ul class="nav navbar-right top-nav">
		<li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${pageContext.request.userPrincipal.name} <b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li>
					<a href="<c:url value="/logout" />"><i class="fa fa-fw fa-power-off"></i> <spring:message code="label.logout" /></a>
				</li>
			</ul>
		</li>
	</ul>
</c:if>
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav side-nav">
			<li${currURL eq '/index.app' ? ' class="active"' : ''}>
				<a href="<c:url value="/index.app" />"><i class="fa fa-fw fa-table"></i> <spring:message code="menu.dashboard.table" /></a>
			</li>
			<li${currURL eq '/search.app' ? ' class="active"' : ''}>
				<a href="<c:url value="/search.app" />"><i class="fa fa-fw fa-edit"></i> <spring:message code="menu.dashboard.search" /></a>
			</li>
		</ul>
	</div>
</nav>