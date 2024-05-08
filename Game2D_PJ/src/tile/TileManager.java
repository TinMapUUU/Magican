package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
		
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[21];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		
		getTileImage();
		loadMap("/maps/map1.txt");
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/00.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/01.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/02.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/03.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/04.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/05.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/06.png"));
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/07.png"));
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/08.png"));
			
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/09.png"));
			
			tile[11] = new Tile();
			tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/11.png"));
			
			tile[12] = new Tile();
			tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/12.png"));
			
			tile[13] = new Tile();
			tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/13.png"));
			
			tile[14] = new Tile();
			tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/14.png"));
			
			tile[15] = new Tile();
			tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/15.png"));
			tile[15].collision = true;
			
			tile[16] = new Tile();
			tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/16.png"));
			tile[16].collision = true;
			
			tile[17] = new Tile();
			tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/17.png"));
			tile[17].collision = true;
			
			tile[18] = new Tile();
			tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/18.png"));
			
			tile[19] = new Tile();
			tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/19.png"));
			
			tile[20] = new Tile();
			tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/20.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
					
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics2D g2) {
		
		
		int worldCol = 0;
		int worldRow = 0;
 
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol *gp.TileSize;
			int worldY = worldRow *gp.TileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.TileSize > gp.player.worldX - gp.player.screenX &&
			   worldX - gp.TileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.TileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.TileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.TileSize, gp.TileSize, null);
			}
			
			worldCol++;

			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;

			}
		}


		
	}

}