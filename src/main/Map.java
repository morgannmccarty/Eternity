package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import com.jogamp.opengl.awt.GLCanvas;

import board.Board;
import tile.Tile;
import tile.TileBorder;
import tile.TileFloor;
import tile.TileWall;
import tile.type.Type;
import util.Position;

public class Map {
	
	public static Tile[][] tiles = new Tile[40][30];
	
	private static Random rand = new Random();
	private static int sx = rand.nextInt(20)+10;
	private static int sy = rand.nextInt(15)+10;
	
	private static int ex = rand.nextInt(20)+5;
	private static int ey = rand.nextInt(10)+5;
	
	public static Position spawn_point = new Position(sx, sy);
	public static Position end_point = new Position(ex, ey);
	public static boolean firstTime = true;
	
	public static void generate(GLCanvas canvas, int wallCount)
	{
		Library.LOGGER.log(Level.INFO, "Generating map...");
		
		if(!firstTime)
		{
			sx = rand.nextInt(20)+10;
			sy = rand.nextInt(15)+10;
			ex = rand.nextInt(20)+5;
			ey = rand.nextInt(10)+5;
			while((Math.abs(sx-ex) < 10))
			{
				ex = rand.nextInt(20)+5;
			}
			while((Math.abs(sy-ey) < 10))
			{
				ey = rand.nextInt(20)+5;
			}
			spawn_point = new Position(sx, sy);
			end_point = new Position(ex, ey);
		}
		
		List<Position> walls = new ArrayList<Position>();
		
		for(int i = 0; i<wallCount; ++i)
		{
			
			walls.add(randomTileWall(walls));
		}
		
		for(int i = 0; i<40; ++i)
		{
			for(int j = 0; j<30; ++j)
			{
				if(i == 0 || j == 0 || i == tiles.length-1 || j == tiles[0].length-2 || j == tiles[0].length-1)
				{
					tiles[i][j] = new TileBorder(new Position(i,j));
				}
				else 
				{
				    if(i == sx && j == sy)
					{
						tiles[i][j] = new Tile(new Position(i, j), .6F, .6F, 0.6F, new Type("spawn_point"));
					}
				    else if(i == ex && j == ey)
				    {
				    	tiles[i][j] = new Tile(new Position(i, j), .5F, .5F, .5F, new Type("end_point"));
				    }
					else if((i == (sx + 1) && j == (sy + 1)) || (i == (sx - 1) && j == (sy + 1)) || (i == (sx + 1) && j == (sy - 1)) || (i == (sx - 1) && j == (sy - 1)) || (i == (sx) && j == (sy + 1)) || (i == (sx + 1) && j == (sy)) || (i == (sx - 1) && j == (sy)) || (i == (sx) && j == (sy - 1)))
					{
						tiles[i][j] = new TileFloor(new Position(i, j), true);
					}
					else if(walls.contains(new Position(i, j)))
					{
						tiles[i][j] = new TileWall(new Position(i, j));
					}
					else tiles[i][j] = new TileFloor(new Position(i, j), false);
				}
			}
		}
		
		Board board = new Board(tiles);
		
		canvas.addGLEventListener(board);
		Library.LOGGER.log(Level.INFO, "Generated map!");
	}
	
	private static Position randomTileWall(List<Position> walls)
	{
		Random rand = new Random();
		int x, y;
		if(walls.size() == 0)
		{
			x = rand.nextInt((int) Library.MAX_POSITION.getX());
			y = rand.nextInt((int) Library.MAX_POSITION.getY());
			return new Position(x,y);
		}
		else
		{
			boolean complete = false;
			while(!complete)
			{
				x = rand.nextInt((int) Library.MAX_POSITION.getX());
				y = rand.nextInt((int) Library.MAX_POSITION.getY());
				if(adjacentTileCount(new Position(x, y), walls) == 4)
				{
					return new Position(x,y);
				}
				else if(cornerTileCount(new Position(x, y), walls) == 4 && adjacentTileCount(new Position(x, y), walls) > 2)
				{
					return new Position(x, y);
				}
				else if(adjacentTileCount(new Position(x, y), walls) < 3 && cornerTileCount(new Position(x,y), walls) < 3)
				{
					return new Position(x, y);
				}
			}
			return null;
		}
	}
	
	private static int adjacentTileCount(Position tile, List<Position> tiles)
	{
		int count = 0;
		for(int i = 0; i<tiles.size(); ++i)
		{
			if(tiles.get(i).equals(new Position(tile.getX()+1, tile.getY())) || tiles.get(i).equals(new Position(tile.getX(), tile.getY()+1)) || tiles.get(i).equals(new Position(tile.getX()-1, tile.getY())) || tiles.get(i).equals(new Position(tile.getX(), tile.getY()-1))) count++;
		}
		return count;
	}
	private static int cornerTileCount(Position tile, List<Position> tiles)
	{
		int count = 0;
		for(int i = 0; i<tiles.size(); ++i)
		{
			if(tiles.get(i).equals(new Position(tile.getX()+1, tile.getY()+1)) || tiles.get(i).equals(new Position(tile.getX()-1, tile.getY()+1)) || tiles.get(i).equals(new Position(tile.getX()-1, tile.getY()-1)) || tiles.get(i).equals(new Position(tile.getX()+1, tile.getY()-1))) count++;
		}
		return count;
	}
	
	public static Tile adjacentTile(int dX, int dY, Position pos)
	{
		return tiles[(int)pos.getX()+dX][(int)pos.getY()+dY];
	}
	
	public static boolean checkAdjacentTileType(Type type, int dX, int dY, Position pos)
	{
		if(tiles[(int)pos.getX()+dX][(int)pos.getY()+dY].returnType().equals(type)) return true;
		return false;
	}
	
	public static void breakWall(int dX, int dY, Position pos)
	{
		if(checkAdjacentTileType(new Type("wall"), dX, dY, pos)) tiles[(int)pos.getX()+dX][(int)pos.getY()+dY] = new TileFloor(new Position((int)pos.getX()+dX, (int)pos.getY()+dY), false);
	}
	
}	
