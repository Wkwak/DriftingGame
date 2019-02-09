import java.awt.Dimension;

import javax.swing.JFrame;

public class DriftingGame {

	JFrame frame;
	final static int width = 1280;
	final static int height = 720;
	GamePanel gp;

	public DriftingGame() {
		// TODO Auto-generated constructor stub
		frame = new JFrame();
		gp = new GamePanel();

	}

	public static void main(String[] args) {
		DriftingGame dg = new DriftingGame();
		dg.setup();
	}

	public void setup() {
		frame.add(gp);
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		gp.startGame();
		frame.addKeyListener(gp);
	}

}
