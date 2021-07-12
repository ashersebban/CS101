package edu.nyu.cs.as10518;

import java.util.ArrayList;

public class Sentence implements SequentiallyOrdered {
	
	
	//INSTANCE VARIABLES
	private ArrayList<OrderedThing> sentence= new ArrayList<OrderedThing>();
	

	//CONSTRUCTORS
	public Sentence(String s) {
		
		// split by any non-alphanumeric character
		String[] words = s.split("[^\\w']+");
		
		for(int i = 0;i < words.length;i++) {
			Word w = new Word(words[i],i);
			sentence.add(w);
		}
		
	}
	
	//SETTERS AND GETTERS
	public void setWord(ArrayList<OrderedThing> sentence) {
		this.sentence = sentence;
	}
	
	public ArrayList<OrderedThing> getWord() {
		return this.sentence;
	}

	//METHODS
	@Override
	public OrderedThing getFirst() {
		// TODO Auto-generated method stub
		return sentence.get(0);
	}

	@Override
	public OrderedThing getLast() {
		// TODO Auto-generated method stub
		return sentence.get(sentence.size()-1);
	}

	@Override
	public ArrayList<OrderedThing> getSequence() {
		// TODO Auto-generated method stub
		return sentence;
	}
	
	//toString
	public String toString() {
		String s = "";
		for(int i=0;i<sentence.size();i++) {
			s+= sentence.get(i)+" ";
		}
		return s.trim();
	}
	
}