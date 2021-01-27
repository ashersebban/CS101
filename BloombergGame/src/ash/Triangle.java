package ash;

public class Triangle extends Shape{
	
	
	//CONSTRUCTOR
	
	public Triangle() {
		super();
		this.type = 3;
		this.name = "Triangle";
		this.fill[0] =  255;
	}
	
	public Triangle(int width,int height, int[] fill) {
		super();
		this.type = 3;
		this.height = height;
		this.width = width;
		this.fill = fill;
		
	}
	
	
	
	
}
