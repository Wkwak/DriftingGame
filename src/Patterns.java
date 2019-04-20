import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Patterns extends GameObject{
	
	Color clr; 
	private int modulo; 
	int xItems = 0;
	int yItems = 0;
	Graphics [][] rectangles;
	
	public Patterns(int x, int y, int width, int height) {
		super(x, y, width, height);
		collisionBox.setBounds(x, y, width, height);
		
		// TODO Auto-generated constructor stub
		clr = Color.WHITE;
	}
	public void update() {
		
	}
	public void draw(Graphics g, int mod) {
		this.modulo = mod;
		g.setColor(clr);
		for(int row = 0; row < 20; row++) {
			for(int col = 0; col < 20; col++) {
				if(row%modulo==0 && col%modulo==0) { 
					g.fillRect(row*DriftingGame.width/20, col*DriftingGame.height/20, width, height);
					xItems++;
					yItems++;
				}
			}
		}
		rectangles = new Graphics[xItems][yItems];
	}
	
	public boolean intersects(Car c) {
		/*boolean intersection = false;
		for(int i = 0; i <rectangles.length; i++) {
			if (c.collisionBox) { 
				intersection = true;
			}
		}*/
		return false;//intersection;
	}
}
