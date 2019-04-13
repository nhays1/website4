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
			//top:270px;
		
		margin-right:300px;
		
		height: 600px;
		min-width: 600px;
		border: 3px solid blue;
		}
		
		#container{
		position: absolute;
		left:0px;
		right:0px:
		top:0px;
		height: 600px;
		width: inherit;
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
		var myid,mynumber;
		var otherid;
		var connection;
		try{
			otherid=${otherid};
			//myid=&{mine};
		}
		catch(e){
			console.log(e);
		}
		var urlEncodedData = "";
		var urlEncodedDataPairs = [];

		urlEncodedDataPairs.push(encodeURIComponent("gtmyid") + '=' + encodeURIComponent(true));
		
		urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');
		
		var xmlreq = new XMLHttpRequest();
		xmlreq.open("post", "pongplay");
		xmlreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xmlreq.send(urlEncodedData);
			
		xmlreq.onload=function(){
			if (this.status==200){
				myid=JSON.parse(this.responseText);
				console.log(myid);
				console.log(this.responseText);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				//connection = new WebSocket('ws://192.168.43.86:8080/website/Game4scocket');//!!!!! CHANGE THIS FOR DEMNSTRATION !!!!!!!!!!!!!!!!
				connection = new WebSocket('ws://192.168.43.86:8008/website/Game4scocket');
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				connection.onopen = function () {
					  connection.send("myid :"+myid); // Send the message 'Ping' to the server
					  connection.send("othr :"+otherid);
					};

					// Log errors
					connection.onerror = function (error) {
					  console.log('WebSocket Error ' + error);
					};

					// Log messages from the server
					connection.onmessage = function (e) {
						var message=e.data;
						if(message.substring(0, 5)=="plnum"){
							mynumber=JSON.parse(message.substring(6));
						}
						if(message.substring(0, 5)=="ball "){
							tochangeball=JSON.parse(message.substring(6));
						}
						if(message.substring(0, 5)=="otpad"){
							tochangeotherpad=JSON.parse(message.substring(6));
						}
					  //console.log('Server: '+message);
					
					};
			}
		}
		
		
		function gamestateup(){
			
			connection.send('ping :   ');
			
			
			//var socket=new WebSocket("ws://localhost:8080/website/Game4scocket");
			
			
			
		}
		
		
		</script>
	</head>

	<body >
	
	
	
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
			<div id="container"><br /><br /><br /><br /><br />Generating world...</div>
			
			<script type="text/javascript" src="threejs-master/build/three.js"></script>

		
			<script type="text/javascript" src="threejs-master/mygame/js/controls/FlyControls.js"></script>
			<script type="text/javascript" src="threejs-master/mygame/js/Detector.js"></script>
			<script type="text/javascript" src="threejs-master/mygame/js/libs/stats.min.js"></script>
			<script type="text/javascript" src="threejs-master/mygame/3djspongmulty.js"> </script>
	
			<button id="restart" onclick="restart()">restart</button>
		</div>
		
	
	
	
	
		<button onclick="gamestateup()">host</button>
	
	
	
	
		 <form action="${pageContext.servletContext.contextPath}/index" method="get">
	 	<input type="Submit"  name="chatsubmit" value="home">
	 </form>
	
	<div id="gamescores" class="highscorecontainer">
	
	
	
	</div>
	
	<div id="userscores" class="highscorecontainer">
	
	
	
	</div>
	
	
	
	
	
	
	
	</body>
</html>