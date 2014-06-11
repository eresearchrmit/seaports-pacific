<%--
 Copyright (c) 2014, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="grid_12">
	<span id="top"></span>
	
	<h2>Help</h2>
	
	<br />
	<p>Make sure you download the <b>complete guidelines document</b> for detailed help about the tool.</p>
	
	<a href="<c:url value="/resources/docs/climate-smart-seaports-guidelines.pdf" />" title="Download these guidelines as a PDF document" target="_blank">
		<button class="btn-icon btn-blue btn-arrow-down" >
			<span></span>Download Complete Guidelines PDF (3.8MB)
		</button>
	</a>
	
	<%--<p>TO BE REPLACED BY VIDEO TUTORIAL</p>
	<iframe width="560" height="315" src="//www.youtube.com/embed/SbKVhbRupus" frameborder="0" allowfullscreen></iframe><br />
	<br /> --%>
	
	<sec:authorize access="isAuthenticated()">
		<p><a href="#">Link to the Example Report matching the video ></a></p>
	</sec:authorize>
	
</div>