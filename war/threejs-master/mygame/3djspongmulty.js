
class ballstate{//another class for 2d vectors 
	constructor( obj) {
	    this.bv=obj.velocity;
	    this.bp=obj.position;

	    console.log(this);
	}
	//makefrompoints(origin,head){ //makes this vector point from the origin point twards the head point
								// interesting note since point and vector store data with the same name both 
	//	this.x=head.x-origin.x; // can be passed to this or any other method
	//	this.y=head.y-origin.y;
		
	//}
	nullify(){
		this.bv=null;
	    this.bp=null;
	}
	ubdateball(obj){
		 this.bv=obj.velocity;
		 this.bp=obj.position;
	}
	
}
class paddlestate {//another class for 2d vectors 
	constructor(obj) {
		var r1=new THREE.Vector3(obj.rotation.x,obj.rotation.y,obj.rotation.z);
	    this.pv=obj.velocity;
	    this.pp=obj.position;
	    this.pr= r1;

	    console.log(this);
	}
	//makefrompoints(origin,head){ //makes this vector point from the origin point twards the head point
								// interesting note since point and vector store data with the same name both 
	//	this.x=head.x-origin.x; // can be passed to this or any other method
	//	this.y=head.y-origin.y;
		
	//}
	nullify(){
	    this.pv=null;
	    this.pp=null;
	    this.pr=null;
	}

