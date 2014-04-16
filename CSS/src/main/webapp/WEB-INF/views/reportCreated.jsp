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

<div class="grid_12">
	<center>
		<div id="successMessage" class="message success">
			<h5>Your new report has been created !</h5>
		</div>
		
		<h3>How to edit your report</h3>
		
		<p class="info-paragraph">The Report edition is where data can be collected and reviewed.</p>  

		<p class="info-paragraph">It has a number of tabs for users to progress through, presenting different data sets: Observed Climate & Marine; Future Climate & Marine; Non-climate context; Applications. Users add relevant data from each tab, and then review their collected data in the "Summary" tab.</p> 
		
		<%--<img src="<c:url value="/resources/img/help/workboard-instructions.png" />" style="margin: 50px auto; opacity: 0.7" />--%>
		
		<h4>Click on "Continue" when you are ready to start.</h4>
	</center>
	<br />
	<div align="center">
		<a href="/auth/report?id=${report.id}">
			<button id="btnContinueToReport" class="btn btn-icon btn-blue btn-arrow-right" >
				<span></span>Continue
			</button>
		</a>
	</div>
	
</div>