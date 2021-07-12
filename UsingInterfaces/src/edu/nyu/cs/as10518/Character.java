package edu.nyu.cs.as10518;

public class Character extends OrderedThing{
	
	
	//INSTANCE VARIABLES
	private char character;
	
	
	//CONSTRUCTORS
	public Character(char c) {
		
		this.character = c;
		
	}
	
	//SETTERS AND GETTERS
	public void setCharacter(char c) {
		this.character = c;
	}
	
	public char getCharacter() {
		return this.character;
		
	}
	
	//toString
	public String toString() {
		return character+"";
	}
	
	

}
