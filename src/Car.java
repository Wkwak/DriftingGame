import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

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
	Point currentPos = new Point();
	
	double distanceFromPrevious; 
	
	ArrayList<Point> carPosition = new ArrayList<Point>();
	ArrayList<Double> distances = new ArrayList<Double>();
	
	int dCounter = distances.size()-2;

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
		
		currentPos.x = this.carCenterX;
		currentPos.y  = this.carCenterY; 
		carPosition.add(currentPos); //adds the position of each frame 
		
		
		
		dCounter = distances.size() - 1;
		
		if(dCounter>=1) { //how would dCounter be >= 1 if the size of distances does not increase
			distanceFromPrevious = carPosition.get(dCounter).distance(carPosition.get(0));
			distances.add(distanceFromPrevious); // adding the distances from the previous point in the arrayList 
			
		}
		
		while(distances.size()>30) {
			distances.remove(0);
		}
		
		while(carPosition.size()>30) {
			carPosition.remove(0);
		}
		
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
		for(int i = 0; i < distances.size() - 1; i++) {
			if(distances.get(i)<distances.get(i+1)) { //create a list of x, y positions and check the next frames set of points 
				   // create another array that holds the distances 
				   // if the distance at one point ends up being shorter than the previous then don't add points
				driftScore+=deltaTheta;
			}
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
