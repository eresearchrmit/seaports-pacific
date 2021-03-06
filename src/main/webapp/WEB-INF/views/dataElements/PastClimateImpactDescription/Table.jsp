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
	<c:set var="weatherEvent" value="${element.data[0]}" />

	<div class="centered">
		<h5 style="color: rgb(69, 114, 167)">
			Consequences Rating of <c:out value="${weatherEvent.type}" /> in <c:out value="${weatherEvent.year}" />
		</h5>
	</div>
	<br />
	
	<p>In <c:out value="${weatherEvent.year}" />, the port experienced a disruptive <c:out value="${fn:toLowerCase(weatherEvent.type)}" /> event. This event impacted the port ${weatherEvent.direct == true ? 'directly' : 'indirectly through the impact on its supply chain'}. The impact was described as: <i>"<c:out value="${weatherEvent.impact}" />"</i></p>
	
	<p>The identified business consequences of these impacts, and their severity can be represented by the following table:</p>
	
	<c:set var="consequenceRatings" value="${fn:split(weatherEvent.consequencesRating, ',')}" />
	<c:set var="consequencesCategoriesString" value="Marine Infrastructure,Port Infrastructure,Port Superstructure,Supply Chain,Operations,Workforce,Financial,Legal / Regulatory,Environment,Trade,Community,Reputation,Other" />
	<c:set var="consequencesCategories" value="${fn:split(consequencesCategoriesString, ',')}" />
	<table class="data display datatable">
		<thead>
			<tr>
				<th>Consequence</th>
				<th>None</th>
				<th>Insignificant</th>
				<th>Minor</th>
				<th>Major</th>
				<th>Extreme</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${consequenceRatings}" var="rating" varStatus="ratingsLoopStatus">
				<tr class="${ratingsLoopStatus.index % 2 == 0 ? 'even' : 'odd'}">
					<td class="top">${consequencesCategories[ratingsLoopStatus.index]}</td>
					<c:forEach var="i" begin="0" end="4">
					   <td class="top"><c:if test="${i == rating}">X</c:if></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
	<p>Other business consequences of these impacts are identified as: <i>"<c:out value="${weatherEvent.consequencesOther}" />"</i></p>
	
	<p>The response to this disruptive climate related event is describes as <i>${weatherEvent.responseAdequate == true ? 'adequate' : 'inadequate'}</i>. The following changes have been nominated as a result of the event: <i>"<c:out value="${weatherEvent.changes}" />"</i></p>
</c:if>