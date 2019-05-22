package tile;

import tile.type.Type;
import util.Position;

public class TileFloor extends Tile{

	private boolean isSafe;
	
	public TileFloor(Position pos, boolean isSafe) {
		super(pos, 0.8F, 0.8F, 0.8F, new Type("floor"));
		this.isSafe = isSafe;
	}
	
	public boolean isSafe()
	{
		return isSafe;
	}

}
