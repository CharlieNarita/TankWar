package com.charlie.tank.strategy;

import java.io.Serializable;

import com.charlie.tank.AbstractVehicle;

public interface Fireable extends Serializable {
	public void fire(AbstractVehicle av);
}
