package ash;

/**
 * Obstacle class that generates obstacles in the GameWindow
 * @author Asher Sebban
 * @version 2
 */
public abstract class Shape {

	private int sizeOffset = 10; //Makes players slightly thinner than the layers so they dont touch when passing through
	protected String name;
	protected int type;
	protected int height;
	protected int width;
	protected int[] fill = {0,0,0}; //allows inheriting classes to only change one color index
	protected double jumpSpeed; //circle only
	protected double fallSpeed; //circle only
	protected boolean upsidedown; //triangle only
	
	
	//CONSTRUCTOR
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
