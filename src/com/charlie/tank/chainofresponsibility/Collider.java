package com.charlie.tank.chainofresponsibility;

import java.io.Serializable;

import com.charlie.tank.AbstractItem;

public interface Collider extends Serializable {
	public boolean collide(AbstractItem item1, AbstractItem item2);
}
