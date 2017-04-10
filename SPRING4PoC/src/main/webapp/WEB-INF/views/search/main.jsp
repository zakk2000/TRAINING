<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header"><spring:message code="label.dashboard.search" /></h2>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-12">
				
				<form id="searchFrm" name="searchFrm" role="form">
				
				<div class="form-group input-group">
					<input type="text" id="searchStr" name="searchStr" placeholder="<spring:message code="label.form.input.search" />" autofocus class="form-control" />
					<span class="input-group-btn"><button id="searchBtn" class="btn btn-default" type="submit"><i class="fa fa-search"></i></button></span>
				</div>
				
				</form>
			
			</div>
		</div>
		<div class="col-lg-12">
			
		</div>
	</div>
</div>

<script type="text/javascript">

var DEBUG_MODE = false;
if(!DEBUG_MODE) {
	
	console.debug = function() {};

}

$(function() {
	
	

});

var listData;
$('#searchBtn').on('click', function(event) {
	
	var searchKeyword = $('#searchStr').val();
	
	if(searchKeyword == '') {
		
		$('#searchStr').prop('required', true);
		
		return;
	
	}
	
	var reqUrl = '<c:url value="/rest/search/" />' + searchKeyword;
	$.getJSON(reqUrl, function(resData) {
		
		console.log('RETURN CHECK >>>>>>>>>>>>>>>>>', resData);
	
	});
	
	event.preventDefault();

});

</script>
