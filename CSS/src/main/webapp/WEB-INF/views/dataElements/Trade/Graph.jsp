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
			Value of the top ${element.data[0].imported ? 'imported': 'exported'} goods in ${element.data[0].region.name} (${element.data[0].variable.uom})
		</h5>
	</div>
	
		<script type="text/javascript">
		$(function () {
			setTimeout(function(){
				
	        $('#${graphId}-trade-Graph${element.id}').highcharts({
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
	                	text: "${element.data[0].variable.shortName} (${element.data[0].variable.uom})"
	                }
	            },
	            tooltip: {
	                shared: true,
	                valueSuffix: " ${element.data[0].variable.uom}"
	            },
	            legend: {
	                enabled: true
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
	                <c:forEach items="${element.data}" var="tradeData" varStatus="loop">
	                
		                <c:if test="${not empty previousProduct && tradeData.variable.name != previousProduct}" >
		                	]},
		                </c:if>
		                <c:if test="${(empty previousProduct) || (not empty previousProduct && tradeData.variable.name != previousProduct)}" >
			                {
		                	name: "<c:out value="${tradeData.variable.name}" />",
		                	pointInterval: 365 * 24 * 3600 * 1000, // 5 years
		                    pointStart: Date.UTC(${element.data[0].year}, 0, 01),
		                    data: [
	                    </c:if>
                    
						${tradeData.value},
						<c:set var="previousProduct" value="${tradeData.variable.name}" />
	                
	            </c:forEach>
	            	]}
				]
	        });
			},3);
	    });
	</script>
	<div id="${graphId}-trade-Graph${element.id}" class="highcharts" style="width:95%; margin-bottom:30px">
	</div>
	
	<i class="credits">(Source: ${element.data[0].sourceName}, <fmt:formatDate value="${element.data[0].creationDate}" pattern="yyyy" />).</i>
</c:if>