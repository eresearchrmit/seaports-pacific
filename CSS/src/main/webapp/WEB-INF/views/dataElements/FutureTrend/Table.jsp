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
	<h6>Projected ${element.data[0].variable.name} trend (${element.data[0].variable.uom})</h6>
	
	<table class="data display datatable">
		<thead>
			<tr>
				<th>Season</th>
				<th>Emission Scenario</th>
				<th>Centred on 2030</th>
				<th>Centred on 2055</th>
				<th>Centred on 2090</th>
			</tr>
		</thead>
		<tbody>
			<tr class="even">
			
			<c:forEach items="${element.data}" var="futureTrendData" varStatus="dataLoopStatus">
				
				<c:if test="${not empty previousScenario && futureTrendData.emissionScenario.id != previousScenario}">
					</tr>
					<tr class="even">
					<c:if test="${not empty previousSeason && futureTrendData.measureSeason != previousSeason}">
						<td rowspan="3" class="top nowrap">${futureTrendData.measureSeason}</td>
					</c:if>
					<td class="top nowrap">${futureTrendData.emissionScenario.name} (${futureTrendData.emissionScenario.description})</td>
				</c:if>
				<c:if test="${empty previousScenario}">
					<td rowspan="3" class="top nowrap">${futureTrendData.measureSeason}</td>
					<td class="top nowrap">${futureTrendData.emissionScenario.name} (${futureTrendData.emissionScenario.description})</td>
				</c:if>
				
				<td class="top nowrap">${futureTrendData.value} &plusmn; ${futureTrendData.variation}</td>
				<c:set var="previousScenario" value="${futureTrendData.emissionScenario.id}" />
				<c:set var="previousSeason" value="${futureTrendData.measureSeason}" />
			</c:forEach>
			
			</tr>
			
		</tbody>
	</table>
	
	<br />
	<i class="credits">(Source: ${element.data[0].sourceName}, <fmt:formatDate value="${element.data[0].creationDate}" pattern="yyyy" />).</i>
</c:if>