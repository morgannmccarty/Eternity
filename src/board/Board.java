package board;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import entity.EntityRegistry;
import tile.Tile;

public class Board implements GLEventListener{
	
	private Tile[][] tiles;

	
	public Board(Tile[][] tiles)
	{
		this.tiles = tiles;
	}
	
	@Override
	public void display(GLAutoDrawable drawable) { 
		for(int i = 0; i<tiles.length; ++i)
		{
			for(int j = 0; j<tiles[i].length; ++j)
			{
				tiles[i][j].drawTile(drawable);
			}
		}
		EntityRegistry.drawEntities(drawable);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) { }

	@Override
	public void init(GLAutoDrawable drawable) { }

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) { }
	
}
