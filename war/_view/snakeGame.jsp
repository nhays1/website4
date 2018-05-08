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
		canvas {
   		border:1px solid #d3d3d3;
    	background-color: #f1f1f1;
    	position: absolute;
			left:0px;
			top:270;
		}
		
		#allgames{
		float:left;
		width:150px;
		height: 1000px;
		border: 3px solid;
		border-color: darkred;
		background-color: darkred;
		
		}
		.tmp{
			top:0px;
		left:0px;
		position:fixed;
		}
		.gamescoreentry{
		
		margin:5px;
		font-size: 200%;
		padding: 5px;
		border: solid red;
		}
		.highscorecontainer{
		
		width:300px;
		
		}
		#banner{
		
		margin-right:200px;
		cursor: pointer;
		min-width:1000px;
		height: 100px;
		width:100%;
		 font-size: 400%;
		background-color: #e60000;
		}
		#restart{
		position: absolute;
		height: 40px;
		width: 170px;
		top:50%;
		left:50%;
		visibility: hidden;
		z-index: 3;
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
			position: relative;
			left:160px;
			top:270;
		
		margin-right:678px;
	
		height: 602px;
		min-width: 600px;
		border: 3px solid blue;
		}
		
		#gamescores{
		margin-left:10px;
		float: left;
		}
		#userscores{
		margin-left:500px;
		
		
		}
		
		
		<!-- start chat style -->
		#hidechatbutt{
		position:fixed;
		right:0px;
		
		
		}
		
		#chatwindow{
		visibility: visible;
		position: fixed;
		background-color: black;
		
		width: 300px;
		height: 100%;
		right:0;
		top:0;
		transition: ease-in-out, width .4s  ease-in-out;
		
		
		}
		
		#chatoptions{
		
		width: 300px;
		height:24px;
		background-color: lightgrey;
		}
		
		#chattabs{
		width: 300px;
		height: 36px;
		background-color: grey;
		}
		.swithchchat{
		background-color: inherit;
		
		border: none;
		outline: none;
		cursor: pointer;
		padding: 7px 8px;
		transition: 0.3s;
		font-size: 17px; 
		
		}
		.swithchchatactive {
		
		color:#c0c0c0;
		border: none;
		outline: none;
		cursor: pointer;
		padding: 7px 8px;
		transition: 0.3s;
		font-size: 17px;
		background-color: #303030;
		}
		
		.swithchchat:hover {
		background-color: #dddddd;
		}
		
		#newchatoverlay{
		height: 100%;
		width:100%;
		background-color:rgba(128,128,128,0.5);
		position: fixed;
		left:0;
		top:0;
		visibility: hidden;
		transition: 0.3s;
		 z-index: 3;
		}
		
		#newchatwindow{
		padding: 20px;
		heiht: 300px;
		width: 300px;
		background-color: white;
		position: fixed;
		right:50%;
		top:50%;
		visibility: hidden;
		 z-index: 4;
		}
		
		#chatuseropt{
		padding: 20px;
		heiht: 300px;
		width: 100px;
		background-color: white;
		position: fixed;
		right:300px;
		//top:50%;
		visibility: hidden;
		 z-index: 4;
		
		}
		#chatuseroptionsoverlay{
		height: 100%;
		width:100%;
		background-color:rgba(128,128,128,0);
		position: fixed;
		left:0;
		top:0;
		visibility: hidden;
		transition: 0.3s;
		 z-index: 3;
		
		
		}
		
		
		#userchats{
		padding: 4px;
		
		float: right;
		background-color: grey;
		display: none;
    	position: fixed;
    	top:56px;
    	right:10px;
    	 z-index: 3;
		}
		
		
		#usercahtscontainer{
		 display: inline;
		 padding: 2px;
		}
		
		#swithcuserchat{
    	display: block;
		}
		
		#chattext{
		position: absolute;
		bottom: 140px;
		width: 300px;
		top:60px;
		background-color: #303030;
		overflow: scroll;
		}
		
		
		.chatentry{
		margin-left:16px;
		white-space:pre-wrap;
		color: #c0c0c0;
		}
		.chatheader{
		cursor: pointer;
		color: #fc5200;
		white-space:pre-wrap;
		
		}
		
		#chatinput{
		position: absolute;
		width: 300px;
		height:140px;
		background-color: white;
		bottom: 0px;
		
		}
		
		#comment{
		word-break: break-word;
		overflow: scroll;
		}
		
		.hide{
		visibility: hidden;
		position: fixed;
		top:-64px;
    	right:-64px;
		}
		<!-- end chat style -->
		</style>
		
		<script type="text/javascript">
		var score=0;
		var gamescore;
		var userscores;
		
		
		
		function updatescores(){
			document.getElementById("gamescores").innerHTML = "";
			document.getElementById("userscores").innerHTML = "";
			
			var toAdd = document.createDocumentFragment();
			 var newDiv = document.createElement('div');
			 var newHr = document.createElement('hr');
			 newDiv.className = 'gamescoreentry';
			 newDiv.innerHTML ="overall high scores";
			 toAdd.appendChild(newDiv);
			 toAdd.appendChild(newHr);
			
			for(var i=0; i < gamescore.length; i++){
					newDiv = document.createElement('div');
				  	newHr = document.createElement('hr');
				   
				   var score=gamescore[i].value;
				   var username=gamescore[i].key;
				   username+=" |-| ";
				   username+=score;
				  
				   //newDiv.id = 'r'+i;
				   newDiv.className = 'gamescoreentry';
				  
				   newDiv.innerHTML = username;
				  
				   toAdd.appendChild(newDiv);
				   toAdd.appendChild(newHr);
				}
				//toAdd.appendChild(previousposts);
				document.getElementById("gamescores").appendChild(toAdd);
				
				// start user score
				
				var toAdd = document.createDocumentFragment();
				var toAdd = document.createDocumentFragment();
				 var newDiv = document.createElement('div');
				 var newHr = document.createElement('hr');
				 newDiv.className = 'gamescoreentry';
				 newDiv.innerHTML ="your high scores";
				 toAdd.appendChild(newDiv);
				 toAdd.appendChild(newHr);
				 var x=1;
				for(var i=1; i < userscores.length+1; i++){
					if (userscores[i-1]!=0){
						var newDiv = document.createElement('div');
						   var newHr = document.createElement('hr');
						 
						   var score=""+x+" |-| "
						   
						   score+=userscores[i-1];
						 
						 
						  
						   //newDiv.id = 'r'+i;
						   newDiv.className = 'gamescoreentry';
						  
						   newDiv.innerHTML = score;
						  
						   toAdd.appendChild(newDiv);
						   toAdd.appendChild(newHr);
						   x++;
					}
				  
				}
				//toAdd.appendChild(previousposts);
				document.getElementById("userscores").appendChild(toAdd);
		}
		
		
		
		function postscore(postscores){
			
			 var urlEncodedData = "";
			var urlEncodedDataPairs = [];
			//var text = document.getElementById("chattextarea").value;
			var chatedc;
			//document.getElementById("chattextarea").value='';
			
			//urlEncodedDataPairs.push(encodeURIComponent("chatinputtext") + '=' + encodeURIComponent(text));
			//urlEncodedDataPairs.push(encodeURIComponent("numberofpost") + '=' + encodeURIComponent(count));
			urlEncodedDataPairs.push(encodeURIComponent("score") + '=' + encodeURIComponent(score));
			urlEncodedDataPairs.push(encodeURIComponent("forscores") + '=' + encodeURIComponent(postscores));
			urlEncodedDataPairs.push(encodeURIComponent("getmoreposts") + '=' + encodeURIComponent(false));
			urlEncodedDataPairs.push(encodeURIComponent("isasync") + '=' + encodeURIComponent(true));
			
			 urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');
			 
			 
			 
			 var xmlreq = new XMLHttpRequest();
			 xmlreq.open("post", "snakeGame");
				xmlreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				xmlreq.send(urlEncodedData);
			 
			 
				xmlreq.onreadystatechange = function() {
			        if (this.readyState == 4 && this.status == 200) {
			        //	console.log(this.responseText);
			        	var resp=this.responseText;
			        	var objstr1,objstr2;
			        	for(var i=0;i<resp.length;i++){
			        		if(resp.charAt(i)==']'){
			        			objstr1=resp.substring(0, i+1);
			        			objstr2=resp.substring(i+2,resp.length-1 );
			        			//console.log(objstr1);
			        			//console.log(objstr2);
			        			break;
			        		}
			        			
			        	}
			        	gamescore=JSON.parse(objstr1);
			    		userscores= JSON.parse(objstr2);
			    		updatescores();
			        	
			       }
			    };

		}
	
		
		
	
	
	
	
	
	
		</script>
		
	</head>
	
	

	
	<!-- start chat html /////////////////////////-->
	 
	
	<!--end chat html -->
	
	
	
	
	
	
	
	
	
	
	
	
	
	<div id="bannerholder" onclick="home()">
	
	
	</div>
	<div id="banner" onclick="home()">
		Snake Game!
	
	</div>
	
	<div id ="allgames">
	<ul>
	<li> 
	<form action="${pageContext.servletContext.contextPath}/Gamewindow" method="get">
		<input name="newuser" type="submit" value="Coin Flip" />
	</form>
