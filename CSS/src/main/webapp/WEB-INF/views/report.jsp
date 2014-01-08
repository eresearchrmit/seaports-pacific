<%--
 Copyright (c) 2013, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://code.google.com/p/climate-smart-seaports/
--%>
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
	<!-- Titles -->
	<div style="margin-left: 20px; float:left">
		<h2><c:out value="${report.name}" /></h2>
		<h4><c:out value="${report.seaport.region.name}" /> region - <c:out value="${report.seaport.name}" /></h4>
	</div>
	
	<a class="lnkConvertToUserStory" href="/auth/userstory/create?id=${report.id}" style="margin-right: 10px; float:right">
		<button id="btnConvertToUserStory" type="button" class="btn btn-icon btn-blue btn-arrow-right" >
			<span></span>Create Report
		</button>
	</a>
	<a class="lnkDeleteWorkboard" href="/auth/workboard/delete?id=${report.id}" style="margin-right: 10px; float:right">
		<button id="btnDeleteWorkboard" type="button" class="btn btn-icon btn-blue btn-cross" >
			<span></span>Delete WorkBoard
		</button>
	</a>
	<div style="display:none" id="confirmWorkboardDeletionModalWindow" title="Delete your workboard ?">
		<p class="message"><span class="error"><b>Warning : Deleting your workboard will also delete all data it contains. This action cannot be undone !</b></span></p>
		<p>Are you sure you want to permanently delete your current workboard ?</p> 
	</div>
	<div style="display:none" id="confirmConvertToUserStoryModalWindow" title="Create a report from this workboard ?">
		<p>This will create a Report based on your Workboard. Once the Workboard becomes a Report, no more data can be added to it and only text can be typed.</p> 
		<p>Are you sure you want to create a Report from your Workboard now ?</p>
	</div>
	<div style="display:none" id="confirmDataElementDeletionModalWindow" title="Delete this data element ?">
		<p>Are you sure you want to delete this data element from your Workboard ?</p> 
	</div>
	<div class="clear"></div><br />
	
	<c:set var="successMessage" scope="request" value="${successMessage}"/>
	<c:set var="warningMessage" scope="request" value="${warningMessage}"/>
	<c:set var="errorMessage" scope="request" value="${errorMessage}"/>
	<jsp:include page="notifications.jsp" />
	
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

			setupConfirmBox("confirmConvertToUserStoryModalWindow", "lnkConvertToUserStory");
			setupConfirmBox("confirmWorkboardDeletionModalWindow", "lnkDeleteWorkboard");
			setupConfirmBox("confirmDataElementDeletionModalWindow", "lnkDeleteDataElement");
			
			// Help tooltips activation
			setupTooltips();
		});
	</script>
	
	<c:set var="report" scope="request" value="${report}"/>
	<c:set var="elements" scope="request" value="${elements}"/>
	
	<div id="tabs">
		<c:if test="${not empty allCategories}">
		<ul>
			<c:forEach items="${allCategories}" var="category" varStatus="status">
				<li><a href="#tabs-${fn:replace(category.name, ' ', '-')}" id="lnk${fn:replace(category.name, ' ', '-')}">${category.name}</a></li>
			</c:forEach>
			<li style="float:right"><a href="#tabs-summary" id="lnkSummaryTab" class="${(dataelementsCounts[0] > 0 && dataelementsCounts[1] > 0 && dataelementsCounts[2] > 0 && dataelementsCounts[3] > 0) ? 'checked' : ''}">Summary (All)</a></li>
		</ul>
		</c:if>
		
		<c:forEach items="${allCategories}" var="category" varStatus="status">
			<div id="tabs-${fn:replace(category.name, ' ', '-')}">
			
				<!-- Explanation text -->
				<c:set var="path" scope="request" value="${elements}"/>
				<a href="#" id="helpTooltip-${fn:replace(category.name, ' ', '-')}" class="helpTooltip" title="${category.helpText}">
					<img src="<c:url value="/resources/img/icons/help.png" />">
				</a>
				
				<!-- Toolbox -->
				<%--<center>
					<jsp:include page="workboardToolbox.jsp" />
				</center> --%>
				<div class="clear"></div><br />
				
				<!-- Elements -->
				<c:if test="${not empty elements}">
					<c:forEach items="${elements}" var="element" varStatus="status">
						<c:if test="${element.category.name == category.name}">
							<c:set var="categoryNotEmpty" scope="request" value="true"/>
						
							<div class="box round">
								<div class="box-header">
								<h5 class="floatleft">${element.name}<%--<c:if test="${dataelement.class.simpleName == 'DataElementFile'}">.${dataelement.filetype}</c:if>--%></h5>
									<a class="lnkDeleteDataElement" href="/auth/workboard/deletedataelement?dataelementid=${element.id}">
									<button type="button" class="btn btn-icon btn-blue btn-small btn-cross floatright" >
										<span></span>Delete
									</button>
									</a>
									<div class="clear"></div>
								</div>
								<input name="elements[${status.index}].fileid" value="${element.id}" type="hidden">
								<input name="elements[${status.index}].filename" value="${element.name}" type="hidden">
								
								<div class="box-content">
									<c:if test="${element.class.simpleName == 'DataElement'}">
				 						DATA
					 				</c:if>
									<c:if test="${element.class.simpleName == 'InputElement'}">
					 					<c:choose>
											<c:when test="${element.contentType == 'jpg' || element.contentType == 'jpeg'}">
												<ul class="prettygallery clearfix">
													<li>
														<a href="data:image/jpeg;charset=utf-8;base64,${element.stringContent}" target="_blank" rel="prettyPhoto" title="${element.name}">
															<img name="${element.name}" src="data:image/jpeg;charset=utf-8;base64,${element.stringContent}" style="max-width:100%; max-height: 500px;" />
														</a>
											    	</li>
												</ul>
										    </c:when>
										
											<c:otherwise>
												<%-- <textarea name="elements[${status.index}].name" rows="12" disabled>${element.stringContent}</textarea> --%>
												${element.stringContent}
											</c:otherwise>
										</c:choose>
					 				</c:if>
								</div>
							</div>
							
						</c:if>
					</c:forEach>
					
					<c:choose>
					    <c:when test="${not empty categoryNotEmpty && categoryNotEmpty == 'true'}">
					       <!-- If category isn't empty, displays the green 'check' icon in the tab header -->
					       <script type="text/javascript">
								$("#lnk${fn:replace(category.name, ' ', '-')}").addClass('checked');
							</script>
					    </c:when>
					    <c:otherwise>
					        <!-- If category is empty,hides the help tooltip and display full description text -->
							${category.descriptionText}
							<script type="text/javascript">
								$("#helpTooltip-${fn:replace(category.name, ' ', '-')}").hide();
							</script>
					    </c:otherwise>
					</c:choose>
										
					<c:set var="categoryNotEmpty" scope="request" value="false"/>
				</c:if>
			</div>
		</c:forEach>
		
		<div id="tabs-summary">
			<div id="msgTabSummary" class="message info">
				<h5>Information</h5>
				<p><c:out value="All your selected data is presented in this section." /></p>
			</div>
			
			<c:if test="${not empty elements}">
				<c:forEach items="${elements}" var="element" varStatus="status">
						<div class="box round">
							<div class="box-header">
							<h5 class="floatleft">${element.name}<%--<c:if test="${dataelement.class.simpleName == 'DataElementFile'}">.${dataelement.filetype}</c:if>--%></h5>
								<a class="lnkDeleteDataElement" href="/auth/workboard/deletedataelement?dataelementid=${element.id}">
								<button type="button" class="btn btn-icon btn-blue btn-small btn-cross floatright" >
									<span></span>Delete
								</button>
								</a>
								<div class="clear"></div>
							</div>
							<input name="elements[${status.index}].fileid" value="${element.id}" type="hidden">
							<input name="elements[${status.index}].filename" value="${element.name}" type="hidden">
							
							<div class="box-content">
								
								<c:if test="${element.class.simpleName == 'DataElement'}">
				 					DATA
				 				</c:if>
								<c:if test="${element.class.simpleName == 'InputElement'}">
				 					<c:choose>
										<c:when test="${element.contentType == 'jpg' || element.contentType == 'jpeg'}">
											<ul class="prettygallery clearfix">
												<li>
													<a href="data:image/jpeg;charset=utf-8;base64,${element.stringContent}" target="_blank" rel="prettyPhoto" title="${element.name}">
														<img name="${element.name}" src="data:image/jpeg;charset=utf-8;base64,${element.stringContent}" style="max-width:100%; max-height: 500px;" />
													</a>
										    	</li>
											</ul>
									    </c:when>
									
										<c:otherwise>
											<%-- <textarea name="elements[${status.index}].name" rows="12" disabled>${element.stringContent}</textarea> --%>
											${element.stringContent}
										</c:otherwise>
									</c:choose>
				 				</c:if>
							</div>
						</div>
				</c:forEach>
			</c:if>
			
			<div style="text-align:center">
				<a class="lnkConvertToUserStory" href="/auth/userstory/create?id=${report.id}" >
					<button id="btnConvertToUserStory" type="button" class="btn btn-icon btn-blue btn-arrow-right">
						<span></span>Create Report
					</button>
				</a>
			</div>
		</div>
		
		<%--<div id="tabs-non-climate-context">
			
			<!-- Explanation text -->
			<c:choose>
				<c:when test="${dataelementsCounts[0] <= 0}">
					<br />
					<%@ include file="/WEB-INF/views/help/explanationTextNonClimate.jsp" %>
				</c:when>
				<c:otherwise>
					<a href="#" class="helpTooltip" title="<%@ include file="/WEB-INF/views/help/explanationTooltipNonClimate.jsp" %>">
						<img src="<c:url value="/resources/img/icons/help.png" />">
					</a>
				</c:otherwise>
			</c:choose>
						
			<!-- Toolbox -->
			<c:set var="elementsfilter" scope="request" value="NonClimate"/>
			<center>
				<jsp:include page="workboardToolbox.jsp" />
			</center>
			<div class="clear"></div><br />
			
			<jsp:include page="dataElements.jsp" />
		</div>
		
		<div id="tabs-observed-climate">
			<!-- Explanation text -->
			<c:choose>
				<c:when test="${dataelementsCounts[1] <= 0}">
					<br />
					<%@ include file="/WEB-INF/views/help/explanationTextObservedClimate.jsp" %>
				</c:when>
				<c:otherwise>
					<a href="#" class="helpTooltip" title="<%@ include file="/WEB-INF/views/help/explanationTooltipObservedClimate.jsp" %>">
						<img src="<c:url value="/resources/img/icons/help.png" />">
					</a>
				</c:otherwise>
			</c:choose>
			
			<!-- Toolbox -->
			<c:set var="elementsfilter" scope="request" value="ObservedClimate"/>
			<center>
				<jsp:include page="workboardToolbox.jsp" />
			</center>
			<div class="clear"></div><br />
			
			<jsp:include page="dataElements.jsp" />
		</div>
		
		<div id="tabs-future-climate">
			
			<!-- Explanation text -->
			<c:choose>
				<c:when test="${dataelementsCounts[2] <= 0}">
					<br />
					<%@ include file="/WEB-INF/views/help/explanationTextFutureClimate.jsp" %>
				</c:when>
				<c:otherwise>
					<a href="#" class="helpTooltip" title="<%@ include file="/WEB-INF/views/help/explanationTooltipFutureClimate.jsp" %>">
						<img src="<c:url value="/resources/img/icons/help.png" />">
					</a>
				</c:otherwise>
			</c:choose>
			
			<!-- Toolbox -->
			<c:set var="elementsfilter" scope="request" value="Future"/>
			<center>
				<jsp:include page="workboardToolbox.jsp" />
			</center>
			<div class="clear"></div><br />
			
			<jsp:include page="dataElements.jsp" />
		</div>
		
		<div id="tabs-applications">
			<!-- Explanation text -->
			<c:choose>
				<c:when test="${dataelementsCounts[3] <= 0}">
					<br />
					<%@ include file="/WEB-INF/views/help/explanationTextApplications.jsp" %>
				</c:when>
				<c:otherwise>
					<a href="#" class="helpTooltip" title="<%@ include file="/WEB-INF/views/help/explanationTooltipApplications.jsp" %>">
						<img src="<c:url value="/resources/img/icons/help.png" />">
					</a>
				</c:otherwise>
			</c:choose>
			
			<!-- Toolbox -->
			<c:set var="elementsfilter" scope="request" value="Applications"/>
			<center>
				<jsp:include page="workboardToolbox.jsp" />
			</center>
			<div class="clear"></div><br />
			
			<jsp:include page="dataElements.jsp" />
		</div>
		
		<div id="tabs-summary">
			<div id="msgTabSummary" class="message info">
				<h5>Information</h5>
				<p><c:out value="All your selected data is presented in this section." /></p>
			</div>
						
			<c:set var="elements" scope="request" value="${elements}"/>
			<c:set var="elementsfilter" scope="request" value="All"/>
			<jsp:include page="dataElements.jsp" />
			
			<center>
			<a class="lnkConvertToUserStory" href="/auth/userstory/create?id=${report.id}">
				<button id="btnConvertToUserStory" type="button" class="btn btn-icon btn-blue btn-arrow-right" >
					<span></span>Create Report
				</button>
			</a>
			</center>
		</div>--%>
	</div>
</c:if>
<c:if test="${empty report}">
	<c:set var="successMessage" scope="request" value="${successMessage}"/>
	<c:set var="warningMessage" scope="request" value="${warningMessage}"/>
	<c:set var="errorMessage" scope="request" value="${errorMessage}"/>
	<jsp:include page="notifications.jsp" />
</c:if>
</div>
