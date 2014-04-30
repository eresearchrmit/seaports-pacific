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
	<h6>Current ${element.data[0].variable.description}</h6>
	
	<table class="data display datatable">
		<thead>
			<tr>
				<th>Locations</th>
				<th>Observed return periods (years)</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${element.data}" var="observedExtremeData" varStatus="dataLoopStatus">
				<tr class="even">
					<td class="top nowrap">${observedExtremeData.location}</td>
					<td class="top nowrap">${observedExtremeData.value}</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	
	<i class="credits">(Source: ${element.data[0].sourceName}, <fmt:formatDate value="${element.data[0].creationDate}" pattern="yyyy" />).</i>
</c:if>