package edu.nyu.cs.as10518;


//imported libraries
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Creates a "Fractal Tree" on JPanel using method of recursion 
 *
 * @author Foo Barstein, with comments by Asher Sebban (as10518)"
 * @version 0.1 
 */
public class FractalTree extends JPanel{
	
	/**
	 * This used to draw the stuff
	 * 
	 * param of drawFractal()
	 */
	public Graphics2D g1;
	
	/**
	 * Sets range for maximum angle as 360 
	 * 
	 * used in modulus operation 
	 * @see line 175
	 */
	public static final int maxAngle = 360;
	
	
	/**
	 * Starting point for FractalTree (x axis)
	 * 
	 * 600 = 50% of JPanel width (1200)
	 * note: so program starts in middle of the JPanel
	 * 
	 * param of drawFractal() ... startX = x
	 */
	public static final int startX = 600;
	
	
	/**
	 * Starting point for FractalTree (y axis)
	 * 
	 * 800 = 80% of JPanel height (1000)
	 * note: JPanel (0,0) is TOPLEFT so 800 is near the BOTTOM
	 * param of  drawFractal()...startY = y 
	 */
	public static final int startY = 800;
	
	/**
	 * Variable to control the number of recursions (how many fractal trees to draw)
	 * 
	 * param of drawFractal()...numOfRecursions = n 
	 */
	public static final int numOfRecursions = 9;
	
	/**
	 * Starting angle of first line
	 * 
	 * 0 = first line will have angle and will be drawn straight up 
	 * param of drawFractal() ... startAngle = angle
	 */
	public static final int startAngle = 0;
	
	/**
	 * The proportional size of the tree
	 * 
	 * used when getting the length of a given branch
	 * len = treeSize ** n-1
	 */
	public static final double treeSize = 2;
	
	/**
	 * Used as a control to break recursive method
	 * 
	 * When numOfRecursions (n...n...n) reaches Detail -> end recursion
	 * When 9 reaches 3 -> 6 recursive calls 
	 */
	public static final int Detail = 3;
	
	/**
	 * Sets range for getting a random number 
	 * 
	 * used when creating the angles of the branches
	 * recursive startAngle -> @see (angle + r.nextInt(randFact) + constFact[0]) % maxAngle)
	 */
	public static final int randFact = 30;
	
	/**
	 * represents constant variable change between 4 branches
	 *  
	 * used when creating the angles of the branches
	 * @see line 175
	 */
	public static final int[] constFact = { -60, 05, -50, 45 };

