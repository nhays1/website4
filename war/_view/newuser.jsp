<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Game</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
	
		body {
		background-color: brown;
		
		}
		
		
		</style>
	</head>

	<body>
		
	<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
	
	<form style="text-align:center" action="${pageContext.servletContext.contextPath}/newuser" method="post">
  	
  	Username:<br>
  	<input type="text" name="username" value="${username}"/><br>
  	
	
  	Password:<br> 
  	<input type="text" name="password" value="${password}"/>
	<br>
	
	
	Email:<br>   
	<input type="text" name="email" value="${email}"/>
	<br>
	<br>
	
	
	<input type="submit" name="submit" value="Create Account">
	</form>
	
	

	
	
	</body>
</html>