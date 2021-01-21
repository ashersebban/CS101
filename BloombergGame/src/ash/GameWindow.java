package ash;

import processing.core.*;

/**
 * A simple class that inherits from Processing's PApplet class and displays an oscillating circle that bounces back and forth
 * @author Foo Barstein
 * @version 2
 */
public class GameWindow extends PApplet {
	
	//instance properties
	
	//constants that hold the width and height of the window
	final private int w = 600;
	final private int h = 400;

	//window variables
	final private int ceiling = 0;
	final private int floor = h;
	final private int leftWall = 0;
	final private int rightWall = w;
	
	//game variables
	int counter = 0;
	boolean collision;
	boolean gameOver = false;
	int f=0;
	int score = 0;
	boolean highscoreReached = false;
	
	
	
	Player player1;
	Obstacle obstacle1;
	Brick[] newWall;
	
	/**
	 * This method is automatically called by Java when the program first starts.
	 * @param args any command line arguments (not used)
	 */
	public static void main(String[] args) {
		//must call PApplet's static main method, and supply the full package and class name of this class as an argument
		PApplet.main("ash.GameWindow");
	}
	
	/**
	 * Method to draft general settings of the app
	 */
	public void settings() {
		//set the site of the window in pixels to the values of the instance properties w and h
		this.size(this.w, this.h); 

	}
	
	/**
	 * Method to compose the first 'frame' of the app
	 */
	public void setup() {
		
		player1 = new Player(w,h);
		
		obstacle1 = new Obstacle(w,h);
		newWall = obstacle1.generateNewWall();
		
		//fill the background with this color (specified in 8-bit R, G, B values)
		this.background(255, 255, 255); //white

		//set the fill color (the color which solid shapes will be filled with)
		this.fill(player1.getPlayerColor()[0],player1.getPlayerColor()[1],player1.getPlayerColor()[2]); 
		
	}
	
	
	
	/**
	 * This method is called repeatedly many times per second (usually 30 times per second by default) for the lifetime of the app.
	 */
	public void draw() {
		
		float offset = 0; 
		
		ellipseMode(CORNER);
		rectMode(CORNER);
		
		//fill the background with this color (specified in 8-bit R, G, B values)
		this.background(255, 255, 255); //white
		
		//set the fill color (the color which solid shapes will be filled with)
		this.fill(player1.getPlayerColor()[0],player1.getPlayerColor()[1],player1.getPlayerColor()[2]); 
		
		//COLLISION CHECK
		collision = collision();
		if(collision) {
			gameOverMessage();
		}
		
		for(int i=0;i<8;i++) {
			stroke(0);
			line(0,floor-offset,w,floor-offset);
			offset += floor/8;
		}
		
		
		//OBSTACLE
		for(int i=0;i<obstacle1.getNumLayers();i++) {
			fill(newWall[i].getFill()[0],newWall[i].getFill()[1],newWall[i].getFill()[2]);
			rect(newWall[i].getPosX(),newWall[i].getPosY(),newWall[i].getWidth(),newWall[i].getHeight());
		}
	
		if(collision == false) {
			obstacle1.setPosX(obstacle1.getPosX()-obstacle1.getSpeed());
			if(obstacle1.getPosX() < -50) {
				newWall = obstacle1.generateNewWall();
				obstacle1.setPosX(w+50);
				
			}
		}
		
		
		//DRAW SHAPE BASED ON PLAYER SHAPES
		if(player1.getPlayerShape().equalsIgnoreCase("Square"))rect(player1.getPlayerX(),player1.getPlayerY(),player1.getPlayerWidth(),player1.getPlayerHeight());
		else if(player1.getPlayerShape().equalsIgnoreCase("Circle"))ellipse(player1.getPlayerX(),player1.getPlayerY(),player1.getPlayerWidth(),player1.getPlayerHeight());
		else if(player1.getPlayerShape().equalsIgnoreCase("Triangle")) {
			if(!player1.isUpsidedown())triangle(player1.getPlayerX(),player1.getPlayerY()+player1.getPlayerHeight(),player1.getPlayerWidth()+player1.getPlayerX(),player1.getPlayerY()+player1.getPlayerHeight(),player1.getPlayerX()+player1.getPlayerWidth()/2,player1.getPlayerY());
			else if(player1.isUpsidedown())triangle(player1.getPlayerX(),player1.getPlayerY(),player1.getPlayerWidth()+player1.getPlayerX(),player1.getPlayerY(),player1.getPlayerX()+player1.getPlayerWidth()/2,player1.getPlayerY()+player1.getPlayerHeight());
		}
		
		//PLAYER VARIABLES 2
		int topOfPlayer = player1.getPlayerY();
		int bottomOfPlayer = player1.getPlayerY() + player1.getPlayerHeight();
		
		
		//CANNOT MOVE PAST CEILING
		if(topOfPlayer<= ceiling) {
			player1.setReachedMaxHeight(true);
			player1.setPlayerY(ceiling);
		}
		else player1.setReachedMaxHeight(false);
		
		//CANNOT MOVE PAST FLOOR
		if(bottomOfPlayer >= floor-10) {
			player1.setReachedMinHeight(true);
			player1.setPlayerY(floor - player1.getPlayerHeight() - 10);
		}
		else player1.setReachedMinHeight(false);
		
		//MOVEMENT 2
		if(!collision) {
			//TRIANGLE
			if(player1.getPlayerShape() == "Triangle") {
				//GENERAL MOVEMENT
				player1.setPlayerY(player1.getPlayerY() + player1.getPlayerVelocity()); 
				if(player1.isReachedMaxHeight()||player1.isReachedMinHeight())player1.setPlayerVelocity(0);
			}
			
			//SQUARE
			else if(player1.getPlayerShape() == "Square") {
				//GENERAL MOVEMENT
				player1.setPlayerY(player1.getPlayerY() + player1.getPlayerVelocity()); 
				if((player1.isReachedMaxHeight()||player1.isReachedMinHeight()) || (player1.getPlayerY()%(h/obstacle1.getNumLayers())==0 && !keyPressed)) {
					player1.setPlayerVelocity(0);
				}
			}
			
			//CIRCLE
			else if(player1.getPlayerShape() == "Circle") {
				if(keyPressed && keyCode == UP) {
					player1.setPlayerY(player1.getPlayerY()-player1.getCircleJumpSpeed());
				}
				if(keyPressed && keyCode == DOWN) {
					player1.setPlayerY(player1.getPlayerY()+player1.getCircleJumpSpeed());
				}
				else player1.setPlayerY(player1.getPlayerY()+player1.getCircleFallSpeed());
			}
		}
	}

