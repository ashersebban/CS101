package ash;
import processing.core.*;

public abstract class Shape{
	
	protected String name;
	protected int sizeX;
	protected int sizeY;
	protected int posX;
	protected int posY;
	protected int[] rgb;
	
	protected PShape newShape;
	
	public Shape(String name,int sizeX,int sizeY,int posX,int posY,int[] rgb) {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
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

	public int[] getRGB() {
		return rgb;
	}

	public void setRGB(int[] rgb) {
		this.rgb = rgb;
	}
	
	

}

