package main;

import javax.swing.JFrame;

import com.jogamp.opengl.DebugGL2;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

public class Game extends JFrame implements GLEventListener{

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 1050;
	public static final int TILE_WIDTH = 40;
	public static final int TILE_HEIGHT = 35;
	
	private GLU glu;
	private FPSAnimator animator;

	public Game()
	{
		this.setSize(WIDTH, HEIGHT);
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		
		GL2 gl = drawable.getGL().getGL2();
		
		//setCamera(gl, glu, 100);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) { 
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		glu = GLU.createGLU(gl);
		drawable.setGL(new DebugGL2(gl));
		//gl.glEnable(GL2.GL_DEPTH_TEST);
		//gl.glDepthFunc(GL2.GL_LEQUAL);
		//gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
		gl.glClearColor(0F, 0F, 0F, 1F);
		glu.gluOrtho2D(0, TILE_WIDTH, 0, TILE_HEIGHT);
		//gl.glOrtho(0, 100, 0, 100, 0, 10);
		
		animator = new FPSAnimator(60);
		animator.start();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glViewport(0, 0, WIDTH, HEIGHT);
	}

	
}
