package tile.topdata;

import tile.Tile;
import tile.type.Type;
import util.Position;

public class TileHealthDisplay extends Tile{
	public TileHealthDisplay(Position pos) {
		super(pos, 1.0F, 0.0F, 0.0F, new Type("health_display"));

	}
}
