package com.charlie.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Shell extends AbstractItem implements Movable {

	public boolean isFlying() {
		return flying;
	}

	public void setFlying(boolean flying) {
		this.flying = flying;
	}

	public Rectangle getRect() {
		return rect;
	}
	
	private static int width = ResourceMgr.shellsUp.getWidth();
	private static int height = ResourceMgr.shellsUp.getHeight();
	
	
	private boolean flying = true;
	private int flySpeed = 10;
	private Group group;
	private Direction direction;
	
	private Rectangle rect;

	public Shell(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public Shell(int x, int y, Group group, Direction direction) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.group = group;
		this.direction = direction;
		rect = new Rectangle(x, y, width, height);
	}

	public void paint(Graphics g) {
		
		if(!isFlying()) {
			return;
		}
		
		switch (direction) {
		case Up:
			g.drawImage(ResourceMgr.shellsUp, x, y, null);
			break;
		case Down:
			g.drawImage(ResourceMgr.shellsDown, x, y, null);
			break;
		case Left:
			g.drawImage(ResourceMgr.shellsLeft, x, y, null);
			break;
		case Right:
			g.drawImage(ResourceMgr.shellsRight, x, y, null);
			break;
		default:
			break;
		}
		move();
		
		//update the rectangle 
		UpdateRect();
		
		//test rectangle
//		Color c = g.getColor();
//		g.setColor(Color.YELLOW);
//		g.drawRect(rect.x, rect.y, rect.width, rect.height);
//		g.setColor(c);
		
		
	}
	
	public void UpdateRect() {
		rect.x = x;
		rect.y = y;
	}

	public void move() {
		switch (direction) {
		case Left:
			x -= flySpeed;
			break;
		case Right:
			x += flySpeed;
			break;
		case Up:
			y -= flySpeed;
			break;
		case Down:
			y += flySpeed;
			break;
		default:
			break;
		}

		boundsCheck();
	}

//	public void hitTank(AbstractVehicle vehicle) {
//		// TODO Auto-generated method stub
//		if (!this.isFlying() || !vehicle.isAlive()) {
//			return;
//		}
//		if (vehicle.getGroup() == this.getGroup()) {
//			return;
//		}
//		Rectangle rectSH = new Rectangle(getX(), getY(), getShellWidth(), getShellHeight());
//		Rectangle rectTK = new Rectangle(vehicle.getX(), vehicle.getY(), vehicle.getWidth(), vehicle.getHeight());
//		if (rectSH.intersects(rectTK)) {
//			this.setFlying(false);
//			vehicle.setAlive(false);
//			if (vehicle instanceof NpcTank) {
//				((NpcTank) vehicle).die();
//			}
//		}
//
//	}
	
	

//	public void hitWall(AbstractConstruction construction) {
//		
//	}

	private void boundsCheck() {
		if (x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			setFlying(false);
		}
	}

	public Group getGroup() {
		// TODO Auto-generated method stub
		return group;
	}
	
	public static int getShellWidth() {
		return width;
	}

	public static int getShellHeight() {
		return height;
	}

	@Override
	public boolean isAlive() {
		return flying;
	}

}
