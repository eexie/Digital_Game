import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Bullet extends Unit {
	private int x, y; // x,y
	private static int size = 5; // width and height
	private int time, damage;
	public String type;

	// constructor
	public Bullet(int x, int y, int time, int damage, String type) {
		super(x, y, size);
		this.damage = damage;
		// System.out.println(tx+" "+ty);
		// System.out.println(x+" "+y);
		// System.out.println("Here");
		this.time = time;
		this.type = type;
	}

	public void moveTo(int tx, int ty) {
		this.tx = tx;
		this.ty = ty;
	}

	public void update() {
		time--;
		if ((x > 700 && y > 700) || time == 0)
			Game.bullets.remove(this);
		else {
			super.update();
			checkCollision();
		}

	}

	// returning all the necessary value of this class

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDamage() {
		return damage;
	}

	public void draw(Graphics g) {
		switch (damage) {
		case 2:
			g.setColor(Color.BLUE);
			break;
		case 4:
			g.setColor(Color.GREEN);
			break;
		case 6:
			g.setColor(Color.RED);
			break;
		}
		g.drawRect(getRect().x, getRect().y, getRect().width, getRect().height);
		g.fillRect(super.x - 5, super.y - 5, size * 2, size * 2);
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	private void checkCollision() {
		if (type == "bug"){
			for (int i = 0; i < Game.enemies.size(); i++) {
				Unit curr = Game.enemies.get(i);
				if (getRect().intersects(curr.getRect())) {
					System.out.println("intersect");
					if (((Enemy) curr).health > 0) {
						((Enemy) curr).health -= 1;
					}
					if (((Enemy) curr).health <= 0) {
						Game.score += 5;
						Game.enemies.remove(i);
					}
					Game.bullets.remove(this);
				}
			}
		}
		else{
			for (int i = 0; i < Game.bugs.size(); i++) {
				Unit curr = Game.bugs.get(i);
				if (getRect().intersects(curr.getRect())) {
					System.out.println("intersect");
					if (((Bug) curr).health > 0) {
						((Bug) curr).health -= 1;
					}
					if (((Bug) curr).health <= 0) {
						Game.score += 5;
						Game.bugs.remove(i);
					}
					Game.bullets.remove(this);
				}
			}
		}
		
	}
}
