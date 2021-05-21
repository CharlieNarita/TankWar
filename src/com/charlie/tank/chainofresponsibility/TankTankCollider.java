package com.charlie.tank.chainofresponsibility;

import com.charlie.tank.AbstractItem;
import com.charlie.tank.AbstractVehicle;

public class TankTankCollider implements Collider{
	@Override
	public boolean collide(AbstractItem item1, AbstractItem item2) {
		if(item1 != item2 && item1 instanceof AbstractVehicle && item2 instanceof AbstractVehicle) {
			AbstractVehicle tank1 = (AbstractVehicle)item1;
			AbstractVehicle tank2 = (AbstractVehicle)item2;
//			if(!tank1.isAlive() || !tank2.isAlive()) {
//				return false;
//			}
			if(tank1.isAlive() && tank2.isAlive()) {
				if(tank1.getRect().intersects(tank2.getRect())) {
					tank1.goBack(); 
					tank2.goBack();
				}
			}
		} 
		return true;
	}
}
