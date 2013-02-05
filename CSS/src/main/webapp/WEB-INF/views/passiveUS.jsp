<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.codec.binary.*"%>
<%@ page import= "org.springframework.web.servlet.tags.*" %>
<%@ page import="org.apache.commons.lang.*" %>
<%@ page language="java" import="war.model.UserStory" %>
<%@ page language="java" import="war.model.User" %>
<%@ page language="java" import="war.model.DataElement" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="grid_12">
	<h2>${userstory.name} </h2>
	
	<c:if test="${not empty successMessage}">
		<div id="successMessage" class="message success">
			<h5>Success !</h5>
			<p>${successMessage}.</p>
		</div>
		<!-- Makes the success messages fade out after 3 seconds -->
		<script type="text/javascript">
			$(document).ready(function(){
				setTimeout(function(){
					$("#successMessage").fadeOut("slow", function () {
						$("#successMessage").remove();
					});
				}, 3000);
			});
		</script>
	</c:if>
	<c:if test="${not empty errorMessage}">
		<div class="message error">
			<h5>Error</h5>
			<p>${errorMessage}.</p>
		</div>
	</c:if>
	
	<form:form id="userStoryForm" method="post" action="/CSS/spring/userstory/save?story=${userstory.id}" modelAttribute="userstory">
	  	<form:input value="${userstory.id}" type="hidden" path="id" />
	  	<c:if test="${not empty userstory.dataElements}">		
		 	<ul id="sortable">
		 	
		 	<!-- Iteration on every element in the User Story -->
		 	<c:forEach items="${userstory.dataElements}" var="dataelement" varStatus="status">
		 			<li class="sortableItem" id="dataElement${dataelement.id}">
			 			<div class="box round${dataelement.included == false ? ' box-disabled' : ''}">
							
							<div class="box-header<c:if test="${dataelement.class.simpleName == 'DataElementFile' && dataelement.filetype == 'comment'}"> box-header-white</c:if>" >
								<h5 class="floatleft">${dataelement.name}<c:if test="${dataelement.class.simpleName == 'DataElementFile' && dataelement.filetype != 'comment'}">.${dataelement.filetype}</c:if></h5>
								
								<!-- 'Include/Exclude' button -->
								<button type="button" class="btn-mini ${dataelement.included == false ? 'btn-grey btn-plus' : 'btn-blue btn-minus'} floatright" onclick="location.href='/CSS/spring/userstory/includeDataElement?story=${userstory.id}&dataelement=${dataelement.id}'" >
									<span></span>Include/Exclude
								</button>
								
								<!-- 'Remove Text' button -->
								<c:if test="${dataelement.class.simpleName == 'DataElementFile' && dataelement.filetype == 'comment'}">
									<a class="lnkRemoveTextFromStory" href="/CSS/spring/userstory/removeText?text=${dataelement.id}" >
										<button type="button" class="btn btn-icon ${dataelement.included == false ? 'btn-grey' : 'btn-blue'} btn-small btn-cross floatright" style="margin-right:5px">
											<span></span>Remove
										</button>
									</a>
								</c:if>
								<div class="clear"></div>
							</div>
							
							<input type="hidden" name="dataElements[${status.index}].id" value="${dataelement.id}" id="dataElements[${status.index}].id" >
							<input type="hidden" name="dataElements[${status.index}].name" value="${dataelement.name}" id="dataElements[${status.index}].name">
							<input type="hidden" name="dataElements[${status.index}].position" value="${dataelement.position}" id="dataElements[${status.index}].position" class="dataElementPosition">
							
							<c:if test="${dataelement.class.simpleName == 'DataElementCsiro'}">
		 					<!-- CSIRO Data Element -->
		 					<c:choose>
			 					<c:when test="${not empty dataelement.csiroDataList}">
			 						<p><b>${dataelement.csiroDataList[0].parameters.modelName} model (${dataelement.csiroDataList[0].parameters.model.name}) in the region ${dataelement.csiroDataList[0].parameters.region.name} for ${dataelement.csiroDataList[0].parameters.emissionScenario.description} (${dataelement.csiroDataList[0].parameters.emissionScenario.name})</b></p>
				 					
				 					<table class="data display datatable" id="example">
										<thead>
											<tr>
												<th>Variable</th>
												<th>Year</th>
												<th>Value</th>
											</tr>
										</thead>
					 					<tbody>
					 						<c:forEach items="${dataelement.csiroDataList}" var="csiroData" varStatus="dataLoopStatus">
						 						<tr class="${dataLoopStatus.index % 2 == 0 ? 'even' : 'odd'}">
							 						<td class="center">${csiroData.variable.name} (${csiroData.variable.uom})</td>
							 						<td class="center">${csiroData.parameters.year}</td>
							 						<td class="center">${csiroData.value} ${csiroData.variable.uomVariation}</td>
						 						</tr>
						 					</c:forEach>
					 					</tbody>
				 					</table>
				 					<i>Data provided by CSIRO on <fmt:formatDate value="${dataelement.csiroDataList[0].creationDate}" pattern="dd MMM yyyy" /> was the best available to date</i>
				 				</c:when>
				 				<c:otherwise>
				 					<div id="warningMessage" class="message warning">
										<h5>No Data</h5>
										<p>No data corresponding to the selected settings.</p>
									</div>
				 				</c:otherwise>
		 					</c:choose>
		 				</c:if>
							
						<c:if test="${dataelement.class.simpleName == 'DataElementEngineeringModel'}">
		 					<!-- Engineering Model Data Element -->
		 					<p>ENGINEERING MODEL DATA ELEMENT</p>
		 				</c:if>	

						<c:if test="${dataelement.class.simpleName == 'DataElementFile'}">
		 					<!-- File Data Element, display a picture if JPEG, textarea with content otherwise -->
		 					<input type="hidden" name="dataElements[${status.index}].filetype" value="${dataelement.filetype}" id="dataElements[${status.index}].filetype">
		 					<c:choose>
			 					<c:when test="${dataelement.filetype == 'jpg' || dataelement.filetype == 'jpeg'}">
									<ul class="prettygallery clearfix">
			                        	<li>
			                        		<a href="data:image/jpeg;charset=utf-8;base64,${dataelement.stringContent}" rel="prettyPhoto[gallery2]" title="${dataelement.name}">
			                            		<img name="${dataelement.name}" src="data:image/jpeg;charset=utf-8;base64,${dataelement.stringContent}" class="dataElementThumb" />
			                            	</a>
			                            </li>
									</ul>		
			                 	</c:when>
			 					<c:when test="${dataelement.filetype == 'comment'}">
									<textarea name="dataElements[${status.index}].stringContent" rows="12" onfocus="if ($(this).val() == 'Add text here...') { $(this).val(''); }" onblur="if ($(this).val() == '') { $(this).val('Add text here...'); }">${dataelement.stringContent}</textarea>		
			                 	</c:when>
			                 	<c:otherwise>
			                 		<textarea class="no-border as-plain-text" rows="12" disabled>${dataelement.stringContent}</textarea>
								</c:otherwise>
							</c:choose>
						</c:if>
						</div>
					</li>
					<c:if test="${status.index} % 2 != 0">
						<div class="clearfix"></div>
					</c:if>
			</c:forEach>
			</ul>
			
			<script type="text/javascript">
				$(document).ready(function () {
					// Resize the sortable list items 2 by 2 to have a proper grid
					resizeListItems();
					
					// Enables the sortable list
					$( "#sortable" ).sortable({
						placeholder: "sortable-placeholder",
						cursor: 'crosshair',
			            update: function(event, ui) {
			            	// Reorder the positions into the hidden field of each data element
			            	var order = $("#sortable").sortable("toArray");
							for (var i = 0; i < order.length; i++)
							{
								var element = $("#" + order[i]);
								element.find(".dataElementPosition").attr("value", (i + 1));
							}
							// Resize the sortable list items 2 by 2 to have a proper grid
							resizeListItems();
			            }
					});
					$( "#sortable" ).disableSelection();
					
					// This is not a Jquery '$', but a JSTL tag to get the current number of data elements
					var index = ${fn:length(userstory.dataElements)};
				});
				
				// Resize the sortable list items 2 by 2 to have a proper grid
				function resizeListItems() {
					var listItems = $("#sortable li.sortableItem .box");
					var boxOne = null;
					listItems.each(function(idx, box) {
						if (idx % 2 != 0) {
							boxTwo = $(box);
							if (boxOne.height() > boxTwo.height())
								boxTwo.height(boxOne.height());
							else if (boxTwo.height() > boxOne.height())
								boxOne.height(boxTwo.height());
					    }
					    else
					    	boxOne = $(box);
					});
				}
			</script>
		</c:if>
		<div class="clearfix"></div><br />
		<button type="button" class="btn btn-icon btn-blue btn-check floatright" onclick="$('#userStoryForm').submit();">
			<span></span>Save
		</button>
		<div class="clearfix"></div><br />
	</form:form>
	
	<div id="dataElementAdder">
		<a href="/CSS/spring/userstory/addText?story=${userstory.id}">
			<button class="btnAddDataElement btn btn-icon btn-blue btn-plus" >
				<span></span>Add Text to Story
			</button>
		</a>
	</div>
		
	<div id="confirmTextDeletionModalWindow" title="Permanently delete this text ?">
		<p>Are you sure you want to permanently delete this text ?</p> 
	</div>
	<script type="text/javascript">
		setupConfirmBox("confirmTextDeletionModalWindow", "lnkRemoveTextFromStory");
	</script>
</div>