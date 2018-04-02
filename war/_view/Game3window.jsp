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
			
		margin-left:160px;
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
	</head>

	<body onload="startGame()">
	
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
	
			<script>
			//javascrit variables do not have any type they are defined only by what you put in them
			
			//therefor the x in the next class can be a int, float, double, boolean, or whatever you pass into the constructor
			
			//example class in javascript(js)
			class Point {
				  constructor(x, y) {//constructor  js classes/functions store variables as this.variablename
				    this.x = x;
				    this.y = y;
				  }

				   distance( p2) {//example method retuns distance between this and input point

				    return Math.sqrt((this.x - p2.x)*(this.x - p2.x)+(this.y - p2.y)*(this.y - p2.y));
				  }
				}

				//var p1 = new Point(5, 5);
				//var p2 = new Point(10, 10);

				//console.log(p1.distance( p2));

			class vector2d {//another class for 2d vectors 
				constructor(x, y) {
				    this.x = x;
				    this.y = y;
				  }
				makefrompoints(origin,head){ //makes this vector point from the origin point twards the head point
											// interesting note since point and vector store data with the same name both 
					this.x=head.x-origin.x; // can be passed to this or any other method
					this.y=head.y-origin.y;
					
				}
				multiplyScalar( scal ) {
					this.x *= scal;
					this.y *= scal;

					return this;
				}
				normalize () { //normalises this vector
					
						return this.multiplyScalar(1/ this.length() );
					
				}
				 length () { //returns the length of this vector
					
					return Math.sqrt( this.x * this.x + this.y * this.y );

				}
				
			}
			
			
			//var vect = new vector2d(54,5);
			//console.log(p1);
			//console.log(vect.normalize().length());
			//console.log(vect.x);
			var gamescore=${gemescores};
			
			console.log(gamescore);
			var gravity=.05;
			var score=0;
			var myGamePiece;//this is the black spining ball
			var piece;//larger stationary circle
			var mybulets = [];//arrays for the outgoing and incomeing bulets
			var atacks=[];
			var myScore;
			var createbullet = false; 
			var mous = new Point(0,0); //location of mouse pointer
			var pointing=new vector2d(0,0); // direction vector from center of canvas towards mouse pointer
			var origon=new Point(0,0);  // cordinats of the center of the canvas
			var ofset=new Point(0,0) ;  //if i could get it to work this would be the ofset between the boddy and the gamewindow div
			var gameover=false;
			var gwidth=document.getElementById("gamecontent").clientWidth;
			var ghight=document.getElementById("gamecontent").clientHeight;
			//  gwidth   ghight
			function startGame() {
			  
			    myGamePiece = new circle(10,200,200,"black");   //inicalizes the game piece oblects
			    piece = new circle(20,gwidth/2, ghight/2,"red");
			    myScore = new rect("30px", "Consolas", "black", 280, 40);
			    score=0;

			    myGameArea.start();
			}

			var myGameArea = {
			    canvas : document.createElement("canvas"),
			    start : function() {//this is anothe way of creating classes/ methods
			        this.canvas.width = gwidth;
			        this.canvas.height = ghight;
			        this.canvas.id = "cnvs";
			        this.context = this.canvas.getContext("2d"); // im not certian what this dose but it seems to be improtant
			        score=0;
			       origon=new Point(gwidth/2,ghight/2)//sets the origon
	
			        var dx = this.canvas.offsetLeft;  //if i could get thhis block to work it would determine the 
				    var dy = this.canvas.offsetTop; // x and y distance between the edge of the body and the game div
				    var canvs=this.canvas;  		// but it dosnt work so at the moment it has been replaced by constant values
				    while (this.canvas.offsetParent) {
				    	canvs = canvs.offsetParent;
				    	dx += canvs.offsetLeft;
				    	dy += canvs.offsetTop;
				  	}  
				 	ofset = new Point(dx,dy) 
				 	
				 	
			       
				      // Record the mouse position when it moves.
				      this.canvas.addEventListener("mousemove", function(e) {
				        mousex = e.clientX;//these get the absolute position of the mouse on the page
				        mousey = e.clientY;
				       // mousex-=ofset.x-window.pageXOffset;
				      //  mousey-=ofset.y-window.pageYOffset;    // window.pageYOffset gets the position of the scrol bar
				      mousex+=window.pageXOffset-170;   // the static value is the distance between the edge of the body and the game div
				      mousey+=window.pageYOffset-270;
				      
				        mous= new Point(mousex,mousey);  //final position of mouse on the canvas __ top left 0,0
				        pointing.makefrompoints(origon,mous);
				        pointing.normalize()
				        //console.log(pointing);
				      });
				 	this.canvas.addEventListener("mousedown", function(e) {
				 		
				 		createbullet=true;//on mouse click alerts another method to create a bulet see   updateGameArea()
				 		// console.log(pointing);
				 	//	pointing.normalize()
				 		//bulet(pointing,origon,"black");
				 		
				 		
				 	});
			        document.getElementById("gamecontent").appendChild(this.canvas);  // places the canvas inside the game content div
			        

			        
			        this.frameNo = 0; //sets number of frames to 0
			        this.interval = setInterval(updateGameArea, 20); // stes how often to update the view
			        },
			    clear : function() {  //clears everything in the canvas
			        this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
			    }
			}

			function bulet(veloictyvector,origionpoint,color,mine){//serves as constructor for bullet class
				this.x=origionpoint.x;
				this.y=origionpoint.y;
				this.vx=veloictyvector.x*1.4;
				this.vy=veloictyvector.y*1.4;
				this.ismine=mine;   //mine was a bool, is no longer used
				this.gravitySpeed=0;
				
				this.update = function() {  // another method example
					ctx = myGameArea.context; //not sure what this dose but its necicary for this to work
				  	ctx.fillStyle = color;  // sets the color to be painted
				  	 ctx.beginPath(); //most canvas elements are made by creating a path and then filling in the insied of that paht
				  	//ctx.arc(x, y, radius, startAngle, endAngle, anticlockwise);
				  	ctx.arc(this.x, this.y,10, 0, Math.PI* 2, 0); //creates an arcing path (aka circle)
				  	ctx.fill(); // vills the path
				}
														//i is the array indix of this bulet
				this.newPos = function(i,mybullets) {// called to calculate the next state position
												// mybullets is a boolean that signifies wheater or not this bulet was fired by or, at my tank thing
						this.x+=this.vx;
				    	this.y+=this.vy;
				        this.gravitySpeed +=.02;
				       // this.y += this.vy + this.gravitySpeed;
				     if(mybullets==true){//weather or not this bullet was fired by my tank
				    	 this.y+= this.gravitySpeed+this.vy;
				    	 this.x+=this.vx;
				    	 if (this.x<0||this.y<0||this.y>ghight||this.x>gwidth){//if this was fired by my tank and then goes outside the border 
					    		//mybulets.shift();								//remve it from the array
			    				mybulets.splice(i, 1);	//js syntax for removing a single element from an array
						    	score-=.5;
			    				//console.log(mybulets.length)
					    	}
				    	 
				     }
				     else{
				    	 var disttotarget=Math.sqrt((this.x - origon.x)*(this.x - origon.x)+(this.y - origon.y)*(this.y - origon.y));
				    	 if(disttotarget<25){//if this was fired at my tank and moves withinn my tank, end the game
				    		 console.log("kill");
				    		 gameover=true;
				    		 post();
				    	 }
				     }	
			    }
				this.crashWith = function(otherobj,i,v) {//i and v are the array indices of these bulets
				//	mybulets[i].crashWith(atacks[v],i,v);
					 var disttotarget=Math.sqrt((this.x - otherobj.x)*(this.x - otherobj.x)+(this.y - otherobj.y)*(this.y - otherobj.y));
					 if(disttotarget<20){	//if these bullets colided remove them both from their respective arrays
			    		 console.log("block");
			    		 mybulets.splice(i, 1);
			    		 atacks.splice(v, 1);
			    		 score++; //incrament the score when an atacking bulet is destroied
			    		 
			    	 }
			    }
			       

			}
			
			function circle(radius,x,y,color){//draws circle
				this.x=x;
				this.y=y;
				this.radius=radius;
				
				this.update = function() {
				  	ctx = myGameArea.context;
				  	ctx.fillStyle = color;
				  	 ctx.beginPath();
				  	//ctx.arc(x, y, radius, startAngle, endAngle, anticlockwise);
				  	ctx.arc(this.x, this.y, this.radius, 0, Math.PI* 2, 0);
				  	ctx.fill();
				}
				this.newPos = function() {///  this is only used for the moving turrett it moves the nnormalized pointin vector at a 
			    	this.x=origon.x+pointing.x*20;	// radius of 20
			    	this.y=origon.y+pointing.y*20;
			     
			    }
			}
			function rect(width, height, color, x, y) { // used for displaying the score
			    this.width = width;
			    this.height = height;
			    this.x = x;
			    this.y = y;

			    this.update = function() {
			        ctx = myGameArea.context;
			            ctx.font = this.width + " " + this.height;
			            ctx.fillStyle = color;
			            ctx.fillText(this.text, this.x, this.y);
			        
			    }
   
			}

			function updateGameArea() { 
			    if(gameover==true){ // if the games over dont update
			    	
			    	

			    	return;
			    }
			    
			    myGameArea.clear();//clears exsisting stuf from the canvas
			    myGameArea.frameNo += 1; // incraments frame number
			    if (myGameArea.frameNo == 1 || everyinterval(50)) {//spawns a new atacking bullet every 50 frames
			        
			        innerspawnlimt = 200;
			        outerspawnlimt = 500;
			        
			        //gwidth   ghight
			        
			        
			        xspawn = Math.floor((Math.random()*((outerspawnlimt*2)+gwidth))-outerspawnlimt);//randomly determins a spawn point between 200 and 500
			        yspawn = Math.floor((Math.random()*((outerspawnlimt*2)+ghight))-outerspawnlimt); // from the origon
			        var from=new Point(xspawn,yspawn);
			        var to=new Point(origon.x,origon.y);
			        var vected = new vector2d(0,0);
			        vected.makefrompoints(from,to);// makes a vector to represent the veloicity of the bullet
			        vected.normalize();		// normalizes the vector
			        if(to.distance(from)>innerspawnlimt)// if spawn is at least innerspawnlimt from origon add to atack bullet arry
			        	atacks.push(new bulet(vected,from,"blue",false) );
			        
			    }
			    for (i = 0; i < atacks.length; i += 1) {//runs through and updates atack array
			    	atacks[i].newPos(i,false);
			    	atacks[i].update();
			    }
			   if(createbullet==true){ // if createbullet is true create a new bullit moving along the pointing vector  
				   mybulets.push(new bulet(pointing,origon,"black",true) ); // 
				   
				  createbullet=false;
			   }
			   for(i=0;i<mybulets.length;i+=1){ // runs thtough and updates the my bullets array
				   mybulets[i].newPos(i,true);
				   mybulets[i].update();
				   for(v=0;v<atacks.length; v++){ //also check if any of my bulets have colided with atacking bullets
					   mybulets[i].crashWith(atacks[v],i,v);
					   
				   }
				   
			   }
			    myScore.text="SCORE: " + score; //outbuts the score
			    myScore.update();
			    piece.update();
			    myGamePiece.newPos(); 
			    myGamePiece.update(); 
			}

			function everyinterval(n) {
			    if ((myGameArea.frameNo / n) % 1 == 0) {return true;}
			    return false;
			}
			function post(){
			 var urlEncodedData = "";
				var urlEncodedDataPairs = [];
				
				urlEncodedDataPairs.push(encodeURIComponent("score") + '=' + encodeURIComponent(score));
				
				 urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');
				 
				 var xhttp = new XMLHttpRequest();
				    xhttp.onreadystatechange = function() {
				        if (this.readyState == 4 && this.status == 200) {
				        	
				        	console.log("check")
				       }
				    };
				    xhttp.open("post", "Game3window", true);
				    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				    xhttp.send(urlEncodedData); 
				
				
			}
				
			
			
			
			
			
			
			
			
				
			</script>
		</div>
		
	
	   
	
	
	
	
	
	
	
	
		 <form action="${pageContext.servletContext.contextPath}/index" method="get">
	 	<input type="Submit" name="chatsubmit" value="home">
	 </form>
	<div id="gamescores" class="highscorecontainer">
	
	
	
	</div>
	
	<div id="userscores" class="highscorecontainer">
	
	
	
	</div>
	
		<script>
		var gamescore=${gemescores};
		var userscores=${userscores};
		
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
			for(var i=1; i < userscores.length+1; i++){
			   var newDiv = document.createElement('div');
			   var newHr = document.createElement('hr');
			 
			   var score=""+i+" |-| "
			   
			   score+=userscores[i-1];
			 
			 
			  
			   //newDiv.id = 'r'+i;
			   newDiv.className = 'gamescoreentry';
			  
			   newDiv.innerHTML = score;
			  
			   toAdd.appendChild(newDiv);
			   toAdd.appendChild(newHr);
			}
			//toAdd.appendChild(previousposts);
			document.getElementById("userscores").appendChild(toAdd);
			
			
		
		
		
		
		</script>
	
	
	
	
	
	
	
	</body>
</html>