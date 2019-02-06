/**
 * 
 */
package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Class for fish that has stomach and can digest. Each fish has a bar to represent its hunger.
 * @author Minh Phuong
 *
 */
public class HungryFish extends Fish {
	
	Random rand = new Random();
	/**
	 * Max hunger, like MaxHP but for stomach.
	 */
	int hungerMax;

	/**
	 * Current hunger. One simply can't stay full forever without food intakes.
	 */
	int hunger;
	
	/**
	 * Hungry and need to eat = true. Happen when hunger drops to 0.
	 */
	boolean isHungry;

	/**
	 * Create a fish similar to what Fish do but this one can also goes hungry.
	 * @param c - color type Color
	 * @param startX - start x position
	 * @param startY - start y position
	 * @param left - facing direction type boolean
	 * @param little - little fish or big fish
	 */
	public HungryFish(Color c, double startX, double startY, boolean left, boolean little) {
		super(c, startX, startY, left, little);
		
		this.hungerMax = 1000 + rand.nextInt(2000);
		this.hunger = this.hungerMax;
		this.isHungry = false;
	}
	
	/**
	 * Decrease hunger. Should have put fullness instead but oh well.
	 */
	public void hungry() {
		if (hunger > 0) {
			this.hunger -= 1;
		}
	}
	
	/**
	 * Call on Fish's draw methods to draw fish as normal, and then draw the hunger bar.
	 */
	public void draw(Graphics2D g) {
		// Draw fish
		super.draw(g);
		
		// Decrease hunger
		hungry();
		
		// Draw hunger meter.
		Shape hungerOutline = new Rectangle2D.Double(this.x - 40, this.y - 40, 80, 10);
		
		// Variable to calculate how long the meter should be.
		double hungerPercent = (double) hunger/hungerMax;
		Shape hungerMeter = new Rectangle2D.Double(this.x - 40, this.y - 40, hungerPercent*80, 10);
		
		// Determine the color of the meter. Hunger > 70% = green, 30% < hunger < 70% = yellow, < 30% = red.
		if (hungerPercent > 0.7) {
			g.setColor(Color.green);
		} else if (hungerPercent > 0.3) {
			g.setColor(Color.yellow);
		} else {
			g.setColor(Color.red);
			// Also set isHungry to true when the bar drops to red.
			this.isHungry = true;
		}
		
		// Draw the meter.
		g.fill(hungerMeter);
		
		g.setColor(Color.black);
		g.draw(hungerOutline);
	}
	
	/**
	 * Getter for isHungry
	 * @return true if fish is hungry, false if otherwise.
	 */
	public boolean getIsHungry() {
		return isHungry;
	}
	
	/**
	 * What to do when the fish ate (set is Hungry to false, set current hunger to max)
	 */
	public void ate() {
		this.isHungry = false;
		this.hunger = this.hungerMax;
	}
}