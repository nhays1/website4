<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>acount information</title>
		<style type="text/css">
	
		#allgames{
		float:left;
		width:150px;
		height: 1000px;
		border: 3px solid;
		border-color: darkred;
		background-color: darkred;
		
		}

		body {
		background-color: brown;
		
		}
		
		.smallroundcorners{
		border-radius: 5px;
		
		}
		
		.medroundcorners{
		border-radius: 15px 5px 15px 5px;
		 
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
		height:150px;
		width: 150px;
		
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
			#userstats{
		
		min-width:300px;
		
		padding:5px;
		margin-left:160px;
		margin-right:300px;
		transition: ease-in-out, margin .4s  ease-in-out;
		}
		
		.information{
		margin:5px;
		margin-left:160px;
		margin-right:600px;
			
			font-size: 200%;
			padding: 5px;
			border: solid red;
		
		}
		.data{
		padding:0;
			margin:0;
		padding-left:40px;
		font-size: 125%;
		
		}
		
		
		
		</style>
		<script>
		//if(user.)
		
			
			
			
			var acountoptionsvisible = false;
			var loginvisable = false;
			var ussing  = "${ user.username    }"
			
			
			
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
			function toggleacountoptions(){
			
			if(acountoptionsvisible){
				document.getElementById("acountopt").style.visibility="visible";
				document.getElementById("acountoptions").style.top="-200px";
				//document.getElementById("cancleacountopt").style.visibility="hidden";
				acountoptionsvisible=false;
				document.getElementById("loginpost").method="get";
				
				
				
				
				// if(){
				//	 document.getElementById("login").innerHTML = "logout";
				//	 document.getElementById("login").onclick="logout()"
				// }
				// else{
				//	 document.getElementById("login").innerHTML = "login";
				//	 document.getElementById("login").onclick="logclick()"
					 
				// }
				
			}
			else{
			document.getElementById("acountopt").style.visibility="hidden";
			document.getElementById("acountoptions").style.top="0px";
			//document.getElementById("cancleacountopt").style.visibility="visible";
			acountoptionsvisible=true;
			}
			document.getElementById("loginpost").method = "post";
		}
		function sendimg(){
			//file reader taken from https://stackoverflow.com/questions/22087076/how-to-make-a-simple-image-upload-using-javascript-html
			console.log(document.getElementById("fileimg").value);
			var pre= document.querySelector('img');
			var file= document.querySelector('input[type=file]').files[0];
			var reader=new FileReader();
			reader.onloadend=function(){
				try{
					//console.log(reader.result);
					pre.src=reader.result;
					postimg(reader.result);
				}
				catch(e){
					alert("file not compatable");
				}
			}
			
			//console.log(reader);
			//console.log(file);
			console.log(pre);
			try{
				reader.readAsDataURL(file);
			}
			catch(e){
				alert("file not compatable");
			}
		}
		function postimg(img){
			//console.log(img);
			 var urlEncodedData = "";
			var urlEncodedDataPairs = [];
			var logout = true;
			
			
			urlEncodedDataPairs.push(encodeURIComponent("img") + '=' + encodeURIComponent(img));
			
			
			 urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');
			
			
			var xmlreq = new XMLHttpRequest();
			xmlreq.open("post", "userinfo");
			xmlreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xmlreq.send(urlEncodedData);
			
			xmlreq.onload=function(){
				if (this.status==200){
					//isguest= JSON.parse(this.responseText);
					
				}
			}
		
		}
		function getimg(){
			var urlEncodedData = "";
			var urlEncodedDataPairs = [];
			var logout = true;
			
			
			urlEncodedDataPairs.push(encodeURIComponent("getimg") + '=' + encodeURIComponent(true));
			
			
			 urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');
			
			
			var xmlreq = new XMLHttpRequest();
			xmlreq.open("post", "user_asyinc");
			xmlreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xmlreq.send(urlEncodedData);
			
			xmlreq.onload=function(){
				if (this.status==200){
					//isguest= JSON.parse(this.responseText);userphoto
					console.log(this.responseText);
					document.getElementById("userphoto").src=this.responseText;
				}
			}
			
			
		}
		
		</script>
		
		
		
		
		
		
	</head>

	<body >
	
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
	
	
	
	
	
	<div id="userstats"> 
		
			<div class= "userphoto">
				<div class="smallroundcorners" id= "acountoptions">
				<img id="userphoto" onclick="toggleacountoptions()" src="img/largeloginicon.png"  />
				<button id="login"  onclick="logclick()" >login</button>
			
			
				
				</div>
			</div>
			
			
			
		</div>
	
		
	
	
	
	
	
	
	
	
			<a>
				<img id="acountopt" onclick="toggleacountoptions()" src="img/loginicon.png"  />
			
			</a>
	
	
		<div class="information" >
		username
		<p class="data">${ user.username    }</p> 
		
		</div>
	
		<div class="information" >
		email adress
		<p  class="data">${ user.email    }</p> 
		</div>
	
		<div class="information" id="coins">
		coins
		<p class="data">${ user.coins    }</p> 
		</div>
	
	
	
	
	
	
	 <form action="${pageContext.servletContext.contextPath}/index" method="get">
	 	<input type="Submit" name="chatsubmit" value="home">
	 </form>
	





	<div id="loginoverlay"  onclick="logclick()" >
        
        
        </div>
        
        <div class="medroundcorners" id="loginwindow">
			<form id="loginpost" action="${pageContext.servletContext.contextPath}/userinfo" method="post">
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
	
		 <input id="fileimg" type="file" value="file">
		<button onclick="sendimg()"> send</button>
	
		<button onclick="getimg()"> get img</button>
	
	
	
	
	</body>
</html>