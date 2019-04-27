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
	double targetTheta;
	double actualTheta;
	boolean pressed; 
	double deltaTheta;
	int driftScore;

	public Car(int x, int y, int width, int height) {
		super(x, y, width, height);
		speedX = 5;
		speedY = 5;
		accel = 0.1;
	}

	public void update() {
		super.update();
		
		//add in a multiplier to the score at the top of the screen 
		//which changes according to the size of deltaTheta;
		
		deltaTheta = Math.abs(targetTheta - actualTheta);
		
		carCenterX=x+width/2;
		carCenterY=y+height/2;
		
		if (direction == LEFT && pressed) {
			targetTheta -= 0.1;
			
		} else if (direction == RIGHT && pressed) {
			targetTheta += 0.1;	
		
		}
		
		if(actualTheta < targetTheta) {
			actualTheta+=3.8 * (deltaTheta/90);
		}else if(actualTheta > targetTheta) {
			actualTheta-=3.8 * (deltaTheta/90);
		}
		
		speedX = Math.sin(actualTheta) * 5;
		speedY = -Math.cos(actualTheta) * 5;
		
		y+=speedY;
		x+=speedX;
		
		
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
	
	public int getDriftScore() {
		if(getDistanceFromCenter()<150) { //instead of distance from center try and implement a timer in the future 
										//subtract points for each second passed before crossing the line 
			driftScore+=deltaTheta;
		}
		return driftScore;
	}
	
	public void setDriftScore(int restart) {
		driftScore = 0;
	}

	public void draw(Graphics g) {
		Graphics2D car2D = (Graphics2D) g.create();
		
			car2D.rotate(targetTheta, carCenterX, carCenterY);
			
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
			
			car2D.rotate(targetTheta, carCenterX, carCenterY);
			car2D.dispose();
	}

}
