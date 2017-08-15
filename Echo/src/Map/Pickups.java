package Map;

import java.awt.Graphics;

public interface Pickups extends java.io.Serializable{
	public int getX();
	public int getY();
	public void onPickup();
	public void draw(Graphics g, int scale);
	public boolean onGround();
}
