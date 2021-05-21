package com.charlie.tank.strategy;

import com.charlie.tank.AbstractVehicle;
import com.charlie.tank.Direction;
import com.charlie.tank.Shell;
import com.charlie.tank.TankFrame;

public class EnhanceFireStrategy implements Fireable {
	public void fire(AbstractVehicle av) {
		int biasX = av.getX() + av.getHeight() / 2 - Shell.getShellWidth() / 2;
		int biasY = av.getX() + av.getHeight() / 2 - Shell.getShellHeight() / 2;
		
		Direction[] dirs = Direction.values();
		for(Direction d : dirs) {
			TankFrame.INSTANCE.getModel().addItem(new Shell(biasX, biasY, av.getGroup(), d));
		}
	}
}
