package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import entity.EntityPlayer;
import entity.EntityProjectileRegistry;
import entity.EntityRegistry;
import entity.IEntity;
import main.action.PlayerProjectileAction;
import main.topdata.TopData;

public class Main {
	
	private static int count = 0;
	
	public static boolean gameOver = false;
	public static int lastDirection = 0;
	public static Timer timer;
	public static EntityPlayer player = new EntityPlayer(Map.spawn_point, 0, 10, 100);

	public static void main(String[] args)
	{
		Library.LOGGER.log(Level.INFO, "Starting game...");
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		final GLCapabilities capabilites = new GLCapabilities(profile);
		final GLCanvas canvas = new GLCanvas(capabilites);
		
		
		Game game = new Game();
		
		canvas.addGLEventListener(game);
		canvas.addGLEventListener(player);
		Map.generate(canvas, 1000);
		Map.firstTime = false;
		TopData.showTopData(canvas);
		
		JFrame frame = new JFrame("Eternity");
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		keyBindings(player, frame, canvas);
		secondUpdate(canvas);
		
		frame.getRootPane().requestFocusInWindow();
		
		Library.LOGGER.log(Level.INFO, "Game started!");
	}
	
	public static void secondUpdate(GLCanvas canvas)
	{
		timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if(gameOver)
				{
					EntityRegistry.clearEntities();
					Map.gameOver(canvas);
					timer.cancel();
				}
				count++;
				EntityRegistry.entityAttacks();
				if(count%10 == 0) {
					
					EntityRegistry.moveEntities();
					EntityRegistry.tickEntities();
					count = 0;
				}
				EntityProjectileRegistry.launchProjectiles();
				canvas.display();
			}
			
		};
		timer.scheduleAtFixedRate(task, 0, 100);
	}
	
	@SuppressWarnings("serial")
	public static void keyBindings(EntityPlayer player, JFrame frame, GLCanvas canvas)
	{
		InputMap im = frame.getRootPane().getInputMap();
		ActionMap am = frame.getRootPane().getActionMap();
		
		im.put(KeyStroke.getKeyStroke("UP"),"moveup");
		im.put(KeyStroke.getKeyStroke("DOWN"),"movedown");
		im.put(KeyStroke.getKeyStroke("RIGHT"), "moveright");
		im.put(KeyStroke.getKeyStroke("LEFT"), "moveleft");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0), "breakWall");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), "reset");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "fireballup");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "fireballdown");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "fireballleft");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "fireballright");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "slash");
		
		am.put("moveup", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				player.moveUp();
				if(!(Map.adjacentTile(0, 1, player.getPosition())).isSeen())
				{
					player.changeManaBy(1);
					Map.adjacentTile(0, 1, player.getPosition()).seen();
				}
				
				canvas.display();
				//System.out.println("up");
				lastDirection = 0;
			}
			
		});
		am.put("movedown", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				player.moveDown();
				if(!(Map.adjacentTile(0, -1, player.getPosition())).isSeen())
				{
					player.changeManaBy(1);
					Map.adjacentTile(0, -1, player.getPosition()).seen();
				}
					
				canvas.display();
				//System.out.println("down");
				lastDirection = 1;
			}
			
		});
		am.put("moveright", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				player.moveRight();
				if(!(Map.adjacentTile(1, 0, player.getPosition())).isSeen())
				{
					player.changeManaBy(1);
					Map.adjacentTile(1, 0, player.getPosition()).seen();
				}
				
				canvas.display();
				//System.out.println("right");
				lastDirection = 2;
			}
		});
		am.put("moveleft", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				player.moveLeft();
				if(!(Map.adjacentTile(-1, 0, player.getPosition())).isSeen())
				{
					player.changeManaBy(1);
					Map.adjacentTile(-1, 0, player.getPosition()).seen();
				}
				
				canvas.display();
				//System.out.println("left");
				lastDirection = 3;
			}
		});
		am.put("breakWall", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("x");
				if(player.getMana()>=3)
				{
					if(lastDirection == 0) Map.breakWall(0, 1, player.getPosition());
					if(lastDirection == 1) Map.breakWall(0, -1, player.getPosition());
					if(lastDirection == 2) Map.breakWall(1, 0, player.getPosition());
					if(lastDirection == 3) Map.breakWall(-1, 0, player.getPosition());
					player.changeManaBy(-3);
					canvas.display();
				}
			}
			
		});
		am.put("reset", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("n");
				timer.cancel();
				if(player.getPosition().equals(Map.end_point))
				{
					EntityRegistry.clearEntities();
					Map.generate(canvas, 1000);
					player.getPosition().setX(Map.spawn_point.getX());
					player.getPosition().setY(Map.spawn_point.getY());
					canvas.display();
				}
				secondUpdate(canvas);
			}
			
		});
		am.put("slash", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.player.slash(lastDirection);
			}
			
		});
		
		am.put("fireballup", new PlayerProjectileAction(canvas, player, 0));
		am.put("fireballdown", new PlayerProjectileAction(canvas, player, 1));
		am.put("fireballleft", new PlayerProjectileAction(canvas, player, 3));
		am.put("fireballright", new PlayerProjectileAction(canvas, player, 2));
	}

}
