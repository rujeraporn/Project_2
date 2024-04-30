import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class UFO {

    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private BufferedImage image;
    private int health;

    UFO(int x, int y) {
        this.x = x;
        this.y = y;
        width = 90;
        height = 70;
        speed = -9;
        health = 150;
        File file = new File("C:\\Users\\hp pc\\OneDrive\\Desktop\\Plane Game\\src\\image\\alian.png");
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

    public void decreaseHealth(int amount) {
        health -= amount;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
        g.setColor(Color.red);
        g.fillRect(x, y - 10, width, 5);
        g.setColor(Color.blue);
        g.fillRect(x, y - 10, (int) ((double) health / 150 * width), 5);
    }

    public void move() {
        x += speed;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }
}
