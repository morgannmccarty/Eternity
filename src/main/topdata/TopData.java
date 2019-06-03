package main.topdata;

import com.jogamp.opengl.awt.GLCanvas;

import board.Board;
import main.Main;
import tile.Tile;
import tile.topdata.TileHealthDisplay;
import tile.topdata.TileManaDisplay;
import tile.type.Type;
import util.Position;

public class TopData {
	
	public static Tile goldCount = new Tile(new Position(2, 30), 0.9F, 0.9F, .15F, new Type("gold_counter"));
	public static DisplayText goldNum = new Counter(new Position(3.5F, 30.3F), Main.player.getPlayerGold());
	
	public static Tile manaCount = new TileManaDisplay(new Position(2, 32));
	public static DisplayText manaNum = new Counter(new Position(3.5F, 32.3F), Main.player.getMana(), Main.player.getMaxMana(), 0.0F, 0.0F, 1.0F);

	public static Tile healthCount = new TileHealthDisplay(new Position(14, 32));
	public static DisplayText healthNum = new Counter(new Position(15.5F, 32.3F), Main.player.getHealth(), Main.player.getMaxHealth(), 1.0F, 0.0F, 0.0F);
	
	public static Tile experienceCount = new Tile(new Position(14, 30), 0.6F, 1.0F, 0.6F, new Type("experience_counter"));
	public static DisplayText experienceNum = new Counter(new Position(15.5F, 30.3F), Main.player.getExperience(), Main.player.getExperienceReq(), 0.6F, 1.0F, 0.6F);
	
	public static Board topBoard = new Board(new Tile[][] {{goldCount}, {manaCount}, {healthCount}, {experienceCount}});
	
	public static void showTopData(GLCanvas canvas)
	{
		canvas.addGLEventListener(topBoard);
		canvas.addGLEventListener(goldNum);
		canvas.addGLEventListener(manaNum);
		canvas.addGLEventListener(healthNum);
		canvas.addGLEventListener(experienceNum);
	}
	
	public static void removeData(GLCanvas canvas)
	{
		canvas.removeGLEventListener(topBoard);
		canvas.removeGLEventListener(goldNum);
		canvas.removeGLEventListener(manaNum);
		canvas.removeGLEventListener(healthNum);
		canvas.removeGLEventListener(experienceNum);
	}
	
	public static void changeGoldCounter()
	{
		((Counter) goldNum).changeCount(Main.player.getPlayerGold());
	}
	
	public static void changeExperienceCounter()
	{
		((Counter) experienceNum).changeCount(Main.player.getExperience(), Main.player.getExperienceReq());
	}
	
	public static void changeManaDisplay()
	{
		((Counter) manaNum).changeCount(Main.player.getMana(), Main.player.getMaxMana());
	}
	public static void changeHealthDisplay()
	{
		((Counter) healthNum).changeCount(Main.player.getHealth(), Main.player.getMaxHealth());
	}
}
