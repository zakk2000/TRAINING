<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="top-content">
	<div class="inner-bg">
		<div class="container">
			<div class="row">
				<div class="col-sm-8 col-sm-offset-2 text">
					<h1><spring:message code="label.page.strong.title" /></h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3 form-box">
					<div class="form-top">
						<div class="form-top-left">
							<h3><spring:message code="label.login.title" /></h3>
						</div>
						<div class="form-top-right">
							<i class="fa fa-lock"></i>
						</div>
					</div>
					<div class="form-bottom">
						
						<c:url var="loginProcUrl" value="/j_spring_security_check" />
						
						<form id="loginFrm" name="loginFrm" method="post" action="${loginProcUrl}" role="form" class="login-form">
						
						<div class="form-group">
							<label class="sr-only" for="userId"><spring:message code="info.login.id" /></label>
							<input type="text" id="userId" name="userId" required placeholder="<spring:message code="info.login.id" />" autofocus class="form-username form-control">
						</div>
						<div class="form-group">
							<label class="sr-only" for="userPw"><spring:message code="info.login.pw" /></label>
							<input type="password" id="userPw" name="userPw" required placeholder="<spring:message code="info.login.pw" />" class="form-password form-control">
						</div>
						<button type="submit" id="loginBtn" class="btn" data-loading-text="<spring:message code="button.login.ing" />"><spring:message code="button.login" /></button>
						
						</form>
					
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3 social-login">
					<p><spring:message code="label.page.copyright" /></p>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

$(function() {
	
	$(".btn").click(function() {
		
		$(this).button('loading').delay(1000).queue(function() {
			
			$(this).button('reset');
			$(this).dequeue();
		
        });
	
    });

});

</script>