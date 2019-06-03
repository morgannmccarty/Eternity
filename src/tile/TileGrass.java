package tile;

import tile.type.Type;
import util.Position;

public class TileGrass extends Tile{

	public TileGrass(Position pos, float red) {
		super(pos, red, 0.7F, 0.2F, new Type("grass"));
	}

}
