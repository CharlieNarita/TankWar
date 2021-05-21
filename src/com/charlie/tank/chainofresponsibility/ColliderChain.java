package com.charlie.tank.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

import com.charlie.tank.AbstractItem;
import com.charlie.tank.PropertyMgr;

public class ColliderChain implements Collider{
	private List<Collider> colliders;
	
	public ColliderChain() {
		initColliders();
	}
	
	public void initColliders() {
		colliders = new ArrayList<>();
		String[] collidersNames = PropertyMgr.get("colliders").split(",");
		for(String name : collidersNames) {
			try {
				Class<?> clazz = Class.forName("com.charlie.tank.chainofresponsibility." + name);
				Collider c = (Collider)clazz.getConstructor().newInstance();
				colliders.add(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean collide(AbstractItem item1, AbstractItem item2) {
		for(Collider collider : colliders) {
			if(!collider.collide(item1, item2)) {
				return false;
			}
		}
		
		return true;
	}
}
