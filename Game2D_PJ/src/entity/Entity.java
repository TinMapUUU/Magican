package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int worldX, worldY;
	public int speed;
	
				// 1: TRÁI	  2: PHẢI
	public BufferedImage up1, up2, right1, right2,
						down1, down2, left1, left2;
	public String direction;
	
			//BIẾN CHAR MOVE
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	public boolean collisionOn = false;
	
}