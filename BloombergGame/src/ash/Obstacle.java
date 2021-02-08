package ash;

import java.util.Random;
import java.util.Arrays;

/**
 * Obstacle class that generates obstacles in the GameWindow
 * @author Asher Sebban
 * @version 3
 */
public class Obstacle {
	
	//VARIABLES
	private Random rand = new Random();
	
	private int[] wall_values = new int[GameWindow.NUM_LAYERS];
	private Brick[] wall_bricks = new Brick[wall_values.length];
	
	private double posX;
	private int width;
	private int height; 
	private double speed; // default speed
	
	//CONSTRUCTOR 
	public Obstacle() {
		this.posX = GameWindow.SIZE_X*2;
		this.width =  GameWindow.SIZE_Y/GameWindow.NUM_LAYERS;
		this.height = GameWindow.SIZE_Y;
		this.speed = 4.7; //default speed
		
	}
	
	/**
	 * This method generates the triangle obstacles 
	 * @return an array of integers that will be used to create wall bricks
	 */
	private int[] generateTriangleObstacles(int[] layers) {
		Arrays.fill(wall_values,1);
		int TOType = rand.nextInt(3);
		if(TOType == 0)wall_values[0] = 4;
		else if(TOType == 1) wall_values[wall_values.length-1] = 4;
		else {
			wall_values[0] = 4;
			wall_values[wall_values.length-1] = 4;
		}
		return wall_values;
	}
	
	/**
	 * This method generates the Cricle obstacles 
	 * @return an array of integers that will be used to create wall bricks
	 */
	private int[] generateCircleObstacles(int[] layers) {
		int circleGap = rand.nextInt(layers.length);
		Arrays.fill(wall_values,1);
		wall_values[circleGap] = 2;
		if(!(circleGap==0))wall_values[circleGap-1]= 2;
		if(!(circleGap==wall_values.length-1))wall_values[circleGap+1]=2;
		return wall_values;
	}
	
	/**
	 * This method generates the Square obstacles 
	 * @return an array of integers that will be used to create wall bricks
	 */
	private int[] generateSquareObstacles(int[] layers) {
		int squareGap = rand.nextInt(layers.length);
		Arrays.fill(wall_values,1);
		wall_values[squareGap] = 3;
		return wall_values;
	}
	
	
	/**
	 * This method generates a new wall that is either a square, triangle, or circle obstacle - called randomly
	 * @return an array of bricks based on the wall values array
	 */
	public Brick[] generateNewWall() {
		int numOfObs = 3;
		int randObs = rand.nextInt(numOfObs)+2;
		if(randObs == 2)wall_values = generateSquareObstacles(wall_values);
		if(randObs == 3)wall_values = generateTriangleObstacles(wall_values);
		if (randObs == 4)wall_values = generateCircleObstacles(wall_values);
		
		for(int i = 0; i<wall_values.length;i++) {
			Brick newBrick = new Brick(posX,height/GameWindow.NUM_LAYERS*i+height/GameWindow.NUM_LAYERS/2, width, height/GameWindow.NUM_LAYERS,wall_values[i]);
			wall_bricks[i] = newBrick;
		}
		return wall_bricks;
		
	}
	
	/**
	 * This method generates that is either a square, triangle, or circle obstacle - called specifically. If so, change speed as well
	 * this will be used for making levels
	 * @return an array of bricks based on the wall values array
	 */
	public Brick[] generateNewWall(int obstacleType) {
		if(obstacleType == 3) {
			wall_values = generateSquareObstacles(wall_values);
			speed = 8;
		}
		if(obstacleType == 4) {
			wall_values = generateTriangleObstacles(wall_values);
			speed = 15;
		}
		if (obstacleType == 2) {
			wall_values = generateCircleObstacles(wall_values);
			speed = 9;
		}
		
		for(int i = 0; i<wall_values.length;i++) {
			Brick newBrick = new Brick(posX,height/GameWindow.NUM_LAYERS*i+height/GameWindow.NUM_LAYERS/2, width, height/GameWindow.NUM_LAYERS,wall_values[i]);
			wall_bricks[i] = newBrick;
		}
		return wall_bricks;
		
	}
	

	
	//GETTERS AND SETTERS
	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) { //ALSO SETS POSX FOR ALL BRICKS IN THE WALL_BRICKS ARRAY
		this.posX = posX;
		for(int i = 0; i< wall_bricks.length;i++) {
			wall_bricks[i].setPosX(this.posX);
		}
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


}
