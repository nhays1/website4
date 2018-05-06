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
		#multyplayers{
			position: relative;
			left:160px;
			//top:270px;
		
		margin-right:450px;
		//right:300px;
		height: 600px;
		min-width: 600px;
		border: 3px solid blue;
		}
		
		
		</style>
		<script type="text/javascript">
			window.onunload = function(){
			
				leavemultiplayer();
			
			}
			
			function leavemultiplayer(to){
				var urlEncodedData = "";
				var urlEncodedDataPairs = [];
	
				urlEncodedDataPairs.push(encodeURIComponent("leavemultiplayer") + '=' + encodeURIComponent(true));
				
				urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');
				
				var xmlreq = new XMLHttpRequest();
				xmlreq.open("post", "multysetup");
				xmlreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				xmlreq.send(urlEncodedData);
					
				xmlreq.onload=function(){
					if (this.status==200){
						console.log("left")
						if(to=="home"){
							document.getElementById("gohome").submit();
						}
						if(to=="play"){
							document.getElementById("goplay").submit();
						}
					}
				}
			}
		
			
			this.interval = setInterval(getmultyplayerlist,1000);
			function getmultyplayerlist(){
				var xmlreq = new XMLHttpRequest();
				xmlreq.open("post", "multysetup");
				xmlreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				xmlreq.send();
					
				xmlreq.onload=function(){
					if (this.status==200){
						//console.log(this.response);
						try{
							var obj= JSON.parse(this.responseText);
							//console.log(obj);
							makeplayerlist(obj)
						}
						catch(e){
							console.log(this.responseText);
							console.log(e);
						}
					}
				}
				
			}
			function makeplayerlist(players){
				
				
				document.getElementById("multyplayers").innerHTML = "";
				
				var toAdd = document.createDocumentFragment();
				 var newDiv = document.createElement('div');
				 var newHr = document.createElement('hr');
				 var newbut;
				 newDiv.className = 'playerentry';
				 newDiv.innerHTML ="open users";
				 toAdd.appendChild(newDiv);
				 toAdd.appendChild(newHr);
				
				for(var i=0; i < players.length; i++){
						newDiv = document.createElement('div');
					  	newHr = document.createElement('hr');
					  	newbut =document.createElement('button');
					  	
					  	
					   var userid=players[i].second;
					   var username=players[i].first;
					   newDiv.className = 'playerentry';
					   newDiv.innerHTML = username+" ";
					  

					   
					   
					   newbut =document.createElement('button');
					   newbut.innerHTML = " chalange ";
					   newbut.id=userid;
					   newbut.onclick = function() { 
						   console.log(1);
							chalangeuser(event,this.id);
					   };
					   if(players[i].third){
						   newbut.innerHTML = " accept chalange ";
						   newbut.onclick = function() { 
							   leavemultiplayer("play")
								
						   };
					   }
					   
					   newDiv.appendChild(newbut);
					   
					   toAdd.appendChild(newDiv);
					   toAdd.appendChild(newHr);
					}
					//toAdd.appendChild(previousposts);
					document.getElementById("multyplayers").appendChild(toAdd);
			}
			function chalangeuser(event, tochalange){
				var urlEncodedData = "";
				var urlEncodedDataPairs = [];
	
				urlEncodedDataPairs.push(encodeURIComponent("chalangeid") + '=' + encodeURIComponent(tochalange));
				
				urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');
				
				var xmlreq = new XMLHttpRequest();
				xmlreq.open("post", "multysetup");
				xmlreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				xmlreq.send(urlEncodedData);
					
				xmlreq.onload=function(){
					if (this.status==200){
						
						
					}
				}
				
				
			}
			
			
			
			
			function home(){
				leavemultiplayer("home")
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
	
		<div id="multyplayers">
			
		</div>
		
	
	   	
	
	
	
		 <form action="${pageContext.servletContext.contextPath}/index" method="get" id="gohome">
	 
	 </form>
		<button onclick="home()">home</button>
	
	 <form action="${pageContext.servletContext.contextPath}/pongplay" method="get" id="goplay">
	 
	 </form>
	</body>
</html>