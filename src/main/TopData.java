package main;

import com.jogamp.opengl.awt.GLCanvas;

import board.Board;
import tile.Tile;
import tile.topdata.TileHealthDisplay;
import tile.topdata.TileManaDisplay;
import tile.type.Type;
import util.Position;

public class TopData {
	
	public static Tile goldCount = new Tile(new Position(2, 30), 0.9F, 0.9F, .15F, new Type("gold_counter"));
	public static Tile[] mana = new Tile[10];
	public static Tile[] health = new Tile[10];
	
	public static void showTopData(GLCanvas canvas)
	{
		
		for(int i = 0; i<((double)Main.player.getMana()/(double)Main.player.getMaxMana())*10; ++i)
		{
			mana[i] = new TileManaDisplay(new Position(2 + i, 32));
		}
		
		for(int i = 0; i<((double)Main.player.getHealth()/(double)Main.player.getMaxHealth())*10; ++i)
		{
			health[i] = new TileHealthDisplay(new Position(14 + i, 32));
		}
		
		Board topBoard = new Board(new Tile[][] {{goldCount}, mana, health});
		canvas.addGLEventListener(topBoard);
	}
	
	public static void changeManaDisplay()
	{
		for(int i = 0; i<((double)Main.player.getMana()/(double)Main.player.getMaxMana())*10; ++i)
		{
			mana[i] = new TileManaDisplay(new Position(2 + i, 32));
		}
		for(int i = 9; i >= ((double)Main.player.getMana()/(double)Main.player.getMaxMana())*10; --i)
		{
			mana[i] = new Tile(new Position(2 + i, 32));
		}
	}
	public static void changeHealthDisplay()
	{
		for(int i = 0; i<((double)Main.player.getHealth()/(double)Main.player.getMaxHealth())*10; ++i)
		{
			mana[i] = new TileHealthDisplay(new Position(2 + i, 32));
		}
		for(int i = 9; i >= ((double)Main.player.getHealth()/(double)Main.player.getMaxHealth())*10; --i)
		{
			mana[i] = new Tile(new Position(2 + i, 32));
		}
	}
}
