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

	<c:set var="graphId" scope="request" value="${element.category.id}"/>
	<c:if test="${not empty category}">
		<c:set var="graphId" scope="request" value="${category}"/>
	</c:if>
	
	<div class="centered">
		<h5 style="color: rgb(69, 114, 167); font-weight:normal">
			Projected ${element.data[0].variable.description}
		</h5>
	</div>
	
		<script type="text/javascript">
		$(function () {
			setTimeout(function(){
				
	        $('#${graphId}-futureExtreme-Graph${element.id}').highcharts({
	            chart: {
	                zoomType: 'x',
	                spacingRight: 20
	            },
	            title: {
	                text: ''
	            },
	            xAxis: {
	                type: 'datetime',
	                //maxZoom: 25 * 365 * 24 * 3600 * 1000, // 5 years
	                title: {
	                    text: null
	                }
	            },
	            yAxis: {
	                title: {
	                	text: "Return Period (years)"
	                }
	            },
	            tooltip: {
	                shared: true,
	                useHTML: true,
	                xDateFormat: '%Y',
	                valueSuffix: ' ${element.data[0].variable.uom}'
	            },
	            legend: {
	                enabled: false
	            },
	            plotOptions: {
	                area: {
	                    fillColor: {
	                        linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
	                        stops: [
	                            [0, Highcharts.getOptions().colors[0]],
	                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
	                        ]
	                    },
	                    lineWidth: 1,
	                    marker: {
	                        enabled: false
	                    },
	                    shadow: false,
	                    states: {
	                        hover: {
	                            lineWidth: 1
	                        }
	                    },
	                    threshold: null
	                }
	            },
	            
	            series: [
	                <c:forEach items="${element.data}" var="futureExtremeData" varStatus="loop">
	                
		                <c:if test="${not empty previousLocation && futureExtremeData.location != previousLocation}" >
		                	]},
		                </c:if>
		                <c:if test="${(empty previousLocation) || (not empty previousLocation && futureExtremeData.location != previousLocation)}" >
			                {
		                	name: "<c:out value="${futureExtremeData.location}" />",
		                    data: [
	                    </c:if>
                    
						[Date.UTC(${futureExtremeData.year}, 0, 1), ${futureExtremeData.value}],
						<c:set var="previousLocation" value="${futureExtremeData.location}" />
	                
	            </c:forEach>
	            	]}
				]
	        });
			},3);
	    });
	</script>
	<div id="${graphId}-futureExtreme-Graph${element.id}" class="highcharts" style="width:95%; margin-bottom:30px">
	</div>
	
	<i class="credits">(Source: ${element.data[0].sourceName}, <fmt:formatDate value="${element.data[0].creationDate}" pattern="yyyy" />).</i>
</c:if>