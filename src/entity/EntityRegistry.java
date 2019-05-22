package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jogamp.opengl.GLAutoDrawable;

import main.Library;
import main.Map;
import tile.type.Type;
import util.Position;

public class EntityRegistry {
	
	public static List<IEntity> entities = new ArrayList<IEntity>();
	
	public static IEntity player = new EntityPlayer(Map.spawn_point, 1, 10, 100);
	
	public static void createEntities()
	{
		createMonsters(30);
	}
	
	public static void createMonsters(int monsterCount)
	{
		List<Position> badPos = new ArrayList<Position>();
		for(int i = 0; i<Map.tiles.length; ++i)
		{
			for(int j = 0; j<Map.tiles[i].length; ++j)
			{
				if(Map.tiles[i][j].returnType().equals(new Type("wall"))) badPos.add(Map.tiles[i][j].getPosition());
				if(Map.tiles[i][j].returnType().equals(new Type("border"))) badPos.add(Map.tiles[i][j].getPosition());
			}
		}
		for(int i = 0; i<entities.size(); ++i)
		{
			badPos.add(entities.get(i).getPosition());
		}
		for(int i = 0; i<monsterCount; ++i)
		{
			Random rand = new Random();
			int x = rand.nextInt((int)Library.MAX_POSITION.getX() - 2) + 2;
			int y = rand.nextInt((int)Library.MAX_POSITION.getY() - 2) + 2;
			for(Position pos: badPos)
			{
				while((new Position(x, y)).equals(pos))
				{
					x = rand.nextInt((int)Library.MAX_POSITION.getX() - 2) + 2;
					y = rand.nextInt((int)Library.MAX_POSITION.getY() - 2) + 2;
				}
			}
			int type = rand.nextInt(1);
			if(type == 0) entities.add(new EntityRogue(new Position(x, y)));
		}
		
	}
	
	public static void clearEntities()
	{
		for(int i = 0; i<entities.size(); ++i)
		{
			if(!(entities.get(i) instanceof EntityPlayer))
			{
				entities.remove(i);
			}
		}
	}
	
	public static void moveEntities()
	{
		for(int i = 0; i<entities.size(); ++i)
		{
			if(!(entities.get(i) instanceof EntityPlayer))
			{
				for(int j = 0; j<entities.get(i).getSpeed(); ++j)
				{
					Random rand = new Random();
					int dir = rand.nextInt(4);
					if(dir == 0) entities.get(i).moveUp();
					if(dir == 1) entities.get(i).moveDown();
					if(dir == 2) entities.get(i).moveRight();
					if(dir == 3) entities.get(i).moveLeft();
				}
			}
		}
	}
	
	public static void drawEntities(GLAutoDrawable drawable)
	{
		entities.add(player);
		for(IEntity entity: entities)
		{
			entity.display(drawable);
		}
	}
}
