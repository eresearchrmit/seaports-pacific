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
	
	<c:set var="currentClimateRisk" value="${element.data[0]}" />

	<c:set var="consequenceRatings" value="${fn:split(currentClimateRisk.consequencesRating, ',')}" />
	<c:set var="consequencesCategoriesString" value="Marine Infrastructure,Port Infrastructure,Port Superstructure,Supply Chain,Operations,Workforce,Financial,Legal / Regulatory,Environment,Trade,Community,Reputation,Other" />
	<c:set var="consequencesCategories" value="${fn:split(consequencesCategoriesString, ',')}" />
	
	<div class="centered">
		<h5 style="color: rgb(69, 114, 167)">
			Current climate risk perception assessment for <c:out value="${element.report.seaport.name}" /> - <fmt:formatDate value="${element.creationDate}" pattern="dd MMM yyyy" />
		</h5>
	</div>
	
	<table class="data display datatable">
		<thead>
			<tr>
				<th>Climate Change Effect</th>
				<th>Not Vulnerable</th>
				<th>Could be Vulnerable</th>
				<th>Somewhat vulnerable</th>
				<th>Moderately Vulnerable</th>
				<th>Significantly Vulnerable</th>
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
</c:if>