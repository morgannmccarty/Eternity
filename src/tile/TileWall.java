package tile;

import tile.type.ITypeable;
import tile.type.Type;
import util.Position;

public class TileWall extends Tile implements ITypeable{

	public TileWall(Position pos) {
		super(pos, 0.0F, 0.0F, 0.0F, new Type("wall"));
		
	}

}
