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
	boolean changeBackground = false;
	int differentScore = 0;
	
	int r, g, b;
	int r1 = 255;
	int r2 = 165;
	int r3 = 0;
	int modulo = (int) (Math.random()*4) + 5;
	
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
		patterns.draw(g, modulo);
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
	    	if(!changeBackground) {
	    		r1 = (int) (Math.random()*255);
		    	r2 = (int) (Math.random()*255);
		    	r3 = (int) (Math.random()*255);
		    	modulo = (int) Math.max((Math.random()*6) + 4, 1);
		    	
		    	circle.setColor(r1, r2, r3);	
		    	changeBackground = true;
		    	score+=car.getDriftScore();
		    	car.setDriftScore(0);
		    	patterns.coords.clear();
	    	}
	    	
	    } 
	    if(patterns.intersects(car)) {
	    	car.isAlive = false;
	    }
	    	
	    	
	    if(isHittingLine == false) {
	    	
	    	changeBackground = false;
	    	
	    }
	    else {
	    	isHittingLine=false;	   
	    }
	}
}