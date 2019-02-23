import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

	public static BufferedImage homeScreen;
	Car car = new Car(250, 500, 50, 50);
	ObjectManager om = new ObjectManager(car);
		
	int circleWidth = 700;
	int circleHeight = 700;

	final int circleX = DriftingGame.width/2 - circleWidth/2;
	final int circleY = DriftingGame.height/2 - (circleWidth/2+10);
	
	ClosingCircle circle = new ClosingCircle(circleX, circleY, circleWidth, circleHeight);

	
	public GamePanel() {
		// TODO Auto-generated constructor stub
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Helvetica", Font.PLAIN, 48);
		smallFont = new Font("Helvetica", Font.PLAIN, 34);

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

		if (car.isAlive == false) {
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
		circle.draw(g);
		om.draw(g);
		
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
		System.out.println("This");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END_STATE) {
				car = new Car(250, 500, 50, 50);
				om = new ObjectManager(car);
			}

			currentState++;
		}

		if (currentState > END_STATE) {
			currentState = MENU_STATE;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			car.direction = car.LEFT;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			car.direction = car.RIGHT;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			car.direction = car.UP;
		}
		/*if(e.getkeyCode() == KeyEvent.VK_DOWN) {
			car.direction = car.DOWN;
		}
		if(e.getkeycode() == KeyEvent.VK_STOP) {
			car.direction = car.STILL;
		}*/

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
