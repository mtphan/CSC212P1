package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A class for Shark the Vegetarian. It doesn't eat fish but it will swim towards them!
 * @author Minh Phuong
 *
 */
public class Shark extends HungryFish {
	
	/**
	 * The prey number in the fish array (shark doesn't know about the fish array, just its target)
	 */
	int preyNo;

	/**
	 * Create a shark at point (x, y). It's always a twice the size of normal fish one and has the color gray.
	 * @param startX - starting X position
	 * @param startY - start Y position
	 * @param left - true for facing left, false for facing right
	 */
	public Shark(double startX, double startY, boolean left) {
		super(Color.gray, startX, startY, left, false);		
	}
	
	/**
	 * Check if fish has reached its destination, error range is larger for shark.
	 * @return boolean - true for destination reached, false for not.
	 */
	public boolean reachDestination() {
		
		// If fish is not close to destination (within 10 pixel of destination).
		if ((Math.abs(this.x - this.destX) >= 10) || 
			(Math.abs(this.y - this.destY) >= 10)) {
			return false;
			// If fish reaches destination.
		} else {
			return true;
		}
	}
	
	public void draw(Graphics2D g) {
		swim();
		if (this.facingLeft) {
			DrawFish.bigFacingLeft(g, this.color, this.x, this.y);
		} else {
			DrawFish.bigFacingRight(g, this.color, this.x, this.y);
		}
	}
	
	/**
	 * Set the number of prey here.
	 * @param n - prey no. It's like a ticket for your order at the restaurant (I think).
	 */
	public void setPreyNo(int n) {
		this.preyNo = n;
	}
	
	/**
	 * Return the number of the shark's current prey.
	 * @return variable preyNo.
	 */
	public int getPreyNo() {
		return preyNo;
	}
}
