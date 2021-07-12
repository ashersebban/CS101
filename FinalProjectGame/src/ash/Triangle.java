package ash;


/**
 * Triangle Class that creates triangle properties for player
 * @author Asher Sebban
 * @version 2
 */

public class Triangle extends Shape{
	
	
	//CONSTRUCTOR
	public Triangle() {
		super();
		this.type = 3;
		this.name = "Triangle";
		this.fill[0] =  255;
	}
	
	//OVERLOADED CONSTRUCTOR
	//CAN CHANGE SIZE (for powerups)
	public Triangle(int width,int height, int[] fill) {
		super();
		this.type = 4;
		this.height = height;
		this.width = width;
		this.fill = fill;
		
	}
	
	
	
	
}
