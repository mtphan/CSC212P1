package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import me.jjfoley.gfx.GFX;

/**
 * Aquarium is a graphical "application" that uses some code I built and have
 * shared with you that takes care of opening a window and communicating with
 * the user in a simple way.
 * 
 * The method draw is called 50 times per second, so we make an animation by
 * drawing our fish in one place, and moving that place slightly. The next time
 * draw gets called, our fish looks like it moved!
 * 
 * @author jfoley
 *
 */
public class Aquarium extends GFX {
	/**
	 * This is a static variable that tells us how wide the aquarium is.
	 */
	public static int WIDTH = 1000;
	/**
	 * This is a static variable that tells us how tall the aquarium is.
	 */
	public static int HEIGHT = 600;
	
	/**
	 * Put a snail on the top of the tank.
	 */
	Snail algorithm = new Snail(177, Snail.HEIGHT+1, "top");
	
	/**
	 * fishArr stores pointers to fish objects in the tank.
	 */
	public Fish[] fishArr = new Fish[10];

	/**
	 * BubbleSystem that control bubbles.
	 */
	BubbleSystem bubbles = new BubbleSystem();
	
	/**
	 * Greeness of the tank.
	 */
	int greeness = 0;
	
	/**
	 * This is a constructor, code that runs when we make a new Aquarium.
	 */
	public Aquarium() {
		// Here we ask GFX to make our window of size WIDTH and HEIGHT.
		// Don't change this here, edit the variables instead.
		super(WIDTH, HEIGHT);
		
		// Generate some number of fish.
		Random rand = new Random();
		for (int i=0; i<fishArr.length; i++) {
			
			// Pick random x,y, color, isLittle and facingLeft.
			double x = WIDTH*rand.nextDouble();
			double y = HEIGHT*rand.nextDouble();
			Color c = new Color(rand.nextInt(205)+50, rand.nextInt(205)+50, rand.nextInt(205)+50);
			boolean little = rand.nextBoolean();
			boolean left = rand.nextBoolean();
			
			// Add the fish into fishArr.
			fishArr[i] = new Fish(c, x, y, left, little);
		}
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		// Draw the "ocean" background.
		g.setColor(new Color(0, greeness, 100));
		
		// Green-fy the sink if Algorithm is sleeping.
		if (algorithm.getIsSleeping()) {
			greeness += 1;
		}
		// Algorithm cleans the sink.
		greeness = algorithm.clean(greeness);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw our bubbles.
		bubbles.draw(g);

		// Draw our snail!
		algorithm.draw(g);
		
		// Draw all the fish in fishArr!
		for (Fish fish : fishArr) {
			fish.draw(g);
		}
	
	}

	public static void main(String[] args) {
		// Note that we can store an Aquarium in a variable of type GFX because Aquarium
		// is a very specific GFX, much like 7 can be stored in a variable of type int!
		GFX app = new Aquarium();
		app.start();
	}

}
