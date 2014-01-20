<%--
 Copyright (c) 2013, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://code.google.com/p/climate-smart-seaports/
--%>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.codec.binary.*"%>
<%@ page import= "org.springframework.web.servlet.tags.*" %>
<%@ page import="org.apache.commons.lang.*" %>
<%@ page language="java" import="edu.rmit.eres.seaports.model.Report" %>
<%@ page language="java" import="edu.rmit.eres.seaports.model.User" %>
<%@ page language="java" import="edu.rmit.eres.seaports.model.DataElement" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="grid_12">
	<div style="margin-left: 20px; float:left">
		<h2>${listingTitle}</h2>
	</div>
	
	<a class="lnkConvertToUserStory" href="/auth/report/create" style="margin-right: 10px; float:right">
		<button id="btnConvertToUserStory" type="button" class="btn btn-icon btn-blue btn-plus" >
			<span></span>New Report
		</button>
	</a>
	<div class="clear"></div><br />
	
	<c:set var="successMessage" scope="request" value="${successMessage}"/>
	<c:set var="warningMessage" scope="request" value="${warningMessage}"/>
	<c:set var="errorMessage" scope="request" value="${errorMessage}"/> 			
	<jsp:include page="notifications.jsp" />
	
	<c:choose> 
		<c:when test="${not empty reportList}">
			<table class="data display datatable" id="tblReportList">
				<thead>
					<tr>
						<th>Title</th>
						<th>View</th>
						<th>Edit</th>
						<th>Publish</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${reportList}" var="report" varStatus="status"> 
					<tr>
						<td><c:out value="${report.name}" /></td>
						<td><a href="/auth/report/view?id=${report.id}" title="View this Report" target="_blank"><img src="<c:url value="/resources/img/icons/page_white.png" />" alt="View" /></a></td>
						<c:choose>
							<c:when test="${report.mode == 'published'}">
								<td></td>
								<td>Published (<fmt:formatDate value="${report.publishDate}" pattern="dd MMM yyyy" />) <img src="<c:url value="/resources/img/icons/accept.png" />" /></td>
								<td></td>
		                 	</c:when>
		                 	<c:otherwise>
		                 		<td><a href="/auth/report?id=${report.id}" title="Edit this Report"><img src="<c:url value="/resources/img/icons/pencil.png" />" alt="Edit"/></a></td>
		                 		<td><a href="/auth/report/publish?id=${report.id}" class="lnkPublishReport" title="Publish this Report"><img src="<c:url value="/resources/img/icons/world_go.png" />" alt="Publish"/></a></td>
								<td><a href="/auth/report/delete?id=${report.id}" class="lnkDeleteReport" title="Delete this Report"><img src="<c:url value="/resources/img/icons/delete.png" />" alt="Delete" /></a></td>
		                 	</c:otherwise>
						</c:choose>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<script type="text/javascript">
				$(document).ready(function () {
					$('#tblReportList').dataTable();
				});
		 	</script>
		</c:when>
		<c:otherwise>
			<p><i>You don't have any report.</i></p>
			<p><i>Click on the 'New Report' button to start.</i></p>
			<a class="lnkConvertToUserStory" href="/auth/report/create">
				<button id="btnConvertToUserStory" type="button" class="btn btn-icon btn-blue btn-plus" >
					<span></span>New Report
				</button>
			</a>
		</c:otherwise>
	</c:choose>

	<div id="confirmUserStoryPublishModalWindow" title="Really publish this report ?" style="display:none">
		<p class="message"><span class="error"><b>Publishing this report will automatically submit it to ANDS, and appear on Research Data Australia.</b></span></p>
		<p class="message"><span class="error"><b>Once published, a report cannot be edited, deleted or made private again.</b></span></p>
		<p>Are you sure you want to publish this report ?</p> 
	</div>
	<div id="confirmUserStoryDeletionModalWindow" title="Permanently delete the report ?" style="display:none">
		<p class="message"><span class="error"><b>Warning: this will also delete all the data elements and texts contained in this report. This action cannot be undone !</b></span></p>
		<p>Are you sure you want to permanently delete this report ?</p> 
	</div>
	<script type="text/javascript">
	setupConfirmBox("confirmUserStoryPublishModalWindow", "lnkPublishReport");
	setupConfirmBox("confirmUserStoryDeletionModalWindow", "lnkDeleteReport");
	</script>
</div>	
