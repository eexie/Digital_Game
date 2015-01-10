
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Enemy extends Unit {
	private static int size;
	protected int health;
	private int type;
	private int cooldown = 50;
	private int direction;

	public Enemy(int x, int y, int type) {
		super(x, y, size);
		this.type = type;
		size = 20 * type;
		health = (int) (90 + Math.pow(2, type));
		if (type == 1||type==2) {
			direction = (int) (Math.random() * 4) + 1;
		}
		System.out.println(direction);
	}

	public void attack() {
		switch (type) {
		case 1: // straight
			fireStraight(1);
			break;
		case 2: // straight, 2x damage
			fireStraight(2);
			break;
		case 3: // four streams of bullets
			fireFour(1);
			break;
		case 4: // eight streams of bullets
			fireEight(1);
			break;
		case 5: // eight streams of bullets,2x damage
			fireEight(2);
			break;
		}
	}

	public void update() {
		cooldown--;
		if (cooldown == 25) {
			attack();
			cooldown = 50;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		((Graphics2D) g).setStroke(new BasicStroke(2));
		drawHCircle(x, y, size, type, g);
		drawVCircle(x, y, size, type, g);
		g.setColor(Color.RED);
		g.fillRect(x - size, (int) (y + size * 3), (int) (health * type / 1.3),
				10);
	}

	public void drawHCircle(int cx, int cy, int radius, int depth, Graphics g) {
		g.drawOval(cx, cy, radius * 2, radius * 2);
		if (depth > 0) { // what happens if you change the 8?
			drawHCircle(cx + radius * 3 / 2, cy + radius / 2, radius / 2,
					depth - 1, g);
			drawHCircle(cx - radius / 2, cy + radius / 2, radius / 2,
					depth - 1, g);
		}
	}

	public void drawVCircle(int cx, int cy, int radius, int depth, Graphics g) {
		g.drawOval(cx, cy, radius * 2, radius * 2);
		if (depth > 0) { // what happens if you change the 8?
			drawVCircle(cx + +radius / 2, cy + radius * 3 / 2, radius / 2,
					depth - 1, g);
			drawVCircle(cx + radius / 2, cy - radius / 2, radius / 2,
					depth - 1, g);
		}
	}

	public void fireStraight(int damage) {
		int x2 = x + size, y2 = y + size;
		Bullet newBullet = new Bullet(x2, y2, 30*type, 1, "enemy");
		switch (direction) {
		case 1: // move straight left
			newBullet.moveTo(0, y2 + 1);
			break;
		case 2: // move straight right
			newBullet.moveTo(1000, y2);
			break;
		case 3: // move straight up
			newBullet.moveTo(x2 + 1, 0);
			break;
		case 4: // move straight down
			newBullet.moveTo(x2, 700);
			newBullet.ty = 700;
			break;
		}
		Game.enemyBullets.add(newBullet);
	}

	public void fireFour(int damage) {
		for (int i = 1; i <= 4; i++) {
			int x2 = x + size, y2 = y + size;
			Bullet newBullet = new Bullet(x2, y2, 60, 1, "enemy");
			switch (i) {
			case 1: // move straight left
				newBullet.moveTo(0, y2 + 1);
				break;
			case 2: // move straight right
				newBullet.moveTo(1000, y2);
				break;
			case 3: // move straight up
				newBullet.moveTo(x2 + 1, 0);
				break;
			case 4: // move straight down
				newBullet.moveTo(x2, 700);
				newBullet.ty = 700;
				break;
			}
			Game.enemyBullets.add(newBullet);
		}
	}

	public void fireEight(int damage) {
		for (int i = 1; i <= 8; i++) {
			int x2 = x + size, y2 = y + size;
			Bullet newBullet = new Bullet(x2, y2, 100, 1, "enemy");
			int n = (int) (Math.sqrt(1000000));
			switch (i) {
			case 1: // move straight left
				newBullet.moveTo(0, y2 + 1);
				break;
			case 2: // move straight right
				newBullet.moveTo(1000, y2);
				break;
			case 3: // move straight up
				newBullet.moveTo(x2 + 1, 0);
				break;
			case 4: // move straight down
				newBullet.moveTo(x2, 700);
				break;
			case 5: // move diagonal right down
				newBullet.moveTo(newBullet.tx + n + 1, newBullet.ty + n + 1);
				break;
			case 6: // move diagonal left up
				newBullet.moveTo(newBullet.tx - n - 1, newBullet.ty - n - 1);
				break;
			case 7: // move diagonal right up
				newBullet.moveTo(newBullet.tx + n + 1, newBullet.ty - n - 1);
				break;
			case 8: // move diagonal left down
				newBullet.moveTo(newBullet.tx - n - 1, newBullet.ty + n + 1);
				break;
			}
			Game.enemyBullets.add(newBullet);
		}
	}

	public Ellipse2D.Double getBound() {
//		return new Rectangle(x, y, size * 2, size * 2);
		return new Ellipse2D.Double(x, y, size*2, size*2);
	}
}
