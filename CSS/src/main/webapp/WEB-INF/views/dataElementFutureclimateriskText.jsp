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
	
	<c:set var="futureClimateRisk" value="${element.data[0]}" />

	<p>
	<c:out value="${futureClimateRisk.eventType}" /> have been identified as a <c:out value="${futureClimateRisk.priority}" /> priority risk on <c:out value="${futureClimateRisk.area}" />.
	The risk consists in '<c:out value="${futureClimateRisk.description}" />' . 
	The current thresholds for this risk are: '<c:out value="${futureClimateRisk.details}" />'
	The consequences of this risk in the future are identified as '<c:out value="${futureClimateRisk.futureConsequences}" />'.
	</p>
</c:if>