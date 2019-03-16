import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	Timer timer;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont;
	Font smallFont;
	Font gameFont;

	public static BufferedImage homeScreen;
	Car car = new Car(800, 380, 50, 50);
	ObjectManager om = new ObjectManager(car);

	int score = 0; 
	
	public GamePanel() {
		// TODO Auto-generated constructor stub
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Helvetica", Font.PLAIN, 48);
		smallFont = new Font("Helvetica", Font.PLAIN, 34);
		gameFont = new Font("Times New Roman", Font.ROMAN_BASELINE, 30);

		try {

			homeScreen = ImageIO.read(this.getClass().getResourceAsStream("homeScreen.gif"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
	}

	public void updateMenuState() {

	}

	public void updateGameState() {
		om.update();

		if (om.getCar().isAlive == false) {
			currentState = END_STATE;
		}
	
	}

	public void updateEndState() {

	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, DriftingGame.width, DriftingGame.height);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("WATERED DOWN DRIFT GAME", 280, 180);
		g.setFont(smallFont);
		g.drawString("Press ENTER to start", 470, 300);
	
		g.drawImage(GamePanel.homeScreen, 360, 460, 610, 343, null);
	}

	public void drawGameState(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, DriftingGame.width, DriftingGame.height);
		om.draw(g);
		g.setFont(gameFont);
		g.drawString("Score: " + score, 100, 100);
		//car.isAlive=true; make sure to check on this later
		
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, DriftingGame.width, DriftingGame.height);

		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 112, 150);
		g.setFont(smallFont);
		g.drawString("Your drift score was a total of " + "!X!" + " points", 50, 320);
		g.drawString("Press ENTER to restart", 60, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();

		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
	}

	public void startGame() {
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.fillRect(10, 10, 100, 100);
		if (currentState == MENU_STATE) {

			drawMenuState(g);

		} else if (currentState == GAME_STATE) {

			drawGameState(g);

		} else if (currentState == END_STATE) {

			drawEndState(g);
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			car.pressed = false;
			if (currentState == END_STATE) {
				
				car = new Car(250, 500, 50, 50);
				om = new ObjectManager(car);
			}

			currentState++;
			

			if (currentState > END_STATE) {
				currentState = MENU_STATE;
			}
			//fix the restart later
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			car.direction = car.LEFT;
			car.pressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			car.direction = car.RIGHT;
			car.pressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			car.direction = car.UP;
			car.pressed = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		car.pressed = false;
	}

}
