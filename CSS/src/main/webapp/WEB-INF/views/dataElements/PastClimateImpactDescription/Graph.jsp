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
		<h5 style="color: rgb(69, 114, 167); font-weight:normal">
			Consequences Rating of <c:out value="${weatherEvent.type}" /> in <c:out value="${weatherEvent.year}" />
		</h5>
	</div>
	
	<p>In <c:out value="${weatherEvent.year}" />, the port experienced a disruptive <c:out value="${fn:toLowerCase(weatherEvent.type)}" /> event. This event impacted the port ${weatherEvent.direct == true ? 'directly' : 'indirectly through the impact on its supply chain'}. The impact was described as: <i>"<c:out value="${weatherEvent.impact}" />"</i></p>
	
	<p>The identified business consequences of these impacts, and their severity can be represented by the following graph:</p>
	
	<c:set var="graphId" scope="request" value="${element.category.id}"/>
	<c:if test="${not empty category}">
		<c:set var="graphId" scope="request" value="${category}"/>
	</c:if>
	
	<script type="text/javascript">
	$(function () {
		setTimeout(function(){
		var consequencesLabels = ["None", "Insignificant", "Moderate", "Major", "Extreme"];
		$('#${graphId}-vulnerability-SpiderGraph${element.id}').highcharts({   
		    chart: {
		        polar: true,
		        type: 'area'
		    },
		    legend: {
		    	enabled: false
		    },
		    title: {
		    	text: ''
		    },
		    pane: {
		    	size: '70%'
		    },
		    xAxis: {
		        categories: ["Marine Infrastructure", "Port Infrastructure", "Port Superstructure", 
		             		"Supply Chain", "Operations", "Workforce", "Financial", "Legal / Regulatory", "Environment", "Trade", 
		            		"Community", "Reputation", "Other"],
		        tickmarkPlacement: 'on',
		        lineWidth: 0
		    },
		    yAxis: {
		        gridLineInterpolation: 'polygon',
		        lineWidth: 0,
		        min: 0,
		        max: 4,
		        step: 0.5,
		        labels: {
		            enabled: false
		        }
		    },
		    tooltip: {
		    	shared: true,
		    	formatter: function() {
	                var s = '<b>'+ this.x +'</b>';
	                $.each(this.points, function(i, point) {
	                    s += '<br/>' + point.series.name +': <span style="color:' + point.series.color + '">' + consequencesLabels[point.y] + '</span>';
	                });
	                return s;
	            }
		    },
		    plotOptions: {
		    	series: {
		    		pointInterval: 1	
		    	}
		    },
		    series: [{
		        name: 'Consequence rating',
		        data: [<c:out value="${weatherEvent.consequencesRating}" />],
		        pointPlacement: 'on',
		        color: '#CC0000',
		        fillColor: {
	                linearGradient: [0, 0, 0, 300],
	                stops: [
	                    [0, 'rgb(69, 114, 167)'],
	                    [1, 'rgba(2,0,0,0)']
	                ]
	            }
		    }]
		});
		}, 5);
	});
	</script>
	
	<div id="${graphId}-vulnerability-SpiderGraph${element.id}" class="highcharts" style="width: 95%; margin: 10px auto">
	</div>
	
	<p>Other business consequences of these impacts are identified as: <i>"<c:out value="${weatherEvent.consequencesOther}" />"</i></p>
	
	<p>The response to this disruptive climate related event is described as <i>${weatherEvent.responseAdequate == true ? 'adequate' : 'inadequate'}</i>. The following changes have been nominated as a result of the event: <i>"<c:out value="${weatherEvent.changes}" />"</i></p>
</c:if>