package com.charlie.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class AbstractVehicle extends AbstractItem implements Movable {
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Direction getDirection() {
		return direction;
	}
	

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
		
	public boolean alive = true;
	public boolean moving = false;
	public int speed = 0;
	public Group group;
	public Direction direction;
	

	public AbstractVehicle(int x, int y, int width, int height, Group group, Direction direction) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		this.group = group;
		this.direction = direction;
	}

	public AbstractVehicle(int x, int y, Group group, Direction direction) {
		super(x, y);
		this.group = group;
		this.direction = direction;
	}

	public abstract void paint(Graphics g);
	
	public abstract void move();

	public abstract Rectangle getRect();
	
	public abstract void isDestroy();

	public abstract void goBack();
	
}
