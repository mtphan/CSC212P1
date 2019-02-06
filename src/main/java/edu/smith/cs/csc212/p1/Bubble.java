package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * A class which create instances of bubble.
 * Bubbles will follow a sinusoidal path instead of floating up straight.
 * @author Minh Phuong
 *
 */
public class Bubble {
	
	/**
	 * Location variable.
	 */
	public double x, y;
	
	/**
	 * Float speed variable.
	 */
	public double speedY;
	
	/**
	 * Bubble path variable for x: wiggle = a*sin(y/b)
	 */
	public double a, b, wiggle;
	
	/**
	 * Bubble size variable.
	 */
	public int size;
	Random rand = new Random();
	
	/**
	 * Variable for bubble that "pop out" from clamp.
	 */
	boolean isPop;
	
	/**
	 * Create a bubble at position (startX, startY) with size s.
	 * Pop determined if it's a bubble pop out from a clam.
	 * @param startX - start X position (upper corner)
	 * @param startY - start Y position (upper corner)
	 * @param s - size
	 * @param pop - is it a "pop out" bubble?
	 */
	public Bubble(double startX, double startY, int s, boolean pop) {
		this.x = startX;
		this.y = startY;
		this.size = s;
		this.a = 4.0 + 3.0*rand.nextDouble();
		this.b = 4.0 + 3.0*rand.nextDouble();
		this.speedY = 0.3 + rand.nextDouble();
		this.isPop = pop;
	}
	
	/**
	 * Draw bubbles according to its x and y.
	 * @param g - canvas
	 */
	public void draw(Graphics2D g) {
		// Draw bubble.
		// Set g to a color white with transparency.
		g.setColor(new Color(255, 255, 255, 150));
		Shape bubble = new Ellipse2D.Double(x + wiggle, y, size, size);
		g.fill(bubble);
		g.setColor(Color.white);
		g.draw(bubble);
		
		// Bubble rise up (can't name it float).
		rise();
	}
	
	/**
	 * Move bubble in a sinuisoidal path.
	 */
	public void rise() {
		this.y -= speedY;
		// If off-screen.
		if (y < -100) {
			// Reappear place of "pop from clamp" bubble is different.
			if (isPop) {
				this.y = Aquarium.HEIGHT - 80;
			} else {
				this.y = Aquarium.HEIGHT + 150;
			}
		}
		
		this.wiggle = (a * Math.sin(this.y/b));
	}
	
}