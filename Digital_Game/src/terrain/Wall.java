package terrain;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Terrain {
	public Wall(int x,int y){
		super(x,y);
		super.solid=true;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(pos.getX(),pos.getY(),50,50);
	}
}
