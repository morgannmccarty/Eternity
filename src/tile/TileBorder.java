package tile;

import tile.type.ITypeable;
import tile.type.Type;
import util.Position;

public class TileBorder extends Tile implements ITypeable{

	public TileBorder(Position pos) {
		super(pos, 0.0F, 0.0F, 0.0F, new Type("border"));
	}

}
