package ash;

import java.util.Arrays;
import processing.core.*;

/**
 * A simple class that inherits from Processing's PApplet class and displays an oscillating circle that bounces back and forth
 * @author Asher Sebban
 * @version 3
 */
public class GameWindow extends PApplet {

	//constants that hold the width and height of the window
	
	final private int sizeX = 600;
	final private int sizeY = 400;

	public static int SIZE_X = 600;
	public static int SIZE_Y = 400;
	public static final int NUM_LAYERS = 8;
	private int layerHeight = SIZE_Y/NUM_LAYERS;

	private int[] layer_y_values = new int[NUM_LAYERS];
	private int[] player_y_values = new int[NUM_LAYERS];

	//game variables
	boolean collision = false;
	boolean gameOver = false;
	int score = 0;
	int highscore = 0;
	boolean highscoreReached = false;

	Player player1;
	Obstacle obstacle1;
	Brick[] newWall;


	/**
	 * This method is automatically called by Java when the program first starts.
	 * @param args any command line arguments
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
		this.size(this.sizeX, this.sizeY); 
	}

	/**
	 * Method to compose the first 'frame' of the app
	 */
	public void setup() {

		//SETS DEFAULT SETTINGS
		setDefaults();
		
		//INSTATIATES GAME OBJECTS
		player1 = new Player();
		obstacle1 = new Obstacle();
		newWall = obstacle1.generateNewWall();

		//GENERATES LAYERS USED IN THE GAME
		generateLayers();

	}

	/**
	 * This method is called repeatedly many times per second (usually 30 times per second by default) for the lifetime of the app.
	 */
	public void draw() {
		
		//RESETS DEFAULT SETTINGS EACH FRAME
		setDefaults();

		//DRAWS OBJECTS TO THE SCREEN
		drawBackground();
		drawObstacle();
		drawPlayer();
		drawScoreBoard();

		//CHECKS IF PLAYER AND OBSTACLE HAVE COLLIDED
		//IF THEY COLLIDED THEN GAME OVER
		//TAB to restart in KeyPressed overload
		if(collision()) {
			collision = true;
			gameOver = true;
			gameOverMessage();
		}
		//OTHERWISE UPDATE PLAYER AND OBSTACLE LOCATIONS AND THE SCORE
		else{
			updateObstacleX();
			updatePlayerY();
			updateScore();
		}
	}

	/**
	 * This method sets the given default settings where/when called
	 * This is useful to ensure that particular settings (Stroke,fill, rectMode, etc) do not carry over into other functions unintentionally
	 */
	public void setDefaults() {
		ellipseMode(CENTER);
		rectMode(CENTER);
		fill(255);
		stroke(0);
	}
	

	/**
	 * This method generates the layers of the game. POPULATES: layer_y_values and player_y_values
	 * these arrays are used to ensure accurate player movement
	 */
	public void generateLayers() {
		for(int i = 0; i < layer_y_values.length;i++) {
			layer_y_values[i] = SIZE_Y-(i*layerHeight);
			player_y_values[i] = SIZE_Y-(i*layerHeight) - layerHeight/2;
		}
	}
	

	/**
	 * This method draws the background of the game and calls the drawLayerLines() function
	 * drawLayerLines() can and should be turned off at some point
	 */
	public void drawBackground() {

		//fill the background with this color (specified in 8-bit R, G, B values)
		this.background(255, 255, 255); //white

		drawLayerLines(true);

	}
	

	/**
	 * This method draws a part of the background called the layer lines
	 * The layers lines are used for debugging and will be commented out when the game is in its final version
	 * @param player if true, window displays both black layer lines and red player lines, if false only shows black layer lines
	 */
	public void drawLayerLines(boolean player){

		//LOOPS THROUGH LAYER Y VALUES ARRAY AND DRAWS LINES ON THE SCREEN TO INDICATE THEIR LOCATION
		for(int i=0;i<layer_y_values.length;i++) {
			stroke(0);
			fill(0);
			line(30,layer_y_values[i],sizeX,layer_y_values[i]);
			textSize(10);
			text(layer_y_values[i],10,layer_y_values[i]+2);
			//IF PLAYER IS SET TO TRUE, THIS FUNCTION ALSO DRAWS PLAYER LINES ON THE SCREEN (MIDPOINTS INDICATES WHERE THE PLAYER CAN BE DRAWN)
			if(player) {
				stroke(255,0,0);
				fill(255,0,0);
				line(30,player_y_values[i],sizeX,player_y_values[i]);
				text(player_y_values[i],10,player_y_values[i]+2);
			}
		}
		//SINCE THERE IS A LOT OF CHANGING COLORS, THE PROGRAM RESETS THE DEFAULTS AS A CHECK
		setDefaults();
	}
	
	
	

