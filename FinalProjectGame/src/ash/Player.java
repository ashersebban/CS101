package ash;

/**
 * Obstacle class that generates obstacles in the GameWindow
 * @author Asher Sebban
 * @version 4
 */
public class Player {

	//player variables
	
	private int posX;
	private int posY;
	private int currentLayer;
	private boolean reachedMaxHeight;
	private boolean reachedMinHeight;
	private Shape[] shapes = new Shape[4];
	private int currentShape;
	
	
	//CONSTRUCTOR
	public Player() {
		this.shapes[1]=new Circle();
		this.shapes[2]=new Square();
		this.shapes[3]=new Triangle();
		this.currentShape = 1;
		this.posX = shapes[currentShape].getHeight()*2;
		this.posY = shapes[currentShape].getHeight();
		this.reachedMinHeight = true; 
		this.reachedMaxHeight = false;
		
		
	}//end of constructor
	
	
	/**
	 * This method prints player shape, player Y pos, current layer, and whether the player has reached max or min height
	 * USED FOR DEBUGGING
	 */
	public void debugUpdate() {
		System.out.println("Shape: "+shapes[currentShape].getName());
		System.out.print(" posY: "+posY);
		System.out.print(" currentLayer: "+currentLayer);
		if(reachedMaxHeight)System.out.print(" max: "+reachedMaxHeight);
		if(reachedMinHeight)System.out.print(" min: "+reachedMinHeight);
		System.out.println();	
	}
	
	//GETTERS AND SETTERS
	
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

	public Shape getShape() {
		return shapes[currentShape];
	}

	public void setShape(int currentShape) {
		this.currentShape = currentShape;
	}
	
	public Shape[] getShapeArray() {
		return shapes;
	}
	
	
	
	
}
