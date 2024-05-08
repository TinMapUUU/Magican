package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
			//MÀN HÌNH (SETTINGS)
	final int originTileSize = 16; 		// 16px x 16px (png)
	final int scale = 3;
	
	public final int TileSize = originTileSize * scale;	//48px x 48px
	
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = TileSize * maxScreenCol;	 //768px
	public final int screenHeight = TileSize * maxScreenRow; 	// 576px
	
		// WORLD SETTINGS
	public final int maxWorldCol = 40;
	public final int maxWorldRow = 40;
	public final int worldWidth = TileSize * maxWorldCol;
	public final int worldHeight = TileSize * maxWorldRow;
	
		//FPS
	int FPS = 60;
	
	TileManager titleM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();	
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyH);
	
	

	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
				//VÒNG LẶP GAME (CỐT LÕI)  
								// (PHƯƠNG PHÁP SLEEP)
	@Override
//	public void run() {
//		
//		while(gameThread != null) {
//			
//			double drawInterval = 1000000000/FPS;	//0.01667 seconds
//			double nextDrawTime = System.nanoTime() + drawInterval;
//			
////			1. UPDATE : CẬP NHẬT VỊ TRÍ CHAR
//			update();
////			2. DRAW : CẬP NHẬT ĐỐI TƯỢNG
//			repaint();
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;	// CHUYỂN SANG ĐV MILI GIẤY
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);		//SLEEP CHẤP NHẬN MILI GIÂY
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
						
								// PHƯƠNG PHÁP TÍCH LŨY
	public void run() {
		
		double drawInterval = 1000000000/FPS;	//0.01667 seconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	}
	
	public void update() {
		
		player.update();
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;	//CHUYỂN SANG ĐỒ HỌA 2D
		
		titleM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
	}
	

	
}