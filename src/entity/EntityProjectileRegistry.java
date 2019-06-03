package entity;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GLAutoDrawable;

import util.Position;

public class EntityProjectileRegistry {
	private static List<IEntityProjectile> projectiles = new ArrayList<IEntityProjectile>();
	public static void createProjectile(IEntity entityAt, int direction)
	{
		Position pos = new Position(entityAt.getPosition());
		projectiles.add(new ProjectileFireball(pos, direction, entityAt, 30));
	}
	public static void launchProjectiles()
	{
		for(int i = 0; i<projectiles.size(); ++i)
		{
			projectiles.get(i).launch();
		}
		
	}
	public static void drawProjectiles(GLAutoDrawable drawable)
	{
		for(int i = 0; i<projectiles.size(); ++i)
		{
			projectiles.get(i).display(drawable);
		}
	}
	public static void cleanProjectiles(IEntityProjectile projectile)
	{
		projectiles.remove(projectile);
	}
	public static void addNewProjectile(IEntityProjectile projectile)
	{
		projectiles.add(projectile);
	}
}