	updatepaddle(obj){
		//var r1=new THREE.Vector3(paddle.rotation.x,paddle.rotation.y,paddle.rotation.z);
		var r1=new THREE.Vector3(obj.rotation.x,obj.rotation.y,obj.rotation.z);
	    this.pv=obj.velocity;
	    this.pp=obj.position;
	    this.pr= r1;
	}
	
}






			if ( ! Detector.webgl ) Detector.addGetWebGLMessage();

			var container, stats;

			var camera, scene, renderer;

			var raycaster, mouse;

			var mesh, line;
			
			var left, right, up, down, foward, mousedown, level;
			
			var paddle,otherpaddle , walls=[], invisibleplane, ball, table, bounds=[];
			
			var gravity, nextside,hitmypaddle=false;
			
			var cardinals=[];
			
			var balstate;
			
			var mypadstate,otherpadstate;
			
			var tochangeball=null,tochangeotherpad=null;
			
			var paddletowards=new THREE.Vector3(0,0,200);
			init();
			animate();

			function init() {

				container = document.getElementById( 'container' );
				document.getElementById( 'container' ).innerHTML = "";
				//
				//console.log(gamecontent.offsetWidth);
				
				camera = new THREE.PerspectiveCamera( 35, (gamecontent.offsetWidth )/container.offsetHeight, 1, 3500 );
				camera.position.z = 500;
				camera.position.y = 120;

				gravity= new THREE.Vector3(0,-.05,0);
		
				//controls = new THREE.PointerLockControls( camera );
				
				cardinals.push(new THREE.Vector3(0,-1,0));
				cardinals.push(new THREE.Vector3(1,0,0));
				cardinals.push(new THREE.Vector3(-1,0,0));
				cardinals.push(new THREE.Vector3(0,0,-1));
				
				scene = new THREE.Scene();
				scene.background = new THREE.Color( 0x808080 );
				scene.fog = new THREE.Fog( 0x050505, 2000, 3500 );

				//

				scene.add( new THREE.AmbientLight( 0x444444 ) );

				var light1 = new THREE.DirectionalLight( 0xf6ff6f, 0.5 );
				light1.position.set( 1, 100, 1 );
				light1.castShadow = true;
				scene.add( light1 );

				var light2 = new THREE.DirectionalLight( 0x141414, 1.5 );
				light2.position.set( 0, 50, 300 );
				light2.castShadow = true;
				scene.add( light2 );
				light2.shadow.mapSize.width = 512;  // default
				light2.shadow.mapSize.height = 512; // default
				light2.shadow.camera.near = 0.5;       // default
				light2.shadow.camera.far = 500      // default
				
				var colorMaterial = new THREE.MeshPhongMaterial( { specular: 0xccffff, flatShading: true, vertexColors: THREE.VertexColors } );
					colorMaterial.color.setHSL( Math.random() * 0.2 + 0.5, 0.75, Math.random() * 0.25 + 0.75 );
				
				
				var paddlegeom = new THREE.BoxGeometry( 50, 50, 2);
				paddle = new THREE.Mesh( paddlegeom, colorMaterial);
				paddle.receiveShadow=true;
				paddle.mass=Infinity;
				paddle.position.z = 200;
				paddle.velocity=new THREE.Vector3(0,0,0);
				scene.add( paddle );
				
				
				var paddlegeom = new THREE.BoxGeometry( 50, 50, 2);
				otherpaddle = new THREE.Mesh( paddlegeom, colorMaterial);
				otherpaddle.receiveShadow=true;
				otherpaddle.mass=Infinity;
				otherpaddle.position.z = -200;
				otherpaddle.velocity=new THREE.Vector3(0,0,0);
				//walls.push(otherpaddle);
				scene.add( otherpaddle );
				
				
				var colorMaterial = new THREE.MeshPhongMaterial( { specular: 0xccffff, flatShading: true, vertexColors: THREE.VertexColors } );
				colorMaterial.color.setHSL( Math.random() * 0.2 + 0.5, 0.75, Math.random() * 0.25 + 0.75 );
				
				var tablegeom = new THREE.BoxGeometry( 200, 20, 400);
				
				table = new THREE.Mesh( tablegeom, colorMaterial);
				table.receiveShadow=true;
				table.mass=Infinity;
				
				//table.position.y=-40;
				walls.push(table);
				scene.add( table );
				
				var colorMaterial = new THREE.MeshPhongMaterial( { specular: 0xccffff, flatShading: true, vertexColors: THREE.VertexColors } );
				colorMaterial.color.setHSL( Math.random() * 0.2 + 0.5, 0.75, Math.random() * 0.25 + 0.75 );
				
				var wallgeom = new THREE.BoxGeometry( 20, 800, 400);
				var wall= new THREE.Mesh( wallgeom, colorMaterial);
				wall.position.x = 110;
				wall.position.y = 390;
				wall.receiveShadow=true;
				walls.push(wall);
				scene.add( wall );
				
				var wall= new THREE.Mesh( wallgeom, colorMaterial);
				wall.position.x = -110;
				wall.position.y = 390;
				wall.receiveShadow=true;
				walls.push(wall);
				scene.add( wall );
				
				var wallgeom = new THREE.BoxGeometry( 200, 800, 10);
				var wall= new THREE.Mesh( wallgeom, colorMaterial);
				wall.position.z = -200;
				wall.position.y = 390;
				wall.receiveShadow=true;
				
				//walls.push(wall);
				//scene.add( wall );
				
				
					var planegeom = new THREE.PlaneGeometry( 2000, 2000);
			//	var material = new THREE.MeshBasicMaterial( {color: 0xffff00, side: THREE.BackSide} );
				var material = new THREE.MeshBasicMaterial( {color: 0xffff00, side: THREE.FrontSide} );
				material.visible=false;
				console.log(material);
				invisibleplane = new THREE.Mesh( planegeom, material);
				
				invisibleplane.position.z = 200;
		
				scene.add( invisibleplane );
				
				

				var material = new THREE.MeshBasicMaterial( {color: 0xffff00, side: THREE.DoubleSide} );
				material.visible=false;
				console.log(material);
				var invisiblbound = new THREE.Mesh( planegeom, material);
				
				invisiblbound.position.z = 220;
				scene.add( invisiblbound );
				bounds.push(invisiblbound);
				//walls.push(invisiblbound);
				
				
				var material = new THREE.MeshBasicMaterial( {color: 0xffff00, side: THREE.DoubleSide} );
				material.visible=false;
				console.log(material);
				var invisiblbound = new THREE.Mesh( planegeom, material);
				
				invisiblbound.position.z = -220;
				scene.add( invisiblbound );
				bounds.push(invisiblbound);
				//walls.push(invisiblbound);
				
				
				var geometry = new THREE.SphereGeometry( 8, 8, 8);
				//BoxGeometry
				geometry.computeBoundingSphere();
				
				ball = new THREE.Mesh( geometry, new THREE.MeshLambertMaterial( { color: Math.random() * 0xffffff } ) );

				ball.position.x =  0;
				ball.position.y =  50;
				ball.position.z =  190;
				
				ball.mass=.5;
				ball.velocity=new THREE.Vector3(0,0,0);
				ball.hasgravity=false;
				ball.castShadow = true;
				
				ball.castShadow = true; 
				ball.receiveShadow = false; 
				
				scene.add( ball );

				
				
				raycaster = new THREE.Raycaster();
				mouse = new THREE.Vector2();
				
				

				renderer = new THREE.WebGLRenderer( );
				renderer.setPixelRatio( window.devicePixelRatio );
				renderer.setSize( gamecontent.offsetWidth, gamecontent.offsetHeight );
				container.appendChild( renderer.domElement );

				//

				stats = new Stats();
				container.appendChild( stats.dom );

				//
				var r1=new THREE.Vector3(paddle.rotation.x,paddle.rotation.y,paddle.rotation.z);
				var r2=new THREE.Vector3(otherpaddle.rotation.x,otherpaddle.rotation.y,otherpaddle.rotation.z);
				
				 //game= new gamestate(ball.velocity,ball.position,paddle.velocity,paddle.position,  r1  ,otherpaddle.velocity,otherpaddle.position, r2 );
				//,pad1v,pad1pos,pad1rt,pad2v,pad2pos,pad2rt
				//rotation
				// var gamestring=JSON.stringify(game)
				balstate =new ballstate(ball);
				mypadstate =new paddlestate(paddle);
				otherpadstate =new paddlestate(otherpaddle);
				 try{
						//connection.send(gamestring ); 
					}
					catch (e){
						console.log(e);
				}
				
				
				
				container.addEventListener( 'resize', onWindowResize, false );
				container.addEventListener( 'mousemove', onDocumentMouseMove, false );
				container.addEventListener( 'mousedown', onDocumentmousedown, false );
				document.addEventListener( 'mouseup', onDocumentmouseup, false );
				document.addEventListener( 'keydown', onkeydown, false );
				document.addEventListener( 'keyup', onkeyup, false );
			}
			
			function reset(lastside){
			scene.remove(ball);
				var geometry = new THREE.SphereGeometry( 8, 8, 8);
				//BoxGeometry
				var nextside=-lastside;
				geometry.computeBoundingSphere();
				
				ball = new THREE.Mesh( geometry, new THREE.MeshLambertMaterial( { color: Math.random() * 0xffffff } ) );

				ball.position.x =  0;
				ball.position.y =  50;
				if(nextside>0){
					ball.position.z =  190;
				}
				else{
					ball.position.z =  -190;
				}
					
				ball.mass=.5;
				ball.velocity=new THREE.Vector3(0,0,0);
				ball.hasgravity=false;
				ball.castShadow = true;
				
				scene.add( ball );

			
			}
			
			function onDocumentmousedown( event ) {
			//	console.log(event.button);
				mousedown=true;
			

			};
			
			
			
			function onDocumentmouseup( event ) {
			//	console.log(event.button);
				mousedown=false;
			

			};
			
			
			function onkeydown( event ) {
				//console.log(event.keyCode);
				
				switch ( event.keyCode ) {

					case 86: /* space bar */ foward=true; break;

				
					case 87: /*W*/ up =true ; break;
					case 83: /*S*/ down =true ; break;

					case 65: /*A*/ left=true; break;
					case 68: /*D*/ right=true; break;				
					case 81: /*Q*/ level=true; break;
				}


			};

			function onkeyup( event ) {
				switch ( event.keyCode ) {

					case 86: /* space bar */ foward=false; break;


					case 87: /*W*/ up =false ; break;
					case 83: /*S*/ down =false ; break;

					case 65: /*A*/ left=false; break;
					case 68: /*D*/ right=false; break;
					case 81: /*Q*/ level=false; break;
			
				}

			};
			
			
			
			function onWindowResize() {

				camera.aspect = window.innerWidth / window.innerHeight;
				camera.updateProjectionMatrix();

				renderer.setSize( window.innerWidth, window.innerHeight );

			}

			function onDocumentMouseMove( event ) {

				event.preventDefault();
				
				//console.log(camera);
				if (mousedown){
					var movementX = event.movementX || event.mozMovementX || event.webkitMovementX || 0;
					var movementY = event.movementY || event.mozMovementY || event.webkitMovementY || 0;
					
					camera.quaternion.y+=(movementX*.0006);
					camera.quaternion.x+=(movementY*.0006);

				}
				mouse.x = ( event.clientX / window.innerWidth ) * 2 - 1;
				mouse.y = - ( (event.clientY - document.body.scrollTop) / window.innerHeight ) * 2 + 1;
				//paddle.position.x=mouse.x;   container document.getElementById("container").
				
				
				//console.log(document.getElementById("gamecontent").scrollTop);
				//console.log(document.getElementById("gamecontent").offsetHeight );
				
				//mouse.x-=170;
			//	mouse.y-=270;
				
				//console.log(mouse);
				
				raycaster.setFromCamera( mouse, camera );
				
				var intersects = raycaster.intersectObject( invisibleplane );
				
				var previous=paddle.position.clone();
				var poin = paddle.position.clone();
				if ( intersects.length > 0 ) {

					var intersect = intersects[ 0 ];
					paddletowards = intersect.point;
					
				}
				//paddle.velocity.x=poin.clone().sub(previous).x;
				//paddle.velocity.y=poin.clone().sub(previous).y;
				//paddle.velocity=paddle.position.clone().sub(previous);
				//console.log(paddle.velocity);
				
			}

			//

			function animate() {
				//console.log(paddle.velocity);
				requestAnimationFrame( animate );
				
				if(foward&&paddle.position.z>180){
					paddle.velocity.z=-3;
				}
				else if (!foward&&paddle.position.z<200){
					paddle.velocity.z=3;
				}
				else {paddle.velocity.z=0;}
				
				//paddle.position.z+=paddle.velocity.z;
				//paddletowards
				
				//paddle.velocity.x=paddle.position.x-paddletowards.x;
				var pvx=paddletowards.x-paddle.position.x;
				var pvy=paddletowards.y-paddle.position.y;
				var pvxy=new THREE.Vector2(pvx,pvy);
				pvxy.clampLength ( 0, 4 );
				paddle.velocity.x=pvxy.x;
				paddle.velocity.y=pvxy.y;
				paddle.position.add(paddle.velocity);
				

				//tochangeball=null,tochangeotherpad=null;
				if(tochangeotherpad!=null){
					//console.log(otherpaddle);
					otherpaddle.position.x=tochangeotherpad.pp.x;
					otherpaddle.position.y=tochangeotherpad.pp.y;
					otherpaddle.position.z=0-tochangeotherpad.pp.z;
					otherpaddle.velocity.x=tochangeotherpad.pv.x;
					otherpaddle.velocity.y=tochangeotherpad.pv.y;
					otherpaddle.velocity.z=tochangeotherpad.pv.z;
					otherpaddle.rotation.x=-tochangeotherpad.pr.x;
					otherpaddle.rotation.y=-tochangeotherpad.pr.y;
					otherpaddle.rotation.z=-tochangeotherpad.pr.z;
					tochangeotherpad==null;
				}
				if(mynumber==2&&tochangeball!=null){
					ball.position.x=tochangeball.bp.x;
					ball.position.y=tochangeball.bp.y;
					ball.position.z=0-tochangeball.bp.z;
					ball.velocity.x=tochangeball.bv.x;
					ball.velocity.y=tochangeball.bv.y;
					ball.velocity.z=tochangeball.bv.z;
					tochangeball==null;
				}
				ball.velocity.clampLength ( 0, 10 );
				
				
				if(up&&paddle.quaternion.x<.8){
					paddle.quaternion.x+=.05;
				}
				
				if(down&&paddle.quaternion.x>-.4){
					paddle.quaternion.x-=.05;
				}
				
				if(left&&paddle.quaternion.y<.4){
					paddle.quaternion.y+=.05;
				}
				
				if(right&&paddle.quaternion.y>-.4){
					paddle.quaternion.y-=.05;
				}
				
				if(level){
					paddle.quaternion.y=0;
					paddle.quaternion.x=0;
				}
				
				//console.log(paddle);
				for (var vertexIndex = 0; vertexIndex < ball.geometry.vertices.length; vertexIndex++){        				
					var localVertex = ball.geometry.vertices[vertexIndex].clone();
					var globalVertex = localVertex.applyMatrix4( ball.matrix ) ;
					var directionVector = globalVertex.sub( ball.position );
					
					var raycaster = new THREE.Raycaster( ball.position, directionVector.clone().normalize(), 0, ball.geometry.boundingSphere.radius);
					var intersects;
					var cast=false;
					if(ball.position.z>100){
						intersects = raycaster.intersectObject( paddle);
						cast=true;
					}
					if(ball.position.z<-100){
						intersects = raycaster.intersectObject( otherpaddle);
						cast=true;
					}
					//var intersects = raycaster.intersectObject( paddle);
					
					if (cast){
						if (intersects.length>0){
							//console.log(paddle);
							var inside=ball.geometry.boundingSphere.radius-intersects[ 0 ].distance;
							
							//console.log(intersects);
							
							//var dir=ball.position.clone().sub(intersects[ 0 ].point.clone());
							var dir=intersects[ 0 ].face.normal.clone(); ; 
							//dir.applyQuaternion(paddle.quaternion);
							
							if(ball.position.z>100){
								dir.applyEuler(paddle.rotation);
								hitmypaddle=true;
							}
							if(ball.position.z<-100){
								dir.applyEuler(otherpaddle.rotation);
							}
						
							var a=dir.x;
							var b=dir.y ;
							var c=dir.z; 
							
							var velo=ball.velocity;
							
							var  reflect = new THREE.Matrix4(); 
							reflect.set( (c*c)+(b*b)-(a*a), a*b*-2,			a*c*-2, 	0,
									a*b*-2, 		(c*c)+(a*a)-(b*b), 	b*c*-2,	 	0,
									a*c*-2, 			b*c*-2,		(b*b)+(a*a)-(c*c),		 	0,
									0, 					0, 				0, 		 	1 );
							
							velo.applyMatrix4 ( reflect );
	
							//ball.velocity=velo;
							//ball.velocity.add(paddle.velocity);
							
							//ball.velocity.add(paddle.velocity);
							
							
							var force= dir.clone().setLength(inside) ;
							ball.velocity.add(force );
							ball.velocity.divideScalar( 1.01);
							
							inside+=.1;
							var insidevect= dir.clone().setLength(inside) ;
							//ball.position.add(insidevect);
							
							//velo.applyMatrix4 ( reflect );
							ball.hasgravity=true;
						}
					}
				}
				
				for (var j=0;j<cardinals.length;j++){
					var direct=cardinals[j];
					//console.log(direct);
					var raycaster = new THREE.Raycaster( ball.position, direct.clone().normalize(), 0, ball.geometry.boundingSphere.radius);

					var intersects = raycaster.intersectObjects( walls);
					//console.log(direct);
					
					if (intersects.length>0){
					//console.log(intersects);
						var inside=ball.geometry.boundingSphere.radius-intersects[ 0 ].distance;
						var dir=intersects[ 0 ].face.normal.clone(); 
						
						
						var a=dir.x;
						var b=dir.y ;
						var c=dir.z; 
						
						var velo=ball.velocity;
						
						var  reflect = new THREE.Matrix4(); 
						reflect.set( (c*c)+(b*b)-(a*a), a*b*-2,			a*c*-2, 	0,
								a*b*-2, 		(c*c)+(a*a)-(b*b), 	b*c*-2,	 	0,
								a*c*-2, 			b*c*-2,		(b*b)+(a*a)-(c*c),		 	0,
								0, 					0, 				0, 		 	1 );
						
						
						inside+=.1;
						//var force= dir.clone().setLength(inside*2) ;
						
					//	ball.velocity.add(force );
						ball.velocity.divideScalar( 1.03);
						
						var insidevect= dir.clone().setLength(inside) ;
						velo.applyMatrix4 ( reflect );
						ball.velocity=velo;
						ball.position.add(insidevect);
						
					}
				}
				
				for (var j=0;j<cardinals.length;j++){
					var direct=cardinals[j];
					//console.log(direct);
					var raycaster = new THREE.Raycaster( ball.position, direct.clone().normalize(), 0, ball.geometry.boundingSphere.radius);

					var intersects = raycaster.intersectObjects( bounds);
					//console.log(direct);
					
					if (intersects.length>0){
					//console.log(intersects);
						reset(intersects[0].point.z);
						console.log("reset");
					}
				}
				
				
			
				
				ball.position.add(ball.velocity);
				if (ball.hasgravity){
					ball.velocity.add(gravity)
				
				}
				
				
				if(mynumber==1){
					balstate.ubdateball(ball);
					mypadstate.updatepaddle(paddle);
					try{
						connection.send("ball :"+JSON.stringify(balstate)); 
						connection.send("mypad:"+JSON.stringify(mypadstate)); 
					}
					catch (e){
						console.log(e);
					}
				}
				else if(mynumber==2){
					mypadstate.updatepaddle(paddle);
					try{
						connection.send("mypad:"+JSON.stringify(mypadstate)); 
						if(hitmypaddle){
							//connection.send("ball :"+JSON.stringify(balstate));
						}
					}
					catch (e){
						console.log(e);
					}
				}
				
				
				
				//connection.send(gamestring ); 
				//console.log("sent");
	//			paddle.velocity.x=0;
	//			paddle.velocity.y=0;
				
				render();
				stats.update();

			}

			function render() {

				camera.rotation.setFromQuaternion( camera.quaternion, camera.rotation.order );

				renderer.render( scene, camera );

			}
