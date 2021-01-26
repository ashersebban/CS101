package ash;

import java.util.Random;
import java.util.Arrays;

public class Obstacle {
	
	//CLASS
	private Random rand = new Random();
	private int[] wall_values = new int[GameWindow.NUM_LAYERS];
	private Brick[] wall_bricks = new Brick[wall_values.length];
	
	//OBJECT
	private double posX;
	private int width = 50;
	private int height = 50;
	private double speed = 4.7;
	
	
	
	//TRIANGLE OBSTACLES
	private int[] generateTriangleObstacles(int[] layers) {
		Arrays.fill(wall_values,1);
		int TOType = rand.nextInt(3);
		if(TOType == 0)wall_values[0] = 3;
		else if(TOType == 1) wall_values[wall_values.length-1] = 3;
		else {
			wall_values[0] = 3;
			wall_values[wall_values.length-1] = 3;
		}
		return wall_values;
	}
	
	//CIRCLE OBSTACLES
	private int[] generateCircleObstacles(int[] layers) {
		int circleGap = rand.nextInt(layers.length);
		Arrays.fill(wall_values,1);
		wall_values[circleGap] = 4;
		if(!(circleGap==0))wall_values[circleGap-1]= 4;
		if(!(circleGap==wall_values.length-1))wall_values[circleGap+1]=4;
		//else wall_values[circleGap+1]= 0;
		return wall_values;
	}
	
	//SQUARE OBSTACLES
	private int[] generateSquareObstacles(int[] layers) {
		int squareGap = rand.nextInt(layers.length);
		Arrays.fill(wall_values,1);
		wall_values[squareGap] = 2;
		return wall_values;
	}
	
	
	
	public Brick[] generateNewWall() {
		int numOfObs = 3;
		int randObs = rand.nextInt(numOfObs)+1;
		//if(randObs == 1)return generateRandomObstacles(layers);
		if(randObs == 1)wall_values = generateSquareObstacles(wall_values);
		if(randObs == 2)wall_values = generateTriangleObstacles(wall_values);
		if (randObs == 3)wall_values = generateCircleObstacles(wall_values);
		
		for(int i = 0; i<wall_values.length;i++) {
			Brick newBrick = new Brick(posX,height/GameWindow.NUM_LAYERS*i+height/GameWindow.NUM_LAYERS/2, width, height/GameWindow.NUM_LAYERS,wall_values[i]);
			wall_bricks[i] = newBrick;
		}
		return wall_bricks;
		
	}
	
	public Brick[] generateNewWall(int obstacleType) {
		if(obstacleType == 1) {
			wall_values = generateSquareObstacles(wall_values);
			speed = 8;
		}
		if(obstacleType == 2) {
			wall_values = generateTriangleObstacles(wall_values);
			speed = 15;
		}
		if (obstacleType == 3) {
			wall_values = generateCircleObstacles(wall_values);
			speed = 9;
		}
		
		for(int i = 0; i<wall_values.length;i++) {
			Brick newBrick = new Brick(posX,height/GameWindow.NUM_LAYERS*i+height/GameWindow.NUM_LAYERS/2, width, height/GameWindow.NUM_LAYERS,wall_values[i]);
			wall_bricks[i] = newBrick;
		}
		return wall_bricks;
		
	}
	
	
	//MAIN
	public static void main(String[] args) {
		
		
		
	}
	
	//CONSTRUCTOR 
	public Obstacle(double sizeX,int sizeY) {
		this.posX = sizeX*2;
		this.width = 50;
		this.height = sizeY;
		this.speed = speed;
		
	}
	
	
	//GETTERS AND SETTERS
	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
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
