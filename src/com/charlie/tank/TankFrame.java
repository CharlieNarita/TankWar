package com.charlie.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class TankFrame extends Frame {

	public Model getModel() {
		return model;
	}


	private static final long serialVersionUID = 1L;

	public static final TankFrame INSTANCE = new TankFrame();
	
	public static final int GAME_WIDTH = 1024;
	public static final int GAME_HEIGHT = 768;
//	public static final int GAME_WIDTH = Integer.parseInt(PropertyMgr.get("gameWidth"));
//	public static final int GAME_HEIGHT = Integer.parseInt(PropertyMgr.get("gameHeight"));
	
	private Model model = new Model();
	
	
	private TankFrame() {
		this.setTitle("TankWar");
		this.setLocation(400, 100);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);

		this.addKeyListener(new TankKeyListener());

		model.initItems();
	}

	
	// repaint-->update-->paint
	public void paint(Graphics g) {

		model.paint(g);
	
	}

	
	// offScreenImage intend create image buffer in RAM for storing complete image
	// and then transit complete image into video memory entirely;
	Image offScreenImage = null;
	@Override
	// repaint-->update-->paint
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	private class TankKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_S) {
				save();
			} else if(key == KeyEvent.VK_L) {
				load();
			} else {
				model.getMyTank().keyPressed(e);
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			model.getMyTank().keyReleased(e);
		}
	}
	
	
	public void save() {
		ObjectOutputStream oos = null;
		try {
			File f = new File("C:/Users/C-2020/Desktop/tank.dat");
			FileOutputStream fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(model);
			oos.flush();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void load() {
		ObjectInputStream ois = null;
		
		try {
			File f = new File("C:/Users/C-2020/Desktop/tank.dat");
			FileInputStream fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			this.model = (Model)(ois.readObject());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