	/**
	 * COLOR ARRAYS 
	 * EVENTUALLY USED TO SET COLOR OF THE TREE
	 */
	public static int[] red = { 0, 0, 0, 0, 7, 15, 23, 31, 39, 47, 55, 43 };
	public static int[] green = { 171, 159, 147, 135, 123, 111, 99, 87, 75, 63, 51, 43 };
	public static int[] blue = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	
	/**
	 * CONVERTS DEGREES TO RADIANS
	 * 
	 * radians required for Math.sin() and Math.cos() 
	 * (used in setting child branches xn1 and yn1)
	 * @see line 151
	 * 
	 * @retuns radian version of degree value as a double
	 *
	 */
	public static double degToRad(int deg) {
		return deg * Math.PI / 180;
	}
	
	
	/**
	 * DRAWS FRACTAL TREE (Recursive Functionality)
	 * 
	 * For START values @see line 214
	 * @param g1 = what the program uses to draw
	 * @param x = starting point for branch x (also used to create child branches)(START: startY(600))
	 * @param y = starting point for branch y (also used to create child branches)(START: startY(800))
	 * @param n = number of times recursive method called (START: numOfRecursions (9))
	 * @param angle = the angle at which the straight line is drawn (START: startAngle (0))
	 * @return void
	 */
	public static void drawFractal(Graphics2D g1, int x, int y, int n, int angle) {
		
		//BASE CASE: 
		//When numOfRecursions (n...n...n) reaches Detail -> end recursion 
		//(decrement rate: 1) 
		//9 - (1*i) = 3 -> i=6
		if (n == Detail) 
			return;
		
		//SETS LENGTH OF BRANCH IN PERPORTION TO N (branches get smaller and smaller)
		int len = (int) Math.round(Math.pow(treeSize, n - 1)); //treeSize = 2

		
		//xn1 and yn1 necessary in creating branches
		int xn1 = (int) Math.round(x - (2 * len * Math.sin(degToRad(angle))));
		int yn1 = (int) Math.round(y - (2 * len * Math.cos(degToRad(angle))));
		
		//CREATES STARTING X,Y POINTS FOR 4 DIFFERENT BRANCHES
		//note: x and y used
		//note: xn1 and xn2 used
		int mid1x = (x + xn1) / 2;
		int mid1y = (y + yn1) / 2;
		int mid2x = (mid1x + xn1) / 2;
		int mid2y = (mid1y + yn1) / 2;
		int mid3x = (x + mid1x) / 2;
		int mid3y = (y + mid1y) / 2;
		int mid4x = (mid3x + mid1x) / 2;
		int mid4y = (mid3y + mid1y) / 2;

		//CREATES NEW RANDOM OBJECT 
		//used to get the random angles which will apply to all branches
		java.util.Random r = new java.util.Random();
		
		
		//RECURSION!
		//draw 4 branches... each with 4 branches...each with 4 branches ... until break condition is met
		//uses mid(x,y) values, decrements n, and then uses current angle + randFact + constFact %360 to get angle
		drawFractal(g1, mid1x, mid1y, n - 1, (angle + r.nextInt(randFact) + constFact[0]) % maxAngle);
		drawFractal(g1, mid2x, mid2y, n - 1, (angle + r.nextInt(randFact) + constFact[1]) % maxAngle);
		drawFractal(g1, mid3x, mid3y, n - 1, (angle + r.nextInt(randFact) + constFact[2]) % maxAngle);
		drawFractal(g1, mid4x, mid4y, n - 1, (angle + r.nextInt(randFact) + constFact[3]) % maxAngle);

		//COLOR STUFF
		Color c = new Color(red[(r.nextInt() % 3) + n], green[(r.nextInt() % 3) + n], blue[(r.nextInt() % 3) + n]);
		g1.setColor(c);
		
		//CREATE LINE
		Line2D L1 = new Line2D.Double(x, y, xn1, yn1);
		
		//DRAW LINE
		g1.draw(L1);
		return;
	}

	
	/**
	 * STARTS THE PAINTING PROCESS
	 * DRAWS FRACTAL TREE (USING STARTING VALUES)
	 * 
	 * @param final graphic?
	 * @return void
	 */
	public void paint(final Graphics g) {
		g1 = (Graphics2D) g;
		
		/**
		 * DRAWS FRACTAL TREE (USING STARTING VALUES)
		 * 
		 * @param g1 = what the program uses to draw
		 * @param x = starting point for branch x (also used to create child branches)(START: startY(600))
		 * @param y = starting point for branch y (also used to create child branches)(START: startY(800))
		 * @param n = number of times recursive method called (START: numOfRecursions (9))
		 * @param angle = the angle at which the straight line is drawn (START: startAngle (0))
		 * @return void
		 */
		drawFractal(g1, startX, startY, numOfRecursions, startAngle);
	}

	/**
	 * MAIN
	 * 
	 * @param of String args[] of commands interpreted by JVM
	 * @return void
	 */
	public static void main(String args[]) {
		
		//FF = JFrame = THE GREY BOX AND BOREDERS 
		JFrame FF = new JFrame("Drawing a recursive tree");
		
		//TRADITIONAL PROTOCOL - RED BUTTON CLOSES APPLICATION
		FF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//CREATES FRACTAL TREE (F)
		FractalTree F = new FractalTree(); //<------this is where the magic happens, self calling
		
		FF.setBackground(Color.BLACK);
		
		//Adds fractal tree to JFrame
		FF.add(F);
		//Other stuff
		FF.pack();
		FF.setVisible(true);
		//SIZE OF JFrame FF = WIDTH 1200,HEIGHT 1000
		FF.setSize(1200, 1000);
	}

}
