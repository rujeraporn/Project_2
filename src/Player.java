import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Player {

	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	private BufferedImage image;
	ArrayList<Bullet> bullets = new ArrayList<>();
	private int health;
	private int maxHealth;

	public Player() {
		x = 20;
		y = 280;
		width = 70;
		height = 62;
		speed = 20;
		health = 100;
		maxHealth = 100;
		File file = new File("C:\\Users\\hp pc\\OneDrive\\Desktop\\Plane Game\\src\\image\\PLANE.png");
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public void fire() {
		int frontX = x + image.getWidth() / 2;
		int frontY = y;
		Bullet bullet = new Bullet(frontX, frontY);
		bullets.add(bullet);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
		g.setColor(Color.yellow);
		g.fillRect(x, y - 10, width, 5);
		g.setColor(Color.red);
		int healthWidth = (int) ((double) health / maxHealth * width);
		g.fillRect(x, y - 10, healthWidth, 5);
	}

	public void move() {
		int newY = y + speed;
		if (newY >= 0 && newY + height <= 600) {
			y = newY;
		} else {
			if (newY < 0) {
				y = 0;
			} else {
				y = 500;
			}
		}
	}

	public void decreaseHealth(int amount) {
		this.health -= amount;
	}

	public void increaseHealth(int amount) {
		health += amount;
		if (health > maxHealth) {
			health = maxHealth;
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
}
