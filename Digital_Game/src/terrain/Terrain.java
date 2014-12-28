package terrain;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Terrain {
	protected int x;
	private int y;
	protected boolean solid;
	protected String type;
	private int size=25;
	public Terrain(int x,int y){
		this.setX(x);
		this.setY(y);
	}
	public boolean getSolid(){
		return solid;
	}
	public abstract void draw(Graphics g);
	
	public Rectangle getRect(){
		return new Rectangle(getX(),getY(),getSize()*2,getSize()*2);
	}
	public String toString(){
		return "Position: "+getX()+" "+getY();
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
