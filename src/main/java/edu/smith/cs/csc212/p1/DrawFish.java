package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * This class is used statically (i.e., as a library) to draw fish in various positions and sizes.
 * 
 * @author jfoley
 *
 */
public class DrawFish {
	/**
	 * This code based on the Python fish in CSC111 labs, e.g.,
	 * https://jcrouser.github.io/CSC111/labs/lab-10-animation.html
	 * 
	 * @author jfoley
	 */
	public static void facingLeft(Graphics2D g, Color color, double x, double y) {
		// Draw the fish.
		g.setColor(color);

		Shape body = new Ellipse2D.Double(x - 40, y - 20, 80, 40);
		Shape tail = new Ellipse2D.Double(x + 30, y - 30, 20, 60);
		Shape eye = new Ellipse2D.Double(x - 15, y - 10, 10, 10);

		g.fill(body);

		// draw body outline.
		g.setColor(Color.black);
		g.draw(body);

		// draw eye (still black):
		g.fill(eye);

		// draw tail:
		Color tailColor = color.darker();
		g.setColor(tailColor);
		g.fill(tail);

		// draw tail outline.
		g.setColor(Color.black);
		g.draw(tail);
	}
	
	/**
	 * This method is implemented by creating a new Graphics "world" located at x,y
	 * that is scaled in a "backwards" X world.
	 * 
	 * @param g the original graphics world.
	 * @param color the color of the fish!
	 * @param x the x-coordinate of the position to draw the fish.
	 * @param y the y-coordinate of the position to draw the fish.
	 */
	public static void facingRight(Graphics2D g, Color color, double x, double y) {
		Graphics2D flipped = (Graphics2D) g.create();
		flipped.translate(x, y);
		flipped.scale(-1, 1);
		facingLeft(flipped, color, 0, 0);
		flipped.dispose();
	}

	/**
	 * This method is implemented by creating a new Graphics "world" located at x,y
	 * that is half the size of a regular one and calling our regular Fish method.
	 * 
	 * @param g the original graphics world.
	 * @param color the color of the fish!
	 * @param x the x-coordinate of the position to draw the fish.
	 * @param y the y-coordinate of the position to draw the fish.
	 */
	public static void smallFacingLeft(Graphics2D g, Color color, double x, double y) {
		Graphics2D halfSize = (Graphics2D) g.create();
		halfSize.translate(x, y);
		halfSize.scale(0.5, 0.5);
		facingLeft(halfSize, color, 0, 0);
		halfSize.dispose();
	}
	
	/**
	 * This method is implemented by creating a new Graphics "world" located at x,y
	 * that is half the size of a regular one and calling our regular Fish method.
	 * 
	 * @param g the original graphics world.
	 * @param color the color of the fish!
	 * @param x the x-coordinate of the position to draw the fish.
	 * @param y the y-coordinate of the position to draw the fish.
	 */
	public static void smallFacingRight(Graphics2D g, Color color, double x, double y) {
		Graphics2D halfSize = (Graphics2D) g.create();
		halfSize.translate(x, y);
		halfSize.scale(-0.5, 0.5);
		facingLeft(halfSize, color, 0, 0);
		halfSize.dispose();
	}

	/**
	 * This method is implemented by creating a new Graphics "world" located at x,y
	 * that is 1.5 the size of a regular one and calling our regular Fish method.
	 * 
	 * @param g the original graphics world.
	 * @param color the color of the fish!
	 * @param x the x-coordinate of the position to draw the fish.
	 * @param y the y-coordinate of the position to draw the fish.
	 */
	public static void bigFacingLeft(Graphics2D g, Color color, double x, double y) {
		Graphics2D twiceSize = (Graphics2D) g.create();
		twiceSize.translate(x, y);
		twiceSize.scale(1.5, 1.5);
		facingLeft(twiceSize, color, 0, 0);
		twiceSize.dispose();
	}
	
	/**
	 * This method is implemented by creating a new Graphics "world" located at x,y
	 * that is 1.5 the size of a regular one and calling our regular Fish method.
	 * 
	 * @param g the original graphics world.
	 * @param color the color of the fish!
	 * @param x the x-coordinate of the position to draw the fish.
	 * @param y the y-coordinate of the position to draw the fish.
	 */
	public static void bigFacingRight(Graphics2D g, Color color, double x, double y) {
		Graphics2D twiceSize = (Graphics2D) g.create();
		twiceSize.translate(x, y);
		twiceSize.scale(-1.5, 1.5);
		facingLeft(twiceSize, color, 0, 0);
		twiceSize.dispose();
	}
	
}
