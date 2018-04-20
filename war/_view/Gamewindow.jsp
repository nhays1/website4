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
		width:300px;
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
		margin-left:360px;
		margin-right:300px;
	
		height: 600px;
		min-width: 600px;
		border: 3px solid gray;
		background-image: url(img/pokerFelt.jpg);
		background-size: fill;
		
		
		}
		
		.radButton{
			top-margin: 24px;
			width: 24px;
			text-align: left;
			color: white;
			font-family: "Arial", sans-serif;
			font-size: 24pt;
			text-shadow: 3px 2px black;
		}
		
		.label{
			text-align: left;
			color: white;
			font-family: "Arial", sans-serif;
			font-size: 24pt;
			text-shadow: 3px 2px black;
		}
		
		.betInput{
			border: 2px solid black;
   			border-radius: 4px;
		}
		
		.buttonHigher {
			background-color: #4CAF50; /* Green */
		    border: none;
		    color: white;
		    padding: 15px 32px;
		    text-align: center;
		    text-decoration: none;
		    display: inline-block;
		    font-size: 16px;
		    margin-top: 10px;
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
			<li> 
				<form action="${pageContext.servletContext.contextPath}/Gamewindow" method="get">
					<input name="newuser" class="buttonHigher" type="submit" value="Coin Flip" />
				</form>
			</li>
			<li>
			<form action="${pageContext.servletContext.contextPath}/Game2window" method="get">
					<input name="newuser" class="buttonHigher" type="submit" value="What's your guess?" />
				</form>
			</li>
			<li><form action="${pageContext.servletContext.contextPath}/Game3window" method="get">
					<input name="newuser" class="buttonHigher" type="submit" value="Yeet Shooter" />
				</form>
			</li>
		</ul>	
		<%= session.getAttribute( "userid" ) %>
		
		 </div>
	
		<div id=gamecontent>
		
		<form action="${pageContext.servletContext.contextPath}/Gamewindow" method="post">
			<table>
				<tr>
					<td class="label">Bet Amount:</td>
					<td><input type="text" class="betInput" name="userBet" size="12" value="${userBet}" /></td>
				</tr>
				<tr>
					<form action="choice">
	  					<input type="radio" class="radButton" name="choice" value="heads"> Heads<br>
	  					<input type="radio" class="radButton" name="choice" value="tails"> Tails<br>
  					</form>
				</tr>
				</tr>
				<tr>
					<td><input type="submit" class="buttonHigher" name="flip" size="12" value="FLIP" /></td>
				</tr>
				<tr>
					<td class="label">User Chose:</td>
					<td class="label">${choice}</td>
				</tr>
				<tr>
					<td class="label">Result:</td>
					<td class="label">${result}</td>
				</tr>
				<tr>
					<td class="label">User:</td>
					<td class="label">${transaction}</td>
				</tr>
			</table>
		</form>

		</div>



		 <form action="${pageContext.servletContext.contextPath}/index" method="get">
	 	<input type="Submit" name="chatsubmit" value="home">
	 </form>
	
	
	
	
	
	
	
	
	</body>
</html>