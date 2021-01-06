package edu.nyu.cs.as10518;

public class Asteroids {
	
	private double diameter;
	private double speed; 
	private int x_location;
	private int y_location;
	private int[] coordinates = {x_location,y_location};
	
	public Asteroids(double diameter,double speed,int x_location,int y_location) {
		
	}
	
	public void rotate() {
		
	}
	
	public void thrust() {
		
	}
	
	public void shoot() {
		
	}
	
	public int[] move(String direction) {
		int new_x_location = 0;
		int new_y_location = 0;
		int[] new_coordinates = {new_x_location,new_y_location};
		return new_coordinates;
	}

}
