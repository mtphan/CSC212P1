package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
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
	public static int WIDTH = 1200;
	/**
	 * This is a static variable that tells us how tall the aquarium is.
	 */
	public static int HEIGHT = 700;
	
	/**
	 * Put a snail on the top of the tank.
	 */
	Snail algorithm = new Snail(177, Snail.HEIGHT+1, "top");
	
	/**
	 * BubbleSystem that control bubbles.
	 */
	BubbleSystem bubbles = new BubbleSystem();
	
	/**
	 * Feeder that drops food pellets for hungry fish.
	 */
	Feeder feeder;
	
	/**
	 * Greenness of the tank. Starts at 0.
	 */
	int greenness = 0;
	
	/**
	 * Stores pointers to all Fish objects in the tank.
	 */
	List<Fish> fishLst = new ArrayList<>();
	
	/**
	 * List of all the fish that are HungryFish.
	 */
	List<HungryFish> hungryFish = new ArrayList<>();
	
	Random rand = new Random();
	
	/**
	 * Shark variable.
	 */
	Shark megalodon = new Shark(WIDTH*rand.nextDouble(), HEIGHT*rand.nextDouble(), rand.nextBoolean());
	
	/**
	 * This is a constructor, code that runs when we make a new Aquarium.
	 */
	public Aquarium() {
		// Here we ask GFX to make our window of size WIDTH and HEIGHT.
		// Don't change this here, edit the variables instead.
		super(WIDTH, HEIGHT);
		int fishNum = 10;
		createFish(fishNum);
		
		// Create feeder instance with the number of food pellet equal to number of HungryFish.
		feeder = new Feeder(this.hungryFish.size());
	}
	
	/**
	 * Create a bunch of fish with random attributes.
	 * @param fishNum - total number of fish in the tank.
	 */
	public void createFish(int fishNum) {
		for (int i=0; i<fishNum; i++) {
			
			// Pick random x,y, color, isLittle and facingLeft.
			double x = WIDTH*rand.nextDouble();
			double y = HEIGHT*rand.nextDouble();
			Color c = Color.getHSBColor(rand.nextFloat(), 0.8f, 1.0f);
			boolean little = rand.nextBoolean();
			boolean left = rand.nextBoolean();
			
			// Choose to create HungryFish or normal Fish randomly.
			if (rand.nextBoolean()) {
				// Add HungryFish into hungryFish list AND fishArr.
				HungryFish fishPointer = new HungryFish(c, x, y, left, little);
				this.hungryFish.add(fishPointer);
				this.fishLst.add(fishPointer);
			} else {
				this.fishLst.add(new Fish(c, x, y, left, little));
			}
		}
	}
	
	public void pickAllDestinations(Graphics2D g) {
		// Pick destination for the HungryFish
		for (HungryFish fish : hungryFish) {
			
			// Pick destination fish is hungry.
			if (fish.getIsHungry()) {
				// Feeder drop food when hungry. Each HungryFish has a food pellet that
				// corresponds to itself, which will be dropped by the feeder when it is hungry.
				feeder.dropFood(g, hungryFish.indexOf(fish));
				
				// If the food is visible (i.e. has fell out of the tube)
				if (feeder.visible(hungryFish.indexOf(fish))) {
					// Fish immediately go toward the food. Continuing updating the food's position
					hungryFish.get(hungryFish.indexOf(fish)).pickDestination(Feeder.pellets[hungryFish.indexOf(fish)].getX(),
													  		 				 Feeder.pellets[hungryFish.indexOf(fish)].getY());
					
					// When fish reaches food, fish eat food and food's position is reset.
					if (fish.reachDestination()) {
						fish.ate();
						feeder.reset(hungryFish.indexOf(fish));
					}
				// Fish stays in the food zone (under the tube) when food is not visible.
				} else {
					hungryFish.get(hungryFish.indexOf(fish)).pickDestination(Feeder.feederX, Feeder.feederX + Feeder.feederW,
																			 Feeder.feederH + 60, HEIGHT);
				}
			}
		}
		
		// Pick destination for the shark.
		if (megalodon.reachDestination()) {
			// Set the prey to be one of the fish in fishLst.
			megalodon.setPreyNo(rand.nextInt(fishLst.size() - 1));
		}
		
		// Shark goes to the fish. This pickDestination method allow constant updating.
		// This shark is a vegetarian.
		megalodon.pickDestination(fishLst.get(megalodon.getPreyNo()).getX(),
								  fishLst.get(megalodon.getPreyNo()).getY());
		
		// Pick destination for all fish, including HungryFish and Shark.
		// If HungryFish already picked a destination in the food zone it won't pick a new one here.
		for (Fish fish : fishLst) {
			fish.pickDestination();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// Draw the "ocean" background.
		g.setColor(new Color(0, greenness, 100));
		
		// Green-fy the sink if Algorithm is sleeping.
		if (algorithm.getIsSleeping()) {
			greenness += 1;
		}
		
		// Algorithm the Snail cleans the sink. We draw him later.
		greenness = algorithm.clean(greenness);
		g.fillRect(0, 0, getWidth(), getHeight());
		

		// Pick destination for all the fish. Group them together because they are too long.
		pickAllDestinations(g);
		
		// Draw the feeder.
		feeder.drawFeeder(g);
		
		// Draw our bubbles + bubble source.
		bubbles.draw(g);

		// Draw our snail!
		algorithm.draw(g);		
		
		// Draw all of our fish!
		for (Fish fish : fishLst) {
			fish.draw(g);
		}
		
		// Draw our shark.
		megalodon.draw(g);
	}

	public static void main(String[] args) {
		// Note that we can store an Aquarium in a variable of type GFX because Aquarium
		// is a very specific GFX, much like 7 can be stored in a variable of type int!
		GFX app = new Aquarium();
		app.start();
	}
	
}
