package com.charlie.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class NpcTank extends AbstractVehicle {

	private int oldX;
	private int oldY;

	private static int width = ResourceMgr.npcTankUp.getWidth();
	private static int height = ResourceMgr.npcTankUp.getHeight();
	
	private Rectangle rect;

	public NpcTank(int x, int y, Group group, Direction direction) {
		super(x, y, group, direction);
		
		rect = new Rectangle(x, y, width, height);
		
		setMoving(true);
		setSpeed(3);
	}

	public void paint(Graphics g) {
		
		if (!isAlive()) {
			return;
		}
		
		switch (direction) {
		case Up:
			g.drawImage(ResourceMgr.npcTankUp, x, y, null);
			break;
		case Down:
			g.drawImage(ResourceMgr.npcTankDown, x, y, null);
			break;
		case Left:
			g.drawImage(ResourceMgr.npcTankLeft, x, y, null);
			break;
		case Right:
			g.drawImage(ResourceMgr.npcTankRight, x, y, null);
			break;
		default:
			break;
		}

		move();
		
		//update rectangle
		UpdateRect();
	}

	public void move() {
		if (!isMoving()) {
			return;
		}

		currentPosition();

		switch (direction) {
		case Left:
			x -= speed;
			break;
		case Right:
			x += speed;
			break;
		case Up:
			y -= speed;
			break;
		case Down:
			y += speed;
			break;
		default:
			break;
		}

		boundsCheck();

		randomDir();

		if (r.nextInt(100) > threshold) {
			fire();
		}
	}

	private Random r = new Random();
	private int threshold = 95;

	private void randomDir() {
		if (r.nextInt(100) > threshold) {
			direction = Direction.randomDir();
		}
	}

	private void fire() {
		int biasX = x + getNpcTankWidth() / 2 - Shell.getShellWidth() / 2;
		int biasY = y + getNpcTankHeight() / 2 - Shell.getShellHeight() / 2;
		TankFrame.INSTANCE.getModel().addItem(new Shell(biasX, biasY, group, direction));
	}

	public void currentPosition() {
		oldX = x;
		oldY = y;
	}

	private void boundsCheck() {
		if (x < 0 || y < 30 || x > (TankFrame.GAME_WIDTH - getNpcTankWidth())
				|| y > (TankFrame.GAME_HEIGHT - getNpcTankHeight())) {
			goBack();
		}
	}

	public void goBack() {
		x = oldX;
		y = oldY;
	}
	
	public void UpdateRect() {
		rect.x = x;
		rect.y = y;
	}

	public void isDestroy() {
		int biasX = x + getNpcTankWidth() / 2 - Explode.getExplodeWidth() / 2;
		int biasY = y + getNpcTankHeight() / 2 - Explode.getExplodeHeight() / 2;
		TankFrame.INSTANCE.getModel().addItem(new Explode(biasX, biasY));
	}

	public int getWidth() {
		return ResourceMgr.npcTankUp.getWidth();
	}

	public int getHeight() {
		return ResourceMgr.npcTankUp.getHeight();
	}

	public static int getNpcTankWidth() {
		return width;
	}

	public static int getNpcTankHeight() {
		return height;
	}
	
	public Rectangle getRect() {
		return rect;
	}
}
