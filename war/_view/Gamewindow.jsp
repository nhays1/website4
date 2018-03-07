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
		
		td.label {
			text-align: right;
		}
		body {
		background-color: brown;
		
		}
		
		#allgames{
		float:left;
		width:150px;
		height: 1000px;
		border: 3px solid;
		border-color: darkred;
		background-color: darkred;
		
		}
		#banner{
		margin-right:300px;
		
		min-width:1000px;
		height: 80px;
		 font-size: 400%;
		
		}
		#gamecontent{
			padding:5px;
		margin-left:160px;
		
		height: 600px;
		max-widht: 600px;
		border: 3px solid blue;
		
		
		
		
		}
		
		</style>
	</head>

	<body>
	
	<!-- once the chat is flushed out it will go here -->
	
		
		
		
		
		<div id="banner">
			this is the title of our website (img)
			
		</div>
		
		<div id ="allgames">
		<ul>
			<li>game1</li>
			<li>game2</li>
			<li>game3</li>
		</ul>	
		
		
		 </div>
	
		<div id=gamecontent>
		
		
		
		
		
		</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/Gamewindow" method="post">
			<table>
				<tr>
					<td class="label">First number:</td>
					<td><input type="text" name="first" size="12" value="${model.first}" /></td>
				</tr>
				<tr>
					<td class="label">Second number:</td>
					<td><input type="text" name="second" size="12" value="${model.second}" /></td>
                    
				</tr>
                <tr>
					<td class="label">third number:</td>
					<td><input type="text" name="third" size="12" value="${model.third}" /></td>
				</tr>
				<tr>
					<td class="label">Result:</td>
					<td>${result}</td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="gammmamam!">
		</form>
         <hr/>
        <hr/>
        <form action="${pageContext.servletContext.contextPath}/index" method="get">
        <input type="Submit" name="submit" value="home">
            <form/>
	</body>
</html>