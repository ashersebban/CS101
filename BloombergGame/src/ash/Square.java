package ash;

public class Square extends Shape{
	
	//CONSTRUCTORS
		public Square() {
			super();
			this.type = 2;
			this.name = "Square";
			this.fill[2] =  255;
		}
		
		public Square(int width,int height, int[] fill) {
			super();
			this.type = 2;
			this.name = "Square";
			this.height = height;
			this.width = width;
			this.fill = fill;
			
		}

}
