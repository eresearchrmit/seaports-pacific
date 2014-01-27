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
	<p style="margin-top:10px; text-align:center; font-weight"><b>Average ${firstDataRow.variable.name} for the ${firstDataRow.region.name} region under a ${firstDataRow.emissionScenario.description} (${firstDataRow.emissionScenario.name}) emissions scenario.</b></p>

	<table class="data display datatable" style="width:100%">
		<thead>
			<tr>
				<th>Latitude</th>
				<th>Longitude</th>
				<th>Change centered around ${element.data[0].year}</th>
			</tr>
		</thead>
			<tbody>
				<c:forEach items="${element.data}" var="cmarData">
					<c:forEach items="${cmarData.values}" var="entry" varStatus="entryLoopStatus">
					<tr class="${entryLoopStatus.index % 2 == 0 ? 'even' : 'odd'}">
						<td class="top">
							${entry.key.x}
						</td>
						<td class="top">
							${entry.key.y}
						</td>
						<td class="top">
							${entry.value} ${cmarData.variable.uom}
						</td>
					</tr>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	
	<br />
	<i class="credits">Data provided by CSIRO Marine and Atmospheric Research on <fmt:formatDate value="${firstDataRow.creationDate}" pattern="dd MMM yyyy" /> was the best available to date.</i>
</c:if>



	