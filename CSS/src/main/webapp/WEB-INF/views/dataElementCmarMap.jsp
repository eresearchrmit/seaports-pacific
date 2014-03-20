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
	
	<c:set var="mapId" scope="request" value="${fn:replace(element.category.name, ' ', '')}"/>
	<c:if test="${not empty category}">
		<c:set var="mapId" scope="request" value="${category}"/>
	</c:if>

	<script type="text/javascript">
		var map${mapId}${element.id};
		var mapBounds${element.id};
		
		// Initialize the map
		function initializeMap${mapId}${element.id}() {
		
			var mapOptions = {
				mapTypeControlOptions: {
					mapTypeIds: [google.maps.MapTypeId.TERRAIN]
				},
				mapTypeId: google.maps.MapTypeId.TERRAIN
				
			};
			
			map = new google.maps.Map(document.getElementById('map-${mapId}-canvas-${element.id}'), mapOptions);
		
			var str = "https://maps.googleapis.com/maps/api/staticmap?size=600x300&maptype=terrain&sensor=false";
			
			var image = '<c:url value="/resources/img/icons/transparent.png" />';
		    var bounds = new google.maps.LatLngBounds();
			<c:forEach items="${element.data}" var="cmarData">
				<c:forEach items="${cmarData.values}" var="entry">
					// Create a marker with label for each coodinates
					var marker = new MarkerWithLabel({
				       position: new google.maps.LatLng(${entry.key.x}, ${entry.key.y}),
				       draggable: false,
				       raiseOnDrag: false,
				       map: map,
				       icon: image, // transparent icon
				       labelContent: '+ ${entry.value} ${cmarData.variable.uom}',
				       labelAnchor: new google.maps.Point(4, 6),
				       labelClass: "mapLabels",
				       labelStyle: {opacity: 0.90}
				     });
					bounds.extend(marker.position);
					
					str += "&markers=size:mid%7Ccolor:blue%7C${entry.key.x},${entry.key.y}";
				</c:forEach>
			</c:forEach>
			map.setCenter(bounds.getCenter());
			map.fitBounds(bounds);
			
			$("#map-${mapId}-picture-${element.id}").attr("src", str);
			
			mapBounds${element.id} = bounds;
			map${mapId}${element.id} = map;
		}
				
		google.maps.event.addDomListener(window, 'load', initializeMap${mapId}${element.id});
		
		// Refresh the map when changing tabs, if there are tabs
		$(function() {
			if ($("#tabs").length) {
				$("#tabs").on( "tabsactivate", function(event, ui){
					google.maps.event.trigger(map${mapId}${element.id}, 'resize');
					map${mapId}${element.id}.setCenter(mapBounds${element.id}.getCenter());
					map${mapId}${element.id}.fitBounds(mapBounds${element.id});
				});
			}
		});
	</script>
	
	<c:set var="firstDataRow" value="${element.data[0]}" />
	<p style="margin-top:10px; font-weight"><b>Average ${firstDataRow.variable.name} for the ${firstDataRow.region.name} region under a ${firstDataRow.emissionScenario.description} (${firstDataRow.emissionScenario.name}) emissions scenario.</b></p>
	
	<div id="map-${mapId}-canvas-${element.id}" class="cmarMap"></div>
	
	<img id="map-${mapId}-picture-${element.id}" class="map-pic" src="" />
	
	<br />
	<i class="credits">Data provided by CSIRO Marine and Atmospheric Research on <fmt:formatDate value="${firstDataRow.creationDate}" pattern="dd MMM yyyy" /> was the best available to date.</i>
</c:if>



	