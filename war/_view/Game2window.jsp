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
		top:0px;
		left:0px;
		position:fixed;
		margin-right:200px;
		cursor: pointer;
		min-width:1000px;
		height: 100px;
		width:100%;
		 font-size: 400%;
		background-color: #e60000;
		}
		
		#bannerholder{
		
		margin-bottom:10px;
		padding:0px;
		min-width:1000px;
		height: 150px;
		width:100%;
		 font-size: 400%;
		background-color: #e60000;
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
	
		
		
		
		<!-- end chat -->	
		<div id="bannerholder" onclick="home()">
			
			
		</div>
		<div id="banner" onclick="home()">
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
		
		<form action="${pageContext.servletContext.contextPath}/Gamewindow2" method="post">
			<table>
				<tr>
					<td class="label">Bet Amount:</td>
					<td><input type="text" name="userBet" size="12" value="${userBet}" /></td>
				</tr>
				<tr>
					<td class="label">Bet Amount:</td>
					<td><input type="text" name="userBet" size="12" value="${userBet}" /></td>
					
					<tr>
					<form action="choice">
  					<input type="radio" name="choice" value="higher"> Heads<br>
  					<input type="radio" name="choice" value="lower"> Tails<br>
  					</form>
					<form action="flip cards">
  					<input type="Submit" name="flip" value="flip"> Flip! <br>
  					</form>
				</tr>
					<!-- add game input here  -->
				</tr>
				</tr>
				<tr>
					<td><input type="submit" name="flip" size="12" value="FLIP" /></td>
				</tr>
				<tr>
					<td class="label">User Flipped:</td>
					<td>${userCardResult}</td>
				</tr>
				<tr>
					<td class="label">CPU Flipped:</td>
					<td>${cpuCardResult}</td>
				</tr>
				<tr>
					<td class="label">User:</td>
					<td>${transaction}</td>
				</tr>
			</table>
		</form>

		</div>

	
	
	
	
	
	
	
	
	
	
	
		 <form action="${pageContext.servletContext.contextPath}/index" method="get">
	 	<input type="Submit" name="chatsubmit" value="home">
	 </form>
	
	
	
	
	
	
	
	
	
	</body>
</html>