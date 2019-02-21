package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * General class Fish, which creates Fish instances.
 * Fish picks destination and move towards that destination.s
 * @author Minh Phuong
 *
 */
public class Fish {
	
	/**
	 * Location variable.
	 */
	public double x, y;
	
	/**
	 * Colour variable.
	 */
	public Color color;
	
	/**
	 * Direction variable.
	 */
	public boolean facingLeft;

	/**
	 * Size variable.
	 */
	public boolean isLittle;
	
	/**
	 * Speed variable.
	 */
	public double speedX, speedY, speed;
	
	/**
	 * Destination variable.
	 */
	public double destX, destY;

	Random rand = new Random();
	
	/**
	 * Create a fish with color c at position (startX, startY).
	 * Its direction is determined by left, its size is determined by little.
	 * @param c - color of fish.
	 * @param startX - start x-position
	 * @param startY - start y-position
	 * @param left - true if the fish is facing left
	 * @param little - true if the fish is small
	 */
	public Fish(Color c, double startX, double startY, boolean left, boolean little) {
		this.x = startX;
		this.y = startY;
		
		// Initialize a destination. Needed because the logic of my code make it so that without destination they will swim towards infinity.
		this.destX = startX + 1;
		this.destY = startY + 1;
		
		this.color = c;
		this.facingLeft = left;
		this.isLittle = little;
		
		// Random x-speed and y-speed.
		this.speedX = 1.0 + 3*rand.nextDouble();
		this.speedY = 1.0 + 3*rand.nextDouble();
		if (!facingLeft) {
			// Move backwards if facing right
			this.speedX *= -1;
		}
		// Actual speed.
		speed = Math.sqrt(Math.pow(speedX, 2) + Math.pow(speedY, 2));
	}
	
	/**
	 * Get position of fish.
	 * @return x - x position of fish.
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Get position of fish.
	 * @return y - y position of fish.
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Change position of fish by an amount.
	 */
	public void swim() {
		this.x -= speedX;
		this.y -= speedY;
	}
	
	/**
	 * Check if fish has reached its destination.
	 * @return boolean - true for destination reached, false for not.
	 */
	public boolean reachDestination() {
		
		// If fish is not close to destination (within 3 pixel of destination).
		if ((Math.abs(this.x - this.destX) >= 3) || 
			(Math.abs(this.y - this.destY) >= 3)) {
			return false;
			// If fish reaches destination.
		} else {
			return true;
		}
	}
	
	/**
	 * Draw the fish + do the swiming.
	 * @param g - canvas
	 */
	public void draw(Graphics2D g) {
		// Swim away!
		swim();
		
		// A bunch of if statements to decide which DrawFish methods to call.
		if (isLittle) {
			if (facingLeft) {
				DrawFish.smallFacingLeft(g, color, x, y);
			} else {
				DrawFish.smallFacingRight(g, color, x, y);
			}
		} else {
			if (facingLeft) {
				DrawFish.facingLeft(g, color, x, y);
			} else {
				DrawFish.facingRight(g, color, x, y);
			}
		}
				
//		 Uncomment + import to see destination of the fish drawn in the form of small colored dot.
/*		 Shape dest = new Ellipse2D.Double(destX, destY, 10, 10);
		 g.setColor(color);
		 g.fill(dest);
		 g.setColor(Color.black);
		 g.draw(dest);
*/		
	}
	
	/**
	 * General method to pick a random destination.
	 */
	public void pickDestination() {
		// Pick a random destination only when reached destination.
		if (reachDestination()) {
			this.destX = Aquarium.WIDTH*rand.nextDouble();
			this.destY = Aquarium.HEIGHT*rand.nextDouble();
			calculateSpeed();
		}
	}
	
	/**
	 * Force picking a destination (instead of picking randomly).
	 * @param forceX - destination's x position
	 * @param forceY - destination's y position.
	 */
	public void pickDestination(double forceX, double forceY) {
		this.destY = forceY;
		this.destX = forceX;
		calculateSpeed();
	}
	
	/**
	 * Pick random destination within a certain range.
	 * @param startX - range left x-bound.
	 * @param endX - range right x-bound.
	 * @param startY - range upper y-bound.
	 * @param endY - range lower y-bound.
	 */
	public void pickDestination(double startX, double endX, double startY, double endY) {
		if (reachDestination()) {
			this.destX = startX + (endX - startX)*rand.nextDouble();
			this.destY = startY + (endY - startY)*rand.nextDouble();
			calculateSpeed();
		}
	}
	
	/**
	 * Calculate fish's speed in x-direction and y-direction. True speed is kept the same.
	 */
	public void calculateSpeed() {
		
		// If x-destX.isCloseTo(0), add 1 to destX.
		// Did this to guarantee that length is never 0 to prevent divide by zero exception.
		// Consequence: Fish never truly swim vertically, which is coincidentally realistic.
		if (Math.abs(x-destX)<=1e-3) {
			this.destX += 1;
		}
		
		// Calculate speedX and speedY using trigonometry.
		double length = Math.sqrt(Math.pow(x-destX, 2) + Math.pow(y-destY, 2));
		double sin = (y-destY)/(length);
		double cos = (x-destX)/(length);

		this.speedX = cos*speed;
		this.speedY = sin*speed;
		
		// Change fish direction if needed.
		if (speedX > 0) {
			facingLeft = true;
		} else {
			facingLeft = false;
		}
	}
}