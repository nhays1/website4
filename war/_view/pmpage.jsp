<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>acount information</title>
		<style type="text/css">
	
		body {
		background-color: #909090;
		
		}
		p{
			padding:0;
			margin:0;
		}
		#homebut{
			top:200px;
		left:550px;
		position:absolute;
		
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
		color:  #c0c0c0;
		background-color: #040404;
		 z-index: 2;
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
		 z-index: 3;
		
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
		
		
		#pmcontainer{
		position: absolute;
		width:500px;
		
		}
		#pmotheruser{
		width: 500px;
		height:40px;
		
		background-color: lightgrey;
		}
		#pmchats{
		//position: absolute;
		
		width: 500px;
		top:40px;
		background-color: #303030;
		
		}
		#pminput{
		//position: absolute;
		width: 500px;
		
		background-color: white;
		//bottom: 0px;
		
		}
		.mypost{
		width: 300px;
		position: relative;
		left:185px;
		background-color: #0000cc;
		padding:2px;
		margin: 5px;
		border-radius: 10px;
		color: #f0f0f0;
		
		}
		.otherpost{
		width: 300px;
		position: relative;
		right:0px;
		background-color: #b0b0b0;
		padding:2px;
		margin: 5px;
		border-radius: 10px;
		color: black;
		}
		.pmentry{
		margin-left:16px;
		white-space:pre-wrap;
		//color: #c0c0c0;
		color: inherit;
		}
		.pmheader{
		//color: #fc5200;
		color: inherit;
		white-space:pre-wrap;
		
		}
		
		#pmtextarea{
		
		
		}
		
		
		
		</style>
		<script src="_view/chat.js"></script>
		
		<script>
		var thisid='${user.userid}';
		var chatpm;
		var pmcont;
		var currentpm='${pmchaid}'
		console.log(currentpm);
		
		function init(){
			post();
			postpm();
		}
		
		function postpm(){
			
			 var urlEncodedData = "";
				var urlEncodedDataPairs = [];
				var text = document.getElementById("pmtextarea").value;
				var chatedc;
				document.getElementById("pmtextarea").value='';
				
				urlEncodedDataPairs.push(encodeURIComponent("pminput") + '=' + encodeURIComponent(text));
				urlEncodedDataPairs.push(encodeURIComponent("numberofpost") + '=' + encodeURIComponent(pmcont));
				urlEncodedDataPairs.push(encodeURIComponent("getmoreposts") + '=' + encodeURIComponent(false));
				urlEncodedDataPairs.push(encodeURIComponent("isasync") + '=' + encodeURIComponent(true));
				urlEncodedDataPairs.push(encodeURIComponent("pmcahtid") + '=' + encodeURIComponent(currentpm));
				
				 urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');
				 
				 
				 
				 var xmlreq = new XMLHttpRequest();
				 xmlreq.open("post", "pmpage");
					xmlreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
					xmlreq.send(urlEncodedData);
				 
				 
					xmlreq.onreadystatechange = function() {
				        if (this.readyState == 4 && this.status == 200) {
				        	var chattts=this.responseText;
				        	
				        	console.log(chattts);
				        	//if(chattts.charAt(0)=='['){
				        		chatedc= JSON.parse(this.responseText);
								console.log(chatedc)
					        	refreshpm(chatedc);
				        	//}
				       }
				    };

			
			
			
		}
		
		
		
		
		function refreshpm(chats){
			
			
			
			if(chats!=null){
				chatpm=chats;
			}
			
			console.log(chatpm);
		
		
			pmcont = Object.keys(chatpm).length;
			  console.log(pmcont);
			
			
			
			
			document.getElementById("pmchats").innerHTML = "";
			var toAdd = document.createDocumentFragment();
			
			var neHr = document.createElement('hr');
			var newbutt = document.createElement('button');
			newbutt.innerHTML="show more posts";
			newbutt.id="morepostsbutt";
			 toAdd.appendChild(newbutt);
			 toAdd.appendChild(neHr);
			
			
			
			
			for(var i=0; i < chatpm.length; i++){
				var containerdiv=document.createElement('div');
			   var newDiv = document.createElement('p');
			   var newHr = document.createElement('hr');
			   var newP = document.createElement('p');
			   var time= new Date();
			   var now = time.getTime();

			   
			   var posttext=chatpm[i].post;
			   //console.log(posttext);
			   var username;
			   newP.id=chatpm[i].usid;
			   username=" ";
			   var posttime=chatpm[i].mit;
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
			
			   //newDiv.id = 'r'+i;
			   
			   newDiv.className = 'pmentry';
			   newP.className='pmheader';
			   if((chatpm[i].usid)!=thisid){
				   containerdiv.className='otherpost';
			   }
			   else{
				   containerdiv.className='mypost';
			   }
			   newP.onclick = function() { 
				   otheruseroptions(event, true ,this.id);
			   };
			   newDiv.innerHTML = posttext;
			   newP.innerHTML = username;
			   containerdiv.appendChild(newP);
			   containerdiv.appendChild(newDiv);
			  
			   toAdd.appendChild(containerdiv);
			  // toAdd.appendChild(newHr);
			}
			//toAdd.appendChild(previousposts);
			document.getElementById("pmchats").appendChild(toAdd);
			//document.getElementById("chattext").appendChild(previousposts);
			
			
			
			
			//element.scrollTop = element.scrollHeight;
			
			
		}
		
		
		</script>
		
		
		
		
	</head>

	<body onload=" init()">
	
	<div id="bannerholder" onclick="home()">
			
			
		</div>
		<div id="banner" onclick="home()">
			this is the title of our website (img)
		
		</div>

	
	
	
	
	
		
	
	
		 
		 <!-- start chat html /////////////////////////-->
		 
		 
		<div id="chatwindow">
			<div id="chatoptions">
			
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
	
	<div id="pmcontainer">
		<div id="pmotheruser">
		<button id="refresfchatbut" onclick="postpm()  ">refresh</button>
		</div>
		<div id="pmchats"></div>
		<div id="pminput">
			<p>add comment</p>
			<textarea class="smallroundcorners" name="chatinputtext" rows="5" cols="38" id="pmtextarea" > </textarea>
			<button onclick="postpm()">post</button>
		</div>
	
	
	
	
	
	
	
	
	</div>
	
	
	
	
			
	
		
	
	
	
	
	 <form id="homebut" action="${pageContext.servletContext.contextPath}/index" method="get">
	 	<input type="Submit"  value="home">
	 </form>
	





	
	
	
	
	
	
	
	
	
	</body>
</html>