</li>
<li>
<form action="${pageContext.servletContext.contextPath}/Game2window" method="get">
		<input name="newuser" type="submit" value="Card Game" />
	</form>
</li>
<li><form action="${pageContext.servletContext.contextPath}/Game3window" method="get">
		<input name="newuser" type="submit" value="Shooter" />
	</form>
</li>
<li><form action="${pageContext.servletContext.contextPath}/Game4window" method="get">
		<input name="newuser" type="submit" value="3D Pong" />
	</form>
</li>
<li><form action="${pageContext.servletContext.contextPath}/snakeGame" method="get">
		<input name="newuser" type="submit" value="Snake Game" />
	</form>
	</ul>	
	
	
	 </div>

	
	 
	 
	 
	 
	 <form action="${pageContext.servletContext.contextPath}/index" method="get">
	 	<input type="Submit"  name="chatsubmit" value="home">
	 </form>
	
	<button onclick="postscore(5)">post score</button>
	 
 
	 
	 
<div id=gamecontent align = "center">	 
<canvas id="content" width="1200" height="600"></canvas>

<script>
window.onload=function() {
	canv=document.getElementById("content");
	ctx=canv.getContext("2d");
	document.addEventListener("keydown",keyPush);
	document.addEventListener("keyup",keyRelease);
	//var interval = 50;
	
	setInterval(gameStart,75);
}



