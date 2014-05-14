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
	<h6>Value of the top ${element.data[0].imported ? 'imported': 'exported'} Goods in ${element.data[0].region.name}</h6>
	
	<table class="data display datatable">
		<thead>
			<tr>
				<th></th>
				
				<c:forEach var="year" begin="2000" end="2010">
				<th><c:out value="${year}"/></th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<tr class="even">
			
			<c:forEach items="${element.data}" var="tradeData" varStatus="dataLoopStatus">
			
			<%-- Changes row if location is different from previous --%>
			<c:if test="${not empty previousProduct && tradeData.variable.name != previousProduct}">
				</tr>
				<tr class="even">
				<td class="top nowrap">${tradeData.variable.name}</td>
			</c:if>
			
			
			<c:if test="${empty previousProduct}">
				<td class="top nowrap">${tradeData.variable.name}</td>
			</c:if>
			
				<td class="top nowrap"><fmt:formatNumber type="number" maxFractionDigits="0" value="${tradeData.value}" /></td>
				<c:set var="previousProduct" value="${tradeData.variable.name}" />
			</c:forEach>
			
			</tr>
			
		</tbody>
	</table>
	
	<br/>
	<i class="credits">(Source: ${element.data[0].sourceName}, <fmt:formatDate value="${element.data[0].creationDate}" pattern="yyyy" />).</i>
</c:if>