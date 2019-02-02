package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import java.util.Random;

public class Fish {
	
	public double x, y;
	public Color color;
	public boolean facingLeft;
	public boolean isLittle;
	
	public double speedX, speedY;
	public double destX, destY;
	// speed = sqrt(speedX^2 + speedY^2)
	public double speed;
	
	Random rand = new Random();
	
	public Fish(Color c, double startX, double startY, boolean left, boolean little) {
		this.x = startX;
		this.y = startY;
		this.destX = startX;
		this.destY = startY;
		this.color = c;
		this.facingLeft = left;
		this.isLittle = little;
		
		// Random x-speed and y-speed.
		this.speedX = 0.1 + 2*rand.nextDouble();
		this.speedY = 0.1 + 2*rand.nextDouble();
		if (!facingLeft) {
			// Move backwards if facing right
			this.speedX *= -1;
		}
		// Actual speed.
		speed = Math.sqrt(Math.pow(speedX, 2) + Math.pow(speedY, 2));
	}
	
	/**
	 * Get position of fish.
	 * @return
	 */
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	
	public boolean swim() {
		double eps = 3;
		boolean isSwimming = true;
		
		// If fish is not close to destination.
		if ((Math.abs(this.x - this.destX) >= eps) && 
			(Math.abs(this.x - this.destX) >= eps)) {
			this.x -= speedX;
			this.y -= speedY;
		
		// If fish reaches destination.
		} else {
			isSwimming = false;
		}
		return isSwimming;
	}
	
	public void draw(Graphics2D g) {
		// If fish not swimming (i.e. reach destination), pick new destination.
		if (!swim()) {
			pickDestination();
		};
		
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
		
		// Draw destination for easier visualization.
		Shape dest = new Ellipse2D.Double(destX, destY, 10, 10);
		g.setColor(color);
		g.fill(dest);
		g.setColor(Color.black);
		g.draw(dest);
	}
	
	public void pickDestination() {
		// Pick a random destination
    	this.destX = Aquarium.WIDTH*rand.nextDouble();
		this.destY = Aquarium.HEIGHT*rand.nextDouble();
		
		// If x-destX = 0, pick a new destination. This is to prevent division by zero error.
		// Did this because I ran into the NaN thing a lot and it ruined my code.
		while (Math.abs(x-destX)<=1e-3) {
			this.destX = Aquarium.WIDTH*rand.nextDouble();
			this.destY = Aquarium.HEIGHT*rand.nextDouble();
		}
		
		// Calculate speedX and speedY using trig.
		double length = Math.sqrt(Math.pow(x-destX, 2) + Math.pow(y-destY, 2));
		double tan = (y-destY)/(x-destX);
		double cos = (x-destX)/(length);

		this.speedX = cos*speed;
		this.speedY = tan*speedX;
		
		// Change fish direction.
		if (speedX > 0) {
			facingLeft = true;
		} else {
			facingLeft = false;
		}
	}
}