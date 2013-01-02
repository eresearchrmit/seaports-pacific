<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import= "org.springframework.web.servlet.tags.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="grid_12">

	<div class="pitch">
		<h3 class="lightblue">Australia's climate is changing, and it will pose challenges to the successful operation of ports over coming decades.</h3>
		<div class="pitch-link"><a href="#">Read more...</a></div>
	</div>
	
	<div class="box round first" style="text-align:center">
		<h3>Connect Now</h3>
		<div class="block">
			<input type="button" class="btn btn-blue" value="Login" onclick="location.href='/CSS/spring/login'" />
		</div>
	</div>
	<h3>OR</h3>
	<div class="box round first" style="text-align:center">
		<h3>Search for User Stories</h3>
		Search <input type="text" width="400px" /> <input type="button" class="btn btn-grey" value="Search" />
	</div>
</div>