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
	boolean isHittingLine = false;
	int differentScore = 0;
	
	int r, g, b;
	int r1 = 255;
	int r2 = 165;
	int r3 = 0;
	
	Patterns patterns = new Patterns(0,0, 10, 10);
	Car car;
	ClosingCircle circle = new ClosingCircle(circleX, circleY, circleWidth, circleHeight);
	FinishLine line = new FinishLine(690, 360, 300, 2);
	
	public ObjectManager(Car car) {
		this.car = car; 
	}

	public void update() {
		car.update();
		checkCollision();
	}
	
	public void draw(Graphics g) {
		circle.draw(g);
		patterns.draw(g);
		line.draw(g);
		car.draw(g);
		
	}
	
	public int getScore() {
		return score;
	}
	
	public Car getCar() {
		return car;
	}
	
	public void checkCollision() {
		
		System.out.println(car.getDriftScore());
	    if(car.getDistanceFromCenter()<=circle.width/2 && car.getDistanceFromCenter()>=50){
             car.isAlive = true;
	    }
	    else {
	    	car.isAlive = false;
	    }
	    if(car.collisionBox.intersects(line.collisionBox)) {
	    	isHittingLine = true;
	 
	    	score++;
	    	car.speedX+=100;
	    	car.speedY+=100; //when doing this the car momentarily switches speed 
	    	//System.out.println(car.speedX);
	    r1 = (int) (Math.random()*255);
	    	r2 = (int) (Math.random()*255);
	    	r3 = (int) (Math.random()*255);
	    	
	    } 
	    if(isHittingLine == false) {
	    	circle.setColor(r1, r2, r3);	
	    	score+=car.getDriftScore();
	    }
	    else {
	    	isHittingLine=false;	   
	    }
	}
}