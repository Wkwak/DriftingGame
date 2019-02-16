import java.awt.Color;
import java.awt.Graphics;

public class Car extends GameObject {

	double speedX;
	double speedY;
	int direction = -1;
	final int LEFT = 0;
	final int RIGHT = 1;
	final int STILL = -2;
	final int UP = 2;
	final int DOWN = 3;
	double accel;

	public Car(int x, int y, int width, int height) {
		super(x, y, width, height);
		speedX = 1;
		speedY = 1;
		accel = 0.1;
	}

	public void update() {
		super.update();
		System.out.println(speedX);

		if (direction == LEFT) {
			speedY = 0;
			if (speedX >= -4) {
				speedX -= accel;
			}
			x += speedX;
		} else if (direction == RIGHT) {
			speedY = 0;
			if (speedX <= 4) {
				speedX += accel;
			}
			x += speedX;
		} else if (direction == UP) {
			speedX = 0;
			if (speedY >= -4) {
				speedY -= accel;
			}
			y += speedY;
		} else if (direction == DOWN) {
			speedX = 0;
			if (speedY <= 4) {
				speedY += accel;
			}
			y += speedY;
		} else if (direction == STILL) {
			accel -= 1;
			speedX -= accel;
			speedY -= accel;
		}

	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRoundRect(x, y, 45, 70, 15, 10); // car itself
		g.setColor(Color.BLACK); // 4 ovals = wheels
		g.fillOval(x - 5, y + 10, 10, 20);
		g.fillOval(x + 40, y + 10, 10, 20);
		g.fillOval(x - 5, y + 45, 10, 20);
		g.fillOval(x + 40, y + 45, 10, 20);
		g.setColor(Color.WHITE); // other stuff
		g.fillRect(x + 10, y + 50, 25, 2);
		g.fillRect(x + 10, y + 45, 25, 2);
		g.fillRect(x + 10, y + 40, 25, 2);
		g.fillRect(x + 35, y + 10, 5, 20);
		g.fillRect(x + 5, y + 10, 5, 20);
	}

}
