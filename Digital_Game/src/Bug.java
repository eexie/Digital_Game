import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import terrain.Terrain;

public class Bug extends Unit {
	private int type;
	private Image timg;
	static int SEPARATE_THRESHHOLD = 20;
	public boolean selected;
	public Bullet bullet;
	protected int health;
	public static int size = 25;

	public Bug(int x, int y) {
		super(x, y, size);
		type = 1;
		updateImage();
		health = 80 + 20 * type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) { // types are added together for new type
		this.type = type;
	}

	public void updateImage() {
		timg = new ImageIcon("t" + type + ".png").getImage();
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawImage(timg, x - size, y - size, size * 2, size * 2, null);
		if (selected) {
			g.setColor(new Color(240, 233, 34));
			((Graphics2D) g).setStroke(new BasicStroke(3));
			g.drawRect(x - size, y - size, size * 2, size * 2);
		}
		((Graphics2D) g).setStroke(new BasicStroke(1));
		g.setColor(Color.GREEN);
		g.fillRect(x - size, y - size, health / 2, 5);
	}

	public Image getImage() {
		return timg;
	}

	public void attack() {
		switch (type) {
		case 1:
			fire(40, 5);
			break;
		case 2:
			fire(40, 10);
			break;
		case 3:
			fire(80, 5);
			break;
		case 4:
			fire(80, 10);
			break;
		case 5:
			fire(80, 20);
			break;

		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void fire(int time, int damage) {
		Bullet newBullet = new Bullet(x, y, time, damage, "bug");
		// move toward nearest enemy
		Game.bullets.add(newBullet);
	}

	public int getHealth() {
		return health;
	}

	public void update(World map) { // collision rect

		for (Terrain i : map.getSect().getMap()) {
			if (i.getSolid()
					&& super.getCollision().intersects(i.getCollision())) {
				if (Math.abs(tx - x) > Math.abs(ty - y)) {
					if (x > i.getX())
						x = i.getX() + i.getSize() + 1 + size;
					else if (x < i.getX())
						x = i.getX() - i.getSize() - 1 - size;
				} else {
					if (y > i.getY())
						y = i.getY() + i.getSize() + 1 + size;
					else if (y < i.getY())
						y = i.getY() - i.getSize() - 1 - size;
				}
				tx = x;
				ty = y;
				System.out.println("box" + x + " " + y);
				System.out.println(i.getX() + " " + i.getY());
			} else {
				if (!i.getSolid()
						&& super.getCollision().intersects(i.getCollision())) {
					int active = i.active();
					switch (active) {
					case 0:
						break;
					case 1:
						System.out.print("We did it, O:");
						break;
					case 3:
						map.change(1);
						break;
					case 4:
						map.change(-1);
						break;

					// Check point captured. Show's the captured point.

					}
				}
				super.update();
			}
		}
	}

	public void moveTo(int tx, int ty) {
		this.tx = tx;
		this.ty = ty;
		this.tx += (int) (Math.random() * 50);
		this.tx -= (int) (Math.random() * 50);
		this.ty += (int) (Math.random() * 50);
		this.ty -= (int) (Math.random() * 50);
	}

	public void separate() {
		for (Unit i : Game.bugs) {
			if (i == this)
				continue;
			int distance = (int) Math.sqrt((Math.pow(this.x - i.x, 2) + Math
					.pow(this.y - i.y, 2)));
			if (distance < SEPARATE_THRESHHOLD) {

			}
		}
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
