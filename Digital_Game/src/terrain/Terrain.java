package terrain;

import java.awt.Graphics;

public abstract class Terrain {
	protected Vector pos;
	protected boolean solid;
	protected String type;
	public Terrain(int x,int y){
		pos=new Vector(x,y);
	}
	public boolean getSolid(){
		return solid;
	}
	public abstract void draw(Graphics g);
}
