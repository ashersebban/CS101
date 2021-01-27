package ash;

import java.util.Arrays;
import processing.core.*;

/**
 * A simple class that inherits from Processing's PApplet class and displays an oscillating circle that bounces back and forth
 * @author Foo Barstein
 * @version 2
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
		this.size(this.sizeX, this.sizeY); 
	}

	/**
	 * Method to compose the first 'frame' of the app
	 */
	public void setup() {

		setDefaults();
		player1 = new Player();
		obstacle1 = new Obstacle();
		newWall = obstacle1.generateNewWall();

		generateLayers();

		for(int i = 0 ; i< player_y_values.length;i++) {
			System.out.println("Index: "+i+" Value: "+player_y_values[i]);
		}

	}

	/**
	 * This method is called repeatedly many times per second (usually 30 times per second by default) for the lifetime of the app.
	 */
	public void draw() {

		setDefaults();

		drawBackground();
		drawObstacle();
		drawPlayer();
		drawScoreBoard();

		if(collision()) {
			collision = true;
			gameOver = true;
			gameOverMessage();
		}
		else{
			updateObstacleX();
			updatePlayerY();
			updateScore();
		}
	}

	/**
	 * This method draws the background of the game and calls the drawLayerLines() function
	 * @param args any command line arguments (not used)
	 */
	public void setDefaults() {
		ellipseMode(CENTER);
		rectMode(CENTER);
		fill(255);
		stroke(0);
	}

	/**
	 * This method draws the background of the game and calls the drawLayerLines() function
	 * @param args any command line arguments (not used)
	 */
	public void generateLayers() {

		for(int i = 0; i < layer_y_values.length;i++) {

			layer_y_values[i] = SIZE_Y-(i*layerHeight);
			player_y_values[i] = SIZE_Y-(i*layerHeight) - layerHeight/2;
		}
	}

	/**
	 * This method draws the background of the game and calls the drawLayerLines() function
	 * @param args any command line arguments (not used)
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

		for(int i=0;i<layer_y_values.length;i++) {
			stroke(0);
			fill(0);
			line(30,layer_y_values[i],sizeX,layer_y_values[i]);
			textSize(10);
			text(layer_y_values[i],10,layer_y_values[i]+2);
			if(player) {
				stroke(255,0,0);
				fill(255,0,0);
				line(30,player_y_values[i],sizeX,player_y_values[i]);
				text(player_y_values[i],10,player_y_values[i]+2);
			}
		}
		setDefaults();
	}

	/**
	 * This method draws the obstacle with a color and changing X position (moving left)
	 * The setDefaults() method is used as a safety precaution in case the rectMode() carries out of the function
	 * @param args any command line arguments (not used)
	 */
	public void drawObstacle() {

		rectMode(CENTER);
		//OBSTACLE
		for(int i=0;i<GameWindow.NUM_LAYERS;i++) {
			//color
			fill(newWall[i].getFill()[0],newWall[i].getFill()[1],newWall[i].getFill()[2]);

			//shape and position
			rect((float)(newWall[i].getPosX()),newWall[i].getPosY(),newWall[i].getWidth(),newWall[i].getHeight());
		}
	}

	/**
	 * This method draws the player with a color and changing Y position (moving up or down)
	 * Drawing method changes based on whether the user is a Square, Circle, or Triangle
	 * The Triangle also has two versions: upside down (when on the ceiling) and right side up (When on the floor)
	 */
	public void drawPlayer() {


		rectMode(CENTER);
		//set the fill color (the color which solid shapes will be filled with)
		this.fill(player1.getShape().getFill()[0],player1.getShape().getFill()[1],player1.getShape().getFill()[2]);

		//IF PLAYER IS A CIRCLE
		if(player1.getShape().getName().equalsIgnoreCase("Circle")) {
			this.fill(0,255,0);
			ellipse(player1.getPosX(),player1.getPosY(),player1.getShape().getWidth(),player1.getShape().getHeight());
		}

		//IF PLAYER IS A SQUARE
		else if(player1.getShape().getName().equalsIgnoreCase("Square")) {
			this.fill(0,0,255);
			rect(player1.getPosX(),player1.getPosY(),player1.getShape().getWidth(),player1.getShape().getHeight());
		}


		//IF PLAYER IS A TRIANGLE
		else if(player1.getShape().getName().equalsIgnoreCase("Triangle")) {
			this.fill(255,0,0);
			if(!player1.getShape().isUpsidedown())triangle(player1.getPosX(),player1.getPosY()+player1.getShape().getHeight()/2,player1.getShape().getWidth()+player1.getPosX(),player1.getPosY()+player1.getShape().getHeight()/2,player1.getPosX()+player1.getShape().getWidth()/2,player1.getPosY()-player1.getShape().getHeight()/2);
			else if(player1.getShape().isUpsidedown())triangle(player1.getPosX(),player1.getPosY()-player1.getShape().getHeight()/2,player1.getShape().getWidth()+player1.getPosX(),player1.getPosY()-player1.getShape().getHeight()/2,player1.getPosX()+player1.getShape().getWidth()/2,player1.getPosY()+player1.getShape().getHeight()/2);
		}

	}

	/**
	 * This method draws the score in the window and visually outputs whether the highScore was reached (turns the score box yellow)
	 * 
	 * @param args any command line arguments (not used)
	 */
	public void drawScoreBoard() {

		rectMode(CORNER);
		if(highscoreReached){
			fill(255, 243, 20);
		}
		else fill(255);
		stroke(0);
		rect(sizeX-sizeX/6,8,95,30);
		fill(0);
		textSize(12);
		text("Highscore: "+highscore,sizeX-50,25);
		rectMode(CORNER);
		textAlign(CENTER);
		textSize(36);
		text(score,sizeX/2,50);
		fill(255);
	}

	/**
	 * This method detects whether the player, while passing through the obstacle, has collided with the obstacle
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
					//IF THE PLAYER IS NOT ALLOWED TO PASS THROUGH THAT BRICK, THEN THERES A COLLISION
					if(newWall[i].getValue()==1)return true;
					else if(newWall[i].getValue()==2 && !(player1.getShape().getName().equalsIgnoreCase("Square")))return true;
					else if(newWall[i].getValue()==3 && !(player1.getShape().getName().equalsIgnoreCase("Triangle")))return true;
					else if(newWall[i].getValue()==4 && !(player1.getShape().getName().equalsIgnoreCase("Circle")))return true;
				}
			} 
		}
		//OTHERWISE WE RETURN FALSE (NO COLLISION)
		return false;
	}

	/**
	 * This method draws the gameOver message in the window ("GAME OVER. PRESS TAB TO START AGAIN")
	 * 
	 * @param args any command line arguments (not used)
	 */
	public void gameOverMessage(){
		stroke(255);
		fill(0);
		rectMode(CENTER);
		rect(sizeX/2,sizeY/2,sizeX,sizeY);
		//rectMode(CORNER);
		fill(255);
		textAlign(CENTER);
		textSize(60);
		text("GAME OVER",sizeX/2,sizeY/2);
		textSize(20);
		text("Press TAB to replay",sizeX/2,sizeY-60);
		fill(255);
	}

	/**
	 * This method updates the Obstacle X position (moving to the left)
	 * If the Obstacle hits the left wall, the player scores a point, a new wall layout is generated and... 
	 * ...the obstacle's x position is set to behind the right wall
	 * @param args any command line arguments (not used)
	 */
	public void updateObstacleX(){

		obstacle1.setPosX(obstacle1.getPosX()-obstacle1.getSpeed());
		if(obstacle1.getPosX() < -obstacle1.getWidth()) {
			score+=1;
			if(score%10 != 0)obstacle1.setPosX(sizeX+obstacle1.getWidth()*2);
			else obstacle1.setPosX(sizeX*3);
			if(score>=40) {
				newWall = obstacle1.generateNewWall();
				obstacle1.setSpeed(7);
			}
			else if(score>=30)newWall = obstacle1.generateNewWall(2);
			else if(score>=20)newWall = obstacle1.generateNewWall(1);
			else if(score>=10)newWall = obstacle1.generateNewWall(3);
			else if(score<10)newWall = obstacle1.generateNewWall();

		}
	}

	/**
	 * This method updates the Player Y position (moving up or down) based on keyPresses (these movements are different for the different shapes)
	 * 
	 * If the Player hits the ceiling, maxHeight is reached, if the player hits the floor, minHeight is reached otherwise they are false
	 * ...the obstacle's x position is set to behind the right wall
	 * @param args any command line arguments (not used)
	 */
	public void updatePlayerY(){


		//REACHED MAX HEIGHT
		if( player1.getPosY() <= player_y_values[NUM_LAYERS-1]) {
			player1.setReachedMaxHeight(true);
		}
		else player1.setReachedMinHeight(false);

		//CANNOT MOVE PAST sizeY
		if(player1.getPosY() >= player_y_values[0]) {
			player1.setReachedMinHeight(true);

		}
		else player1.setReachedMaxHeight(false);

		//TRIANGLE && SQUARE MOVEMENT
		if(!player1.getShape().getName().equalsIgnoreCase("Circle")) {
			//int closestLayer = findClosestLayer(player1.getPosY());
			int closestLayer = findClosestLayer_R(player_y_values,player1.getPosY());
			//player1.setCurrentLayer((closestLayer+layerHeight/2)/NUM_LAYERS);

			player1.setPosY(closestLayer);
			player1.setCurrentLayer(NUM_LAYERS-(closestLayer/layerHeight)-1);

			if(player1.getPosY()<=player_y_values[NUM_LAYERS-1])player1.setPosY(player_y_values[NUM_LAYERS-1]);



		}

		//CIRCLE MOVEMENT
		else {

			if(keyPressed && keyCode == UP)player1.setPosY(player1.getPosY()-(int)player1.getShape().getJumpSpeed());
			else player1.setPosY(player1.getPosY()+(int)player1.getShape().getFallSpeed());

			if(player1.getPosY()>=player_y_values[0])player1.setPosY(player_y_values[0]);
			if(player1.getPosY()<=player_y_values[NUM_LAYERS-1])player1.setPosY(player_y_values[NUM_LAYERS-1]);

		}

		if(player1.getPosY()>=SIZE_Y/2)player1.getShapeArray()[3].setUpsidedown(false);
		else player1.getShapeArray()[3].setUpsidedown(true);

	}

	/**
	 * This method updates the Player Y position (moving up or down) based on keyPresses (these movements are different for the different shapes)
	 * 
	 * If the Player hits the ceiling, maxHeight is reached, if the player hits the floor, minHeight is reached otherwise they are false
	 * ...the obstacle's x position is set to behind the right wall
	 * @param args any command line arguments (not used)
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
	 * This method updates the Player Y position (moving up or down) based on keyPresses (these movements are different for the different shapes)
	 * 
	 * If the Player hits the ceiling, maxHeight is reached, if the player hits the floor, minHeight is reached otherwise they are false
	 * ...the obstacle's x position is set to behind the right wall
	 * @param args any command line arguments (not used)
	 */
	public int findClosestLayer_R(int[] y_values,int target) {

		if(y_values.length==1)return y_values[0];

		int m = y_values.length/2;
		int mid = y_values[m];

		if(target == mid) return mid;

		int[] lowerHalf = Arrays.copyOfRange(y_values, 0, m);
		int[] upperHalf = Arrays.copyOfRange(y_values, m, y_values.length);


		if(target < mid) return findClosestLayer_R(upperHalf,target);

		else return findClosestLayer_R(lowerHalf,target);


	}


	/**
	 * This method updates the score and checks if the highscore was reached (Recorded in a boolean)
	 * 
	 * @param args any command line arguments (not used)
	 */
	public void updateScore() {
		if (score!= 0 && score >= highscore) {
			highscore = score;
			highscoreReached = true;
		}
	}

	/**
	 * This method overwrites the Processing keyTyped() method to introduce changing shapes and moving objects
	 * @param args any command line arguments (not used)
	 */
	public void keyPressed() {
		//MOVEMENT
		if(!gameOver) {
			//changes shape
			if(key == 'c') {
				if (player1.getShape().getName().equalsIgnoreCase("Circle")) {
					player1.setShape(2);

				}
				else if(player1.getShape().getName().equalsIgnoreCase("Square")) {
					player1.setShape(3);

				}
				else if (player1.getShape().getName().equalsIgnoreCase("Triangle")) {
					player1.setShape(1);


				}

				player1.debugUpdate();
			}

			//TRIANGLE
			if(player1.getShape().getName().equalsIgnoreCase("Triangle")) {
				//GENERAL MOVEMENT
				if(keyCode == UP) {
					player1.getShape().setUpsidedown(true);
					player1.setPosY(player_y_values[NUM_LAYERS-1]);
					//player1.setReachedMaxHeight(true);
					//player1.moveToLayer(topLayer);
				}
				else if(keyCode == DOWN) {
					player1.getShape().setUpsidedown(false);
					player1.setPosY(player_y_values[0]);
					//player1.setReachedMinHeight(true);
					//player1.moveToLayer(bottomLayer);
				}
			}

			//SQUARE
			else if(player1.getShape().getName().equalsIgnoreCase("Square")) {
				//BELOW HAS ERROR PLEASE HELP
				if(!player1.isReachedMaxHeight() && player1.getCurrentLayer() != NUM_LAYERS-1 && keyCode == UP) {
					player1.setCurrentLayer(player1.getCurrentLayer()+1);
					player1.setPosY(player_y_values[player1.getCurrentLayer()]);
				}
				else if(!player1.isReachedMinHeight() && player1.getCurrentLayer()!= 0 && keyCode == DOWN) {
					player1.setCurrentLayer(player1.getCurrentLayer()-1);
					player1.setPosY(player_y_values[player1.getCurrentLayer()]);
				}
				//else if(key == ' ')System.out.println("SHOOTING BULLET!");

			}

			//CIRCLE
			else if(player1.getShape().getName().equalsIgnoreCase("Circle")) {
				if(keyCode == UP) player1.setPosY(player1.getPosY()-(int)player1.getShape().getJumpSpeed());
				else if(keyCode == DOWN) player1.setPosY(player1.getPosY()+(int)player1.getShape().getJumpSpeed());
				if(!player1.isReachedMinHeight())player1.setPosY(player1.getPosY()+(int)player1.getShape().getFallSpeed());
			}
		}
		else {
			if(keyCode == TAB)gameReset();
		}
	}

	/**
	 * This method resets the game so that it appears as though the player is starting anew.
	 * Every variable is reset except for highScore, which is never reset
	 * 
	 * @param args any command line arguments (not used)
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

