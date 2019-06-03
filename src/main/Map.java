package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import com.jogamp.opengl.awt.GLCanvas;

import board.Board;
import entity.EntityRegistry;
import tile.Tile;
import tile.TileBorder;
import tile.TileFloor;
import tile.TileGrass;
import tile.TileLeaves;
import tile.TileTrunk;
import tile.TileWall;
import tile.type.Type;
import util.Position;

public class Map {
	
	public static Tile[][] tiles = new Tile[40][30];
	
	private static boolean lastTown = false;
	public static boolean isTown = false;
	
	private static Random rand = new Random();
	private static int sx = rand.nextInt(20)+10;
	private static int sy = rand.nextInt(15)+10;
	
	private static int ex = rand.nextInt(20)+5;
	private static int ey = rand.nextInt(10)+5;
	
	public static Position spawn_point = new Position(sx, sy);
	public static Position end_point = new Position(ex, ey);
	public static boolean firstTime = true;
	
	public static int currentTreeCount;
	
	public static final int MAX_TREE_COUNT = 100;
	
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
			
			if(rand.nextInt(1) == 0 && !lastTown)
			{
				currentTreeCount = rand.nextInt(MAX_TREE_COUNT);
				generateTown(canvas, currentTreeCount);
				//generateMarketplace(canvas);
				EntityRegistry.clearEntities();
				lastTown = true;
				return;
			}
			else
			{
				lastTown = false;
			}
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
				if(i == 0 || j == 0 || i == tiles.length-1 || j == tiles[0].length-1 || j == tiles[0].length-1)
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
		walls.clear();
		Board board = new Board(tiles);
		
		canvas.addGLEventListener(board);
		EntityRegistry.createEntities();
		Library.LOGGER.log(Level.INFO, "Generated map!");
	}
	