	/**
	 * This method draws the obstacle with a color and changing X position (moving left)
	 */
	public void drawObstacle() {

		//LOOPS THROUGH EACH BRICK IN THE WALL
		for(int i=0;i<GameWindow.NUM_LAYERS;i++) {
			
			//SET THE FILL TO THE WALL'S BRICK'S ASSIGNED COLOR
			fill(newWall[i].getFill()[0],newWall[i].getFill()[1],newWall[i].getFill()[2]);

			//DRAWS BRICK IN CORRECT POSITION 
			rect((float)(newWall[i].getPosX()),newWall[i].getPosY(),newWall[i].getWidth(),newWall[i].getHeight());
		}
	}
	

	/**
	 * This method draws the player with a color and changing Y position (moving up or down)
	 * Drawing method changes based on whether the user is a Square, Circle, or Triangle
	 * The Triangle also has two versions: upside down (when on the ceiling) and right side up (When on the floor)
	 */
	public void drawPlayer() {

		
		//SET THE FILL TO THE PLAYER'S SHAPE'S ASSIGNED COLOR
		this.fill(player1.getShape().getFill()[0],player1.getShape().getFill()[1],player1.getShape().getFill()[2]);
		//DRAW CIRCLE
		if(player1.getShape().getName().equalsIgnoreCase("Circle")) {
			ellipse(player1.getPosX(),player1.getPosY(),player1.getShape().getWidth(),player1.getShape().getHeight());
		}
		//DRAW SQUARE
		else if(player1.getShape().getName().equalsIgnoreCase("Square")) {
			rect(player1.getPosX(),player1.getPosY(),player1.getShape().getWidth(),player1.getShape().getHeight());
		}
		//DRAW TRIANGLE
		else if(player1.getShape().getName().equalsIgnoreCase("Triangle")) {
			//DRAW UPSIDEDOWN TRIANGLE
			if(!player1.getShape().isUpsidedown())triangle(player1.getPosX(),player1.getPosY()+player1.getShape().getHeight()/2,player1.getShape().getWidth()+player1.getPosX(),player1.getPosY()+player1.getShape().getHeight()/2,player1.getPosX()+player1.getShape().getWidth()/2,player1.getPosY()-player1.getShape().getHeight()/2);
			//DRAW RIGHT SIDE UP TRIANGLE
			else if(player1.getShape().isUpsidedown())triangle(player1.getPosX(),player1.getPosY()-player1.getShape().getHeight()/2,player1.getShape().getWidth()+player1.getPosX(),player1.getPosY()-player1.getShape().getHeight()/2,player1.getPosX()+player1.getShape().getWidth()/2,player1.getPosY()+player1.getShape().getHeight()/2);
		}

	}
	

	/**
	 * This method draws the score in the window and visually outputs whether the highScore was reached (turns the score box yellow)
	 */
	public void drawScoreBoard() {
		
		rectMode(CORNER);
		
		//IF HIGHSCORE IS REACHED, TURN THE COUNTER TO YELLOW
		//IF THIS HAPPENS, HIGHSCORE AND SCORE WOULD BEGIN CHANGING SIMULTANOUSLY 
		if(highscoreReached){
			fill(255, 243, 20);
		}
		//OTHERWISE THE HIGHSCORE COUNTER WILL BE WHITE
		else fill(255);
		stroke(0);
		
		//DISPLAY HIGHSCORE
		rect(sizeX-sizeX/6,8,95,30);
		fill(0);
		textSize(12);
		text("Highscore: "+highscore,sizeX-50,25);
		
		//DISPLAY SCORE
		rectMode(CORNER);
		textAlign(CENTER);
		textSize(36);
		text(score,sizeX/2,50);
		fill(255);
	}
	

