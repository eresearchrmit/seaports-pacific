<%--
 Copyright (c) 2014, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import= "org.springframework.web.servlet.tags.*" %>

<sec:authorize access="isAuthenticated()">
	<div id="userInformations">
		<div class="floatright marginleft10">
			<ul class="inline-ul floatleft">
				<li><a href="/public/user/${user.username}">${user.firstname} ${user.lastname} (<sec:authentication property="principal.username" />)</a></li>
				<li><a href="/j_spring_security_logout">Logout</a></li>
			</ul>
			<br />
		</div>
			<div class="floatright">
			<img src="<c:url value="/resources/img/theme/icon-profile.png" />" alt="Profile Picture" />
		</div>
	</div>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
	<a href="/auth/report/my-reports">Log in</a>
</sec:authorize>