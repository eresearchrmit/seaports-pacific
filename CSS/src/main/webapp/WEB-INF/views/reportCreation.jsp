<%--
 Copyright (c) 2013, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://code.google.com/p/climate-smart-seaports/
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" import="edu.rmit.eres.seaports.model.User" %>

<div class="grid_12">
	<h2>New Report</h2>
	
	<c:set var="successMessage" scope="request" value="${successMessage}"/>
	<c:set var="warningMessage" scope="request" value="${warningMessage}"/>
	<c:set var="errorMessage" scope="request" value="${errorMessage}"/>
	<jsp:include page="notifications.jsp" />
	
	<!-- <p class="hint"><i>You have no active workboard. Create a new report using the page below, or read the <a href="/public/guidelines#introduction" target="_blank">Climate Smart Seaports User Guide</a> for more information on how to use this tool.</i></p> -->
	
	<%-- 
		ANDS GEOLOCATION WIDGET
		http://researchdata.ands.org.au/developers/documentation/widgets/location_widget
		
		<script type="text/javascript" src="//maps.google.com/maps/api/js?sensor=false&libraries=drawing&v=3"></script>
		<script src='//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.js'></script>
		<script type="text/javascript" src="//researchdata.ands.org.au/apps/assets/location_capture_widget/js/location_capture_widget.js"></script>
		<link rel="stylesheet" type="text/css" href="//researchdata.ands.org.au/apps/assets/location_capture_widget/css/location_capture_widget.css" />
		
		<div id="mapContainer"></div>
		
		<script type="text/javascript">
		  $(document).ready(function() {
			$("#mapContainer").ands_location_widget({target:'coordinates'});
		  });
		</script>
	 --%>
	
	<form:form method="POST" action="/auth/report/create"  modelAttribute="report" >
	
	<table class="form" width="100%">
		<tr>
			<td>
				<label style="font-size:13px">Title:</label>
			</td>
			<td class="col2">
				<form:input id="txtReportTitle" path="name" style="width:300px" onblur="checkTitle()" />
			</td>
			<td class="top" style="min-width:300px">
				<span id="titleErrorMessage" style="color:red;"></span>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<label style="font-size:13px">Region Selection <a href="#" class="helpTooltip" title="Hover over a Natural Resource Management region and click to select the region you want. <a href=&quot;/public/guidelines#css-workboard&quot; target=&quot;_blank&quot;>Read more...</a>"><img src="<c:url value="/resources/img/icons/help.png" />" alt="Help" /></a>:</label>
			</td>
			<td class="col2" valign="top">
				<div id="displayReportRegion" style="display:inline; font-size:13px" ><span class="hint"><i>Select a region using the map then select a seaport within it.</i></span></div>
				<span id="regionErrorMessage" style="color:red;"></span>
				<form:hidden id="hdnReportRegion" path="seaport.region.name" />
				<br />
			</td>
			<td></td>
		</tr>
		<tr>		
			<td colspan="3" style="vertical-align:center">
				<div id="map-container" style="">
				    <img class="south-pacific-map" src="<c:url value="/resources/img/data/south-pacific-regions-map.png" />" width="1017" height="496" 
				    usemap="#south-pacific-regions" data-maphilight='{"strokeColor":"000000"}' />
					<!-- Map has been generated using www.image-maps.com -->
					<map name="south-pacific-regions">	
						<area class="mapArea" shape="poly" title="Fiji" data-maphilight='{"fillColor":"9900FF"}' coords="571,297, 593,300, 637,351, 647,357, 659,374, 652,408, 631,442, 617,438, 603,427, 591,443, 566,449, 550,400, 558,386, 558,362, 567,348, 558,328, 563,305" />
						<area class="mapArea" shape="poly" title="Papua New Guinea" data-maphilight='{"fillColor":"00FF00"}' coords="231,188, 243,181, 275,173, 301,183, 323,178, 337,183, 349,202, 361,208, 399,214, 425,212, 443,221 ,448,232, 450,240, 406,248, 373,270, 368,280, 398,322, 393,339, 365,347, 341,345, 286,322, 270,296, 262,294, 242,293, 223,309, 214,307, 233,290, 232,226, 237,209" />
					</map>
				</div>
				<a href="#" class="helpTooltip" title="<p>The Flanders Marine Institute manages Marine Regions, but is aware that it is not complete and undoubtedly contains errors. The Flanders Marine Institute cannot be made responsible for any errors or misuse of data contained in this register. Comments from our users are more than welcome, so if you come across any error or incomplete information or you are willing to contribute to this initiative please contact us.</p><p>The data is provided 'as is', and no warranty express, implied or otherwise is offered as to the data's accuracy. The developers do not imply any opinion concerning the legal status of any country, territory or area, or concerning the delimitation of its frontiers or boundaries. The data can be used for educational, scientific or research purposes but should not be used for legal, commercial/economical (exploration of natural resources) or navigational purposes.</p><p>Anyone can download this data but it is for the sole use of the organisation or individual downloading the data. The geodata may not be redistributed without the permission of the Flanders Marine Institute (VLIZ). The geodata may be used in a Value-Added Software Application (like webservices), on condition that the Flanders Marine Institute is acknowledged as the source of the data. Redistribution rights are granted for hard-copy renditions or static, electronic map images (e.g. jpeg, gif, etc.) that are plotted, printed or publicly displayed with reference to the Flanders Marine Institute. For redistribution rights of derived products, please contact us. You can contact us at <a href='mailto:info@marineregions.org'>info@marineregions.org</a></p><p>The database can be cited as follows: Claus S., De Hauwere N., Vanhoorne B., Hernandez F., Mees J. (Flanders Marine Institute) (2014). Marineregions.org. Accessed at <a href='http://www.marineregions.org'>http://www.marineregions.org</a> on 2014-02-24.</p><p>The map has been adjusted by the CSIRO to better fit the needs of the The Pacific Climate Change Science Program Climate Futures <a href='http://www.pacificclimatefutures.net/main/'>http://www.pacificclimatefutures.net/main/</a>">
					&copy; Flanders Marine Institute and CSIRO
				</a>
			</td>
		</tr>
		<tr>
			<td class="top">
				<label style="font-size:13px">Seaport <a href="#" class="helpTooltip" title="Select your port. <a href=&quot;/public/guidelines#css-workboard&quot; target=&quot;_blank&quot;>Read more...</a>"><img src="<c:url value="/resources/img/icons/help.png" />" alt="Help" /></a>:</label>
			</td>
			<td class="col2">
				<select id="cbbSeaport" disabled style="width:300px">
					<option value="none">Please first select a region on the map.</option>
				</select>
				<form:hidden id="hdnSeaportCode" path="seaport.code" value="none" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="top">
				<label style="font-size:13px">Purpose of inquiry <a href="#" class="helpTooltip" title="Tell us why you are using this tool. For example, climate risk assessment for work; study (indicate what field) or research (indicate your area of interest / topic). <a href=&quot;/public/guidelines#css-workboard&quot; target=&quot;_blank&quot;>Read more...</a>" ><img src="<c:url value="/resources/img/icons/help.png" />" alt="Help" /></a>:</label>
			</td>
			<td class="col2" valign="top">
				<form:textarea id ="txtReportPurpose" path="purpose" rows="5" cols="30" style="width:300px" onblur="checkPurpose()" value="Test" />
			</td>
			<td class="top" style="min-width:300px">
				<span id="purposeErrorMessage" style="color:red;"></span>
			</td>
		</tr>
	</table>
	
	<div align="center">
	<button id="btnCreateReport" type="submit" class="btn btn-icon btn-blue btn-arrow-right" >
		<span></span>Create
	</button>
	</div>
	</form:form>
	
	<script type="text/javascript">
	$(function() {
		// Map highlight
		$('.south-pacific-map').maphilight();
		
		var dataArr = [
		<c:forEach items="${allSeaports}" var="seaport" varStatus="seaportsLoop">
			{'region':'${seaport.region.name}', 'seaportCode':'${seaport.code}', 'seaportName':'${seaport.name}'}<c:if test="${!seaportsLoop.last}">,</c:if>
		</c:forEach>
		];
		
		// Click on map event
		$(".mapArea").click(function(event) {
			var selectedValue = $(this).attr("title");
			$("#hdnReportRegion").attr("value", selectedValue);
			$("#displayReportRegion").html(selectedValue);
			checkRegion();
			
			// Only append seaports from the selected region
			$('#cbbSeaport option').remove();
			$.each(dataArr, function(i){
			    if (dataArr[i]['region'] == selectedValue){
			        $('#cbbSeaport').append($("<option></option>")
			        		.attr("value",dataArr[i]['seaportCode'])
			        		.text(dataArr[i]['seaportName']));
			    }
			    $("#hdnSeaportCode").val($("#cbbSeaport").val());
			});
			$('#cbbSeaport').removeAttr('disabled');
		});
		
		// Seaport selection change event
		$("#cbbSeaport").change(function (e) {
			$("#hdnSeaportCode").val($("#cbbSeaport").val());
		});
		
		// Field verifications
		$("#titleErrorMessage").hide();
		$("#regionErrorMessage").hide();
		$("#purposeErrorMessage").hide();
		$("#btnCreateReport").click(function (e) {
			if (checkTitle() == false || checkRegion() == false || checkPurpose() == false)
				e.preventDefault();
		});
		
		// Help tooltips activation
		setupTooltips();
	});
	
	function checkTitle() {
		if ($("#txtReportTitle").val().length > 0) {
			$("#txtReportTitle").removeClass("error");
			$("#titleErrorMessage").hide();
			return true;
		}
		else {
			$("#titleErrorMessage").html("Please provide a title for your report.");
		}
		$("#txtReportTitle").addClass("error");
		$("#titleErrorMessage").show();
		return false;
	}
	
	function checkRegion() {
		if ($("#hdnReportRegion").val().length > 0) {
			$("#hdnReportRegion").removeClass("error");
			$("#regionErrorMessage").hide();
			return true;
		}
		else {
			$("#regionErrorMessage").html("Please select a region.");
		}
		$("#hdnReportRegion").addClass("error");
		$("#regionErrorMessage").show();
		return false;
	}
	
	function checkPurpose() {
		if ($("#txtReportPurpose").val().length > 0) {
			$("#txtReportPurpose").removeClass("error");
			$("#purposeErrorMessage").hide();
			return true;
		}
		else {
			$("#purposeErrorMessage").html("Please describe the purpose of your work.");
		}
		$("#txtReportPurpose").addClass("error");
		$("#purposeErrorMessage").show();
		return false;
	}
</script>
</div>