package com.charlie.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceMgr {
	public static BufferedImage playerTankUp, playerTankDown, playerTankLeft, playerTankRight;
	public static BufferedImage npcTankUp, npcTankDown, npcTankLeft, npcTankRight;
	public static BufferedImage shellsUp, shellsDown, shellsLeft, shellsRight;
	public static BufferedImage[] explodes = new BufferedImage[8];

	// static initialize block...
	static {
		try {
			playerTankUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/greenTank.png"));
			playerTankDown = ImageUtil.rotateImage(playerTankUp, 180);
			playerTankLeft = ImageUtil.rotateImage(playerTankUp, -90);
			playerTankRight = ImageUtil.rotateImage(playerTankUp, 90);

			npcTankUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/redTank.png"));
			npcTankDown = ImageUtil.rotateImage(npcTankUp, 180);
			npcTankLeft = ImageUtil.rotateImage(npcTankUp, -90);
			npcTankRight = ImageUtil.rotateImage(npcTankUp, 90);

			shellsUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/cannonBall.png"));
			shellsDown = ImageUtil.rotateImage(shellsUp, 180);
			shellsLeft = ImageUtil.rotateImage(shellsUp, -90);
			shellsRight = ImageUtil.rotateImage(shellsUp, 90);

			for (int i = 0; i < 8; i++) {
				explodes[i] = ImageIO
						.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + i + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
