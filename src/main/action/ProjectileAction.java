package main.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.jogamp.opengl.awt.GLCanvas;

import entity.EntityProjectileRegistry;
import entity.IEntity;

@SuppressWarnings("serial")
public class ProjectileAction extends AbstractAction{

	private GLCanvas canvas;
	private IEntity entityActing;
	private int direction;
	
	public ProjectileAction(GLCanvas canvas, IEntity entityActing, int direction)
	{
		this.canvas = canvas;
		this.entityActing = entityActing;
		this.direction = direction;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//System.out.println("test " + direction);
		EntityProjectileRegistry.createProjectile(entityActing, direction);
		canvas.display();
	}
	
	

}
