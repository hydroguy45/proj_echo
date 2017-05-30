package Map;

public interface Pickups extends java.io.Serializable{
	public int getX();
	public int getY();
	public void onPickup();
	public void draw();
	public boolean onGround();
}
