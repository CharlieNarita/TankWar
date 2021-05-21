package com.charlie.tank;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class AbstractItem implements Serializable{
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int x;
	public int y;
	public int width;
	public int height;
	public boolean alive;
	
	public AbstractItem(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public AbstractItem(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void paint(Graphics g);

	public abstract boolean isAlive();
	
}
