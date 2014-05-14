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
	<h2>${listingTitle}</h2>
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
				<tr onclick="document.location.href = '/public/published-report/view?id=${publishedReport.id}'" class="clickable">
					<td><c:out value="${publishedReport.name}" /></td>
					<td><a href="/public/user/${publishedReport.owner.username}" title="View profile">${publishedReport.owner.firstname} ${publishedReport.owner.lastname}</a></td>
					<td><fmt:formatDate value="${publishedReport.creationDate}" pattern="dd MMM yyyy" /></td>
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
		<i>There is no published report available.</i>
	</c:otherwise>
	</c:choose>
</div>	
