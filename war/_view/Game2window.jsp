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
		
		<script> 
			
		images = new Array();
		images[0] = null;
		images[1] = new Image();
		images[1].src = "img/CardImages/HeartA.png";
		images[2] = new Image();
		images[2].src = "img/CardImages/Heart2.png";
		images[3] = new Image();
		images[3].src = "img/CardImages/Heart3.png";
		images[4] = new Image();
		images[4].src = "img/CardImages/Heart4.png";
		images[5] = new Image();
		images[5].src = "img/CardImages/Heart5.png";
		images[6] = new Image();
		images[6].src = "img/CardImages/Heart6.png";
		images[7] = new Image();
		images[7].src = "img/CardImages/Heart7.png";
		images[8] = new Image();
		images[8].src = "img/CardImages/Heart8.png";
		images[9] = new Image();
		images[9].src = "img/CardImages/Heart9.png";
		images[10] = new Image();
		images[10].src = "img/CardImages/Heart10.png";
		images[11] = new Image();
		images[11].src = "img/CardImages/HeartJ.png";
		images[12] = new Image();
		images[12].src = "img/CardImages/HeartQ.png";
		images[13] = new Image();
		images[13].src = "img/CardImages/HeartK.png";
		
		images[14] = new Image();
		images[14].src = "img/CardImages/DiamondA.png";
		images[15] = new Image();
		images[15].src = "img/CardImages/Diamond2.png";
		images[16] = new Image();
		images[16].src = "img/CardImages/Diamond3.png";
		images[17] = new Image();
		images[17].src = "img/CardImages/Diamond4.png";
		images[18] = new Image();
		images[18].src = "img/CardImages/Diamond5.png";
		images[19] = new Image();
		images[19].src = "img/CardImages/Diamond6.png";
		images[20] = new Image();
		images[20].src = "img/CardImages/Diamond7.png";
		images[21] = new Image();
		images[21].src = "img/CardImages/Diamond8.png";
		images[22] = new Image();
		images[22].src = "img/CardImages/Diamond9.png";
		images[23] = new Image();
		images[23].src = "img/CardImages/Diamond10.png";
		images[24] = new Image();
		images[24].src = "img/CardImages/DiamondJ.png";
		images[25] = new Image();
		images[25].src = "img/CardImages/DiamondQ.png";
		images[26] = new Image();
		images[26].src = "img/CardImages/DiamondK.png";
		
		images[27] = new Image();
		images[27].src = "img/CardImages/SpadeA.png";
		images[28] = new Image();
		images[28].src = "img/CardImages/Spade2.png";
		images[29] = new Image();
		images[29].src = "img/CardImages/Spade3.png";
		images[30] = new Image();
		images[30].src = "img/CardImages/Spade4.png";
		images[31] = new Image();
		images[31].src = "img/CardImages/Spade5.png";
		images[32] = new Image();
		images[32].src = "img/CardImages/Spade6.png";
		images[33] = new Image();
		images[33].src = "img/CardImages/Spade7.png";
		images[34] = new Image();
		images[34].src = "img/CardImages/Spade8.png";
		images[35] = new Image();
		images[35].src = "img/CardImages/Spade9.png";
		images[36] = new Image();
		images[36].src = "img/CardImages/Spade10.png";
		images[37] = new Image();
		images[37].src = "img/CardImages/SpadeJ.png";
		images[38] = new Image();
		images[38].src = "img/CardImages/SpadeQ.png";
		images[39] = new Image();
		images[39].src = "img/CardImages/SpadeK.png";
		
		images[40] = new Image();
		images[40].src = "img/CardImages/ClubA.png";
		images[41] = new Image();
		images[41].src = "img/CardImages/Club2.png";
		images[42] = new Image();
		images[42].src = "img/CardImages/Club3.png";
		images[43] = new Image();
		images[43].src = "img/CardImages/Club4.png";
		images[44] = new Image();
		images[44].src = "img/CardImages/Club5.png";
		images[45] = new Image();
		images[45].src = "img/CardImages/Club6.png";
		images[46] = new Image();
		images[46].src = "img/CardImages/Club7.png";
		images[47] = new Image();
		images[47].src = "img/CardImages/Club8.png";
		images[48] = new Image();
		images[48].src = "img/CardImages/Club9.png";
		images[49] = new Image();
		images[49].src = "img/CardImages/Club10.png";
		images[50] = new Image();
		images[50].src = "img/CardImages/ClubJ.png";
		images[51] = new Image();
		images[51].src = "img/CardImages/ClubQ.png";
		images[52] = new Image();
		images[52].src = "img/CardImages/ClubK.png";
		
		
			function getCardImage(index){
				
				
				return image[index];
				
			}	
			
			function changeImage(){
				var userCardIndex = "${userCardIndex}";
				console.log(images[userCardIndex]);
				//document.getElementById("userCard").src = images[userCardIndex].src;
			}
			
		</script>
		
	</head>

	<body onLoad = "changeImage()">
	
	
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
			<li>
				<form action="${pageContext.servletContext.contextPath}/Game3window" method="get">
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
						<a id="userCard"></a><img width = "50" height="75">
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