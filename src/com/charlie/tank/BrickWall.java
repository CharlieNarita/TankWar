package com.charlie.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BrickWall extends AbstractConstruction {	
	
	private Rectangle rect;
	
	public BrickWall(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		rect = new Rectangle(x, y, width, height);
		alive = true;
	}
	
	public BrickWall(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(c);
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	@Override
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
