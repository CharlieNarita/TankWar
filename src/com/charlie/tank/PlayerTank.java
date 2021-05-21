package com.charlie.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.charlie.tank.strategy.Fireable;

public class PlayerTank extends AbstractVehicle implements Movable {
	
	private boolean btnLeft = false;
	private boolean btnRight = false;
	private boolean btnUp = false;
	private boolean btnDown = false;
	
	private int oldX;
	private int oldY;
	
	private static int width = ResourceMgr.playerTankUp.getWidth();
	private static int height = ResourceMgr.playerTankUp.getHeight();
	
	private Fireable fireStrategy = null;
	
	private Rectangle rect;

	public PlayerTank(int x, int y, Group group, Direction direction) {
		super(x, y, group, direction);
		setSpeed(5);
		
		fireStrategy = this.initFireStrategy();
		
		rect = new Rectangle(x, y, width, height);
	}

	public void paint(Graphics g) {
		
//		overwhelming();
		
//		if (!isAlive()) {
//			return;
//		}
		
		switch (direction) {
		case Up:
			g.drawImage(ResourceMgr.playerTankUp, x, y, null);
			break;
		case Down:
			g.drawImage(ResourceMgr.playerTankDown, x, y, null);
			break;
		case Left:
			g.drawImage(ResourceMgr.playerTankLeft, x, y, null);
			break;
		case Right:
			g.drawImage(ResourceMgr.playerTankRight, x, y, null);
			break;
		default:
			break;
		}

		move();
		
		//update rectangle
		UpdateRect();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			btnLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			btnRight = true;
			break;
		case KeyEvent.VK_UP:
			btnUp = true;
			break;
		case KeyEvent.VK_DOWN:
			btnDown = true;
			break;
		default:
			break;
		}
		setDirection();
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			btnLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			btnRight = false;
			break;
		case KeyEvent.VK_UP:
			btnUp = false;
			break;
		case KeyEvent.VK_DOWN:
			btnDown = false;
			break;
		case KeyEvent.VK_CONTROL:
			fire();
			break;
		default:
			break;
		}
		setDirection();
	}
	
	private void fire() {
		if(!isAlive()) {
			return;
		}
		fireStrategy.fire(this);	
	}
	
	private Fireable initFireStrategy() {
		// ClassLoader loader = PlayerTank.class.getClassLoader(); 
				// get PlayerTank's ClassLoader
		
		String className = PropertyMgr.get("fireStrategy"); // get string type value from config.file 

		try {
			// Class<?> fireStrategy = loader.loadClass("com.charlie.tank.strategy."+className); 
			// another way to loading class by ClassLoader

			Class<?> clazz = Class.forName("com.charlie.tank.strategy." + className); 
			// get actual class through loading class name
			
			fireStrategy = (Fireable) (clazz.getDeclaredConstructor().newInstance()); 
			// initial new instance of actual class																						
																									
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fireStrategy;
	}
	
	
	private void setDirection() {
		if (!btnLeft && !btnRight && !btnUp && !btnDown) {
			moving = false;
		} else {
			moving = true;
			if (btnLeft && !btnRight && !btnUp && !btnDown) {
				direction = Direction.Left;
			}
			if (!btnLeft && btnRight && !btnUp && !btnDown) {
				direction = Direction.Right;
			}
			if (!btnLeft && !btnRight && btnUp && !btnDown) {
				direction = Direction.Up;
			}
			if (!btnLeft && !btnRight && !btnUp && btnDown) {
				direction = Direction.Down;
			}
		}

	}

	public void move() {
		if (!moving) {
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
		
	}
	
	public void currentPosition() {
		oldX = x;
		oldY = y;
	}

	private void boundsCheck() {
		if (x < 0 || y < 30 || x > (TankFrame.GAME_WIDTH - getWidth()) || y > (TankFrame.GAME_HEIGHT - getHeight())) {
			goBack();
		}
	}

	public void goBack() {
		x = oldX;
		y = oldY;
//		System.out.println("player goBack()");
//		System.out.println("x = " + x + ", y = " + y);
		
	}
	
	public void UpdateRect() {
		rect.x = x;
		rect.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static int getPlayerTankWidth() {
		return ResourceMgr.playerTankUp.getWidth();
	}

	public static int getPlayerTankHeight() {
		return ResourceMgr.playerTankUp.getHeight();
	}
	
	public Rectangle getRect() {
		return rect;
	}

	@Override
	public void isDestroy() {
		int biasX = x + getPlayerTankWidth() / 2 - Explode.getExplodeWidth() / 2;
		int biasY = y + getPlayerTankHeight() / 2 - Explode.getExplodeHeight() / 2;
		TankFrame.INSTANCE.getModel().addItem(new Explode(biasX, biasY));
	}
	
//	public void overwhelming() {
//		alive = true;
//	}
}
