import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Car extends GameObject {

	double speedX;
	double speedY;
	int direction = -1;
	final int LEFT = 0;
	final int RIGHT = 1;
	final int STILL = -2;
	final int UP = 2;
	final int DOWN = 3;
	final int SHIFT = 4;
	double accel;
	int carCenterX;
	int carCenterY;
	double distanceSquared;
	double distance;
	double theta;

	public Car(int x, int y, int width, int height) {
		super(x, y, width, height);
		speedX = 1;
		speedY = 1;
		accel = 0.1;
	}

	public void update() {
		super.update();
		
		//using vectors change the direction of the object like when you press 
		//left the car moves more to the left than up
		
		carCenterX=x+width/2;
		carCenterY=y+height/2;
		
		if (speedY >= -4) {
			speedY -= accel;
		}
		y += speedY;
		
		if (direction == LEFT) {
			speedY = 0;
			theta = -1;
			if (speedX >= -4) {
				speedX -= accel;
			}
			x += speedX;
		} else if (direction == RIGHT) {
			speedY = 0;
			theta = 1;
			if (speedX <= 4) {
				speedX += accel;
			}
			x += speedX;
		}
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getcarCenterX() {
		return this.carCenterX;
	}
	public int getcarCenterY() {
		return this.carCenterY;
	}
	
	public void setX(int x) {
		this.carCenterX = x;
	}
	public void setY(int y) {
		this.carCenterY = y;
	}
	
	public double getDistanceFromCenter() {
		distanceSquared = Math.pow((double)carCenterX-DriftingGame.width/2, 2) + (Math.pow((double)carCenterY-DriftingGame.height/2, 2));
		distance = Math.sqrt(distanceSquared);
		return distance;
	}

	public void draw(Graphics g) {
		Graphics2D car2D = (Graphics2D) g.create();
		
			car2D.rotate(theta, carCenterX, carCenterY);
			
			car2D.setColor(Color.BLACK);
			car2D.fillRoundRect(x, y, 45, 70, 15, 10); // car itself
			car2D.setColor(Color.BLACK); // 4 ovals = wheels
			car2D.fillOval(x - 5, y + 10, 10, 20);
			car2D.fillOval(x + 40, y + 10, 10, 20);
			car2D.fillOval(x - 5, y + 45, 10, 20);
			car2D.fillOval(x + 40, y + 45, 10, 20);
			car2D.setColor(Color.WHITE); // other stuff
			car2D.fillRect(x + 10, y + 50, 25, 2);
			car2D.fillRect(x + 10, y + 45, 25, 2);
			car2D.fillRect(x + 10, y + 40, 25, 2);
			car2D.fillRect(x + 35, y + 10, 5, 20);
			car2D.fillRect(x + 5, y + 10, 5, 20);
			
			car2D.rotate(theta, carCenterX, carCenterY);
			car2D.dispose();
	}

}