	/**
	 * This method detects whether the player, while passing through the obstacle, has collided with the obstacle
	 * @return true if player has collided with an obstacle
	 * @return false if player has not collided with an obstacle while passing through it
	 */
	public boolean collision(){

		//PLAYER VARIABLES
		//creates local reference variables to make collision code shorter and easier to read
		int frontOfPlayer = player1.getPosX()+player1.getShape().getWidth()/2;
		int backOfPlayer = player1.getPosX()-player1.getShape().getWidth()/2;
		int topOfPlayer = player1.getPosY()-player1.getShape().getHeight()/2;
		int bottomOfPlayer = player1.getPosY() + player1.getShape().getHeight()/2;

		//LOOPS THROUGH EACH BRICK IN OBSTACLE WALL TO SEE IF PLAYER HAS COLLIDED WITH THAT BRICK
		//IN THIS VERSION OF THE GAME THERE ARE 8 BRICKS PER WALL
		for(int i = 0;i<newWall.length;i++) {

			//OBSTACLE VARIABLES
			//creates local reference variables to make collision code shorter and easier to read
			double frontOfObstacle = newWall[i].getPosX()-newWall[i].getWidth()/2;
			double backOfObstacle = newWall[i].getPosX() + newWall[i].getWidth()/2;
			double bottomOfObstacle = newWall[i].getPosY() + newWall[i].getHeight()/2;
			double topOfObstacle = newWall[i].getPosY()-newWall[i].getHeight()/2;

			//IF PLAYER IS OVERLAPPING WITH THE OBSTACLE WALL
			if(frontOfPlayer >= frontOfObstacle && backOfPlayer <= backOfObstacle) {
				//IF THE PLAYER IS OVERLAPPING WITH A SPECIFIC BRICK 
				if((topOfPlayer <= bottomOfObstacle && topOfPlayer >= topOfObstacle)||(bottomOfPlayer <= bottomOfObstacle && bottomOfPlayer >= topOfObstacle)) {
					
					//EXCEPTION CASES (per color)
					//IF THE PLAYER IS NOT ALLOWED TO PASS THROUGH THAT BRICK, THEN THERES A COLLISION
					if(newWall[i].getValue()==1)return true;
					else if(newWall[i].getValue()==2 && !(player1.getShape().getName().equalsIgnoreCase("Circle")))return true;//circle can pass through green,
					else if(newWall[i].getValue()==3 && !(player1.getShape().getName().equalsIgnoreCase("Square")))return true; //triangle can pass through red
					else if(newWall[i].getValue()==4 && !(player1.getShape().getName().equalsIgnoreCase("Triangle")))return true; //square can pass through blue
					
				}
			} 
		}
		//OTHERWISE WE RETURN FALSE (NO COLLISION)
		return false;
	}

	/**
	 * This method draws the gameOver message in the window ("GAME OVER. PRESS TAB TO START AGAIN")
	 * @see keyPressed() for TAB functionality
	 */
	public void gameOverMessage(){
		//BLACK BOX
		fill(0);
		rectMode(CENTER);
		textAlign(CENTER);
		rect(sizeX/2,sizeY/2,sizeX,sizeY);
		
		//WHITE TEXT
		fill(255);
		textSize(60);
		text("GAME OVER",sizeX/2,sizeY/2);
		textSize(20);
		text("Press TAB to replay",sizeX/2,sizeY-60);
	}

	/**
	 * This method updates the Obstacle X position (moving to the left)
	 * It also updates the score, obstacle position, and switches to new levels
	 */
	
	public void updateObstacleX(){

		//SET POSITION OF OBSTACLE
		obstacle1.setPosX(obstacle1.getPosX()-obstacle1.getSpeed());
		
		//IF OBSTACLE HITS BACK WALL THEN USER HAS PASSED IT AND SCORE INCREASES BY ONE
		if(obstacle1.getPosX() < -obstacle1.getWidth()) {
			score+=1;
			
			//EVERY 10 POINTS THE OBSTACLE GETS SET FARTHER BACK TO ACCOMODATE PLAYER SWITCHING TO NEW SHAPE
			if(score%10 != 0)obstacle1.setPosX(sizeX+obstacle1.getWidth()*2);
			else obstacle1.setPosX(sizeX*3);
			
			//Level 4: random obstacles at faster speed
			if(score>=40) {
				newWall = obstacle1.generateNewWall();
				obstacle1.setSpeed(7);
			}
			else if(score>=30)newWall = obstacle1.generateNewWall(4); //Level 3: red triangle obstacles
			else if(score>=20)newWall = obstacle1.generateNewWall(3); //Level 3: blue square obstacles
			else if(score>=10)newWall = obstacle1.generateNewWall(2); //Level 2: green circle obstacles
			else if(score<10)newWall = obstacle1.generateNewWall(); //Level 1: random obstacles
		}
	}

