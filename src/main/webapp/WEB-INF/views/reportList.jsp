<%--
 Copyright (c) 2014, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
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
						<th class="sorting_desc_disabled">Edit</th>
						<th class="sorting_desc_disabled">Preview</th>
						<th class="sorting_desc_disabled">Publish</th>
						<th class="sorting_desc_disabled">Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${reportList}" var="report" varStatus="status"> 
					<tr>
						<td><c:out value="${report.name}" /></td>
						<%--<td><a href="/auth/report?id=${report.id}" title="Edit this Report"><img src="<c:url value="/resources/img/icons/report_edit.png" />" alt="Edit"/></a></td>
		                <td><a href="/auth/report/view?id=${report.id}" title="View this Report" target="_blank"><img src="<c:url value="/resources/img/icons/report.png" />" alt="View" /></a></td>
		                <td><a href="/auth/report/publish?id=${report.id}" class="lnkPublishReport" title="Publish this Report"><img src="<c:url value="/resources/img/icons/report_go.png" />" alt="Publish"/></a></td>
						<td><a href="/auth/report/delete?id=${report.id}" class="lnkDeleteReport" title="Delete this Report"><img src="<c:url value="/resources/img/icons/delete.png" />" alt="Delete" /></a></td>--%>
						
						<td>
							<a href="/auth/report?id=${report.id}" title="Edit this Report">
								<button id="btnEditReport" type="button" class="btn btn-small btn-icon btn-blue btn-edit" style="margin-top: 5px" >
									<span></span>Edit
								</button>
							</a>
						</td>
						
						<td>
							<a href="/auth/report/view?id=${report.id}" title="View this Report">
								<button id="btnViewReport" type="button" class="btn btn-small btn-icon btn-blue btn-doc" style="margin-top: 5px" >
									<span></span>View
								</button>
							</a>
						</td>
						
						<td>
							<a href="/auth/report/publish?id=${report.id}" class="lnkPublishReport" title="Publish this Report">
								<button id="btnPublishReport" type="button" class="btn btn-small btn-icon btn-blue btn-globe" style="margin-top: 5px" >
									<span></span>Publish
								</button>
							</a>
						</td>
						
						<td>
							<a href="/auth/report/delete?id=${report.id}" class="lnkDeleteReport" title="Delete this Report">
								<button id="btnDeleteReport" type="button" class="btn btn-small btn-icon btn-blue btn-cross" style="margin-top: 5px" >
									<span></span>Delete
								</button>
							</a>
						</td>
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
			<a href="/auth/report/create">
				<button id="btnConvertToUserStory" type="button" class="btn btn-icon btn-blue btn-plus" >
					<span></span>New Report
				</button>
			</a>
		</c:otherwise>
	</c:choose>
	
	<h2>My Published Reports</h2>
	<c:choose> 
	<c:when test="${not empty publishedReports}">
		<table class="data display datatable" id="tblPublishedReportList">
			<thead>
				<tr>
					<th>Title</th>
					<th>Region</th>
					<th>Published on</th>
					<th class="sorting_desc_disabled">View</th>
					<th class="sorting_desc_disabled">Unpublish</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${publishedReports}" var="publishedReport" varStatus="status"> 
				<tr>
					<td><c:out value="${publishedReport.name}" /></td>
					<td>${publishedReport.report.seaport.region.name}</td>
					<td><fmt:formatDate value="${publishedReport.creationDate}" pattern="dd MMM yyyy (HH:mm:ss)" /></td>
					<%--<td><a href="/public/published-report/view?id=${publishedReport.id}" title="View this Story" target="_blank"><img src="<c:url value="/resources/img/icons/report.png" />" alt="View" /></a></td>
					<td><a href="/auth/report/unpublish?id=${publishedReport.id}" class="lnkUnpublishReport" title="Unpublish this Report"><img src="<c:url value="/resources/img/icons/report_delete.png" />" alt="Unpublish" /></a></td>--%>
					<td>
						<a href="/public/published-report/view?id=${publishedReport.id}" title="View this Report">
							<button id="btnViewPublishedReport" type="button" class="btn btn-small btn-icon btn-blue btn-doc" style="margin-top: 5px" >
								<span></span>View
							</button>
						</a>
					</td>
					<td>
						<a href="/auth/report/unpublish?id=${publishedReport.id}" title="Unpublish this Report" class="lnkUnpublishReport">
							<button id="btnUnpublishReport" type="button" class="btn btn-small btn-icon btn-blue btn-cross" >
								<span></span>Unpublish
							</button>
						</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
			$(document).ready(function () {
				$('#tblPublishedReportList').dataTable();
			});
	 	</script>
	</c:when>
	<c:otherwise>
		<i>There is no published report available.</i>
	</c:otherwise>
	</c:choose>

	<div id="confirmReportPublishModalWindow" title="Really publish this report ?" style="display:none">
		<p>The current status of this report is about to be published. You will be able to make further changes to the report but not the published version.</p> 
		<p>Are you sure you want to publish this report now ?</p>
	</div>
	<div id="confirmReportUnpublishModalWindow" title="Unpublish the report ?" style="display:none">
		<p class="message"><span class="error"><b>Warning: This publication will be permanently deleted !</b></span></p>
		<p>Are you sure you want to unpublish this report ?</p> 
	</div>
	<div id="confirmReportDeletionModalWindow" title="Permanently delete the report ?" style="display:none">
		<p class="message"><span class="error"><b>Warning : Deleting this report will also delete all the data it contains. This action cannot be undone !</b></span></p>
		<p>Are you sure you want to permanently delete this report ?</p> 
	</div>
	<script type="text/javascript">
	setupConfirmBox("confirmReportPublishModalWindow", "lnkPublishReport");
	setupConfirmBox("confirmReportUnpublishModalWindow", "lnkUnpublishReport");
	setupConfirmBox("confirmReportDeletionModalWindow", "lnkDeleteReport");
	</script>
</div>	
