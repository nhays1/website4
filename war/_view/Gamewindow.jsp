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
		margin-right:300px;
	
		height: 600px;
		min-width: 600px;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	</body>
</html>