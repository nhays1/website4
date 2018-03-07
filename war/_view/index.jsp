<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>our game site</title>
		<style type="text/css">
		body {
		background-color: grey;
		
		}
		
		p{
			padding:0;
			margin:0;
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
		
		min-width:1000px;
		height: 80px;
		 font-size: 400%;
		
		}
		#log{
		float: right;
		margin-right:260px;
		transition: ease-in-out, margin .4s  ease-in-out;
		}
		
		#acountopt{
		float: right;
		
		}
		#acountoptions{
		float: right;
		height: 0px;
		width:200px;
		background-color: black;
		transition: ease-in-out, height .4s  ease-in-out;
		}
		
		#loginwindow{
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
		
		
		
		<!-- start chat style -->
		
		#chatbuble{
		
		position: fixed;
		right:0;
		top:0;
		}
		
		#showchatbutt{
		float: right;
		visibility: hidden;
		}
		#hidechatbutt{
		float: right;
		
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
		function hidechat(){
			document.getElementById("chatwindow").style.width = "0px";
			document.getElementById("showchatbutt").style.visibility ="visible";
			document.getElementById("featuredgames").style.marginRight="0px";
			document.getElementById("log").style.marginRight="0px";
			
				}
	
		function showchat(){
				document.getElementById("chatwindow").style.width = "300px";
				document.getElementById("showchatbutt").style.visibility ="hidden";
				document.getElementById("featuredgames").style.marginRight="300px";
				document.getElementById("log").style.marginRight="260px";
				
		}
		
		function logclick(){
			
			document.getElementById("loginwindow").style.visibility ="visible";
			document.getElementById("loginoverlay").style.visibility ="visible";
			
			
		}
		function canclelog(){
			document.getElementById("uzer").value="";
			document.getElementById("pass").value="";
			document.getElementById("loginwindow").style.visibility ="hidden";
			document.getElementById("loginoverlay").style.visibility ="hidden";
		}
		function acount(){
			document.getElementById("acountopt").style.visibility="hidden";
			document.getElementById("acountoptions").style.visibility="visible";
			document.getElementById("acountoptions").style.height="200px";
			document.getElementById("cancleacountopt").style.visibility="visible";
		}
		function closeacount(){
			document.getElementById("acountopt").style.visibility="visible";
		document.getElementById("acountoptions").style.visibility="hidden";
			document.getElementById("acountoptions").style.height="0px";
			document.getElementById("cancleacountopt").style.visibility="hidden";
		}
	
	</script>
	</head>

	<body>
	
		
		
		
		<!-- start chat html -->
		<div id="chatbuble">
		
			<button id="showchatbutt" onclick="showchat() ">__</button>
			<button id="log"  onclick="logclick()" >login</button>
			<button id="acountopt"  onclick="acount()" >acount options</button>
			<div id= "acountoptions">
				
				<button id="cancleacountopt"  onclick="closeacount()" >acount options</button>
			</div>
			
			
		
		 </div>
		<div id="chatwindow">
			<div id="chatoptions">
			<button id="hidechatbutt" onclick="hidechat() ">__</button>
				
				
			</div>
			
			<div id="chattext">
			<script>
			
			
			
			
			</script>
				<ul>
					<li>comcmsvjsiojvsvi</li>
					<hr/>
					<li><script> document.write("some stuf");    </script></li>
					<hr/>
					<li>gaafaefazfefgrafs e3</li>
					<hr/>
					<li>gaafaefazfefgrafs e3</li>
					<hr/>
					<li>gaafaefazfefgrafs e3</li>
					<hr/>
					<li>gaafaefazfefgrafs e3</li>
					<hr/>
					<li>gaafaefazfefgrafs e3</li>
					<hr/>
					<li>gaafaefazfefgrafs e3</li>
					
					<hr/>
					<li>gaafaefazfefgrafs e3</li>
					
					<hr/>
					<li>gaafaefazfefgrafs e3</li>
					<hr/>
					<li>gaafaefazfefgrafs e3</li>
					<hr/>
					<li>gaafaefazfefgrafs e3</li>
					<hr/>
					<li>gaafaefazfefgrafs e3</li>
					<hr/>
					<li>ersgascafarfghe bawuhf hahew fheb fhebauebfu ebffhe bfuhbafa  h f beyb eushb hbrsuhb rbsihbvbsvj nuse u nu</li>
					<hr/>
					<li>ersgascafarfghe bawuhf hahew fheb fhebauebfu ebffhe bfuhbafa  h f beyb eushb hbrsuhb rbsihbvbsvj nuse u nu</li>
					<hr/>
					<li>ersgascafarfghe bawuhf hahew fheb fhebauebfu ebffhe bfuhbafa  h f beyb eushb hbrsuhb rbsihbvbsvj nuse u nu</li>
					<hr/>
					<li>ersgascafarfghe bawuhf hahew fheb fhebauebfu ebffhe bfuhbafa  h f beyb eushb hbrsuhb rbsihbvbsvj nuse u nu</li>
					<hr/>
					<li>ersgascafarfghe bawuhf hahew fheb fhebauebfu ebffhe bfuhbafa  h f beyb eushb hbrsuhb rbsihbvbsvj nuse u nu</li>
				</ul>	
			</div>
			
			
			<div id="chatinput">
				<p>add comment</p>
				<form action="${pageContext.servletContext.contextPath}/index" method="get">
					<textarea rows="6" cols="38"></textarea>
				
				
					<input type="Submit" name="submit" value="post">
				</form>
			
				
				
				
				
			</div>
			
		 </div>
		
		<!--end chat html -->
		
		
		<div id="banner">
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
			
		</div>
	
	
		
		
		
	
	<div id="tbd">
	
		<div>
               <form action="${pageContext.servletContext.contextPath}/multiplyNumbers" method="get">

			<input type="Submit" name="submit" value="Multiply Numbers!">
		</form>
        
        </div>
            <hr/>
        <div>
        <form action="${pageContext.servletContext.contextPath}/Gamewindow" method="get">
	
			<input type="Submit" name="submit" value="Add Numbers!">
		</form>
        
        
        </div>
            <hr/>
        <div>
        <form action="${pageContext.servletContext.contextPath}/guessingGame" method="get">
			
				<input name="startGame" type="submit" value="guessing game" />
			
		
			</form>

        </div>
        
        </div>
        
        
        <form action="${pageContext.servletContext.contextPath}/userinfo" method="get">

			<input type="Submit" name="submit" value="userinfo">
		</form>
        
        <div id="loginoverlay"  onclick="canclelog()" >
        
        
        </div>
        
        <div id="loginwindow">
			<form action="${pageContext.servletContext.contextPath}/index" method="get">
				<table>
					<tr>
						<td class="label">username:</td>
					<td><input  id="uzer" type="text" name="username" size="20" value="${ model.first     }" /></td>
					</tr>
					<tr>
						<td class="label">Password:</td>
					<td><input id="pass" type="password" name="password" size="20" value="${  model.first   }" /></td>
                    
					</tr>
                
				</table>
				<input type="Submit" name="submit" value="login">
			</form>
			
			  <form action="${pageContext.servletContext.contextPath}/newuser" method="get">
			
				<input name="newuser" type="submit" value="new user" />
			
		
			</form>
			
			<button id="canclelog"  onclick="canclelog()" >cancle</button>
		</div>
	</body>
</html>

