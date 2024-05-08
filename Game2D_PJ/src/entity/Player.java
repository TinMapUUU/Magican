package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.TileSize/2);
		screenY = gp.screenHeight/2 - (gp.TileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;

		setDefaultValues();
		getPlayerImage();
	}
	// SET UP GTRI MẶC ĐỊNH
	public void setDefaultValues() {

		worldX = gp.TileSize *19; 	//VỊ TRÍ CỐ ĐỊNH 
		worldY = gp.TileSize *19;	//VỊ TRÍ CỐ ĐỊNH
		speed = 4;
		direction = "down";
	}

	// SET UP ẢNH MAIN-CHAR
	public void getPlayerImage() {

		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/main_player/Up - Left.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/main_player/Up - Right.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/main_player/Down - Left.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/main_player/Down - Right.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/main_player/Right - Left.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/main_player/Right - Right.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/main_player/Left - Left.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/main_player/Left - Right.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {

		if(keyH.upPressed == true ||
				keyH.downPressed == true ||
				keyH.leftPressed == true ||
				keyH.rightPressed == true) {

			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";			
			}
			else if(keyH.rightPressed == true) {
				direction = "right";			
			}
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			spriteCounter++;
			if(spriteCounter > 8) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}			
		}		
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		switch (direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}			
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		default:
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.TileSize, gp.TileSize, null);
	}

}