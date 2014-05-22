<%--
 Copyright (c) 2014, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="grid_12">
	<center>
		<h3>Introduction</h3>
	
		<div id="successMessage" class="message success">
			<h5>Congratulations, your account has been created !</h5>
		</div>
		
		<p class="info-paragraph">The impact of climate change on ports will differ according to the location, function and business model of the ports. Climate Smart ports want to understand the relevant climate impacts and risks for their particular operation; only then can they determine what adaptation measures may be appropriate.</p> 
		
		<p class="info-paragraph">The Climate Smart Seaports Tool is designed primarily for port personnel who make (or influence) decisions around long-term port planning for infrastructure, assets and management systems. However, it will also be of value to port owners and related businesses, government departments, local authorities concerned with ports and infrastructure, and researchers.</p>
		
		<p class="info-paragraph">The Tool facilitates the collection of information so ports are able to identify their current and future climate risks. It is designed to generate material for the scope and context setting stages of the <a href="http://www.iso.org/iso/home/standards/iso31000.htm" target="_blank" title="ISO 31000">ISO 31000 risk management standard</a>, and it is also compatible with other processes such as <a href="http://www.iso.org/iso/home/standards/management-standards/iso14000.htm" target="_blank" title="ISO 14001">ISO 14001</a> and the initial stages of Engineers Australia's <a href="http://www.engineersaustralia.org.au/sites/default/files/shado/Learned Groups/National Committees and Panels/Coastal and Ocean Engineering/vol_1_web.pdf" target="_blank" title="View Guilelines">Guidelines for Responding to the Effects of Climate Change in Coastal and Ocean Engineering</a>.<br />
		Data available includes: current and historical climate trends, future climate data and non-climate context.</p>
		
		<p class="info-paragraph"><b>Climate Smart Seaports suggested workflow:</b></p>	
		
		<img src="<c:url value="/resources/img/help/user_workflow.png" />" alt="Suggested workflow of the Climate Smart Seaports tool" width="750" />
		
		<p class="info-paragraph">The tool is agile and it is possible to move iteratively between steps 2, 3, and the Preview function of step 4.</p>
		
		<p class="info-paragraph">It features aids (applications) to investigate (i) port response to specific extreme weather events in the past, (ii) an assessment of port vulnerability to all extreme weather events with a priority rating in the form of a table, and (iii) a priority rating for future climate impacts in the form of a likelihood and consequence table.</p>
		
		<p class="info-paragraph">Associated with the tool are: (i) a list of resources, located under the 'help' button and (ii) an engineering model of concrete deterioration under different emissions scenarios that can be accessed from the 'help' page.</p>
		
		<p class="info-paragraph">The "publish" function in the tool stores the final report on the Climate Smart Seaports site, to provide a growing repository of information and knowledge about climate change adaptation for peer-to-peer learning.</p>
		
		<h4>Click on "Get started" to log in and start.</h4>
	</center>
	<br />
	<div align="center">
		<a href="/auth/workboard/my-workboard">
			<button id="btnContinueToWorkboard" class="btn btn-icon btn-blue btn-arrow-right" >
				<span></span>Get started
			</button>
		</a>
	</div>
</div>