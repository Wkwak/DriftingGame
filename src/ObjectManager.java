import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObjectManager {
	
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	int score = 0;
	
	int circleWidth = 700;
	int circleHeight = 700;

	final int circleX = DriftingGame.width/2 - circleWidth/2;
	final int circleY = DriftingGame.height/2 - (circleWidth/2+10);
	
	Car car;
	ClosingCircle circle = new ClosingCircle(circleX, circleY, circleWidth, circleHeight);

	public ObjectManager(Car car) {
		this.car = car; 
	}

	public void update() {
		car.update();
		checkCollision();
	}

	public void draw(Graphics g) {
		
		circle.draw(g);
		car.draw(g);
		
	}
	
	public int getScore() {
		return score;
	}
	
	public Car getCar() {
		return car;
	}
	
	public void checkCollision() {
	    if(car.getDistanceFromCenter()<=circle.width/2 && car.getDistanceFromCenter()>=50){
             car.isAlive = true;
	    }
	    else {
	    	car.isAlive = false;
	    }
	}
}
