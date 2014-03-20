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
	
	<p style="margin-top:10px; text-align:center; font-weight"><b>Average ${element.data[0].variable.name} for the ${element.data[0].region.name} region under a ${element.data[0].emissionScenario.description} (${element.data[0].emissionScenario.name}) emissions scenario.</b></p>
	
	<c:forEach items="${element.data}" var="csiroData" varStatus="dataLoopStatus">
		<p>According to the <b>${csiroData.modelName} model (${csiroData.model.name})</b>, the average ${csiroData.variable.name} for the ${csiroData.region.name} region will have a change of <b>${csiroData.value} ${csiroData.variable.uomVariation}</b> compared to a baseline of <b>${csiroData.baseline} ${csiroData.variable.uom}</b>, under a ${csiroData.emissionScenario.description} (${csiroData.emissionScenario.name}) emissions scenario.</p>
	</c:forEach>
	
	<i class="credits">Data provided by CSIRO on <fmt:formatDate value="${element.data[0].creationDate}" pattern="dd MMM yyyy" /> was the best available to date</i>
</c:if>