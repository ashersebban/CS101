package ash;


/**
 * Square Class that creates square properties for player
 * @author Asher Sebban
 * @version 2
 */

public class Square extends Shape{
	
	//CONSTRUCTORS
		public Square() {
			super();
			this.type = 2;
			this.name = "Square";
			this.fill[2] =  255;
		}
		
		//OVERLOADED CONSTRUCTOR
		//CAN CHANGE SIZE (for powerups)
		public Square(int width,int height, int[] fill) {
			super();
			this.type = 3;
			this.name = "Square";
			this.height = height;
			this.width = width;
			this.fill = fill;
			
		}

}
