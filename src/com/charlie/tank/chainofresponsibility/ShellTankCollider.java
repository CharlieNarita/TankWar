package com.charlie.tank.chainofresponsibility;

import com.charlie.tank.AbstractItem;
import com.charlie.tank.AbstractVehicle;
import com.charlie.tank.Shell;

public class ShellTankCollider implements Collider{

	public boolean collide(AbstractItem item1, AbstractItem item2) {
		if(item1 instanceof Shell && item2 instanceof AbstractVehicle) {
			Shell shell = (Shell)item1;
			AbstractVehicle tank = (AbstractVehicle)item2;
			if(!shell.isFlying() || !tank.isAlive()) {
				return false;
			}
			if (shell.getGroup() == tank.getGroup()) {
				return true;
			}		
			if(shell.getRect().intersects(tank.getRect())) {
				shell.setFlying(false);
				tank.setAlive(false);
				tank.isDestroy();
				return false;
			}  else if(item1 instanceof AbstractVehicle && item2 instanceof Shell) {
				return collide(item2, item1);
			}
		}
		
		return true;
			
	}

}


//public void hitTank(AbstractVehicle vehicle) {
//	// TODO Auto-generated method stub
//	if (!this.isFlying() || !vehicle.isAlive()) {
//		return;
//	}
//	if (vehicle.group == this.group) {
//		return;
//	}
//	Rectangle rectCB = new Rectangle(x, y, getShellWidth(), getShellHeight());
//	Rectangle rectTK = new Rectangle(vehicle.getX(), vehicle.getY(), vehicle.getWidth(), vehicle.getHeight());
//	if (rectCB.intersects(rectTK)) {
//		this.setFlying(false);
//		vehicle.setAlive(false);
//		if (vehicle instanceof NpcTank) {
//			((NpcTank) vehicle).die();
//		}
//	}
//
//}