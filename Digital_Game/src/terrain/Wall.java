package terrain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends Terrain {

	public Wall(int x, int y) {
		super(x, y);
		super.solid = true;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(super.x, super.getY(), getSize()*2, getSize()*2);

		g.drawRect(super.x, super.getY(), getSize()*2, getSize()*2);
	}
}
