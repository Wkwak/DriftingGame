import java.awt.Color;
import java.awt.Graphics;

public class ClosingCircle extends GameObject{
	
	ClosingCircle(int x, int y, int width, int height){
		super(x, y, width, height);
		collisionBox.setBounds(x, y, width, height);
	}
	
	public void update() {
		super.update();
		
	}
	
	public void draw(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect(0, 0, DriftingGame.width, DriftingGame.height);
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, width, height);
		g.setColor(Color.WHITE);
		g.fillOval(DriftingGame.width/2 - 50, DriftingGame.height/2 - 50, 100, 100);
		g.setColor(Color.BLACK);
		g.fillRect(690, 360, 300, 10);
	}
	
	
	
}
