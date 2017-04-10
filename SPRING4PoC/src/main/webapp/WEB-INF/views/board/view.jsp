<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header"><spring:message code="label.dashboard.title" /></h2>
			</div>
		</div>
		<div class="col-lg-12">
			<p class="text-right">TESTER 2017-02-21 19:35:34</p>
		</div>
		<div class="col-lg-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">제목</h3>
				</div>
				<div class="panel-body">
					내용
				</div>
			</div>
		</div>
		<div class="col-lg-12 gap-bottom">
			<div class="btn-group pull-right" role="group">
				<button id="listBtn" class="btn btn-info" type="button"><spring:message code="button.list" /></button>
				<button id="deleteBtn" class="btn btn-primary" type="button"><spring:message code="button.remove" /></button>
			</div>
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

$('#listBtn').on('click', function(event) {
	
	bootbox.alert('목록으로 돌아가는 기능을 완성하세요~');

});

$('#deleteBtn').on('click', function(event) {
	
	bootbox.alert('삭제 기능을 완성하세요~');

});

</script>
