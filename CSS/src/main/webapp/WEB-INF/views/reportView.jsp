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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="grid_12">

	<c:if test="${not empty report}">	
		<a href="javascript: window.print()" id="btnPrint" style="margin-right: 10px; float:right">
			<button class="btnAddDataElement btn btn-icon btn-blue btn-print">
				<span></span>Print
			</button>
		</a>
		
		<c:if test="${empty publicView}">
		<a href="javascript: window.close()" id="btnClosePreview" style="margin-right: 10px; float:right">
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
	
	<br />
	<c:if test="${not empty successMessage}">
		<div id="successMessage" class="message success">
			<h5>Success !</h5>
			<p>${successMessage}.</p>
		</div>
	</c:if>
	<c:if test="${not empty errorMessage}">
		<div class="message error">
			<h5>Error</h5>
			<p>${errorMessage}</p>
		</div>
	</c:if>
	
	<c:if test="${not empty report}">
	<p class="report-content">${report.fullDescription}</p>
	<br/><br/><br/>
	
	<c:if test="${not empty report.elements}">
	<div style="text-align:left; width:90%; margin-right:auto;margin-left:auto">	 	
	 	<!-- Iteration on every element in the User Story -->
	 	<c:forEach items="${report.elements}" var="element" varStatus="status">
			
			<c:set var="element" scope="request" value="${element}"/>
	 		<c:set var="elementLoopIndex" scope="request" value="${status.index}"/>
	 			
			<c:if test="${element.included == true}">
				<c:if test="${element.class.simpleName == 'DataElement'}">
 					DATA
 				</c:if>
				<c:if test="${element.class.simpleName == 'InputElement'}">
 					<c:choose>
						<c:when test="${element.contentType == 'jpg' || element.contentType == 'jpeg'}">
							<ul class="prettygallery clearfix">
								<li>
									<a href="data:image/jpeg;charset=utf-8;base64,${element.stringContent}" target="_blank" rel="prettyPhoto" title="${element.name}">
										<img name="${element.name}" src="data:image/jpeg;charset=utf-8;base64,${element.stringContent}" style="max-width:100%; max-height: 500px;" />
									</a>
						    	</li>
							</ul>
					    </c:when>
					
						<c:otherwise>
							<c:out value="${element.stringContent}" escapeXml="false" />
						</c:otherwise>
					</c:choose>
 					</c:if>				
				<br /><br /><br /><br />
			</c:if>
		</c:forEach>
	</div>
	<div class="report-license" style="margin: 25px 25px">
		<div style="float:left">
			<a href="http://creativecommons.org/licenses/by-nc-nd/3.0/" id="lnkCCLicence" target="_blank">
				<img src="<c:url value="/resources/img/help/cc-by-nc-nd.png" />" title="View the full licence statement" />
			</a> 
		</div>	
		<div style="float:left; padding-left: 5px">
			&copy; ${report.owner.firstname} ${report.owner.lastname} <fmt:formatDate value="${report.publishDate}" pattern="yyyy" />
			<br />
			<a href="/public/terms-of-service#license" id="lnkLicence" target="_blank">This report is licensed under a Creative Commons Attribution license.</a><br />
		</div>
	</div>
	</c:if>
	</c:if>
	<div class="clearfix"></div><br />
</div>