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
		
		int[] color = {255,0,255};
		Square square1 = new Square("Square1",h/2,w/2,100,100,color);
		
		//fill the background with this color (specified in 8-bit R, G, B values)
		this.background(255, 255, 255); //white

		//set the fill color (the color which solid shapes will be filled with)
		this.fill(color[0],color[1],color[2]); 

		public void keyPressed() {
			if (key == ' ') {
				square1.setPosY(square1.getPosY()+30);
			}
		}
	}
	

	
	/**
	 * This method is called repeatedly many times per second (usually 30 times per second by default) for the lifetime of the app.
	 */
	public void draw() {
		
		//set the fill color (the color which solid shapes will be filled with)
				this.fill(color[0],color[1],color[2]); 
				
		//fill the background with this color (specified in 8-bit R, G, B values)
		this.background(255, 255, 255); //white
		
		rect(square1.getPosX(),square1.getPosY(),square1.sizeX,square1.sizeY);
		
		}
		
		
	}
	