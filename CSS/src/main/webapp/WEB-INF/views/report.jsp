<%--
 Copyright (c) 2013, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://code.google.com/p/climate-smart-seaports/
--%>
<%@page import="edu.rmit.eres.seaports.helpers.FileTypeHelper"%>
<%@page import="edu.rmit.eres.seaports.model.Element"%>
<%@page import="edu.rmit.eres.seaports.model.InputElement"%>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.codec.binary.*"%>
<%@ page import= "org.springframework.web.servlet.tags.*" %>
<%@ page import="org.apache.commons.lang.*" %>
<%@ page language="java" import="edu.rmit.eres.seaports.model.Report" %>
<%@ page language="java" import="edu.rmit.eres.seaports.model.User" %>
<%@ page language="java" import="edu.rmit.eres.seaports.model.DataElement" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="grid_12">
<c:if test="${not empty report}">
	<%-- Titles --%>
	<div class="floatleft" style="margin-left: 20px">
		<h2><c:out value="${report.name}" /></h2>
		<h4><c:out value="${report.seaport.region.name}" /> region - <c:out value="${report.seaport.name}" /></h4>
	</div>
	
	<%-- Action buttons --%>
	<div id="actionButtons">
		<a id="lnkPublishReport" href="/auth/report/publish?id=${report.id}" class="floatright btn-margin">
			<button id="btnConvertToUserStory" type="button" class="btn btn-icon btn-blue btn-globe" >
				<span></span>Publish Report
			</button>
		</a>
		
		<a href="/auth/report/view?id=${report.id}" target="_blank" class="floatright btn-margin">
			<button class="btnAddDataElement btn btn-icon btn-blue btn-doc" >
				<span></span>Preview the report
			</button>
		</a>
		
		<a id="lnkDeleteReport" href="/auth/report/delete?id=${report.id}" class="floatright btn-margin">
			<button id="btnDeleteWorkboard" type="button" class="btn btn-icon btn-blue btn-cross" >
				<span></span>Delete Report
			</button>
		</a>
		
		<a href="javascript: $('#reportOrderForm').submit();" class="floatright btn-margin">
			<button id="btnSaveReportOrder" type="button" class="btn btn-icon btn-grey btn-refresh">
				<span></span>Save order
			</button>
		</a>
		
		<div style="display:none" id="confirmWorkboardDeletionModalWindow" title="Delete this report ?">
			<p class="message"><span class="error"><b>Warning : Deleting this report will also delete all the data it contains. This action cannot be undone !</b></span></p>
			<p>Are you sure you want to permanently delete this report ?</p> 
		</div>
		<div style="display:none" id="confirmConvertToUserStoryModalWindow" title="Publish this report ?">
			<p>The current status of this report is about to be published. You will be able to make further changes to the report but not the published version.</p> 
			<p>Are you sure you want to publish this report now ?</p>
		</div>
		<div style="display:none" id="confirmDataElementDeletionModalWindow" title="Delete this element ?">
			<p>Are you sure you want to delete this element from this report ?</p> 
		</div>
	</div>
	
	<%-- Script enabling the action buttons to stay always visible at the top of the page --%>
	<script type="text/javascript">
		var originalTop = $('#actionButtons').position().top;
		$(window).scroll(function() {
			if($(this).scrollTop() > originalTop) {
				$('#actionButtons').css({
					'position':'fixed',
			    	'top': '0', 
			    	'left': '50%',
			    	'z-index': '10',
			    	'width':'75%',
			    	'height': '38px',
			    	'margin-left': '-37.45%',
			    	'padding': '5px 0px 0px 0px',
			    	'background-color': 'white',
			    	'border-bottom': '1px solid #1d3b53',
			    	'box-shadow': '0px 3px 5px #1d3b53',
			    	'-webkit-border-bottom-right-radius': '10px',
					'-webkit-border-bottom-left-radius': '10px',
					'-moz-border-radius-bottomright': '10px',
					'-moz-border-radius-bottomleft': '10px',
					'border-bottom-right-radius': '10px',
					'border-bottom-left-radius': '10px'
				});
			}
			else {
				$('#actionButtons').css({
					'position':'relative',
					'left': '0',
					'background-color': 'transparent',
					'width':'100%',
					'margin-left': '0',
					'border-bottom': 'none',
					'box-shadow': 'none'
				});
			}
		});
	</script>
	<div class="clear"></div><br />
	
	<c:set var="successMessage" scope="request" value="${successMessage}"/>
	<c:set var="warningMessage" scope="request" value="${warningMessage}"/>
	<c:set var="errorMessage" scope="request" value="${errorMessage}"/>
	<jsp:include page="notifications.jsp" />
	
	<%-- Script enabling the tabs --%>
	<script type="text/javascript">
	
		$(function() {
			$("#tabs").tabs({
				activate: function(event, ui) {
					
					// Hide all notifications when changing tab
					$(".message").hide();
					
					// Change the hash of the webpage to the activated Tab, without scrolling to that hash
					// This is just to return to the right tab if a form is posted or page refreshed
					var active = $("#tabs").tabs("option", "active");
					var tabHash = $("#tabs ul>li a").eq(active).attr('href');
					if(history.pushState)
					    history.pushState(null, null, tabHash);
					else
					    location.hash = tabHash;

					// Triggered to resize the Highcharts graphs to a % of the width of the tab
					$(window).resize();
				}
			});
			
			setupConfirmBox("confirmConvertToUserStoryModalWindow", "lnkPublishReport");
			setupConfirmBox("confirmWorkboardDeletionModalWindow", "lnkDeleteReport");
			setupConfirmBox("confirmDataElementDeletionModalWindow", "lnkDeleteElement");
			
			// Help tooltips activation
			setupTooltips();
		});
	</script>
	
	<c:set var="report" scope="request" value="${report}"/>
	
	<div id="tabs">
		<%-- Header of tabs --%>
		<c:if test="${not empty allCategories}">
		<ul>
			<c:forEach items="${allCategories}" var="category" varStatus="status">
				<li><a href="#tabs-${fn:replace(category.name, ' ', '-')}" id="lnk${fn:replace(category.name, ' ', '-')}">${category.name}</a></li>
			</c:forEach>
			<li style="float:right"><a href="#tabs-summary" id="lnkSummaryTab" class="${(dataelementsCounts[0] > 0 && dataelementsCounts[1] > 0 && dataelementsCounts[2] > 0 && dataelementsCounts[3] > 0) ? 'checked' : ''}">Summary (All)</a></li>
		</ul>
		</c:if>
		
		<%-- Content of tabs --%>
		<c:forEach items="${allCategories}" var="category" varStatus="status">
			<c:set var="categoryNameWithDashes" value="${fn:replace(category.name, ' ', '-')}" />
			<div id="tabs-${categoryNameWithDashes}">
			
				<%-- Explanation text --%>
				<a href="#" id="helpTooltip-${categoryNameWithDashes}" class="helpTooltip" title="${category.helpText}">
					<img src="<c:url value="/resources/img/icons/help.png" />">
				</a>
				
				<%-- 'Add Element' button --%>
				<div class="centered">
					<button id="btnOpenAddDataModalWindow${category.id}" type="button" class="btn btn-icon btn-blue btn-plus" >
						<span></span>Add Data
					</button>
					<button id="btnOpenAddTextModalWindow${category.id}" type="button" class="btn btn-icon btn-blue btn-plus" >
						<span></span>Add Text
					</button>
					<button id="btnOpenAddFileModalWindow${category.id}" type="button" class="btn btn-icon btn-blue btn-plus" >
						<span></span>Add File
					</button>
				</div>
				
				<%-- Text Addition Modal Window --%>
				<div id="addTextModalWindow${category.id}" class="box round first" title="New Text" style="display:none">
					<form:form method="post" action="/auth/report/create-text-element?id=${report.id}#tabs-${categoryNameWithDashes}" modelAttribute="newinputelement">
						
						<form:hidden id="hdnInputElementId" path="id" value="0" />
						<form:hidden id="hdnInputElementCategory" path="category.name" value="${category.name}" />
						<form:hidden id="hdnInputElementIncluded" path="included" value="true" />
						<form:hidden id="hdnInputElementContentType" path="contentType" value="txt" />
						<form:hidden id="hdnInputElementReport" path="report.id" value="${report.id}" />
						
						<textarea name="textContent" class="tinymce" rows="12">
						</textarea>
						<br />
						<div style="height: 50px">
							<button type="button" class="btn btn-icon btn-blue btn-plus btn-small floatright" onclick="submit();">
								<span></span>Add Text to report
							</button>
							
							<div class="floatright btn-margin">
								<span class="hint">Insert text after:</span>
								<form:select id="cbbNewTextPosition" name="position" path="position">
									<option value="0">[Insert in 1st position]</option>
									<c:if test="${not empty report.elements}">
										<c:forEach items="${report.elements}" var="positionLoopElements" varStatus="elementPositionLoopStatus">
											<option value="${positionLoopElements.position}"${elementPositionLoopStatus.index == 0 ? ' selected' : ''}>
												${positionLoopElements.name} [${positionLoopElements.position}]
											</option>
										</c:forEach>
									</c:if>
								</form:select>
							</div>
						</div>
					</form:form>
				</div>
				
				<%-- File Addition Modal Window --%>
				<div id="addFileModalWindow${category.id}" class="box round first" title="New File" style="display:none">
					<form:form method="post" action="/auth/report/create-file-element" enctype="multipart/form-data" modelAttribute="newinputelement">
						<form:hidden id="hdnInputElementId" path="id" value="0" />
						<form:hidden id="hdnInputElementCategory" path="category.name" value="${category.name}" />
						<form:hidden id="hdnInputElementIncluded" path="included" value="true" />
						<form:hidden id="hdnInputElementReport" path="report.id" value="${report.id}" />
						
						<table width="auto" height="auto" class="form">
							<tr>
								<td>Select a file to upload&nbsp;
									<a href="#" class="helpTooltip" title="Port authorities have extensive data regarding their organisation's characteristics, throughput and local context. Therefore, this section allows the user to upload custom data files.<br /><br />These files may include graphic depictions of the port system and its assets, information regarding organisational objectives and/or current risks, or data on throughput volume or the types of activity that characterise the port.<br /><br />Acceptable formats are Text (.txt) or Image (.jpeg or .jpg) formats."><img src="<c:url value="/resources/img/icons/help.png" />" alt="Help" /></a>:
								</td>
								<td class="col2">
									<input type="file" name="file" id="customFileUpload" />
									<p class="hint">
										<i>Accepted formats: Text (txt), table (csv) or Image (jpeg)</i>
									</p>
								</td>
							</tr>
						</table>
						
						<div style="height: 50px">
							<button type="button" class="btn btn-icon btn-blue btn-plus btn-small floatright" onclick="submit();" >
								<span></span>Add File
							</button>
							
							<div class="floatright btn-margin">
								<span class="hint">Insert file after:</span>
								<form:select id="cbbNewTextPosition" name="position" path="position">
									<option value="0">[Insert in 1st position]</option>
									<c:if test="${not empty report.elements}">
										<c:forEach items="${report.elements}" var="positionLoopElements" varStatus="elementPositionLoopStatus">
											<option value="${positionLoopElements.position}"${elementPositionLoopStatus.index == 0 ? ' selected' : ''}>
												${positionLoopElements.name} [${positionLoopElements.position}]
											</option>
										</c:forEach>
									</c:if>
								</form:select>
							</div>
						</div>
					</form:form>
				</div>
				
				<%-- Data Element Addition Modal Window --%>
				<div id="addDataModalWindow${category.id}" class="box round first" title="New Element" style="display:none">
						<p><strong>1. Data Source:</strong></p>
						<table width="auto" height="auto" class="form">
							<tr>
								<td>Choose a data source:</td>
								<td class="col2">
									<select id="cbbDataSource${category.id}" name="dataSource">
										<option value="none">- Select Data Source -</option>
										<c:forEach items="${category.dataSourcesAvailable}" var="datasource" varStatus="dsLoopStatus">
											<option value="${datasource.name}">${datasource.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</table>
					<p>
					
					<c:forEach items="${category.dataSourcesAvailable}" var="datasource" varStatus="dsLoopStatus">
						<div id="addElementForm${category.id}-${datasource.name}" class="elementForm">
							<form:form method="post" action="/auth/report/create-data-element?id=${report.id}#tabs-${categoryNameWithDashes}" modelAttribute="newdataelement">
								<%--<form:input id="txtElementName" path="name" style="width:300px" />--%>
								
								<form:hidden id="hdnDataElementId" path="id" value="0" />
								<form:hidden id="hdnEDatalementCategory" path="category.name" value="${category.name}" />
								<form:hidden id="hdnDataElementDataSource" path="dataSource.name" value="${datasource.name}" />
								<form:hidden id="hdnDataElementReport" path="report.id" value="${report.id}" />
								
								<p><strong>2. ${datasource.name} Element Options:</strong></p>
								
								<table width="auto" height="auto" class="form">
									<c:forEach items="${datasource.parameters}" var="parameter" varStatus="parameterLoopStatus">
										<tr>
											<td>
												${parameter.name}&nbsp;
												<a href="#" class="helpTooltip" title="<p>${parameter.description}</p>"><img src="<c:url value="/resources/img/icons/help.png" />" alt="Help" /></a>:
											
											</td>
											<td class="col2">
												<form:select id="cbb${datasource.name}Param${parameter.name}" path="selectedOptions[${parameterLoopStatus.index}].id" multiple="false">
													<c:forEach items="${parameter.options}" var="option">
														<form:option value="${option.id}" >${option.name}</form:option>
													</c:forEach>
												</form:select>
											</td>
										</tr>
									</c:forEach>
									
									<%--<tr>
										<td>
											Seaport&nbsp;
											<a href="#" class="helpTooltip" title="Re-select your port"><img src="<c:url value="/resources/img/icons/help.png" />" alt="Help" /></a>:
										</td>
										<td class="col2">
											<c:if test="${not empty datasource.seaports}">
											<select id="cbb${datasource.name}Seaport" name="seaport">
												<c:forEach items="${datasource.seaports}" var="dsSeaport" varStatus="dsSeaportLoopStatus">
													<option value="${dsSeaport.code}">
														${dsSeaport.name} (${dsSeaport.urbanCenter})
													</option>
												</c:forEach>
											</select>
											</c:if>
										</td>
									</tr>--%>
								</table>
								<p><strong>3. Display Options:</strong></p>
								<table width="auto" height="auto" class="form">
									<tr>
										<td>Display data as:</td>
										<td class="col2">
											<c:forEach items="${datasource.displayTypes}" var="displayType" varStatus="displayLoopStatus">
												<input type="radio" name="displayType" value="${displayType.name}" ${displayLoopStatus.first ? 'checked="checked"' : ''} /> ${displayType.name}
											</c:forEach>
										</td>
									</tr>
								</table>
								
								<div style="margin-top: 40px">
									<button type="button" class="btn btn-icon btn-blue btn-plus btn-small floatright" onclick="submit();" >
										<span></span>Add ${datasource.name} Data
									</button>
									
									<div class="floatright btn-margin">
										<span class="hint">Insert file after:</span>
										<form:select id="cbb${datasource.name}ElementPosition" name="position" path="position">
											<option value="0">[Insert in 1st position]</option>
											<c:if test="${not empty report.elements}">
												<c:forEach items="${report.elements}" var="positionLoopElements" varStatus="elementPositionLoopStatus">
													<option value="${positionLoopElements.position}"${elementPositionLoopStatus.index == 0 ? ' selected' : ''}>
														${positionLoopElements.name} [${positionLoopElements.position}]
													</option>
												</c:forEach>
											</c:if>
										</form:select>
									</div>
								</div>
							</form:form>
						</div>
					</c:forEach>
					<script type="text/javascript">
					$(function () {		
						setTimeout(function(){
							// Dynamic forms display from the "Data Source" dropdownlist
							$('select#cbbDataSource${category.id}').change(function() {
								$('.elementForm').hide();
								var selectedValue = $('select#cbbDataSource${category.id}').val();
								
								if (selectedValue != "none")
									$("#addElementForm${category.id}-" + selectedValue).show();
								
								$("#addDataModalWindow${category.id}").dialog('option', 'position', 'center');
							});
						}, 0);
					});
					</script>
				</div>
				
				<%-- Script enabling the dynamic forms based on Data Source --%>
				<script type="text/javascript">
				$(function () {						
					// "Loading" message in the Engineering Model Data Element form
			       	$("#loading").hide();

					// Hiding all elements addition forms by default 
					$('.elementForm').hide();
					
					// Open the right modal window on click
					setupDialogBox("addDataModalWindow${category.id}", "btnOpenAddDataModalWindow${category.id}");
					setupDialogBox("addTextModalWindow${category.id}", "btnOpenAddTextModalWindow${category.id}");
					setupDialogBox("addFileModalWindow${category.id}", "btnOpenAddFileModalWindow${category.id}");					
				});
				</script>
				
				<div class="clear"></div><br />
				
				
				<%-- Elements --%>
				<c:if test="${not empty report.elements}">
					<c:forEach items="${report.elements}" var="element" varStatus="status">
						<c:if test="${element.category.name == category.name}">
							<c:set var="element" scope="request" value="${element}"/>
							<c:set var="categoryNotEmpty" scope="request" value="true"/>
							
							<div class="box round${element.included == false ? ' box-disabled' : ''}">
								<div class="box-header">
								<h5 class="floatleft">${element.name}<%--<c:if test="${dataelement.class.simpleName == 'DataElementFile'}">.${dataelement.filetype}</c:if>--%></h5>
									<!-- Delete button -->
									<a class="lnkDeleteElement" href="/auth/report/delete-element?id=${element.id}">
										<button type="button" class="btn btn-icon btn-blue btn-small btn-cross floatright" >
											<span></span>Delete
										</button>
									</a>
									<!-- 'Include/Exclude' button -->
									<a class="lnkIcludeExcludeElement" href="/auth/report/include-element?id=${element.id}&included=${!element.included}" title="${element.included == false ? 'Include in the report' : 'Exclude from the report'}">
										<button type="button" class="btn-mini btn-blue ${element.included == false ? ' btn-plus' : ' btn-minus'} floatright btn-margin">
											<span></span>${element.included == false ? 'Include' : 'Exclude'}
										</button>
									</a>
									<!-- 'Edit text' button for plain text elements -->
									<% Element element = (Element)(request.getAttribute("element"));
										System.out.print(element.getClass().getName());
										if (element instanceof InputElement) {
											if (FileTypeHelper.IsContentTypePlaintext(((InputElement)element).getContentType())) { %>
										<button id="btnOpenEditTextElementModalWindow" class="btn btn-small btn-icon btn-blue btn-edit floatright btn-margin btnEditText"
										 onclick="$('#hdnTextElementToEditId').val(${element.id}); tinyMCE.get('txtElementToEditContent').setContent('${fn:escapeXml(element.escapedStringContent)}');">
											<span></span>Edit text
										</button>
									<% 		}
										} else if (element instanceof DataElement) { %>
										<form:form id="editDataElementDisplayTypeForm${element.id}" method="post" action="/auth/report/edit-display-type" class="floatright btn-margin"> 
											<input id="hdnDataElementToEditId" type="hidden" name="elementId" value="${element.id}" />
											
											<select name="displayType" onchange="submit();">
												<option value="0">--- Change Display Type ---</option>
												<c:forEach items="${element.dataSource.displayTypes}" var="displayType" varStatus="displayLoopStatus">
													<option value="${displayType.name}" ${displayType.name == element.displayType.name ? ' selected' : ''} /> ${displayType.name}</option>
												</c:forEach>
											</select>
										</form:form>
									<% } %>
									<div class="clear"></div>
								</div>
								
								<div class="box-content">
									<c:set var="element" scope="request" value="${element}" />
									<jsp:include page="element.jsp" />
								</div>
							</div>
							
						</c:if>
					</c:forEach>					
				</c:if>
				
				
				<c:choose>
					 <%-- If category isn't empty, displays the green 'check' icon in the tab header --%>
					<c:when test="${not empty categoryNotEmpty && categoryNotEmpty == 'true'}">
						<script type="text/javascript">
							$("#lnk${fn:replace(category.name, ' ', '-')}").addClass('checked');
						</script>
				    </c:when>
				     <%-- If category is empty, hides the help tooltip and display full description text --%>
				    <c:otherwise>
						${category.descriptionText}
						<script type="text/javascript">
							$("#helpTooltip-${fn:replace(category.name, ' ', '-')}").hide();
						</script>
				    </c:otherwise>
				</c:choose>
				
				<c:set var="categoryNotEmpty" scope="request" value="false"/>
			</div>
		</c:forEach>
		
		<%-- Summary tab --%>
		<div id="tabs-summary">		
			<c:if test="${not empty report.elements}">
			
				<div id="msgTabSummary" class="message info">
					<h5>Information</h5>
					<p><c:out value="All your selected data is presented in this section." /></p>
				</div>
				
				<form:form id="reportOrderForm" method="post" action="/auth/report/save" modelAttribute="report">
	  				<form:input value="${report.id}" type="hidden" path="id" />
		 			<ul id="sortable">
		 	
						<c:forEach items="${report.elements}" var="element" varStatus="status">
						<li class="sortableItem" id="element${element.id}">
							<div class="box round${element.included == false ? ' box-disabled' : ''}">
								<div class="box-header">
								<h5 class="floatleft">${element.name}<%--<c:if test="${dataelement.class.simpleName == 'DataElementFile'}">.${dataelement.filetype}</c:if>--%></h5>
									<!--  Delete Element Button -->
									<a class="lnkDeleteDataElement" href="/auth/report/delete-element?id=${element.id}">
										<button type="button" class="btn btn-icon btn-blue btn-small btn-cross floatright" >
											<span></span>Delete
										</button>
									</a>
									<!-- 'Include/Exclude' button -->
									<a class="lnkIcludeExcludeElement" href="/auth/report/include-element?id=${element.id}&included=${!element.included}" title="${element.included == false ? 'Include in the report' : 'Exclude from the report'}">
										<button type="button" class="btn-mini btn-blue ${element.included == false ? ' btn-plus' : ' btn-minus'} floatright btn-margin">
											<span></span>${element.included == false ? 'Include' : 'Exclude'}
										</button>
									</a>
									<div class="clear"></div>
								</div>

								<input type="hidden" name="elements[${status.index}].id" value="${element.id}" id="elements[${status.index}].id" >
								<input type="hidden" name="elements[${status.index}].name" value="${element.name}" id="elements[${status.index}].name">
								<input type="hidden" name="elements[${status.index}].position" value="${element.position}" id="elements[${status.index}].position" class="dataElementPosition">
								
								<div class="box-content">
									<c:set var="element" scope="request" value="${element}" />
									<jsp:include page="element.jsp" />
								</div>
							</div>
						</li>
						</c:forEach>
					</ul>
				</form:form>
				
			<%-- Script enabling drag-and-drop reordering --%>
			<script type="text/javascript">
				$(document).ready(function () {
					
					var draggedTextContent = null;
					
					// Enables the sortable list
					$( "#sortable" ).sortable({
						placeholder: "sortable-placeholder",
						cursor: 'crosshair',
						start: function(event, ui) {
							draggedTextContent = ui.item.find('.tinymce').html();
						},
			            update: function(event, ui) {
			            	// Reorder the positions into the hidden field of each data element
			            	var order = $("#sortable").sortable("toArray");
							for (var i = 0; i < order.length; i++)
							{
								var element = $("#" + order[i]);
								element.find(".dataElementPosition").attr("value", (i + 1));
							}
							
							// Highlights the save button to remind the user to save the report after reordering
							$("#btnSaveReportOrder").removeClass("btn-grey");
							$("#btnSaveReportOrder").addClass("btn-blue");
							$("#btnSaveReportOrder").effect( "highlight", {color:"#FF7F24"}, 2000 );
			            }
					});
					$( "#sortable" ).disableSelection();
					
					// This is not a Jquery '$', but a JSTL tag to get the current number of data elements
					var index = ${fn:length(report.elements)};
				});
			</script>
				
			</c:if>
			
			<%-- 'No element' message if report empty --%>
			<c:if test="${empty report.elements}">
				<div id="msgTabSummary" class="message info">
					<h5>Information</h5>
					<p><c:out value="There is no element in the report. Select category tabs and add elements to fill the report." /></p>
				</div>
			</c:if>
			
			<%-- Publish report button --%>
			<div class="centered">
				<a class="lnkPublishReport" href="/auth/report/publish?id=${report.id}" >
					<button id="btnPublishReport" type="button" class="btn btn-icon btn-blue btn-globe">
						<span></span>Publish Report
					</button>
				</a>
			</div>
			
		</div>
	</div>	
</c:if>

<%-- Still display error, warnings and infos even if the report is empty --%>
<c:if test="${empty report}">
	<c:set var="successMessage" scope="request" value="${successMessage}"/>
	<c:set var="warningMessage" scope="request" value="${warningMessage}"/>
	<c:set var="errorMessage" scope="request" value="${errorMessage}"/>
	<jsp:include page="notifications.jsp" />
</c:if>

	<%-- Modal window to edit text --%>
	<div id="editTextElementModalWindow" class="box round" title="Edit text" style="display:none; padding:0;">
		<form:form id="editTextForm" method="post" action="/auth/report/edit-text-element"> 
			<input id="hdnTextElementToEditId" type="hidden" name="elementId" />
			<textarea id="txtElementToEditContent" name="textContent" class="tinymce" rows="25">
			</textarea>
			<div style="height: 50px">
				<button type="button" class="btn btn-icon btn-blue btn-check btn-small floatright btn-margin" onclick="submit();" style="margin-top:20px">
					<span></span>Save changes
				</button>
			</div>
		</form:form>
	</div>
	
	<%-- Scripts enabling TinyMCE on every textarea --%>			
	<script type="text/javascript" src="<c:url value="/resources/js/tiny_mce/tiny_mce.js" />"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			setupDialogBoxByClass("editTextElementModalWindow", "btnEditText");
			
			tinyMCE.init({
		        // General options
		        mode : "textareas",
		        theme : "advanced",
		        plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",
		
		        // Theme options
		        theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,sub,sup,charmap,|,bullist,numlist,blockquote,outdent,indent,|,justifyleft,justifycenter,justifyright,justifyfull" 
		        	 + "fontselect,fontsizeselect,forecolor,backcolor,formatselect,|,link,unlink,|,insertdate,inserttime,|,cleanup,code,|,help",
		        theme_advanced_buttons2 : "",
		        theme_advanced_buttons3 : "",
		        theme_advanced_buttons4 : "",
		        theme_advanced_toolbar_location : "top",
		        theme_advanced_toolbar_align : "left",
		        theme_advanced_statusbar_location : "bottom",
		        theme_advanced_resizing : false,
		        width: "100%",
		        height: "300px"
			});
		});
	</script>

</div>
