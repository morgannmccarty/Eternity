package tile;

import tile.type.Type;
import util.Position;

public class TileLeaves extends Tile{

	private boolean walkable;
	
	public TileLeaves(Position pos, float red, boolean walkable) {
		super(pos, red, 0.9F, 0.3F, new Type("leaves"));
		this.walkable = walkable;
	}
	
	public boolean isWalkable()
	{
		return walkable;
	}

}
