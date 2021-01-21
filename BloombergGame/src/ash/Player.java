package ash;

public class Player {
	
	private int offset1 = 18;
	private int offset2 = 30;

	//player variables
	private int playerWidth;
	private int playerHeight;
	private int playerX;
	private int playerY;
	private String playerShape;
	private int[] playerColor = new int[3];
	private boolean reachedMaxHeight;
	private boolean reachedMinHeight;
	private int playerVelocity;
	
	public int getPlayerVelocity() {
		return playerVelocity;
	}

	public void setPlayerVelocity(int playerVelocity) {
		this.playerVelocity = playerVelocity;
	}

	//SQUARE ONLY
	private int squareVertical;
	
	public int getSquareVertical() {
		return squareVertical;
	}

	public void setSquareVertical(int squareVertical) {
		this.squareVertical = squareVertical;
	}

	public int getCircleJumpSpeed() {
		return circleJumpSpeed;
	}

	public void setCircleJumpSpeed(int circleJumpSpeed) {
		this.circleJumpSpeed = circleJumpSpeed;
	}

	public int getCircleFallSpeed() {
		return circleFallSpeed;
	}

	public void setCircleFallSpeed(int circleFallSpeed) {
		this.circleFallSpeed = circleFallSpeed;
	}

	public int getTriangleVertical() {
		return triangleVertical;
	}

	public void setTriangleVertical(int triangleVertical) {
		this.triangleVertical = triangleVertical;
	}

	public boolean isUpsidedown() {
		return upsidedown;
	}

	public void setUpsidedown(boolean upsidedown) {
		this.upsidedown = upsidedown;
	}

	//CIRCLE ONLY
	private int circleJumpSpeed;
	private int circleFallSpeed;
	
	//TRIANGLE ONLY
	private int triangleVertical;
	private boolean upsidedown;
	
	
	public int getPlayerWidth() {
		return playerWidth;
	}

	public void setPlayerWidth(int playerWidth) {
		this.playerWidth = playerWidth;
	}

	public int getPlayerHeight() {
		return playerHeight;
	}

	public void setPlayerHeight(int playerHeight) {
		this.playerHeight = playerHeight;
	}

	public int getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	public String getPlayerShape() {
		return playerShape;
	}

	public void setPlayerShape(String playerShape) {
		this.playerShape = playerShape;
	}

	public int[] getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(int[] playerColor) {
		this.playerColor = playerColor;
	}

	public boolean isReachedMaxHeight() {
		return reachedMaxHeight;
	}

	public void setReachedMaxHeight(boolean reachedMaxHeight) {
		this.reachedMaxHeight = reachedMaxHeight;
	}

	public boolean isReachedMinHeight() {
		return reachedMinHeight;
	}

	public void setReachedMinHeight(boolean reachedMinHeight) {
		this.reachedMinHeight = reachedMinHeight;
	}
	
	public Player(int w, int h) {
		this.playerWidth = w/offset1;
		this.playerHeight = w/offset1;
		this.playerX = w/offset1;
		this.playerY = h - this.playerHeight - h/offset2;
		this.playerShape = "Circle";
		this.playerColor[0] = 0;
		this.playerColor[1] = 0;
		this.playerColor[2] = 0;
		this.reachedMinHeight = true; 
		this.reachedMaxHeight = false;
		this.playerVelocity = 0;
		
		
		//SQUARE ONLY
		this.squareVertical = h/80;
		
		//CIRCLE ONLY
		this.circleJumpSpeed = 15;
		this.circleFallSpeed = circleJumpSpeed/3;
		
		//TRIANGLE ONLY
		this.triangleVertical = h/15;
		this.upsidedown = false;
	}
}
