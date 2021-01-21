package ash;

import java.util.Random;
import java.util.Arrays;

public class Obstacle {
	
	//CLASS
	private int numLayers = 8;
	private Random rand = new Random();
	private int[] layers = new int[numLayers];
	private int[] wall_values = new int[layers.length];
	private Brick[] wall_bricks = new Brick[wall_values.length];
	
	//OBJECT
	private int posX;
	private int width = 50;
	private int height = 50;
	private int speed ;
	
	
	//RANDOM OBSTACLES
	private int[] generateRandomObstacles(int[] layers) {
		for(int i = 0;i<layers.length;++i) {
			int rand_on_off = rand.nextInt(2);
			wall_values[i] = rand_on_off;
		}
		return wall_values;
		
	}
	
	//TRIANGLE OBSTACLES
	private int[] generateTriangleObstacles(int[] layers) {
		Arrays.fill(wall_values,1);
		int TOType = rand.nextInt(2);
		if(TOType == 0)wall_values[0] = 3;
		else if(TOType == 1) wall_values[wall_values.length-1] = 3;
//		else {
//			wall_values[0] = 0;
//			wall_values[wall_values.length-1] = 0;
//		}

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
		int numOfObs = 4;
		int randObs = rand.nextInt(numOfObs)+1;
		//if(randObs == 1)return generateRandomObstacles(layers);
		if(randObs == 1)wall_values = generateSquareObstacles(layers);
		else if(randObs == 2)wall_values = generateTriangleObstacles(layers);
		else if (randObs == 3)wall_values = generateCircleObstacles(layers);
		else if(randObs == 4)wall_values = generateSquareObstacles(layers);
		
		for(int i = 0; i<wall_values.length;i++) {
			Brick newBrick = new Brick(posX,height/numLayers*i, width, height/numLayers,wall_values[i]);
			wall_bricks[i] = newBrick;
		}
		return wall_bricks;
		
		
	}
	
	//MAIN
	public static void main(String[] args) {
		
		Obstacle o = new Obstacle (400,600);
		o.generateNewWall();
		
		
	}
	
	//CONSTRUCTOR 
	public Obstacle(int w,int h) {
		this.posX = w + 50;
		this.width = 50;
		this.height = h;
		this.speed = w/150;
		
	}
	
	
	//GETTERS AND SETTERS
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
		for(int i = 0; i< wall_bricks.length;i++) {
			wall_bricks[i].setPosX(this.posX);
		}
	}

	public int getNumLayers() {
		return numLayers;
	}

	public void setNumLayers(int numLayers) {
		this.numLayers = numLayers;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	
	
	
	

}
