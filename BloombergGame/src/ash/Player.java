package ash;

public class Player {

	//player variables
	private int width;
	private int height;
	private int posX;
	private int posY;
	private int currentLayer;
	private String shape;
	private int[] fill = new int[3];
	private boolean reachedMaxHeight;
	private boolean reachedMinHeight;
	
	//CIRCLE ONLY
	private int circleJumpSpeed;
	private int circleFallSpeed;
	
	//TRIANGLE ONLY
	private boolean upsidedown;
	
	
	//CONSTRUCTOR
	public Player(int sizeX, int sizeY) {
		this.width = sizeY/GameWindow.NUM_LAYERS-10;
		this.height = sizeY/GameWindow.NUM_LAYERS-10;
		this.posX = width*2;
		this.posY = sizeY-height+height/2;
		this.shape = "Circle";
		this.fill[0] = 0;
		this.fill[1] = 0;
		this.fill[2] = 0;
		this.reachedMinHeight = true; 
		this.reachedMaxHeight = false;
		
		//CIRCLE ONLY
		this.circleJumpSpeed = GameWindow.SIZE_Y/60;
		this.circleFallSpeed = GameWindow.SIZE_Y/65;
		
		//TRIANGLE ONLY
		this.upsidedown = false;
		
	}//end of constructor
	
	//NEEDS WORK ... DOES NOT WORK
	public void moveToLayer(int targetLayer) {
		
		int nextLayer = GameWindow.SIZE_Y/GameWindow.NUM_LAYERS;
		
		if(currentLayer < targetLayer) {
			for(int i=currentLayer;i<=targetLayer;++i) {
				if(!reachedMaxHeight) {
					currentLayer = i;
					posY -= nextLayer;
					}
				else currentLayer = GameWindow.NUM_LAYERS;
			}
		}
		else if(currentLayer > targetLayer) {
			for(int i=currentLayer;i>=targetLayer;--i) {
				if(!reachedMinHeight) {
					currentLayer = i;
					posY += nextLayer;
				}
				else currentLayer = 0;
			}
		}
	}
	
	//USED FOR DEBUGGING
	public void debugUpdate() {
		System.out.println("Shape: "+shape);
		System.out.print(" posY: "+posY);
		System.out.print(" currentLayer: "+currentLayer);
		System.out.print(" max: "+reachedMaxHeight);
		System.out.print(" min: "+reachedMinHeight);
		System.out.println();
				
	}
	
	//GETTERS AND SETTERS
	
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
	
	public int getCurrentLayer() {
		return currentLayer;
	}

	public void setCurrentLayer(int currentLayer) {
		this.currentLayer = currentLayer;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	
	public int[] getFill() {
		return fill;
	}

	public void setFill(int[] fill) {
		this.fill = fill;
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

	public boolean isUpsidedown() {
		return upsidedown;
	}

	public void setUpsidedown(boolean upsidedown) {
		this.upsidedown = upsidedown;
	}
	
	
	
	
}
