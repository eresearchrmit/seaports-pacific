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
	<p style="margin-top:10px; text-align:center; font-weight"><b>Average ${firstDataRow.variable.name} for the ${firstDataRow.region.name} NRM region under a ${firstDataRow.emissionScenario.description} (${firstDataRow.emissionScenario.name}) emissions scenario.</b></p>
		
	<table class="data display datatable">
		<thead>
			<tr>
				<th>Climate Model</th>
				<th>Baseline</th>
				<th>Change centred around ${element.data[0].year}</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${element.data}" var="csiroData" varStatus="dataLoopStatus">
				<tr class="${dataLoopStatus.index % 2 == 0 ? 'even' : 'odd'}">
					<td class="top head">${csiroData.modelName} (${csiroData.model.name})</td>
					<td class="top">${csiroData.baseline} ${csiroData.variable.uom}</td>
					<td class="top"><c:if test="${csiroData.value > 0}">+</c:if>${csiroData.value} ${csiroData.variable.uomVariation}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<i class="credits">Data provided by CSIRO on <fmt:formatDate value="${element.data[0].creationDate}" pattern="dd MMM yyyy" /> was the best available to date</i>
</c:if>