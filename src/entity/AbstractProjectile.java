package entity;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import main.Map;
import tile.type.Type;
import util.Position;

public abstract class AbstractProjectile implements IEntityProjectile{

	private Position pos;
	private float sideLength = 0.25F;
	private int direction;
	private IEntity entity;
	private int moveCount = 0;
	private int maxMoves;
	
	public AbstractProjectile(Position pos, int direction, IEntity entity, int maxMoves)
	{
		this.pos = pos;
		this.direction = direction;
		this.entity = entity;
		this.maxMoves = maxMoves;
	}

	public void end()
	{
		EntityProjectileRegistry.cleanProjectiles(this);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glColor3f(1.0F, 0.0F, 0.0F);
		gl.glEnable(GL2.GL_POLYGON_OFFSET_LINE);
		gl.glBegin(GL2.GL_QUADS);
		//bottom left
		gl.glVertex3f(pos.getX()+.375F, pos.getY()+.375F, 0F);
		//bottom right
		gl.glVertex3f(pos.getX()+.375F + sideLength, pos.getY()+.375F, 0F);
		//top right
		gl.glVertex3f(pos.getX()+.375F + sideLength, pos.getY()+.375F + sideLength, 0F);
		//top left
		gl.glVertex3f(pos.getX()+.375F, pos.getY()+.375F + sideLength, 0F);
		gl.glEnd();
		
	}
	
	@Override
	public void move(float x, float y) {
		if(moveCount >= maxMoves)
		{
			end();
			return;
		}
		if((Map.tileAtRound(pos).returnType().equals(new Type("wall")) || Map.tileAtRound(pos).returnType().equals(new Type("border"))) && (direction == 0 || direction == 2))
		{
			end();
			return;
		}
		else if((Map.tileAtCast(pos).returnType().equals(new Type("wall")) || Map.tileAtCast(pos).returnType().equals(new Type("border"))) && (direction == 1 || direction == 3))
		{
			end();
			return;
		}
		moveCount++;
		pos.setX(pos.getX()+x);
		pos.setY(pos.getY()+y);
	}

	@Override
	public int getSpeed() {
		return 0;
	}

	@Override
	public void moveUp() {}

	@Override
	public void moveDown() {}

	@Override
	public void moveLeft() {}

	@Override
	public void moveRight() {}

	@Override
	public Position getPosition() {
		return pos;
	}
	
	@Override
	public IEntity getControllingEntity()
	{
		return entity;
	}
	
	@Override
	public int getDirection()
	{
		return direction;
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {}

	@Override
	public void init(GLAutoDrawable drawable) {}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}


}
