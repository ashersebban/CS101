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
	boolean collision = false;
	
	Player player1;
	Obstacle obstacle1;
	int[] newWall;
	
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
		
		obstacle1 = new Obstacle(w);
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
		
		//fill the background with this color (specified in 8-bit R, G, B values)
		this.background(255, 255, 255); //white
		
		//set the fill color (the color which solid shapes will be filled with)
		this.fill(player1.getPlayerColor()[0],player1.getPlayerColor()[1],player1.getPlayerColor()[2]); 
		
		
		for(int i=0;i<8;i++) {
			stroke(0);
			line(0,floor-offset,w,floor-offset);
			offset += floor/8;
		}
		
		//OBSTACLE
		int layer = 0;
		for(int i=0;i<obstacle1.getNumLayers();i++) {
			if(newWall[i]==1)rect(obstacle1.getObstacleX(),layer,player1.getPlayerWidth(),h/obstacle1.getNumLayers());
			layer+=h/obstacle1.getNumLayers();
		}
		obstacle1.setObstacleX(obstacle1.getObstacleX()-obstacle1.getObstacleSpeed());
		if(obstacle1.getObstacleX() < -50) {
			newWall = obstacle1.generateNewWall();
			obstacle1.setObstacleX(w+50);
			
		}
		
		
		//DRAW SHAPE BASED ON PLAYER SHAPES
		if(player1.getPlayerShape().equalsIgnoreCase("Square"))rect(player1.getPlayerX(),player1.getPlayerY(),player1.getPlayerWidth(),player1.getPlayerHeight());
		else if(player1.getPlayerShape().equalsIgnoreCase("Circle"))ellipse(player1.getPlayerX(),player1.getPlayerY(),player1.getPlayerWidth(),player1.getPlayerHeight());
		else if(player1.getPlayerShape().equalsIgnoreCase("Triangle")) {
			if(!player1.isUpsidedown())triangle(player1.getPlayerX(),player1.getPlayerY()+player1.getPlayerHeight(),player1.getPlayerWidth()+player1.getPlayerX(),player1.getPlayerY()+player1.getPlayerHeight(),player1.getPlayerX()+player1.getPlayerWidth()/2,player1.getPlayerY());
			else if(player1.isUpsidedown())triangle(player1.getPlayerX(),player1.getPlayerY(),player1.getPlayerWidth()+player1.getPlayerX(),player1.getPlayerY(),player1.getPlayerX()+player1.getPlayerWidth()/2,player1.getPlayerY()+player1.getPlayerHeight());
		}
		
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
			if((player1.isReachedMaxHeight()||player1.isReachedMinHeight()) || (player1.getPlayerY()%(h/obstacle1.getNumLayers())==0 && !keyPressed))player1.setPlayerVelocity(0);
		}
		
		//CIRCLE
		else if(player1.getPlayerShape() == "Circle") {
			if(keyPressed && keyCode == UP) {
				player1.setPlayerY(player1.getPlayerY()-player1.getCircleJumpSpeed());
			}
			if(keyPressed && keyCode == DOWN) {
				player1.setPlayerY(player1.getPlayerY()+player1.getCircleJumpSpeed());
			}
			//else player1.setPlayerY(player1.getPlayerY()+player1.getCircleFallSpeed());
		}
				

		
	}

	public void keyReleased() {
		//MOVEMENT
		if(!collision) {
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
}

	