	/**
	 * This method updates the Player Y position (moving up or down) based on keyPresses (these movements are different for the different shapes)
	 * 
	 * If the Player hits the ceiling, maxHeight is reached, if the player hits the floor, minHeight is reached otherwise they are false
	 */
	public void updatePlayerY(){

		//REACHED MAX HEIGHT
		if( player1.getPosY() <= player_y_values[NUM_LAYERS-1])player1.setReachedMaxHeight(true);
		else player1.setReachedMinHeight(false);

		//REACHED MIN HEIGHT
		if(player1.getPosY() >= player_y_values[0])player1.setReachedMinHeight(true);
		else player1.setReachedMaxHeight(false);

		//TRIANGLE && SQUARE MOVEMENT
		if(!player1.getShape().getName().equalsIgnoreCase("Circle")) {
			
			//SNAP TO CLOSEST LATER
			int closestLayer = findClosestLayer_R(player_y_values,player1.getPosY()); //RECURSIVE FUNCTION
			//int closestLayer = findClosestLayer(player1.getPosY()); //NOT RECURSIVE VERSION
			
			player1.setPosY(closestLayer);
			player1.setCurrentLayer(NUM_LAYERS-(closestLayer/layerHeight)-1); //must set current layer

			//if(player1.getPosY() <= player_y_values[NUM_LAYERS-1])player1.setPosY(player_y_values[NUM_LAYERS-1]); //NO LONGER NEEDED
		}

		//CIRCLE MOVEMENT
		else {
			
			//KEY PRESSED
			if(keyPressed && keyCode == UP)player1.setPosY(player1.getPosY()-(int)player1.getShape().getJumpSpeed());//JUMPING
			else if(keyPressed && keyCode == DOWN)player1.setPosY(player1.getPosY()+(int)player1.getShape().getJumpSpeed()*2); //DIVING
			else player1.setPosY(player1.getPosY()+(int)player1.getShape().getFallSpeed());//FALLING
			
			//CEILING AND FLOOR LIMITS
			if(player1.getPosY()>=player_y_values[0])player1.setPosY(player_y_values[0]);
			if(player1.getPosY()<=player_y_values[NUM_LAYERS-1])player1.setPosY(player_y_values[NUM_LAYERS-1]);

		}

		//UPSIDE DOWN, APPLIES TO ALL SHAPES
		if(player1.getPosY()>=SIZE_Y/2)player1.getShapeArray()[3].setUpsidedown(false);
		else player1.getShapeArray()[3].setUpsidedown(true);

	}

	/**
	 * SNAP TO CLOSEST LAYER ORIGINAL FUNCTION - COMMENTED OUT AS RECUSRIVE FUNCITON NOW USED
	 */
	//	public int findClosestLayer(int playerY) {
	//		
	//		//IF NUMBER > 325
	//		if(playerY > player_y_values[0]) {
	//			return player_y_values[0];
	//		}
	//		//IF NUMBER < 25
	//		else if(playerY < player_y_values[player_y_values.length-1]) {
	//			return player_y_values[NUM_LAYERS-1];
	//		}
	//	
	//		
	//		else {
	//			for(int i = 0;i < NUM_LAYERS-1;i++) {
	//				
	//				if(playerY > player_y_values[i]) {
	//					if(playerY-player_y_values[i] <= player_y_values[i-1]-playerY) {
	//						return player_y_values[i];
	//					}
	//					else{
	//						return player_y_values[i-1];
	//					}
	//				}
	//			}	
	//		}
	//		return 0;
	//	}


