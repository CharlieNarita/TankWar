package com.charlie.tank.chainofresponsibility;

import com.charlie.tank.AbstractConstruction;
import com.charlie.tank.AbstractItem;
import com.charlie.tank.Shell;

public class ShellWallCollider implements Collider {

	@Override
	public boolean collide(AbstractItem item1, AbstractItem item2) {
		// TODO Auto-generated method stub
		if(item1 instanceof Shell && item2 instanceof AbstractConstruction) {
			Shell shell = (Shell)item1;
			AbstractConstruction wall = (AbstractConstruction)item2;
			if(shell.isFlying()) {
				if(shell.getRect().intersects(wall.getRect())) {
					shell.setFlying(false);
					return false;
				}
			}
		} else if(item1 instanceof AbstractConstruction && item2 instanceof Shell) {
			return collide(item2, item1);
		}
		
		return true;
	}

}


//public void hitTank(AbstractVehicle vehicle) {

//if (!this.isFlying() || !vehicle.isAlive()) {
//	return;
//}
//if (vehicle.group == this.group) {
//	return;
//}
//Rectangle rectCB = new Rectangle(x, y, getShellWidth(), getShellHeight());
//Rectangle rectTK = new Rectangle(vehicle.getX(), vehicle.getY(), vehicle.getWidth(), vehicle.getHeight());
//if (rectCB.intersects(rectTK)) {
//	this.setFlying(false);
//	vehicle.setAlive(false);
//	if (vehicle instanceof NpcTank) {
//		((NpcTank) vehicle).die();
//	}
//}
//
//}