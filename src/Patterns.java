import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

public class Patterns extends GameObject{
	
	Color clr; 
	private int modulo; 
	int items = 0;
	ArrayList<ArrayList<Integer>> coords = new ArrayList<ArrayList<Integer>>();
	
	public Patterns(int x, int y, int width, int height) {
		super(x, y, width, height);
		collisionBox.setBounds(x, y, width, height);
		
		// TODO Auto-generated constructor stub
		clr = Color.WHITE;
	}
	public void update() {
		
	}
	
	public void draw(Graphics g, int mod) {
		this.modulo = mod; ///change later
		g.setColor(clr);
		for(int row = 0; row < 20; row++) {
			for(int col = 0; col < 20; col++) {
				if(row%modulo==0 && col%modulo==0) { 
					int xPos = row*DriftingGame.width/20;
					int yPos = col*DriftingGame.height/20;
					g.fillRect(xPos, yPos, width, height);
					
					coords.add(new ArrayList<Integer>());
					coords.get(items).add(xPos);
					coords.get(items).add(yPos);
					items++; 
				}
			}
		}
	}
	
	public boolean intersects(Car c) {
		boolean intersection = false;
		for(int i = 0; i <coords.size(); i++) {
			if (c.collisionBox.contains(coords.get(i).get(i), coords.get(i).get(i+1))) { 
				intersection = true;
			}
		}
		return intersection;
	}
}
