import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Rocket {

	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	private BufferedImage image;

	Rocket(int x, int y) {
		this.x = x;
		this.y = y;
		height = 75;
		width = 60;
		speed = -5;
		File file = new File("C:\\Users\\hp pc\\OneDrive\\Desktop\\Plane Game\\src\\image\\rocket.png");
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	}

	public void move() {
		x += speed;
	}
}