var snakeX = 20; //Snake starting X
var snakeY = 10; //Snake starting Y
var grid = 30;
var tiles = 30;
var foodX = Math.floor(Math.random()*40); //Initial Position of food X
var foodY = Math.floor(Math.random()*20); //Initial Position of food Y
var xv = 0; //X Velocity
var yv = 0; //Y Velocity
var trail=[];
var tail = 5;
var score = 0;
var foodEaten = 0; //Tracks how amount of food eaten
var gameOver = false;
var r = false; //Right
var l = false; //Left
var d = false; //Down
var u = false; //Up
var c = Math.floor(Math.random() * 8); //Food Color
var sc = 0;//Snake Color
var pause = false;
var gStart = false;
var walls = false;

function gameStart() {
	if(pause == false){
	snakeX+=xv;
	snakeY+=yv;
	}
	
	if(snakeX<0 && walls == true) {
		snakeX= 40;
	}
	
	if(snakeX<0 && walls == false) {
		gameOver = true;
	}
	
	/////
	if(snakeX>40 && walls == true) {
		snakeX= 0;
	}
	
	if(snakeX>40 && walls == false) {
		gameOver = true;
	}
	/////
	if(snakeY<0 && walls == true) {
		snakeY= 19;
	}
	
	if(snakeY<0 && walls == false) {
		gameOver = true;
	}
	/////
	if(snakeY>19 && walls == true) {
		snakeY= 0;
	}
	
	if(snakeY>19 && walls == false) {
		gameOver = true;
	}
	
	
	ctx.fillStyle="grey";
	ctx.fillRect(0,0,canv.width,canv.height);

	
	ctx.fillStyle="black";
	
	for(var i=0;i<trail.length;i++) {
		
		ctx.fillRect(trail[i].x*grid,trail[i].y*grid,20,20);
		
		if(pause == false){
		if(trail[i].x==snakeX && trail[i].y==snakeY) {
			tail = 5;
			foodEaten = 0;			
			score = 0;			
			
			
		}
		}
	}
	
	if(pause == false){
	trail.push({x:snakeX,y:snakeY});
	}
	if(pause == false){
	while(trail.length>tail) {
	trail.shift();
	}
	}

	//Snake eats food
	if(foodX==snakeX && foodY==snakeY) {
		tail+=2;
		score += (foodEaten * 10) + 100;
		foodEaten++;
		//Generates location of next food
		foodX=Math.floor(Math.random()*40);
		foodY=Math.floor(Math.random()*20);
		c = Math.floor( Math.random() * 8);
	
		
	}
	
	
	//random color for food
	if(c == 0){
		ctx.fillStyle="red";
	}
	if(c == 1){
		ctx.fillStyle="blue";
	}
	if(c == 2){
		ctx.fillStyle="lime";
	}
	if(c == 3){
		ctx.fillStyle="orange";
	}
	if(c == 4){
		ctx.fillStyle="white";
	}
	if(c == 5){
		ctx.fillStyle="purple";
	}
	if(c == 6){
		ctx.fillStyle="pink";
	}
	if(c == 7){
		ctx.fillStyle="gold";
	}

	//Draws food
	ctx.fillRect(foodX*grid,foodY*grid,20,20);
	
	//Draws Score
	ctx.fillStyle="black";
	ctx.font="30px Arial";
	ctx.fillText("Score: "+ score, 5, 25);
	ctx.fillText("Food Consumed: "+foodEaten, 540, 25);
	//Draws pause in the middle of the screen when the game is paused
	if(pause == true && gameOver == false){
		ctx.font=("50px Arial");
		ctx.fillText("PAUSE", 540, 290);
	}	
	
	//Starting message
	if(gStart == false){
		ctx.fillText("Press W, A, S, D, or any Arrow Key to start", 330, 200);
		ctx.fillText("Use V to move through walls, P to toggle Pause", 300, 250);
	}
	if(gameOver == true){
		xv = 0; yv = 0;
		trail = [];
		ctx.fillText("Game Over!",540, 290);
		ctx.fillText("Press R to Restart", 500, 320);
	}
}





