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

	<c:set var="graphId" scope="request" value="${element.category.id}"/>
	<c:if test="${not empty category}">
		<c:set var="graphId" scope="request" value="${category}"/>
	</c:if>
		
	<div class="centered">
		<h5 style="color: rgb(69, 114, 167); font-weight:normal">
			 ${element.data[0].variable.name} (${element.data[0].variable.uom})
		</h5>
	</div>
	
		<script type="text/javascript">
		$(function () {
			setTimeout(function(){
				
	        $('#${graphId}-demographics-Graph${element.id}').highcharts({
	            chart: {
	                defaultSeriesType: 'column',
	                spacingRight: 20
	            },
	            title: {
	                text: ''
	            },
	            xAxis: {
	                categories: [
	                	<c:forEach items="${element.data}" var="demographicsData" varStatus="loop">     
                   			'${demographicsData.year}',	                
						</c:forEach>
					],
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
	            
	            series: [{
                     name: "<c:out value="${element.data[0].variable.name}" />",
                     data: [
						<c:forEach items="${element.data}" var="demographicsData" varStatus="loop">     
                            ${demographicsData.value},	                
						</c:forEach>
	            	]
	            }]
	        });
			},3);
	    });
	</script>
	<div id="${graphId}-demographics-Graph${element.id}" class="highcharts" style="width:95%; margin-bottom:30px">
	</div>
	
	<i class="credits">(Source: ${element.data[0].sourceName}, <fmt:formatDate value="${element.data[0].creationDate}" pattern="yyyy" />).</i>
</c:if>