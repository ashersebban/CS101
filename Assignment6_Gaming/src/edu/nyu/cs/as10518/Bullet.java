package edu.nyu.cs.as10518;

public class Bullet {
	
	private double speed;
	private int x_location;
	private int y_location;
	private int[] coordinates = {x_location,y_location};
	
	public Bullet() {
		
	}
	
	
	public boolean collision() {
		return false;
	}

	public int[] move(String direction) {
		int new_x_location = 0;
		int new_y_location = 0;
		int[] new_coordinates = {new_x_location,new_y_location};
		return new_coordinates;
	}
}
