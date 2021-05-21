package com.charlie.tank.chainofresponsibility;

import com.charlie.tank.AbstractConstruction;
import com.charlie.tank.AbstractItem;
import com.charlie.tank.AbstractVehicle;

public class TankWallCollider implements Collider{

	@Override
	public boolean collide(AbstractItem item1, AbstractItem item2) {
		// TODO Auto-generated method stub
		if(item1 instanceof AbstractVehicle && item2 instanceof AbstractConstruction) {
			AbstractVehicle tank = (AbstractVehicle)item1;
			AbstractConstruction wall = (AbstractConstruction)item2;
			if(!tank.isAlive() || !wall.isAlive()) {
				return false;
			}
			if(tank.getRect().intersects(wall.getRect())) {
				tank.goBack();
			}
			
		} else if(item1 instanceof AbstractConstruction && item2 instanceof AbstractVehicle) {
			return collide(item2, item1);
		}
		
		return true;
	}

}
