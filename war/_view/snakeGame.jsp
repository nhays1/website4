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
		
		margin-right:300px;
	
		height: 600px;
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
		
		
		
		function post(postscores){
			
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
	
	<div id="bannerholder" onclick="home()">
	
	
	</div>
	<div id="banner" onclick="home()">
		this is the title of our website (img)
	
	</div>
	
	<div id ="allgames">
	<ul>
		<li>Coin Flip</li>
		<li>Card Game</li>
		<li>Shooter</li>
		<li>Pong</li>
		<li>Snake</li>
	</ul>	
	
	
	 </div>

	
	 
	 
	 
	 
	 <form action="${pageContext.servletContext.contextPath}/index" method="get">
	 	<input type="Submit"  name="chatsubmit" value="home">
	 </form>
	
	<div id="gamescores" class="highscorecontainer">
	
	
	
	</div>
	
	<div id="userscores" class="highscorecontainer">
	
	
	
	</div>
	
		<script>
		var gamescore=${gemescores};
		var userscores=${userscores};
		
		updatescores();
	
		</script>
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
<div id=gamecontent>	 
<canvas id="content" width="600" height="600"></canvas>

<script>
window.onload=function() {
	canv=document.getElementById("content");
	ctx=canv.getContext("2d");
	document.addEventListener("keydown",keyPush);
	setInterval(gameStart,75);
}



var snakeX = 10;
var snakeY = 10;
var grid = 30;
var tiles = 30;
var foodX = 5
var foodY = 5
var xv = 0;
var yv = 0;
var trail=[];
var tail = 5;
var score = 0;
var foodEaten = 0;
var gameOver;

function gameStart() {
	snakeX+=xv;
	snakeY+=yv;
	
	if(snakeX<0) {
		snakeX= 20-1;
	}
	if(snakeX>20-1) {
		snakeX= 0;
	}
	if(snakeY<0) {
		snakeY= 20-1;
	}
	if(snakeY>20-1) {
		snakeY= 0;
	}
	
	ctx.fillStyle="grey";
	ctx.fillRect(0,0,canv.width,canv.height);
	ctx.fillStyle="black";
	
	for(var i=0;i<trail.length;i++) {
		ctx.fillRect(trail[i].x*grid,trail[i].y*grid,20,20);
		if(trail[i].x==snakeX && trail[i].y==snakeY) {
			tail = 5;
			foodEaten = 0;
		}
	}
	trail.push({x:snakeX,y:snakeY});
	while(trail.length>tail) {
	trail.shift();
	}

	//Snake eats food
	if(foodX==snakeX && foodY==snakeY) {
		tail++;
		score += (foodEaten * 10) + 100;
		foodEaten++;
		//Generates location of next food
		foodX=Math.floor(Math.random()*20);
		foodY=Math.floor(Math.random()*20);
		
	}
	
	
	ctx.fillStyle="red";
	ctx.fillRect(foodX*grid,foodY*grid,20,20);
	
	//Draws Score
	ctx.font="30px Arial";
	ctx.fillText("Score: "+ score, 5, 25);
	
	
	
	

	
	
}


function keyPush(event) {
	switch(event.keyCode) {
		case 37: //Left Arrow
			xv=-1;yv=0;
			break;
			
		case 38: //Down Arrow
			xv=0;yv=-1;
			break;
			
		case 39: //Right Arrow
			xv=1;yv=0;
			break;
			
		case 40: //Up Arrow
			xv=0;yv=1;
			break;
			
		case 65: //A 
			xv=-1;yv=0;
			break;
			
		case 83: //W
			xv=0;yv=1;
			break;
			
		case 68: //D
			xv=1;yv=0;
			break;
			
		case 87: //S
			xv=0;yv=-1;
			break;
	}
}
</script>
</div>