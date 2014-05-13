<%--
 Copyright (c) 2013, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://code.google.com/p/climate-smart-seaports/
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import= "org.springframework.web.servlet.tags.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${not empty element.data}">

	<h6>Trend in ${element.data[0].variable.name} (<fmt:formatDate value="${element.data[0].periodStart}" pattern="yyyy" />-<fmt:formatDate value="${element.data[0].periodEnd}" pattern="yyyy" />):</h6>

	<c:forEach items="${element.data}" var="observedTrendData" varStatus="dataLoopStatus">
		<c:if test="${not empty observedTrendData.picture}">
			<c:if test="${empty pictureDisplayed}">
				<ul class="prettygallery clearfix">
					<li>
						<a href="/resources/img/data/observedTrend/${observedTrendData.picture}.png" target="_blank" rel="lightbox" title="${element.name}" class="centered">
							<img name="${element.name}" src="/resources/img/data/observedTrend/${observedTrendData.picture}.png" style="max-width:100%; max-height: 500px;" />
						</a>
			    	</li>
				</ul>
			</c:if>
			<c:set var="pictureDisplayed" value="true" />
		</c:if>
	</c:forEach>
	
	<c:if test="${empty pictureDisplayed}">
		<div id="errorMessage" class="message warning">
			<h5>Warning</h5>
			<p><c:out value="No picture available for the selected variable." /></p>
		</div>
	</c:if>
	
	<c:if test="${not empty pictureDisplayed}">
		<i class="credits">(Source: ${element.data[0].sourceName}, <fmt:formatDate value="${element.data[0].creationDate}" pattern="yyyy" />).</i>
	</c:if>
</c:if>