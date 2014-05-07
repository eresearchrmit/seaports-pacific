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
	<h6>Projected ${element.data[0].variable.description}</h6>
	
	<table class="data display datatable">
		<thead>
			<tr>
				<th>Locations</th>
				<th>Observed</th>
				<th>2025</th>
				<th>2050</th>
				<th>2075</th>
				<th>2100</th>
			</tr>
		</thead>
		<tbody>
			<tr class="even">
			
			<c:forEach items="${element.data}" var="observedExtremeData" varStatus="dataLoopStatus">
			
			<%-- Changes row if location is different from previous --%>
			<c:if test="${not empty previousLocation && observedExtremeData.location != previousLocation}">
				</tr>
				<tr class="even">
				<td class="top nowrap">${observedExtremeData.location}</td>
			</c:if>
			
			<c:if test="${empty previousLocation}">
				<td class="top nowrap">${observedExtremeData.location}</td>
			</c:if>
			
				<td class="top nowrap">${observedExtremeData.value}</td>
				<c:set var="previousLocation" value="${observedExtremeData.location}" />
			</c:forEach>
			
			</tr>
			
		</tbody>
	</table>
	
	<i class="credits">(Source: ${element.data[0].sourceName}, <fmt:formatDate value="${element.data[0].creationDate}" pattern="yyyy" />).</i>
</c:if>