//	public static void generateMarketplace(GLCanvas canvas)
//	{
//		List<Position> pos = new ArrayList<Position>();
//		List<Position> basPos = new ArrayList<Position>();
//		for(int i = 0; i<tiles.length; ++i)
//		{
//			for(int j = 0; j<tiles[i].length; ++j)
//			{
//				if(i == 0 || j == 0 || i == tiles.length-1 || j == tiles[0].length-1 || j == tiles[0].length-1)
//				{
//					tiles[i][j] = new TileBorder(new Position(i,j));
//				}
//				else
//				{
//					Random rand = new Random();
//					int x = rand.nextInt((int)(Library.MAX_POSITION.getX() - 30)) + 10;
//					for(int l = 0; l<x; ++l)
//					{
//						
//					}
//				}
//			}
//		}
//	}
	
	public static void gameOver(GLCanvas canvas)
	{
		for(int i = 0; i < tiles.length; ++i)
		{
			for(int j = 0; j<tiles[i].length; ++j)
			{
				tiles[i][j] = new Tile(new Position(i, j));
			}
		}
	}
	
	public static void generateTown(GLCanvas canvas, int treeCount)
	{
		List<Position> trees = new ArrayList<Position>();
		List<Position> badPos = new ArrayList<Position>();
		ey = rand.nextInt(3) + 14;
		end_point = new Position(ex, ey);
		sy = rand.nextInt(3) + 14;
		spawn_point = new Position(sx, sy);
		Main.player.getPosition().setX(spawn_point.getX());
		Main.player.getPosition().setY(spawn_point.getY());
		for(int i = 0; i<treeCount; ++i)
		{
			trees.add(randomTreeLocation(trees));
		}
		for(int i = 0; i<tiles.length; ++i)
		{
			for(int j = 0; j<tiles[i].length; ++j)
			{
				if(i == 0 || j == 0 || i == tiles.length-1 || j == tiles[0].length-1 || j == tiles[0].length-1)
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
				    else if(trees.contains(new Position(i, j)))
				    {
				    	if(spawnTree(i, j, trees, badPos)) tiles[i][j] = new TileTrunk(new Position(i, j));;
				    }
				    else
				    {
				    	if(!badPos.contains(new Position(i, j))) tiles[i][j] = new TileGrass(new Position(i, j), getRedBasedOnTreeCount(trees.size(), 0.7F));
				    }
				}
				
			}
		}
		trees.clear();
		badPos.clear();
	}
	
	private static boolean spawnTree(int i, int j, List<Position> trees, List<Position> forbiddenPos)
	{
		Random rand = new Random();
		if(!trees.contains(new Position(i, j+1)))
		{			
			List<Position> usedPos = new ArrayList<Position>();
			int q = rand.nextInt(2)+1;
			for(int l = 1; l<q; ++l)
			{
				if(trees.contains(new Position(i, j+q))) q--;
			}
			for(int l = 1; l<q; ++l)
			{
				tiles[i][j+l] = new TileTrunk(new Position(i, j+l));
				usedPos.add(new Position(i, j+l));
			}
			if((j+q) < tiles[i].length && (j+q) > 0)
			{
				tiles[i][j+q] = new TileLeaves(new Position(i, j+q), getRedBasedOnTreeCount(trees.size(), 0.7F), false);
				usedPos.add(new Position(i, j+q));
				int bb = rand.nextInt(3)+1;
				int hh = rand.nextInt(3)+1;
				boolean first = true;
				for(int breadth = 0; breadth < bb; ++breadth)
				{
					for(int height = 0; height < hh; ++height)
					{
						if((i+breadth) < tiles.length - 1 && (i+breadth) > 0 && (j+q+height) < tiles[0].length - 1 && (j+q+height) > 1)
						{
							tiles[i+breadth][j+q+height] = new TileLeaves(new Position(i+breadth, j+q+height), getRedBasedOnTreeCount(trees.size() , 0.7F), first ? false : true);
							usedPos.add(new Position(i + breadth, j+q+height));
						}
						if((i-breadth) < tiles.length - 1 && (i-breadth) > 0 && (j+q+height) < tiles[0].length - 1 && (j+q+height) > 1)
						{
							tiles[i-breadth][j+q+height] = new TileLeaves(new Position(i-breadth, j+q+height), getRedBasedOnTreeCount(trees.size() , 0.7F), first ? false : true);
							usedPos.add(new Position(i - breadth, j+q+height));
						}
					}
				}
			}
			
			for(int l = 0; l<usedPos.size(); ++l)
			{
				forbiddenPos.add(usedPos.get(l));
			}
			return true;
		}
		return false;
	}
	
	public static float getRedBasedOnTreeCount(int count, float val)
	{
		return 1.0F - (((float)count/(float)MAX_TREE_COUNT) * val);
	}
	
	private static Position randomTreeLocation(List<Position> rootPositions)
	{
		Random rand = new Random();
		int x, y;
		if(rootPositions.size() == 0)
		{
			if(rand.nextInt(2) == 0)
			{
				x = rand.nextInt((int) Library.MAX_POSITION.getX());
				y = rand.nextInt((int) Library.MAX_POSITION.getY() - 20) + 19;
			}
			else
			{
				x = rand.nextInt((int) Library.MAX_POSITION.getX());
				y = rand.nextInt((int) Library.MAX_POSITION.getY() - 20);
			}
			return new Position(x, y);
		}
		else
		{
			while(true)
			{
				if(rootPositions.get(rootPositions.size()-1).getY() > 19)
				{
					x = rand.nextInt((int) Library.MAX_POSITION.getX());
					y = rand.nextInt((int) Library.MAX_POSITION.getY() - 20);
					if(adjacentTileCount(new Position(x, y), rootPositions) == 0)
					{
						if(cornerTileCount(new Position(x, y), rootPositions) == 0)
						{
							return new Position(x, y);
						}
					}
				}
				else
				{
					x = rand.nextInt((int) Library.MAX_POSITION.getX());
					y = rand.nextInt((int) Library.MAX_POSITION.getY() - 20) + 19;
					if(adjacentTileCount(new Position(x, y), rootPositions) == 0)
					{
						if(cornerTileCount(new Position(x, y), rootPositions) == 0)
						{
							return new Position(x, y);
						}
					}
				}
			}
		}
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
			while(true)
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
	
	public static Tile tileAtRound(Position pos)
	{
		return tiles[Math.round(pos.getX())][Math.round(pos.getY())];
	}
	
	public static Tile tileAtCast(Position pos)
	{
		return tiles[(int)pos.getX()][(int)pos.getY()];
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