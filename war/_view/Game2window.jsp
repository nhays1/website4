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
			text-align: left;
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
		
		.button{
		 	background-color: #f44336; /* Red */
		    border: none;
		    color: white;
		    padding: 15px 35px;
		    text-align: center;
		    text-decoration: none;
		    display: inline-block;
		    font-size: 16px;
		    margin-bottom: 10px;
		    justify-content: center;
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
		.buttonLower {
		    background-color: #f44336; /* Red */
		    border: none;
		    color: white;
		    padding: 15px 35px;
		    text-align: center;
		    text-decoration: none;
		    display: inline-block;
		    font-size: 16px;
		    margin-bottom: 10px;
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
					<input name="newuser" class="buttonHigher" type="submit" value="Yeet shooter" />
				</form>
			</li>
		</ul>	
		<%= session.getAttribute( "userid" ) %>
		
		 </div>
	
		<div id=gamecontent>
		
		<form action="${pageContext.servletContext.contextPath}/Gamewindow2" method="post">
			<table>
				<tr>
					<td class="label">Bet Amount:</td>
					<td><input type="text" class="betInput" name="userBet" size="12" value="${userBet}" /></td>
				</tr>
				<tr>
					<tr>
					<form action="choice">
  					<input  type="submit" class="buttonHigher" name="higher" value="higher"><br>
  					<input  type="submit" class="buttonLower"name="lower" value="lower"><br>
				</tr>
					<!-- add game input here  -->
				</tr>
				</tr>
				<div>
					<tr>
						<td class="label">User Flipped:</td>
						<td>${userCardResult}</td>
					</tr>
				</div>
				<div>
					<tr>
						<td class="label">CPU's next Card:</td>
						<td>${cpuCardResult}</td>
					</tr>
				</div>
				<div>
					<tr>
						<td class="label">User:</td>
						<td>${transaction}</td>
					</tr>
				</div>
			</table>
		</form>

		</div>

	
	
	
	
	
	
	
	
	
	
		
	 </form>
	
	
	
	
		<div>
			<form action="${pageContext.servletContext.contextPath}/index" method="get">
		 	<input type="Submit" class="button" name="chatsubmit" value="home">
	 	</div>
	
	
	
	
	</body>
</html>