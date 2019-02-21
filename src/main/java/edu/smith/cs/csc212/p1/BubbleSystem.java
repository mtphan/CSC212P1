package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * This class is created to control bubbles on screen. Bubbles will follow a sinusoidal path.
 * Som bubble will pop out of a clam instead of floating up from the bottom.
 * @author Minh Phuong
 *
 */
public class BubbleSystem {
	
	Bubble[] bubbleArr = new Bubble[20];
	
	/**
	 * Variable for x-position of clam, a source of bubble.
	 * Some bubbles will pop out of here instead of floating up from the bottom.
	 */
	double sourceX;
	Random rand = new Random();
	
	/**
	 * Create a bubble system to control bubbles.
	 * Initialize random bubbles.
	 */
	public BubbleSystem() {
		// Pick a random place for the clamp to spawn.
		sourceX = 10 + (Aquarium.WIDTH-150)*rand.nextDouble();
		
		// Generate random bubbles.
		for (int i=0; i<bubbleArr.length; i++) {
			// Random size.
			int size = 10 + rand.nextInt(41);
			// Initial random y.
			double y = Aquarium.HEIGHT * rand.nextDouble();
			
			// 30% chance of making a bubble that appear from the clamp
			if (rand.nextDouble() < 0.3) {
				bubbleArr[i] = new Bubble(sourceX + 80*rand.nextDouble(), y, size, true);
			// Else make it a normal bubble that float up from the bottom
			} else {
				double x = 50 + (Aquarium.WIDTH-100) * rand.nextDouble();
				bubbleArr[i] = new Bubble(x, y, size, false);
			}
		}
	}
	
	/**
	 * Draw clam and bubbles.
	 * @param g - canvas
	 */
	public void draw(Graphics2D g) {
		// Draw clam.
		Shape bot = new Ellipse2D.Double(sourceX, Aquarium.HEIGHT-40, 120, 40);
		Shape top = new Ellipse2D.Double(sourceX, Aquarium.HEIGHT-120, 120, 80);
		Shape pearl = new Ellipse2D.Double(sourceX + 40, Aquarium.HEIGHT-60, 40, 40);
				
		g.setColor(Color.pink);
		g.fill(bot);
		g.fill(top);
		g.setColor(Color.white);
		g.fill(pearl);
		g.draw(bot);
		g.draw(top);
		g.draw(pearl);
				
		// Draw bubbles.
		for (Bubble bubble : bubbleArr) {
			bubble.draw(g);
		}
	}
}