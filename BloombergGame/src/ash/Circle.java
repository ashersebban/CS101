package ash;

public class Circle extends Shape{
	

	//CONSTRUCTORS
	public Circle() {
		super();
		this.type = 1;
		this.name = "Circle";
		this.fill[1] =  255;
		this.fallSpeed = GameWindow.SIZE_Y/55;
		this.jumpSpeed = GameWindow.SIZE_Y/65;
	}
	
	public Circle(int width,int height,double jumpSpeed, double fallSpeed, int[] fill) {
		super();
		this.type = 1;
		this.name = "Circle";
		this.height = height;
		this.width = width;
		this.fill = fill;
		this.jumpSpeed = GameWindow.SIZE_Y/55;
		this.fallSpeed = GameWindow.SIZE_Y/65;
		
	}
	
	
	
	
}
