package th1s.is.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import th1s.is.game.entity.mob.Player;
import th1s.is.game.graphics.Screen;
import th1s.is.game.input.ClientInterface;
import th1s.is.game.input.Keyboard;
import th1s.is.game.input.Mouse;
import th1s.is.game.level.Level;
import th1s.is.game.level.SpawnLevel;
import th1s.is.game.level.TileCoordinate;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	//public data members
	private static int width = 300; // resolution of the game
	private static int height = width / 16 * 9;
	private static int scale = 3;
	public String title = "Styrofoamium";
	
	//private data members
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;
	private Screen screen;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int pixels[] = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private ClientInterface client;
	private String playerName = "David";
	private int lastx = 0;
	private int lasty = 0;
	
	public Game() throws SocketException, UnknownHostException{
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size); // set the dimensions
		
		screen = new Screen(width, height);
		frame = new JFrame();
		
		key = new Keyboard();
		level = new SpawnLevel("/textures/TestWorld.png");
		TileCoordinate playerSpawn = new TileCoordinate(32, 32);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		player.init(level);
		client = new ClientInterface(level);
		client.connect(playerName);
		client.sendMessage();
		addKeyListener(key);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
	}
	
	public static int getWindowWidth(){
		return width * scale;
	}
	
	public static int getWindowHeight(){
		return height * scale;
	}
	
	public synchronized void start(){ 
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop(){
		running = false;
		try{
			thread.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		requestFocus();
		
		while(running){
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle(title + "     fps: " + frames); // title and fps
			}
		}
		stop();
	}
	
	public void tick(){
		key.update();
		player.update();
		level.update();
		
	}

	public void render(){
		 BufferStrategy bs = getBufferStrategy();
		 if(bs == null){
			 createBufferStrategy(3); // triple buffering
			 return;
		 }
		 
		 screen.clear();
		 // player is in the middle of the screen
		 int xScroll = player.x - screen.width / 2; 
		 int yScroll = player.y - screen.height / 2;
		 if(player.x != lastx || player.y !=lasty)
			 client.move(player);
		 lastx = player.x;
		 lasty = player.y;
		 
		 level.render(xScroll, yScroll,  screen);
		 player.render(screen);
		 for(int i = 0; i < pixels.length; i++)
			 pixels[i] = screen.pixels[i];
		 
		 Graphics g = bs.getDrawGraphics();
		 g.setColor(Color.BLACK);
		 g.drawImage(image,  0,  0,  getWidth(), getHeight(), null);
		 //g.fillRect(0, scale * (height - height/ 10), width * scale, height * scale);
		 g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		 g.setColor(Color.WHITE);
		 g.drawString("Comic Sans MS", 0, scale * height);
		 g.dispose();
		 bs.show();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
}
