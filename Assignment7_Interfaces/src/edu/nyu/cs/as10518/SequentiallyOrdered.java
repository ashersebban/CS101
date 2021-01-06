package edu.nyu.cs.as10518;

import java.util.ArrayList;

public interface SequentiallyOrdered {
	

	public abstract OrderedThing getFirst();
	public abstract OrderedThing getLast();
	public abstract ArrayList<OrderedThing> getSequence();



}
