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

	final private int ceiling = 0;
	final private int floor = h;
	final private int leftWall = 0;
	final private int rightWall = w;
	int counter = 0;
	
	
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
	
	int vertical = 10;
	
	int playerWidth = 50;
	int playerHeight = 50;
	int playerX = leftWall + 50;
	int playerY = floor - playerHeight - 10;
	String playerShape = "Square";
	
	boolean jumping = false;
	boolean falling = false; 
	
	int[] color = {255,0,0};
	Square user = new Square("User",playerX,playerY,playerWidth,playerHeight,color);
	
	/**
	 * Method to compose the first 'frame' of the app
	 */
	public void setup() {
		
		
		//fill the background with this color (specified in 8-bit R, G, B values)
		this.background(255, 255, 255); //white

		//set the fill color (the color which solid shapes will be filled with)
		this.fill(color[0],color[1],color[2]); 

	}
	
	public void keyPressed() {
		 if(key == ' ') {
			if(playerShape.equalsIgnoreCase("Square")) {
				playerShape = "Circle";
			}
			else if (playerShape.equalsIgnoreCase("Circle")) {
				playerShape = "Triangle";
			}
			else if (playerShape.equalsIgnoreCase("Triangle")) {
				playerShape = "Square";
			}
			
		}
		if(key == CODED) {
			if(!jumping && !falling && keyCode == UP) {
				jumping = true;
			}
		}
		
	}
	
	
	/**
	 * This method is called repeatedly many times per second (usually 30 times per second by default) for the lifetime of the app.
	 */
	public void draw() {
		
		ellipseMode(CORNER);
		
		//fill the background with this color (specified in 8-bit R, G, B values)
		this.background(255, 255, 255); //white
		
		//set the fill color (the color which solid shapes will be filled with)
		this.fill(color[0],color[1],color[2]);
		
		if(playerShape.equalsIgnoreCase("Square"))rect(playerX,playerY,playerWidth,playerHeight);
		else if(playerShape.equalsIgnoreCase("Circle"))ellipse(playerX,playerY,playerWidth,playerHeight);
		else if(playerShape.equalsIgnoreCase("Triangle"))triangle(playerX,playerY+playerHeight,playerWidth+playerX,playerY+playerHeight,playerX+playerWidth/2,playerY);
		
		if(jumping) {
			playerY -= vertical;
			falling = false;
			
		}
		else if(falling) {
			playerY += vertical;
		}
		
		int topOfPlayer = playerY;
		int bottomOfPlayer = playerY + playerHeight;
		
		if(topOfPlayer <= ceiling) {
			jumping = false;
			falling = true;
			System.out.println();
			System.out.println(topOfPlayer + " | "+ceiling);

			
		}
		
		if(bottomOfPlayer >= floor-10) {
			jumping = false;
			falling = false;
			System.out.println();
			System.out.println(bottomOfPlayer + " | "+floor);
			playerY = floor - playerHeight - 10;
		}
		
	}
}
	