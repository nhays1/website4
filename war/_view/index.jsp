<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>our game site</title>
		<style type="text/css">
		body {
		background-color: #909090;
		
		}
		
		p{
			padding:0;
			margin:0;
		}
		
		.smallroundcorners{
		border-radius: 5px;
		
		}
		
		.medroundcorners{
		border-radius: 15px 5px 15px 5px;
		 
		}
		
		.gamedisplay{
		display: inline-block;
		
		background-color: black;
		width: 256px;
		  
		height: 290px;
		border: 3px solid;
		border-color: #040404;
		margin: 10px;
		
		}
		
		.gametitle{
		background-color: #c0c0c0;
		width: 256px;
		height: 30px;
		
		
		}
		
		
		#featuredgames{
		
		min-width:300px;
		
		padding:5px;
		margin-left:160px;
		margin-right:300px;
		transition: ease-in-out, margin .4s  ease-in-out;
		}
		
		
		#allgames{
		float:left;
		width:150px;
		height: 1000px;
		border: 3px solid;
		border-color: #040404;
		background-color: #040404;
		
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
		 color:  #c0c0c0;
		background-color: #040404;
		}
		
		#bannerholder{
		
		margin-bottom:10px;
		padding:0px;
		min-width:1000px;
		height: 150px;
		width:100%;
		 font-size: 400%;
		background-color: #202020;
		}
		
		
		
		#acountopt{
		cursor: pointer;
		float: right;
		margin-right:300px;
		transition: ease-in-out, margin .4s  ease-in-out;
		}
		
		#acountopt{
		float: right;
		height: 50px;
		width:50px;
		}
		#userphoto{
		padding-left:25px;
		padding-right:25px;
		
		}
		#acountoptions{
		position: fixed;
		top:-200px;
		right:300px;
		height: 200px;
		width:200px;
		background-color: black;
		transition: ease-in-out, top .4s  ease-in-out;
		}
		
		#loginwindow{
		padding: 20px;
		heiht: 300px;
		width: 300px;
		background-color: white;
		position: fixed;
		right:50%;
		top:50%;
		visibility: hidden;
		}
		
		
		#canclelog{
		float: right;
		
		}
		
		#loginoverlay{
		height: 100%;
		width:100%;
		background-color:rgba(128,128,128,0.5);
		position: fixed;
		left:0;
		top:0;
		visibility: hidden;
		}
		
		#login{
		visibility: visible;
		
		
		
		
		}
		
		
		
		#pageoptions{
		
		position: fixed;
		right:0;
		top:0;
		}
		
		#showchatbutt{
		float: right;
		visibility: hidden;
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
		
		
	
		
		
		#tbd{
		float: down;
		margin-left:160px;
		margin-right:300px;
		
		}
		</style>
		<script type="text/javascript">
		var currentchat="general";
		var chakvisable= true;
		var acountoptionsvisible = false;
		var loginvisable = false;
		//var ussing  = "${ user.username    }";
		var isguest='${user.isguest}';
		
		
		var chat;
		var chatedc;
		
	    var	count =11;
		var previousheight;
		var scroleposition;
		
		function home(){
		}

		function logclick(){
			if(loginvisable){
				document.getElementById("uzer").value="";
				document.getElementById("pass").value="";
				document.getElementById("loginwindow").style.visibility ="hidden";
				document.getElementById("loginoverlay").style.visibility ="hidden";
				loginvisable= false;
			}
			else{
			document.getElementById("loginwindow").style.visibility ="visible";
			document.getElementById("loginoverlay").style.visibility ="visible";
			
			
			loginvisable=true;
			}
		}
		
		
		function switchlogbutton(){
			 if(isguest==false){
				 console.log("dsaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				 document.getElementById("login").innerHTML = "logout";
				 document.getElementById("login").onclick=function(){
					 
					 console.log("logout");
						// <!-- %= session.setAttribute( "userid",null ) %> -->
						
						 var urlEncodedData = "";
						var urlEncodedDataPairs = [];
						var logout = true;
						
						
						urlEncodedDataPairs.push(encodeURIComponent("logout") + '=' + encodeURIComponent(logout));
						urlEncodedDataPairs.push(encodeURIComponent("isasync") + '=' + encodeURIComponent(true));
						
						 urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');
						
						
						var xmlreq = new XMLHttpRequest();
						xmlreq.open("post", "index");
						xmlreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
						xmlreq.send(urlEncodedData);
						
						xmlreq.onload=function(){
							if (this.status==200){
								//isguest= JSON.parse(this.responseText);
								isguest=this.response;
								console.log("isguest");
								console.log(isguest);
								newchat(event,'general');
								getusechats();
							}
						}
					 
						toggleacountoptions();
				 }
				 
			 }
			 else{
				 document.getElementById("login").innerHTML = "login";
				 document.getElementById("login").onclick=function(){
						if(loginvisable){
							document.getElementById("uzer").value="";
							document.getElementById("pass").value="";
							document.getElementById("loginwindow").style.visibility ="hidden";
							document.getElementById("loginoverlay").style.visibility ="hidden";
							loginvisable= false;
						}
						else{
						document.getElementById("loginwindow").style.visibility ="visible";
						document.getElementById("loginoverlay").style.visibility ="visible";
						
						
						loginvisable=true;
						}
					}
				 
			 }
			 
			if(acountoptionsvisible){
				document.getElementById("acountopt").style.visibility="visible";
				document.getElementById("acountoptions").style.top="-200px";
				//document.getElementById("cancleacountopt").style.visibility="hidden";
				acountoptionsvisible=false;
				document.getElementById("loginpost").method="get";

			}
			else{
				document.getElementById("acountopt").style.visibility="hidden";
				document.getElementById("acountoptions").style.top="0px";
				//document.getElementById("cancleacountopt").style.visibility="visible";
				acountoptionsvisible=true;
			}
			document.getElementById("loginpost").method = "post";
		}
			
		
	
		function toggleacountoptions(){
			checkisguest();
			console.log(isguest);
		}
		
		
		function checkisguest(){

			 
			 var xmlreq = new XMLHttpRequest();
			 xmlreq.open("post", "user_asyinc");
				xmlreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				xmlreq.send();
			 
			 
				xmlreq.onreadystatechange = function() {
			        if (this.readyState == 4 && this.status == 200) {
			        	//console.log(this.responseText);
			        	isguest= JSON.parse(this.responseText);
						//console.log(isguest)
			        	switchlogbutton();
			        	
			       }
			    };

		}
		 
		
		
	</script>
	<script src="_view/chat.js"></script>
	</head>

	<body onload="post()">
	
	
	
	<div id="bannerholder" onclick="home()">
			
			
		</div>
		<div id="banner" onclick="home()">
			this is the title of our website (img)
			<a>
				<img id="acountopt" onclick="toggleacountoptions()" src="img/loginicon.png"  />
			
			</a>
		</div>
		

			<div class="smallroundcorners" id= "acountoptions">
			<img id="userphoto" onclick="toggleacountoptions()" src="img/largeloginicon.png"  />
			
				<button id="login"  onclick="logclick()" >login</button>
				
				<form action="${pageContext.servletContext.contextPath}/userinfo" method="get">
					<input type="Submit" name="submit" value="userinfo">
				</form>
			
			
			
				
			</div>
		
		
		
		<div id="pageoptions">
		
			<button id="showchatbutt" onclick="togglechat() ">__</button>
	
			
			
		
		 </div>
		 
		 <!-- start chat html /////////////////////////-->
		 
		 
		<div id="chatwindow">
			<div id="chatoptions">
			<button id="hidechatbutt" onclick="togglechat() ">__</button>
			<!-- <button id="refresfchatbut" onclick="post() ">refresh</button> -->
			<button id="refresfchatbut" onclick="post()  ">refresh</button>
			
			
				
			</div>
		<div id="chattabs" >
				<button class="swithchchatactive" onclick="newchat(event,'general')  ">general</button>
				<button class="swithchchat" onclick="newchat(event,'towerdef1')  ">towerdef1</button>
				<div id="usercahtscontainer" onmouseover="toggleuserchats(true);" onmouseout="toggleuserchats(false);">
					<button class="swithchchat"  id ="showuserchats">your chats</button>
					<div id="userchats">
					<!-- <button id="swithcuserchat" class=" swithchchat" onclick="newchat(event,'general')  ">chat 1</button>-->
					
					</div>
					
				</div>
				
			</div>
			
			<div id="chattext">
			
		
		
			</div>
			
			
			<div id="chatinput">
				<p>add comment</p>
				
					<textarea class="smallroundcorners" name="chatinputtext" rows="5" cols="38" id="chattextarea" > </textarea>
				
				
				
				
				<button onclick="post()">post</button>
				
				
				
				
			</div>
			
		 </div><!-- end fixed chat window -->
		  <div id="newchatoverlay"  onclick="newwuserchat(false)" >
        
        
        </div>
        
        
        <div class="medroundcorners" id="newchatwindow">
			
				<table>
					<tr>
						<td class="label">name of chat:</td>
					<td><input id="inputchatname" type="text" placeholder="Enter chat name" name="spec_chatname" size="20" class="smallroundcorners"  /></td>
					</tr>
					
                
				</table>
				<button   onclick="newwuserchat(false)" >cancle</button>

			<button   onclick="createuserchat(false)" >create chat</button>
			<button   onclick="createuserchat(true)" >add  chat</button>
		</div>
		
		
		 <div id="chatuseroptionsoverlay"  onclick="otheruseroptions(false)" >        </div>
        
        
        <div class="medroundcorners" id="chatuseropt">
			<button   onclick="otheruseroptions(false)" >cancle</button>
			
			<form action="${pageContext.servletContext.contextPath}/pmpage" method="post">
				<!--  -->
					
					<input name="newuser" type="submit" value="privatte mesage" />
					<input class="hide" id="pmid" type="text" name="pmid" value="0" >
				</form>
			<!--<button   onclick="" >blacklist (WIP)</button>-->
		</div>
		
		
		<!--end chat html -->
		
		
		
		
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
			</li>
		</ul>	
		<%= session.getAttribute( "userid" ) %>
		
		 </div>
		
		
		
		
			
	
		<div id="featuredgames"> 
		
			<div class= "gamedisplay">
			<form action="${pageContext.servletContext.contextPath}/Gamewindow" method="get">
				
					<input type="image" src="img/game3snap.PNG" alt="Submit" onmouseover="this.src='img/giphy.gif'" onmouseout="this.src='img/coinflip.PNG'"style="width:256px;height:256px;"  > 
				<div class="gametitle">
					<input name="newuser" type="submit" value="Coin Flip" />
				</div>
			</form>
			</div>
			
			<div class= "gamedisplay">
			<form action="${pageContext.servletContext.contextPath}/Game2window" method="get">
				
					<input type="image" src="img/game3snap.PNG" alt="Submit" onmouseover="this.src='img/giphy.gif'" onmouseout="this.src='cardgame.PNG'"style="width:256px;height:256px;"  > 
				<div class="gametitle">
					<input name="newuser" type="submit" value="Card Game" />
				</div>
			</form>
			</div>
			
			<div class= "gamedisplay">
			<form action="${pageContext.servletContext.contextPath}/Game3window" method="get">
				
					<input type="image" src="img/game3snap.PNG" alt="Submit" onmouseover="this.src='img/giphy.gif'" onmouseout="this.src='img/game3snap.PNG'"style="width:256px;height:256px;"  > 
				<div class="gametitle">
					<input name="newuser" type="submit" value="Yeet Shooter" />
				</div>
			</form>
			</div>
			
			
			<div class= "gamedisplay">
			<form action="${pageContext.servletContext.contextPath}/Game4window" method="get">
				
					<input type="image" src="img/game3snap.PNG" alt="Submit" onmouseover="this.src='img/giphy.gif'" onmouseout="this.src='img/game3snap.PNG'"style="width:256px;height:256px;"  > 
				<div class="gametitle">
					<input name="newuser" type="submit" value="3D Pong" />
				</div>
			</form>
			</div>

			<div class= "gamedisplay">
			<form action="${pageContext.servletContext.contextPath}/snakeGame" method="get">
				
					<input type="image" src="img/game3snap.PNG" alt="Submit" onmouseover="this.src='img/giphy.gif'" onmouseout="this.src='img/game5.PNG'"style="width:256px;height:256px;"  > 
				<div class="gametitle">
					<input name="newuser" type="submit" value="Snake Game" />
				</div>
			</form>
			</div>


			</div>
			
		</div>

        <div id="loginoverlay"  onclick="logclick()" >
        
        
        </div>
         
        
        <div class="medroundcorners" id="loginwindow">
			<form id="loginpost" action="${pageContext.servletContext.contextPath}/index" method="post">
				<table>
					<tr>
						<td class="label">username:</td>
					<td><input  id="uzer" type="text" placeholder="Enter Username" name="username" size="20" class="smallroundcorners" value="${ model.username     }" /></td>
					</tr>
					<tr>
						<td class="label">Password:</td>
					<td><input id="pass" type="password" placeholder="Enter password" name="password" size="20" class="smallroundcorners" value="${  model.password   }" /></td>
                    
					</tr>
                
				</table>
				<input type="Submit" name="loginsubmit" value="login">
			</form>
			
			  <form action="${pageContext.servletContext.contextPath}/newuser" method="get">
			
				<input name="newuser" type="submit" value="new user" />
			
		
			</form>
			
			<button id="canclelog"  onclick="logclick()" >cancle</button>
		</div>
	</body>
</html>

