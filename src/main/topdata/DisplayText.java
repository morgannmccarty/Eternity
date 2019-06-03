package main.topdata;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.gl2.GLUT;

public class DisplayText implements GLEventListener		{

	private GLUT glut = new GLUT();
	private float x, y;
	private float red, green, blue;
	private String textToDisplay;
	
	public DisplayText(float x, float y, float red, float green, float blue, String textToDisplay)
	{
		this.x = x;
		this.y = y;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.textToDisplay = textToDisplay;
	}
	
	public void changeText(String s)
	{
		this.textToDisplay = s;
	}
	
	public void move(float x, float y)
	{
		this.x+=x;
		this.y+=y;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		glut.glutBitmapLength(glut.BITMAP_HELVETICA_18, textToDisplay);
		gl.glColor3f(red, green, blue);
		gl.glRasterPos2f(x, y);
		glut.glutBitmapString(glut.BITMAP_HELVETICA_18, textToDisplay);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {}

	@Override
	public void init(GLAutoDrawable arg0) {}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
	}

}
