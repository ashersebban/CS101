package ash;

public abstract class Shape {

	private int sizeOffset = 10;
	protected String name;
	protected int type;
	protected int height;
	protected int width;
	protected int[] fill = {0,0,0};
	protected double jumpSpeed;
	protected double fallSpeed;
	protected boolean upsidedown;
	
	
	//CONSTRUCTRO
	public Shape() {
		this.height = (GameWindow.SIZE_Y/GameWindow.NUM_LAYERS)-sizeOffset;
		this.width = (GameWindow.SIZE_Y/GameWindow.NUM_LAYERS)-sizeOffset;
	}
	
	//GETTERS AND SETTERS
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int[] getFill() {
		return fill;
	}
	public void setFill(int[] fill) {
		this.fill = fill;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public double getJumpSpeed() {
		return jumpSpeed;
	}

	public void setJumpSpeed(int jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	public double getFallSpeed() {
		return fallSpeed;
	}

	public void setFallSpeed(int fallSpeed) {
		this.fallSpeed = fallSpeed;
	}
	
	
	public boolean isUpsidedown() {
		return upsidedown;
	}

	public void setUpsidedown(boolean upsideDown) {
		this.upsidedown = upsideDown;
	}
	
	
	
	

}
