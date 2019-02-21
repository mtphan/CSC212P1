package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * 
 * This class is used to draw food and food source for fish.
 * Created because I don't want too many methods in Aquarium.
 * @author Minh Phuong
 *
 */
public class Feeder {
	
	/**
	 * X-position of feeder.
	 */
	static double feederX = Aquarium.WIDTH - 160;
	
	/**
	 * Height of feeder. Accessed statically.
	 */
	static double feederH = Aquarium.HEIGHT - 300;
	/**
	 * Width of feeder. Accessed statically.
	 */
	static double feederW = 80;
	
	Random rand = new Random();
	
	/**
	 * Array of food pellets. Array instead of a class because I don't want to many classes.
	 */
	static Ellipse2D[] pellets;
	
	/**
	 * Create foodNum number of food pellets for the hungry fish.
	 * @param foodNum - number of food needed to create (i.e. number of HungryFish in tank)
	 */
	public Feeder(int foodNum) {
		// Initialise pellets.
		pellets = new Ellipse2D[foodNum];
		for (int i=0; i<pellets.length; i++) {
			Ellipse2D pellet = new Ellipse2D.Double(feederX + (feederW-30)*rand.nextDouble(),
													feederH*rand.nextDouble() - feederH, 20, 20);
		
			pellets[i] = pellet;
		}
	}
	
	/**
	 * Draw and drop the food pellet for fish.
	 * @param g - canvas to draw on
	 * @param pelletNo - index of the pellet needed to drop in the pellets array.
	 */
	public void dropFood(Graphics2D g, int pelletNo) {
		
		// Draw the pellet.
		g.setColor(Color.red);
		g.fill(pellets[pelletNo]);
		g.setColor(Color.white);
		g.draw(pellets[pelletNo]);
		
		// Get y-position of pellet.
		double pelletY = pellets[pelletNo].getY();
		
		// Record its y-position and add 2 to it if it's not at the bottom of the tank all ready.
		if (pelletY <= Aquarium.HEIGHT-pellets[pelletNo].getHeight()) {
			pelletY += 2;
		}
		
		// Move pellet to pelletY position.
		pellets[pelletNo].setFrame(pellets[pelletNo].getX(), pelletY,
								   pellets[pelletNo].getWidth(), pellets[pelletNo].getHeight());
	}
	
	/**
	 * Check if the pellet is "visible" (i.e. fell out of the feeder tube).
	 * @param pelletNo - index of the pellet needed to be checked in the pellets array.
	 * @return true if pellet is "visible", false if not.
	 */
	public boolean visible(int pelletNo) {
		// Check pellet's y-position against the lowest y-position feeder.
		if (pellets[pelletNo].getY() > feederH + 60) {
			return true;
		}
		return false;
	}
	
	/**
	 * Reset the pellet to a random position after it was "eaten" by the fish.
	 * @param pelletNo - index of the pellet needed to be reseted in the pellets array.
	 */
	public void reset(int pelletNo) {
		pellets[pelletNo].setFrame(feederX + (feederW-30)*rand.nextDouble(),
								   feederH*rand.nextDouble() - feederH,
								   pellets[pelletNo].getWidth(), pellets[pelletNo].getHeight());
	}
	
	/**
	 * Draw the feeder, a gray boring tube that drops food for fish.
	 * @param g - canvas
	 */
	public void drawFeeder(Graphics2D g) {
		
		Shape tube = new Rectangle2D.Double(feederX, 0, feederW, feederH);
		Shape feeder = new Rectangle2D.Double(feederX - 50, feederH, feederW + 100, 60);
	
		// Draw feeder.
		g.setColor(Color.gray);
		g.fill(tube);
		g.fill(feeder);
		g.setColor(Color.white);
		g.draw(tube);
		g.draw(feeder);
	}
}
