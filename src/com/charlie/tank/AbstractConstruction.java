package com.charlie.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

public abstract class AbstractConstruction extends AbstractItem {
	

	public AbstractConstruction(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public AbstractConstruction(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract void paint(Graphics g);

	public abstract Rectangle getRect();
	
	public abstract boolean isAlive();
	
}
