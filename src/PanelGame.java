import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;

public class PanelGame extends JPanel {
	Player player;
	Timer timer;
	Rocket[][] rocket = new Rocket[300][3];
	Monter[][] monter = new Monter[300][3];
	UFO[][] ufo = new UFO[300][3];
	ArrayList<Bullet> bullets = new ArrayList<>();
	int score = 0;
	Sound sound = new Sound();

	PanelGame() {
		double x = 800;
		for (int i = 0; i < 300; i++) {
			double y = 40;
			for (int j = 0; j < 3; j++) {
				Rocket rocket1 = new Rocket((int) x, (int) y);
				rocket[i][j] = rocket1;
				y += 150 + (Math.random() * 300);
			}
			x += 200 + (Math.random() * 300);
		}
		x = -60;
		for (int i = 0; i < 300; i++) {
			double y = 40;
			for (int j = 0; j < 3; j++) {
				Monter monter1 = new Monter((int) x, (int) y);
				monter[i][j] = monter1;
				y += 150 + (Math.random() * 300);
			}
			x += 200 + (Math.random() * 300);
		}
		x = -60;
		for (int i = 0; i < 300; i++) {
			double y = 40;
			for (int j = 0; j < 3; j++) {
				UFO ufo1 = new UFO((int) x, (int) y);
				ufo[i][j] = ufo1;
				y += 150 + (Math.random() * 300);
			}
			x += 200 + (Math.random() * 300);
		}
		player = new Player();
		registerEvents();
		gameLoop();
	}

	public static void startGame() {
		SwingUtilities.invokeLater(() -> {
			Menu menu = new Menu();
			menu.setVisible(true);
			PanelGame panelGame = new PanelGame();
			panelGame.setFocusable(true);
			panelGame.requestFocusInWindow();
			panelGame.setVisible(true);
		});
	}

	private void drawBullets(Graphics g) {
		Iterator<Bullet> iterator = bullets.iterator();
		while (iterator.hasNext()) {
			Bullet bullet = iterator.next();
			bullet.move();
			bullet.draw(g);
			if (bullet.getY() < 0 || bullet.getX() > 800) {
				iterator.remove();
			}
		}
	}

