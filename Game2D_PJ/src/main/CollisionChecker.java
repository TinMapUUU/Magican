
package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.TileSize;
		int entityRightCol = entityRightWorldX/gp.TileSize;
		int entityTopRow = entityTopWorldY/gp.TileSize;
		int entityBottomRow = entityBottomWorldY/gp.TileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.TileSize;
			tileNum1 = gp.titleM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.titleM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.titleM.tile[tileNum1].collision == true || gp.titleM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
				
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.TileSize;
			tileNum1 = gp.titleM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.titleM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.titleM.tile[tileNum1].collision == true || gp.titleM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.TileSize;
			tileNum1 = gp.titleM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.titleM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.titleM.tile[tileNum1].collision == true || gp.titleM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.TileSize;
			tileNum1 = gp.titleM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.titleM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.titleM.tile[tileNum1].collision == true || gp.titleM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
		
	}

}
