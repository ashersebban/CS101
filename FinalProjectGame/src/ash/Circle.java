package ash;

/**
 * Circle class that generates cricle obstacles in the GameWindow
 * @author Asher Sebban
 * @version 2
 */

public class Circle extends Shape{
	

	//CONSTRUCTOR
	public Circle() {
		super();
		this.type = 1;
		this.name = "Circle";
		this.fill[1] =  255;
		this.jumpSpeed = GameWindow.SIZE_Y/55; //speed based on window size for scalability
		this.fallSpeed = GameWindow.SIZE_Y/90; //speed based on window size for scalability
		
	}
	
	
	//OVERLOADED CONSTRUCTOR
	//CAN CHANGE SPEEDS, SIZE, OR COLOR : USEFUL FOR POWERUPS (TBA)
	public Circle(int width,int height,double jumpSpeed, double fallSpeed, int[] fill) {
		super();
		this.type = 2;
		this.name = "Circle";
		this.height = height;
		this.width = width;
		this.fill = fill;
		this.jumpSpeed = GameWindow.SIZE_Y/55;
		this.fallSpeed = GameWindow.SIZE_Y/65;
		
	}
	
	
	
	
}
