<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Home</title>
</head>
<body>
<h3 style="color:green;"> Work Board : ${workboard.workBoardName} </h3>

<%-- <c:forEach items="${people}" var="v_person">
	<a href="edit?id=${v_person.id}">${v_person.id} -
	${v_person.firstName} ${v_person.lastName}</a>
	<br />
</c:forEach> --%>

<br/>
<br/>
<br/>
<br/>

<h4> Upload Data File</h4>
<form method="post" action="views/fileupload.jsp" name="upform"  
enctype="multipart/form-data">  
  <table width="60%" border="0" cellspacing="1" cellpadding="1"  
align="center" class="style1">  
    <tr>  
      <td align="left"><b>Select a file to upload :</b></td>  
    </tr>  
    <tr>  
      <td align="left">  
        <input type="file" name="uploadfile" size="50">  
        </td>  
    </tr>  
    <tr>  
      <td align="left">  
  <input type="hidden" name="todo" value="upload">  
        <input type="submit" name="Submit" value="Upload">  
        <input type="reset" name="Reset" value="Cancel">  
        </td>  
    </tr>  
  </table>  
</form>  


<%-- 
<form:form method="POST" modelAttribute="workboard" style="padding:8px" >
 
  <table align="left">
    <tr>
        <td><form:input path="WorkBoardName"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Upload"/>
        </td>
    </tr>
</table> 
</form:form> 

--%>



<br/>
<br/>
<br/>
<%-- <a href="createwb?firstName=${workboard.person.firstName}" style="font-size: large;"> Create a Work Board </a> --%>
</body>
</html>