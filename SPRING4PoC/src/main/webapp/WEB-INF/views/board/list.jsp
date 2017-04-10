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
		
		<div class="row">
			
			<form id="searchFrm" name="searchFrm" role="form">
			
			<input type="hidden" id="currPageNo" name="currPageNo" value="${(currPageNo ne null) ? currPageNo : 1}" />
			
			<div class="col-lg-5">
				<div class="col-lg-6">
					<div class="form-group">
						<div id="datetimepickerFrom" class="input-group date">
							<input type="text" id="srchFromYMDHM" name="srchFromYMDHM" placeholder="<spring:message code="label.form.search.fromDate" />" autofocus class="form-control" />
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="form-group">
						<div id="datetimepickerTo" class="input-group date">
							<input type="text" id="srchToYMDHM" name="srchToYMDHM" placeholder="<spring:message code="label.form.search.toDate" />" autofocus class="form-control" />
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-7">
				<div class="form-group has-success">
					<div class="col-lg-3">
						<select id="searchType" name="searchType" class="form-control">
							<option value=""><spring:message code="label.form.select" /></option>
							<option value="all"><spring:message code="label.form.select.all" /></option>
							<option value="title"><spring:message code="label.form.select.title" /></option>
							<option value="cont"><spring:message code="label.form.select.cont" /></option>
							<option value="createdby"><spring:message code="label.form.select.creator" /></option>
						</select>
					</div>
					<div class="col-lg-3">
						<input type="text" id="searchStr" name="searchStr" placeholder="<spring:message code="label.form.search.string" />" class="form-control" />
					</div>
					<div class="col-lg-6">
						<div class="btn-group pull-right" role="group">
							<button id="resetBtn" class="btn btn-info" type="button"><spring:message code="button.reset" /></button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-12 gap-bottom">
				<div class="col-lg-12">
					<div class="btn-group pull-right" role="group">
						<button id="searchBtn" class="btn btn-primary" type="submit"><spring:message code="button.search" /></button>
						<a id="exportExcel" class="btn btn-success disabled" href="#"><spring:message code="button.excel.export" /></a>
					</div>
				</div>
			</div>
			
			</form>
		
		</div>
		<div class="col-lg-12">
			<div class="table-responsive">
				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<th class="col-md-1"><spring:message code="label.table.seq" /></th>
							<th class="col-md-8"><spring:message code="label.table.title" /></th>
							<th class="col-md-1"><spring:message code="label.table.creator" /></th>
							<th class="col-md-2"><spring:message code="label.table.date.created" /></th>
						</tr>
					</thead>
					<tbody id="listTbody">
					</tbody>
				</table>
			</div>
		</div>
		
		<div id="pageNavBar" class="col-lg-12">
		</div>
	</div>
</div>

<script type="text/javascript">

var DEBUG_MODE = false;
if(!DEBUG_MODE) {
	
	console.debug = function() {};

}

var srchFromYMDHM;
var srchToYMDHM;
var searchType;
var searchStr;

$(function() {
	
	$('#datetimepickerFrom').datetimepicker({
		locale: 'ko',
		format: 'YYYY.MM.DD HH.mm'
	});
	
	$('#datetimepickerTo').datetimepicker({
		useCurrent: false,
		locale: 'ko',
		format: 'YYYY.MM.DD HH.mm'
	});
	
	$('#datetimepickerFrom').on('dp.change', function(e) {
		$('#datetimepickerTo').data("DateTimePicker").minDate(e.date);
	});
	
	$('#datetimepickerTo').on("dp.change", function(e) {
		$('#datetimepickerFrom').data("DateTimePicker").maxDate(e.date);
	});
	
	getBoardList(1);

});

var listData;
$('#searchBtn').on('click', function(event) {
	
	$('#currPageNo').val(1);
	
	getBoardList(1);
	
	event.preventDefault();

});

function setSearchCondition() {
	
	srchFromYMDHM = (!isNaN(moment($('#srchFromYMDHM').val(), 'YYYYMMDD HHmm').format('YYYYMMDDHHmm'))) ? moment($('#srchFromYMDHM').val(), 'YYYYMMDD HHmm').format('YYYYMMDDHHmm') : '0';
	srchToYMDHM = (!isNaN(moment($('#srchToYMDHM').val(), 'YYYYMMDD HHmm').format('YYYYMMDDHHmm'))) ? moment($('#srchToYMDHM').val(), 'YYYYMMDD HHmm').format('YYYYMMDDHHmm') : '0';
	searchType = $('#searchType').val();
	searchStr = $('#searchStr').val();

}

function getBoardList(pageNo) {
	
	setSearchCondition();
	
	var reqUrl = '<c:url value="/rest/board/" />' + pageNo + '/' + srchFromYMDHM + '/' + srchToYMDHM + '/' + searchType + '/' + searchStr;
	$.getJSON(reqUrl, function(resData) {
		
		listData = resData.list;
		console.log(listData);
		showResult();
		
		$('#pageNavBar').empty();
		$('#pageNavBar').append(resData.pageBar);
		
		if(resData.length > 0) {
			
			$('#exportExcel').removeClass('disabled');
		
		} else {
			
			$('#exportExcel').addClass('disabled');
			
			//bootbox.alert('<spring:message code="info.list.empty" />');
		
		}
	
	});

}

function showResult() {
	
	$('#listTbody').empty();
	
	$.each(listData, function(i, col) {
		
		var posResultStr = '<tr class="table-row-on">' +
						   '<td class="col-md-1">' + col.rownum + '</td>' +
						   '<td class="col-md-8">' + col.title + '<input type="hidden" name="seq" value="' + col.seq + '" /></td>' +
						   '<td class="col-md-1">' + col.createdBy + '</td>' +
						   '<td class="col-md-2">' + col.createDate + '</td>' +
						   '</tr>';
		
		$('#listTbody').append(posResultStr);
	
	});

}

$('#exportExcel').on('click', function(event) {
	
	setSearchCondition();
	
	/* if(posResult !== undefined && posResult.length > 0 && !$(this).hasClass('disabled')) {
		
		$(this).addClass('disabled');
		
		var downExcelUrl = '<c:url value="/pos/recordExcel.app" />';
		downExcelUrl += '?srchFromYMDHM=' + srchFromYMDHM + '&srchToYMDHM=' + srchToYMDHM + '&msisdn=' + srchMdn + '&fixtype=' + srchPosType;
		
		$('#exportExcel').attr('href', downExcelUrl);
	
	} */

});

$('#resetBtn').on('click', function(event) {
	
	$('#searchFrm').find('input, select').val('');
	
	event.preventDefault();

});

$('#pageNavBar').on('click', '#btnFirst, #btnPrev, button[name=btnPage], #btnNext, #btnLast',function(event) {
	
	var pageVal = $(this).attr('data-page');
	var currPageVal = $('#currPageNo').val();
	if(pageVal != currPageVal) {
		
		$('#currPageNo').val(pageVal);
		getBoardList(pageVal);
	
	}

});

</script>
