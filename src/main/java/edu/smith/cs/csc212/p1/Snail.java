package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class Snail {
	/**
	 * How tall is the snail? Needed to put it upside-down.
	 */
	public static int HEIGHT = 50;
	/**
	 * The positioning of the snail. Use setSide(s) to change this.
	 */
	private String side;
	/**
	 * The position of the Snail; x-coordinate.
	 */
	public int x;
	/**
	 * The position of the Snail; y-coordinate.
	 */
	public int y;
	
	/**
	 * Moving speed of the snail.
	 */
	public int speed;
	
	public boolean isSleeping;
	
	/**
	 * Create a snail at (sx, sy) with position s.
	 * @param sx - x coordinate
	 * @param sy - y coordinate
	 * @param s - the "positioning" of the Snail
	 */
	public Snail(int sx, int sy, String s) {
		this.setSide(s);
		this.x = sx;
		this.y = sy;
		this.speed = 15;
		this.isSleeping = true;
	}
	
	/**
	 * Change which side of the the snail thinks its on.
	 * @param s - one of "top", "bottom", "left" or "right".
	 */
	public void setSide(String s) {
		this.side = s.toLowerCase();
	}
	
	/**
	 * Get variable isSleeping.
	 * @return boolean - true if snail is sleeping, false if otherwise.
	 */
	public boolean getIsSleeping() {
		return isSleeping;
	}
	
	/**
	 * Clean the sink by decreasing its greenness. Greenness is passed in as a parameter.
	 * @param greenness - the green component in the RGB of the background.
	 * @return greenness - return greenness after the tank was "cleaned" a little bit.
	 */
	public int clean(int greenness) {
		// Wake snail up at greenness = 150.
		if (greenness > 150) {
			isSleeping = false;
		}
		// Start cleaning...
		if (!isSleeping) {
			greenness -= 1;
		}
		// Go to sleep when greenness reaches 0 or less.
		if (greenness <= 0) {
			greenness = 0;
			isSleeping = true;
		}
		return greenness;
	}
	
	/**
	 * Move the snail about.
	 */
	public void move() {
		// Move the snail and flip in depends on its current side and position.
		if ("bottom".equals(this.side)) {
			this.x -= this.speed;
			if (this.x <= 0) {
				this.x = 50;
				setSide("left");
			}
		} else if ("top".equals(this.side)) {
			this.x += this.speed;
			if (this.x >= Aquarium.WIDTH) {
				this.x = Aquarium.WIDTH - 50;
				setSide("right");
			}
		} else if ("left".equals(this.side)) { 
			this.y -= this.speed;
			if (this.y <= 0) {
				this.y = HEIGHT;
				setSide("top");
			}
		} else { // we don't have to say "right" here.
			this.y += this.speed;
			if (this.y >= Aquarium.HEIGHT) {
				this.y = Aquarium.HEIGHT - 50;
				setSide("bottom");
			}
		}
	}
		
	/**
	 * Draw the snail at the current setup.
	 * @param g - the window to draw to.
	 */
	public void draw(Graphics2D g) {
		// By calling move here, if we want to move our snail, we can do so.
		// Move gets called by draw, so whenever draw gets called.
		if (!isSleeping) {
			this.move();
		}
		
		// By making a new Graphics2D object, we can move everything that gets drawn to it.
		// This is kind of tricky to wrap your head around, so I gave it to you.
		Graphics2D position = (Graphics2D) g.create();
		position.translate(x, y);
		
		Color eyeColor = Color.white;
		Color pupilColor = Color.black;
		
		if (isSleeping) {
			eyeColor = Color.red;
			pupilColor = Color.red;
		}
		
		// Note that I need to compare strings with ".equals" this is a Java weirdness.
		if ("bottom".equals(this.side)) {
			drawSnail(position, Color.red, Color.white, eyeColor, pupilColor);
		} else if ("top".equals(this.side)) {
			position.scale(-1, -1);
			drawSnail(position, Color.red, Color.white, eyeColor, pupilColor);
		} else if ("left".equals(this.side)) { 
			// Oh no, radians.
			position.rotate(Math.PI/2);
			drawSnail(position, Color.red, Color.white, eyeColor, pupilColor);
		} else { // we don't have to say "right" here.
			// Oh no, radians.
			position.rotate(-Math.PI/2);
			drawSnail(position, Color.red, Color.white, eyeColor, pupilColor);
		}
		
		// It's OK if you forget this, Java will eventually notice, but better to have it!
		position.dispose();
	}
	
	/**
	 * Kudos to Group 7, (Fall 2018).
	 * @param g The graphics object to draw with.
	 * @param bodyColor The color of the snail body.
	 * @param shellColor The color of the snail shell.
	 * @param eyeColor The color of the snail eye.
	 */
	public static void drawSnail(Graphics2D g, Color bodyColor, Color shellColor, Color eyeColor, Color pupilColor) {
		Shape body = new Rectangle2D.Double(0,0,40,50);
		Shape tentacleL = new Rectangle2D.Double(0,-20,5,20);
		Shape eyeWhiteL = new Ellipse2D.Double(-4, -28, 12, 12);
		Shape eyePupilL = new Ellipse2D.Double(-2, -26, 4, 4);
		
		g.setColor(bodyColor);
		g.fill(body);
		g.fill(tentacleL);
		
		g.setColor(eyeColor);
		g.fill(eyeWhiteL);
		g.setColor(pupilColor);
		g.fill(eyePupilL);

		Shape tentacleR = new Rectangle2D.Double(35,-20,5,20);
		Shape eyeWhiteR = new Ellipse2D.Double(35-4, -28, 12, 12);
		Shape eyePupilR = new Ellipse2D.Double(35+2, -26+4, 4, 4);
		
		g.setColor(bodyColor);
		g.fill(tentacleR);
		g.setColor(eyeColor);
		g.fill(eyeWhiteR);
		g.setColor(pupilColor);
		g.fill(eyePupilR);
		
		Shape shell3 = new Ellipse2D.Double(45, 20, 10, 10);
		Shape shell2 = new Ellipse2D.Double(35, 10, 30, 30);
		Shape shell1 = new Ellipse2D.Double(25, 0, 50, 50);
		
		g.setColor(shellColor);
		g.fill(shell1);
		g.setColor(Color.black);
		g.draw(shell1);
		g.setColor(shellColor);
		g.fill(shell2);
		g.setColor(Color.black);
		g.draw(shell2);
		g.setColor(shellColor);
		g.fill(shell3);
		g.setColor(Color.black);
		g.draw(shell3);
	}
}
