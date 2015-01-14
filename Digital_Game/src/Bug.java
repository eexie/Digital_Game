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
	public boolean selected;
	public Bullet bullet;
	public static int size = 25;

	public Bug(int x, int y) {
		super(x, y);
		super.size = size;
		type = 1;
		updateImage();
		maxHealth = 100 + 100 * type;
		health = maxHealth;
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
		super.draw(g, Color.GREEN);
		g.setColor(Color.RED);
		g.drawImage(timg, x - size - cx, y - size - cy, size * 2, size * 2,
				null);
		if (selected) {
			g.setColor(new Color(240, 233, 34));
			((Graphics2D) g).setStroke(new BasicStroke(3));
			g.drawRect(x - size - cx, y - size - cy, size * 2, size * 2);
		}
		((Graphics2D) g).setStroke(new BasicStroke(1));

		// draw healthbar
		double one = (double)size*2/maxHealth;
		if (health > maxHealth / 2)
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
		g.fillRect(x - size - cx, y - size - cy, (int)(health*one), 5); // health bar
		// g.drawRect(x - size - cx, y - size - cy, size * 2, size *
		// 2);//collision box

	}

	public Image getImage() {
		return timg;
	}

	// public void attack() {
	// switch (type) {
	// case 1:
	// fire(40, 5);
	// break;
	// case 2:
	// fire(40, 10);
	// break;
	// case 3:
	// fire(80, 5);
	// break;
	// case 4:
	// fire(80, 10);
	// break;
	// case 5:
	// fire(80, 20);
	// break;
	//
	// }
	// }

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
		for (Terrain i : map.getSect().getMap()) {// check collision with
													// terrain
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
			} else {
				if (!i.getSolid()
						&& super.getCollision().intersects(i.getCollision())) {
					int active = i.active();

					// ACTIVES RETURNED BY THE TERRAIN
					//
					//
					//
					//
					//
					switch (active) {
					case 0:
						break;
					case 1:
						// Check point captured. Show's the captured point.
						break;
					case 2:
						// Moves to a different level of Map.
						map.change(1);
						break;
					case 3:
						map.change(-1);
						break;
					case 100: // picked up resource for new bug
						Game.bugs.add(new Bug(i.getX(), i.getY()));
						map.getSect().getMap().remove(i);
						break;
					}
				}
				super.update();
				switch (type) {
				case 1:
				case 2:
					damage = 1;
					break;
				case 3:
				case 4:
					damage = 2;
					break;
				case 5:
					damage = 5;
					break;
				}
				attack(1);
			}
		}

		// Mechanics.
		if (health > maxHealth) {
			health = maxHealth;
		}
	}

	public void moveTo(int tx, int ty) {
		attack = null;
		support = null;
		combat = false;
		this.tx = tx;
		this.ty = ty;
		// this.tx += (int) (Math.random() * 50);
		// this.tx -= (int) (Math.random() * 50);
		// this.ty += (int) (Math.random() * 50);
		// this.ty -= (int) (Math.random() * 50);
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
