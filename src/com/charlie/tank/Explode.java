package com.charlie.tank;

import java.awt.Graphics;

public class Explode extends AbstractItem {

	@Override
	public String toString() {
		return "Explode [step=" + step + ", alive=" + alive + ", x=" + x + ", y=" + y + ", width=" + width + ", height="
				+ height + "]";
	}

	private static int width = ResourceMgr.explodes[0].getWidth();
	private static int height = ResourceMgr.explodes[0].getHeight();
	private int step = 0;
	private boolean alive = true;
	
	public Explode(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public Explode(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	
	public void paint(Graphics g) {
		if (!isAlive()) {
			return;
		}
		g.drawImage(ResourceMgr.explodes[step], x, y, null);
		step++;
		if (step >= ResourceMgr.explodes.length) {
			this.over();
		}
	}

	private void over() {
		this.setAlive(false);
	}

	public static int getExplodeWidth() {
		return width;
	}

	public static int getExplodeHeight() {
		return height;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	
}
