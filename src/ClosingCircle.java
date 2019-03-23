import java.awt.Color;
import java.awt.Graphics;

public class ClosingCircle extends GameObject{
	
	Color color;
	
	ClosingCircle(int x, int y, int width, int height){
		super(x, y, width, height);
		collisionBox.setBounds(x, y, width, height);
		color = Color.ORANGE;
	}
	
	public void update() {
		super.update();
		
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, width, height);
		g.setColor(Color.WHITE);
		g.fillOval(DriftingGame.width/2 - 50, DriftingGame.height/2 - 50, 100, 100);
	}
	
	public void setColor(int r, int g, int b) {
		color = new Color (r, g, b);
	}
	
}
