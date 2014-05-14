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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="grid_12">

	<%-- Display errors, warnings, notifications --%>
	<c:set var="successMessage" scope="request" value="${successMessage}"/>
	<c:set var="warningMessage" scope="request" value="${warningMessage}"/>
	<c:set var="errorMessage" scope="request" value="${errorMessage}"/>
	<jsp:include page="notifications.jsp" />
	<br />

	<c:if test="${not empty report}">	
		<a href="javascript: window.print()" id="btnPrint" class="floatright btn-margin">
			<button class="btnAddDataElement btn btn-icon btn-blue btn-print">
				<span></span>Print
			</button>
		</a>
		
		<c:if test="${empty publicView}">
		<a href="javascript: window.close()" id="btnClosePreview" class="floatright btn-margin">
			<button class="btnAddDataElement btn btn-icon btn-blue btn-arrow-left">
				<span></span>Close Preview
			</button>
		</a>
		</c:if>
		<div class="clear"></div>
		<center>
			<h2><c:out value="${report.name}" /></h2>
			<h4><c:out value="${report.seaport.region.name}" /> region</h4>
		</center>
	</c:if>
	
	

	
	<%-- Display content --%>
	<c:if test="${not empty report}">	
		<c:if test="${not empty report.elements}">
			<div class="element-container">	 	
			 	
			 	<!-- Iteration on every element in the report -->
			 	
				<c:forEach items="${allCategories}" var="category" varStatus="status">
				 	<c:forEach items="${report.elements}" var="element" varStatus="status">
						<c:if test="${element.category.id == category.id}">
							<c:if test="${element.fullWidth == true}">
								<div class="clearfix"></div>
							</c:if>
							
							<c:if test="${element.included == true}">
								<div class="element${element.fullWidth == false ? ' halfwidth' : '' }">
									<c:set var="element" scope="request" value="${element}"/>
						 			<c:set var="elementLoopIndex" scope="request" value="${status.index}"/>
									<jsp:include page="element.jsp" />
								</div>
							</c:if>
							<c:if test="${element.pageBreakAfter == true}">
								<div class="pagebreak"></div>
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach>
			</div>
		</c:if>
		
		<br/><br/><br/><br/>
		<p class="report-content">${report.fullDescription}</p>

		
		<div class="report-license">
			<div class="floatleft">
				<a href="http://creativecommons.org/licenses/by-nc-nd/3.0/" id="lnkCCLicence" target="_blank">
					<img src="<c:url value="/resources/img/help/cc-by-nc-nd.png" />" title="View the full licence statement" />
				</a> 
			</div>	
			<div class="floatleft license-text">
				&copy; ${report.owner.firstname} ${report.owner.lastname}
				<br />
				<a href="/public/terms-of-service#license" id="lnkLicence" target="_blank">This report is licensed under a Creative Commons Attribution license.</a><br />
			</div>
		</div>
		
	</c:if>
	<div class="clearfix"></div><br />
</div>