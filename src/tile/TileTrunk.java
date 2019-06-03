package tile;

import tile.type.Type;
import util.Position;

public class TileTrunk extends Tile{

	public TileTrunk(Position pos) {
		super(pos, 0.5F, 0.2F, 0.1F, new Type("trunk"));
	}

}
