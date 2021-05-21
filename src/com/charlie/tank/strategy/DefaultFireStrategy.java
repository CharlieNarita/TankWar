package com.charlie.tank.strategy;

import com.charlie.tank.AbstractVehicle;
import com.charlie.tank.Shell;
import com.charlie.tank.TankFrame;

public class DefaultFireStrategy implements Fireable {
	
	public void fire(AbstractVehicle av) {
		int biasX = av.getX() + av.getWidth() / 2 - Shell.getShellWidth() / 2;
		int biasY = av.getY() + av.getHeight() / 2 - Shell.getShellHeight() / 2;
		TankFrame.INSTANCE.getModel().addItem(new Shell(biasX, biasY, av.getGroup(), av.getDirection()));
	}
	
}
 