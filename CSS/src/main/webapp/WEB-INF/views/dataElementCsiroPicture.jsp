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
	<c:set var="firstDataRow" value="${element.data[0]}" />

	<c:set var="pictureName" value="/resources/img/data/csiro/${fn:replace(firstDataRow.variable.name, ' ', '-')}-${fn:replace(firstDataRow.emissionScenario.name, ' ', '-')}-${fn:replace(report.seaport.region.name, ' ', '-')}-${firstDataRow.year}.png" />
	<c:set var="formattedPictureName" value="${fn:toLowerCase(pictureName)}" />
	<img name="${firstDataRow.variable.name}" src="<c:url value="${formattedPictureName}" />" class="dataelementIllustrationPicture" style="width: 100%" />
</c:if>