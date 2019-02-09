import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObjectManager {
	
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	int score = 0;
	
	Car car;

	public ObjectManager(Car car) {
		this.car = car; 
	}

	public void update() {
		
		car.update();
	}

	public void draw(Graphics g) {
		
		car.draw(g);
		

	}
	
	public int getScore() {
		return score;
	}

}
