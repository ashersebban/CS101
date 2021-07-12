package ash;

/**
 * Obstacle class that generates obstacles in the GameWindow
 * @author Asher Sebban
 * @version 3
 */
public class Brick {
	
	
	
	private double posX;
	private int posY; 
	private int width;
	private int height;
	private int[] fill = new int[3];
	private int value;
//	private int health;
//	private String material;
	
	
	//CONSTRUCTOR
	public Brick(double x,int y,int width, int height, int value) {
		
		this.value = value;
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		
		//COLORS
		//black - universal - 1
		if(value == 1) {
//			this.fill[0] = 255;
//			this.fill[1] = 255;
//			this.fill[2] = 255;
			this.fill[0] = 0;
			this.fill[1] = 0;
			this.fill[2] = 0;
		}
		
		//green - circle - 2
		else if(value == 2) {
//			this.fill[0] = 255;
//			this.fill[1] = 255;
//			this.fill[2] = 255;
			this.fill[0] = 0;
			this.fill[1] = 255;
			this.fill[2] = 0;
		}
		
		//blue - square - 3
		else if(value == 3) {
//			this.fill[0] = 255;
//			this.fill[1] = 255;
//			this.fill[2] = 255;
			this.fill[0] = 0;
			this.fill[1] = 0;
			this.fill[2] = 225;
		}
		//red - triangle - 4
		else if(value == 4) {
//			this.fill[0] = 255;
//			this.fill[1] = 255;
//			this.fill[2] = 255;
			this.fill[0] = 255;
			this.fill[1] = 0;
			this.fill[2] = 0;
		}
		
		
	}//end of constructor 
	
	
	//GETTERS AND SETTERS

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[] getFill() {
		return fill;
	}

	public void setFill(int[] fill) {
		this.fill = fill;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
	
	
	
	

}
