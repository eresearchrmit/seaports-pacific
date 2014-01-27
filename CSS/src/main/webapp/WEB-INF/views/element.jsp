<%--
 Copyright (c) 2013, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://code.google.com/p/climate-smart-seaports/
--%>
<%@page import="edu.rmit.eres.seaports.model.InputElement"%>
<%@page import="edu.rmit.eres.seaports.helpers.FileTypeHelper"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import= "org.springframework.web.servlet.tags.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
	<%-- Data Element --%>
	<c:when test="${element.class.simpleName == 'DataElement'}">
		<%-- Display the data element according to its data source and display type --%>
		<c:choose>
			<c:when test="${not empty element.data}">
				<c:set var="element" value="${element}" scope="request" />
				<c:set var="formattedDataSourceName" value="${fn:toUpperCase(fn:substring(element.dataSource.name, 0, 1))}${fn:toLowerCase(fn:substring(element.dataSource.name, 1,fn:length(element.dataSource.name)))}" />
				<c:catch var="e">
				    <jsp:include page="dataElement${formattedDataSourceName}${element.displayType}.jsp" />
				</c:catch>
				<c:if test="${!empty e}">
				   <div id="warningMessage" class="message warning">
						<h5>Display error</h5>
						<p>Display error for this display type: ${e}.</p>
					</div>
				</c:if>
			</c:when>
			<c:otherwise>
				<div id="warningMessage" class="message warning">
					<h5>No Data</h5>
					<p>No data corresponding to the selected settings.</p>
				</div>
			</c:otherwise>
		</c:choose>
	</c:when>
	
	<%-- Input Element --%>
	<c:when test="${element.class.simpleName == 'InputElement'}">
			<%-- Need to use a scriplet here in order to test against the 'IsContentTypeJpeg' method --%>
			<% InputElement element = (InputElement)(request.getAttribute("element"));
				if (FileTypeHelper.IsContentTypeJpeg(element.getContentType())) {	%>
				<ul class="prettygallery clearfix">
					<li>
						<a href="data:image/jpeg;charset=utf-8;base64,${element.stringContent}" target="_blank" rel="prettyPhoto" title="${element.name}">
							<img name="${element.name}" src="data:image/jpeg;charset=utf-8;base64,${element.stringContent}" style="max-width:100%; max-height: 500px;" />
						</a>
			    	</li>
				</ul>
		    <% } else { %>
				${element.stringContent}
			<% } %>
	</c:when>

</c:choose>