function keyPush(event) {
	switch(event.keyCode) {
			
		//ARROW KEY CASES	
		case 37: //A, left
			if(r == false){
			xv=-1;yv=0;
			l = true;
			r = false;
			d = false;
			u = false;
			gStart = true;
			}
			break;
			
		case 40: //W, up
			if(d == false){
			xv=0;yv=1;
			l = false;
			r = false;
			d = false;
			u = true;
			gStart = true;
			}
			break;
			
		case 39: //D, right
			if(l == false){
			xv=1;yv=0;
			l = false;
			r = true;
			d = false;
			u = false;
			gStart = true;
			}
			
			break;
			
		case 38: //S, down
			if(u == false){
			xv=0;yv=-1;
			l = false;
			r = false;
			d = true;
			u = false;
			gStart = true;
			}
			break;	
		
		//WASD CASES	
		case 65: //A, left
			if(r == false){
			xv=-1;yv=0;
			l = true;
			r = false;
			d = false;
			u = false;
			gStart = true;
			}
			break;
			
		case 83: //W, up
			if(d == false){
			xv=0;yv=1;
			l = false;
			r = false;
			d = false;
			u = true;
			gStart = true;
			}
			break;
			
		case 68: //D, right
			if(l == false){
			xv=1;yv=0;
			l = false;
			r = true;
			d = false;
			u = false;
			gStart = true;
			}
			
			break;
			
		case 87: //S, down
			if(u == false){
			xv=0;yv=-1;
			l = false;
			r = false;
			d = true;
			u = false;
			gStart = true;
			}
			break;
			
		case 80: //p, Pause
			
			if(pause == false){
				
				pause = true;
			}
			else{
				
				pause = false;
			}				
			break;
			
		case 86: //v
			walls = true;
			
			break;
			
		case 82: //r
			if(gameOver == true){
			gameOver = false;
			snakeX = 20; //Snake starting X
			snakeY = 10; //Snake starting Y
			grid = 30;
			tiles = 30;
			foodX = Math.floor(Math.random()*40); //Initial Position of food X
			foodY = Math.floor(Math.random()*20); //Initial Position of food Y
			xv = 0; //X Velocity
			yv = 0; //Y Velocity
			trail=[];
			tail = 5;
			score = 0;
			foodEaten = 0; //Tracks how amount of food eaten
			gameOver = false;
			r = false; //Right
			l = false; //Left
			d = false; //Down
			u = false; //Up
			c = Math.floor(Math.random() * 8); //Food Color
			sc = 0;//Snake Color
			pause = false;
			gStart = false;
			walls = false;
			}
			break;
			
	}
}

function keyRelease(event) {
	
	switch(event.keyCode)  {
	
	case 86: //v
		walls = false;
		break;
	}
}

</script>
</div>


<div id="gamescores" class="highscorecontainer">



</div>

<div id="userscores" class="highscorecontainer">



</div>

	<script>
	var gamescore=${gemescores};
	var userscores=${userscores};
	
	updatescores();

	</script>