	public void keyReleased() {
		//MOVEMENT
		if(!collision()) {
			//TRIANGLE
			if(player1.getPlayerShape() == "Triangle") {
				//UP
				if(player1.isReachedMinHeight() && player1.getPlayerVelocity() == 0 && keyCode == UP) {
					player1.setPlayerVelocity(player1.getTriangleVertical()*-1) ;
					player1.setUpsidedown(true);
					player1.setReachedMaxHeight(false);
					player1.setReachedMinHeight(false);
				}
				//DOWN
				else if(player1.isReachedMaxHeight()  && player1.getPlayerVelocity() == 0 && keyCode == DOWN) {
					player1.setPlayerVelocity(player1.getTriangleVertical()) ;
					player1.setUpsidedown(false);
					player1.setReachedMaxHeight(false);
					player1.setReachedMinHeight(false);
				}
			}
			//SQUARE
			else if(player1.getPlayerShape() == "Square") {
				//UP
				if(!player1.isReachedMaxHeight() && player1.getPlayerVelocity() == 0 && keyCode == UP) {
					player1.setPlayerVelocity(player1.getSquareVertical()*-1) ;
				}
				//DOWN
				else if(!player1.isReachedMinHeight()  && player1.getPlayerVelocity() == 0 && keyCode == DOWN) {
					player1.setPlayerVelocity(player1.getSquareVertical()) ;
				}
			}
			//changes shape
			if(key == 'c') {
				if(player1.getPlayerShape().equalsIgnoreCase("Square")) {
					player1.setPlayerShape("Triangle");
				}
				else if (player1.getPlayerShape().equalsIgnoreCase("Triangle")) {
					player1.setPlayerShape("Circle");
				}
				else if (player1.getPlayerShape().equalsIgnoreCase("Circle")) {
					player1.setPlayerShape("Square");
				}
			}
			
			player1.setPlayerY(player1.getPlayerY() + player1.getPlayerVelocity()); 
		}	
	}
	
	public boolean collision(){
		
		//PLAYER
		  int frontOfPlayer = player1.getPlayerX()+player1.getPlayerWidth();
		  int backOfPlayer = player1.getPlayerX();
		  int topOfPlayer = player1.getPlayerY();
		  int bottomOfPlayer = player1.getPlayerY() + player1.getPlayerHeight();
		 
		  //OBSTACLE
		  for(int i = 0;i<newWall.length;i++) {
			  int frontOfObstacle = newWall[i].getPosX();
			  int backOfObstacle = newWall[i].getPosX() + newWall[i].getWidth();
			  int bottomOfObstacle = newWall[i].getPosY() + newWall[i].getHeight();
			  int topOfObstacle = newWall[i].getPosY();
			  
			  if(frontOfPlayer >= frontOfObstacle && backOfPlayer <= backOfObstacle) {
				  if((topOfPlayer <= bottomOfObstacle && topOfPlayer >= topOfObstacle)||(bottomOfPlayer <= bottomOfObstacle && bottomOfPlayer >= topOfObstacle)) {
					  System.out.println("Collision");
					  System.out.println(newWall[i].getValue());
					  if(newWall[i].getValue()==1)return true;
					  else if(newWall[i].getValue()==2 && !(player1.getPlayerShape() == "Square"))return true;
					  else if(newWall[i].getValue()==3 && !(player1.getPlayerShape() == "Triangle"))return true;
					  else if(newWall[i].getValue()==4 && !(player1.getPlayerShape() == "Circle"))return true;
				  }
			  } 
		  }
		 
		  return false;
	}
	
	public void gameReset(){
		  player1.setPlayerY(0);
		  obstacle1.setPosX(leftWall*2);
		  gameOver = false;
		  f = 0;
		  score = 0;
		  highscoreReached = false;
		  collision = false;
		}
	
	public void gameOverMessage(){
		  stroke(0);
		  rectMode(CENTER);
		  rect(rightWall/2,ceiling/2,rightWall/2,ceiling/2);
		  rectMode(CORNER);
		  fill(0);
		  textAlign(CENTER);
		  textSize(20);
		  text("GAME OVER",rightWall/2,ceiling/2);
		  textSize(14);
		  text("Press TAB to replay",rightWall/2,ceiling/2+30);
		  fill(255);
		}
	
	
}

	