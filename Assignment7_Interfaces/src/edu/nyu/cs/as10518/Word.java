package edu.nyu.cs.as10518;

import java.util.*;


public class Word extends OrderedThing implements SequentiallyOrdered {
	
	
	//INSTANCE VARIABLES
	private ArrayList<OrderedThing> word = new ArrayList<OrderedThing>();
	private int position;

	//CONSTRUCTORS
	public Word(String w,int p) {
		
		for(int i = 0;i < w.length();i++) {
			Character c = new Character(w.charAt(i));
			word.add(c);
		}
		
		this.position = p;
		
	}
	
	//SETTERS AND GETTERS
	public void setWord(ArrayList<OrderedThing> word) {
		this.word = word;
	}
	
	public ArrayList<OrderedThing> getWord() {
		return this.word;
	}

	public void setPosition(int p) {
		this.position = p;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	//METHODS
	@Override
	public OrderedThing getFirst() {
		// TODO Auto-generated method stub
		return word.get(0);
	}

	@Override
	public OrderedThing getLast() {
		// TODO Auto-generated method stub
		return word.get(word.size()-1);
	}

	@Override
	public ArrayList<OrderedThing> getSequence() {
		// TODO Auto-generated method stub
		return word;
	}
	
	public String toString() {
		String w = "";
		for(int i =0;i<word.size();i++) {
			w+=word.get(i).toString();
		}
		return w;
	}
	

}
