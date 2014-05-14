<%--
 Copyright (c) 2014, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import= "org.springframework.web.servlet.tags.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${not empty element.data}">

	<h6>Trend in ${element.data[0].variable.name} (<fmt:formatDate value="${element.data[0].periodStart}" pattern="yyyy" />-<fmt:formatDate value="${element.data[0].periodEnd}" pattern="yyyy" />):</h6>
	<br />
	<c:forEach items="${element.data}" var="observedTrendData" varStatus="dataLoopStatus">
		<p>The average ${observedTrendData.measureSeason} ${fn:toLowerCase(observedTrendData.variable.name)} ${observedTrendData.value >= 0 ? 'increased' : 'decreased'} by ${observedTrendData.value}${observedTrendData.variable.uom}<c:if test="${not empty observedTrendData.percentageValue}"> (approximately ${observedTrendData.percentageValue}${observedTrendData.variable.uomVariation} per annum)</c:if>.</p>
	</c:forEach>
	<br />
	<i class="credits">(Source: ${element.data[0].sourceName}, <fmt:formatDate value="${element.data[0].creationDate}" pattern="yyyy" />).</i>
</c:if>