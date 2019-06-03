package entity;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import main.Library;
import main.Map;
import tile.type.Type;
import util.Position;

public abstract class AbstractEntity implements IEntity{
	
	private Position pos;
	private int sideLength = 1;
	private float red, green, blue;
	
	public AbstractEntity(Position pos, float red, float green, float blue) {
		this.pos = pos;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	@Override
	public int getDirectionToEntity(IEntity entity)
	{
		return Math.abs(getPosition().getX() - entity.getPosition().getX()) < Math.abs(getPosition().getY() - entity.getPosition().getY()) && (getPosition().getY() - entity.getPosition().getY()) < 0 ? 0 :
			   Math.abs(getPosition().getX() - entity.getPosition().getX()) < Math.abs(getPosition().getY() - entity.getPosition().getY()) && (getPosition().getY() - entity.getPosition().getY()) < 0 ? 2 :
			   Math.abs(getPosition().getX() - entity.getPosition().getX()) > Math.abs(getPosition().getY() - entity.getPosition().getY()) && (getPosition().getX() - entity.getPosition().getX()) > 0 ? 1 :
			   Math.abs(getPosition().getX() - entity.getPosition().getX()) > Math.abs(getPosition().getY() - entity.getPosition().getY()) && (getPosition().getX() - entity.getPosition().getX()) < 0 ? 3 :
			   4;
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
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
	
	@Override
	public int getSpeed()
	{
		return 1;
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void init(GLAutoDrawable drawable) {
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	}
	
	
	
	@Override
	public void moveUp() {
		if(pos.getY() + 1 >= Library.MAX_POSITION.getY()) return;
		if(!(Map.checkAdjacentTileType(new Type("wall"), 0, 1, pos)) && !(Map.checkAdjacentTileType(new Type("border"), 0, 1, pos)))
			move(0, 1);
	}

	@Override
	public void moveDown() {
		if(pos.getY() - 1 <= Library.MIN_POSITION.getY()) return;
		if(!(Map.checkAdjacentTileType(new Type("wall"), 0, -1, pos)) && !(Map.checkAdjacentTileType(new Type("border"), 0, -1, pos)))
			move(0, -1);
	}

	@Override
	public void moveLeft() {
		if(pos.getX() - 1 <= Library.MIN_POSITION.getX()) return;
		if(!(Map.checkAdjacentTileType(new Type("wall"), -1, 0, pos)) && !(Map.checkAdjacentTileType(new Type("border"), -1, 0, pos)))
			move(-1, 0);
	}

	@Override
	public void moveRight() {
		if(pos.getX() + 1 <= Library.MIN_POSITION.getX()) return;
		if(!(Map.checkAdjacentTileType(new Type("wall"), 1, 0, pos)) && !(Map.checkAdjacentTileType(new Type("border"), 1, 0, pos)))
			move(1, 0);
	}
	@Override
	public void move(float x, float y) {
		pos.setX(pos.getX()+x);
		pos.setY(pos.getY()+y);
	}
	@Override
	public Position getPosition() {
		return pos;
	}
	
	public void onTick()
	{
		
	}
}
