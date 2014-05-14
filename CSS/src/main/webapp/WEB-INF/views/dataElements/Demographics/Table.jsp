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
	<h6>${element.data[0].variable.name} in ${element.data[0].region.name}</h6>
	
	<table class="data display datatable">
		<thead>
			<tr>
				<th>Year</th>
				<th>${element.data[0].variable.name} (${element.data[0].variable.uom})</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${element.data}" var="demographicsData" varStatus="dataLoopStatus">
			<tr class="even">
				<td class="top nowrap">${demographicsData.year}</td>
				<td class="top nowrap"><fmt:formatNumber type="number" maxFractionDigits="0" value="${demographicsData.value}" /></td>
			</tr>
		</c:forEach>
			
		</tbody>
	</table>
	
	<br/>
	<i class="credits">(Source: ${element.data[0].sourceName}, <fmt:formatDate value="${element.data[0].creationDate}" pattern="yyyy" />).</i>
</c:if>