	/**
	 * This method finds the closest layer that player can snap to using the player_y_values array
	 * 
	 * @return the closest layer y value for shape's current position using recursion
	 */
	public int findClosestLayer_R(int[] y_values,int target) {

		//IF REACHED MINIMUM LENGTH OF ARRAY, RETURN INDEX 0
		if(y_values.length==1)return y_values[0];

		int m = y_values.length/2; //m is the middle index
		int mid = y_values[m]; // mid is the value of the middle index

		if(target == mid) return mid; //if the shape is exactly on the middle value return that value

		//OTHERWISE, SPLIT ARRAY INTO TOP AND BOTTOM HALF
		int[] lowerHalf = Arrays.copyOfRange(y_values, 0, m);
		int[] upperHalf = Arrays.copyOfRange(y_values, m, y_values.length);

		//IF TARGET IS LESS THAN MID, RESUIVELY CALL FUNCTION FOR THE UPPER HALF OF THE ARRAY
		if(target < mid) return findClosestLayer_R(upperHalf,target);
		//ELSE RECURSIVELY CALL FUNCTION USING THE BOTTOM HALF OF THE ARRAY
		else return findClosestLayer_R(lowerHalf,target);
	}


	/**
	 * This method simply checks if the highscore was reached and updates the highscoreReached value(Recorded in a boolean)
	 */
	public void updateScore() {
		
		if (score!= 0 && score >= highscore) {
			highscore = score;
			highscoreReached = true;
		}
	}

	/**
	 * This method overwrites the Processing keyTyped() method to introduce changing shapes and moving objects
	 * OVERVIEW: SPACEBAR TO CHANGE SHAPE, UP AND DOWN TO MOVE, AND TAB TO REPLAY ONCE GAME OVER
	 */
	public void keyPressed() {
		
		//IF THE GAME ISN'T OVER, MOVEMENT AND CHANGE SHAPE ARE POSSIBLE
		if(!gameOver) {
			
			//changes shape
			if(key == ' ') {
				if (player1.getShape().getName().equalsIgnoreCase("Circle"))player1.setShape(2);
				else if(player1.getShape().getName().equalsIgnoreCase("Square"))player1.setShape(3);
				else if (player1.getShape().getName().equalsIgnoreCase("Triangle"))player1.setShape(1);
			}

			//TRIANGLE
			if(player1.getShape().getName().equalsIgnoreCase("Triangle")) {
				//UP
				if(keyCode == UP) {
					player1.getShape().setUpsidedown(true); //switches triangle shape as it passes through midpoint
					player1.setPosY(player_y_values[NUM_LAYERS-1]);
				}
				//DOWN
				else if(keyCode == DOWN) {
					player1.getShape().setUpsidedown(false); //switches triangle shape as it passes through midpoint
					player1.setPosY(player_y_values[0]);
				}
			}

			//SQUARE
			else if(player1.getShape().getName().equalsIgnoreCase("Square")) {
				//UP - if not at top or reached max height
				if(!player1.isReachedMaxHeight() && player1.getCurrentLayer() != NUM_LAYERS-1 && keyCode == UP) {
					player1.setCurrentLayer(player1.getCurrentLayer()+1);
					player1.setPosY(player_y_values[player1.getCurrentLayer()]);
				}
				//DOWN - if not at the bottom or reached min height
				else if(!player1.isReachedMinHeight() && player1.getCurrentLayer()!= 0 && keyCode == DOWN) {
					player1.setCurrentLayer(player1.getCurrentLayer()-1);
					player1.setPosY(player_y_values[player1.getCurrentLayer()]);
				}

			}

		}
		//IF THE GAME IS OVER, TAB CALLS GAMERESET() FUNCTION
		else {
			if(keyCode == TAB)gameReset();
		}
	}

	/**
	 * This method resets the game so that it appears as though the player is starting anew.
	 * Every variable is reset except for highScore, which is never reset
	 */
	public void gameReset(){
		newWall = obstacle1.generateNewWall();
		obstacle1.setSpeed(4.7);
		player1.setShape(1);
		player1.setPosY(player_y_values[NUM_LAYERS-1]);
		player1.setCurrentLayer(NUM_LAYERS-1);
		obstacle1.setPosX(sizeX*3);
		gameOver = false;
		score = 0;
		highscoreReached = false;
		collision = false;
	}


}

