package tile;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import tile.type.ITypeable;
import tile.type.Type;
import util.IPositionable;
import util.Position;

public class Tile implements IPositionable, ITypeable
{
	private Position pos;
	private float sideLength;
	private float red, green, blue;
	private Type type;
	private boolean seen = false;

	public Tile(Position pos, float red, float green, float blue, Type type)
	{
		this.red = red; this.green = green; this.blue = blue;
		this.pos = pos;
		this.sideLength = 1;
		this.type = type;
	}
	
	public Tile(Position pos, Type type)
	{
		this.red = 0.0F; this.green = 0.0F; this.blue = 0.0F;
		this.pos = pos;
		this.sideLength = 1;
		this.type = type;
	}
	public Tile(Position pos)
	{
		this.red = 0.0F; this.green = 0.0F; this.blue = 0.0F;
		this.pos = pos;
		this.sideLength = 1;
		this.type = new Type("");
	}
	
	public void drawTile(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		gl.glColor3f(red, green, blue);
		gl.glEnable(GL2.GL_POLYGON_OFFSET_LINE);
		gl.glBegin(GL2.GL_QUADS);
		//bottom left
		gl.glVertex3f(pos.getX(), pos.getY(), 0F);
		//bottom right
		gl.glVertex3f(pos.getX() + sideLength, pos.getY(), 0F);
		//top right
		gl.glVertex3f(pos.getX() + sideLength, pos.getY() + sideLength, 0F);
		//top left
		gl.glVertex3f(pos.getX(), pos.getY() + sideLength, 0F);
		gl.glEnd();
	}

	public boolean isSafe()
	{
		return false;
	}
	
	@Override
	public Position getPosition() {
		return pos;
	}
	
	public boolean isSeen()
	{
		return this.seen;
	}
	
	public void seen()
	{
		this.seen = true;
	}

	@Override
	public Type returnType() {
		// TODO Auto-generated method stub
		return type;
	}
	
}