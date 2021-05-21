package com.charlie.tank;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankFrame.INSTANCE.setVisible(true);

		for (;;) {
			try {
//				TimeUnit.MICROSECONDS.sleep(1000);
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			TankFrame.INSTANCE.repaint(); // repaint-->update-->paint
		}
	}

}
