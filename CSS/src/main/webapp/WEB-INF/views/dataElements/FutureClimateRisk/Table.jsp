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
	
	<c:set var="futureClimateRisk" value="${element.data[0]}" />

	<table class="data display datatable">
		<thead>
			<tr>
				<th></th>
				<th>Risk Area</th>
				<th>Risk Description</th>
				<th>Details of Risk</th>
				<th>Future Consequences</th>
				<th>Priority</th>
			</tr>
		</thead>
		<tbody>
			<tr class="even">
				<td class="top nowrap">${futureClimateRisk.eventType}</td>
				<td class="top nowrap">${futureClimateRisk.area}</td>
				<td class="top">${futureClimateRisk.description}</td>
				<td class="top">${futureClimateRisk.details}</td>
				<td class="top">${futureClimateRisk.futureConsequences}</td>
				<td class="top nowrap">${futureClimateRisk.priority}</td>
			</tr>
		</tbody>
	</table>
	<br />
	<p>The priority was calculated based on the '<i>${futureClimateRisk.consequencesRatingString}</i>' consequences rating and the '<i>${futureClimateRisk.likelihoodRatingString}</i>' likelihood rating of the event.</p>
</c:if>