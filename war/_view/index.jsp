<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>our game site</title>
		<style type="text/css">
		body {
		background-color: brown;
		
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
		border-color: darkred;
		margin: 10px;
		
		}
		
		.gametitle{
		background-color: brown;
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
		border-color: darkred;
		background-color: darkred;
		
		}
		#banner{
		margin-right:300px;
		cursor: pointer;
		min-width:1000px;
		height: 80px;
		 font-size: 400%;
		
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
		background-color: grey;
		}
		
		#chattext{
		position: absolute;
		bottom: 140px;
		width: 300px;
		top:24px;
		background-color: lightgrey;
		overflow: scroll;
		}
		
		
		.chatentry{
		margin-left:16px;
		white-space:pre-wrap;
		}
		.chatheader{
		text-decoration-color: orange;
		color: #cc5200;
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
		
		<!-- end chat style -->
		
		
	
		
		
		#tbd{
		float: down;
		margin-left:160px;
		margin-right:300px;
		
		}
		</style>
		<script type="text/javascript">
		var chakvisable= true;
		var acountoptionsvisible = false;
		var loginvisable = false;
		var ussing  = "${ user.username    }"
		var numposts;
		var chat;
		
		
		
		
		function togglechat(){
			
			if(chakvisable){
				document.getElementById("chatwindow").style.width = "0px";
				document.getElementById("showchatbutt").style.visibility ="visible";
				document.getElementById("featuredgames").style.marginRight="0px";
				chakvisable=false;
			}
			else{
				document.getElementById("chatwindow").style.width = "300px";
				document.getElementById("showchatbutt").style.visibility ="collapse";
				document.getElementById("featuredgames").style.marginRight="300px";
				chakvisable=true;
			}
			
			
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
		function logout(){
			
			
			
			
		}
		
		
		
		
		
		
		function toggleacountoptions(){
			
			if(acountoptionsvisible){
				document.getElementById("acountopt").style.visibility="visible";
				document.getElementById("acountoptions").style.top="-200px";
				//document.getElementById("cancleacountopt").style.visibility="hidden";
				acountoptionsvisible=false;
				document.getElementById("loginpost").method="get";
				
				
				
				
				 if(!${ user.isguest    }){
					 document.getElementById("login").innerHTML = "logout";
					 document.getElementById("login").onclick="logout()"
				 }
				 else{
					 document.getElementById("login").innerHTML = "login";
					 document.getElementById("login").onclick="logclick()"
					 
				 }
				
			}
			else{
			document.getElementById("acountopt").style.visibility="hidden";
			document.getElementById("acountoptions").style.top="0px";
			//document.getElementById("cancleacountopt").style.visibility="visible";
			acountoptionsvisible=true;
			}
			document.getElementById("loginpost").method = "post";
		}
		function post(){
			
			 var urlEncodedData = "";
			var urlEncodedDataPairs = [];
			var text = document.getElementById("chattextarea").value;
			document.getElementById("chattextarea").value='';
			
			urlEncodedDataPairs.push(encodeURIComponent("usreing") + '=' + encodeURIComponent(ussing));
			urlEncodedDataPairs.push(encodeURIComponent("chatinputtext") + '=' + encodeURIComponent(text));
			
			
			 urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');
			 
			 
			 
			var xmlreq = new XMLHttpRequest();
			xmlreq.open("post", "index");
			xmlreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xmlreq.send(urlEncodedData);
			
			xmlreq.onload=function(){
				if (this.status==200){
					
					refreshchat();
				}
			}
			
			
			
			
		}
		
		function get(){
			var xmlreq = new XMLHttpRequest(); 
			xmlreq.open('get','index'+ encodeURIComponent(ussing),true);
			xmlreq.send();
			
			xmlreq.onload=function(){
				if (this.status==200){
					
				
				}
			}
			refreshchat();
			 
			
		}
		
		
		
		
		
		
		
		
		
		
		 //Window.sessionStorage.setItem('using', "${ user.username    }");
	</script>
	</head>

	<body body onLoad="refreshchat()">
	
	
		
		
		
		
			<a>
				<img id="acountopt" onclick="toggleacountoptions()" src="img/loginicon.png"  />
			
			</a>
			
			
			<div class="smallroundcorners" id= "acountoptions">
			<img id="userphoto" onclick="toggleacountoptions()" src="img/largeloginicon.png"  />
				<button id="login"  onclick="logclick()" >login</button>
				<form action="${pageContext.servletContext.contextPath}/userinfo" method="get">
					<input type="Submit" name="submit" value="userinfo">
				</form>
			
			<!--<button id="cancleacountopt"  onclick="toggleacountoptions()" >close acount options</button>  -->	
			
				
			</div>
		
		
		
		<div id="pageoptions">
		
			<button id="showchatbutt" onclick="togglechat() ">__</button>
	
			
			
		
		 </div>
		 
		 <!-- start chat html -->
		 
		 
		<div id="chatwindow">
			<div id="chatoptions">
			<button id="hidechatbutt" onclick="togglechat() ">__</button>
			<button id="refresfchatbut" onclick="post() ">refresh</button>
			
			
				
			</div>
			
			<div id="chattext">
			
		
			<script>
			function refreshchat(){
				
				
				numposts=${ chatlength };
				chat=${chatposts};
				
				console.log(chat);
				console.log("${ user.username    }");
				console.log(numposts);
				  var count = Object.keys(chat).length;
				  console.log(count);
				
				 document.getElementById("chattext").innerHTML = "";
				
			
				var toAdd = document.createDocumentFragment();
				for(var i=0; i < numposts; i++){
				   var newDiv = document.createElement('div');
				   var newHr = document.createElement('hr');
				   var newP = document.createElement('p');
				   var time= new Date();
				   var now = time.getTime();

				   
				   var posttext=chat[i].post;
				   var username=chat[i].username;
				   username+=" ";
				   var posttime=chat[i].milstime;
				   now-=posttime;
				   if(now<60000){
					   now=now/1000
					   now= Math.floor(now);
					   username+=now;
					   username+=" seconds agao"  
				   }
				   else if(now<3600000){
					   now=now/60000
					   now= Math.floor(now);
					   username+=now;
					   username+=" minutes agao"
				   }
				   else if(now<86400000){
					   now=now/3600000
					   now= Math.floor(now);
					   username+=now;
					   username+=" hours agao"
				   }
				   else if(now<(86400000*365)) {
					   now=now/86400000;
					   now= Math.floor(now);
					   username+=now;
					   username+=" days agao"
				   }
				   else {
					   now=now/(86400000*365)
					   now= Math.floor(now);
					   username+=now;
					   username+=" years agao"  
				   }
				
				   newDiv.id = 'r'+i;
				   newDiv.className = 'chatentry';
				   newP.className='chatheader';
				   newDiv.innerHTML = posttext;
				   newP.innerHTML = username;
				   toAdd.appendChild(newP);
				   toAdd.appendChild(newDiv);
				   toAdd.appendChild(newHr);
				}
				
				document.getElementById("chattext").appendChild(toAdd);
				
				
				var element = document.getElementById("chattext");
				element.scrollTop = element.scrollHeight;
			}
			
			setInterval(refreshchat(),1000);
			</script>
			
			</div>
			
			
			<div id="chatinput">
				<p>add comment</p>
				<form action="${pageContext.servletContext.contextPath}/index" method="post">
					<textarea class="smallroundcorners" name="chatinputtext" rows="5" cols="38" id="chattextarea" > </textarea>
				
				
					<button>Send Meeeeeee!</button>
				</form>
				<button onclick="post() ">Send Me!</button>
				
				
				
				
			</div>
			
		 </div>
		
		<!--end chat html -->
		
		
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
		
		
		
		
			
	
		<div id="featuredgames"> 
		
			<div class= "gamedisplay">
				<img src="img/giphy.gif" />
	
				<div class="gametitle">
					game1
				</div>
	
			</div>
			
			<div class= "gamedisplay">
				<img src="img/giphy.gif" />
	
				<div class="gametitle">
					game2
				</div>
	
			</div>
			
			<div class= "gamedisplay">
				<img src="img/giphy.gif"  />
					
				<div class="gametitle">
					game3
				</div>

			</div>
			
			
			<div class= "gamedisplay">
				<img src="img/giphy.gif"  />
					
				<div class="gametitle">
					game4
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
					<td><input  id="uzer" type="text" name="username" size="20" class="smallroundcorners" value="${ model.username     }" /></td>
					</tr>
					<tr>
						<td class="label">Password:</td>
					<td><input id="pass" type="password" name="password" size="20" class="smallroundcorners" value="${  model.password   }" /></td>
                    
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

