package com.charlie.tank;

import java.util.Random;

public enum Direction {
	Left, Right, Up, Down;

	private static Random r = new Random();

	public static Direction randomDir() {
		return values()[r.nextInt(values().length)];
	}

}
