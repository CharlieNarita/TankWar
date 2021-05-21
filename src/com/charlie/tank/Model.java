package com.charlie.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.charlie.tank.chainofresponsibility.ColliderChain;

public class Model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<AbstractItem> items;
	
	private PlayerTank myTank;
	
	ColliderChain chain = new ColliderChain();
	
	
	public Model() {
		initItems();
	}
	
	
	public void initItems() {
		
		items = new ArrayList<>();
		
		myTank = new PlayerTank(500, 500, Group.PLAYER, Direction.Up);

		int tankCount = Integer.parseInt(PropertyMgr.get("initNpcTankCount"));

		for (int i = 0; i < tankCount; i++) {
			this.addItem(new NpcTank(50 + i * 80, 100, Group.NPC, Direction.Down));
		}
		
		this.addItem(new BrickWall(300, 300, 400, 25));
		
		this.addItem(myTank);
		
	}
	
	public void addItem(AbstractItem item) {
		items.add(item);
	}
	
	
	public void paint(Graphics g) {
		//show info of current items
				Color c = g.getColor();
				g.setColor(Color.WHITE);
				g.drawString("[Items Numbers : " + items.size() + "]", 10, 50);
				g.setColor(c);
		
		
				
		for(int i=0; i<items.size(); i++) {
			AbstractItem item = items.get(i);
			if(!item.isAlive()) {
				items.remove(i);
			}
		}
		
		for(int i=0; i<items.size(); i++) { 
//			if(!items.get(i).isAlive()) {
//				items.remove(i);
//				continue;
//			}
			AbstractItem item1 = items.get(i);
			for(int j=0; j<items.size(); j++) {
				AbstractItem item2 = items.get(j);
				chain.collide(item1, item2);
			}
			if(items.get(i).isAlive()) {
				items.get(i).paint(g);
			}
//			items.get(i).paint(g);
		}
		
		
//		 attention this block of codes, its easy to make bug.
//		for (int i = 0; i < shells.size(); i++) {
//			for (int j = 0; j < tanks.size(); j++) {
//				if (!shells.get(i).isFlying() || !tanks.get(j).isAlive()) {
//					break;
//				}
//				shells.get(i).hitTank(tanks.get(j));
//
//			}
//			if (!shells.get(i).isFlying()) {
//				shells.remove(i);
//			} else {
//				shells.get(i).paint(g); // must add else before this line!!!
//			}
//		}
//
//		for (int i = 0; i < tanks.size(); i++) {
//			if (!tanks.get(i).isAlive()) {
//				tanks.remove(i);
//			} else {
//				tanks.get(i).paint(g);
//			}
//		}
//
//		for (int i = 0; i < explodes.size(); i++) {
//			if (!explodes.get(i).isAlive()) {
//				explodes.remove(i);
//			} else {
//				explodes.get(i).paint(g);
//			}
//		}
	}
	
	public PlayerTank getMyTank() {
		return myTank;
	}
	
	public List<AbstractItem> getItems() {
		return items;
	}
	
	
}
