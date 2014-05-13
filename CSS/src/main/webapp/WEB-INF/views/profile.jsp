<%--
 Copyright (c) 2013, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://code.google.com/p/climate-smart-seaports/
--%>
<%@ page session="false" %>
<%@ page import= "org.springframework.web.servlet.tags.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="grid_12">

	<c:set var="successMessage" scope="request" value="${successMessage}"/>
	<c:set var="warningMessage" scope="request" value="${warningMessage}"/>
	<c:set var="errorMessage" scope="request" value="${errorMessage}"/>
	<jsp:include page="notifications.jsp" />

	<c:if test="${not empty userProfile}">
		<h2>${userProfile.firstname} ${userProfile.lastname}'s profile</h2>
	
		<div style="font-size: 1.2em; margin-bottom: 40px">
			<strong>Username</strong>: ${userProfile.username}<br />
			<strong>E-mail address</strong>: ${userProfile.email}<br />
			<strong>NLA Number</strong>: ${not empty userProfile.nlaNumber ? userProfile.nlaNumber : 'N/A'} 
		</div>
		
		<h5>Reports published by ${userProfile.firstname} ${userProfile.lastname}:</h5>
		<c:choose> 
			<c:when test="${not empty publishedReports}">
				<table class="data display datatable" id="tblPublishedReportList">
					<thead>
						<tr>
							<th>Title</th>
							<th>Author</th>
							<th>Published on</th>
							<th>Region</th>
							<th class="sorting_desc_disabled">View</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${publishedReports}" var="publishedReport" varStatus="status">
							<tr>
								<td><c:out value="${publishedReport.name}" /></td>
								<td>${publishedReport.owner.firstname} ${publishedReport.owner.lastname}</td>
								<td><fmt:formatDate value="${report.creationDate}" pattern="dd MMM yyyy" /></td>
								<td>${publishedReport.report.seaport.region.name}</td>
								<td>
									<a href="/public/published-report/view?id=${publishedReport.report.id}" title="View this Report" target="_blank">
										<button id="btnViewReport" type="button" class="btn btn-small btn-icon btn-blue btn-doc" style="margin-top: 5px" >
											<span></span>View
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
				<i>${userProfile.firstname} ${userProfile.lastname} hasn't published any report yet.</i>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>