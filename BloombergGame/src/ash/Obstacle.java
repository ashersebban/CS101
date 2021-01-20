package ash;

import java.util.Random;
import java.util.Arrays;

public class Obstacle {
	
	private int numLayers = 8;
	private int obstacleX;
	private int obstacleSpeed;
	
	

	private Random rand = new Random();
	private int[] layers = new int[numLayers];
	private int[] final_array= new int[layers.length];
	
	private int[] generateRandomObstacles(int[] layers) {
		System.out.println("Random Obstacles:");
		for(int i = 0;i<layers.length;++i) {
			int rand_on_off = rand.nextInt(2);
			System.out.println(rand_on_off);
			final_array[i] = rand_on_off;
		}
		System.out.println(); 
		return final_array;
		
	}
	
	private int[] generateTriangleObstacles(int[] layers) {
		System.out.println("Triangle Obstacles:");
		Arrays.fill(final_array,1);
		int TOType = rand.nextInt(3);
		System.out.println("TOTYPE: "+TOType);
		if(TOType == 0)final_array[0] = 0;
		else if(TOType == 1) final_array[final_array.length-1] = 0;
		else {
			final_array[0] = 0;
			final_array[final_array.length-1] = 0;
		}
		for(int i = 0;i<final_array.length;i++) System.out.println(final_array[i]);
		System.out.println();
		return final_array;
	}
	
	private int[] generateCircleObstacles(int[] layers) {
		int barrierGap = rand.nextInt(layers.length-2)+1;
		System.out.println("Circle Obstacles:");
		System.out.println("Gap from top: "+barrierGap);
		
		for(int i = 0;i< final_array.length;i++) {
			if(i<=barrierGap-1) {
				final_array[i]=0;
			}
			else final_array[i] = 1;
			System.out.println(final_array[i]);
		}
		System.out.println();
		return final_array;
	}
	
	public int[] generateNewWall() {
		int numOfObs = 3;
		int randObs = rand.nextInt(numOfObs)+1;
		if(randObs == 1)return generateRandomObstacles(layers);
		else if(randObs ==2)return generateTriangleObstacles(layers);
		else if (randObs ==3)return generateCircleObstacles(layers);
		else return new int[0];
	}
	
	public static void main(String[] args) {
		
		Obstacle o = new Obstacle (400);
		o.generateNewWall();
		
		
	}
	
	public Obstacle(int w) {
		this.obstacleX = w + 50;
		this.obstacleSpeed = w/100;
	}
	
	public int getObstacleX() {
		return obstacleX;
	}

	public void setObstacleX(int obstableX) {
		this.obstacleX = obstableX;
	}

	public int getNumLayers() {
		return numLayers;
	}

	public void setNumLayers(int numLayers) {
		this.numLayers = numLayers;
	}

	public int getObstacleSpeed() {
		return obstacleSpeed;
	}

	public void setObstacleSpeed(int obstacleSpeed) {
		this.obstacleSpeed = obstacleSpeed;
	}
	
	
	
	

}
