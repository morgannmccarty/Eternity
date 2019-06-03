package entity;

import com.jogamp.opengl.GLEventListener;

public interface IEntityProjectile extends IEntity, GLEventListener{
	public void launch();
	public int getDirection();
	public boolean hitEntity();
	public IEntity getControllingEntity();
}