	private Boolean isCollided() {
		int playerWidth = player.getWidth();
		int playerHeight = player.getHeight();
		int xDistance, yDistance;

		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 3; j++) {
				xDistance = Math.abs(player.getX() - rocket[i][j].getX());
				yDistance = Math.abs(player.getY() - rocket[i][j].getY());
				if (xDistance <= playerWidth - 30 && yDistance <= playerHeight - 20) {
					return true;
				}

				xDistance = Math.abs(player.getX() - monter[i][j].getX());
				yDistance = Math.abs(player.getY() - monter[i][j].getY());
				if (xDistance <= playerWidth - 30 && yDistance <= playerHeight - 20) {
					return true;
				}

				xDistance = Math.abs(player.getX() - ufo[i][j].getX());
				yDistance = Math.abs(player.getY() - ufo[i][j].getY());
				if (xDistance <= playerWidth - 30 && yDistance <= playerHeight - 20) {
					return true;
				}

			}
		}
		return false;
	}

	private void GameOverMessage(Graphics g) {
		File file = new File("C:\\Users\\hp pc\\OneDrive\\Desktop\\Plane Game\\src\\image\\GameOver.png");
		try {
			BufferedImage image = ImageIO.read(file);
			int x = (getWidth() - image.getWidth()) / 2;
			int y = (getHeight() - image.getHeight()) / 2;
			g.drawImage(image, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		timer.stop();

	}

	private void GameOverCheck(Graphics g) {
		if (player.getHealth() <= 0) {
			GameOverMessage(g);
		}

	}

	private void registerEvents() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.setSpeed(-20);
					player.move();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.setSpeed(20);
					player.move();
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER && isCollided()) {
					restartGame();
				}
			}
		});
	}

	private void restartGame() {
		player = new Player();
		bullets.clear();
		score = 0;
		if (!timer.isRunning()) {
			timer.start();
		}
	}

	private void gameLoop() {
		timer = new Timer(0, (e) -> {
			for (int i = 0; i < 300; i++) {
				for (int j = 0; j < 3; j++) {
					requestFocus(true);
					rocket[i][j].move();
					monter[i][j].move();
					ufo[i][j].move();
				}
			}
			checkIfRocketInside();
			checkIfMonterInside();
			checkIUFOInside();
			addBullet();
			repaint();
		});
		timer.start();
	}

	private int bulletDelay = 250;
	private long lastBulletTime = 0;

	private void addBullet() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastBulletTime > bulletDelay) {
			int bulletX = player.getX() + player.getWidth() - 63;
			int bulletY = player.getY() + 1;
			Bullet bullet = new Bullet(bulletX, bulletY);
			bullets.add(bullet);
			lastBulletTime = currentTime;
		}
	}

	private void drawRocket(Graphics g) {
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 3; j++) {
				if (score < 8) {
					rocket[i][j].draw(g);
				}
			}
		}
	}

	private void drawMonter(Graphics g) {
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 3; j++) {
				if (score > 6) {
					monter[i][j].draw(g);
				}
			}
		}
	}

	private void drawUFO(Graphics g) {
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 3; j++) {
				if (score > 15) {
					ufo[i][j].draw(g);
				}
			}
		}
	}

	private boolean checkIfUFODead(UFO ufos) {
		int xDistance;
		int yDistance;

		Iterator<Bullet> iterator = bullets.iterator();
		while (iterator.hasNext()) {
			Bullet bullet = iterator.next();
			xDistance = Math.abs(bullet.getX() - ufos.getX());
			yDistance = Math.abs(bullet.getY() - ufos.getY());
			if (xDistance <= ufos.getWidth() - 30 && yDistance <= ufos.getHeight() - 20) {
				ufos.decreaseHealth(15);
				if (ufos.getHealth() <= 0) {
					iterator.remove();
					sound.soundDestroy();
					return true;
				}

				iterator.remove();
				sound.soundHit();

				return false;
			}
		}
		return false;
	}

	private boolean checkIfMonterDead(Monter monters) {
		int xDistance;
		int yDistance;

		Iterator<Bullet> iterator = bullets.iterator();
		while (iterator.hasNext()) {
			Bullet bullet = iterator.next();
			xDistance = Math.abs(bullet.getX() - monters.getX());
			yDistance = Math.abs(bullet.getY() - monters.getY());
			if (xDistance <= monters.getWidth() - 30 && yDistance <= monters.getHeight() - 20) {
				monters.decreaseHealth(10);
				if (monters.getHealth() <= 0) {
					iterator.remove();
					sound.soundDestroy();
					return true;
				}
				iterator.remove();
				sound.soundHit();

				return false;
			}
		}
		return false;
	}

	private boolean checkIfRocketDead(Rocket rockets) {
		int xDistance;
		int yDistance;

		Iterator<Bullet> iterator = bullets.iterator();
		while (iterator.hasNext()) {
			Bullet bullet = iterator.next();
			xDistance = Math.abs(bullet.getX() - rockets.getX());
			yDistance = Math.abs(bullet.getY() - rockets.getY());
			if (xDistance <= rockets.getWidth() - 30 && yDistance <= rockets.getHeight() - 20) {
				bullet.setX(1300);
				bullet.setY(0);
				bullet.setSpeed(0);
				iterator.remove();
				sound.soundHit();

				return true;
			}

		}
		return false;

	}

	private void checkIUFOInside() {
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 3; j++) {
				if (ufo[i][j].getX() <= 800) {
					if (checkIfUFODead(ufo[i][j])) {
						score++;
						ufo[i][j].setX(1400);
						ufo[i][j].setY(0);
						ufo[i][j].setSpeed(0);

					}
				}
			}
		}
	}

	private void checkIfMonterInside() {
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 3; j++) {
				if (monter[i][j].getX() <= 800) {
					if (checkIfMonterDead(monter[i][j])) {
						score++;
						monter[i][j].setX(1400);
						monter[i][j].setY(0);
						monter[i][j].setSpeed(0);

					}
				}
			}
		}
	}

	private void checkIfRocketInside() {
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 3; j++) {
				if (rocket[i][j].getX() <= 800) {
					if (checkIfRocketDead(rocket[i][j])) {
						score++;
						rocket[i][j].setX(1400);
						rocket[i][j].setY(0);
						rocket[i][j].setSpeed(0);

					}
				}
			}
		}
	}

	private void reducePlayerHealth() {
		if (isCollided()) {
			player.decreaseHealth(2);
			if (player.getHealth() <= 0) {
				player.setHealth(0);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackground(g);
		player.draw(g);
		drawRocket(g);
		drawMonter(g);
		drawUFO(g);
		drawBullets(g);
		GameOverCheck(g);
		drawScore(g);
		reducePlayerHealth();
	}

	private void drawScore(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 10, 30);
	}

	private void drawBackground(Graphics g) {
		File file = new File("C:\\Users\\hp pc\\OneDrive\\Desktop\\Plane Game\\src\\image\\BG.png");
		try {
			BufferedImage image = ImageIO.read(file);
			g.drawImage(image, 0, 0, 800, 600, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
