package main.action;

import java.awt.event.ActionEvent;

import com.jogamp.opengl.awt.GLCanvas;

import entity.EntityPlayer;

@SuppressWarnings("serial")
public class PlayerProjectileAction extends ProjectileAction{
	
	EntityPlayer player;

	public PlayerProjectileAction(GLCanvas canvas, EntityPlayer entityActing, int direction) {
		super(canvas, entityActing, direction);
		this.player = entityActing;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(player.getMana() >= 5)
		{
			super.actionPerformed(e);
			player.changeManaBy(-5);
		}
	}

}
