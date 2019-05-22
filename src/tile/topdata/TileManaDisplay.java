package tile.topdata;

import tile.Tile;
import tile.type.Type;
import util.Position;

public class TileManaDisplay extends Tile {

	public TileManaDisplay(Position pos) {
		super(pos, 0.0F, 0.0F, 1.0F, new Type("mana_display"));

	}

}
