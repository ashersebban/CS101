package ash;

public class Brick {
	
	private int posX;
	private int posY; 
	private int width;
	private int height;
	
	
	private int[] fill = new int[3];
	private int value;
//	private int health;
//	private String material;
	
	public Brick(int x,int y,int width, int height, int value) {
		
		this.value = value;
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		
		//COLLORS
		//black - universal
		if(value == 1) {
			this.fill[0] = 0;
			this.fill[1] = 0;
			this.fill[2] = 0;
		}
		//blue - square
		else if(value == 2) {
			this.fill[0] = 0;
			this.fill[1] = 0;
			this.fill[2] = 225;
		}
		//red - triangle
		else if(value == 3) {
			this.fill[0] = 255;
			this.fill[1] = 0;
			this.fill[2] = 0;
		}
		//green - circle
		else if(value == 4) {
			this.fill[0] = 0;
			this.fill[1] = 255;
			this.fill[2] = 0;
		}
		
//		this.health;
//		this.material;
		
	}//end of constructor 

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
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
