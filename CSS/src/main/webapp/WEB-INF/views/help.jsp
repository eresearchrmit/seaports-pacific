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
	
	<%--<sec:authorize access="isAuthenticated()">
		<p><a href="#">Link to the Example Report matching the video ></a></p>
	</sec:authorize>--%>
	
	<br />
	<h2>Resources</h2>
	<br />
	<p>Climate data can be sourced from CSIRO and the Bureau of Meteorology via the <a href="http://www.pacificclimatechangescience.org/publications/reports/" target="_blank">Pacific-Australia Climate Change Science and Adaptation Planning (PACCSAP) Program</a>.</p>
	
	<p>Country specific climate data can be found at <a href="http://www.pacificclimatechangescience.org/publications/reports/" target="_blank">Pacific Climate Change Science Reports</a>.</p>
	
	<p>CSIRO and BoM <a href="http://pacificclimatefutures.net" target="_blank">Pacific Climate Futures website.</a>.</p>

	<p>BoM <a href="http://www.bom.gov.au/cosppac/" target="_blank">Climate & Ocean Support Program in the Pacific</a>.</p>

	<p>CSIRO <a href="http://www.cmar.csiro.au/sealevel/sl_hist_few_hundred.html" target="_blank">Marine and Atmospheric Research website</a>.</p>


	<p>UKCIP provides an <a href="http://www.ukcip.org.uk/wizard/" target="_blank">online adaptation Tool (Wizard)</a>.</p>

	<p><a href="http://www.ifc.org/wps/wcm/connect/topics_ext_content/ifc_external_corporate_site/ifc+sustainability/publications/publications_Report_climateriskandbusiness-ports__wci__1319578898769" target="_blank">Climate Risk and Business Ports: Terminal Maritimo Muelles le Boaque Cartagena, Columbia</a> (IFC World Bank Group) is a climate risk assessment available online that provides one of the first comprehensive risk assessments of a large sea port.</p>

	<p>UK Port adaptation plans: Infrastructure providers were asked to submit adaptation plans in response to the UK Climate Change Act 2008. This information is still online in a <a href="http://webarchive.nationalarchives.gov.uk/20130123162956/http:/www.defra.gov.uk/environment/climate/sectors/reporting-authorities/reporting-authorities-Reports/" target="_blank">DEFRA Archive</a>.</p>

	<p>Engineering Australia has published a set of guidelines for responding to climate change under Australian conditions. Resources can be downloaded from the <a href="https://www.engineersaustralia.org.au/coastal-ocean-engineering/publications" target="_blank">Engineers Australia website</a>.</p>

	<p>NCCARF Guidelines for <a href="http://www.nccarf.edu.au/publications/enhancing-resilience-seaports-climate-change-adaptation-guidelines" target="_blank">Adapting Seaports to a Changing Climate</a>. These guideline were produced as part of a research project, focused on Australian seaports. The full suite of reports from this project can be downloaded from the <a href="http://www.nccarf.edu.au/publications/enhancing-resilience-seaports-synthesis-and-implications" target="_blank">NCCARF site</a>.</p>
</div>