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
	<br />
	
	<table>
	
	<thead>
		<tr>
			<th class="top nowrap">Emission Scenario</th>
			<th class="top nowrap center">Centred on 2030</th>
			<th class="top nowrap center">Centred on 2055</th>
			<th class="top nowrap center">Centred on 2090</th>
		</tr>
	</thead>
	
	<tr class="even">
		<c:forEach items="${element.data}" var="futureTrendData" varStatus="dataLoopStatus">
		
			<%-- Stops displaying if switching to a different season, because the pictures are 
			the same for all seasons (avoids displaying 3 times the same series of pictures) --%>
			<c:if test="${not empty previousSeason && previousSeason != futureTrendData.measureSeason}">
				<c:set var="stopFlag" value="stop" />
			</c:if>
		
			<c:if test="${stopFlag != 'stop'}">
			
			<c:if test="${empty previousScenario || (not empty previousScenario && futureTrendData.emissionScenario.id != previousScenario)}">
				</tr>
				<tr class="even">
				<td class="top nowrap"><b><br />${futureTrendData.emissionScenario.name} (${futureTrendData.emissionScenario.description})</b></td>
			</c:if>
			
			<td>
					<c:if test="${not empty futureTrendData.picture}">
					<ul class="prettygallery clearfix">
						<li>
							<a href="/resources/img/data/futureTrend/${futureTrendData.picture}.png" target="_blank" rel="lightbox" title="Projected trend of ${futureTrendData.variable.name} (${futureTrendData.variable.uom}) centred on ${futureTrendData.year} under a ${futureTrendData.emissionScenario.description} emission scenario (${futureTrendData.emissionScenario.name})" class="centered">
								<img name="${element.name}" src="/resources/img/data/futureTrend/${futureTrendData.picture}.png" style="max-width:80%; max-height: 500px;" />
							</a>
				    	</li>
					<c:set var="pictureDisplayed" value="true" />
					</ul>
					<c:set var="previousScenario" value="${futureTrendData.emissionScenario.id}" />
					<c:set var="previousSeason" value="${futureTrendData.measureSeason}" />
					</c:if>
				
			</td>
			
			</c:if>
		</c:forEach>
	</tr>
	
	</table>
	
	<c:if test="${not empty pictureDisplayed}">
	<br />
	<i class="credits">(Source: ${element.data[0].sourceName}, <fmt:formatDate value="${element.data[0].creationDate}" pattern="yyyy" />).</i>
	</c:if>
</c:if>