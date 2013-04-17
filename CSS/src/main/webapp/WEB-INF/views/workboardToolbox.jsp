<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" import="war.model.UserStory" %>
<%@ page language="java" import="war.model.User" %>
<%@ page language="java" import="war.model.DataElement" %>

<c:if test="${not empty userstory}">
<p>
	<button id="btnOpenAdd${dataelementsfilter}DataElementModalWindow" type="button" class="btn btn-icon btn-blue btn-plus" >
		<span></span>Add Data Element
	</button>
</p>
<div id="add${dataelementsfilter}DataElementModalWindow" class="box round first" title="New Data Element" style="display:none">
	<div class="block">
		<p><strong>1. Data Source:</strong></p>
		<table width="auto" height="auto" class="form">
			<tr>
				<td class="col1">Choose a data source:</td>
				<td class="col2">
					<select id="cbb${dataelementsfilter}DataSource" name="dataSource">
						<option value="none">- Select Data Source -</option>
						<c:if test="${dataelementsfilter == 'NonClimate'}"><option value="abs">ABS data</option></c:if>
						<c:if test="${dataelementsfilter == 'NonClimate'}"><option value="bitre">BITRE data</option></c:if>
						<c:if test="${dataelementsfilter == 'NonClimate'}"><option value="customFile">Custom file</option></c:if>
						<c:if test="${dataelementsfilter == 'ObservedClimate'}"><option value="past">Past data</option></c:if>
						<c:if test="${dataelementsfilter == 'ObservedClimate'}"><option value="acornSat">ACORN-SAT data</option></c:if>
						<c:if test="${dataelementsfilter == 'Future'}"><option value="csiro">CSIRO</option></c:if>
						<c:if test="${dataelementsfilter == 'Future'}"><option value="cmar">CMAR</option></c:if>
						<c:if test="${dataelementsfilter == 'Applications'}"><option value="engineering">Engineering Model</option></c:if>
						<c:if test="${dataelementsfilter == 'Applications'}"><option value="vulnerability">Vulnerability Assessment</option></c:if>
					</select>
				</td>
			</tr>
		</table>
		<p>
		
		<c:if test="${dataelementsfilter == 'NonClimate'}">
		<div id="absDataForm" class="dataElementForm">
			<form:form method="post" action="/CSS/auth/workboard/addAbsData#tabs-non-climate-context">
				<input type="hidden" name="userstoryid" value="${userstory.id}" />
				<p><strong>2. ABS Data Element Options:</strong></p>
				<table width="auto" height="auto" class="form">
					<tr>
						<td>Variable:</td>
						<td class="col2">
							<select id="cbbAbsDataVariable" name="variable">
								<option value="1">Population change</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Seaport:</td>
						<td class="col2">
							<c:if test="${not empty regionSeaports}">
							<select id="cbbAbsDataSeaport" name="seaport">
								<c:forEach items="${regionSeaports}" var="regionSeaport" varStatus="regionSeaportLoopStatus">
									<option value="${regionSeaport.code}" <c:if test="${empty regionSeaport.urbanCenter}">disabled</c:if>>
										<c:choose>
											<c:when test="${not empty regionSeaport.urbanCenter}">
												${regionSeaport.name} (${regionSeaport.urbanCenter})
											</c:when>
											<c:otherwise>
												${regionSeaport.name} (No Data Available)
											</c:otherwise>
										</c:choose>
									</option>
								</c:forEach>
							</select>
							</c:if>
						</td>
					</tr>
				</table>
				<p><strong>3. Display Options:</strong></p>
				<table width="auto" height="auto" class="form">
					<tr>
						<td class="col1">Display data as:</td>
						<td class="col2">
							<input type="radio" name="displayType" value="graph" checked="checked" /> Graph 
							<input type="radio" name="displayType" value="picture" /> Map
							<input type="radio" name="displayType" value="table" /> Table
						</td>
					</tr>
				</table>
				<button type="button" class="btn btn-icon btn-blue btn-plus" onclick="submit();" >
					<span></span>Add ABS Data
				</button>
			</form:form>
		</div>
		</c:if>
		
				
		<c:if test="${dataelementsfilter == 'NonClimate'}">
		<div id="bitreDataForm" class="dataElementForm">
			<form:form method="post" action="/CSS/auth/workboard/addBitreData#tabs-non-climate-context">
				<input type="hidden" name="userstoryid" value="${userstory.id}" />
				<p><strong>2. BITRE Data Element Options:</strong></p>
				<table width="auto" height="auto" class="form">
					<tr>
						<td>Variable type:</td>
						<td class="col2">
							<select id="cbbBitreDataVariable" name="variableCategory">
								<option value="1">Freight throughput by cargo type</option>
								<option value="2">Commercial vessels calls by type</option>
								<option value="3">Export commodities by type</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Seaport:</td>
						<td class="col2">
							<c:if test="${not empty regionSeaports}">
							<select id="cbbbitreDataSeaport" name="seaport">
								<c:forEach items="${regionSeaports}" var="regionSeaport" varStatus="regionSeaportLoopStatus">
									<option value="${regionSeaport.code}">${regionSeaport.name}</option>
								</c:forEach>
							</select>
							</c:if>
						</td>
					</tr>
				</table>
				<p><strong>3. Display Options:</strong></p>
				<table width="auto" height="auto" class="form">
					<tr>
						<td class="col1">Display data as:</td>
						<td class="col2">
							<input type="radio" name="displayType" value="graph" checked="checked" /> Graph
							<input type="radio" name="displayType" value="table" /> Table
						</td>
					</tr>
				</table>
				<button type="button" class="btn btn-icon btn-blue btn-plus" onclick="submit();" >
					<span></span>Add BITRE Data
				</button>
			</form:form>
		</div>
		</c:if>
		
		<c:if test="${dataelementsfilter == 'NonClimate'}">
		<div id="customFileDataForm" class="dataElementForm">
			<form:form method="post" action="/CSS/auth/workboard/upload?id=${userstory.id}#tabs-non-climate-context" enctype="multipart/form-data">
				<p><strong>2. Custom Data Element Options:</strong></p>
				<table width="auto" height="auto" class="form">
					<tr>
						<td>Select a file to upload <a href="#" id="lnkHelpFileUpload" ><img src="<c:url value="/resources/img/icons/help.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<input type="file" name="file" id="file" />
						</td>
					</tr>
				</table>
				<button type="button" class="btn btn-icon btn-blue btn-plus" onclick="submit();" >
					<span></span>Add Custom Data
				</button>
			</form:form>
		</div>
		</c:if>
		
		<c:if test="${dataelementsfilter == 'ObservedClimate'}">
		<div id="pastDataForm" class="dataElementForm">
			<form:form method="post" action="/CSS/auth/workboard/addPastData#tabs-observed-climate">
			<input type="hidden" name="userstoryid" value="${userstory.id}" />
			<p><strong>2. Past Data Options:</strong></p>
			<table width="auto" height="auto" class="form">
				<tr>
					<td>Variable:</td>
					<td class="col2">
						<select id="cbbPastDataId" name="pastDataId">
							<option value="1">Trend in mean temperatures</option>
							<option value="2">Trend in maximum temperatures</option>
							<option value="3">Trend in total annual rainfall</option>
							<option value="4">Long-term sea level rise measurements</option>
							<option value="5">Shorter-term changes in sea level</option>
						</select>
					</td>
				</tr>
			</table>
			<button type="button" class="btn btn-icon btn-blue btn-plus" onclick="submit();" >
				<span></span>Add Past Data
			</button>
			</form:form>
		</div>
		</c:if>
		
		<c:if test="${dataelementsfilter == 'ObservedClimate'}">
		<div id="acornSatDataForm" class="dataElementForm">
			<form:form method="post" action="/CSS/auth/workboard/addAcornSatData#tabs-observed-climate">
			<input type="hidden" name="userstoryid" value="${userstory.id}" />
			<p><strong>2. ACORN-SAT Data Options:</strong></p>
			<table width="auto" height="auto" class="form">
				<tr>
					<td>Type of measurements:</td>
					<td class="col2">
						<select id="cbbAcornSatDataExtreme" name="acornSatExtremeData">
							<option value="mean">Mean measurements</option>
							<option value="extreme">Extreme measurements</option>
						</select>
					</td>
				</tr>
			</table>
			<button type="button" class="btn btn-icon btn-blue btn-plus" onclick="submit();" >
				<span></span>Add ACORN-SAT Data Element
			</button>
			</form:form>
		</div>
		</c:if>
		
		<c:if test="${dataelementsfilter == 'Future'}">
		<div id="csiroDataForm" class="dataElementForm">
			<form:form method="post" action="/CSS/auth/workboard/addCsiroData#tabs-future-climate" modelAttribute="climateData">
				<input type="hidden" name="userstoryid" value="${userstory.id}" />
				<p><strong>2. CSIRO Data Options:</strong></p>
				<table width="auto" height="auto" class="form">
					<tr>
						<td class="col1">Variable <a href="#" id="lnkHelpCsiroVariable" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<select id="cbbClimateVariable" name="climateVariable">
								<option value="All">All Variables</option>
								<option value="Temperature">Temperature</option>
								<option value="Wind speed">Wind speed</option>
								<option value="Rainfall">Rainfall</option>
								<option value="Relative humidity">Relative humidity</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="col1">Emission Scenario <a href="#" id="lnkHelpCsiroEmissionScenario" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<select id="cbbClimateEmissionScenario" name="climateEmissionScenario">
								<option value="A1B">Medium CO2 emissions (A1B)</option>
								<option value="A1FI">High CO2 emissions (A1FI)</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="col1">Climate Model <a href="#" id="lnkHelpCsiroClimateModel" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<select id="cbbClimateClimateModel" name="climateModel">
								<option value="Most Likely">Most Likely</option>
								<option value="Hotter &amp; Drier">Hotter &amp; Drier</option>
								<option value="Cooler &amp; Wetter">Cooler &amp; Wetter</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="col1">Time Period <a href="#" class="lnkHelpCmarTimePeriod" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<select id="cbbYear" name="year">
								<option value="2030">2030</option>
								<option value="2055">2055</option>
								<option value="2070">2070</option>
							</select>
						</td>
					</tr>
				</table>
				<p><strong>3. Display Options:</strong></p>
				<table width="auto" height="auto" class="form">
					<tr>
						<td class="col1">Include map image:</td>
						<td class="col2">
							<input type="checkbox" id="cbIncludePictures" name="includePictures" /> <i>Check this if you want to include pictures of the region map corresponding to the data</i>  
						</td>
					</tr>
				</table>
				<button type="button" class="btn btn-icon btn-blue btn-plus" onclick="submit();" >
					<span></span>Add CSIRO Data
				</button>
			</form:form>
		</div>
		</c:if>
		
		<c:if test="${dataelementsfilter == 'Future'}">
		<div id="cmarDataForm" class="dataElementForm">
			<form:form method="post" action="/CSS/auth/workboard/addCmarData#tabs-future-climate" modelAttribute="climateData">
				<input type="hidden" name="userstoryid" value="${userstory.id}" />
				<p><strong>2. CMAR Data Options:</strong></p>
				<table width="auto" height="auto" class="form">
					<tr>
						<td class="col1">Variable <a href="#" id="lnkHelpCmarVariable" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<select id="cbbClimateVariable" name="climateVariable" disabled="disabled">
								<option value="Sea Level Rise">Sea Level Rise</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="col1">Emission Scenario <a href="#" id="lnkHelpCmarEmissionScenario" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<select id="cbbClimateEmissionScenario" name="climateEmissionScenario" disabled="disabled">
								<option value="A1B">Medium CO2 emissions (A1B)</option>
								<!-- <option value="A1FI">High CO2 emissions (A1FI)</option> -->
							</select>
						</td>
					</tr>
					<tr>
						<td class="col1">Climate Model <a href="#" id="lnkHelpCmarClimateModel" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<select id="cbbClimateClimateModel" name="climateModel" disabled="disabled">
								<option value="Most Likely">Most Likely</option>
								<!-- <option value="Hotter &amp; Drier">Hotter &amp; Drier</option> -->
								<!-- <option value="Cooler &amp; Wetter">Cooler &amp; Wetter</option> -->
							</select>
						</td>
					</tr>
					<tr>
						<td class="col1">Time Period <a href="#" id="lnkHelpCsiroTimePeriod" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<select id="cbbYear" name="year">
								<option value="2030">2030</option>
								<!-- <option value="2055">2055</option> -->
								<option value="2070">2070</option>
							</select>
						</td>
					</tr>
				</table>
				<p><strong>3. Display Options:</strong></p>
				<table width="auto" height="auto" class="form">
					<tr>
						<td class="col1">Include map image:</td>
						<td class="col2">
							<input type="checkbox" id="cbIncludePictures" name="includePictures" /> <i>Check this if you want to include pictures of the region map corresponding to the data</i>  
						</td>
					</tr>
				</table>
				<button type="button" class="btn btn-icon btn-blue btn-plus" onclick="submit();" >
					<span></span>Add CMAR Data
				</button>
			</form:form>
		</div>
		</c:if>
		
		<c:if test="${dataelementsfilter == 'Applications'}">
		<div id="engineeringDataForm" class="dataElementForm">
			<form:form id="formEngineeringData" method="post" action="/CSS/auth/workboard/addEngineeringData?id=${userstory.id}#tabs-applications" enctype="multipart/form-data">
				<p><strong>2. Engineering Model Data Element Options:</strong></p>
				
				<input type="hidden" name="sourceType" id="hdnEngineeringSourceType" value="upload" />
				
				<table width="auto" height="auto" class="form">
					<tr>
						<td><input type="radio" name="rdEngineeringSourceType" value="example" /> Use a predefined example for this region</td>
					</tr>
					<tr>
						<td><input type="radio" name="rdEngineeringSourceType" value="upload" checked="checked" /> Or select an engineering model file to upload <a href="#" id="lnkHelpEngUpload" ><img src="<c:url value="/resources/img/icons/help.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<input type="file" name="file" id="file" />
						</td>
					</tr>
					<tr>
						<td style="padding-top:50px">Select a variable to use for the Data Element <a href="#" id="lnkHelpEngVariable" ><img src="<c:url value="/resources/img/icons/help.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<input type="hidden" id="hdnEngVariableCategory" name="engVariableCategory" value="${chlorideEngineeringModelVariables[0].category}" />
							<select id="cbbEngineeringVariable" name="engVariable">
								<option disabled class="dropDownTitle">${chlorideEngineeringModelVariables[0].category}</option>
								<c:forEach items="${chlorideEngineeringModelVariables}" var="engineeringModelVariable" varStatus="loop">
									<option value="${engineeringModelVariable.name}" title="${engineeringModelVariable.category}">${engineeringModelVariable.name}</option>
								</c:forEach>
								<option disabled class="dropDownTitle">${carbonationEngineeringModelVariables[0].category}</option>
								<c:forEach items="${carbonationEngineeringModelVariables}" var="engineeringModelVariable" varStatus="loop">
									<option value="${engineeringModelVariable.name}" title="${engineeringModelVariable.category}">${engineeringModelVariable.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<p><strong>3. Display Options:</strong></p>
				<table width="auto" height="auto" class="form">
					<tr>
						<td class="col1">Display data as:</td>
						<td class="col2">
							<input type="radio" name="displayType" value="graph" checked="checked" /> Graph
							<input type="radio" name="displayType" value="table" /> Table
						</td>
					</tr>
				</table>
				<button type="button" id="btnAddEngineeringModelDataElement" class="btn btn-icon btn-blue btn-plus" onclick="submit();" >
					<span></span>Add Engineering Model Data
				</button><br />
				<div id="loading" class="center">
					<p>Extracting data and preparing your data element...</p>
					<img src="<c:url value="/resources/img/ajax-loader.gif" />" alt="Loading..." title="Loading..." /><br />
					<p>Please don't refresh the page.</p>
				</div>
			</form:form>
		</div>
		</c:if>

		<c:if test="${dataelementsfilter == 'Applications'}">
		<div id="vulnerabilityDataForm" class="dataElementForm">
			<form:form id="formVulnerabilityData" method="post" action="/CSS/auth/workboard/addVulnerability#tabs-applications" enctype="multipart/form-data">
				<input type="hidden" name="userstoryid" value="${userstory.id}" />
				
				<p><strong>2. Current Vulnerability Assessment Options:</strong></p>
				
				<p style="width:800px; text-align:justify; font-size:8pt;color:#3f7227"><i>This section identifies your current vulnerability to particular climate related events. When considering the questions below, think of the impact on Port assets (machinery, buildings, equipment), infrastructure (drainage, rail, road, berths), and people (injuries, work disruptions). Please complete this page for each different climate related event that has impacted the port in recent years.</i></p>
				
				<table width="auto" height="auto" class="form">
					<tr>
						<td class="top">Weather event <a href="#" id="lnkHelpVulnerabilityWeatherEvent" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<select id="cbbWeatherEventType" name="weatherEventType">
								<option value="Heatwave">Heatwave</option>
								<option value="Strong wind">Strong wind</option>
								<option value="Heavy rain">Heavy rain</option>
								<option value="Electrical storm">Electrical storm</option>
								<option value="Storm (wind and rain combined)">Storm (wind and rain combined)</option>
								<option value="Cyclone">Cyclone</option>
								<option value="Hail">Hail</option>
								<option value="Storm surge">Storm surge</option>
								<option value="Sea level rise">Sea level rise</option>
								<option value="Fog">Fog</option>
								<option value="Drought">Drought</option>
								<option value="Flood">Flood</option>
								<option value="Other">Other</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="top">Date<a href="#" id="lnkHelpVulnerabilityDate" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<select id="cbbWeatherEventYear" name="weatherEventYear">					
								<c:forEach var="i" begin="2003" end="2013" step="1" varStatus ="status">
									<option value="<c:out value="${i}" />"><c:out value="${i}" /></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="top">Direct or indirect impact <a href="#" id="lnkHelpVulnerabilityDirect" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<input type="radio" name="weatherEventDirect" value="direct" checked="checked" /> Direct
							<input type="radio" name="weatherEventDirect" value="indirect" /> Indirect
						</td>
					</tr>
					<tr>
						<td class="top">Impact <a href="#" id="lnkHelpVulnerabilityImpact" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<textarea id="txtWeatherEventImpact" name="weatherEventImpact" rows="5" class="small"></textarea>
						</td>
					</tr>
					<tr><td colspan="2">
						<p>Identify and rate the business consequences of these impacts. Please complete the whole table <a href="#" id="lnkHelpVulnerabilityConsequences" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</p>
					</td></tr>
					<tr>
						<td class="top">Lost time due to staff or contractor injuries<a href="#" id="lnkHelpVulnerabilityConsequence1" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<input type="radio" name="weatherEventConsequence1" value="0" checked="checked" /> No impact
							<input type="radio" name="weatherEventConsequence1" value="1" /> Insignificant
							<input type="radio" name="weatherEventConsequence1" value="2" /> Moderate
							<input type="radio" name="weatherEventConsequence1" value="3" /> Major
							<input type="radio" name="weatherEventConsequence1" value="4" /> Extreme
						</td>
					</tr>
					<tr>
						<td class="top">Staff not able to attend work<a href="#" id="lnkHelpVulnerabilityConsequence2" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<input type="radio" name="weatherEventConsequence2" value="0" checked="checked" /> No impact
							<input type="radio" name="weatherEventConsequence2" value="1" /> Insignificant
							<input type="radio" name="weatherEventConsequence2" value="2" /> Moderate
							<input type="radio" name="weatherEventConsequence2" value="3" /> Major
							<input type="radio" name="weatherEventConsequence2" value="4" /> Extreme
						</td>
					</tr>
					<tr>
						<td class="top">Increased annual Workcover costs:</td>
						<td class="col2">
							<input type="radio" name="weatherEventConsequence3" value="0" checked="checked" /> No impact
							<input type="radio" name="weatherEventConsequence3" value="1" /> Insignificant
							<input type="radio" name="weatherEventConsequence3" value="2" /> Moderate
							<input type="radio" name="weatherEventConsequence3" value="3" /> Major
							<input type="radio" name="weatherEventConsequence3" value="4" /> Extreme
						</td>
					</tr>
					<tr>
						<td class="top">Increased maintenance costs:</td>
						<td class="col2">
							<input type="radio" name="weatherEventConsequence4" value="0" checked="checked" /> No impact
							<input type="radio" name="weatherEventConsequence4" value="1" /> Insignificant
							<input type="radio" name="weatherEventConsequence4" value="2" /> Moderate
							<input type="radio" name="weatherEventConsequence4" value="3" /> Major
							<input type="radio" name="weatherEventConsequence4" value="4" /> Extreme
						</td>
					</tr>
					<tr>
						<td class="top">Deferment of capital expenditure:</td>
						<td class="col2">
							<input type="radio" name="weatherEventConsequence5" value="0" checked="checked" /> No impact
							<input type="radio" name="weatherEventConsequence5" value="1" /> Insignificant
							<input type="radio" name="weatherEventConsequence5" value="2" /> Moderate
							<input type="radio" name="weatherEventConsequence5" value="3" /> Major
							<input type="radio" name="weatherEventConsequence5" value="4" /> Extreme
						</td>
					</tr>
					<tr>
						<td class="top">Increased insurance costs<a href="#" id="lnkHelpVulnerabilityConsequence6" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<input type="radio" name="weatherEventConsequence6" value="0" checked="checked" /> No impact
							<input type="radio" name="weatherEventConsequence6" value="1" /> Insignificant
							<input type="radio" name="weatherEventConsequence6" value="2" /> Moderate
							<input type="radio" name="weatherEventConsequence6" value="3" /> Major
							<input type="radio" name="weatherEventConsequence6" value="4" /> Extreme
						</td>
					</tr>
					<tr>
						<td class="top">Adverse reputational impact<a href="#" id="lnkHelpVulnerabilityConsequence7" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<input type="radio" name="weatherEventConsequence7" value="0" checked="checked" /> No impact
							<input type="radio" name="weatherEventConsequence7" value="1" /> Insignificant
							<input type="radio" name="weatherEventConsequence7" value="2" /> Moderate
							<input type="radio" name="weatherEventConsequence7" value="3" /> Major
							<input type="radio" name="weatherEventConsequence7" value="4" /> Extreme
						</td>
					</tr>
					<tr>
						<td class="top">Environmental impact<a href="#" id="lnkHelpVulnerabilityConsequence8" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<input type="radio" name="weatherEventConsequence8" value="0" checked="checked" /> No impact
							<input type="radio" name="weatherEventConsequence8" value="1" /> Insignificant
							<input type="radio" name="weatherEventConsequence8" value="2" /> Moderate
							<input type="radio" name="weatherEventConsequence8" value="3" /> Major
							<input type="radio" name="weatherEventConsequence8" value="4" /> Extreme
						</td>
					</tr>
					<tr>
						<td class="top">Regulatory impact<a href="#" id="lnkHelpVulnerabilityConsequence9" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<input type="radio" name="weatherEventConsequence9" value="0" checked="checked" /> No impact
							<input type="radio" name="weatherEventConsequence9" value="1" /> Insignificant
							<input type="radio" name="weatherEventConsequence9" value="2" /> Moderate
							<input type="radio" name="weatherEventConsequence9" value="3" /> Major
							<input type="radio" name="weatherEventConsequence9" value="4" /> Extreme
						</td>
					</tr>
					<tr>
						<td class="top">Interruption/halt to logistics operations<a href="#" id="lnkHelpVulnerabilityConsequence10" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<input type="radio" name="weatherEventConsequence10" value="0" checked="checked" /> No impact
							<input type="radio" name="weatherEventConsequence10" value="1" /> Insignificant
							<input type="radio" name="weatherEventConsequence10" value="2" /> Moderate
							<input type="radio" name="weatherEventConsequence10" value="3" /> Major
							<input type="radio" name="weatherEventConsequence10" value="4" /> Extreme
						</td>
					</tr>
					<tr>
						<td class="top">Interruption to boat movements:</td>
						<td class="col2">
							<input type="radio" name="weatherEventConsequence11" value="0" checked="checked" /> No impact
							<input type="radio" name="weatherEventConsequence11" value="1" /> Insignificant
							<input type="radio" name="weatherEventConsequence11" value="2" /> Moderate
							<input type="radio" name="weatherEventConsequence11" value="3" /> Major
							<input type="radio" name="weatherEventConsequence11" value="4" /> Extreme
						</td>
					</tr>
					<tr>
						<td class="top">Altered dredging schedule:</td>
						<td class="col2">
							<input type="radio" name="weatherEventConsequence12" value="0" checked="checked" /> No impact
							<input type="radio" name="weatherEventConsequence12" value="1" /> Insignificant
							<input type="radio" name="weatherEventConsequence12" value="2" /> Moderate
							<input type="radio" name="weatherEventConsequence12" value="3" /> Major
							<input type="radio" name="weatherEventConsequence12" value="4" /> Extreme
						</td>
					</tr>
					<tr>
						<td class="top">Other business consequences <a href="#" id="lnkHelpVulnerabilityConsequencesOther" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a>:</td>
						<td class="col2">
							<textarea id="txtWeatherEventConsequencesOther" name="weatherEventConsequencesOther" rows="5" class="small"></textarea>
						</td>
					</tr>
					<tr>
						<td class="top">Would you say your response was adequate?</td>
						<td class="col2">
							<input type="radio" name="weatherEventResponseAdequate" value="adequate" checked="checked" /> Yes
							<input type="radio" name="weatherEventResponseAdequate" value="no" /> No
						</td>
					</tr>
					<tr>
						<td class="top">What were the changes implemented as a result of this event? <a href="#" id="lnkHelpVulnerabilityChanges" ><img src="<c:url value="/resources/img/icons/information.png" />" alt="Help" /></a></td>
						<td class="col2">
							<textarea id="txtWeatherEventChanges" name="weatherEventChanges" rows="5" class="small"></textarea>
						</td>
					</tr>
				</table>
				<button type="button" id="btnAddVulnerabilityMatrixDataElement" class="btn btn-icon btn-blue btn-plus" onclick="submit();" >
					<span></span>Add Vulnerability Assessment Data
				</button><br />
			</form:form>
		</div>
		</c:if>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function () {
		
		// Dynamic forms display from the Data Source" dropdownlist
		$('select#cbb${dataelementsfilter}DataSource').change(function() {
			$('.dataElementForm').hide();			
			var selectedValue = $('select#cbb${dataelementsfilter}DataSource').val();
			if (selectedValue != "none")
				$("#" + selectedValue + "DataForm").show();
			
			$("#add${dataelementsfilter}DataElementModalWindow").dialog('option', 'position', 'center');
		});
		
		// "Loading" message in the Engineering Model Data Element form
       	$("#loading").hide();
		$("#btnAddEngineeringModelDataElement").click (function () {
			$("#btnAddEngineeringModelDataElement").hide();
			$("#loading").show();
		});
		
		// Radio button to choose the source of Engineering Model Data Element
		$("input[name='rdEngineeringSourceType']").change(function(){
			var selectedRadio = $("input[name='rdEngineeringSourceType']:checked").val();
			$("#hdnEngineeringSourceType").val(selectedRadio);
		});
		
		// Variable selection updates the hidden engineering model category field
		$("#cbbEngineeringVariable").change(function(){
			var selectedCategory = $("#cbbEngineeringVariable option:selected").attr("title");
			$("#hdnEngVariableCategory").val(selectedCategory);
		});
		
		$('.dataElementForm').hide();
		
		setupDialogBox("add${dataelementsfilter}DataElementModalWindow", "btnOpenAdd${dataelementsfilter}DataElementModalWindow");

		
		// Help bubble popups
		var strPathToBubblePopupTheme = "${pageContext.request.contextPath}/resources/img/bubblepopup";
		
		<c:if test="${dataelementsfilter == 'NonClimate'}">
			setupBubblePopup("lnkHelpFileUpload", "Accepted formats: Text (txt) or Image (jpeg, jpg)", strPathToBubblePopupTheme);
		</c:if>
		
		<c:if test="${dataelementsfilter == 'Future'}">
			setupBubblePopup("lnkHelpCsiroVariable", "<p><h6>Mean Temperature</h6>Mean air temperature in degrees Celsius (�C) as measured at 2 m above ground. Values are given as change from modelled baseline (1981-2000) climate.</p><p><h6>Rainfall</h6>Mean rainfall in millimetres (mm). Values are given as change from modelled baseline (1981-2000) climate.</p><p><h6>Daily Relative Humidity</h6>Calculated at 2 m above ground and expressed in percent (%). Values are given as change from modelled baseline (1981-2000) climate.</p><p><h6>Wind Speed</h6>Mean wind speed, in metres per second (m/sec) as measured at 10m above the ground. Values are given as change from modelled baseline (1981-2000) climate.</p>", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpCmarVariable", "<p><h6>Sea Level Rise</h6>.</p>", strPathToBubblePopupTheme);

			var strHelpEmissionScenario = "<p>The <b>emission scenarios</b> represent the future development of greenhouse gas emissions and are based on assumptions about economic, technological and population growth. The two emissions scenarios that are available here are from the 'A1 storyline' and were developed by the IPCC Special Report on Emissions Scenarios (SRES).</p><p>As a general guide:</p><p><b>Emission Scenario A1B</b>: medium CO2 emissions, peaking around 2030</p><p><b>Emission Scenario A1FI</b>: high CO2�emissions, increasing throughout the 21st century</p>";
			setupBubblePopup("lnkHelpCsiroEmissionScenario", strHelpEmissionScenario, strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpCmarEmissionScenario", strHelpEmissionScenario, strPathToBubblePopupTheme);

			var strHelpClimateModel = "<p><b>Global climate models</b> (GCMs) are mathematical representations of the behaviour of the planet's climate system through time. Each mathematical equation is the basis for complex computer programs used for simulating the atmosphere or oceans of the Earth.</p><p>For this portal, CSIRO's Climate Futures software was used to select models by considering the alignment of model results over each of the natural resource management regions. 18 climate models were sub-divided into pre-defined categories such as \"Hotter, Drier\" and then assigned a relative likelihood based on the number of climate models that fell within that category. For example, if 9 of 18 models fell into the \"Warmer - Drier\" category, it was given a relative likelihood of 50%.</p><p>Three model categories are used here:<br/>A '<b>most likely</b>'* future: hotter and little change in rainfall.<br/>A '<b>hotter and drier</b>' future: much hotter and much drier.<br/>A '<b>cooler and wetter</b>' future: cooler and wetter.<br/></p><p>*Category represented by the greatest number of models that must be greater than or equal to 33% of the total number of models.</p>";
			setupBubblePopup("lnkHelpCsiroClimateModel", strHelpClimateModel, strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpCmarClimateModel", strHelpClimateModel, strPathToBubblePopupTheme);

			var strHelpTimePeriod = "<p><b>Time periods</b> are expressed relative to the 1981-2000 baseline period and are centred on a given decade. For example, the 2030s time period refers to the period 2020-2039.</p><p>Three future time periods are available: <b>2030</b>, <b>2055</b> and <b>2070</b>.</p>";
			setupBubblePopup("lnkHelpCsiroTimePeriod", strHelpTimePeriod, strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpCmarTimePeriod", strHelpTimePeriod, strPathToBubblePopupTheme);
		</c:if>
		
		<c:if test="${dataelementsfilter == 'Applications'}">
			setupBubblePopup("lnkHelpEngUpload", "Please use the Excel template (available <a href=\"http://10.118.96.11/ccimt\" title=\"Go to the engineering model tool\" target=\"blank\">here</a>) to import the asset data for the concrete deterioration modelling. Once uploaded, the tool will verify each entry to ensure it meets the requirements of the analysis.", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpEngVariable", "Each excel file and example contains data for many engineering variables. Choose one of these variables to use in the new Data Element. If you need more than one variable, add several Data Elements to your workboard.", strPathToBubblePopupTheme);
			
			setupBubblePopup("lnkHelpVulnerabilityWeatherEvent", "What disruptive climate related events has the port experienced in the last 15 years? Select from the drop down list", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpVulnerabilityDate", "What year did this disruptive event occur?", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpVulnerabilityDirect", "Did the climate related event impact your port specifically (direct impact), or did it impact your supply chain (indirect), causing a flow-on impact to your business?", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpVulnerabilityImpact", "Describe how the climate related event impacted your business. Eg: Rain caused onsite flooding; the cyclone damaged rail-lines from suppliers to the port", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpVulnerabilityConsequences", "<p>Eg: Flooding may have led to waste flow from the site - causing a Moderate Environmental Impact; and a Major Interruption to logistics operations.</p> <p>Rating of \"No impact\" through to \"Extreme\" should align with your organisational risk consequence table. If you do not have access to this, refer to the Guidance Document.</p>", strPathToBubblePopupTheme);
			
			setupBubblePopup("lnkHelpVulnerabilityConsequence1", "A lost-time injury is an one that resulted in a fatality, permanent disability or time lost from work of one day/shift or more", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpVulnerabilityConsequence2", "Events outside the port may restrict staff access, Eg: staff are in the local CFA, and fighting fires during heatwave", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpVulnerabilityConsequence6", "Insurance premiums may increase due to direct impacts on the port, or due to the perceived increased risk to the area", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpVulnerabilityConsequence7", "Reputation may be affected amongst the local community, or clients and other stakeholders, Eg: negative media reports; or perceived unreliability of port due to disruption caused by climate event", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpVulnerabilityConsequence8", "Any impact on the natural environment caused by port operations as a result of the climate related event, this may be excessive dust due to dry, windy conditions; contaminated water from the site; excessive fumes.", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpVulnerabilityConsequence9", "Regulatory impact refers to a contravention of a regulation or legal requirement, Eg; caused by environmental contravention as above, or workforce safety contravention.", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpVulnerabilityConsequence10", "A halt to the moving of goods to and from boats, and across the port; also an impact to storage on site", strPathToBubblePopupTheme);
			
			setupBubblePopup("lnkHelpVulnerabilityConsequencesOther", "Specify any other business consequences not listed above in the text box", strPathToBubblePopupTheme);
			setupBubblePopup("lnkHelpVulnerabilityChanges", "Changes may be to management systems, to safety protocols, maintenance processes, communication protocols and so forth.", strPathToBubblePopupTheme);
			
		</c:if>
	});
</script>
</c:if>