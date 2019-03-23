import java.awt.Color;
import java.awt.Graphics;

public class FinishLine extends GameObject{

	public FinishLine(int x, int y, int width, int height) {
		super(x, y, width, height);
		collisionBox.setBounds(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public void update() {
		super.update();
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}

}
