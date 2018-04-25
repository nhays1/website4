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
		
		<script type="text/javascript"> 
			
		
			images = new Array();
			images[0] = null;
			images[1] = "img/CardImages/HeartA.png";
			images[2] = "img/CardImages/Heart2.png";
			images[3] = "img/CardImages/Heart3.png";
			images[4] = "img/CardImages/Heart4.png";
			images[5] = "img/CardImages/Heart5.png";
			images[6] = "img/CardImages/Heart6.png";
			images[7] = "img/CardImages/Heart7.png";
			images[8] = "img/CardImages/Heart8.png";
			images[9] = "img/CardImages/Heart9.png";
			images[10] = "img/CardImages/Heart10.png";
			images[11] = "img/CardImages/HeartJ.png";
			images[12] = "img/CardImages/HeartQ.png";
			images[13] = "img/CardImages/HeartK.png";
			
			images[14] = "img/CardImages/DiamondA.png";
			images[15] = "img/CardImages/Diamond2.png";
			images[16] = "img/CardImages/Diamond3.png";
			images[17] = "img/CardImages/Diamond4.png";
			images[18] = "img/CardImages/Diamond5.png";
			images[19] = "img/CardImages/Diamond6.png";
			images[20] = "img/CardImages/Diamond7.png";
			images[21] = "img/CardImages/Diamond8.png";
			images[22] = "img/CardImages/Diamond9.png";
			images[23] = "img/CardImages/Diamond10.png";
			images[24] = "img/CardImages/DiamondJ.png";
			images[25] = "img/CardImages/DiamondQ.png";
			images[26] = "img/CardImages/DiamondK.png";
			
			images[27] = "img/CardImages/SpadeA.png";
			images[28] = "img/CardImages/Spade2.png";
			images[29] = "img/CardImages/Spade3.png";
			images[30] = "img/CardImages/Spade4.png";
			images[31] = "img/CardImages/Spade5.png";
			images[32] = "img/CardImages/Spade6.png";
			images[33] = "img/CardImages/Spade7.png";
			images[34] = "img/CardImages/Spade8.png";
			images[35] = "img/CardImages/Spade9.png";
			images[36] = "img/CardImages/Spade10.png";
			images[37] = "img/CardImages/SpadeJ.png";
			images[38] = "img/CardImages/SpadeQ.png";
			images[39] = "img/CardImages/SpadeK.png";
			
			images[40] = "img/CardImages/ClubA.png";
			images[41] = "img/CardImages/Club2.png";
			images[42] = "img/CardImages/Club3.png";
			images[43] = "img/CardImages/Club4.png";
			images[44] = "img/CardImages/Club5.png";
			images[45] = "img/CardImages/Club6.png";
			images[46] = "img/CardImages/Club7.png";
			images[47] = "img/CardImages/Club8.png";
			images[48] = "img/CardImages/Club9.png";
			images[49] = "img/CardImages/Club10.png";
			images[50] = "img/CardImages/ClubJ.png";
			images[51] = "img/CardImages/ClubQ.png";
			images[52] = "img/CardImages/ClubK.png";
			
			
		</script>
		
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
		
		<form action="${pageContext.servletContext.contextPath}/Game2window" method="post">
			<table>
				<tr>
					<td class="label">Bet Amount:</td>
					<td><input type="text" class="betInput" name="userBet" size="12" value="${userBet}" /></td>
				</tr>
				<tr>
					<tr>
					<form action="choice">
  					<input  type="submit" class="buttonHigher" name="choice" value="higher"><br>
  					 <input  type="submit" class="buttonLower"name="choice" value="lower"><br>
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